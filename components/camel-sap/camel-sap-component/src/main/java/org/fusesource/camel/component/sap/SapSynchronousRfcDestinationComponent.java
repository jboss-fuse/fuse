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

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP component that manages {@link SapSynchronousRfcDestinationEndpoint}.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class SapSynchronousRfcDestinationComponent extends UriEndpointComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcDestinationComponent.class);
	
	public SapSynchronousRfcDestinationComponent() {
		super(SapSynchronousRfcDestinationEndpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		if (!uri.startsWith("sap-srfc-destination:")) { 
			throw new IllegalArgumentException("The URI '" +  uri + "' has invalid scheme; should be 'sap-srfc-destination:'");			
		}
		// Parse URI
		String[] uriComponents = remaining.split(":");

		if (uriComponents.length != 2) {
			throw new IllegalArgumentException("URI must be of the form: sap-srfc-destination:<destination>:<rfc>");
		}

		// Extract URI components
		// Add component specific prefix to destination name to scope destination configurations to this component.
		parameters.put("destinationName", uriComponents[0]); 
		parameters.put("rfcName", uriComponents[1]);
		SapSynchronousRfcDestinationEndpoint endpoint = new SapSynchronousRfcDestinationEndpoint(uri, this);

		// Configure Endpoint
		setProperties(endpoint, parameters);
		LOG.debug("Created endpoint '" + uri + "'");
		
		// Create a request to ensure that the data layer's package registry is
		// loaded with the schema of this endpoint's request and response types.
		endpoint.createRequest();
		
		return endpoint;
	}

	@Override
    protected void doStart() throws Exception {
    	super.doStart();
    	LOG.debug("STARTED");
    }
    
    @Override
    protected void doStop() throws Exception {
    	super.doStop();
    	LOG.debug("STOPPED");
    }
}
