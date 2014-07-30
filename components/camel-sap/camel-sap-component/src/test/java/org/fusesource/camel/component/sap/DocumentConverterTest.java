package org.fusesource.camel.component.sap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.fusesource.camel.component.sap.converter.DocumentConverter;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl;
import org.fusesource.camel.component.sap.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoIDoc.class })
public class DocumentConverterTest extends SapIDocTestSupport {

	public static final String DOCUMENT_STRING = 
		"<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
		"<idoc:Document xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:TEST_IDOC_TYPE-TEST_IDOC_TYPE_EXTENSION-TEST_SYSTEM_RELEASE-TEST_APPLICATION_RELEASE=\"http://sap.fusesource.org/idoc/TEST_REPOSITORY/TEST_IDOC_TYPE/TEST_IDOC_TYPE_EXTENSION/TEST_SYSTEM_RELEASE/TEST_APPLICATION_RELEASE\" xmlns:idoc=\"http://sap.fusesource.org/idoc\" archiveKey=\"archiveKeyValue\" client=\"clientValue\" creationDate=\"1861-04-12T11:50:37.722-0500\" creationTime=\"2014-07-29T04:30:15.725-0400\" direction=\"directionValue\" EDIMessage=\"ediMessageValue\" EDIMessageGroup=\"editMessageGroupValue\" EDIMessageType=\"editMessageTypeValue\" EDIStandardFlag=\"ediStandardFlagValue\" EDIStandardVersion=\"ediStandardVersionValue\" EDITransmissionFile=\"ediTransmissionFileValue\" iDocCompoundType=\"idocCompoundTypeValue\" iDocNumber=\"idocNumberValue\" iDocSAPRelease=\"idocSAPReleaseValue\" iDocType=\"idocTypeValue\" iDocTypeExtension=\"idocTypeExtensionValue\" messageCode=\"messageCodeValue\" messageFunction=\"messageFunctionValue\" messageType=\"messageTypeValue\" outputMode=\"outputModeValue\" recipientAddress=\"recipientAddressValue\" recipientLogicalAddress=\"recipientLogicalAddressValue\" recipientPartnerFunction=\"recipientPartnerFunctionValue\" recipientPartnerNumber=\"recipientPartnerNumberValue\" recipientPartnerType=\"recipientPartnerTypeValue\" recipientPort=\"recipientPortValue\" senderAddress=\"senderAddressValue\" senderLogicalAddress=\"senderLogicalAddressValue\" senderPartnerFunction=\"senderPartnerFunctionValue\" senderPartnerNumber=\"senderPartnerNumberValue\" senderPartnerType=\"senderPartnerTypeValue\" senderPort=\"senderPortValue\" serialization=\"serializationValue\" status=\"statusValue\" testFlag=\"testFlagValue\">" +
		"  <rootSegment xsi:type=\"TEST_IDOC_TYPE-TEST_IDOC_TYPE_EXTENSION-TEST_SYSTEM_RELEASE-TEST_APPLICATION_RELEASE:ROOT\" document=\"/\">" +
		"    <segmentChildren parent=\"//@rootSegment\">" +
		"      <LEVEL1 parent=\"//@rootSegment\" document=\"/\" FIELD0=\"FIELD0_VALUE\" FIELD1=\"FIELD1_VALUE\" FIELD2=\"FIELD2_VALUE\" FIELD3=\"FIELD3_VALUE\" FIELD4=\"FIELD4_VALUE\" FIELD5=\"FIELD5_VALUE\" FIELD6=\"FIELD6_VALUE\" FIELD7=\"FIELD7_VALUE\" FIELD8=\"FIELD8_VALUE\" FIELD9=\"FIELD9_VALUE\" FIELD10=\"FIELD10_VALUE\" FIELD11=\"FIELD11_VALUE\" FIELD12=\"FIELD12_VALUE\" FIELD13=\"FIELD13_VALUE\" FIELD14=\"FIELD14_VALUE\" FIELD15=\"FIELD15_VALUE\" FIELD16=\"FIELD16_VALUE\" FIELD17=\"FIELD17_VALUE\" FIELD18=\"FIELD18_VALUE\" FIELD19=\"FIELD19_VALUE\" FIELD20=\"FIELD20_VALUE\">" +
		"        <segmentChildren parent=\"//@rootSegment/@segmentChildren/@LEVEL1.0\">" +
		"          <LEVEL2 parent=\"//@rootSegment/@segmentChildren/@LEVEL1.0\" document=\"/\" FIELD0=\"FIELD0_VALUE\" FIELD1=\"FIELD1_VALUE\" FIELD2=\"FIELD2_VALUE\" FIELD3=\"FIELD3_VALUE\" FIELD4=\"FIELD4_VALUE\" FIELD5=\"FIELD5_VALUE\" FIELD6=\"FIELD6_VALUE\" FIELD7=\"FIELD7_VALUE\" FIELD8=\"FIELD8_VALUE\" FIELD9=\"FIELD9_VALUE\" FIELD10=\"FIELD10_VALUE\" FIELD11=\"FIELD11_VALUE\" FIELD12=\"FIELD12_VALUE\" FIELD13=\"FIELD13_VALUE\" FIELD14=\"FIELD14_VALUE\" FIELD15=\"FIELD15_VALUE\" FIELD16=\"FIELD16_VALUE\" FIELD17=\"FIELD17_VALUE\" FIELD18=\"FIELD18_VALUE\" FIELD19=\"FIELD19_VALUE\" FIELD20=\"FIELD20_VALUE\">" +
		"            <segmentChildren parent=\"//@rootSegment/@segmentChildren/@LEVEL1.0/@segmentChildren/@LEVEL2.0\">" +
		"              <LEVEL3 parent=\"//@rootSegment/@segmentChildren/@LEVEL1.0/@segmentChildren/@LEVEL2.0\" document=\"/\" FIELD0=\"FIELD0_VALUE\" FIELD1=\"FIELD1_VALUE\" FIELD2=\"FIELD2_VALUE\" FIELD3=\"FIELD3_VALUE\" FIELD4=\"FIELD4_VALUE\" FIELD5=\"FIELD5_VALUE\" FIELD6=\"FIELD6_VALUE\" FIELD7=\"FIELD7_VALUE\" FIELD8=\"FIELD8_VALUE\" FIELD9=\"FIELD9_VALUE\" FIELD10=\"FIELD10_VALUE\" FIELD11=\"FIELD11_VALUE\" FIELD12=\"FIELD12_VALUE\" FIELD13=\"FIELD13_VALUE\" FIELD14=\"FIELD14_VALUE\" FIELD15=\"FIELD15_VALUE\" FIELD16=\"FIELD16_VALUE\" FIELD17=\"FIELD17_VALUE\" FIELD18=\"FIELD18_VALUE\" FIELD19=\"FIELD19_VALUE\" FIELD20=\"FIELD20_VALUE\"/>" +
		"            </segmentChildren>" +
		"          </LEVEL2>" +
		"        </segmentChildren>" +
		"      </LEVEL1>" +
		"    </segmentChildren>" +
		"  </rootSegment>" +
		"</idoc:Document>";

	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();
		
