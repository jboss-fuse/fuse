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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerTIDHandler;

/**
 * An SAP consumer receiving a transactional remote function call (tRFC) from an SAP system. 
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class SapTransactionalRfcConsumer extends SapConsumer implements JCoServerFunctionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(SapTransactionalRfcConsumer.class);

	Map<String, Object> sessionData = new HashMap<String, Object>();

	public SapTransactionalRfcConsumer(SapTransactionalRfcServerEndpoint endpoint, Processor processor) {
		super(endpoint, processor);
	}

	@Override
	public SapTransactionalRfcServerEndpoint getEndpoint() {
		return (SapTransactionalRfcServerEndpoint) super.getEndpoint();
	}

	@Override
	public void handleRequest(JCoServerContext serverContext, JCoFunction jcoFunction) throws AbapException, AbapClassException {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handling request for RFC '{}'", jcoFunction.getName());
		}

		Exchange exchange = getEndpoint().createExchange(ExchangePattern.InOnly);

		// Create Request structure
		Structure request = RfcUtil.getRequest(serverContext.getRepository(), jcoFunction.getName());
		RfcUtil.extractJCoParameterListsIntoRequest(jcoFunction, request);
		if (LOG.isDebugEnabled()) {
			try {
				LOG.debug("Request: " + (request == null ? request : RfcUtil.marshal(request)));
			} catch (IOException e) {
				LOG.warn("Failed to log request", e);
			}
		}

		try {

			// Populated request
			Message message = exchange.getIn();
			if (isStateful()) {
				exchange.setProperty(SAP_SESSION_CONTEXT_PROPERTY_NAME, getSessionContext());
			}
			message.setBody(request);

			// Process exchange
			getProcessor().process(exchange);

		} catch (Exception e) {
			if(getEndpoint().isPropagateExceptions()) {
				throw new AbapException("ROUTE_EXCEPTION", e.getMessage());
			} else {
				getExceptionHandler().handleException("Failed to process request", e);
			}
		}
		
		if(exchange.getException() != null && getEndpoint().isPropagateExceptions()) {
			throw new AbapException("ROUTE_EXCEPTION", exchange.getException().getMessage());
		}

		JCoServerTIDHandler jcoServerTidHandler = serverContext.getServer().getTIDHandler();
		if (jcoServerTidHandler instanceof ServerTIDHandler) {
			((ServerTIDHandler)jcoServerTidHandler).execute(serverContext);
		}
	}

}
