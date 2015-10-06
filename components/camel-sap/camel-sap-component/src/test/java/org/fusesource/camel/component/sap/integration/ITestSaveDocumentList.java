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

import org.fusesource.camel.component.sap.SapIDocTestSupport;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@MockPolicy({Slf4jMockPolicy.class})
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoIDoc.class })
public class ITestSaveDocumentList extends SapIDocTestSupport {

	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();

		when(JCoDestinationManager.getDestination(TEST_DEST)).thenReturn(mockDestination);
		when(JCoIDoc.getIDocRepository(mockDestination)).thenReturn(mockIDocRepository);
		when(JCoIDoc.getIDocFactory()).thenReturn(mockIDocFactory);
		when(JCoIDoc.getServer(TEST_SERVER)).thenReturn(mockIDocServer);
		
	}

	@Test
	public void saveDocument() throws Exception {

		//
		// Given
		//
		File file = new File("data/testDocumentList.xml");
		DocumentList documentList = createAndPopulateDocumentList();
		
		//
		// When
		//

		IDocUtil.save(file, documentList);

		//
		// Then
		//

	}

}
