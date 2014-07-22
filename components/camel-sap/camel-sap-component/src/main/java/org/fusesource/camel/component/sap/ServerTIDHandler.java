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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerTIDHandler;

/**
 * Handler for transactions received from an SAP system.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class ServerTIDHandler implements JCoServerTIDHandler {

	private static final Logger LOG = LoggerFactory.getLogger(SapSynchronousRfcServerComponent.class);

	// TODO Replace with persistent data store.
    Map<String, TIDState> availableTIDs = new HashMap<String, TIDState>();

    @Override
	public boolean checkTID(JCoServerContext serverContext, String tid) {
		TIDState state = availableTIDs.get(tid);
		if (state == null) {
			availableTIDs.put(tid, TIDState.CREATED);
			LOG.debug("Checked TID '" + tid +"': true");
			return true;
		}
		
		if(state == TIDState.CREATED || state == TIDState.ROLLED_BACK) {
			LOG.debug("Checked TID '" + tid +"': true");
			return true;
		}

		LOG.debug("Checked TID '" + tid +"': false");
		return false;
	}

	@Override
	public void commit(JCoServerContext serverContext, String tid) {
		availableTIDs.put(tid, TIDState.COMMITTED);
		LOG.debug("Committed TID '" + tid +"'");
	}

	@Override
	public void rollback(JCoServerContext serverContext, String tid) {
		availableTIDs.put(tid, TIDState.ROLLED_BACK);
		LOG.debug("Rolledback TID '" + tid +"'");
	}
	
	@Override
	public void confirmTID(JCoServerContext serverContext, String tid) {
		availableTIDs.remove(tid);
		LOG.debug("Confirmed TID '" + tid +"'");
	}
	
	public void execute(JCoServerContext serverContext) {
		String tid = serverContext.getTID();
		if (tid != null) {
			availableTIDs.put(tid, TIDState.EXECUTED);
			LOG.debug("Executed TID '" + tid + "'");
		}
	}

}
