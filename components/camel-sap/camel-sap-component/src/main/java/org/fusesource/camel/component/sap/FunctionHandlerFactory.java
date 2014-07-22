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

import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerFunctionHandlerFactory;

/**
 * Function Handler Factory which enables function handlers to be registered and unregistered.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class FunctionHandlerFactory implements JCoServerFunctionHandlerFactory {

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
