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
import org.apache.camel.spi.UriEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP endpoint providing outbound tRFC (Transactional Remote Function Call) communication to SAP.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-trfc-destination", syntax = "sap-trfc-destination:destinationName:rfcName", title="SAP")
public class SapTransactionalRfcDestinationEndpoint extends SapRfcDestinationEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(SapTransactionalRfcDestinationEndpoint.class);

	public SapTransactionalRfcDestinationEndpoint() {
	}

	public SapTransactionalRfcDestinationEndpoint(String endpointUri, SapTransactionalRfcDestinationComponent component) {
		super(endpointUri, component);
	}

	@Override
	public Producer createProducer() throws Exception {
		LOG.debug("Created producer for endpoint '" + getEndpointUri() + "'");
		return new SapTransactionalRfcProducer(this);
	}
}
