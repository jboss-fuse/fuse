package org.fusesource.camel.component.sap.integration;

import java.io.File;

import org.fusesource.camel.component.sap.SapRfcTestSupport;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.fusesource.camel.component.sap.util.RfcUtil;
import org.fusesource.camel.component.sap.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServerFactory;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@MockPolicy({Slf4jMockPolicy.class})
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoServerFactory.class  })
public class ITestSaveRfcRepository extends SapRfcTestSupport {

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
	public void saveRegistry() throws Exception {

		//
		// Given
		//

		JCoDestination jcoDestination = JCoDestinationManager.getDestination(DESTINATION_NAME);
		JCoRepository repository = jcoDestination.getRepository();
		
		RfcUtil.getEPackage(repository, RfcPackage.eNS_URI);
		RfcUtil.getEPackage(repository, TEST_URL);
		File file = new File("data/testRfcRegistry.ecore");
		
		//
		// When
		//

		IDocUtil.saveRegistry(file);

		//
		// Then
		//

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
