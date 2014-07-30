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
import org.apache.camel.support.SynchronizationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;

/**
 * A Camel synchronization object which manages the SAP stateful session of a
 * destination within an exchange.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class DestinationSapStatefulSessionHandler extends SynchronizationAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(DestinationSapStatefulSessionHandler.class);

	/**
	 * Ensures that an SAP stateful session for <code>destination</code> has begun
	 * and is handled in the <code>exchange</code>.
	 * 
	 * @param exchange
	 *            - the associated exchange.
	 * @param destination
	 *            the associated destination.
	 */
	public static void ensureSapStatefulSessionHasBegunAndIsHandled(Exchange exchange, JCoDestination destination) {
		DestinationSapStatefulSessionHandler sessionHandler = new DestinationSapStatefulSessionHandler(destination);
		if (!exchange.getUnitOfWork().containsSynchronization(sessionHandler)) {

			// Begin SAP stateful session.
			sessionHandler.begin();

			// Add transaction to UOW: SAP Transaction committed/rolledback
			// at end of exchange.
			exchange.getUnitOfWork().addSynchronization(sessionHandler);
		}
	}

	private JCoDestination destination;

	/**
	 * Create an SAP Transaction Handler for given <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination the SAP transaction is associated with.
	 */
	private DestinationSapStatefulSessionHandler(JCoDestination destination) {
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.getDestinationName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DestinationSapStatefulSessionHandler other = (DestinationSapStatefulSessionHandler) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.getDestinationName().equals(other.destination.getDestinationName()))
			return false;
		return true;
	}

	/**
	 * Begin SAP Stateful Session.
	 */
	protected void begin() {
		// Begin stateful session on destination
		JCoContext.begin(destination);
		LOG.debug("Began SAP stateful session for destination '{}'", destination.getDestinationName());
	}

	/**
	 * End SAP Stateful Session.
	 * 
	 * @throws Exception
	 *             Thrown if unable to end stateful session.
	 */
	public void end() {
		try {
			// End stateful session on destination.
			JCoContext.end(destination);
			LOG.debug("Committed SAP Transaction for destination '{}'", destination.getDestinationName());
		} catch (Exception e) {
			LOG.warn("Failed to end SAP stateful session. This exception will be ignored.", e);
		}
	}

	@Override
	public void onComplete(Exchange exchange) {
		end();
	}

	@Override
	public void onFailure(Exchange exchange) {
		end();
	}
}
