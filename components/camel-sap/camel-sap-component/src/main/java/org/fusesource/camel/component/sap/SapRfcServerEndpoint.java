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
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.server.JCoServer;

/**
 * An SAP endpoint providing inbound tRFC (Transactional Remote Function Call) communication from SAP.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public abstract class SapRfcServerEndpoint extends DefaultEndpoint {
	
    private static final Logger LOG = LoggerFactory.getLogger(SapRfcServerEndpoint.class);

	@UriPath(name = "server", description = "Specifies the server this endpoint receives an SAP request from") @Metadata(required = "true")
	protected String serverName;
	
	@UriPath(name = "rfc", description = "Specifies the Remote Function Module this endpoint handles an SAP request for") @Metadata(required = "true")
	protected String rfcName;
	
	@UriParam(name = "stateful", description = "When true, specifies that this endpoint will initiate an SAP stateful session", defaultValue = "false")
	protected boolean stateful;
	
	public SapRfcServerEndpoint() {
	}

	public SapRfcServerEndpoint(String endpointUri, Component component) {
		super(endpointUri, component);
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public String getRfcName() {
		return rfcName;
	}

	public void setRfcName(String rfcName) {
		this.rfcName = rfcName;
	}

	public boolean isStateful() {
		return stateful;
	}

	public void setStateful(boolean stateful) {
		this.stateful = stateful;
	}

	@Override
	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException(
				"Endpoint '"  + getEndpointUri() + "' does not support producers");
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public Structure createResponse() throws Exception {
		return RfcUtil.getResponse(getServer().getRepository(), getRfcName());
	}
	
	
	public SapRfcServerComponent getComponent() {
		return (SapRfcServerComponent) super.getComponent();
	}
	
	protected JCoServer getServer() {
		try {
			return getComponent().getServer(serverName);
		} catch (Exception e) {
			LOG.warn("Failed to get server object for endpoint '"+ getEndpointUri() + "'. This exception will be ignored.", e);
		}
		return null;
	}
}
