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

/**
 * An SAP consumer receiving a synchronous remote function call (sRFC) from an SAP system. 
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class SapSynchronousRfcConsumer extends SapConsumer implements JCoServerFunctionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcConsumer.class);

	public SapSynchronousRfcConsumer(SapSynchronousRfcServerEndpoint endpoint, Processor processor) {
		super(endpoint, processor);
	}

	public SapSynchronousRfcServerEndpoint getEndpoint() {
		return (SapSynchronousRfcServerEndpoint) super.getEndpoint();
	}

	@Override
	public void handleRequest(JCoServerContext serverContext, JCoFunction jcoFunction) throws AbapException, AbapClassException {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Handling request for RFC '{}'", jcoFunction.getName());
		}

		Exchange exchange = getEndpoint().createExchange(ExchangePattern.InOut);

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
				exchange.setProperty(SAP_SESSION_CONTEXT_PROPERTY_NAME, sessionContext);
			}
			message.setBody(request);

			// Process exchange
			getProcessor().process(exchange);

		} catch (Exception e) {
			throw new AbapException("ROUTE_EXCEPTION", e.getMessage());
		}
		
		if(exchange.getException() != null) {
			throw new AbapException("ROUTE_EXCEPTION", exchange.getException().getMessage());
		}
		
		// Return response
		Message message;
		if (exchange.hasOut()) {
			message = exchange.getOut();
		} else {
			message = exchange.getIn();
		}

		Structure response = message.getBody(Structure.class);
		if (LOG.isDebugEnabled()) {
			try {
				LOG.debug("Response: " + (response == null ? response : RfcUtil.marshal(response)));
			} catch (Exception e) {
				LOG.warn("Failed to log response", e);
			}
		}
		RfcUtil.fillJCoParameterListsFromResponse(response, jcoFunction);
		
	}

}
