/**
 * Copyright 2014 Red Hat, Inc.
 * 
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.model.rfc.RepositoryDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.ServerData;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;
import org.fusesource.camel.component.sap.util.ComponentServerDataProvider;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoCustomRepository;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;

/**
 * Represents the component that manages {@link SapSynchronousRfcServerEndpoint}. Maintains
 * map of server configurations that it registers and unregisters with the SAP
 * JCo runtime at component startup and shutdown. It also maintains a map of
 * repository configurations that specifies the remote functions calls (RFC)
 * that are handled by the endpoints of this component.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class SapSynchronousRfcServerComponent extends UriEndpointComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcServerComponent.class);

	protected static final String SERVER_NAME_PREFIX = SapSynchronousRfcServerComponent.class.getName();

	protected final ServerDataStore serverDataStore = RfcFactory.eINSTANCE.createServerDataStore();
	
	protected final RepositoryDataStore repositoryDataStore = RfcFactory.eINSTANCE.createRepositoryDataStore();
	
	protected Map<String,JCoServer> activeServers = new HashMap<String,JCoServer>();
	
	protected Map<String,JCoCustomRepository> repositories = new HashMap<String,JCoCustomRepository>();
	
	protected ServerErrorAndExceptionListener serverErrorAndExceptionListener = new ServerErrorAndExceptionListener();
	
	protected ServerStateChangedListener serverStateChangedListener = new ServerStateChangedListener();

	public SapSynchronousRfcServerComponent() {
		super(SapSynchronousRfcServerEndpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		if (!uri.startsWith("sap-srfc-server:")) { 
			throw new IllegalArgumentException("The URI '" +  uri + "' has invalid scheme; should be 'sap-srfc-server:'");			
		}
		// Parse URI
		String[] uriComponents = remaining.split(":");

		if (uriComponents.length != 2) {
			throw new IllegalArgumentException("URI must be of the form: sap-srfc-server:<serverName>:<rfcName>");
		}
		
		// Extract URI components
		// Add component specific prefix to server name to scope server configurations to this component.
		parameters.put("serverName", SERVER_NAME_PREFIX + "." + uriComponents[0]);
		parameters.put("rfcName", uriComponents[1]);
		Endpoint endpoint = new SapSynchronousRfcServerEndpoint(uri, this);

		// Configure Endpoint
		setProperties(endpoint, parameters);
		LOG.debug("Created endpoint '" + uri + "'");
		return endpoint;
	}

    public Map<String, ServerData> getServerDataStore() {
    	return serverDataStore.getEntries().map();
    }
    
    public void setServerDataStore(Map<String, ServerData> serverDataEntries) {
    	serverDataStore.getEntries().clear();
    	for(Map.Entry<String, ServerData> entry: serverDataEntries.entrySet()) {
    		// Add component specific prefix to server name to scope server configurations to this component.
    		serverDataStore.getEntries().put(SERVER_NAME_PREFIX + "." + entry.getKey(), entry.getValue());
    	}
    }
    
    public Map<String, RepositoryData> getRepositoryDataStore() {
		return repositoryDataStore.getEntries().map();
	}

	public void setRepositoryDataStore(Map<String, RepositoryData> repositoryDataEntries) {
		this.repositoryDataStore.getEntries().clear();
    	for(Map.Entry<String, RepositoryData> entry: repositoryDataEntries.entrySet()) {
    		// Add component specific prefix to repository name to scope repository configurations to this component.
    		repositoryDataStore.getEntries().put(SERVER_NAME_PREFIX + "." +entry.getKey(), entry.getValue());
    	}
	}
	
	protected FunctionHandlerFactory getServerHandlerFactory(String serverName) throws Exception {
		JCoServer server = getServer(serverName);
		if (server == null) {
			return null;
		}
		return (FunctionHandlerFactory) server.getCallHandlerFactory();
	}
	synchronized protected JCoServer getServer(String serverName) throws Exception {
		JCoServer server = activeServers.get(serverName);
		if (server == null) {
			server = JCoServerFactory.getServer(serverName);
			
			server.setCallHandlerFactory(new FunctionHandlerFactory());
			server.setTIDHandler(new ServerTIDHandler());
			
			server.addServerExceptionListener(serverErrorAndExceptionListener);
			server.addServerErrorListener(serverErrorAndExceptionListener);
			server.addServerStateChangedListener(serverStateChangedListener);
			
			String repositoryDestinationName = server.getRepositoryDestination();
			JCoDestination repositoryDestination = null;
			try {
				repositoryDestination = JCoDestinationManager.getDestination(repositoryDestinationName);
			} catch (Exception e1) {
				LOG.warn("Unable to get repository destination'" + repositoryDestinationName + "' for server '" + serverName + "'", e1);
			}
			
			// Set up custom repository for inbound RFCs. 
			JCoCustomRepository repository = getRepository(serverName);
			if (repository != null) {
				if (repositoryDestination != null) {
					try {
						repository.setDestination(repositoryDestination);
					} catch (Exception e) {
						LOG.warn("Unable to set destination on custom repository for server '" + serverName + "'", e);
					}
				}
				server.setRepository(repository);
			}
						
			activeServers.put(serverName, server);
			
			if (isStarted()) {
				server.start();
				LOG.debug("Started server " + server.getProgramID());
			}
		}
		return server;
	}
	
	synchronized protected JCoCustomRepository getRepository(String serverName) {
		JCoCustomRepository repository = repositories.get(serverName);
		if (repository == null) {
			RepositoryData repositoryData = repositoryDataStore.getEntries().get(serverName);
			if (repositoryData != null) {
				repository = RfcUtil.createRepository(serverName, repositoryData);
				repositories.put(serverName, repository);
			}
		}
		return repository;
	}

	@Override
    protected void doStart() throws Exception {
    	super.doStart();
    	ComponentServerDataProvider.INSTANCE.addServerDataStore(serverDataStore);
    	for(JCoServer server: activeServers.values()) {
    		server.start();
    	}
    }
    
    @Override
    protected void doStop() throws Exception {
    	ComponentServerDataProvider.INSTANCE.removeServerDataStore(serverDataStore);
    	super.doStop();
    }
}
