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

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.support.SynchronizationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoDestination;

/**
 * A Camel synchronization object which manages the RFC transaction of a
 * destination within an exchange.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 * 
 */
public class DestinationRfcTransactionHandler extends SynchronizationAdapter {

	public static final String TID_HANDLER_MAP_PROPERTY_KEY = DestinationRfcTransactionHandler.class.getName() + ".tidHandlerMap";

	private static final Logger LOG = LoggerFactory.getLogger(DestinationRfcTransactionHandler.class);

	/**
	 * Returns the ID of the current RFC transaction associated with
	 * <code>destination</code> in <code>exchange</code>. Also ensures that an
	 * RFC transaction for <code>destination</code> has begun and is handled by
	 * <code>exhange</code>.
	 * 
	 * @param exchange
	 *            - the associated exchange.
	 * @param destination
	 *            - the associated destination.
	 * @return The ID of the current RFC transaction associated with
	 *         <code>destination</code> in <code>exchange</code>.
	 * @throws Exception
	 *             Thrown if unable to return TID.
	 */
	public static String getTID(Exchange exchange, JCoDestination destination) throws Exception {

		// Get TID Handler Map from exchange
		@SuppressWarnings("unchecked")
		Map<String, DestinationRfcTransactionHandler> tidHandlerMap = exchange.getProperty(TID_HANDLER_MAP_PROPERTY_KEY, Map.class);
		if (tidHandlerMap == null) {
			// Exchange does not contain a handler map: create and populate
			// exchange with TID handler map.
			tidHandlerMap = new HashMap<String, DestinationRfcTransactionHandler>();
			exchange.setProperty(TID_HANDLER_MAP_PROPERTY_KEY, tidHandlerMap);
		}

		// Get TID Handler from map
		DestinationRfcTransactionHandler tidHandler = tidHandlerMap.get(destination.getDestinationName());
		if (tidHandler != null) {
			// Handler for destination already populated into exchange: return
			// the destination's current TID.
			return tidHandler.getTID(exchange);
		}

		// Handler for destination has not been populated into exchange: create
		// handler for destination and populate handler into map and
		// exchange.
		tidHandler = new DestinationRfcTransactionHandler(destination);
		tidHandlerMap.put(destination.getDestinationName(), tidHandler);
		exchange.getUnitOfWork().addSynchronization(tidHandler);

		// Return the destination' current TID.
		return tidHandler.getTID(exchange);
	}

	private JCoDestination destination;

	private Map<String, String> tidMap = new HashMap<String, String>();

	/**
	 * Create an RFC Transaction Handler for given <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination the RFC transaction is associated with.
	 */
	private DestinationRfcTransactionHandler(JCoDestination destination) {
		if (destination == null) {
			throw new IllegalArgumentException("destination argument can not be null");
		}
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination.getDestinationName() == null) ? 0 : destination.getDestinationName().hashCode());
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
		DestinationRfcTransactionHandler other = (DestinationRfcTransactionHandler) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.getDestinationName().equals(other.destination.getDestinationName()))
			return false;
		return true;
	}

	/**
	 * Return a TID managed by this handler, creating if necessary.
	 * 
	 * @param exchange - the exchange this tid is used in.
	 * @return The TID managed by this handler.
	 * @throws Exception
	 *             Thrown if unable to return TID.
	 */
	protected String getTID(Exchange exchange) throws Exception {

		ProcessorDefinition<?> definition = exchange.getProperty(CurrentProcessorDefinitionInterceptStrategy.CURRENT_PROCESSOR_DEFINITION,
				ProcessorDefinition.class);

		if (definition == null) {
			LOG.warn("Current processor definition not found in exchange: please install org.fusesource.camel.component.sap.CurrentProcessorDefinitionInterceptStrategy into Camel container");
			return destination.createTID();
		}
		
		String tid = tidMap.get(definition.getId());
		if (tid == null) {
			tid = destination.createTID();
			tidMap.put(definition.getId(), tid);
		}
		return tid;
	}

	/**
	 * Confirm the RFC transactions managed by this handler.
	 */
	protected void confirmTids() {
		for (String tid : tidMap.values()) {
			try {
				destination.confirmTID(tid);
			} catch (Exception e) {
				LOG.warn("Failed to confirm transaction id: '" + tid + "': This exception will be ignored", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.camel.support.SynchronizationAdapter#onComplete(org.apache.camel.Exchange)
	 */
	@Override
	public void onComplete(Exchange exchange) {
		done();
	}

	/* (non-Javadoc)
	 * @see org.apache.camel.support.SynchronizationAdapter#onFailure(org.apache.camel.Exchange)
	 */
	@Override
	public void onFailure(Exchange exchange) {
		done();
	}
	
	/**
	 * Confirm tids and clear tidMap.
	 */
	private void done() {
		// Confirm all TIDs used in the exchange.
		confirmTids();

		// Clear the TID map.
		tidMap.clear();
	}

}
