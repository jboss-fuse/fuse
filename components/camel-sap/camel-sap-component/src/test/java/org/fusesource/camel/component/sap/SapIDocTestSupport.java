/**
 * Copyright 2014 Red Hat, Inc.
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

import java.util.Calendar;
import java.util.NoSuchElementException;

import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.junit.BeforeClass;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import com.sap.conn.idoc.IDocDatatype;
import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocDocumentIterator;
import com.sap.conn.idoc.IDocDocumentList;
import com.sap.conn.idoc.IDocFactory;
import com.sap.conn.idoc.IDocRecordMetaData;
import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.IDocSegment;
import com.sap.conn.idoc.IDocSegmentMetaData;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerState;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class SapIDocTestSupport extends JCoTestSupport {
	
	public static final String TEST_PROGRAM_ID = "TEST_PROGRAM_ID";
	public static final String TEST_DEST = "TEST_DEST";
	public static final String TEST_SERVER = "TEST_SERVER";
	public static final String TEST_QUEUE = "TEST_QUEUE";
	
	public static final String FIELD0 = "FIELD0";
	public static final String FIELD1 = "FIELD1";
	public static final String FIELD2 = "FIELD2";
	public static final String FIELD3 = "FIELD3";
	public static final String FIELD4 = "FIELD4";
	public static final String FIELD5 = "FIELD5";
	public static final String FIELD6 = "FIELD6";
	public static final String FIELD7 = "FIELD7";
	public static final String FIELD8 = "FIELD8";
	public static final String FIELD9 = "FIELD9";
	public static final String FIELD10 = "FIELD10";
	public static final String FIELD11 = "FIELD11";
	public static final String FIELD12 = "FIELD12";
	public static final String FIELD13 = "FIELD13";
	public static final String FIELD14 = "FIELD14";
	public static final String FIELD15 = "FIELD15";
	public static final String FIELD16 = "FIELD16";
	public static final String FIELD17 = "FIELD17";
	public static final String FIELD18 = "FIELD18";
	public static final String FIELD19 = "FIELD19";
	public static final String FIELD20 = "FIELD20";
	
	public static final String FIELD0_VALUE = "FIELD0_VALUE";
	public static final String FIELD1_VALUE = "FIELD1_VALUE";
	public static final String FIELD2_VALUE = "FIELD2_VALUE";
	public static final String FIELD3_VALUE = "FIELD3_VALUE";
	public static final String FIELD4_VALUE = "FIELD4_VALUE";
	public static final String FIELD5_VALUE = "FIELD5_VALUE";
	public static final String FIELD6_VALUE = "FIELD6_VALUE";
	public static final String FIELD7_VALUE = "FIELD7_VALUE";
	public static final String FIELD8_VALUE = "FIELD8_VALUE";
	public static final String FIELD9_VALUE = "FIELD9_VALUE";
	public static final String FIELD10_VALUE = "FIELD10_VALUE";
	public static final String FIELD11_VALUE = "FIELD11_VALUE";
	public static final String FIELD12_VALUE = "FIELD12_VALUE";
	public static final String FIELD13_VALUE = "FIELD13_VALUE";
	public static final String FIELD14_VALUE = "FIELD14_VALUE";
	public static final String FIELD15_VALUE = "FIELD15_VALUE";
	public static final String FIELD16_VALUE = "FIELD16_VALUE";
	public static final String FIELD17_VALUE = "FIELD17_VALUE";
	public static final String FIELD18_VALUE = "FIELD18_VALUE";
	public static final String FIELD19_VALUE = "FIELD19_VALUE";
	public static final String FIELD20_VALUE = "FIELD20_VALUE";
	
	public static final String[] RECORD_FIELD_VALUES = new String[] { "A", "B", "C" };
	public static final String[] RECORD_FIELD_VALUE_DESCRIPTIONS = new String[] { "A Description", "B Description", "C Description" };
	public static final String[][] RECORD_FIELD_VALUE_RANGES = new String[][] { { "A", null }, { "B", null }, { "C", null } };
	
	public static final String TEST_TID = "TEST_TID";
	public static final String TEST_REPOSITORY = "TEST_REPOSITORY";
	public static final String ROOT_DESCRIPTION = "ROOT_DESCRIPTION";
	public static final String LEVEL1_DESCRIPTION = "LEVEL1_DESCRIPTION";
	public static final String LEVEL2_DESCRIPTION = "LEVEL2_DESCRIPTION";
	public static final String LEVEL3_DESCRIPTION = "LEVEL3_DESCRIPTION";
	public static final String LEVEL1 = "LEVEL1";
	public static final String LEVEL2 = "LEVEL2";
	public static final String LEVEL3 = "LEVEL3";
	public static final String ROOT = "ROOT";
	public static final String BAR = "|";
	public static final String TEST_APPLICATION_RELEASE = "TEST_APPLICATION_VERSION";
	public static final String TEST_SYSTEM_RELEASE = "TEST_SYSTEM_VERSION";
	public static final String TEST_IDOC_TYPE_EXTENSION = "TEST_IDOC_TYPE_EXTENSION";
	public static final String TEST_IDOC_TYPE = "TEST_IDOC_TYPE";
	public static final String TEST_URL = IdocPackage.eNS_URI + "/" + TEST_REPOSITORY + "/" + TEST_IDOC_TYPE + "/" + TEST_IDOC_TYPE_EXTENSION + "/" + TEST_SYSTEM_RELEASE + "/" + TEST_APPLICATION_RELEASE;
	
	
	public static final String ROOT_SEGMENT_KEY = TEST_IDOC_TYPE + BAR + TEST_IDOC_TYPE_EXTENSION  + BAR + TEST_SYSTEM_RELEASE + BAR + TEST_APPLICATION_RELEASE + BAR + ROOT;
	public static final String LEVEL1_SEGMENT_KEY = TEST_IDOC_TYPE + BAR + TEST_IDOC_TYPE_EXTENSION  + BAR + TEST_SYSTEM_RELEASE + BAR + TEST_APPLICATION_RELEASE + BAR + LEVEL1;
	public static final String LEVEL2_SEGMENT_KEY = TEST_IDOC_TYPE + BAR + TEST_IDOC_TYPE_EXTENSION  + BAR + TEST_SYSTEM_RELEASE + BAR + TEST_APPLICATION_RELEASE + BAR + LEVEL2;
	public static final String LEVEL3_SEGMENT_KEY = TEST_IDOC_TYPE + BAR + TEST_IDOC_TYPE_EXTENSION  + BAR + TEST_SYSTEM_RELEASE + BAR + TEST_APPLICATION_RELEASE + BAR + LEVEL3;

	public static final String TEST_FLAG_VALUE = "testFlagValue";
	public static final String STATUS_VALUE = "statusValue";
	public static final String SERIALIZATION_VALUE = "serializationValue";
	public static final String SENDER_PORT_VALUE = "senderPortValue";
	public static final String SENDER_PARTNER_TYPE_VALUE = "senderPartnerTypeValue";
	public static final String SENDER_PARTNER_NUMBER_VALUE = "senderPartnerNumberValue";
	public static final String SENDER_PARTNER_FUNCTION_VALUE = "senderPartnerFunctionValue";
	public static final String SENDER_LOGICAL_ADDRESS_VALUE = "senderLogicalAddressValue";
	public static final String SENDER_ADDRESS_VALUE = "senderAddressValue";
	public static final String RECIPIENT_PORT_VALUE = "recipientPortValue";
	public static final String RECIPIENT_PARTNER_NUMBER_VALUE = "recipientPartnerNumberValue";
	public static final String RECIPIENT_PARTNER_TYPE_VALUE = "recipientPartnerTypeValue";
	public static final String RECIPIENT_PARTNER_FUNCTION_VALUE = "recipientPartnerFunctionValue";
	public static final String RECIPIENT_LOGICAL_ADDRESS_VALUE = "recipientLogicalAddressValue";
	public static final String RECIPIENT_ADDRESS_VALUE = "recipientAddressValue";
	public static final String OUTPUT_MODE_VALUE = "outputModeValue";
	public static final String MESSAGE_TYPE_VALUE = "messageTypeValue";
	public static final String MESSAGE_FUNCTION_VALUE = "messageFunctionValue";
	public static final String MESSAGE_CODE_VALUE = "messageCodeValue";
	public static final String IDOC_TYPE_EXTENSION_VALUE = "idocTypeExtensionValue";
	public static final String IDOC_TYPE_VALUE = "idocTypeValue";
	public static final String IDOC_SAP_RELEASE_VALUE = "idocSAPReleaseValue";
	public static final String IDOC_NUMBER_VALUE = "idocNumberValue";
	public static final String IDOC_COMPOUND_TYPE_VALUE = "idocCompoundTypeValue";
	public static final String EDI_TRANSMISSION_FILE_VALUE = "ediTransmissionFileValue";
	public static final String EDI_STANDARD_VERSION_VALUE = "ediStandardVersionValue";
	public static final String EDI_STANDARD_FLAG_VALUE = "ediStandardFlagValue";
	public static final String EDI_MESSAGE_TYPE_VALUE = "editMessageTypeValue";
	public static final String EDI_MESSAGE_GROUP_VALUE = "editMessageGroupValue";
	public static final String EDI_MESSAGE_VALUE = "ediMessageValue";
	public static final String DIRECTION_VALUE = "directionValue";
	public static final String CLIENT_VALUE = "clientValue";
	public static final String ARCHIVE_KEY_VALUE = "archiveKeyValue";
	
	protected static Calendar DATE_VALUE;
	protected static Calendar TIME_VALUE;

	protected JCoDestination mockDestination;
	protected JCoIDocServer mockIDocServer;
	protected JCoServerContext mockServerContext;
	protected IDocRepository mockIDocRepository;
	protected IDocFactory mockIDocFactory;
	protected IDocHandlerFactory mockIDocHandlerFactory;
	protected IDocDocumentList mockIDocDocumentList;
	protected IDocDocumentIterator mockIDocDocumentListIterator;
	protected IDocDocument mockIDocDocument;
	protected IDocSegment mockRootSegment;
	protected IDocSegment mockLevel1Segment;
	protected IDocSegment mockLevel2Segment;
	protected IDocSegment mockLevel3Segment;
	
	protected IDocSegmentMetaData mockRootSegmentMetaData;
	protected IDocSegmentMetaData mockLevel1SegmentMetaData;
	protected IDocSegmentMetaData mockLevel2SegmentMetaData;
	protected IDocSegmentMetaData mockLevel3SegmentMetaData;
	
	protected IDocRecordMetaData mockRootRecordMetaData;
	protected IDocRecordMetaData mockLevel1RecordMetaData;
	protected IDocRecordMetaData mockLevel2RecordMetaData;
	protected IDocRecordMetaData mockLevel3RecordMetaData;

	@BeforeClass
	public static void setupIDocTestSupportClass() {
		DATE_VALUE = Calendar.getInstance();
		DATE_VALUE.set(1861, Calendar.APRIL, 12);
		TIME_VALUE = Calendar.getInstance();
		TIME_VALUE.set(Calendar.HOUR, 4);
		TIME_VALUE.set(Calendar.MINUTE, 30);
		TIME_VALUE.set(Calendar.SECOND, 15);
	}
	
	public void createMocks() throws Exception {

		/* Create mocks for destination, IDoc Respository, IDoc Factory and IDoc Document  */
		mockDestination = mock(JCoDestination.class, "TestDestination");
		mockIDocServer = mock(JCoIDocServer.class);
		mockServerContext = mock(JCoServerContext.class);
		mockIDocRepository = mock(IDocRepository.class, "IDocRepository");
		mockIDocFactory = mock(IDocFactory.class, "IDocFactory");
		mockIDocDocumentList = mock(IDocDocumentList.class);
		mockIDocDocumentListIterator = mock(IDocDocumentIterator.class);
		mockIDocHandlerFactory = mock(IDocHandlerFactory.class);
		mockIDocDocument = mock(IDocDocument.class, "IDocDocument");
		
		/* Create mocks for segments */
		mockRootSegment = mock(IDocSegment.class, "RootSegment");
		mockLevel1Segment = mock(IDocSegment.class, "Level1Segment");
		mockLevel2Segment = mock(IDocSegment.class, "Level2Segment");
		mockLevel3Segment = mock(IDocSegment.class, "Level3Segment");
		
		/* Create mocks for segment meta data */
		mockRootSegmentMetaData = mock(IDocSegmentMetaData.class);
		mockLevel1SegmentMetaData = mock(IDocSegmentMetaData.class);
		mockLevel2SegmentMetaData = mock(IDocSegmentMetaData.class);
		mockLevel3SegmentMetaData = mock(IDocSegmentMetaData.class);
		
		/* Create mocks for record meta data */
		mockRootRecordMetaData = mock(IDocRecordMetaData.class);
		mockLevel1RecordMetaData = mock(IDocRecordMetaData.class);
		mockLevel2RecordMetaData = mock(IDocRecordMetaData.class);
		mockLevel3RecordMetaData = mock(IDocRecordMetaData.class);
		
	}
	
	public void enhanceRootSegment() throws Exception {

		/* Enhance Root Segment meta data mock */
		when(mockRootSegmentMetaData.getRecordMetaData()).thenReturn(mockRootRecordMetaData);
		when(mockRootSegmentMetaData.getType()).thenReturn(ROOT);
		when(mockRootSegmentMetaData.getKey()).thenReturn(ROOT_SEGMENT_KEY);
		when(mockRootSegmentMetaData.getDefinition()).thenReturn(ROOT);
		when(mockRootSegmentMetaData.getDescription()).thenReturn(ROOT_DESCRIPTION);
		when(mockRootSegmentMetaData.getHierarchyLevel()).thenReturn(0);
		when(mockRootSegmentMetaData.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockRootSegmentMetaData.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockRootSegmentMetaData.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockRootSegmentMetaData.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockRootSegmentMetaData.getMaxOccurrence()).thenReturn(1L);
		when(mockRootSegmentMetaData.getMinOccurrence()).thenReturn(1L);
		when(mockRootSegmentMetaData.isLocked()).thenReturn(true);
		when(mockRootSegmentMetaData.isMandatory()).thenReturn(true);
		when(mockRootSegmentMetaData.isQualified()).thenReturn(false);
		when(mockRootSegmentMetaData.getChildren()).thenReturn(new IDocSegmentMetaData[] { mockLevel1SegmentMetaData });
		
		/* Enhance Root Segment record meta data mock */
		when(mockRootRecordMetaData.getName()).thenReturn(ROOT);
		when(mockRootRecordMetaData.getNumFields()).thenReturn(0);
		when(mockRootRecordMetaData.getRecordLength()).thenReturn(0);
		
		/* Enhance Root Segment mock */
		when(mockRootSegment.getSegmentMetaData()).thenReturn(mockRootSegmentMetaData);
		when(mockRootSegment.addChild(LEVEL1)).thenReturn(mockLevel1Segment);
		when(mockRootSegment.getChildren(LEVEL1)).thenReturn( new IDocSegment[] { mockLevel1Segment });

	}
	
	public void enhanceLevel1Segment() throws Exception {
		
		/* Enhance Level 1 Segment meta data mock */
		when(mockLevel1SegmentMetaData.getRecordMetaData()).thenReturn(mockLevel1RecordMetaData);
		when(mockLevel1SegmentMetaData.getType()).thenReturn(LEVEL1);
		when(mockLevel1SegmentMetaData.getKey()).thenReturn(LEVEL1_SEGMENT_KEY);
		when(mockLevel1SegmentMetaData.getDefinition()).thenReturn(LEVEL1);
		when(mockLevel1SegmentMetaData.getDescription()).thenReturn(LEVEL1_DESCRIPTION);
		when(mockLevel1SegmentMetaData.getHierarchyLevel()).thenReturn(1);
		when(mockLevel1SegmentMetaData.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockLevel1SegmentMetaData.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockLevel1SegmentMetaData.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockLevel1SegmentMetaData.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockLevel1SegmentMetaData.getMaxOccurrence()).thenReturn(9999999999L);
		when(mockLevel1SegmentMetaData.getMinOccurrence()).thenReturn(1L);
		when(mockLevel1SegmentMetaData.isLocked()).thenReturn(true);
		when(mockLevel1SegmentMetaData.isMandatory()).thenReturn(true);
		when(mockLevel1SegmentMetaData.isQualified()).thenReturn(false);
		when(mockLevel1SegmentMetaData.getChildren()).thenReturn(new IDocSegmentMetaData[] { mockLevel2SegmentMetaData });
		
		/* Enhance Level  Segment record meta data mock */
		when(mockLevel1RecordMetaData.getName()).thenReturn(LEVEL1);
		when(mockLevel1RecordMetaData.getNumFields()).thenReturn(21);
		when(mockLevel1RecordMetaData.getRecordLength()).thenReturn(210);
		
		when(mockLevel1RecordMetaData.getName(0)).thenReturn(FIELD0);
		when(mockLevel1RecordMetaData.getDataTypeName(0)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDatatype(0)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(0)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(0)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(0)).thenReturn("FIELD0_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(0)).thenReturn("FIELD0_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(0)).thenReturn("FIELD0_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(0)).thenReturn("FIELD0_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(0)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(0)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(0)).thenReturn(0);
		when(mockLevel1RecordMetaData.getOutputLength(0)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(0)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(0)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(0)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(0)).thenReturn(false);
		
		when(mockLevel1RecordMetaData.getName(1)).thenReturn(FIELD1);
		when(mockLevel1RecordMetaData.getDataTypeName(1)).thenReturn("QUAN");
		when(mockLevel1RecordMetaData.getDatatype(1)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel1RecordMetaData.getType(1)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(1)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(1)).thenReturn("FIELD1_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(1)).thenReturn("FIELD1_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(1)).thenReturn("FIELD1_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(1)).thenReturn("FIELD1_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(1)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(1)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(1)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOutputLength(1)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(1)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(1)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(1)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(1)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(2)).thenReturn(FIELD2);
		when(mockLevel1RecordMetaData.getDataTypeName(2)).thenReturn("UNIT");
		when(mockLevel1RecordMetaData.getDatatype(2)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(2)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(2)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(2)).thenReturn("FIELD2_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(2)).thenReturn("FIELD2_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(2)).thenReturn("FIELD2_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(2)).thenReturn("FIELD2_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(2)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(2)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(2)).thenReturn(20);
		when(mockLevel1RecordMetaData.getOutputLength(2)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(2)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(2)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(2)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(2)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(3)).thenReturn(FIELD3);
		when(mockLevel1RecordMetaData.getDataTypeName(3)).thenReturn("NUMC");
		when(mockLevel1RecordMetaData.getDatatype(3)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel1RecordMetaData.getType(3)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(3)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(3)).thenReturn("FIELD3_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(3)).thenReturn("FIELD3_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(3)).thenReturn("FIELD3_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(3)).thenReturn("FIELD3_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(3)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(3)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(3)).thenReturn(30);
		when(mockLevel1RecordMetaData.getOutputLength(3)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(3)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(3)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(3)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(3)).thenReturn(false);
		
		when(mockLevel1RecordMetaData.getName(4)).thenReturn(FIELD4);
		when(mockLevel1RecordMetaData.getDataTypeName(4)).thenReturn("DATS");
		when(mockLevel1RecordMetaData.getDatatype(4)).thenReturn(IDocDatatype.DATE);
		when(mockLevel1RecordMetaData.getType(4)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(4)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(4)).thenReturn("FIELD4_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(4)).thenReturn("FIELD4_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(4)).thenReturn("FIELD4_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(4)).thenReturn("FIELD4_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(4)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(4)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(4)).thenReturn(40);
		when(mockLevel1RecordMetaData.getOutputLength(4)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(4)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(4)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(4)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(4)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(5)).thenReturn(FIELD5);
		when(mockLevel1RecordMetaData.getDataTypeName(5)).thenReturn("TIMS");
		when(mockLevel1RecordMetaData.getDatatype(5)).thenReturn(IDocDatatype.TIME);
		when(mockLevel1RecordMetaData.getType(5)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(5)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(5)).thenReturn("FIELD5_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(5)).thenReturn("FIELD5_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(5)).thenReturn("FIELD5_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(5)).thenReturn("FIELD5_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(5)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(5)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(5)).thenReturn(50);
		when(mockLevel1RecordMetaData.getOutputLength(5)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(5)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(5)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(5)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(5)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(6)).thenReturn(FIELD6);
		when(mockLevel1RecordMetaData.getDataTypeName(6)).thenReturn("CURR");
		when(mockLevel1RecordMetaData.getDatatype(6)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel1RecordMetaData.getType(6)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(6)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(6)).thenReturn("FIELD6_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(6)).thenReturn("FIELD6_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(6)).thenReturn("FIELD6_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(6)).thenReturn("FIELD6_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(6)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(6)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(6)).thenReturn(60);
		when(mockLevel1RecordMetaData.getOutputLength(6)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(6)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(6)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(6)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(6)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(7)).thenReturn(FIELD7);
		when(mockLevel1RecordMetaData.getDataTypeName(7)).thenReturn("CUKY");
		when(mockLevel1RecordMetaData.getDatatype(7)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(7)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(7)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(7)).thenReturn("FIELD7_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(7)).thenReturn("FIELD7_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(7)).thenReturn("FIELD7_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(7)).thenReturn("FIELD7_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(7)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(7)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(7)).thenReturn(70);
		when(mockLevel1RecordMetaData.getOutputLength(7)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(7)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(7)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(7)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(7)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(8)).thenReturn(FIELD8);
		when(mockLevel1RecordMetaData.getDataTypeName(8)).thenReturn("LANG");
		when(mockLevel1RecordMetaData.getDatatype(8)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(8)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(8)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(8)).thenReturn("FIELD8_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(8)).thenReturn("FIELD8_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(8)).thenReturn("FIELD8_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(8)).thenReturn("FIELD8_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(8)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(8)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(8)).thenReturn(80);
		when(mockLevel1RecordMetaData.getOutputLength(8)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(8)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(8)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(8)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(8)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(9)).thenReturn(FIELD9);
		when(mockLevel1RecordMetaData.getDataTypeName(9)).thenReturn("CLNT");
		when(mockLevel1RecordMetaData.getDatatype(9)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(9)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(9)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(9)).thenReturn("FIELD9_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(9)).thenReturn("FIELD9_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(9)).thenReturn("FIELD9_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(9)).thenReturn("FIELD9_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(9)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(9)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(9)).thenReturn(90);
		when(mockLevel1RecordMetaData.getOutputLength(9)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(9)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(9)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(9)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(9)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(10)).thenReturn(FIELD10);
		when(mockLevel1RecordMetaData.getDataTypeName(10)).thenReturn("INT1");
		when(mockLevel1RecordMetaData.getDatatype(10)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel1RecordMetaData.getType(10)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(10)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(10)).thenReturn("FIELD10_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(10)).thenReturn("FIELD10_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(10)).thenReturn("FIELD10_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(10)).thenReturn("FIELD10_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(10)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(10)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(10)).thenReturn(100);
		when(mockLevel1RecordMetaData.getOutputLength(10)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(10)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(10)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(10)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(10)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(11)).thenReturn(FIELD11);
		when(mockLevel1RecordMetaData.getDataTypeName(11)).thenReturn("INT2");
		when(mockLevel1RecordMetaData.getDatatype(11)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel1RecordMetaData.getType(11)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(11)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(11)).thenReturn("FIELD11_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(11)).thenReturn("FIELD11_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(11)).thenReturn("FIELD11_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(11)).thenReturn("FIELD11_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(11)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(11)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(11)).thenReturn(110);
		when(mockLevel1RecordMetaData.getOutputLength(11)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(11)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(11)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(11)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(11)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(12)).thenReturn(FIELD12);
		when(mockLevel1RecordMetaData.getDataTypeName(12)).thenReturn("INT4");
		when(mockLevel1RecordMetaData.getDatatype(12)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel1RecordMetaData.getType(12)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(12)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(12)).thenReturn("FIELD12_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(12)).thenReturn("FIELD12_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(12)).thenReturn("FIELD12_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(12)).thenReturn("FIELD12_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(12)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(12)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(12)).thenReturn(120);
		when(mockLevel1RecordMetaData.getOutputLength(12)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(12)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(12)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(12)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(12)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(13)).thenReturn(FIELD13);
		when(mockLevel1RecordMetaData.getDataTypeName(13)).thenReturn("FLTP");
		when(mockLevel1RecordMetaData.getDatatype(13)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel1RecordMetaData.getType(13)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(13)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(13)).thenReturn("FIELD13_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(13)).thenReturn("FIELD13_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(13)).thenReturn("FIELD13_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(13)).thenReturn("FIELD13_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(13)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(13)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(13)).thenReturn(130);
		when(mockLevel1RecordMetaData.getOutputLength(13)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(13)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(13)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(13)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(13)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(14)).thenReturn(FIELD14);
		when(mockLevel1RecordMetaData.getDataTypeName(14)).thenReturn("ACCP");
		when(mockLevel1RecordMetaData.getDatatype(14)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel1RecordMetaData.getType(14)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(14)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(14)).thenReturn("FIELD14_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(14)).thenReturn("FIELD14_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(14)).thenReturn("FIELD14_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(14)).thenReturn("FIELD14_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(14)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(14)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(14)).thenReturn(140);
		when(mockLevel1RecordMetaData.getOutputLength(14)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(14)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(14)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(14)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(14)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(15)).thenReturn(FIELD15);
		when(mockLevel1RecordMetaData.getDataTypeName(15)).thenReturn("PREC");
		when(mockLevel1RecordMetaData.getDatatype(15)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel1RecordMetaData.getType(15)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(15)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(15)).thenReturn("FIELD15_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(15)).thenReturn("FIELD15_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(15)).thenReturn("FIELD15_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(15)).thenReturn("FIELD15_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(15)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(15)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(15)).thenReturn(150);
		when(mockLevel1RecordMetaData.getOutputLength(15)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(15)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(15)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(15)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(15)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(16)).thenReturn(FIELD16);
		when(mockLevel1RecordMetaData.getDataTypeName(16)).thenReturn("LRAW");
		when(mockLevel1RecordMetaData.getDatatype(16)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel1RecordMetaData.getType(16)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(16)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(16)).thenReturn("FIELD16_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(16)).thenReturn("FIELD16_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(16)).thenReturn("FIELD16_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(16)).thenReturn("FIELD16_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(16)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(16)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(16)).thenReturn(160);
		when(mockLevel1RecordMetaData.getOutputLength(16)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(16)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(16)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(16)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(16)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(17)).thenReturn(FIELD17);
		when(mockLevel1RecordMetaData.getDataTypeName(17)).thenReturn("DEC");
		when(mockLevel1RecordMetaData.getDatatype(17)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel1RecordMetaData.getType(17)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(17)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(17)).thenReturn("FIELD17_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(17)).thenReturn("FIELD17_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(17)).thenReturn("FIELD17_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(17)).thenReturn("FIELD17_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(17)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(17)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(17)).thenReturn(170);
		when(mockLevel1RecordMetaData.getOutputLength(17)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(17)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(17)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(17)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(17)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(18)).thenReturn(FIELD18);
		when(mockLevel1RecordMetaData.getDataTypeName(18)).thenReturn("RAW");
		when(mockLevel1RecordMetaData.getDatatype(18)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel1RecordMetaData.getType(18)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(18)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(18)).thenReturn("FIELD18_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(18)).thenReturn("FIELD18_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(18)).thenReturn("FIELD18_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(18)).thenReturn("FIELD18_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(18)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(18)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(18)).thenReturn(180);
		when(mockLevel1RecordMetaData.getOutputLength(18)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(18)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(18)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(18)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(18)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(19)).thenReturn(FIELD19);
		when(mockLevel1RecordMetaData.getDataTypeName(19)).thenReturn("STRING");
		when(mockLevel1RecordMetaData.getDatatype(19)).thenReturn(IDocDatatype.STRING);
		when(mockLevel1RecordMetaData.getType(19)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(19)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(19)).thenReturn("FIELD19_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(19)).thenReturn("FIELD19_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(19)).thenReturn("FIELD19_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(19)).thenReturn("FIELD19_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(19)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(19)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(19)).thenReturn(190);
		when(mockLevel1RecordMetaData.getOutputLength(19)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(19)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(19)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(19)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(19)).thenReturn(false);

		when(mockLevel1RecordMetaData.getName(20)).thenReturn(FIELD20);
		when(mockLevel1RecordMetaData.getDataTypeName(20)).thenReturn("RAWSTRING");
		when(mockLevel1RecordMetaData.getDatatype(20)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel1RecordMetaData.getType(20)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel1RecordMetaData.getTypeAsString(20)).thenReturn("CHAR");
		when(mockLevel1RecordMetaData.getDataElementName(20)).thenReturn("FIELD20_DATA_ELEMENT");
		when(mockLevel1RecordMetaData.getDomainName(20)).thenReturn("FIELD20_DOMAIN_NAME");
		when(mockLevel1RecordMetaData.getDescription(20)).thenReturn("FIELD20_DATA_ELEMENT Description");
		when(mockLevel1RecordMetaData.getCheckTableName(20)).thenReturn("FIELD20_CHECK_TABLE_NAME");
		when(mockLevel1RecordMetaData.getInternalLength(20)).thenReturn(10);
		when(mockLevel1RecordMetaData.getLength(20)).thenReturn(10);
		when(mockLevel1RecordMetaData.getOffset(20)).thenReturn(200);
		when(mockLevel1RecordMetaData.getOutputLength(20)).thenReturn(10);
		when(mockLevel1RecordMetaData.getValues(20)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel1RecordMetaData.getValueDescriptions(20)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel1RecordMetaData.getValueRanges(20)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel1RecordMetaData.isISOCode(20)).thenReturn(false);

		when(mockLevel1Segment.addChild(LEVEL2)).thenReturn(mockLevel2Segment);
		when(mockLevel1Segment.getChildren(LEVEL2)).thenReturn(new IDocSegment[] { mockLevel2Segment });
		when(mockLevel1Segment.getValue(FIELD0)).thenReturn(FIELD0_VALUE);
		when(mockLevel1Segment.getValue(FIELD1)).thenReturn(FIELD1_VALUE);
		when(mockLevel1Segment.getValue(FIELD2)).thenReturn(FIELD2_VALUE);
		when(mockLevel1Segment.getValue(FIELD3)).thenReturn(FIELD3_VALUE);
		when(mockLevel1Segment.getValue(FIELD4)).thenReturn(FIELD4_VALUE);
		when(mockLevel1Segment.getValue(FIELD5)).thenReturn(FIELD5_VALUE);
		when(mockLevel1Segment.getValue(FIELD6)).thenReturn(FIELD6_VALUE);
		when(mockLevel1Segment.getValue(FIELD7)).thenReturn(FIELD7_VALUE);
		when(mockLevel1Segment.getValue(FIELD8)).thenReturn(FIELD8_VALUE);
		when(mockLevel1Segment.getValue(FIELD9)).thenReturn(FIELD9_VALUE);
		when(mockLevel1Segment.getValue(FIELD10)).thenReturn(FIELD10_VALUE);
		when(mockLevel1Segment.getValue(FIELD11)).thenReturn(FIELD11_VALUE);
		when(mockLevel1Segment.getValue(FIELD12)).thenReturn(FIELD12_VALUE);
		when(mockLevel1Segment.getValue(FIELD13)).thenReturn(FIELD13_VALUE);
		when(mockLevel1Segment.getValue(FIELD14)).thenReturn(FIELD14_VALUE);
		when(mockLevel1Segment.getValue(FIELD15)).thenReturn(FIELD15_VALUE);
		when(mockLevel1Segment.getValue(FIELD16)).thenReturn(FIELD16_VALUE);
		when(mockLevel1Segment.getValue(FIELD17)).thenReturn(FIELD17_VALUE);
		when(mockLevel1Segment.getValue(FIELD18)).thenReturn(FIELD18_VALUE);
		when(mockLevel1Segment.getValue(FIELD19)).thenReturn(FIELD19_VALUE);
		when(mockLevel1Segment.getValue(FIELD20)).thenReturn(FIELD20_VALUE);

	}
	
	public void enhanceLevel2Segment() throws Exception {
		
		/* Enhance Level 2 Segment meta data mock */
		when(mockLevel2SegmentMetaData.getRecordMetaData()).thenReturn(mockLevel2RecordMetaData);
		when(mockLevel2SegmentMetaData.getType()).thenReturn(LEVEL2);
		when(mockLevel2SegmentMetaData.getKey()).thenReturn(LEVEL2_SEGMENT_KEY);
		when(mockLevel2SegmentMetaData.getDefinition()).thenReturn(LEVEL2);
		when(mockLevel2SegmentMetaData.getDescription()).thenReturn(LEVEL2_DESCRIPTION);
		when(mockLevel2SegmentMetaData.getHierarchyLevel()).thenReturn(2);
		when(mockLevel2SegmentMetaData.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockLevel2SegmentMetaData.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockLevel2SegmentMetaData.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockLevel2SegmentMetaData.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockLevel2SegmentMetaData.getMaxOccurrence()).thenReturn(9999999999L);
		when(mockLevel2SegmentMetaData.getMinOccurrence()).thenReturn(1L);
		when(mockLevel2SegmentMetaData.isLocked()).thenReturn(true);
		when(mockLevel2SegmentMetaData.isMandatory()).thenReturn(true);
		when(mockLevel2SegmentMetaData.isQualified()).thenReturn(false);
		when(mockLevel2SegmentMetaData.getChildren()).thenReturn(new IDocSegmentMetaData[] { mockLevel3SegmentMetaData });
		
		/* Enhance Level 2 Segment record meta data mock */
		when(mockLevel2RecordMetaData.getName()).thenReturn(LEVEL2);
		when(mockLevel2RecordMetaData.getNumFields()).thenReturn(21);
		when(mockLevel2RecordMetaData.getRecordLength()).thenReturn(210);
		
		when(mockLevel2RecordMetaData.getName(0)).thenReturn(FIELD0);
		when(mockLevel2RecordMetaData.getDataTypeName(0)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDatatype(0)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(0)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(0)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(0)).thenReturn("FIELD0_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(0)).thenReturn("FIELD0_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(0)).thenReturn("FIELD0_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(0)).thenReturn("FIELD0_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(0)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(0)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(0)).thenReturn(0);
		when(mockLevel2RecordMetaData.getOutputLength(0)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(0)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(0)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(0)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(0)).thenReturn(false);
		
		when(mockLevel2RecordMetaData.getName(1)).thenReturn(FIELD1);
		when(mockLevel2RecordMetaData.getDataTypeName(1)).thenReturn("QUAN");
		when(mockLevel2RecordMetaData.getDatatype(1)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel2RecordMetaData.getType(1)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(1)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(1)).thenReturn("FIELD1_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(1)).thenReturn("FIELD1_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(1)).thenReturn("FIELD1_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(1)).thenReturn("FIELD1_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(1)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(1)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(1)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOutputLength(1)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(1)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(1)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(1)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(1)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(2)).thenReturn(FIELD2);
		when(mockLevel2RecordMetaData.getDataTypeName(2)).thenReturn("UNIT");
		when(mockLevel2RecordMetaData.getDatatype(2)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(2)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(2)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(2)).thenReturn("FIELD2_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(2)).thenReturn("FIELD2_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(2)).thenReturn("FIELD2_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(2)).thenReturn("FIELD2_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(2)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(2)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(2)).thenReturn(20);
		when(mockLevel2RecordMetaData.getOutputLength(2)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(2)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(2)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(2)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(2)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(3)).thenReturn(FIELD3);
		when(mockLevel2RecordMetaData.getDataTypeName(3)).thenReturn("NUMC");
		when(mockLevel2RecordMetaData.getDatatype(3)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel2RecordMetaData.getType(3)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(3)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(3)).thenReturn("FIELD3_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(3)).thenReturn("FIELD3_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(3)).thenReturn("FIELD3_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(3)).thenReturn("FIELD3_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(3)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(3)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(3)).thenReturn(30);
		when(mockLevel2RecordMetaData.getOutputLength(3)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(3)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(3)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(3)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(3)).thenReturn(false);
		
		when(mockLevel2RecordMetaData.getName(4)).thenReturn(FIELD4);
		when(mockLevel2RecordMetaData.getDataTypeName(4)).thenReturn("DATS");
		when(mockLevel2RecordMetaData.getDatatype(4)).thenReturn(IDocDatatype.DATE);
		when(mockLevel2RecordMetaData.getType(4)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(4)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(4)).thenReturn("FIELD4_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(4)).thenReturn("FIELD4_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(4)).thenReturn("FIELD4_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(4)).thenReturn("FIELD4_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(4)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(4)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(4)).thenReturn(40);
		when(mockLevel2RecordMetaData.getOutputLength(4)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(4)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(4)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(4)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(4)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(5)).thenReturn(FIELD5);
		when(mockLevel2RecordMetaData.getDataTypeName(5)).thenReturn("TIMS");
		when(mockLevel2RecordMetaData.getDatatype(5)).thenReturn(IDocDatatype.TIME);
		when(mockLevel2RecordMetaData.getType(5)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(5)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(5)).thenReturn("FIELD5_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(5)).thenReturn("FIELD5_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(5)).thenReturn("FIELD5_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(5)).thenReturn("FIELD5_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(5)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(5)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(5)).thenReturn(50);
		when(mockLevel2RecordMetaData.getOutputLength(5)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(5)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(5)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(5)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(5)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(6)).thenReturn(FIELD6);
		when(mockLevel2RecordMetaData.getDataTypeName(6)).thenReturn("CURR");
		when(mockLevel2RecordMetaData.getDatatype(6)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel2RecordMetaData.getType(6)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(6)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(6)).thenReturn("FIELD6_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(6)).thenReturn("FIELD6_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(6)).thenReturn("FIELD6_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(6)).thenReturn("FIELD6_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(6)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(6)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(6)).thenReturn(60);
		when(mockLevel2RecordMetaData.getOutputLength(6)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(6)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(6)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(6)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(6)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(7)).thenReturn(FIELD7);
		when(mockLevel2RecordMetaData.getDataTypeName(7)).thenReturn("CUKY");
		when(mockLevel2RecordMetaData.getDatatype(7)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(7)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(7)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(7)).thenReturn("FIELD7_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(7)).thenReturn("FIELD7_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(7)).thenReturn("FIELD7_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(7)).thenReturn("FIELD7_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(7)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(7)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(7)).thenReturn(70);
		when(mockLevel2RecordMetaData.getOutputLength(7)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(7)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(7)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(7)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(7)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(8)).thenReturn(FIELD8);
		when(mockLevel2RecordMetaData.getDataTypeName(8)).thenReturn("LANG");
		when(mockLevel2RecordMetaData.getDatatype(8)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(8)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(8)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(8)).thenReturn("FIELD8_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(8)).thenReturn("FIELD8_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(8)).thenReturn("FIELD8_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(8)).thenReturn("FIELD8_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(8)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(8)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(8)).thenReturn(80);
		when(mockLevel2RecordMetaData.getOutputLength(8)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(8)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(8)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(8)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(8)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(9)).thenReturn(FIELD9);
		when(mockLevel2RecordMetaData.getDataTypeName(9)).thenReturn("CLNT");
		when(mockLevel2RecordMetaData.getDatatype(9)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(9)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(9)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(9)).thenReturn("FIELD9_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(9)).thenReturn("FIELD9_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(9)).thenReturn("FIELD9_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(9)).thenReturn("FIELD9_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(9)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(9)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(9)).thenReturn(90);
		when(mockLevel2RecordMetaData.getOutputLength(9)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(9)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(9)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(9)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(9)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(10)).thenReturn(FIELD10);
		when(mockLevel2RecordMetaData.getDataTypeName(10)).thenReturn("INT1");
		when(mockLevel2RecordMetaData.getDatatype(10)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel2RecordMetaData.getType(10)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(10)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(10)).thenReturn("FIELD10_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(10)).thenReturn("FIELD10_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(10)).thenReturn("FIELD10_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(10)).thenReturn("FIELD10_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(10)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(10)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(10)).thenReturn(100);
		when(mockLevel2RecordMetaData.getOutputLength(10)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(10)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(10)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(10)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(10)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(11)).thenReturn(FIELD11);
		when(mockLevel2RecordMetaData.getDataTypeName(11)).thenReturn("INT2");
		when(mockLevel2RecordMetaData.getDatatype(11)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel2RecordMetaData.getType(11)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(11)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(11)).thenReturn("FIELD11_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(11)).thenReturn("FIELD11_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(11)).thenReturn("FIELD11_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(11)).thenReturn("FIELD11_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(11)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(11)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(11)).thenReturn(110);
		when(mockLevel2RecordMetaData.getOutputLength(11)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(11)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(11)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(11)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(11)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(12)).thenReturn(FIELD12);
		when(mockLevel2RecordMetaData.getDataTypeName(12)).thenReturn("INT4");
		when(mockLevel2RecordMetaData.getDatatype(12)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel2RecordMetaData.getType(12)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(12)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(12)).thenReturn("FIELD12_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(12)).thenReturn("FIELD12_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(12)).thenReturn("FIELD12_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(12)).thenReturn("FIELD12_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(12)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(12)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(12)).thenReturn(120);
		when(mockLevel2RecordMetaData.getOutputLength(12)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(12)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(12)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(12)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(12)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(13)).thenReturn(FIELD13);
		when(mockLevel2RecordMetaData.getDataTypeName(13)).thenReturn("FLTP");
		when(mockLevel2RecordMetaData.getDatatype(13)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel2RecordMetaData.getType(13)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(13)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(13)).thenReturn("FIELD13_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(13)).thenReturn("FIELD13_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(13)).thenReturn("FIELD13_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(13)).thenReturn("FIELD13_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(13)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(13)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(13)).thenReturn(130);
		when(mockLevel2RecordMetaData.getOutputLength(13)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(13)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(13)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(13)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(13)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(14)).thenReturn(FIELD14);
		when(mockLevel2RecordMetaData.getDataTypeName(14)).thenReturn("ACCP");
		when(mockLevel2RecordMetaData.getDatatype(14)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel2RecordMetaData.getType(14)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(14)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(14)).thenReturn("FIELD14_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(14)).thenReturn("FIELD14_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(14)).thenReturn("FIELD14_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(14)).thenReturn("FIELD14_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(14)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(14)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(14)).thenReturn(140);
		when(mockLevel2RecordMetaData.getOutputLength(14)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(14)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(14)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(14)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(14)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(15)).thenReturn(FIELD15);
		when(mockLevel2RecordMetaData.getDataTypeName(15)).thenReturn("PREC");
		when(mockLevel2RecordMetaData.getDatatype(15)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel2RecordMetaData.getType(15)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(15)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(15)).thenReturn("FIELD15_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(15)).thenReturn("FIELD15_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(15)).thenReturn("FIELD15_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(15)).thenReturn("FIELD15_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(15)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(15)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(15)).thenReturn(150);
		when(mockLevel2RecordMetaData.getOutputLength(15)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(15)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(15)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(15)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(15)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(16)).thenReturn(FIELD16);
		when(mockLevel2RecordMetaData.getDataTypeName(16)).thenReturn("LRAW");
		when(mockLevel2RecordMetaData.getDatatype(16)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel2RecordMetaData.getType(16)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(16)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(16)).thenReturn("FIELD16_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(16)).thenReturn("FIELD16_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(16)).thenReturn("FIELD16_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(16)).thenReturn("FIELD16_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(16)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(16)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(16)).thenReturn(160);
		when(mockLevel2RecordMetaData.getOutputLength(16)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(16)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(16)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(16)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(16)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(17)).thenReturn(FIELD17);
		when(mockLevel2RecordMetaData.getDataTypeName(17)).thenReturn("DEC");
		when(mockLevel2RecordMetaData.getDatatype(17)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel2RecordMetaData.getType(17)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(17)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(17)).thenReturn("FIELD17_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(17)).thenReturn("FIELD17_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(17)).thenReturn("FIELD17_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(17)).thenReturn("FIELD17_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(17)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(17)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(17)).thenReturn(170);
		when(mockLevel2RecordMetaData.getOutputLength(17)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(17)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(17)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(17)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(17)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(18)).thenReturn(FIELD18);
		when(mockLevel2RecordMetaData.getDataTypeName(18)).thenReturn("RAW");
		when(mockLevel2RecordMetaData.getDatatype(18)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel2RecordMetaData.getType(18)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(18)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(18)).thenReturn("FIELD18_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(18)).thenReturn("FIELD18_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(18)).thenReturn("FIELD18_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(18)).thenReturn("FIELD18_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(18)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(18)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(18)).thenReturn(180);
		when(mockLevel2RecordMetaData.getOutputLength(18)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(18)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(18)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(18)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(18)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(19)).thenReturn(FIELD19);
		when(mockLevel2RecordMetaData.getDataTypeName(19)).thenReturn("STRING");
		when(mockLevel2RecordMetaData.getDatatype(19)).thenReturn(IDocDatatype.STRING);
		when(mockLevel2RecordMetaData.getType(19)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(19)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(19)).thenReturn("FIELD19_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(19)).thenReturn("FIELD19_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(19)).thenReturn("FIELD19_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(19)).thenReturn("FIELD19_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(19)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(19)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(19)).thenReturn(190);
		when(mockLevel2RecordMetaData.getOutputLength(19)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(19)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(19)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(19)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(19)).thenReturn(false);

		when(mockLevel2RecordMetaData.getName(20)).thenReturn(FIELD20);
		when(mockLevel2RecordMetaData.getDataTypeName(20)).thenReturn("RAWSTRING");
		when(mockLevel2RecordMetaData.getDatatype(20)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel2RecordMetaData.getType(20)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel2RecordMetaData.getTypeAsString(20)).thenReturn("CHAR");
		when(mockLevel2RecordMetaData.getDataElementName(20)).thenReturn("FIELD20_DATA_ELEMENT");
		when(mockLevel2RecordMetaData.getDomainName(20)).thenReturn("FIELD20_DOMAIN_NAME");
		when(mockLevel2RecordMetaData.getDescription(20)).thenReturn("FIELD20_DATA_ELEMENT Description");
		when(mockLevel2RecordMetaData.getCheckTableName(20)).thenReturn("FIELD20_CHECK_TABLE_NAME");
		when(mockLevel2RecordMetaData.getInternalLength(20)).thenReturn(10);
		when(mockLevel2RecordMetaData.getLength(20)).thenReturn(10);
		when(mockLevel2RecordMetaData.getOffset(20)).thenReturn(200);
		when(mockLevel2RecordMetaData.getOutputLength(20)).thenReturn(10);
		when(mockLevel2RecordMetaData.getValues(20)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel2RecordMetaData.getValueDescriptions(20)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel2RecordMetaData.getValueRanges(20)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel2RecordMetaData.isISOCode(20)).thenReturn(false);

		/* Enhance Level 3 Segment meta data mock */
		when(mockLevel3SegmentMetaData.getRecordMetaData()).thenReturn(mockLevel3RecordMetaData);
		when(mockLevel3SegmentMetaData.getType()).thenReturn(LEVEL3);
		when(mockLevel3SegmentMetaData.getKey()).thenReturn(LEVEL3_SEGMENT_KEY);
		when(mockLevel3SegmentMetaData.getDefinition()).thenReturn(LEVEL3);
		when(mockLevel3SegmentMetaData.getDescription()).thenReturn(LEVEL3_DESCRIPTION);
		when(mockLevel3SegmentMetaData.getHierarchyLevel()).thenReturn(2);
		when(mockLevel3SegmentMetaData.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockLevel3SegmentMetaData.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockLevel3SegmentMetaData.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockLevel3SegmentMetaData.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockLevel3SegmentMetaData.getMaxOccurrence()).thenReturn(9999999999L);
		when(mockLevel3SegmentMetaData.getMinOccurrence()).thenReturn(1L);
		when(mockLevel3SegmentMetaData.isLocked()).thenReturn(true);
		when(mockLevel3SegmentMetaData.isMandatory()).thenReturn(true);
		when(mockLevel3SegmentMetaData.isQualified()).thenReturn(false);
		when(mockLevel3SegmentMetaData.getChildren()).thenReturn(new IDocSegmentMetaData[0]);
		
		when(mockLevel2Segment.addChild(LEVEL3)).thenReturn(mockLevel3Segment);
		when(mockLevel2Segment.getChildren(LEVEL3)).thenReturn(new IDocSegment[] { mockLevel3Segment });
		when(mockLevel2Segment.getValue(FIELD0)).thenReturn(FIELD0_VALUE);
		when(mockLevel2Segment.getValue(FIELD1)).thenReturn(FIELD1_VALUE);
		when(mockLevel2Segment.getValue(FIELD2)).thenReturn(FIELD2_VALUE);
		when(mockLevel2Segment.getValue(FIELD3)).thenReturn(FIELD3_VALUE);
		when(mockLevel2Segment.getValue(FIELD4)).thenReturn(FIELD4_VALUE);
		when(mockLevel2Segment.getValue(FIELD5)).thenReturn(FIELD5_VALUE);
		when(mockLevel2Segment.getValue(FIELD6)).thenReturn(FIELD6_VALUE);
		when(mockLevel2Segment.getValue(FIELD7)).thenReturn(FIELD7_VALUE);
		when(mockLevel2Segment.getValue(FIELD8)).thenReturn(FIELD8_VALUE);
		when(mockLevel2Segment.getValue(FIELD9)).thenReturn(FIELD9_VALUE);
		when(mockLevel2Segment.getValue(FIELD10)).thenReturn(FIELD10_VALUE);
		when(mockLevel2Segment.getValue(FIELD11)).thenReturn(FIELD11_VALUE);
		when(mockLevel2Segment.getValue(FIELD12)).thenReturn(FIELD12_VALUE);
		when(mockLevel2Segment.getValue(FIELD13)).thenReturn(FIELD13_VALUE);
		when(mockLevel2Segment.getValue(FIELD14)).thenReturn(FIELD14_VALUE);
		when(mockLevel2Segment.getValue(FIELD15)).thenReturn(FIELD15_VALUE);
		when(mockLevel2Segment.getValue(FIELD16)).thenReturn(FIELD16_VALUE);
		when(mockLevel2Segment.getValue(FIELD17)).thenReturn(FIELD17_VALUE);
		when(mockLevel2Segment.getValue(FIELD18)).thenReturn(FIELD18_VALUE);
		when(mockLevel2Segment.getValue(FIELD19)).thenReturn(FIELD19_VALUE);
		when(mockLevel2Segment.getValue(FIELD20)).thenReturn(FIELD20_VALUE);
		
	}

	public void enhanceLevel3Segment() throws Exception {

		/* Enhance Level 3 Segment meta data mock */
		when(mockLevel3SegmentMetaData.getRecordMetaData()).thenReturn(mockLevel2RecordMetaData);
		when(mockLevel3SegmentMetaData.getType()).thenReturn(LEVEL3);
		when(mockLevel3SegmentMetaData.getKey()).thenReturn(LEVEL2_SEGMENT_KEY);
		when(mockLevel3SegmentMetaData.getDefinition()).thenReturn(LEVEL3);
		when(mockLevel3SegmentMetaData.getDescription()).thenReturn(LEVEL3_DESCRIPTION);
		when(mockLevel3SegmentMetaData.getHierarchyLevel()).thenReturn(3);
		when(mockLevel3SegmentMetaData.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockLevel3SegmentMetaData.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockLevel3SegmentMetaData.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockLevel3SegmentMetaData.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockLevel3SegmentMetaData.getMaxOccurrence()).thenReturn(9999999999L);
		when(mockLevel3SegmentMetaData.getMinOccurrence()).thenReturn(1L);
		when(mockLevel3SegmentMetaData.isLocked()).thenReturn(true);
		when(mockLevel3SegmentMetaData.isMandatory()).thenReturn(true);
		when(mockLevel3SegmentMetaData.isQualified()).thenReturn(false);
		when(mockLevel3SegmentMetaData.getChildren()).thenReturn(new IDocSegmentMetaData[] { });
		
		/* Enhance Level 3 Segment record meta data mock */
		when(mockLevel3RecordMetaData.getName()).thenReturn(LEVEL3);
		when(mockLevel3RecordMetaData.getNumFields()).thenReturn(21);
		when(mockLevel3RecordMetaData.getRecordLength()).thenReturn(210);
		
		when(mockLevel3RecordMetaData.getName(0)).thenReturn(FIELD0);
		when(mockLevel3RecordMetaData.getDataTypeName(0)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDatatype(0)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(0)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(0)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(0)).thenReturn("FIELD0_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(0)).thenReturn("FIELD0_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(0)).thenReturn("FIELD0_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(0)).thenReturn("FIELD0_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(0)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(0)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(0)).thenReturn(0);
		when(mockLevel3RecordMetaData.getOutputLength(0)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(0)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(0)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(0)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(0)).thenReturn(false);
		
		when(mockLevel3RecordMetaData.getName(1)).thenReturn(FIELD1);
		when(mockLevel3RecordMetaData.getDataTypeName(1)).thenReturn("QUAN");
		when(mockLevel3RecordMetaData.getDatatype(1)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel3RecordMetaData.getType(1)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(1)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(1)).thenReturn("FIELD1_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(1)).thenReturn("FIELD1_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(1)).thenReturn("FIELD1_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(1)).thenReturn("FIELD1_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(1)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(1)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(1)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOutputLength(1)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(1)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(1)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(1)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(1)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(2)).thenReturn(FIELD2);
		when(mockLevel3RecordMetaData.getDataTypeName(2)).thenReturn("UNIT");
		when(mockLevel3RecordMetaData.getDatatype(2)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(2)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(2)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(2)).thenReturn("FIELD2_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(2)).thenReturn("FIELD2_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(2)).thenReturn("FIELD2_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(2)).thenReturn("FIELD2_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(2)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(2)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(2)).thenReturn(20);
		when(mockLevel3RecordMetaData.getOutputLength(2)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(2)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(2)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(2)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(2)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(3)).thenReturn(FIELD3);
		when(mockLevel3RecordMetaData.getDataTypeName(3)).thenReturn("NUMC");
		when(mockLevel3RecordMetaData.getDatatype(3)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel3RecordMetaData.getType(3)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(3)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(3)).thenReturn("FIELD3_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(3)).thenReturn("FIELD3_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(3)).thenReturn("FIELD3_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(3)).thenReturn("FIELD3_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(3)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(3)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(3)).thenReturn(30);
		when(mockLevel3RecordMetaData.getOutputLength(3)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(3)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(3)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(3)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(3)).thenReturn(false);
		
		when(mockLevel3RecordMetaData.getName(4)).thenReturn(FIELD4);
		when(mockLevel3RecordMetaData.getDataTypeName(4)).thenReturn("DATS");
		when(mockLevel3RecordMetaData.getDatatype(4)).thenReturn(IDocDatatype.DATE);
		when(mockLevel3RecordMetaData.getType(4)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(4)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(4)).thenReturn("FIELD4_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(4)).thenReturn("FIELD4_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(4)).thenReturn("FIELD4_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(4)).thenReturn("FIELD4_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(4)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(4)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(4)).thenReturn(40);
		when(mockLevel3RecordMetaData.getOutputLength(4)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(4)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(4)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(4)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(4)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(5)).thenReturn(FIELD5);
		when(mockLevel3RecordMetaData.getDataTypeName(5)).thenReturn("TIMS");
		when(mockLevel3RecordMetaData.getDatatype(5)).thenReturn(IDocDatatype.TIME);
		when(mockLevel3RecordMetaData.getType(5)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(5)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(5)).thenReturn("FIELD5_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(5)).thenReturn("FIELD5_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(5)).thenReturn("FIELD5_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(5)).thenReturn("FIELD5_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(5)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(5)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(5)).thenReturn(50);
		when(mockLevel3RecordMetaData.getOutputLength(5)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(5)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(5)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(5)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(5)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(6)).thenReturn(FIELD6);
		when(mockLevel3RecordMetaData.getDataTypeName(6)).thenReturn("CURR");
		when(mockLevel3RecordMetaData.getDatatype(6)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel3RecordMetaData.getType(6)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(6)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(6)).thenReturn("FIELD6_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(6)).thenReturn("FIELD6_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(6)).thenReturn("FIELD6_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(6)).thenReturn("FIELD6_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(6)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(6)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(6)).thenReturn(60);
		when(mockLevel3RecordMetaData.getOutputLength(6)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(6)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(6)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(6)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(6)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(7)).thenReturn(FIELD7);
		when(mockLevel3RecordMetaData.getDataTypeName(7)).thenReturn("CUKY");
		when(mockLevel3RecordMetaData.getDatatype(7)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(7)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(7)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(7)).thenReturn("FIELD7_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(7)).thenReturn("FIELD7_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(7)).thenReturn("FIELD7_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(7)).thenReturn("FIELD7_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(7)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(7)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(7)).thenReturn(70);
		when(mockLevel3RecordMetaData.getOutputLength(7)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(7)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(7)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(7)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(7)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(8)).thenReturn(FIELD8);
		when(mockLevel3RecordMetaData.getDataTypeName(8)).thenReturn("LANG");
		when(mockLevel3RecordMetaData.getDatatype(8)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(8)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(8)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(8)).thenReturn("FIELD8_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(8)).thenReturn("FIELD8_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(8)).thenReturn("FIELD8_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(8)).thenReturn("FIELD8_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(8)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(8)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(8)).thenReturn(80);
		when(mockLevel3RecordMetaData.getOutputLength(8)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(8)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(8)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(8)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(8)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(9)).thenReturn(FIELD9);
		when(mockLevel3RecordMetaData.getDataTypeName(9)).thenReturn("CLNT");
		when(mockLevel3RecordMetaData.getDatatype(9)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(9)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(9)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(9)).thenReturn("FIELD9_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(9)).thenReturn("FIELD9_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(9)).thenReturn("FIELD9_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(9)).thenReturn("FIELD9_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(9)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(9)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(9)).thenReturn(90);
		when(mockLevel3RecordMetaData.getOutputLength(9)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(9)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(9)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(9)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(9)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(10)).thenReturn(FIELD10);
		when(mockLevel3RecordMetaData.getDataTypeName(10)).thenReturn("INT1");
		when(mockLevel3RecordMetaData.getDatatype(10)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel3RecordMetaData.getType(10)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(10)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(10)).thenReturn("FIELD10_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(10)).thenReturn("FIELD10_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(10)).thenReturn("FIELD10_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(10)).thenReturn("FIELD10_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(10)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(10)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(10)).thenReturn(100);
		when(mockLevel3RecordMetaData.getOutputLength(10)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(10)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(10)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(10)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(10)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(11)).thenReturn(FIELD11);
		when(mockLevel3RecordMetaData.getDataTypeName(11)).thenReturn("INT2");
		when(mockLevel3RecordMetaData.getDatatype(11)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel3RecordMetaData.getType(11)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(11)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(11)).thenReturn("FIELD11_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(11)).thenReturn("FIELD11_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(11)).thenReturn("FIELD11_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(11)).thenReturn("FIELD11_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(11)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(11)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(11)).thenReturn(110);
		when(mockLevel3RecordMetaData.getOutputLength(11)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(11)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(11)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(11)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(11)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(12)).thenReturn(FIELD12);
		when(mockLevel3RecordMetaData.getDataTypeName(12)).thenReturn("INT4");
		when(mockLevel3RecordMetaData.getDatatype(12)).thenReturn(IDocDatatype.INTEGER);
		when(mockLevel3RecordMetaData.getType(12)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(12)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(12)).thenReturn("FIELD12_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(12)).thenReturn("FIELD12_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(12)).thenReturn("FIELD12_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(12)).thenReturn("FIELD12_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(12)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(12)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(12)).thenReturn(120);
		when(mockLevel3RecordMetaData.getOutputLength(12)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(12)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(12)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(12)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(12)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(13)).thenReturn(FIELD13);
		when(mockLevel3RecordMetaData.getDataTypeName(13)).thenReturn("FLTP");
		when(mockLevel3RecordMetaData.getDatatype(13)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel3RecordMetaData.getType(13)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(13)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(13)).thenReturn("FIELD13_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(13)).thenReturn("FIELD13_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(13)).thenReturn("FIELD13_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(13)).thenReturn("FIELD13_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(13)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(13)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(13)).thenReturn(130);
		when(mockLevel3RecordMetaData.getOutputLength(13)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(13)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(13)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(13)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(13)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(14)).thenReturn(FIELD14);
		when(mockLevel3RecordMetaData.getDataTypeName(14)).thenReturn("ACCP");
		when(mockLevel3RecordMetaData.getDatatype(14)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel3RecordMetaData.getType(14)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(14)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(14)).thenReturn("FIELD14_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(14)).thenReturn("FIELD14_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(14)).thenReturn("FIELD14_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(14)).thenReturn("FIELD14_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(14)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(14)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(14)).thenReturn(140);
		when(mockLevel3RecordMetaData.getOutputLength(14)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(14)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(14)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(14)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(14)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(15)).thenReturn(FIELD15);
		when(mockLevel3RecordMetaData.getDataTypeName(15)).thenReturn("PREC");
		when(mockLevel3RecordMetaData.getDatatype(15)).thenReturn(IDocDatatype.NUMERIC);
		when(mockLevel3RecordMetaData.getType(15)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(15)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(15)).thenReturn("FIELD15_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(15)).thenReturn("FIELD15_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(15)).thenReturn("FIELD15_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(15)).thenReturn("FIELD15_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(15)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(15)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(15)).thenReturn(150);
		when(mockLevel3RecordMetaData.getOutputLength(15)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(15)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(15)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(15)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(15)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(16)).thenReturn(FIELD16);
		when(mockLevel3RecordMetaData.getDataTypeName(16)).thenReturn("LRAW");
		when(mockLevel3RecordMetaData.getDatatype(16)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel3RecordMetaData.getType(16)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(16)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(16)).thenReturn("FIELD16_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(16)).thenReturn("FIELD16_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(16)).thenReturn("FIELD16_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(16)).thenReturn("FIELD16_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(16)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(16)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(16)).thenReturn(160);
		when(mockLevel3RecordMetaData.getOutputLength(16)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(16)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(16)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(16)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(16)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(17)).thenReturn(FIELD17);
		when(mockLevel3RecordMetaData.getDataTypeName(17)).thenReturn("DEC");
		when(mockLevel3RecordMetaData.getDatatype(17)).thenReturn(IDocDatatype.DECIMAL);
		when(mockLevel3RecordMetaData.getType(17)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(17)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(17)).thenReturn("FIELD17_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(17)).thenReturn("FIELD17_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(17)).thenReturn("FIELD17_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(17)).thenReturn("FIELD17_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(17)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(17)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(17)).thenReturn(170);
		when(mockLevel3RecordMetaData.getOutputLength(17)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(17)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(17)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(17)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(17)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(18)).thenReturn(FIELD18);
		when(mockLevel3RecordMetaData.getDataTypeName(18)).thenReturn("RAW");
		when(mockLevel3RecordMetaData.getDatatype(18)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel3RecordMetaData.getType(18)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(18)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(18)).thenReturn("FIELD18_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(18)).thenReturn("FIELD18_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(18)).thenReturn("FIELD18_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(18)).thenReturn("FIELD18_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(18)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(18)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(18)).thenReturn(180);
		when(mockLevel3RecordMetaData.getOutputLength(18)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(18)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(18)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(18)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(18)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(19)).thenReturn(FIELD19);
		when(mockLevel3RecordMetaData.getDataTypeName(19)).thenReturn("STRING");
		when(mockLevel3RecordMetaData.getDatatype(19)).thenReturn(IDocDatatype.STRING);
		when(mockLevel3RecordMetaData.getType(19)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(19)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(19)).thenReturn("FIELD19_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(19)).thenReturn("FIELD19_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(19)).thenReturn("FIELD19_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(19)).thenReturn("FIELD19_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(19)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(19)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(19)).thenReturn(190);
		when(mockLevel3RecordMetaData.getOutputLength(19)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(19)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(19)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(19)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(19)).thenReturn(false);

		when(mockLevel3RecordMetaData.getName(20)).thenReturn(FIELD20);
		when(mockLevel3RecordMetaData.getDataTypeName(20)).thenReturn("RAWSTRING");
		when(mockLevel3RecordMetaData.getDatatype(20)).thenReturn(IDocDatatype.BINARY);
		when(mockLevel3RecordMetaData.getType(20)).thenReturn(IDocRecordMetaData.TYPE_CHAR);
		when(mockLevel3RecordMetaData.getTypeAsString(20)).thenReturn("CHAR");
		when(mockLevel3RecordMetaData.getDataElementName(20)).thenReturn("FIELD20_DATA_ELEMENT");
		when(mockLevel3RecordMetaData.getDomainName(20)).thenReturn("FIELD20_DOMAIN_NAME");
		when(mockLevel3RecordMetaData.getDescription(20)).thenReturn("FIELD20_DATA_ELEMENT Description");
		when(mockLevel3RecordMetaData.getCheckTableName(20)).thenReturn("FIELD20_CHECK_TABLE_NAME");
		when(mockLevel3RecordMetaData.getInternalLength(20)).thenReturn(10);
		when(mockLevel3RecordMetaData.getLength(20)).thenReturn(10);
		when(mockLevel3RecordMetaData.getOffset(20)).thenReturn(200);
		when(mockLevel3RecordMetaData.getOutputLength(20)).thenReturn(10);
		when(mockLevel3RecordMetaData.getValues(20)).thenReturn(RECORD_FIELD_VALUES);
		when(mockLevel3RecordMetaData.getValueDescriptions(20)).thenReturn(RECORD_FIELD_VALUE_DESCRIPTIONS);
		when(mockLevel3RecordMetaData.getValueRanges(20)).thenReturn(RECORD_FIELD_VALUE_RANGES);
		when(mockLevel3RecordMetaData.isISOCode(20)).thenReturn(false);

		when(mockLevel3Segment.getValue(FIELD0)).thenReturn(FIELD0_VALUE);
		when(mockLevel3Segment.getValue(FIELD1)).thenReturn(FIELD1_VALUE);
		when(mockLevel3Segment.getValue(FIELD2)).thenReturn(FIELD2_VALUE);
		when(mockLevel3Segment.getValue(FIELD3)).thenReturn(FIELD3_VALUE);
		when(mockLevel3Segment.getValue(FIELD4)).thenReturn(FIELD4_VALUE);
		when(mockLevel3Segment.getValue(FIELD5)).thenReturn(FIELD5_VALUE);
		when(mockLevel3Segment.getValue(FIELD6)).thenReturn(FIELD6_VALUE);
		when(mockLevel3Segment.getValue(FIELD7)).thenReturn(FIELD7_VALUE);
		when(mockLevel3Segment.getValue(FIELD8)).thenReturn(FIELD8_VALUE);
		when(mockLevel3Segment.getValue(FIELD9)).thenReturn(FIELD9_VALUE);
		when(mockLevel3Segment.getValue(FIELD10)).thenReturn(FIELD10_VALUE);
		when(mockLevel3Segment.getValue(FIELD11)).thenReturn(FIELD11_VALUE);
		when(mockLevel3Segment.getValue(FIELD12)).thenReturn(FIELD12_VALUE);
		when(mockLevel3Segment.getValue(FIELD13)).thenReturn(FIELD13_VALUE);
		when(mockLevel3Segment.getValue(FIELD14)).thenReturn(FIELD14_VALUE);
		when(mockLevel3Segment.getValue(FIELD15)).thenReturn(FIELD15_VALUE);
		when(mockLevel3Segment.getValue(FIELD16)).thenReturn(FIELD16_VALUE);
		when(mockLevel3Segment.getValue(FIELD17)).thenReturn(FIELD17_VALUE);
		when(mockLevel3Segment.getValue(FIELD18)).thenReturn(FIELD18_VALUE);
		when(mockLevel3Segment.getValue(FIELD19)).thenReturn(FIELD19_VALUE);
		when(mockLevel3Segment.getValue(FIELD20)).thenReturn(FIELD20_VALUE);
		
	}

	public void enhanceIDocDocument() throws Exception {

		/* Enhance IDoc Document mock */
		when(mockIDocDocument.getRootSegment()).thenReturn(mockRootSegment);
		when(mockIDocDocument.getArchiveKey()).thenReturn(ARCHIVE_KEY_VALUE);
		when(mockIDocDocument.getClient()).thenReturn(CLIENT_VALUE);
		when(mockIDocDocument.getCreationDate()).thenReturn(DATE_VALUE.getTime());
		when(mockIDocDocument.getCreationTime()).thenReturn(TIME_VALUE.getTime());
		when(mockIDocDocument.getDirection()).thenReturn(DIRECTION_VALUE);
		when(mockIDocDocument.getEDIMessage()).thenReturn(EDI_MESSAGE_VALUE);
		when(mockIDocDocument.getEDIMessageGroup()).thenReturn(EDI_MESSAGE_GROUP_VALUE);
		when(mockIDocDocument.getEDIMessageType()).thenReturn(EDI_MESSAGE_TYPE_VALUE);
		when(mockIDocDocument.getEDIStandardFlag()).thenReturn(EDI_STANDARD_FLAG_VALUE);
		when(mockIDocDocument.getEDIStandardVersion()).thenReturn(EDI_STANDARD_VERSION_VALUE);
		when(mockIDocDocument.getEDITransmissionFile()).thenReturn(EDI_TRANSMISSION_FILE_VALUE);
		when(mockIDocDocument.getIDocCompoundType()).thenReturn(IDOC_COMPOUND_TYPE_VALUE);
		when(mockIDocDocument.getIDocNumber()).thenReturn(IDOC_NUMBER_VALUE);
		when(mockIDocDocument.getIDocSAPRelease()).thenReturn(IDOC_SAP_RELEASE_VALUE);
		when(mockIDocDocument.getIDocType()).thenReturn(IDOC_TYPE_VALUE);
		when(mockIDocDocument.getIDocTypeExtension()).thenReturn(IDOC_TYPE_EXTENSION_VALUE);
		when(mockIDocDocument.getMessageCode()).thenReturn(MESSAGE_CODE_VALUE);
		when(mockIDocDocument.getMessageFunction()).thenReturn(MESSAGE_FUNCTION_VALUE);
		when(mockIDocDocument.getMessageType()).thenReturn(MESSAGE_TYPE_VALUE);
		when(mockIDocDocument.getOutputMode()).thenReturn(OUTPUT_MODE_VALUE);
		when(mockIDocDocument.getRecipientAddress()).thenReturn(RECIPIENT_ADDRESS_VALUE);
		when(mockIDocDocument.getRecipientLogicalAddress()).thenReturn(RECIPIENT_LOGICAL_ADDRESS_VALUE);
		when(mockIDocDocument.getRecipientPartnerFunction()).thenReturn(RECIPIENT_PARTNER_FUNCTION_VALUE);
		when(mockIDocDocument.getRecipientPartnerType()).thenReturn(RECIPIENT_PARTNER_TYPE_VALUE);
		when(mockIDocDocument.getRecipientPartnerNumber()).thenReturn(RECIPIENT_PARTNER_NUMBER_VALUE);
		when(mockIDocDocument.getRecipientPort()).thenReturn(RECIPIENT_PORT_VALUE);
		when(mockIDocDocument.getSenderAddress()).thenReturn(SENDER_ADDRESS_VALUE);
		when(mockIDocDocument.getSenderLogicalAddress()).thenReturn(SENDER_LOGICAL_ADDRESS_VALUE);
		when(mockIDocDocument.getSenderPartnerFunction()).thenReturn(SENDER_PARTNER_FUNCTION_VALUE);
		when(mockIDocDocument.getSenderPartnerNumber()).thenReturn(SENDER_PARTNER_NUMBER_VALUE);
		when(mockIDocDocument.getSenderPartnerType()).thenReturn(SENDER_PARTNER_TYPE_VALUE);
		when(mockIDocDocument.getSenderPort()).thenReturn(SENDER_PORT_VALUE);
		when(mockIDocDocument.getSerialization()).thenReturn(SERIALIZATION_VALUE);
		when(mockIDocDocument.getStatus()).thenReturn(STATUS_VALUE);
		when(mockIDocDocument.getTestFlag()).thenReturn(TEST_FLAG_VALUE);
		
		
	}
	
	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();
		
		createMocks();
		
		/* Enhance Destination mock */
		when(mockDestination.createTID()).thenReturn(TEST_TID);
		
		/* Enhance IDoc Server mock */
		when(mockIDocServer.getRepositoryDestination()).thenReturn(TEST_DEST);
		when(mockIDocServer.getProgramID()).thenReturn(TEST_PROGRAM_ID);
		when(mockIDocServer.getIDocHandlerFactory()).thenReturn(mockIDocHandlerFactory);
		when(mockIDocServer.getIDocRepository()).thenReturn(mockIDocRepository);
		when(mockIDocServer.getState()).thenReturn(JCoServerState.STOPPED);
		
		/* Enhance IDoc Repository  mock */
		when(mockIDocRepository.getName()).thenReturn(TEST_REPOSITORY);
		when(mockIDocRepository.getRootSegmentMetaData(TEST_IDOC_TYPE, TEST_IDOC_TYPE_EXTENSION, TEST_SYSTEM_RELEASE, TEST_APPLICATION_RELEASE)).thenReturn(mockRootSegmentMetaData);
		
		/* Enhance IDoc Factory mock */
		when(mockIDocFactory.createIDocDocument(mockIDocRepository, TEST_IDOC_TYPE, TEST_IDOC_TYPE_EXTENSION, TEST_SYSTEM_RELEASE, TEST_APPLICATION_RELEASE)).thenReturn(mockIDocDocument);
		when(mockIDocFactory.createIDocDocumentList(mockIDocRepository, TEST_IDOC_TYPE, TEST_IDOC_TYPE_EXTENSION, TEST_SYSTEM_RELEASE, TEST_APPLICATION_RELEASE)).thenReturn(mockIDocDocumentList);
		
		/* Enhance IDoc Document List mock */
		when(mockIDocDocumentList.iterator()).thenReturn(mockIDocDocumentListIterator);
		when(mockIDocDocumentList.getIDocType()).thenReturn(TEST_IDOC_TYPE);
		when(mockIDocDocumentList.getIDocTypeExtension()).thenReturn(TEST_IDOC_TYPE_EXTENSION);
		when(mockIDocDocumentList.getSystemRelease()).thenReturn(TEST_SYSTEM_RELEASE);
		when(mockIDocDocumentList.getApplicationRelease()).thenReturn(TEST_APPLICATION_RELEASE);
		when(mockIDocDocumentList.addNew()).thenReturn(mockIDocDocument);
		
		/* Enhance IDoc Document List Iterator mock */
		when(mockIDocDocumentListIterator.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockIDocDocumentListIterator.next()).thenReturn(mockIDocDocument).thenReturn(mockIDocDocument).thenThrow(new NoSuchElementException());
		
		enhanceRootSegment();
		enhanceLevel1Segment();
		enhanceLevel2Segment();
		enhanceLevel3Segment();
		enhanceIDocDocument();		
	}
	
	protected Document createAndPopulateDocument() throws Exception {
		Document document = IDocUtil.createDocument(mockIDocRepository, TEST_IDOC_TYPE, TEST_IDOC_TYPE_EXTENSION, TEST_SYSTEM_RELEASE, TEST_APPLICATION_RELEASE);
		
		document.setArchiveKey(ARCHIVE_KEY_VALUE); 
		document.setClient(CLIENT_VALUE);
		document.setCreationDate(DATE_VALUE.getTime());
		document.setCreationTime(TIME_VALUE.getTime());
		document.setDirection(DIRECTION_VALUE);
		document.setEDIMessage(EDI_MESSAGE_VALUE);
		document.setEDIMessageGroup(EDI_MESSAGE_GROUP_VALUE);
		document.setEDIMessageType(EDI_MESSAGE_TYPE_VALUE);
		document.setEDIStandardFlag(EDI_STANDARD_FLAG_VALUE);
		document.setEDIStandardVersion(EDI_STANDARD_VERSION_VALUE);
		document.setEDITransmissionFile(EDI_TRANSMISSION_FILE_VALUE);
		document.setIDocCompoundType(IDOC_COMPOUND_TYPE_VALUE);
		document.setIDocNumber(IDOC_NUMBER_VALUE);
		document.setIDocSAPRelease(IDOC_SAP_RELEASE_VALUE);
		document.setIDocType(IDOC_TYPE_VALUE);
		document.setIDocTypeExtension(IDOC_TYPE_EXTENSION_VALUE);
		document.setMessageCode(MESSAGE_CODE_VALUE);
		document.setMessageFunction(MESSAGE_FUNCTION_VALUE);
		document.setMessageType(MESSAGE_TYPE_VALUE);
		document.setOutputMode(OUTPUT_MODE_VALUE);
		document.setRecipientAddress(RECIPIENT_ADDRESS_VALUE);
		document.setRecipientLogicalAddress(RECIPIENT_LOGICAL_ADDRESS_VALUE);
		document.setRecipientPartnerFunction(RECIPIENT_PARTNER_FUNCTION_VALUE);
		document.setRecipientPartnerNumber(RECIPIENT_PARTNER_NUMBER_VALUE);
		document.setRecipientPartnerType(RECIPIENT_PARTNER_TYPE_VALUE);
		document.setRecipientPort(RECIPIENT_PORT_VALUE);
		document.setSenderAddress(SENDER_ADDRESS_VALUE);
		document.setSenderLogicalAddress(SENDER_LOGICAL_ADDRESS_VALUE);
		document.setSenderPartnerFunction(SENDER_PARTNER_FUNCTION_VALUE);
		document.setSenderPartnerNumber(SENDER_PARTNER_NUMBER_VALUE);
		document.setSenderPartnerType(SENDER_PARTNER_TYPE_VALUE);
		document.setSenderPort(SENDER_PORT_VALUE);
		document.setSerialization(SERIALIZATION_VALUE);
		document.setStatus(STATUS_VALUE);
		document.setTestFlag(TEST_FLAG_VALUE);
		
		Segment rootSegment = document.getRootSegment();
		
		Segment level1Segment = rootSegment.getChildren(LEVEL1).add();
		level1Segment.put(FIELD0, FIELD0_VALUE);
		level1Segment.put(FIELD1, FIELD1_VALUE);
		level1Segment.put(FIELD2, FIELD2_VALUE);
		level1Segment.put(FIELD3, FIELD3_VALUE);
		level1Segment.put(FIELD4, FIELD4_VALUE);
		level1Segment.put(FIELD5, FIELD5_VALUE);
		level1Segment.put(FIELD6, FIELD6_VALUE);
		level1Segment.put(FIELD7, FIELD7_VALUE);
		level1Segment.put(FIELD8, FIELD8_VALUE);
		level1Segment.put(FIELD9, FIELD9_VALUE);
		level1Segment.put(FIELD10, FIELD10_VALUE);
		level1Segment.put(FIELD11, FIELD11_VALUE);
		level1Segment.put(FIELD12, FIELD12_VALUE);
		level1Segment.put(FIELD13, FIELD13_VALUE);
		level1Segment.put(FIELD14, FIELD14_VALUE);
		level1Segment.put(FIELD15, FIELD15_VALUE);
		level1Segment.put(FIELD16, FIELD16_VALUE);
		level1Segment.put(FIELD17, FIELD17_VALUE);
		level1Segment.put(FIELD18, FIELD18_VALUE);
		level1Segment.put(FIELD19, FIELD19_VALUE);
		level1Segment.put(FIELD20, FIELD20_VALUE);
		
		Segment level2Segment = level1Segment.getChildren(LEVEL2).add();
		level2Segment.put(FIELD0, FIELD0_VALUE);
		level2Segment.put(FIELD1, FIELD1_VALUE);
		level2Segment.put(FIELD2, FIELD2_VALUE);
		level2Segment.put(FIELD3, FIELD3_VALUE);
		level2Segment.put(FIELD4, FIELD4_VALUE);
		level2Segment.put(FIELD5, FIELD5_VALUE);
		level2Segment.put(FIELD6, FIELD6_VALUE);
		level2Segment.put(FIELD7, FIELD7_VALUE);
		level2Segment.put(FIELD8, FIELD8_VALUE);
		level2Segment.put(FIELD9, FIELD9_VALUE);
		level2Segment.put(FIELD10, FIELD10_VALUE);
		level2Segment.put(FIELD11, FIELD11_VALUE);
		level2Segment.put(FIELD12, FIELD12_VALUE);
		level2Segment.put(FIELD13, FIELD13_VALUE);
		level2Segment.put(FIELD14, FIELD14_VALUE);
		level2Segment.put(FIELD15, FIELD15_VALUE);
		level2Segment.put(FIELD16, FIELD16_VALUE);
		level2Segment.put(FIELD17, FIELD17_VALUE);
		level2Segment.put(FIELD18, FIELD18_VALUE);
		level2Segment.put(FIELD19, FIELD19_VALUE);
		level2Segment.put(FIELD20, FIELD20_VALUE);
		
		Segment level3Segment = level2Segment.getChildren(LEVEL3).add();
		level3Segment.put(FIELD0, FIELD0_VALUE);
		level3Segment.put(FIELD1, FIELD1_VALUE);
		level3Segment.put(FIELD2, FIELD2_VALUE);
		level3Segment.put(FIELD3, FIELD3_VALUE);
		level3Segment.put(FIELD4, FIELD4_VALUE);
		level3Segment.put(FIELD5, FIELD5_VALUE);
		level3Segment.put(FIELD6, FIELD6_VALUE);
		level3Segment.put(FIELD7, FIELD7_VALUE);
		level3Segment.put(FIELD8, FIELD8_VALUE);
		level3Segment.put(FIELD9, FIELD9_VALUE);
		level3Segment.put(FIELD10, FIELD10_VALUE);
		level3Segment.put(FIELD11, FIELD11_VALUE);
		level3Segment.put(FIELD12, FIELD12_VALUE);
		level3Segment.put(FIELD13, FIELD13_VALUE);
		level3Segment.put(FIELD14, FIELD14_VALUE);
		level3Segment.put(FIELD15, FIELD15_VALUE);
		level3Segment.put(FIELD16, FIELD16_VALUE);
		level3Segment.put(FIELD17, FIELD17_VALUE);
		level3Segment.put(FIELD18, FIELD18_VALUE);
		level3Segment.put(FIELD19, FIELD19_VALUE);
		level3Segment.put(FIELD20, FIELD20_VALUE);
		
		return document;
	}
	
	protected DocumentList createAndPopulateDocumentList() throws Exception {
		DocumentList documentList = IDocUtil.createDocumentList(mockIDocRepository, TEST_IDOC_TYPE, TEST_IDOC_TYPE_EXTENSION, TEST_SYSTEM_RELEASE, TEST_APPLICATION_RELEASE);
		Document document = createAndPopulateDocument();
		documentList.add(document);
		return documentList;
	}

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new StaticApplicationContext();
	}

}
