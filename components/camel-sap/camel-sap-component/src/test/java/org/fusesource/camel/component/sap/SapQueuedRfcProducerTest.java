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


import org.apache.camel.builder.RouteBuilder;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;

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
@PrepareForTest({ JCoDestinationManager.class, Environment.class })
public class SapQueuedRfcProducerTest extends SapRfcTestSupport {
	
	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();
		
		PowerMockito.mockStatic(JCoDestinationManager.class);
		when(JCoDestinationManager.getDestination(DESTINATION_NAME)).thenReturn(mockDestination);
		
	}
	
	@Test
	public void testProducer() throws Exception{ 
		
		//
		// Given
		//
		
		Structure request = createAndPopulateRequest();
		
		//
		// When
		//
		
		template.sendBody("direct:start", request);
		
		//
		// Then
		//
		
		// check access to jco fields

		verify(mockParameterListCharField, times(1)).setValue((Object)CHAR_PARAM_IN_VAL);
		verify(mockParameterListNumField, times(1)).setValue((Object)NUM_PARAM_IN_VAL);
		verify(mockParameterListIntField, times(1)).setValue((Object)INT_PARAM_IN_VAL);
		verify(mockParameterListFloatField, times(1)).setValue((Object)FLOAT_PARAM_IN_VAL);
		verify(mockParameterListBCDField, times(1)).setValue((Object)BCD_PARAM_IN_VAL);
		verify(mockParameterListBinaryField, times(1)).setValue((Object)BINARY_PARAM_IN_VAL);
		verify(mockParameterListBinaryArrayField, times(1)).setValue((Object)BINARY_ARRAY_PARAM_IN_VAL);
		verify(mockParameterListDateField, times(1)).setValue((Object)DATE_PARAM_IN_VAL);
		verify(mockParameterListTimeField, times(1)).setValue((Object)TIME_PARAM_IN_VAL);
		verify(mockParameterListStringField, times(1)).setValue((Object)STRING_PARAM_IN_VAL);
		
		verify(mockCharField, times(2)).setValue((Object)CHAR_PARAM_IN_VAL);
		verify(mockNumField, times(2)).setValue((Object)NUM_PARAM_IN_VAL);
		verify(mockIntField, times(2)).setValue((Object)INT_PARAM_IN_VAL);
		verify(mockFloatField, times(2)).setValue((Object)FLOAT_PARAM_IN_VAL);
		verify(mockBCDField, times(2)).setValue((Object)BCD_PARAM_IN_VAL);
		verify(mockBinaryField, times(2)).setValue((Object)BINARY_PARAM_IN_VAL);
		verify(mockBinaryArrayField, times(2)).setValue((Object)BINARY_ARRAY_PARAM_IN_VAL);
		verify(mockDateField, times(2)).setValue((Object)DATE_PARAM_IN_VAL);
		verify(mockTimeField, times(2)).setValue((Object)TIME_PARAM_IN_VAL);
		verify(mockStringField, times(2)).setValue((Object)STRING_PARAM_IN_VAL);
		
		verify(mockChangingParameterList, times(1)).getFieldIterator();
		
		verify(mockStructure, times(1)).getFieldIterator();
		
		verify(mockTable, times(1)).appendRow();
		verify(mockTable, times(1)).getFieldIterator();
		
		verify(mockFunction, times(1)).execute(mockDestination, TEST_TID, QUEUE_NAME);
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("sap-qrfc-destination:TEST_DEST:TEST_QUEUE:TEST_FUNCTION_MODULE").to("mock:result");
			}
		};
	}

}
