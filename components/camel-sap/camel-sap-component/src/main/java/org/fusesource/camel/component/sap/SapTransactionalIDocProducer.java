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
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.fusesource.camel.component.sap.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An SAP producer sending a IDoc (Intermediate Document) to an SAP system using
 * the transactional remote function call (tRFC) protocol.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class SapTransactionalIDocProducer extends DefaultProducer {

	private static final transient Logger LOG = LoggerFactory.getLogger(SapTransactionalIDocProducer.class);

	public SapTransactionalIDocProducer(SapTransactionalIDocDestinationEndpoint endpoint) {
		super(endpoint);
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		Document document = exchange.getIn().getBody(Document.class);
		if (document == null) {
			LOG.warn("Exchange input message body does not contain IDoc document");
			return;
		}
		if (LOG.isDebugEnabled()) {
			try {
				LOG.debug("Sending IDoc document to ''{}''", getEndpoint().getEndpointUri());
				LOG.debug("Document: " + (document == null ? document : Util.marshal(document)));
			} catch (Exception e) {
				LOG.warn("Failed to log request", e);
			}
		}
		String tid = DestinationRfcTransactionHandler.getTID(exchange, getEndpoint().getDestination());
		IDocUtil.sendDocument(getEndpoint().getDestination(), document, tid);
	}

	@Override
	public SapTransactionalIDocDestinationEndpoint getEndpoint() {
		return (SapTransactionalIDocDestinationEndpoint) super.getEndpoint();
	}

}
