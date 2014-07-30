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
package org.fusesource.camel.component.sap.model.idoc;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.fusesource.camel.component.sap.model.idoc.IdocFactory
 * @model kind="package"
 * @generated
 */
public interface IdocPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "idoc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sap.fusesource.org/idoc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "idoc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdocPackage eINSTANCE = org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentListImpl <em>Document List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.DocumentListImpl
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getDocumentList()
	 * @generated
	 */
	int DOCUMENT_LIST = 0;

	/**
	 * The feature id for the '<em><b>Idoc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST__IDOC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Idoc Type Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST__IDOC_TYPE_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>System Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST__SYSTEM_RELEASE = 2;

	/**
	 * The feature id for the '<em><b>Application Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST__APPLICATION_RELEASE = 3;

	/**
	 * The feature id for the '<em><b>Document</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST__DOCUMENT = 4;

	/**
	 * The number of structural features of the '<em>Document List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_LIST_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 1;

	/**
	 * The feature id for the '<em><b>Archive Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__ARCHIVE_KEY = 0;

	/**
	 * The feature id for the '<em><b>Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CLIENT = 1;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CREATION_DATE = 2;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CREATION_TIME = 3;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__DIRECTION = 4;

	/**
	 * The feature id for the '<em><b>EDI Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_MESSAGE = 5;

	/**
	 * The feature id for the '<em><b>EDI Message Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_MESSAGE_GROUP = 6;

	/**
	 * The feature id for the '<em><b>EDI Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_MESSAGE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>EDI Standard Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_STANDARD_FLAG = 8;

	/**
	 * The feature id for the '<em><b>EDI Standard Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_STANDARD_VERSION = 9;

	/**
	 * The feature id for the '<em><b>EDI Transmission File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__EDI_TRANSMISSION_FILE = 10;

	/**
	 * The feature id for the '<em><b>IDoc Compound Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IDOC_COMPOUND_TYPE = 11;

	/**
	 * The feature id for the '<em><b>IDoc Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IDOC_NUMBER = 12;

	/**
	 * The feature id for the '<em><b>IDoc SAP Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IDOC_SAP_RELEASE = 13;

	/**
	 * The feature id for the '<em><b>IDoc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IDOC_TYPE = 14;

	/**
	 * The feature id for the '<em><b>IDoc Type Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IDOC_TYPE_EXTENSION = 15;

	/**
	 * The feature id for the '<em><b>Message Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MESSAGE_CODE = 16;

	/**
	 * The feature id for the '<em><b>Message Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MESSAGE_FUNCTION = 17;

	/**
	 * The feature id for the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MESSAGE_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Output Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__OUTPUT_MODE = 19;

	/**
	 * The feature id for the '<em><b>Recipient Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_ADDRESS = 20;

	/**
	 * The feature id for the '<em><b>Recipient Logical Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_LOGICAL_ADDRESS = 21;

	/**
	 * The feature id for the '<em><b>Recipient Partner Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_PARTNER_FUNCTION = 22;

	/**
	 * The feature id for the '<em><b>Recipient Partner Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_PARTNER_NUMBER = 23;

	/**
	 * The feature id for the '<em><b>Recipient Partner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_PARTNER_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Recipient Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RECIPIENT_PORT = 25;

	/**
	 * The feature id for the '<em><b>Sender Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_ADDRESS = 26;

	/**
	 * The feature id for the '<em><b>Sender Logical Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_LOGICAL_ADDRESS = 27;

	/**
	 * The feature id for the '<em><b>Sender Partner Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_PARTNER_FUNCTION = 28;

	/**
	 * The feature id for the '<em><b>Sender Partner Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_PARTNER_NUMBER = 29;

	/**
	 * The feature id for the '<em><b>Sender Partner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_PARTNER_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Sender Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SENDER_PORT = 31;

	/**
	 * The feature id for the '<em><b>Serialization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SERIALIZATION = 32;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__STATUS = 33;

	/**
	 * The feature id for the '<em><b>Test Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__TEST_FLAG = 34;

	/**
	 * The feature id for the '<em><b>Root Segment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__ROOT_SEGMENT = 35;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = 36;

	/**
	 * The meta object id for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__PARENT = 0;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__DOCUMENT = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Hierarchy Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__HIERARCHY_LEVEL = 5;

	/**
	 * The feature id for the '<em><b>Idoc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__IDOC_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Idoc Type Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__IDOC_TYPE_EXTENSION = 7;

	/**
	 * The feature id for the '<em><b>System Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__SYSTEM_RELEASE = 8;

	/**
	 * The feature id for the '<em><b>Application Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__APPLICATION_RELEASE = 9;

	/**
	 * The feature id for the '<em><b>Num Fields</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__NUM_FIELDS = 10;

	/**
	 * The feature id for the '<em><b>Max Occurrence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__MAX_OCCURRENCE = 11;

	/**
	 * The feature id for the '<em><b>Min Occurrence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__MIN_OCCURRENCE = 12;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__MANDATORY = 13;

	/**
	 * The feature id for the '<em><b>Qualified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__QUALIFIED = 14;

	/**
	 * The feature id for the '<em><b>Record Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__RECORD_LENGTH = 15;

	/**
	 * The number of structural features of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = 16;


	/**
	 * The meta object id for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentChildrenImpl <em>Segment Children</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentChildrenImpl
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegmentChildren()
	 * @generated
	 */
	int SEGMENT_CHILDREN = 3;

	/**
	 * The feature id for the '<em><b>Segments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_CHILDREN__SEGMENTS = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_CHILDREN__PARENT = 1;

	/**
	 * The number of structural features of the '<em>Segment Children</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_CHILDREN_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentListImpl <em>Segment List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentListImpl
	 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegmentList()
	 * @generated
	 */
	int SEGMENT_LIST = 4;

	/**
	 * The number of structural features of the '<em>Segment List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_LIST_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList <em>Document List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document List</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList
	 * @generated
	 */
	EClass getDocumentList();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocType <em>Idoc Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idoc Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocType()
	 * @see #getDocumentList()
	 * @generated
	 */
	EAttribute getDocumentList_IdocType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocTypeExtension <em>Idoc Type Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idoc Type Extension</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocTypeExtension()
	 * @see #getDocumentList()
	 * @generated
	 */
	EAttribute getDocumentList_IdocTypeExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getSystemRelease <em>System Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Release</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList#getSystemRelease()
	 * @see #getDocumentList()
	 * @generated
	 */
	EAttribute getDocumentList_SystemRelease();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getApplicationRelease <em>Application Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Release</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList#getApplicationRelease()
	 * @see #getDocumentList()
	 * @generated
	 */
	EAttribute getDocumentList_ApplicationRelease();

	/**
	 * Returns the meta object for the containment reference list '{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Document</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.DocumentList#getDocument()
	 * @see #getDocumentList()
	 * @generated
	 */
	EReference getDocumentList_Document();

	/**
	 * Returns the meta object for class '{@link org.fusesource.camel.component.sap.model.idoc.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getArchiveKey <em>Archive Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Archive Key</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getArchiveKey()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_ArchiveKey();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getClient <em>Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Client</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getClient()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Client();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getCreationDate()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_CreationDate();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationTime <em>Creation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Time</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getCreationTime()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_CreationTime();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getDirection()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Direction();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessage <em>EDI Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Message</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessage()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDIMessage();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageGroup <em>EDI Message Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Message Group</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageGroup()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDIMessageGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageType <em>EDI Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Message Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDIMessageType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardFlag <em>EDI Standard Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Standard Flag</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardFlag()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDIStandardFlag();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardVersion <em>EDI Standard Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Standard Version</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardVersion()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDIStandardVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDITransmissionFile <em>EDI Transmission File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EDI Transmission File</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getEDITransmissionFile()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_EDITransmissionFile();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocCompoundType <em>IDoc Compound Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IDoc Compound Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getIDocCompoundType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_IDocCompoundType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocNumber <em>IDoc Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IDoc Number</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getIDocNumber()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_IDocNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocSAPRelease <em>IDoc SAP Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IDoc SAP Release</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getIDocSAPRelease()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_IDocSAPRelease();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocType <em>IDoc Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IDoc Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getIDocType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_IDocType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocTypeExtension <em>IDoc Type Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IDoc Type Extension</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getIDocTypeExtension()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_IDocTypeExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageCode <em>Message Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Code</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getMessageCode()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_MessageCode();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageFunction <em>Message Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Function</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getMessageFunction()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_MessageFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getMessageType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_MessageType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getOutputMode <em>Output Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Mode</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getOutputMode()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_OutputMode();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientAddress <em>Recipient Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Address</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientAddress()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientLogicalAddress <em>Recipient Logical Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Logical Address</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientLogicalAddress()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientLogicalAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerFunction <em>Recipient Partner Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Partner Function</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerFunction()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientPartnerFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerNumber <em>Recipient Partner Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Partner Number</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerNumber()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientPartnerNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerType <em>Recipient Partner Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Partner Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientPartnerType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPort <em>Recipient Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipient Port</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPort()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_RecipientPort();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderAddress <em>Sender Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Address</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderAddress()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderLogicalAddress <em>Sender Logical Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Logical Address</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderLogicalAddress()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderLogicalAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerFunction <em>Sender Partner Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Partner Function</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerFunction()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderPartnerFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerNumber <em>Sender Partner Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Partner Number</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerNumber()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderPartnerNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerType <em>Sender Partner Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Partner Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderPartnerType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPort <em>Sender Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sender Port</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSenderPort()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_SenderPort();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSerialization <em>Serialization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Serialization</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getSerialization()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Serialization();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getStatus()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Status();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Document#getTestFlag <em>Test Flag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Flag</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getTestFlag()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_TestFlag();

	/**
	 * Returns the meta object for the containment reference '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRootSegment <em>Root Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Segment</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Document#getRootSegment()
	 * @see #getDocument()
	 * @generated
	 */
	EReference getDocument_RootSegment();

	/**
	 * Returns the meta object for class '{@link org.fusesource.camel.component.sap.model.idoc.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the reference '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getParent()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_Parent();

	/**
	 * Returns the meta object for the reference '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Document</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getDocument()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_Document();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getDescription()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getType()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getDefinition()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Definition();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getHierarchyLevel <em>Hierarchy Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hierarchy Level</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getHierarchyLevel()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_HierarchyLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getIdocType <em>Idoc Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idoc Type</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getIdocType()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_IdocType();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getIdocTypeExtension <em>Idoc Type Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idoc Type Extension</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getIdocTypeExtension()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_IdocTypeExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getSystemRelease <em>System Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Release</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getSystemRelease()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_SystemRelease();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getApplicationRelease <em>Application Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Release</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getApplicationRelease()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_ApplicationRelease();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getNumFields <em>Num Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Fields</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getNumFields()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_NumFields();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getMaxOccurrence <em>Max Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Occurrence</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getMaxOccurrence()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_MaxOccurrence();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getMinOccurrence <em>Min Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Occurrence</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getMinOccurrence()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_MinOccurrence();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#isMandatory()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Mandatory();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#isQualified <em>Qualified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#isQualified()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Qualified();

	/**
	 * Returns the meta object for the attribute '{@link org.fusesource.camel.component.sap.model.idoc.Segment#getRecordLength <em>Record Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Record Length</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.Segment#getRecordLength()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_RecordLength();

	/**
	 * Returns the meta object for class '{@link org.fusesource.camel.component.sap.model.idoc.SegmentChildren <em>Segment Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment Children</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.SegmentChildren
	 * @generated
	 */
	EClass getSegmentChildren();

	/**
	 * Returns the meta object for the attribute list '{@link org.fusesource.camel.component.sap.model.idoc.SegmentChildren#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Segments</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.SegmentChildren#getSegments()
	 * @see #getSegmentChildren()
	 * @generated
	 */
	EAttribute getSegmentChildren_Segments();

	/**
	 * Returns the meta object for the reference '{@link org.fusesource.camel.component.sap.model.idoc.SegmentChildren#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.SegmentChildren#getParent()
	 * @see #getSegmentChildren()
	 * @generated
	 */
	EReference getSegmentChildren_Parent();

	/**
	 * Returns the meta object for class '{@link org.fusesource.camel.component.sap.model.idoc.SegmentList <em>Segment List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment List</em>'.
	 * @see org.fusesource.camel.component.sap.model.idoc.SegmentList
	 * @generated
	 */
	EClass getSegmentList();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IdocFactory getIdocFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentListImpl <em>Document List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.DocumentListImpl
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getDocumentList()
		 * @generated
		 */
		EClass DOCUMENT_LIST = eINSTANCE.getDocumentList();

		/**
		 * The meta object literal for the '<em><b>Idoc Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_LIST__IDOC_TYPE = eINSTANCE.getDocumentList_IdocType();

		/**
		 * The meta object literal for the '<em><b>Idoc Type Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_LIST__IDOC_TYPE_EXTENSION = eINSTANCE.getDocumentList_IdocTypeExtension();

		/**
		 * The meta object literal for the '<em><b>System Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_LIST__SYSTEM_RELEASE = eINSTANCE.getDocumentList_SystemRelease();

		/**
		 * The meta object literal for the '<em><b>Application Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_LIST__APPLICATION_RELEASE = eINSTANCE.getDocumentList_ApplicationRelease();

		/**
		 * The meta object literal for the '<em><b>Document</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_LIST__DOCUMENT = eINSTANCE.getDocumentList_Document();

		/**
		 * The meta object literal for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();

		/**
		 * The meta object literal for the '<em><b>Archive Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__ARCHIVE_KEY = eINSTANCE.getDocument_ArchiveKey();

		/**
		 * The meta object literal for the '<em><b>Client</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__CLIENT = eINSTANCE.getDocument_Client();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__CREATION_DATE = eINSTANCE.getDocument_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Creation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__CREATION_TIME = eINSTANCE.getDocument_CreationTime();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__DIRECTION = eINSTANCE.getDocument_Direction();

		/**
		 * The meta object literal for the '<em><b>EDI Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_MESSAGE = eINSTANCE.getDocument_EDIMessage();

		/**
		 * The meta object literal for the '<em><b>EDI Message Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_MESSAGE_GROUP = eINSTANCE.getDocument_EDIMessageGroup();

		/**
		 * The meta object literal for the '<em><b>EDI Message Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_MESSAGE_TYPE = eINSTANCE.getDocument_EDIMessageType();

		/**
		 * The meta object literal for the '<em><b>EDI Standard Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_STANDARD_FLAG = eINSTANCE.getDocument_EDIStandardFlag();

		/**
		 * The meta object literal for the '<em><b>EDI Standard Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_STANDARD_VERSION = eINSTANCE.getDocument_EDIStandardVersion();

		/**
		 * The meta object literal for the '<em><b>EDI Transmission File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__EDI_TRANSMISSION_FILE = eINSTANCE.getDocument_EDITransmissionFile();

		/**
		 * The meta object literal for the '<em><b>IDoc Compound Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__IDOC_COMPOUND_TYPE = eINSTANCE.getDocument_IDocCompoundType();

		/**
		 * The meta object literal for the '<em><b>IDoc Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__IDOC_NUMBER = eINSTANCE.getDocument_IDocNumber();

		/**
		 * The meta object literal for the '<em><b>IDoc SAP Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__IDOC_SAP_RELEASE = eINSTANCE.getDocument_IDocSAPRelease();

		/**
		 * The meta object literal for the '<em><b>IDoc Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__IDOC_TYPE = eINSTANCE.getDocument_IDocType();

		/**
		 * The meta object literal for the '<em><b>IDoc Type Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__IDOC_TYPE_EXTENSION = eINSTANCE.getDocument_IDocTypeExtension();

		/**
		 * The meta object literal for the '<em><b>Message Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__MESSAGE_CODE = eINSTANCE.getDocument_MessageCode();

		/**
		 * The meta object literal for the '<em><b>Message Function</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__MESSAGE_FUNCTION = eINSTANCE.getDocument_MessageFunction();

		/**
		 * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__MESSAGE_TYPE = eINSTANCE.getDocument_MessageType();

		/**
		 * The meta object literal for the '<em><b>Output Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__OUTPUT_MODE = eINSTANCE.getDocument_OutputMode();

		/**
		 * The meta object literal for the '<em><b>Recipient Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_ADDRESS = eINSTANCE.getDocument_RecipientAddress();

		/**
		 * The meta object literal for the '<em><b>Recipient Logical Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_LOGICAL_ADDRESS = eINSTANCE.getDocument_RecipientLogicalAddress();

		/**
		 * The meta object literal for the '<em><b>Recipient Partner Function</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_PARTNER_FUNCTION = eINSTANCE.getDocument_RecipientPartnerFunction();

		/**
		 * The meta object literal for the '<em><b>Recipient Partner Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_PARTNER_NUMBER = eINSTANCE.getDocument_RecipientPartnerNumber();

		/**
		 * The meta object literal for the '<em><b>Recipient Partner Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_PARTNER_TYPE = eINSTANCE.getDocument_RecipientPartnerType();

		/**
		 * The meta object literal for the '<em><b>Recipient Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__RECIPIENT_PORT = eINSTANCE.getDocument_RecipientPort();

		/**
		 * The meta object literal for the '<em><b>Sender Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_ADDRESS = eINSTANCE.getDocument_SenderAddress();

		/**
		 * The meta object literal for the '<em><b>Sender Logical Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_LOGICAL_ADDRESS = eINSTANCE.getDocument_SenderLogicalAddress();

		/**
		 * The meta object literal for the '<em><b>Sender Partner Function</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_PARTNER_FUNCTION = eINSTANCE.getDocument_SenderPartnerFunction();

		/**
		 * The meta object literal for the '<em><b>Sender Partner Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_PARTNER_NUMBER = eINSTANCE.getDocument_SenderPartnerNumber();

		/**
		 * The meta object literal for the '<em><b>Sender Partner Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_PARTNER_TYPE = eINSTANCE.getDocument_SenderPartnerType();

		/**
		 * The meta object literal for the '<em><b>Sender Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SENDER_PORT = eINSTANCE.getDocument_SenderPort();

		/**
		 * The meta object literal for the '<em><b>Serialization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SERIALIZATION = eINSTANCE.getDocument_Serialization();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__STATUS = eINSTANCE.getDocument_Status();

		/**
		 * The meta object literal for the '<em><b>Test Flag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__TEST_FLAG = eINSTANCE.getDocument_TestFlag();

		/**
		 * The meta object literal for the '<em><b>Root Segment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT__ROOT_SEGMENT = eINSTANCE.getDocument_RootSegment();

		/**
		 * The meta object literal for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__PARENT = eINSTANCE.getSegment_Parent();

		/**
		 * The meta object literal for the '<em><b>Document</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__DOCUMENT = eINSTANCE.getSegment_Document();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__DESCRIPTION = eINSTANCE.getSegment_Description();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__TYPE = eINSTANCE.getSegment_Type();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__DEFINITION = eINSTANCE.getSegment_Definition();

		/**
		 * The meta object literal for the '<em><b>Hierarchy Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__HIERARCHY_LEVEL = eINSTANCE.getSegment_HierarchyLevel();

		/**
		 * The meta object literal for the '<em><b>Idoc Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__IDOC_TYPE = eINSTANCE.getSegment_IdocType();

		/**
		 * The meta object literal for the '<em><b>Idoc Type Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__IDOC_TYPE_EXTENSION = eINSTANCE.getSegment_IdocTypeExtension();

		/**
		 * The meta object literal for the '<em><b>System Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__SYSTEM_RELEASE = eINSTANCE.getSegment_SystemRelease();

		/**
		 * The meta object literal for the '<em><b>Application Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__APPLICATION_RELEASE = eINSTANCE.getSegment_ApplicationRelease();

		/**
		 * The meta object literal for the '<em><b>Num Fields</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__NUM_FIELDS = eINSTANCE.getSegment_NumFields();

		/**
		 * The meta object literal for the '<em><b>Max Occurrence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__MAX_OCCURRENCE = eINSTANCE.getSegment_MaxOccurrence();

		/**
		 * The meta object literal for the '<em><b>Min Occurrence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__MIN_OCCURRENCE = eINSTANCE.getSegment_MinOccurrence();

		/**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__MANDATORY = eINSTANCE.getSegment_Mandatory();

		/**
		 * The meta object literal for the '<em><b>Qualified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__QUALIFIED = eINSTANCE.getSegment_Qualified();

		/**
		 * The meta object literal for the '<em><b>Record Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__RECORD_LENGTH = eINSTANCE.getSegment_RecordLength();

		/**
		 * The meta object literal for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentChildrenImpl <em>Segment Children</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentChildrenImpl
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegmentChildren()
		 * @generated
		 */
		EClass SEGMENT_CHILDREN = eINSTANCE.getSegmentChildren();

		/**
		 * The meta object literal for the '<em><b>Segments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT_CHILDREN__SEGMENTS = eINSTANCE.getSegmentChildren_Segments();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT_CHILDREN__PARENT = eINSTANCE.getSegmentChildren_Parent();

		/**
		 * The meta object literal for the '{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentListImpl <em>Segment List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.SegmentListImpl
		 * @see org.fusesource.camel.component.sap.model.idoc.impl.IdocPackageImpl#getSegmentList()
		 * @generated
		 */
		EClass SEGMENT_LIST = eINSTANCE.getSegmentList();

	}

} //IdocPackage
