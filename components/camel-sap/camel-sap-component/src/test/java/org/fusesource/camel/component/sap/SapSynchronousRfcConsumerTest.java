/**
 * Copyright 2013 Red Hat, Inc.
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


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.fusesource.camel.component.sap.model.rfc.Request;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.model.rfc.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServerFactory;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SAP Producer test cases.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@RunWith(PowerMockRunner.class)
@MockPolicy({Slf4jMockPolicy.class})
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoServerFactory.class })
public class SapSynchronousRfcConsumerTest extends SapRfcTestSupport {
	
	@SuppressWarnings("deprecation")
	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();

		PowerMockito.mockStatic(JCoDestinationManager.class, JCoIDoc.class);
		when(JCoDestinationManager.getDestination(DESTINATION_NAME)).thenReturn(mockDestination);
		when(JCoServerFactory.get()).thenReturn(mockServerFactory);
		when(JCoServerFactory.getServer(SERVER_NAME)).thenReturn(mockServer);
		
	}

	@Test
	public void testConsumer() throws Exception{ 
		
		//
		// Given
		//
		enhanceParameterListMetaData();

		MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
		mockEndpoint.expectedMessageCount(1);
		Producer mockEndpointProducer = mockEndpoint.createProducer();
	
		CamelContext context = context();
		Endpoint endpoint = context.getEndpoint("sap-srfc-server:TEST_SERVER:TEST_FUNCTION_MODULE");
		SapSynchronousRfcConsumer rfcConsumer = (SapSynchronousRfcConsumer) endpoint.createConsumer(mockEndpointProducer);
		
		//
		// When
		//
		
		rfcConsumer.handleRequest(mockServerContext, mockFunction);
		
		//
		// Then
		//
		
		assertMockEndpointsSatisfied();
		
		// check access to jco fields

		verify(mockParameterListCharField, times(1)).setValue((Object)CHAR_PARAM_OUT_VAL);
		verify(mockParameterListCharField, times(1)).getValue();
		verify(mockParameterListNumField, times(1)).setValue((Object)NUM_PARAM_OUT_VAL);
		verify(mockParameterListNumField, times(1)).getValue();
		verify(mockParameterListIntField, times(1)).setValue((Object)INT_PARAM_OUT_VAL);
		verify(mockParameterListIntField, times(1)).getValue();
		verify(mockParameterListFloatField, times(1)).setValue((Object)FLOAT_PARAM_OUT_VAL);
		verify(mockParameterListFloatField, times(1)).getValue();
		verify(mockParameterListBCDField, times(1)).setValue((Object)BCD_PARAM_OUT_VAL);
		verify(mockParameterListBCDField, times(1)).getValue();
		verify(mockParameterListBinaryField, times(1)).setValue((Object)BINARY_PARAM_OUT_VAL);
		verify(mockParameterListBinaryField, times(1)).getValue();
		verify(mockParameterListBinaryArrayField, times(1)).setValue((Object)BINARY_ARRAY_PARAM_OUT_VAL);
		verify(mockParameterListBinaryArrayField, times(1)).getValue();
		verify(mockParameterListDateField, times(1)).setValue((Object)DATE_PARAM_OUT_VAL);
		verify(mockParameterListDateField, times(1)).getValue();
		verify(mockParameterListTimeField, times(1)).setValue((Object)TIME_PARAM_OUT_VAL);
		verify(mockParameterListTimeField, times(1)).getValue();
		verify(mockParameterListStringField, times(1)).setValue((Object)STRING_PARAM_OUT_VAL);
		verify(mockParameterListStringField, times(1)).getValue();
		
		verify(mockCharField, times(2)).setValue((Object)CHAR_PARAM_OUT_VAL);
		verify(mockCharField, times(2)).getValue();
		verify(mockNumField, times(2)).setValue((Object)NUM_PARAM_OUT_VAL);
		verify(mockNumField, times(2)).getValue();
		verify(mockIntField, times(2)).setValue((Object)INT_PARAM_OUT_VAL);
		verify(mockIntField, times(2)).getValue();
		verify(mockFloatField, times(2)).setValue((Object)FLOAT_PARAM_OUT_VAL);
		verify(mockFloatField, times(2)).getValue();
		verify(mockBCDField, times(2)).setValue((Object)BCD_PARAM_OUT_VAL);
		verify(mockBCDField, times(2)).getValue();
		verify(mockBinaryField, times(2)).setValue((Object)BINARY_PARAM_OUT_VAL);
		verify(mockBinaryField, times(2)).getValue();
		verify(mockBinaryArrayField, times(2)).setValue((Object)BINARY_ARRAY_PARAM_OUT_VAL);
		verify(mockBinaryArrayField, times(2)).getValue();
		verify(mockDateField, times(2)).setValue((Object)DATE_PARAM_OUT_VAL);
		verify(mockDateField, times(2)).getValue();
		verify(mockTimeField, times(2)).setValue((Object)TIME_PARAM_OUT_VAL);
		verify(mockTimeField, times(2)).getValue();
		verify(mockStringField, times(2)).setValue((Object)STRING_PARAM_OUT_VAL);
		verify(mockStringField, times(2)).getValue();
		
		verify(mockChangingParameterList, times(2)).getFieldIterator();
		
		verify(mockStructure, times(2)).getFieldIterator();
		
		verify(mockTable, times(2)).getFieldIterator();

		// check response
		Exchange exchange = getMockEndpoint("mock:result").getExchanges().get(0);
		Request response = exchange.getIn().getBody(Request.class);
		assertThat("The response returned by route is an unexpected null value", response, notNullValue());
		
		assertThat("response.get(PARAM_LIST_CHAR_PARAM) returned '" +  response.get(PARAM_LIST_CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_OUT_VAL + "'", (String) response.get(PARAM_LIST_CHAR_PARAM), is(CHAR_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_NUM_PARAM) returned '" +  response.get(PARAM_LIST_NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_OUT_VAL + "'", (String) response.get(PARAM_LIST_NUM_PARAM), is(NUM_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_INT_PARAM) returned '" +  response.get(PARAM_LIST_INT_PARAM) + "' instead of expected value '" + INT_PARAM_OUT_VAL + "'", (Integer) response.get(PARAM_LIST_INT_PARAM), is(INT_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_FLOAT_PARAM) returned '" +  response.get(PARAM_LIST_FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_OUT_VAL + "'", (Double) response.get(PARAM_LIST_FLOAT_PARAM), is(FLOAT_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_BCD_PARAM) returned '" +  response.get(PARAM_LIST_BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_OUT_VAL + "'", (BigDecimal) response.get(PARAM_LIST_BCD_PARAM), is(BCD_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_BINARY_PARAM) returned '" +  response.get(PARAM_LIST_BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_OUT_VAL + "'", (byte[]) response.get(PARAM_LIST_BINARY_PARAM), is(BINARY_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_BINARY_ARRAY_PARAM) returned '" +  response.get(PARAM_LIST_BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_OUT_VAL + "'", (byte[]) response.get(PARAM_LIST_BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_DATE_PARAM) returned '" +  response.get(PARAM_LIST_DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_OUT_VAL + "'", (Date) response.get(PARAM_LIST_DATE_PARAM), is(DATE_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_TIME_PARAM) returned '" +  response.get(PARAM_LIST_TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_OUT_VAL + "'", (Date) response.get(PARAM_LIST_TIME_PARAM), is(TIME_PARAM_OUT_VAL));
		assertThat("response.get(PARAM_LIST_STRING_PARAM) returned '" +  response.get(PARAM_LIST_STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_OUT_VAL + "'", (String) response.get(PARAM_LIST_STRING_PARAM), is(STRING_PARAM_OUT_VAL));
		
		Structure structure = response.get(PARAM_LIST_STRUCTURE_PARAM, Structure.class);
		assertThat("structure.get(PARAM_LIST_STRUCTURE_PARAM) returned unexpected null value", structure, notNullValue());
		assertThat("structure.get(CHAR_PARAM) returned '" +  structure.get(CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_OUT_VAL + "'", (String) structure.get(CHAR_PARAM), is(CHAR_PARAM_OUT_VAL));
		assertThat("structure.get(NUM_PARAM) returned '" +  structure.get(NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_OUT_VAL + "'", (String) structure.get(NUM_PARAM), is(NUM_PARAM_OUT_VAL));
		assertThat("structure.get(INT_PARAM) returned '" +  structure.get(INT_PARAM) + "' instead of expected value '" + INT_PARAM_OUT_VAL + "'", (Integer) structure.get(INT_PARAM), is(INT_PARAM_OUT_VAL));
		assertThat("structure.get(FLOAT_PARAM) returned '" +  structure.get(FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_OUT_VAL + "'", (Double) structure.get(FLOAT_PARAM), is(FLOAT_PARAM_OUT_VAL));
		assertThat("structure.get(BCD_PARAM) returned '" +  structure.get(BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_OUT_VAL + "'", (BigDecimal) structure.get(BCD_PARAM), is(BCD_PARAM_OUT_VAL));
		assertThat("structure.get(BINARY_PARAM) returned '" +  structure.get(BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_OUT_VAL + "'", (byte[]) structure.get(BINARY_PARAM), is(BINARY_PARAM_OUT_VAL));
		assertThat("structure.get(BINARY_ARRAY_PARAM) returned '" +  structure.get(BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_OUT_VAL + "'", (byte[]) structure.get(BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_OUT_VAL));
		assertThat("structure.get(DATE_PARAM) returned '" +  structure.get(DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_OUT_VAL + "'", (Date) structure.get(DATE_PARAM), is(DATE_PARAM_OUT_VAL));
		assertThat("structure.get(TIME_PARAM) returned '" +  structure.get(TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_OUT_VAL + "'", (Date) structure.get(TIME_PARAM), is(TIME_PARAM_OUT_VAL));
		assertThat("structure.get(STRING_PARAM) returned '" +  structure.get(STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_OUT_VAL + "'", (String) structure.get(STRING_PARAM), is(STRING_PARAM_OUT_VAL));
		
		@SuppressWarnings("unchecked")
		Table<? extends Structure> table = response.get(PARAM_LIST_TABLE_PARAM, Table.class);
		assertThat("response.get(PARAM_LIST_TABLE_PARAM) returned unexpected null value", table, notNullValue());
		List<? extends Structure> rows = table.getRows();
		assertThat("rows.size() returned '" + rows.size() + "' instead of expected value of '1'", rows.size(), is(1));
		Structure tableRow = rows.get(0);
		assertThat("tableRow.get(CHAR_PARAM) returned '" +  tableRow.get(CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_OUT_VAL + "'", (String) tableRow.get(CHAR_PARAM), is(CHAR_PARAM_OUT_VAL));
		assertThat("tableRow.get(NUM_PARAM) returned '" +  tableRow.get(NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_OUT_VAL + "'", (String) tableRow.get(NUM_PARAM), is(NUM_PARAM_OUT_VAL));
		assertThat("tableRow.get(INT_PARAM) returned '" +  tableRow.get(INT_PARAM) + "' instead of expected value '" + INT_PARAM_OUT_VAL + "'", (Integer) tableRow.get(INT_PARAM), is(INT_PARAM_OUT_VAL));
		assertThat("tableRow.get(FLOAT_PARAM) returned '" +  tableRow.get(FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_OUT_VAL + "'", (Double) tableRow.get(FLOAT_PARAM), is(FLOAT_PARAM_OUT_VAL));
		assertThat("tableRow.get(BCD_PARAM) returned '" +  tableRow.get(BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_OUT_VAL + "'", (BigDecimal) tableRow.get(BCD_PARAM), is(BCD_PARAM_OUT_VAL));
		assertThat("tableRow.get(BINARY_PARAM) returned '" +  tableRow.get(BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_OUT_VAL + "'", (byte[]) tableRow.get(BINARY_PARAM), is(BINARY_PARAM_OUT_VAL));
		assertThat("tableRow.get(BINARY_ARRAY_PARAM) returned '" +  tableRow.get(BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_OUT_VAL + "'", (byte[]) tableRow.get(BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_OUT_VAL));
		assertThat("tableRow.get(DATE_PARAM) returned '" +  tableRow.get(DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_OUT_VAL + "'", (Date) tableRow.get(DATE_PARAM), is(DATE_PARAM_OUT_VAL));
		assertThat("tableRow.get(TIME_PARAM) returned '" +  tableRow.get(TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_OUT_VAL + "'", (Date) tableRow.get(TIME_PARAM), is(TIME_PARAM_OUT_VAL));
		assertThat("tableRow.get(STRING_PARAM) returned '" +  tableRow.get(STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_OUT_VAL + "'", (String) tableRow.get(STRING_PARAM), is(STRING_PARAM_OUT_VAL));
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("sap-srfc-server:TEST_SERVER:TEST_FUNCTION_MODULE").to("mock:result");
			}
		};
	}

}
