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

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP endpoint providing inbound tRFC (Transactional Remote Function Call) communication from SAP.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-trfc-server", consumerClass=SapTransactionalRfcConsumer.class, syntax = "sap-trfc-server:server:rfc", consumerOnly = true, title="SAP Transactional RFC Server")
public class SapTransactionalRfcServerEndpoint extends SapRfcServerEndpoint {
	
    private static final Logger LOG = LoggerFactory.getLogger(SapTransactionalRfcServerEndpoint.class);

	@UriParam(name = "propagateExceptions", description = "When true, specifies that this endpoint will propagate exceptions back to SAP instead of the exchange's exception handler", defaultValue = "false")
	protected boolean propagateExceptions;

	public SapTransactionalRfcServerEndpoint() {
	}

	public SapTransactionalRfcServerEndpoint(String endpointUri, SapTransactionalRfcServerComponent component) {
		super(endpointUri, component);
	}

	public boolean isPropagateExceptions() {
		return propagateExceptions;
	}

	public void setPropagateExceptions(boolean propagateExceptions) {
		this.propagateExceptions = propagateExceptions;
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		LOG.debug("Created consumer for endpoint '" + getEndpointUri() + "'");
		FunctionHandlerFactory handlerFactory = getComponent().getServerHandlerFactory(serverName);
		if (handlerFactory == null) {
			throw new IllegalStateException("Function Handler Factory for '" + serverName + "' missing.");
		}
		SapTransactionalRfcConsumer consumer = new SapTransactionalRfcConsumer(this, processor);
		if (isStateful()) {
			consumer.setStateful(true);
		}
		handlerFactory.registerHandler(getRfcName(), consumer);
		return consumer;
	}

	@Override
	public SapTransactionalRfcServerComponent getComponent() {
		return (SapTransactionalRfcServerComponent) super.getComponent();
	}

}
