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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.util.ComponentRepositoryDataProvider;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoCustomRepository;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerState;

/**
 * An SAP component that manages {@link SapTransactionalRfcServerEndpoint}
 * .
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public abstract class SapRfcServerComponent extends UriEndpointComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SapRfcServerComponent.class);

	protected File tidStoresLocation = new File(".");

	protected Map<String, JCoServer> activeServers = new HashMap<String, JCoServer>();

	protected Map<String, JCoCustomRepository> repositories = new HashMap<String, JCoCustomRepository>();

	protected ServerErrorAndExceptionListener serverErrorAndExceptionListener = new ServerErrorAndExceptionListener();

	protected ServerStateChangedListener serverStateChangedListener = new ServerStateChangedListener();

	public SapRfcServerComponent(Class<? extends Endpoint> endpointClass) {
		super(endpointClass);
	}

	public String getTidStoresLocation() {
		return tidStoresLocation.getAbsolutePath();
	}

	public void setTidStoresLocation(String tidStoresLocation) {
		this.tidStoresLocation = new File(tidStoresLocation);
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
			
			if (server.getState() != JCoServerState.STOPPED) {
				// Another application has already registered and started this server connection.
				throw new Exception("The server connection '" + serverName + "' is already in use");
			}

			server.setCallHandlerFactory(new FunctionHandlerFactory());
			
			File tidStoreFile = new File(tidStoresLocation, serverName);
			server.setTIDHandler(new ServerTIDHandler(tidStoreFile));

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
			RepositoryData repositoryData = ComponentRepositoryDataProvider.INSTANCE.getRepositoryData(serverName);
			repository = RfcUtil.createRepository(serverName, repositoryData);
			repositories.put(serverName, repository);
		}
		return repository;
	}

	@Override
	protected void doStart() throws Exception {
		super.doStart();
		for (JCoServer server : activeServers.values()) {
			server.start();
		}
	}
	
	@Override
	protected void doStop() throws Exception {
		for (JCoServer server : activeServers.values()) {
			server.stop();
			server.removeServerErrorListener(serverErrorAndExceptionListener);
			server.removeServerExceptionListener(serverErrorAndExceptionListener);
			server.removeServerStateChangedListener(serverStateChangedListener);
			server.setTIDHandler(null);
			server.release();
		}
		super.doStop();
	}
}
