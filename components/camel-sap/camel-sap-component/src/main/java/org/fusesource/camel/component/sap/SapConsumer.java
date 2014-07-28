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

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

/**
 * SAP consumer. This consumer provides a session context if stateful.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class SapConsumer extends DefaultConsumer {

	public static final String SAP_SESSION_CONTEXT_PROPERTY_NAME = "org.fusesource.camel.component.sap.sessionContext";

	protected boolean stateful;
	protected SapServerSessionContext sessionContext;

	public SapConsumer(Endpoint endpoint, Processor processor) {
		super(endpoint, processor);
	}

	/**
	 * Returns <code>true</code> if this consumer is stateful and
	 * <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if this consumer is stateful and
	 *         <code>false</code> otherwise.
	 */
	public boolean isStateful() {
		return stateful;
	}

	/**
	 * Set stateful state of consumer.
	 * 
	 * @param stateful
	 *            - if <code>true</code> this consumer is stateful and
	 *            <code>false</code> otherwise.
	 */
	protected void setStateful(boolean stateful) {
		this.stateful = stateful;
	}

	/**
	 * Returns session context if this consumer.
	 * 
	 * @return session context if this consumer.
	 */
	protected SapServerSessionContext getSessionContext() {
		return sessionContext;
	}

	/**
	 * Sets the session context of this consumer.
	 * 
	 * @param sessionContext
	 *            - the session context.
	 */
	protected void setSessionContext(SapServerSessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}
