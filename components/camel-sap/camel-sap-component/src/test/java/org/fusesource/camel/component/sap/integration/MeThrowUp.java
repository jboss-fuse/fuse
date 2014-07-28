package org.fusesource.camel.component.sap.integration;

import org.apache.camel.Exchange;

public class MeThrowUp {
	
	public void throwUp(Exchange exchange) throws Exception {
		throw new Exception("I threw up!");
	}

}
