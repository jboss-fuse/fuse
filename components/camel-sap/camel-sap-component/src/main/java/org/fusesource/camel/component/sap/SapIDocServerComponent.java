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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.server.JCoServer;

/**
 * Represents the component that manages {@link SapIDocServerEndpoint}. Maintains
 * map of server configurations that it registers and unregisters with the SAP
 * JCo runtime at component startup and shutdown. 
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class SapIDocServerComponent extends UriEndpointComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SapIDocServerComponent.class);

	protected Map<String,JCoIDocServer> activeServers = new HashMap<String,JCoIDocServer>();

	protected ServerErrorAndExceptionListener serverErrorAndExceptionListener = new ServerErrorAndExceptionListener();
	
	protected ServerStateChangedListener serverStateChangedListener = new ServerStateChangedListener();

	public SapIDocServerComponent() {
		super(SapIDocServerEndpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		if (!uri.startsWith("sap-idoc-server:")) { 
			throw new IllegalArgumentException("The URI '" +  uri + "' has invalid scheme; should be 'sap-idoc-server:'");			
		}
		// Parse URI
		String[] uriComponents = remaining.split(":");

		if (uriComponents.length < 2) {
			throw new IllegalArgumentException("URI must be of the form: sap-idoc-server:<serverName>:<idocType>[:<idocTypeExtension>[:<systemRelease>[:<applicationRelease>]]]");
		}
		
		// Extract URI components
		// Add component specific prefix to server name to scope server configurations to this component.
		parameters.put("serverName", uriComponents[0]);
		parameters.put("idocType", uriComponents[1]);
		if(uriComponents.length > 2) {
			parameters.put("idocTypeExtension", uriComponents[2]);
		}
		if(uriComponents.length > 3) {
			parameters.put("systemRelease", uriComponents[3]);
		}
		if(uriComponents.length > 4) {
			parameters.put("applicationRelease", uriComponents[4]);
		}
		Endpoint endpoint = new SapIDocServerEndpoint(uri, this);

		// Configure Endpoint
		setProperties(endpoint, parameters);
		LOG.debug("Created endpoint '" + uri + "'");
		return endpoint;
	}

	synchronized protected JCoIDocServer getServer(String serverName) throws Exception {
		JCoIDocServer server = activeServers.get(serverName);
		if (server == null) {
			server = JCoIDoc.getServer(serverName);
			
			server.setIDocHandlerFactory(new IDocHandlerFactory());
			server.setTIDHandler(new ServerTIDHandler(serverName));
			
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
			
			// Set up IDoc repository for inbound documents.
			if (repositoryDestination != null) {
				try {
					IDocRepository idocRepository = JCoIDoc.getIDocRepository(repositoryDestination);
					server.setIDocRepository(idocRepository);
				} catch (Exception e) {
					LOG.warn("Unable to set IDoc repository on server '" + serverName + "'", e);
				}
			}
			
			activeServers.put(serverName, server);
			
			if (isStarted()) {
				server.start();
				LOG.debug("Started server " + server.getProgramID());
			}
		}
		return server;
	}

	protected IDocHandlerFactory getIDocHandlerFactory(String serverName) throws Exception {
		JCoIDocServer server = getServer(serverName);
		if (server == null) {
			return null;
		}
		return (IDocHandlerFactory) server.getIDocHandlerFactory();
	}
	
	@Override
    protected void doStart() throws Exception {
    	super.doStart();
    	for(JCoServer server: activeServers.values()) {
    		server.start();
    	}
    	LOG.debug("STARTED");
   }
    
    @Override
    protected void doStop() throws Exception {
    	super.doStop();
    	LOG.debug("STOPPED");
    }
}
