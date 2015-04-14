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

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;

/**
 * Base class for SAP RFC (Remote Function Call) Endpoint types.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public abstract class SapRfcDestinationEndpoint extends DefaultEndpoint {
	
    private static final Logger LOG = LoggerFactory.getLogger(SapRfcDestinationEndpoint.class);

	@UriPath(name = "destination", description = "Specifies the destination this endpoint sends an SAP request to") @Metadata(required = "true")
    protected String destinationName;
	
	@UriPath(name = "rfc", description = "Specifies the Remote Function Module this endpoint sends an SAP request to") @Metadata(required = "true")
	protected String rfcName;
	
	@UriParam(name = "transacted", description = "When true, specifies that this endpoint will initiate an SAP transaction", defaultValue = "false")
	protected boolean transacted;
	
	@UriParam(name = "stateful", description = "When true, specifies that this endpoint will initiate an SAP stateful session", defaultValue = "false")
	protected boolean stateful;
	
	protected JCoDestination destination;
	

	public SapRfcDestinationEndpoint() {
	}

	public SapRfcDestinationEndpoint(String endpointUri, Component component) {
		super(endpointUri, component);
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		throw new UnsupportedOperationException(
				"Endpoint '"  + getEndpointUri() + "' does not support consumers");
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getRfcName() {
		return rfcName;
	}

	public void setRfcName(String rfcName) {
		this.rfcName = rfcName;
	}

	public boolean isTransacted() {
		return transacted;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public boolean isStateful() {
		return stateful;
	}

	public void setStateful(boolean stateful) {
		this.stateful = stateful;
	}

	public Structure createRequest() throws Exception {
		return RfcUtil.getRequest(getDestination().getRepository(), getRfcName());
	}

	protected JCoDestination getDestination() {
		if (destination == null) {
			try {
				destination = JCoDestinationManager.getDestination((destinationName));
			} catch (Exception e) {
				LOG.warn("Failed to get destination object for endpoint '"+ getEndpointUri() + "'. This exception will be ignored.", e);
			}
		}
		return destination;
	}
}
