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
import org.apache.camel.spi.UriEndpoint;
import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.util.ComponentDestinationDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the component that manages {@link SapIDocDestinationEndpoint}.
 * Maintains map of destination configurations that it registers and unregisters
 * with the SAP JCo runtime at component startup and shutdown.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
@UriEndpoint(scheme="sap-idoc-destination")
public class SapIDocDestinationComponent extends UriEndpointComponent {

	private static final Logger LOG = LoggerFactory.getLogger(SapIDocDestinationComponent.class);

	protected static final String DESTINATION_NAME_PREFIX = SapIDocDestinationComponent.class.getName();

	protected final DestinationDataStore destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
	
	public SapIDocDestinationComponent() {
		super(SapIDocDestinationEndpoint.class);
	}

    public void setDestinationDataStore(Map<String, DestinationData> destinationDataEntries) {
       	destinationDataStore.getEntries().clear();
    	for(Map.Entry<String, DestinationData> entry: destinationDataEntries.entrySet()) {
    		// Add component specific prefix to destination name to scope destination configurations to this component.
    		destinationDataStore.getEntries().put(DESTINATION_NAME_PREFIX + "." +entry.getKey(), entry.getValue());
    	}
    }
    
    public Map<String, DestinationData> getDestinationDataStore() {
    	return destinationDataStore.getEntries().map();
    }
    
	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		if (!uri.startsWith("sap-idoc-destination:")) { 
			throw new IllegalArgumentException("The URI '" +  uri + "' has invalid scheme; should be 'sap-idoc-destination:'");			
		}
		// Parse URI
		String[] uriComponents = remaining.split(":");

		if (uriComponents.length < 2) {
			throw new IllegalArgumentException("URI must be of the form: sap-idoc-destination:<destinationName>:<idocType>[<idocTypeExtension>[<systemRelease>[<applicationRelease>]]]");
		}

		// Extract URI components
		// Add component specific prefix to destination name to scope destination configurations to this component.
		parameters.put("destinationName", DESTINATION_NAME_PREFIX + "." + uriComponents[0]);
		parameters.put("idocType", uriComponents[1]);
		if(uriComponents.length > 2) {
			parameters.put("idocTypeExtension", uriComponents[2]);
		}
		if(uriComponents.length > 3) {
			parameters.put("systemRelease", uriComponents[3]);
		}
		if(uriComponents.length > 4) {
			parameters.put("applicationRelease", uriComponents[4]);
		}
		Endpoint endpoint = new SapIDocDestinationEndpoint(uri, this);

		// Configure Endpoint
		setProperties(endpoint, parameters);
		LOG.debug("Created endpoint '" + uri + "'");
		return endpoint;
	}

	@Override
    protected void doStart() throws Exception {
    	super.doStart();
    	ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
    	LOG.debug("STARTED");
    }
    
    @Override
    protected void doStop() throws Exception {
    	ComponentDestinationDataProvider.INSTANCE.removeDestinationDataStore(destinationDataStore);
    	super.doStop();
    	LOG.debug("STOPPED");
    }
}
