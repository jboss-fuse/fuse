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
import org.apache.camel.Producer;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;

public class IDocDestinationEndpoint extends IDocEndpoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(IDocDestinationEndpoint.class); 

    protected String destinationName;
    protected JCoDestination destination;

	public IDocDestinationEndpoint() {
	}

	public IDocDestinationEndpoint(String uri, SAPComponent component) {
		super(uri, component);
	}

	@Override
	public Producer createProducer() throws Exception {
		return new IDocProducer(this);	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		throw new UnsupportedOperationException(
				"Destination endpoints do not support consumers");
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	protected JCoDestination getDestination() {
		if (destination == null) {
			try {
				destination = JCoDestinationManager.getDestination((destinationName));
			} catch (Exception e) {
				LOG.warn("Failed to get destination object for endpoint. This exception will be ignored.", e);
			}
		}
		return destination;
	}

	public IDocRepository getIDocRepository() throws Exception{
		if (getDestination() != null) {
			return JCoIDoc.getIDocRepository(getDestination());
		}
		throw new Exception("Failed to get IDoc repository: no destination set of endpoint");
	}

	@Override
	public Document getDocument() throws Exception {
		try {
			return IDocUtil.createIDoc(getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get Document from endpoint", e);
		}
	}

}
