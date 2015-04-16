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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP endpoint providing inbound sRFC (Synchronous Remote Function Call) communication from SAP.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-srfc-server", consumerClass=SapSynchronousRfcConsumer.class, syntax = "sap-srfc-server:server:rfc", consumerOnly = true, title="SAP Synchronous RFC Server")
public class SapSynchronousRfcServerEndpoint extends SapRfcServerEndpoint {
	
    private static final Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcServerEndpoint.class);

	public SapSynchronousRfcServerEndpoint() {
	}

	public SapSynchronousRfcServerEndpoint(String endpointUri, SapSynchronousRfcServerComponent component) {
		super(endpointUri, component);
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		LOG.debug("Created consumer for endpoint '" + getEndpointUri() + "'");
		FunctionHandlerFactory handlerFactory = getComponent().getServerHandlerFactory(serverName);
		if (handlerFactory == null) {
			throw new IllegalStateException("Function Handler Factory for '" + serverName + "' missing.");
		}
		SapSynchronousRfcConsumer consumer = new SapSynchronousRfcConsumer(this, processor);
		if(isStateful()) {
			consumer.setStateful(true);
		}
		handlerFactory.registerHandler(getRfcName(), consumer);
		return consumer;
	}

	@Override
	public SapSynchronousRfcServerComponent getComponent() {
		return (SapSynchronousRfcServerComponent) super.getComponent();
	}

}
