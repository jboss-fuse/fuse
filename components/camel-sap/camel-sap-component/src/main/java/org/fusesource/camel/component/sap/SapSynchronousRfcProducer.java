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

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP producer performing a synchronous remote function call (sRFC) to an remote function module (RFM) in SAP. 
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class SapSynchronousRfcProducer extends DefaultProducer {

	private static final transient Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcProducer.class);

	public SapSynchronousRfcProducer(SapSynchronousRfcDestinationEndpoint endpoint) {
		super(endpoint);
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		if (getEndpoint().isTransacted()) {
			// Ensure that an SAP transaction for destination has begun and is handled by this exchange.
			DestinationSapTransactionHandler.ensureSapTransactionHasBegunAndIsHandled(exchange, getEndpoint().getDestination());
		} else if (getEndpoint().isStateful()) {
			// Ensure that an SAP stateful session for destination has begun and is
			// handled by this exchange.
			DestinationSapStatefulSessionHandler.ensureSapStatefulSessionHasBegunAndIsHandled(exchange, getEndpoint().getDestination());
		}

		Structure request = exchange.getIn().getBody(Structure.class);
		if (LOG.isDebugEnabled()) {
			try {
				LOG.debug("Calling '{}' RFC", getEndpoint().getRfcName());
				LOG.debug("Request: " + (request == null ? request : RfcUtil.marshal(request)));
			} catch (Exception e) {
				LOG.warn("Failed to log request", e);
			}
		}
		Structure response = RfcUtil.executeFunction(getEndpoint().getDestination(), getEndpoint().getRfcName(), request);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response: " + (response == null ? response : RfcUtil.marshal(response)));
		}
		exchange.setOut(exchange.getIn().copy());
		exchange.getOut().setBody(response);
	}
	
	@Override
	public SapSynchronousRfcDestinationEndpoint getEndpoint() {
		return (SapSynchronousRfcDestinationEndpoint) super.getEndpoint();
	}

}
