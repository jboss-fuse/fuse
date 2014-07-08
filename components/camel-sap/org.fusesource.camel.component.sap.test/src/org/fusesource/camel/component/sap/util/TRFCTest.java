package org.fusesource.camel.component.sap.util;


import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.Structure;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

public class TRFCTest {
	
	private static final String MESSAGE_VALUE1 = "ABCDEFGHIJ";
	private static final String MESSAGE_VALUE2 = "ZYXWVUTSRQ";
	
	protected DestinationDataStore destinationDataStore;

	@Before
	public void setUp() throws Exception {
		
		DestinationData destinationData = RfcFactory.eINSTANCE.createDestinationData();
		destinationData.setAshost("nplhost");
		destinationData.setSysnr("42");
		destinationData.setClient("001");
		destinationData.setUser("developer");
		destinationData.setPasswd("ch4ngeme");
		destinationData.setLang("en");
		
		destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
		destinationDataStore.getEntries().put("TestDestination", destinationData);
		
		ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
	}

	@After
	public void tearDown() throws Exception {
		//ComponentDestinationDataProvider.INSTANCE.removeDestinationDataStore(destinationDataStore);
	}
	
	@Test
	public void testTRFC() throws Exception {
		Structure setMessageRequest;
		Structure getMessageRequest;
		Structure getMessageResponse;
		String messageValue;
		
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination"); 
		
		// Start stateful session so the global 'MESSAGE' value is maintained across function calls.
		JCoContext.begin(jcoDestination);

		// Create TID
		String tid = jcoDestination.createTID();
		
		// Perform a tRFC call that should fail.
		setMessageRequest = RfcUtil.getRequest(jcoDestination.getRepository(), "ZJBOSS_SET_MESSAGE");
		RfcUtil.setValue(setMessageRequest, "VALUE", MESSAGE_VALUE1);
		RfcUtil.setValue(setMessageRequest, "FAIL", "X");
		boolean failed = false;
		try {
			RfcUtil.executeFunction(jcoDestination, "ZJBOSS_SET_MESSAGE", setMessageRequest, tid);
		} catch (JCoException e) {
			// Expected
			failed = true;
		}
		assertThat("tRFC did not fail as expected.", failed, is(true));
		
		// Perform a tRFC call that should succeed.
		RfcUtil.setValue(setMessageRequest, "VALUE", MESSAGE_VALUE2);
		RfcUtil.setValue(setMessageRequest, "FAIL", "");
		RfcUtil.executeFunction(jcoDestination, "ZJBOSS_SET_MESSAGE", setMessageRequest, tid);

		// Check whether correct 'MESSAGE' value was set.
		getMessageRequest = RfcUtil.getRequest(jcoDestination.getRepository(), "ZJBOSS_GET_MESSAGE");
		getMessageResponse = RfcUtil.executeFunction(jcoDestination, "ZJBOSS_GET_MESSAGE", getMessageRequest);
		messageValue = getMessageResponse.get("VALUE", String.class);
		assertThat("tRFC call was not executed by server as expected.", messageValue, is(MESSAGE_VALUE2));
		
		// Perform a tRFC call that should be ignored.
		RfcUtil.setValue(setMessageRequest, "VALUE", MESSAGE_VALUE1);
		RfcUtil.setValue(setMessageRequest, "FAIL", "");
		RfcUtil.executeFunction(jcoDestination, "ZJBOSS_SET_MESSAGE", setMessageRequest, tid);
		
		// Check whether 'MESSAGE' value was not changed.
		getMessageResponse = RfcUtil.executeFunction(jcoDestination, "ZJBOSS_GET_MESSAGE", getMessageRequest);
		messageValue = getMessageResponse.get("VALUE", String.class);
		assertThat("ZJBOSS_SET_MESSAGE function module did not set expected value '" + MESSAGE_VALUE2 + "' instead '" + messageValue + "' was set.", messageValue, is(MESSAGE_VALUE2));
		assertThat("tRFC call was not ignored by server as expected.", messageValue, is(MESSAGE_VALUE2));

		// Release TID tracking resources on server.
		jcoDestination.confirmTID(tid);
		
		// Perform a tRFC call with confirmed TID that should create new transaction.
		RfcUtil.setValue(setMessageRequest, "VALUE", MESSAGE_VALUE1);
		RfcUtil.setValue(setMessageRequest, "FAIL", "");
		RfcUtil.executeFunction(jcoDestination, "ZJBOSS_SET_MESSAGE", setMessageRequest, tid);

		// Check whether 'MESSAGE' value was not changed.
		getMessageResponse = RfcUtil.executeFunction(jcoDestination, "ZJBOSS_GET_MESSAGE", getMessageRequest);
		messageValue = getMessageResponse.get("VALUE", String.class);
		assertThat("tRFC call with confirmed tid was not executed by server as expected.", messageValue, is(MESSAGE_VALUE1));

		// All done with stateful session
		JCoContext.end(jcoDestination);
	}

}
