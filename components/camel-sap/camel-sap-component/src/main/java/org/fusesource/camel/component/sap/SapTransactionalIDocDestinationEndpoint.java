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
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP endpoint sending a IDoc (Intermediary Document) to an SAP system using transactional remote function calls (tRFC).
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-idoc-destination", syntax = "sap-idoc-destination:destination:idocType:idocTypeExtension:systemRelease:applicationRelease", producerOnly = true, title="SAP IDoc Destination")
public class SapTransactionalIDocDestinationEndpoint extends SapIDocDestinationEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(SapTransactionalIDocDestinationEndpoint.class); 

	public SapTransactionalIDocDestinationEndpoint() {
	}

	public SapTransactionalIDocDestinationEndpoint(String endpointUri, SapTransactionalIDocDestinationComponent component) {
		super(endpointUri, component);
	}

	public Document createDocument() throws Exception {
		try {
			return IDocUtil.createDocument(getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get Document from endpoint", e);
		}
	}

	@Override
	public Producer createProducer() throws Exception {
		LOG.debug("Created producer for endpoint '" + getEndpointUri() + "'");
		return new SapTransactionalIDocProducer(this);
	}

}
