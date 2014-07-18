/**
 * Copyright 2013 Red Hat, Inc.
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
import org.apache.camel.impl.DefaultComponent;
import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.model.rfc.RepositoryDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.ServerData;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;
import org.fusesource.camel.component.sap.util.ComponentDestinationDataProvider;
import org.fusesource.camel.component.sap.util.ComponentServerDataProvider;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocDocumentList;
import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.idoc.jco.JCoIDocHandler;
import com.sap.conn.idoc.jco.JCoIDocHandlerFactory;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.idoc.jco.JCoIDocServerContext;
import com.sap.conn.jco.JCoCustomRepository;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerFunctionHandlerFactory;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;
import com.sap.conn.jco.server.JCoServerTIDHandler;

/**
 * Represents the component that manages {@link SAPDestinationEndpoint} and {@link SAPServerEndpoint} instances.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class SAPComponent extends DefaultComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SAPComponent.class);
	
	class FunctionHandlerFactory implements JCoServerFunctionHandlerFactory {
		
		class SessionContext {
			Map<String, Object> cachedSessionData = new HashMap<String, Object>(); 
		}
		
		private Map<String, JCoServerFunctionHandler> callHandlers = new HashMap<String, JCoServerFunctionHandler>();
		private Map<String, SessionContext> statefulSessions = new HashMap<String, SessionContext>();
		
		public void registerHandler(String functionName, JCoServerFunctionHandler handler) {
			callHandlers.put(functionName, handler);
		}

		public JCoServerFunctionHandler unregisterHandler(String functionName) {
			return callHandlers.remove(functionName);
		}

		@Override
		public void sessionClosed(JCoServerContext serverContext, String arg1, boolean arg2) {
			statefulSessions.remove(serverContext.getSessionID());
		}

		@Override
		public JCoServerFunctionHandler getCallHandler(JCoServerContext serverContext, String functionName) {
			JCoServerFunctionHandler handler = callHandlers.get(functionName);
			return handler;
		}
		
	}
	
	public class ServerErrorAndExceptionListener implements JCoServerErrorListener, JCoServerExceptionListener {

		@Override
		public void serverExceptionOccurred(JCoServer jcoServer, String connectionId,
				JCoServerContextInfo serverContext, Exception exception) {
			LOG.warn(">>> Exception occured on " + jcoServer.getProgramID() + " connection " + connectionId, exception);
		}

		@Override
		public void serverErrorOccurred(JCoServer jcoServer, String connectionId,
				JCoServerContextInfo serverContext, Error error) {
			LOG.warn(">>> Error occured on " + jcoServer.getProgramID() + " connection " + connectionId, error);
		}
		
	}
	
	public class ServerStateChangedListener implements JCoServerStateChangedListener {

		@Override
		public void serverStateChangeOccurred(JCoServer jcoServer,
				JCoServerState oldState, JCoServerState newState) {
			LOG.info(">>> Server state changed from " + oldState.toString() + " to " + newState.toString() + " on " + jcoServer.getProgramID());
		}
		
	}

	public static class IDocHandlerFactory implements JCoIDocHandlerFactory {
		
		private Map<String, JCoIDocHandler> iDocHandlers = new HashMap<String, JCoIDocHandler>();

		JCoIDocHandler iDocHandler = new JCoIDocHandler() {
			@Override
			public void handleRequest(JCoServerContext serverContext,
					IDocDocumentList iDocDocumentList) {
				String key = createKey(iDocDocumentList.getIDocType(), iDocDocumentList.getIDocTypeExtension(), iDocDocumentList.getSystemRelease(), iDocDocumentList.getApplicationRelease());
				JCoIDocHandler iDocHandler = iDocHandlers.get(key);
				if (iDocHandler != null) {
					iDocHandler.handleRequest(serverContext, iDocDocumentList);
				}
			}
		};
		
		public void registerHandler(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease, JCoIDocHandler iDocHandler) {
			iDocHandlers.put(createKey(iDocType, iDocTypeExtension, systemRelease, applicationRelease), iDocHandler);
		}

		public JCoIDocHandler unregisterHandler(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
			return iDocHandlers.remove(createKey(iDocType, iDocTypeExtension, systemRelease, applicationRelease));
		}

		@Override
		public JCoIDocHandler getIDocHandler(JCoIDocServerContext serverContext) {
			return iDocHandler;
		}
		
		/**
		 * Create key for JCoIDocHandler map
		 * <p>Key string in the form: "IDoc Type|IDoc Type Extension|System Release|Application Release".
		 * 
		 * @param iDocType - IDoc type
		 * @param iDocTypeExtension - IDoc type extenstion
		 * @param systemRelease - System release
		 * @param applicationRelease - Application release
		 * @return Key string in the form: "IDoc Type|IDoc Type Extension|System Release|Application Release" 
		 */
		protected String createKey(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
			StringBuilder key = new StringBuilder(100);
			
			key.append(iDocType);
			key.append("|");
			if(iDocTypeExtension != null) key.append(iDocTypeExtension);
			key.append("|");
			if(systemRelease != null) key.append(systemRelease.replace(".", ""));
			key.append("|");
			if(applicationRelease != null) key.append(applicationRelease.replace(".", ""));
			
			return key.toString();
		}
		
	}

	// TODO Refactor this as a model object
    private enum TIDState
    {
        CREATED, EXECUTED, COMMITTED, ROLLED_BACK, CONFIRMED;
    }

    public class ServerTIDHandler implements JCoServerTIDHandler {

		// TODO Create a TID repository model object and persist
        Map<String, TIDState> availableTIDs = new HashMap<String, TIDState>();

        @Override
		public boolean checkTID(JCoServerContext serverContext, String tid) {
			TIDState state = availableTIDs.get(tid);
			if (state == null) {
				availableTIDs.put(tid, TIDState.CREATED);
				LOG.debug("Checked TID '" + tid +"': true");
				return true;
			}
			
			if(state == TIDState.CREATED || state == TIDState.ROLLED_BACK) {
				LOG.debug("Checked TID '" + tid +"': true");
				return true;
			}

			LOG.debug("Checked TID '" + tid +"': false");
			return false;
		}

		@Override
		public void commit(JCoServerContext serverContext, String tid) {
			availableTIDs.put(tid, TIDState.COMMITTED);
			LOG.debug("Committed TID '" + tid +"'");
		}

		@Override
		public void rollback(JCoServerContext serverContext, String tid) {
			availableTIDs.put(tid, TIDState.ROLLED_BACK);
			LOG.debug("Rolledback TID '" + tid +"'");
		}
		
		@Override
		public void confirmTID(JCoServerContext serverContext, String tid) {
			availableTIDs.remove(tid);
			LOG.debug("Confirmed TID '" + tid +"'");
		}
		
		public void execute(JCoServerContext serverContext) {
			String tid = serverContext.getTID();
			if (tid != null) {
				availableTIDs.put(tid, TIDState.EXECUTED);
				LOG.debug("Executed TID '" + tid + "'");
			}
		}

	}

    protected final DestinationDataStore destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
	
	protected final ServerDataStore serverDataStore = RfcFactory.eINSTANCE.createServerDataStore();
	
	protected final RepositoryDataStore repositoryDataStore = RfcFactory.eINSTANCE.createRepositoryDataStore();
	
	protected Map<String,JCoIDocServer> activeServers = new HashMap<String,JCoIDocServer>();
	
	protected Map<String,JCoCustomRepository> repositories = new HashMap<String,JCoCustomRepository>();
	
	protected ServerErrorAndExceptionListener serverErrorAndExceptionListener = new ServerErrorAndExceptionListener();
	
	protected ServerStateChangedListener serverStateChangedListener = new ServerStateChangedListener();
	
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

		if (uri.startsWith("sap:")) { // RFC Endpoint
			// Parse URI
			String[] uriComponents = remaining.split(":");

			if (uriComponents.length != 3) {
				throw new IllegalArgumentException("URI must be of the form: sap:[destination:<destinationName>|server:<serverName>]:<rfcName>");
			}

			// Extract URI components
			Endpoint endpoint;
			parameters.put("rfcName", uriComponents[2]);
			if (uriComponents[0].equals("destination")) {
				parameters.put("destinationName", uriComponents[1]);
				endpoint = new SAPDestinationEndpoint(uri, this);
			} else if (uriComponents[0].equals("server")) {
				parameters.put("serverName", uriComponents[1]);
				endpoint = new SAPServerEndpoint(uri, this);
			} else {
				throw new IllegalArgumentException("Must specify 'client' or 'server' in URL");
			}
			
			// Configure Endpoint
			setProperties(endpoint, parameters);
			return endpoint;
		} else if (uri.startsWith("idoc:")) { // IDoc Endpoint
			// Parse URI
			String[] urlComponents = remaining.split(":");
			
			if (urlComponents.length < 3) {
				throw new IllegalArgumentException("URI must be of the form: idoc:[destination:<destinationName>|server:<serverName>]:<idocType>[<idocTypeExtension>[<systemRelease>[<applicationRelease>]]]");
			}
			
			// Extract URI components
			Endpoint endpoint;
			parameters.put("idocType", urlComponents[2]);
			if(urlComponents.length > 3) {
				parameters.put("idocTypeExtension", urlComponents[3]);
			}
			if(urlComponents.length > 4) {
				parameters.put("systemRelease", urlComponents[4]);
			}
			if(urlComponents.length > 5) {
				parameters.put("applicationRelease", urlComponents[5]);
			}
			if (urlComponents[0].equals("destination")) {
				parameters.put("destinationName", urlComponents[1]);
				endpoint = new IDocDestinationEndpoint(uri, this);
			} else if (urlComponents[0].equals("server")) {
				parameters.put("serverName", urlComponents[1]);
				endpoint = new IDocServerEndpoint(uri, this);
			} else {
				throw new IllegalArgumentException("Must specify 'client' or 'server' in URL");
			}

			// Configure Endpoint
			setProperties(endpoint, parameters);
			return endpoint;
	    } else  {
			throw new IllegalArgumentException("The uri does not contain a supported scheme");
		}
    }
    
    public void setDestinationDataStore(Map<String, DestinationData> destinationDataEntries) {
    	destinationDataStore.getEntries().clear();
    	destinationDataStore.getEntries().putAll(destinationDataEntries);
    }
    
    public Map<String, DestinationData> getDestinationDataStore() {
    	return destinationDataStore.getEntries().map();
    }
    
    public void setServerDataStore(Map<String, ServerData> serverDataEntries) {
    	serverDataStore.getEntries().clear();
    	serverDataStore.getEntries().putAll(serverDataEntries);
    }
    
    public Map<String, ServerData> getServerDataStore() {
    	return serverDataStore.getEntries().map();
    }
    
    public Map<String, RepositoryData> getRepositoryDataStore() {
		return repositoryDataStore.getEntries().map();
	}

	public void setRepositoryDataStore(Map<String, RepositoryData> repositoryDataEntries) {
		this.repositoryDataStore.getEntries().clear();
		this.repositoryDataStore.getEntries().putAll(repositoryDataEntries);
	}
	
	synchronized protected JCoIDocServer getServer(String serverName) throws Exception {
		JCoIDocServer server = activeServers.get(serverName);
		if (server == null) {
			server = JCoIDoc.getServer(serverName);
			
			server.setCallHandlerFactory(new FunctionHandlerFactory());
			server.setIDocHandlerFactory(new IDocHandlerFactory());
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
	
	protected FunctionHandlerFactory getServerHandlerFactory(String serverName) throws Exception {
		JCoServer server = getServer(serverName);
		if (server == null) {
			return null;
		}
		return (FunctionHandlerFactory) server.getCallHandlerFactory();
	}
	
	protected IDocHandlerFactory getIDocHandlerFactory(String serverName) throws Exception {
		JCoIDocServer server = getServer(serverName);
		if (server == null) {
			return null;
		}
		return (IDocHandlerFactory) server.getIDocHandlerFactory();
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
    	ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
    	ComponentServerDataProvider.INSTANCE.addServerDataStore(serverDataStore);
    	for(JCoServer server: activeServers.values()) {
    		server.start();
    	}
    }
    
    @Override
    protected void doStop() throws Exception {
    	ComponentDestinationDataProvider.INSTANCE.removeDestinationDataStore(destinationDataStore);
    	ComponentServerDataProvider.INSTANCE.removeServerDataStore(serverDataStore);
    	super.doStop();
    }
}
