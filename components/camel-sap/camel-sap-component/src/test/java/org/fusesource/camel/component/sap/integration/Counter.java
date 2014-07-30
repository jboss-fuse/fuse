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
