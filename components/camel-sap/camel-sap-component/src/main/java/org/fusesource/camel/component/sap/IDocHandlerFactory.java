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

import com.sap.conn.idoc.IDocDocumentList;
import com.sap.conn.idoc.jco.JCoIDocHandler;
import com.sap.conn.idoc.jco.JCoIDocHandlerFactory;
import com.sap.conn.idoc.jco.JCoIDocServerContext;
import com.sap.conn.jco.server.JCoServerContext;

/**
 * IDoc Handler Factory which enables IDoc handlers to be registered and unregistered.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class IDocHandlerFactory implements JCoIDocHandlerFactory {
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
