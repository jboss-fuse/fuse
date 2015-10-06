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

import java.io.File;

import org.fusesource.camel.component.sap.SapRfcTestSupport;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServerFactory;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@MockPolicy({Slf4jMockPolicy.class})
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoServerFactory.class })
public class ITestSaveRequest extends SapRfcTestSupport {

	@SuppressWarnings("deprecation")
	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();

		PowerMockito.mockStatic(JCoDestinationManager.class, JCoServerFactory.class);
		when(JCoDestinationManager.getDestination(DESTINATION_NAME)).thenReturn(mockDestination);
		when(JCoServerFactory.get()).thenReturn(mockServerFactory);
		when(JCoServerFactory.getServer(SERVER_NAME)).thenReturn(mockServer);
		
	}

	@Test
	public void saveDocument() throws Exception {

		//
		// Given
		//
		enhanceParameterListMetaData();

		File file = new File("data/testRequest.xml");
		Structure request = createAndPopulateRequest();
		
		//
		// When
		//

		Util.save(file, request);

		//
		// Then
		//

	}

}
