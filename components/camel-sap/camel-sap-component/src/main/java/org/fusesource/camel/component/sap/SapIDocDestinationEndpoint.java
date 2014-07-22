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
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;

/**
 * Represents an SAP endpoint sending a IDoc (Intermediary Document) to an SAP system.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class SapIDocDestinationEndpoint extends DefaultEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(SapIDocDestinationEndpoint.class); 

	protected String idocType;
	protected String idocTypeExtension;
	protected String systemRelease;
	protected String applicationRelease;
    protected String destinationName;
    protected JCoDestination destination;

	public SapIDocDestinationEndpoint() {
	}

	public SapIDocDestinationEndpoint(String endpointUri, Component component) {
		super(endpointUri, component);
	}

	@Override
	public Producer createProducer() throws Exception {
		LOG.debug("Created producer for endpoint '" + getEndpointUri() + "'");
		return new SapIDocProducer(this);
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

	public String getIdocType() {
		return idocType;
	}

	public void setIdocType(String idocType) {
		this.idocType = idocType;
	}

	public String getIdocTypeExtension() {
		return idocTypeExtension;
	}

	public void setIdocTypeExtension(String idocTypeExtension) {
		this.idocTypeExtension = idocTypeExtension;
	}

	public String getSystemRelease() {
		return systemRelease;
	}

	public void setSystemRelease(String systemRelease) {
		this.systemRelease = systemRelease;
	}

	public String getApplicationRelease() {
		return applicationRelease;
	}
	
	public void setApplicationRelease(String applicationRelease) {
		this.applicationRelease = applicationRelease;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public Document createDocument() throws Exception {
		try {
			return IDocUtil.createIDoc(getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get Document from endpoint", e);
		}
	}

	protected IDocRepository getIDocRepository() throws Exception{
		if (getDestination() != null) {
			return JCoIDoc.getIDocRepository(getDestination());
		}
		throw new Exception("Failed to get IDoc repository: no destination set of endpoint");
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