		PowerMockito.mockStatic(JCoDestinationManager.class, JCoIDoc.class);
		when(JCoDestinationManager.getDestination(TEST_DEST)).thenReturn(mockDestination);
		when(JCoIDoc.getIDocRepository(mockDestination)).thenReturn(mockIDocRepository);
		when(JCoIDoc.getIDocFactory()).thenReturn(mockIDocFactory);
		when(JCoIDoc.getServer(TEST_SERVER)).thenReturn(mockIDocServer);
		
	}

	@Test
	public void testToDocumentFromString() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		
		//
		// When
		//
		
		Document document = DocumentConverter.toDocument(DOCUMENT_STRING); 

		//
		// Then
		//
		
		verifyDocument(document);

	}

	@Test
	public void testToDocumentFormInputStream() throws Exception{

		//
		// Given
		//
		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		ByteArrayInputStream bais = new ByteArrayInputStream(DOCUMENT_STRING.getBytes());
		
		//
		// When
		//
		
		Document document = DocumentConverter.toDocument(bais);

		//
		// Then
		//

		verifyDocument(document);

	}

	@Test
	public void testToDocumentFromByteArray() throws Exception {
		
		//
		// Given
		//
		
		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		
		//
		// When
		//
		
		Document document = DocumentConverter.toDocument(DOCUMENT_STRING.getBytes());

		//
		// Then
		//

		verifyDocument(document);

	}

	@Test
	public void testToString() throws Exception {

		//
		// Given
		//

		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		Document document = createAndPopulateDocument();
		
		//
		// When
		//
		
		String documentString = DocumentConverter.toString((DocumentImpl)document);
		
		//
		// Then
		//

		document = DocumentConverter.toDocument(documentString);
		verifyDocument(document);

	}

	@Test
	public void testToOutputStream() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		Document document = createAndPopulateDocument();
		
		//
		// When
		//

		OutputStream os = DocumentConverter.toOutputStream((DocumentImpl)document);
		
		//
		// Then
		//
		
		byte[] bytes = ((ByteArrayOutputStream)os).toByteArray();
		document = DocumentConverter.toDocument(bytes);
		verifyDocument(document);
		
	}

	@Test
	public void testToInputStream() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRegistry.ecore");
		Util.loadRegistry(file);
		Document document = createAndPopulateDocument();
		
		//
		// When
		//
		
		InputStream is = DocumentConverter.toInputStream((DocumentImpl)document);
		
		//
		// Then
		//

		document = DocumentConverter.toDocument(is);
		verifyDocument(document);

	}
	
	public void verifyDocument(Document document) throws Exception {
		assertThat("The document is an unexpected null value",document, notNullValue());
		assertThat("document.getArchiveKey() returned '" +  document.getArchiveKey() + "' instead of expected value '" + ARCHIVE_KEY_VALUE + "'", (String) document.getArchiveKey(), is(ARCHIVE_KEY_VALUE));
		assertThat("document.getClient() returned '" +  document.getClient() + "' instead of expected value '" + CLIENT_VALUE + "'", (String) document.getClient(), is(CLIENT_VALUE));
		assertThat("document.getDirection() returned '" +  document.getDirection() + "' instead of expected value '" + DIRECTION_VALUE + "'", (String) document.getDirection(), is(DIRECTION_VALUE));
		assertThat("document.getEDIMessage() returned '" +  document.getEDIMessage() + "' instead of expected value '" + EDI_MESSAGE_VALUE + "'", (String) document.getEDIMessage(), is(EDI_MESSAGE_VALUE));
		assertThat("document.getEDIMessageGroup() returned '" +  document.getEDIMessageGroup() + "' instead of expected value '" + EDI_MESSAGE_GROUP_VALUE + "'", (String) document.getEDIMessageGroup(), is(EDI_MESSAGE_GROUP_VALUE));
		assertThat("document.getEDIMessageType() returned '" +  document.getEDIMessageType() + "' instead of expected value '" + EDI_MESSAGE_TYPE_VALUE + "'", (String) document.getEDIMessageType(), is(EDI_MESSAGE_TYPE_VALUE));
		assertThat("document.getEDIStandardFlag() returned '" +  document.getEDIStandardFlag() + "' instead of expected value '" + EDI_STANDARD_FLAG_VALUE + "'", (String) document.getEDIStandardFlag(), is(EDI_STANDARD_FLAG_VALUE));
		assertThat("document.getEDIStandardVersion() returned '" +  document.getEDIStandardVersion() + "' instead of expected value '" + EDI_STANDARD_VERSION_VALUE + "'", (String) document.getEDIStandardVersion(), is(EDI_STANDARD_VERSION_VALUE));
		assertThat("document.getEDITransmissionFile() returned '" +  document.getEDITransmissionFile() + "' instead of expected value '" + EDI_TRANSMISSION_FILE_VALUE + "'", (String) document.getEDITransmissionFile(), is(EDI_TRANSMISSION_FILE_VALUE));
		assertThat("document.getIDocCompoundType() returned '" +  document.getIDocCompoundType() + "' instead of expected value '" + IDOC_COMPOUND_TYPE_VALUE + "'", (String) document.getIDocCompoundType(), is(IDOC_COMPOUND_TYPE_VALUE));
		assertThat("document.getIDocNumber() returned '" +  document.getIDocNumber() + "' instead of expected value '" + IDOC_NUMBER_VALUE + "'", (String) document.getIDocNumber(), is(IDOC_NUMBER_VALUE));
		assertThat("document.getIDocSAPRelease() returned '" +  document.getIDocSAPRelease() + "' instead of expected value '" + IDOC_SAP_RELEASE_VALUE + "'", (String) document.getIDocSAPRelease(), is(IDOC_SAP_RELEASE_VALUE));
		assertThat("document.getIDocType() returned '" +  document.getIDocType() + "' instead of expected value '" + IDOC_TYPE_VALUE + "'", (String) document.getIDocType(), is(IDOC_TYPE_VALUE));
		assertThat("document.getIDocTypeExtension() returned '" +  document.getIDocTypeExtension() + "' instead of expected value '" + IDOC_TYPE_EXTENSION_VALUE + "'", (String) document.getIDocTypeExtension(), is(IDOC_TYPE_EXTENSION_VALUE));
		assertThat("document.getMessageCode() returned '" +  document.getMessageCode() + "' instead of expected value '" + MESSAGE_CODE_VALUE + "'", (String) document.getMessageCode(), is(MESSAGE_CODE_VALUE));
		assertThat("document.getMessageFunction() returned '" +  document.getMessageFunction() + "' instead of expected value '" + MESSAGE_FUNCTION_VALUE + "'", (String) document.getMessageFunction(), is(MESSAGE_FUNCTION_VALUE));
		assertThat("document.getMessageType() returned '" +  document.getMessageType() + "' instead of expected value '" + MESSAGE_TYPE_VALUE + "'", (String) document.getMessageType(), is(MESSAGE_TYPE_VALUE));
		assertThat("document.getOutputMode() returned '" +  document.getOutputMode() + "' instead of expected value '" + OUTPUT_MODE_VALUE + "'", (String) document.getOutputMode(), is(OUTPUT_MODE_VALUE));
		assertThat("document.getRecipientAddress() returned '" +  document.getRecipientAddress() + "' instead of expected value '" + RECIPIENT_ADDRESS_VALUE + "'", (String) document.getRecipientAddress(), is(RECIPIENT_ADDRESS_VALUE));
		assertThat("document.getRecipientLogicalAddress() returned '" +  document.getRecipientLogicalAddress() + "' instead of expected value '" + RECIPIENT_LOGICAL_ADDRESS_VALUE + "'", (String) document.getRecipientLogicalAddress(), is(RECIPIENT_LOGICAL_ADDRESS_VALUE));
		assertThat("document.getRecipientPartnerFunction() returned '" +  document.getRecipientPartnerFunction() + "' instead of expected value '" + RECIPIENT_PARTNER_FUNCTION_VALUE + "'", (String) document.getRecipientPartnerFunction(), is(RECIPIENT_PARTNER_FUNCTION_VALUE));
		assertThat("document.getRecipientPartnerNumber() returned '" +  document.getRecipientPartnerNumber() + "' instead of expected value '" + RECIPIENT_PARTNER_NUMBER_VALUE + "'", (String) document.getRecipientPartnerNumber(), is(RECIPIENT_PARTNER_NUMBER_VALUE));
		assertThat("document.getRecipientPartnerType() returned '" +  document.getRecipientPartnerType() + "' instead of expected value '" + RECIPIENT_PARTNER_TYPE_VALUE + "'", (String) document.getRecipientPartnerType(), is(RECIPIENT_PARTNER_TYPE_VALUE));
		assertThat("document.getRecipientPort() returned '" +  document.getRecipientPort() + "' instead of expected value '" + RECIPIENT_PORT_VALUE + "'", (String) document.getRecipientPort(), is(RECIPIENT_PORT_VALUE));
		assertThat("document.getSenderAddress() returned '" +  document.getSenderAddress() + "' instead of expected value '" + SENDER_ADDRESS_VALUE + "'", (String) document.getSenderAddress(), is(SENDER_ADDRESS_VALUE));
		assertThat("document.getSenderLogicalAddress() returned '" +  document.getSenderLogicalAddress() + "' instead of expected value '" + SENDER_LOGICAL_ADDRESS_VALUE + "'", (String) document.getSenderLogicalAddress(), is(SENDER_LOGICAL_ADDRESS_VALUE));
		assertThat("document.getSenderPartnerFunction() returned '" +  document.getSenderPartnerFunction() + "' instead of expected value '" + SENDER_PARTNER_FUNCTION_VALUE + "'", (String) document.getSenderPartnerFunction(), is(SENDER_PARTNER_FUNCTION_VALUE));
		assertThat("document.getSenderPartnerNumber() returned '" +  document.getSenderPartnerNumber() + "' instead of expected value '" + SENDER_PARTNER_NUMBER_VALUE + "'", (String) document.getSenderPartnerNumber(), is(SENDER_PARTNER_NUMBER_VALUE));
		assertThat("document.getSenderPartnerType() returned '" +  document.getSenderPartnerType() + "' instead of expected value '" + SENDER_PARTNER_TYPE_VALUE + "'", (String) document.getSenderPartnerType(), is(SENDER_PARTNER_TYPE_VALUE));
		assertThat("document.getSenderPort() returned '" +  document.getSenderPort() + "' instead of expected value '" + SENDER_PORT_VALUE + "'", (String) document.getSenderPort(), is(SENDER_PORT_VALUE));
		assertThat("document.getSerialization() returned '" +  document.getSerialization() + "' instead of expected value '" + SERIALIZATION_VALUE + "'", (String) document.getSerialization(), is(SERIALIZATION_VALUE));
		assertThat("document.getStatus() returned '" +  document.getStatus() + "' instead of expected value '" + STATUS_VALUE + "'", (String) document.getStatus(), is(STATUS_VALUE));
		assertThat("document.getTestFlag() returned '" +  document.getTestFlag() + "' instead of expected value '" + TEST_FLAG_VALUE + "'", (String) document.getTestFlag(), is(TEST_FLAG_VALUE));
		
		// Creation time and date reset when document is created.
//		assertThat("document.getCreationDate() returned '" +  document.getCreationDate() + "' instead of expected value '" + DATE_VALUE + "'", document.getCreationDate(), is(DATE_VALUE.getTime()));
//		assertThat("document.getCreationTime() returned '" +  document.getCreationTime() + "' instead of expected value '" + TIME_VALUE + "'", document.getCreationTime(), is(TIME_VALUE.getTime()));

		
		Segment rootSegment = document.getRootSegment();
		assertThat("document.getRootSegment() returned unexpected null value", rootSegment, notNullValue());
		assertThat("rootSegment.getType() returned unexpected value", rootSegment.getType(), is(ROOT));
		assertThat("rootSegment.getDefinition() returned unexpected value", rootSegment.getDefinition(), is(ROOT));
		assertThat("rootSegment.getDescription() returned unexpected value", rootSegment.getDescription(), is(ROOT_DESCRIPTION));
		assertThat("rootSegment.getDocument() returned unexpected value", rootSegment.getDocument(), is(document));
		assertThat("rootSegment.getHierarchyLevel() returned unexpected value", rootSegment.getHierarchyLevel(), is(0));
		assertThat("rootSegment.getMaxOccurrence() returned unexpected value", rootSegment.getMaxOccurrence(), is(1L));
		assertThat("rootSegment.getMinOccurrence() returned unexpected value", rootSegment.getMinOccurrence(), is(1L));
		assertThat("rootSegment.getParent() returned unexpected non null value", rootSegment.getParent(), nullValue());
		assertThat("rootSegment.getRecordLength() returned unexpected value", rootSegment.getRecordLength(), is(0));
		assertThat("rootSegment.getTypes().get(0) returned unexpected value", rootSegment.getTypes().get(0), is(LEVEL1));
		assertThat("rootSegment.isMandatory() returned unexpected value", rootSegment.isMandatory(), is(true));
		assertThat("rootSegment.isQualified() returned unexpected value", rootSegment.isQualified(), is(false));
		assertThat("rootSegment.getNumFields() returned unexpected value", rootSegment.getNumFields(), is(0));
		
		Segment level1Segment = rootSegment.getChildren().get(0);
		assertThat("rootSegment.getChildren().get(0) returned unexpected null value", level1Segment, notNullValue());
		assertThat("level1Segment.getType() returned unexpected value", level1Segment.getType(), is(LEVEL1));
		assertThat("level1Segment.getDefinition() returned unexpected value", level1Segment.getDefinition(), is(LEVEL1));
		assertThat("level1Segment.getDescription() returned unexpected value", level1Segment.getDescription(), is(LEVEL1_DESCRIPTION));
		assertThat("level1Segment.getDocument() returned unexpected value", level1Segment.getDocument(), is(document));
		assertThat("level1Segment.getHierarchyLevel() returned unexpected value", level1Segment.getHierarchyLevel(), is(1));
		assertThat("level1Segment.getMaxOccurrence() returned unexpected value", level1Segment.getMaxOccurrence(), is(9999999999L));
		assertThat("level1Segment.getMinOccurrence() returned unexpected value", level1Segment.getMinOccurrence(), is(1L));
		assertThat("level1Segment.getParent() returned unexpected value", level1Segment.getParent(), is(rootSegment));
		assertThat("level1Segment.getRecordLength() returned unexpected value", level1Segment.getRecordLength(), is(210));
		assertThat("level1Segment.getTypes().get(0) returned unexpected value", level1Segment.getTypes().get(0), is(LEVEL2));
		assertThat("level1Segment.isMandatory() returned unexpected value", level1Segment.isMandatory(), is(true));
		assertThat("level1Segment.isQualified() returned unexpected value", level1Segment.isQualified(), is(false));
		assertThat("level1Segment.getNumFields() returned unexpected value", level1Segment.getNumFields(), is(21));
		assertThat("level1Segment.get(FIELD0) returned unexpected value", (String) level1Segment.get(FIELD0), is(FIELD0_VALUE));
		assertThat("level1Segment.get(FIELD1) returned unexpected value", (String) level1Segment.get(FIELD1), is(FIELD1_VALUE));
		assertThat("level1Segment.get(FIELD2) returned unexpected value", (String) level1Segment.get(FIELD2), is(FIELD2_VALUE));
		assertThat("level1Segment.get(FIELD3) returned unexpected value", (String) level1Segment.get(FIELD3), is(FIELD3_VALUE));
		assertThat("level1Segment.get(FIELD4) returned unexpected value", (String) level1Segment.get(FIELD4), is(FIELD4_VALUE));
		assertThat("level1Segment.get(FIELD5) returned unexpected value", (String) level1Segment.get(FIELD5), is(FIELD5_VALUE));
		assertThat("level1Segment.get(FIELD6) returned unexpected value", (String) level1Segment.get(FIELD6), is(FIELD6_VALUE));
		assertThat("level1Segment.get(FIELD7) returned unexpected value", (String) level1Segment.get(FIELD7), is(FIELD7_VALUE));
		assertThat("level1Segment.get(FIELD8) returned unexpected value", (String) level1Segment.get(FIELD8), is(FIELD8_VALUE));
		assertThat("level1Segment.get(FIELD9) returned unexpected value", (String) level1Segment.get(FIELD9), is(FIELD9_VALUE));
		assertThat("level1Segment.get(FIELD10) returned unexpected value", (String) level1Segment.get(FIELD10), is(FIELD10_VALUE));
		assertThat("level1Segment.get(FIELD11) returned unexpected value", (String) level1Segment.get(FIELD11), is(FIELD11_VALUE));
		assertThat("level1Segment.get(FIELD12) returned unexpected value", (String) level1Segment.get(FIELD12), is(FIELD12_VALUE));
		assertThat("level1Segment.get(FIELD13) returned unexpected value", (String) level1Segment.get(FIELD13), is(FIELD13_VALUE));
		assertThat("level1Segment.get(FIELD14) returned unexpected value", (String) level1Segment.get(FIELD14), is(FIELD14_VALUE));
		assertThat("level1Segment.get(FIELD15) returned unexpected value", (String) level1Segment.get(FIELD15), is(FIELD15_VALUE));
		assertThat("level1Segment.get(FIELD16) returned unexpected value", (String) level1Segment.get(FIELD16), is(FIELD16_VALUE));
		assertThat("level1Segment.get(FIELD17) returned unexpected value", (String) level1Segment.get(FIELD17), is(FIELD17_VALUE));
		assertThat("level1Segment.get(FIELD18) returned unexpected value", (String) level1Segment.get(FIELD18), is(FIELD18_VALUE));
		assertThat("level1Segment.get(FIELD19) returned unexpected value", (String) level1Segment.get(FIELD19), is(FIELD19_VALUE));
		assertThat("level1Segment.get(FIELD20) returned unexpected value", (String) level1Segment.get(FIELD20), is(FIELD20_VALUE));

		Segment level2Segment = level1Segment.getChildren().get(0);
		assertThat("level2Segment.getChildren().get(0) returned unexpected null value", level2Segment, notNullValue());
		assertThat("level2Segment.getType() returned unexpected value", level2Segment.getType(), is(LEVEL2));
		assertThat("level2Segment.getDefinition() returned unexpected value", level2Segment.getDefinition(), is(LEVEL2));
		assertThat("level2Segment.getDescription() returned unexpected value", level2Segment.getDescription(), is(LEVEL2_DESCRIPTION));
		assertThat("level2Segment.getDocument() returned unexpected value", level2Segment.getDocument(), is(document));
		assertThat("level2Segment.getHierarchyLevel() returned unexpected value", level2Segment.getHierarchyLevel(), is(2));
		assertThat("level2Segment.getMaxOccurrence() returned unexpected value", level2Segment.getMaxOccurrence(), is(9999999999L));
		assertThat("level2Segment.getMinOccurrence() returned unexpected value", level2Segment.getMinOccurrence(), is(1L));
		assertThat("level2Segment.getParent() returned unexpected value", level2Segment.getParent(), is(level1Segment));
		assertThat("level2Segment.getRecordLength() returned unexpected value", level2Segment.getRecordLength(), is(210));
		assertThat("level2Segment.getTypes().get(0) returned unexpected value", level2Segment.getTypes().get(0), is(LEVEL3));
		assertThat("level2Segment.isMandatory() returned unexpected value", level2Segment.isMandatory(), is(true));
		assertThat("level2Segment.isQualified() returned unexpected value", level2Segment.isQualified(), is(false));
		assertThat("level2Segment.getNumFields() returned unexpected value", level2Segment.getNumFields(), is(21));
		assertThat("level2Segment.get(FIELD0) returned unexpected value", (String) level2Segment.get(FIELD0), is(FIELD0_VALUE));
		assertThat("level2Segment.get(FIELD1) returned unexpected value", (String) level2Segment.get(FIELD1), is(FIELD1_VALUE));
		assertThat("level2Segment.get(FIELD2) returned unexpected value", (String) level2Segment.get(FIELD2), is(FIELD2_VALUE));
		assertThat("level2Segment.get(FIELD3) returned unexpected value", (String) level2Segment.get(FIELD3), is(FIELD3_VALUE));
		assertThat("level2Segment.get(FIELD4) returned unexpected value", (String) level2Segment.get(FIELD4), is(FIELD4_VALUE));
		assertThat("level2Segment.get(FIELD5) returned unexpected value", (String) level2Segment.get(FIELD5), is(FIELD5_VALUE));
		assertThat("level2Segment.get(FIELD6) returned unexpected value", (String) level2Segment.get(FIELD6), is(FIELD6_VALUE));
		assertThat("level2Segment.get(FIELD7) returned unexpected value", (String) level2Segment.get(FIELD7), is(FIELD7_VALUE));
		assertThat("level2Segment.get(FIELD8) returned unexpected value", (String) level2Segment.get(FIELD8), is(FIELD8_VALUE));
		assertThat("level2Segment.get(FIELD9) returned unexpected value", (String) level2Segment.get(FIELD9), is(FIELD9_VALUE));
		assertThat("level2Segment.get(FIELD10) returned unexpected value", (String) level2Segment.get(FIELD10), is(FIELD10_VALUE));
		assertThat("level2Segment.get(FIELD11) returned unexpected value", (String) level2Segment.get(FIELD11), is(FIELD11_VALUE));
		assertThat("level2Segment.get(FIELD12) returned unexpected value", (String) level2Segment.get(FIELD12), is(FIELD12_VALUE));
		assertThat("level2Segment.get(FIELD13) returned unexpected value", (String) level2Segment.get(FIELD13), is(FIELD13_VALUE));
		assertThat("level2Segment.get(FIELD14) returned unexpected value", (String) level2Segment.get(FIELD14), is(FIELD14_VALUE));
		assertThat("level2Segment.get(FIELD15) returned unexpected value", (String) level2Segment.get(FIELD15), is(FIELD15_VALUE));
		assertThat("level2Segment.get(FIELD16) returned unexpected value", (String) level2Segment.get(FIELD16), is(FIELD16_VALUE));
		assertThat("level2Segment.get(FIELD17) returned unexpected value", (String) level2Segment.get(FIELD17), is(FIELD17_VALUE));
		assertThat("level2Segment.get(FIELD18) returned unexpected value", (String) level2Segment.get(FIELD18), is(FIELD18_VALUE));
		assertThat("level2Segment.get(FIELD19) returned unexpected value", (String) level2Segment.get(FIELD19), is(FIELD19_VALUE));
		assertThat("level2Segment.get(FIELD20) returned unexpected value", (String) level2Segment.get(FIELD20), is(FIELD20_VALUE));

		Segment level3Segment = level2Segment.getChildren().get(0);
		assertThat("level3Segment.getChildren().get(0) returned unexpected null value", level3Segment, notNullValue());
		assertThat("level3Segment.getType() returned unexpected value", level3Segment.getType(), is(LEVEL3));
		assertThat("level3Segment.getDefinition() returned unexpected value", level3Segment.getDefinition(), is(LEVEL3));
		assertThat("level3Segment.getDescription() returned unexpected value", level3Segment.getDescription(), is(LEVEL3_DESCRIPTION));
		assertThat("level3Segment.getDocument() returned unexpected value", level3Segment.getDocument(), is(document));
		assertThat("level3Segment.getHierarchyLevel() returned unexpected value", level3Segment.getHierarchyLevel(), is(3));
		assertThat("level3Segment.getMaxOccurrence() returned unexpected value", level3Segment.getMaxOccurrence(), is(9999999999L));
		assertThat("level3Segment.getMinOccurrence() returned unexpected value", level3Segment.getMinOccurrence(), is(1L));
		assertThat("level3Segment.getParent() returned unexpected value", level3Segment.getParent(), is(level2Segment));
		assertThat("level3Segment.getRecordLength() returned unexpected value", level3Segment.getRecordLength(), is(210));
		assertThat("level3Segment.getTypes() returned unexpected value", level3Segment.getTypes().size(), is(0));
		assertThat("level3Segment.isMandatory() returned unexpected value", level3Segment.isMandatory(), is(true));
		assertThat("level3Segment.isQualified() returned unexpected value", level3Segment.isQualified(), is(false));
		assertThat("level3Segment.getNumFields() returned unexpected value", level3Segment.getNumFields(), is(21));
		assertThat("level3Segment.get(FIELD0) returned unexpected value", (String) level3Segment.get(FIELD0), is(FIELD0_VALUE));
		assertThat("level3Segment.get(FIELD1) returned unexpected value", (String) level3Segment.get(FIELD1), is(FIELD1_VALUE));
		assertThat("level3Segment.get(FIELD2) returned unexpected value", (String) level3Segment.get(FIELD2), is(FIELD2_VALUE));
		assertThat("level3Segment.get(FIELD3) returned unexpected value", (String) level3Segment.get(FIELD3), is(FIELD3_VALUE));
		assertThat("level3Segment.get(FIELD4) returned unexpected value", (String) level3Segment.get(FIELD4), is(FIELD4_VALUE));
		assertThat("level3Segment.get(FIELD5) returned unexpected value", (String) level3Segment.get(FIELD5), is(FIELD5_VALUE));
		assertThat("level3Segment.get(FIELD6) returned unexpected value", (String) level3Segment.get(FIELD6), is(FIELD6_VALUE));
		assertThat("level3Segment.get(FIELD7) returned unexpected value", (String) level3Segment.get(FIELD7), is(FIELD7_VALUE));
		assertThat("level3Segment.get(FIELD8) returned unexpected value", (String) level3Segment.get(FIELD8), is(FIELD8_VALUE));
		assertThat("level3Segment.get(FIELD9) returned unexpected value", (String) level3Segment.get(FIELD9), is(FIELD9_VALUE));
		assertThat("level3Segment.get(FIELD10) returned unexpected value", (String) level3Segment.get(FIELD10), is(FIELD10_VALUE));
		assertThat("level3Segment.get(FIELD11) returned unexpected value", (String) level3Segment.get(FIELD11), is(FIELD11_VALUE));
		assertThat("level3Segment.get(FIELD12) returned unexpected value", (String) level3Segment.get(FIELD12), is(FIELD12_VALUE));
		assertThat("level3Segment.get(FIELD13) returned unexpected value", (String) level3Segment.get(FIELD13), is(FIELD13_VALUE));
		assertThat("level3Segment.get(FIELD14) returned unexpected value", (String) level3Segment.get(FIELD14), is(FIELD14_VALUE));
		assertThat("level3Segment.get(FIELD15) returned unexpected value", (String) level3Segment.get(FIELD15), is(FIELD15_VALUE));
		assertThat("level3Segment.get(FIELD16) returned unexpected value", (String) level3Segment.get(FIELD16), is(FIELD16_VALUE));
		assertThat("level3Segment.get(FIELD17) returned unexpected value", (String) level3Segment.get(FIELD17), is(FIELD17_VALUE));
		assertThat("level3Segment.get(FIELD18) returned unexpected value", (String) level3Segment.get(FIELD18), is(FIELD18_VALUE));
		assertThat("level3Segment.get(FIELD19) returned unexpected value", (String) level3Segment.get(FIELD19), is(FIELD19_VALUE));
		assertThat("level3Segment.get(FIELD20) returned unexpected value", (String) level3Segment.get(FIELD20), is(FIELD20_VALUE));

		
	}

}
