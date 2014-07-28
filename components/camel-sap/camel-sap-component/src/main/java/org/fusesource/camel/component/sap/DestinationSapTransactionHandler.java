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
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoDestination;

/**
 * A Camel synchronization object which manages the SAP transaction of a
 * destination within an exchange.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class DestinationSapTransactionHandler extends SynchronizationAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(DestinationSapTransactionHandler.class);

	/**
	 * Ensures that an SAP transaction for <code>destination</code> has begun
	 * and is handled in the <code>exchange</code>.
	 * 
	 * @param exchange
	 *            - the associated exchange.
	 * @param destination
	 *            the associated destination.
	 */
	public static void ensureSapTransactionHasBegunAndIsHandled(Exchange exchange, JCoDestination destination) {
		DestinationSapTransactionHandler transaction = new DestinationSapTransactionHandler(destination);
		if (!exchange.getUnitOfWork().containsSynchronization(transaction)) {
			// Begin SAP Transaction.
			transaction.begin();

			// Add transaction to UOW: SAP Transaction committed/rolledback
			// at end of exchange.
			exchange.getUnitOfWork().addSynchronization(transaction);
		}
	}

	private JCoDestination destination;

	/**
	 * Create an SAP Transaction Handler for given <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination the SAP transaction is associated with.
	 */
	private DestinationSapTransactionHandler(JCoDestination destination) {
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
		DestinationSapTransactionHandler other = (DestinationSapTransactionHandler) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.getDestinationName().equals(other.destination.getDestinationName()))
			return false;
		return true;
	}

	/**
	 * Begin SAP Transaction.
	 */
	protected void begin() {
		// Begin stateful session with destination SAP instance
		RfcUtil.beginTransaction(destination);
		LOG.debug("Began SAP Transaction for destination '{}'", destination.getDestinationName());
	}

	/**
	 * Commit SAP Transaction.
	 * 
	 * @throws Exception
	 *             Thrown if unable to commit transaction.
	 */
	public void commit() throws Exception {
		// Invoke BAPI_TRANSACTION_COMMIT
		RfcUtil.commitTransaction(destination);
		LOG.debug("Committed SAP Transaction for destination '{}'", destination.getDestinationName());
	}

	/**
	 * Rolls back SAP Transaction.
	 * 
	 * @throws Exception
	 *             Thrown if unable to roll back transaction.
	 */
	protected void rollback() throws Exception {
		// Invoke BAPI_TRANSACTION_ROLLBACK
		RfcUtil.rollbackTransaction(destination);
		LOG.debug("Rolledback SAP Transaction for destination '{}'", destination.getDestinationName());
	}

	@Override
	public void onComplete(Exchange exchange) {
		try {
			commit();
		} catch (Exception e) {
			LOG.warn("Failed to commit SAP Transaction. This exception will be ignored.", e);
		}
	}

	@Override
	public void onFailure(Exchange exchange) {
		try {
			rollback();
		} catch (Exception e) {
			LOG.warn("Failed to rollback SAP Transaction. This exception will be ignored.", e);
		}
	}
}
