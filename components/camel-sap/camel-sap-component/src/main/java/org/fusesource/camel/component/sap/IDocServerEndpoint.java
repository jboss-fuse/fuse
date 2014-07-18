package org.fusesource.camel.component.sap;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.fusesource.camel.component.sap.SAPComponent.IDocHandlerFactory;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.jco.JCoException;

public class IDocServerEndpoint extends IDocEndpoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(IDocServerEndpoint.class);

	protected String serverName;
	protected JCoIDocServer server;

	public IDocServerEndpoint() {
	}

	public IDocServerEndpoint(String uri, SAPComponent component) {
		super(uri, component);
	}

	@Override
	public Producer createProducer() throws Exception {
		throw new UnsupportedOperationException(
				"Server endpoints do not support producers");
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		IDocHandlerFactory handlerFactory = getComponent().getIDocHandlerFactory(serverName);
		if (handlerFactory == null) {
			throw new IllegalStateException("IDoc Handler Factory for '" + serverName + "' missing.");
		}
		IDocConsumer consumer = new IDocConsumer(this, processor);
		handlerFactory.registerHandler(getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease(), consumer);
		return consumer;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@Override
	public SAPComponent getComponent() {
		return (SAPComponent) super.getComponent();
	}

	@Override
	public Document getDocument() throws Exception {
		try {
			return IDocUtil.createIDoc(getServer().getIDocRepository(), getIdocType(), getIdocTypeExtension(), getSystemRelease(), getApplicationRelease());
		} catch (Exception e) {
			throw new Exception("Failed to get Document from endpoint", e);
		}
	}

	protected JCoIDocServer getServer() {
		if (server == null) {
			try {
				server = JCoIDoc.getServer(serverName);
			} catch (JCoException e) {
				LOG.warn("Failed to get server object for endpoint. This exception will be ignored.", e);
			}
		}
		return server;
	}
}
