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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerFunctionHandlerFactory;

/**
 * Function Handler Factory which enables function handlers to be registered and
 * unregistered.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class FunctionHandlerFactory implements JCoServerFunctionHandlerFactory {

	private static final Logger LOG = LoggerFactory.getLogger(FunctionHandlerFactory.class);

	private Map<String, JCoServerFunctionHandler> callHandlers = new HashMap<String, JCoServerFunctionHandler>();
	private Map<String, SapServerSessionContext> statefulSessionContexts = new HashMap<String, SapServerSessionContext>();

	public void registerHandler(String functionName, JCoServerFunctionHandler handler) {
		callHandlers.put(functionName, handler);
	}

	public JCoServerFunctionHandler unregisterHandler(String functionName) {
		return callHandlers.remove(functionName);
	}

	@Override
	public void sessionClosed(JCoServerContext serverContext, String message, boolean error) {
		statefulSessionContexts.remove(serverContext.getSessionID());
		LOG.debug("Session " + serverContext.getSessionID() + " was closed " + (error ? message : "by SAP system"));
	}

	@Override
	public JCoServerFunctionHandler getCallHandler(JCoServerContext serverContext, String functionName) {
		JCoServerFunctionHandler handler = callHandlers.get(functionName);
		if (handler instanceof SapConsumer) {
			SapConsumer consumer = (SapConsumer) handler;
			if (consumer.isStateful()) {
				// Manage stateful session.
				SapServerSessionContext sessionContext;
				if (!serverContext.isStatefulSession()) {
					// start new session with new session context
					serverContext.setStateful(true);
					sessionContext = new SapServerSessionContext();
					statefulSessionContexts.put(serverContext.getSessionID(), sessionContext);
				} else {
					// retrieve session context of current session.
					sessionContext = statefulSessionContexts.get(serverContext.getSessionID());
					if (sessionContext == null) {
						throw new RuntimeException("Failed to find session context for session '" + serverContext.getSessionID() + "'");
					}
				}
				consumer.setSessionContext(sessionContext);
			}
		}
		return handler;
	}

}
