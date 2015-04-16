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

import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP endpoint providing outbound tRFC (Transactional Remote Function Call) communication to SAP.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-qrfc-destination", syntax = "sap-qrfc-destination:destination:queue:rfc", producerOnly = true, title="SAP Queued RFC Destination")
public class SapQueuedRfcDestinationEndpoint extends SapRfcDestinationEndpoint {
	
    private static final Logger LOG = LoggerFactory.getLogger(SapQueuedRfcDestinationEndpoint.class);
    
	@UriPath(name = "queue", description = "Specifies the queue this endpoint sends an SAP request to") @Metadata(required = "true")
    protected String queueName;

	public SapQueuedRfcDestinationEndpoint() {
	}

	public SapQueuedRfcDestinationEndpoint(String endpointUri, SapQueuedRfcDestinationComponent component) {
		super(endpointUri, component);
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public Producer createProducer() throws Exception {
		LOG.debug("Created producer for endpoint '" + getEndpointUri() + "'");
		return new SapQueuedRfcProducer(this);
	}
}
