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
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.jco.JCoException;

/**
 * An SAP endpoint receiving an IDoc (Intermediate Document) list from an SAP system
 * using transactional remote function calls (tRFC).
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
@UriEndpoint(scheme = "sap-idoclist-server", consumerClass = SapTransactionalIDocListConsumer.class,
		syntax = "sap-idoclist-server:serverName:rfcName", title="SAP")
public class SapTransactionalIDocListServerEndpoint extends DefaultEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(SapTransactionalIDocListServerEndpoint.class);

	protected String serverName;
	protected String idocType;
	protected String idocTypeExtension;
	protected String systemRelease;
	protected String applicationRelease;
	@UriParam
	protected boolean propagateExceptions;
	@UriParam
	protected boolean stateful;

	protected JCoIDocServer server;

	public SapTransactionalIDocListServerEndpoint() {
	}

	public SapTransactionalIDocListServerEndpoint(String uri, SapTransactionalIDocListServerComponent component) {
		super(uri, component);
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
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

	public boolean isPropagateExceptions() {
		return propagateExceptions;
	}

	public void setPropagateExceptions(boolean propagateExceptions) {
		this.propagateExceptions = propagateExceptions;
	}

	public boolean isStateful() {
		return stateful;
	}

	public void setStateful(boolean stateful) {
		this.stateful = stateful;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException("Endpoint '" + getEndpointUri() + "' does not support producers");
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		IDocHandlerFactory handlerFactory = getComponent().getIDocHandlerFactory(serverName);
		if (handlerFactory == null) {
			throw new IllegalStateException("IDoc Handler Factory for '" + serverName + "' missing.");
		}
		SapTransactionalIDocListConsumer consumer = new SapTransactionalIDocListConsumer(this, processor);
		if (isStateful()) {
			consumer.setStateful(true);
		}
		handlerFactory.registerHandler(getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease(), consumer);
		return consumer;
	}

	public DocumentList createDocumentList() throws Exception {
		try {
			return IDocUtil.createDocumentList(getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get DocumentList from endpoint", e);
		}
	}

	@Override
	public SapTransactionalIDocListServerComponent getComponent() {
		return (SapTransactionalIDocListServerComponent) super.getComponent();
	}

	protected IDocRepository getIDocRepository() throws Exception{
		return getServer().getIDocRepository();
	}

	protected JCoIDocServer getServer() {
		if (server == null) {
			try {
				server = JCoIDoc.getServer(serverName);
			} catch (JCoException e) {
				LOG.warn("Failed to get server object for endpoint '" + getEndpointUri() + "'. This exception will be ignored.", e);
			}
		}
		return server;
	}

}
