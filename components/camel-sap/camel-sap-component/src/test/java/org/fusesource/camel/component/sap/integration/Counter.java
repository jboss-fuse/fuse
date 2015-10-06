/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.fusesource.camel.component.sap.integration;

import org.apache.camel.Exchange;
import org.fusesource.camel.component.sap.SapServerSessionContext;

public class Counter {
	
	public static final String COUNTER_SESSION_DATA_KEY = Counter.class.getName();

	public void process(Exchange exchange) throws Exception {
		SapServerSessionContext sessionContext = exchange.getProperty("org.fusesource.camel.component.sap.sessionContext", SapServerSessionContext.class);
		if (sessionContext != null) {
			Integer count = (Integer) sessionContext.getSessionData().get(COUNTER_SESSION_DATA_KEY);
			if (count == null) {
				sessionContext.getSessionData().put(COUNTER_SESSION_DATA_KEY, 0);
			} else {
				sessionContext.getSessionData().put(COUNTER_SESSION_DATA_KEY, count + 1);
			}
			System.out.println("count = " + sessionContext.getSessionData().get(COUNTER_SESSION_DATA_KEY));
		}
	}
}
