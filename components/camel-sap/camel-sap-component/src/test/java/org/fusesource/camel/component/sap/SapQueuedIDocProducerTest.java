package org.fusesource.camel.component.sap;

import org.apache.camel.builder.RouteBuilder;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.idoc.IDocFactory;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoIDoc.class })
public class SapQueuedIDocProducerTest extends SapIDocTestSupport {

	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();

		PowerMockito.mockStatic(JCoDestinationManager.class, JCoIDoc.class);
		when(JCoDestinationManager.getDestination(TEST_DEST)).thenReturn(mockDestination);
		when(JCoIDoc.getIDocRepository(mockDestination)).thenReturn(mockIDocRepository);
		when(JCoIDoc.getIDocFactory()).thenReturn(mockIDocFactory);
		
	}
	
	@Test
	public void testProducer() throws Exception{ 
		
		//
		// Given
		//
		
		Document document = createAndPopulateDocument();

		//
		// When
		//

		template.sendBody("direct:start", document);
	
		//
		// Then
		//
		
		verify(mockIDocDocument, times(1)).setArchiveKey(ARCHIVE_KEY_VALUE);
		verify(mockIDocDocument, times(1)).setClient(CLIENT_VALUE);
		verify(mockIDocDocument, times(1)).setCreationDate(DATE_VALUE.getTime());
		verify(mockIDocDocument, times(1)).setCreationTime(TIME_VALUE.getTime());
		verify(mockIDocDocument, times(1)).setDirection(DIRECTION_VALUE);
		verify(mockIDocDocument, times(1)).setEDIMessage(EDI_MESSAGE_VALUE);
		verify(mockIDocDocument, times(1)).setEDIMessageGroup(EDI_MESSAGE_GROUP_VALUE);
		verify(mockIDocDocument, times(1)).setEDIMessageType(EDI_MESSAGE_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setEDIStandardFlag(EDI_STANDARD_FLAG_VALUE);
		verify(mockIDocDocument, times(1)).setEDIStandardVersion(EDI_STANDARD_VERSION_VALUE);
		verify(mockIDocDocument, times(1)).setEDITransmissionFile(EDI_TRANSMISSION_FILE_VALUE);
		verify(mockIDocDocument, times(1)).setIDocCompoundType(IDOC_COMPOUND_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setIDocNumber(IDOC_NUMBER_VALUE);
		verify(mockIDocDocument, times(1)).setIDocSAPRelease(IDOC_SAP_RELEASE_VALUE);
		verify(mockIDocDocument, times(1)).setIDocType(IDOC_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setIDocTypeExtension(IDOC_TYPE_EXTENSION_VALUE);
		verify(mockIDocDocument, times(1)).setMessageCode(MESSAGE_CODE_VALUE);
		verify(mockIDocDocument, times(1)).setMessageFunction(MESSAGE_FUNCTION_VALUE);
		verify(mockIDocDocument, times(1)).setMessageType(MESSAGE_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setOutputMode(OUTPUT_MODE_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientAddress(RECIPIENT_ADDRESS_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientLogicalAddress(RECIPIENT_LOGICAL_ADDRESS_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientPartnerFunction(RECIPIENT_PARTNER_FUNCTION_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientPartnerNumber(RECIPIENT_PARTNER_NUMBER_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientPartnerType(RECIPIENT_PARTNER_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setRecipientPort(RECIPIENT_PORT_VALUE);
		verify(mockIDocDocument, times(1)).setSenderAddress(SENDER_ADDRESS_VALUE);
		verify(mockIDocDocument, times(1)).setSenderLogicalAddress(SENDER_LOGICAL_ADDRESS_VALUE);
		verify(mockIDocDocument, times(1)).setSenderPartnerFunction(SENDER_PARTNER_FUNCTION_VALUE);
		verify(mockIDocDocument, times(1)).setSenderPartnerNumber(SENDER_PARTNER_NUMBER_VALUE);
		verify(mockIDocDocument, times(1)).setSenderPartnerType(SENDER_PARTNER_TYPE_VALUE);
		verify(mockIDocDocument, times(1)).setSenderPort(SENDER_PORT_VALUE);
		verify(mockIDocDocument, times(1)).setSerialization(SERIALIZATION_VALUE);
		verify(mockIDocDocument, times(1)).setStatus(STATUS_VALUE);
		verify(mockIDocDocument, times(1)).setTestFlag(TEST_FLAG_VALUE);
		
		verify(mockRootSegment, times(0)).setValue(anyString(), anyObject());
		
		verify(mockLevel1Segment, times(1)).setValue(FIELD0, (Object) FIELD0_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD1, (Object) FIELD1_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD2, (Object) FIELD2_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD3, (Object) FIELD3_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD4, (Object) FIELD4_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD5, (Object) FIELD5_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD6, (Object) FIELD6_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD7, (Object) FIELD7_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD8, (Object) FIELD8_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD9, (Object) FIELD9_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD10, (Object) FIELD10_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD11, (Object) FIELD11_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD12, (Object) FIELD12_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD13, (Object) FIELD13_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD14, (Object) FIELD14_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD15, (Object) FIELD15_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD16, (Object) FIELD16_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD17, (Object) FIELD17_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD18, (Object) FIELD18_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD19, (Object) FIELD19_VALUE);
		verify(mockLevel1Segment, times(1)).setValue(FIELD20, (Object) FIELD20_VALUE);
		
		verify(mockLevel2Segment, times(1)).setValue(FIELD0, (Object) FIELD0_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD1, (Object) FIELD1_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD2, (Object) FIELD2_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD3, (Object) FIELD3_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD4, (Object) FIELD4_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD5, (Object) FIELD5_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD6, (Object) FIELD6_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD7, (Object) FIELD7_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD8, (Object) FIELD8_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD9, (Object) FIELD9_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD10, (Object) FIELD10_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD11, (Object) FIELD11_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD12, (Object) FIELD12_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD13, (Object) FIELD13_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD14, (Object) FIELD14_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD15, (Object) FIELD15_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD16, (Object) FIELD16_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD17, (Object) FIELD17_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD18, (Object) FIELD18_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD19, (Object) FIELD19_VALUE);
		verify(mockLevel2Segment, times(1)).setValue(FIELD20, (Object) FIELD20_VALUE);
		
		verify(mockLevel3Segment, times(1)).setValue(FIELD0, (Object) FIELD0_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD1, (Object) FIELD1_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD2, (Object) FIELD2_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD3, (Object) FIELD3_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD4, (Object) FIELD4_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD5, (Object) FIELD5_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD6, (Object) FIELD6_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD7, (Object) FIELD7_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD8, (Object) FIELD8_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD9, (Object) FIELD9_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD10, (Object) FIELD10_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD11, (Object) FIELD11_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD12, (Object) FIELD12_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD13, (Object) FIELD13_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD14, (Object) FIELD14_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD15, (Object) FIELD15_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD16, (Object) FIELD16_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD17, (Object) FIELD17_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD18, (Object) FIELD18_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD19, (Object) FIELD19_VALUE);
		verify(mockLevel3Segment, times(1)).setValue(FIELD20, (Object) FIELD20_VALUE);
		
		PowerMockito.verifyStatic();
		JCoIDoc.send(mockIDocDocument, IDocFactory.IDOC_VERSION_DEFAULT, mockDestination, TEST_TID, TEST_QUEUE);
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("sap-qidoc-destination:TEST_DEST:TEST_QUEUE:TEST_IDOC_TYPE:TEST_IDOC_TYPE_EXTENSION:TEST_SYSTEM_VERSION:TEST_APPLICATION_VERSION");
			}
		};
	}
}
