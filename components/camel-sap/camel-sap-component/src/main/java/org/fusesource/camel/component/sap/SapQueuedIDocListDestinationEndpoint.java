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
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an SAP endpoint sending a IDoc (Intermediary Document) to an SAP system using the queued remote function call.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@UriEndpoint(scheme="sap-qidoclist-destination", syntax = "sap-qidoclist-destination:destination:queue:idocType:idocTypeExtension:systemRelease:applicationRelease",
		producerOnly = true, title="SAP Queued IDoc List Destination")
public class SapQueuedIDocListDestinationEndpoint extends SapIDocDestinationEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(SapQueuedIDocListDestinationEndpoint.class); 

	@UriPath(name = "queue", description = "Specifies the queue this endpoint sends an IDoc to") @Metadata(required = "true")
    protected String queueName;

    public SapQueuedIDocListDestinationEndpoint() {
	}

	public SapQueuedIDocListDestinationEndpoint(String endpointUri, SapQueuedIDocListDestinationComponent component) {
		super(endpointUri, component);
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public DocumentList createDocumentList() throws Exception {
		try {
			return IDocUtil.createDocumentList(getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get Document List from endpoint", e);
		}
	}

	@Override
	public Producer createProducer() throws Exception {
		LOG.debug("Created producer for endpoint '" + getEndpointUri() + "'");
		return new SapQueuedIDocListProducer(this);
	}

}
