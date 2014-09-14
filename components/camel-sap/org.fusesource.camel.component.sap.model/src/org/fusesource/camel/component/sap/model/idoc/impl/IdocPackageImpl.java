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
package org.fusesource.camel.component.sap.model.idoc.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.model.idoc.IdocFactory;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.SegmentChildren;
import org.fusesource.camel.component.sap.model.idoc.SegmentList;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.impl.RfcPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdocPackageImpl extends EPackageImpl implements IdocPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentChildrenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentListEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IdocPackageImpl() {
		super(eNS_URI, IdocFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link IdocPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IdocPackage init() {
		if (isInited) return (IdocPackage)EPackage.Registry.INSTANCE.getEPackage(IdocPackage.eNS_URI);

		// Obtain or create and register package
		IdocPackageImpl theIdocPackage = (IdocPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof IdocPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new IdocPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		RfcPackageImpl theRfcPackage = (RfcPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RfcPackage.eNS_URI) instanceof RfcPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RfcPackage.eNS_URI) : RfcPackage.eINSTANCE);

		// Create package meta-data objects
		theIdocPackage.createPackageContents();
		theRfcPackage.createPackageContents();

		// Initialize created meta-data
		theIdocPackage.initializePackageContents();
		theRfcPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIdocPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IdocPackage.eNS_URI, theIdocPackage);
		return theIdocPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentList() {
		return documentListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentList_IdocType() {
		return (EAttribute)documentListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentList_IdocTypeExtension() {
		return (EAttribute)documentListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentList_SystemRelease() {
		return (EAttribute)documentListEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentList_ApplicationRelease() {
		return (EAttribute)documentListEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentList_Document() {
		return (EReference)documentListEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocument() {
		return documentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_ArchiveKey() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_Client() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_CreationDate() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_CreationTime() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_Direction() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDIMessage() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDIMessageGroup() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDIMessageType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDIStandardFlag() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDIStandardVersion() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_EDITransmissionFile() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_IDocCompoundType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_IDocNumber() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_IDocSAPRelease() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_IDocType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_IDocTypeExtension() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_MessageCode() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_MessageFunction() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_MessageType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_OutputMode() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientAddress() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientLogicalAddress() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientPartnerFunction() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientPartnerNumber() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientPartnerType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_RecipientPort() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderAddress() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderLogicalAddress() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderPartnerFunction() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderPartnerNumber() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderPartnerType() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_SenderPort() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_Serialization() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_Status() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocument_TestFlag() {
		return (EAttribute)documentEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocument_RootSegment() {
		return (EReference)documentEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegment() {
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_Parent() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_Document() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Description() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Type() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Definition() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_HierarchyLevel() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_IdocType() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_IdocTypeExtension() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_SystemRelease() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_ApplicationRelease() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_NumFields() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_MaxOccurrence() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_MinOccurrence() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Mandatory() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Qualified() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_RecordLength() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegmentChildren() {
		return segmentChildrenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegmentChildren_Segments() {
		return (EAttribute)segmentChildrenEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegmentChildren_Parent() {
		return (EReference)segmentChildrenEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegmentList() {
		return segmentListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdocFactory getIdocFactory() {
		return (IdocFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		documentListEClass = createEClass(DOCUMENT_LIST);
		createEAttribute(documentListEClass, DOCUMENT_LIST__IDOC_TYPE);
		createEAttribute(documentListEClass, DOCUMENT_LIST__IDOC_TYPE_EXTENSION);
		createEAttribute(documentListEClass, DOCUMENT_LIST__SYSTEM_RELEASE);
		createEAttribute(documentListEClass, DOCUMENT_LIST__APPLICATION_RELEASE);
		createEReference(documentListEClass, DOCUMENT_LIST__DOCUMENT);

		documentEClass = createEClass(DOCUMENT);
		createEAttribute(documentEClass, DOCUMENT__ARCHIVE_KEY);
		createEAttribute(documentEClass, DOCUMENT__CLIENT);
		createEAttribute(documentEClass, DOCUMENT__CREATION_DATE);
		createEAttribute(documentEClass, DOCUMENT__CREATION_TIME);
		createEAttribute(documentEClass, DOCUMENT__DIRECTION);
		createEAttribute(documentEClass, DOCUMENT__EDI_MESSAGE);
		createEAttribute(documentEClass, DOCUMENT__EDI_MESSAGE_GROUP);
		createEAttribute(documentEClass, DOCUMENT__EDI_MESSAGE_TYPE);
		createEAttribute(documentEClass, DOCUMENT__EDI_STANDARD_FLAG);
		createEAttribute(documentEClass, DOCUMENT__EDI_STANDARD_VERSION);
		createEAttribute(documentEClass, DOCUMENT__EDI_TRANSMISSION_FILE);
		createEAttribute(documentEClass, DOCUMENT__IDOC_COMPOUND_TYPE);
		createEAttribute(documentEClass, DOCUMENT__IDOC_NUMBER);
		createEAttribute(documentEClass, DOCUMENT__IDOC_SAP_RELEASE);
		createEAttribute(documentEClass, DOCUMENT__IDOC_TYPE);
		createEAttribute(documentEClass, DOCUMENT__IDOC_TYPE_EXTENSION);
		createEAttribute(documentEClass, DOCUMENT__MESSAGE_CODE);
		createEAttribute(documentEClass, DOCUMENT__MESSAGE_FUNCTION);
		createEAttribute(documentEClass, DOCUMENT__MESSAGE_TYPE);
		createEAttribute(documentEClass, DOCUMENT__OUTPUT_MODE);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_ADDRESS);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_LOGICAL_ADDRESS);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_PARTNER_FUNCTION);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_PARTNER_NUMBER);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_PARTNER_TYPE);
		createEAttribute(documentEClass, DOCUMENT__RECIPIENT_PORT);
		createEAttribute(documentEClass, DOCUMENT__SENDER_ADDRESS);
		createEAttribute(documentEClass, DOCUMENT__SENDER_LOGICAL_ADDRESS);
		createEAttribute(documentEClass, DOCUMENT__SENDER_PARTNER_FUNCTION);
		createEAttribute(documentEClass, DOCUMENT__SENDER_PARTNER_NUMBER);
		createEAttribute(documentEClass, DOCUMENT__SENDER_PARTNER_TYPE);
		createEAttribute(documentEClass, DOCUMENT__SENDER_PORT);
		createEAttribute(documentEClass, DOCUMENT__SERIALIZATION);
		createEAttribute(documentEClass, DOCUMENT__STATUS);
		createEAttribute(documentEClass, DOCUMENT__TEST_FLAG);
		createEReference(documentEClass, DOCUMENT__ROOT_SEGMENT);

		segmentEClass = createEClass(SEGMENT);
		createEReference(segmentEClass, SEGMENT__PARENT);
		createEReference(segmentEClass, SEGMENT__DOCUMENT);
		createEAttribute(segmentEClass, SEGMENT__DESCRIPTION);
		createEAttribute(segmentEClass, SEGMENT__TYPE);
		createEAttribute(segmentEClass, SEGMENT__DEFINITION);
		createEAttribute(segmentEClass, SEGMENT__HIERARCHY_LEVEL);
		createEAttribute(segmentEClass, SEGMENT__IDOC_TYPE);
		createEAttribute(segmentEClass, SEGMENT__IDOC_TYPE_EXTENSION);
		createEAttribute(segmentEClass, SEGMENT__SYSTEM_RELEASE);
		createEAttribute(segmentEClass, SEGMENT__APPLICATION_RELEASE);
		createEAttribute(segmentEClass, SEGMENT__NUM_FIELDS);
		createEAttribute(segmentEClass, SEGMENT__MAX_OCCURRENCE);
		createEAttribute(segmentEClass, SEGMENT__MIN_OCCURRENCE);
		createEAttribute(segmentEClass, SEGMENT__MANDATORY);
		createEAttribute(segmentEClass, SEGMENT__QUALIFIED);
		createEAttribute(segmentEClass, SEGMENT__RECORD_LENGTH);

		segmentChildrenEClass = createEClass(SEGMENT_CHILDREN);
		createEAttribute(segmentChildrenEClass, SEGMENT_CHILDREN__SEGMENTS);
		createEReference(segmentChildrenEClass, SEGMENT_CHILDREN__PARENT);

		segmentListEClass = createEClass(SEGMENT_LIST);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		ETypeParameter segmentListEClass_S = addETypeParameter(segmentListEClass, "S");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getSegment());
		segmentListEClass_S.getEBounds().add(g1);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(documentListEClass, DocumentList.class, "DocumentList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentList_IdocType(), ecorePackage.getEString(), "idocType", "", 0, 1, DocumentList.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentList_IdocTypeExtension(), ecorePackage.getEString(), "idocTypeExtension", "", 0, 1, DocumentList.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentList_SystemRelease(), ecorePackage.getEString(), "systemRelease", "", 0, 1, DocumentList.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentList_ApplicationRelease(), ecorePackage.getEString(), "applicationRelease", "", 0, 1, DocumentList.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentList_Document(), this.getDocument(), null, "document", null, 0, -1, DocumentList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(documentListEClass, this.getDocument(), "add", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(documentListEClass, this.getDocument(), "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "index", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocument_ArchiveKey(), ecorePackage.getEString(), "archiveKey", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_Client(), ecorePackage.getEString(), "client", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_CreationDate(), ecorePackage.getEDate(), "creationDate", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_CreationTime(), ecorePackage.getEDate(), "creationTime", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_Direction(), ecorePackage.getEString(), "direction", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDIMessage(), ecorePackage.getEString(), "EDIMessage", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDIMessageGroup(), ecorePackage.getEString(), "EDIMessageGroup", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDIMessageType(), ecorePackage.getEString(), "EDIMessageType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDIStandardFlag(), ecorePackage.getEString(), "EDIStandardFlag", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDIStandardVersion(), ecorePackage.getEString(), "EDIStandardVersion", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_EDITransmissionFile(), ecorePackage.getEString(), "EDITransmissionFile", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_IDocCompoundType(), ecorePackage.getEString(), "iDocCompoundType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_IDocNumber(), ecorePackage.getEString(), "iDocNumber", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_IDocSAPRelease(), ecorePackage.getEString(), "iDocSAPRelease", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_IDocType(), ecorePackage.getEString(), "iDocType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_IDocTypeExtension(), ecorePackage.getEString(), "iDocTypeExtension", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_MessageCode(), ecorePackage.getEString(), "messageCode", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_MessageFunction(), ecorePackage.getEString(), "messageFunction", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_MessageType(), ecorePackage.getEString(), "messageType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_OutputMode(), ecorePackage.getEString(), "outputMode", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientAddress(), ecorePackage.getEString(), "recipientAddress", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientLogicalAddress(), ecorePackage.getEString(), "recipientLogicalAddress", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientPartnerFunction(), ecorePackage.getEString(), "recipientPartnerFunction", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientPartnerNumber(), ecorePackage.getEString(), "recipientPartnerNumber", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientPartnerType(), ecorePackage.getEString(), "recipientPartnerType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_RecipientPort(), ecorePackage.getEString(), "recipientPort", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderAddress(), ecorePackage.getEString(), "senderAddress", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderLogicalAddress(), ecorePackage.getEString(), "senderLogicalAddress", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderPartnerFunction(), ecorePackage.getEString(), "senderPartnerFunction", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderPartnerNumber(), ecorePackage.getEString(), "senderPartnerNumber", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderPartnerType(), ecorePackage.getEString(), "senderPartnerType", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_SenderPort(), ecorePackage.getEString(), "senderPort", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_Serialization(), ecorePackage.getEString(), "serialization", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_Status(), ecorePackage.getEString(), "status", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_TestFlag(), ecorePackage.getEString(), "testFlag", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocument_RootSegment(), this.getSegment(), null, "rootSegment", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(segmentEClass, Segment.class, "Segment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSegment_Parent(), this.getSegment(), null, "parent", null, 0, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSegment_Document(), this.getDocument(), null, "document", null, 0, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Description(), ecorePackage.getEString(), "description", null, 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Type(), ecorePackage.getEString(), "type", null, 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Definition(), ecorePackage.getEString(), "definition", null, 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_HierarchyLevel(), ecorePackage.getEInt(), "hierarchyLevel", null, 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_IdocType(), ecorePackage.getEString(), "idocType", "", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_IdocTypeExtension(), ecorePackage.getEString(), "idocTypeExtension", "", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_SystemRelease(), ecorePackage.getEString(), "systemRelease", "", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_ApplicationRelease(), ecorePackage.getEString(), "applicationRelease", "", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_NumFields(), ecorePackage.getEInt(), "numFields", "0", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_MaxOccurrence(), ecorePackage.getELong(), "maxOccurrence", "0", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_MinOccurrence(), ecorePackage.getELong(), "minOccurrence", "0", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Mandatory(), ecorePackage.getEBoolean(), "mandatory", "false", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Qualified(), ecorePackage.getEBoolean(), "qualified", "false", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_RecordLength(), ecorePackage.getEInt(), "recordLength", "0", 0, 1, Segment.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(segmentEClass, null, "get", 0, 1, IS_UNIQUE, IS_ORDERED);
		ETypeParameter t1 = addETypeParameter(op, "T");
		addEParameter(op, ecorePackage.getEJavaObject(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "type", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(segmentEClass, null, "getChildren", 0, -1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "S");
		g1 = createEGenericType(this.getSegment());
		t1.getEBounds().add(g1);
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = addEOperation(segmentEClass, null, "getChildren", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "S");
		g1 = createEGenericType(this.getSegment());
		t1.getEBounds().add(g1);
		addEParameter(op, ecorePackage.getEString(), "segmentType", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getSegmentList());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		addEOperation(segmentEClass, ecorePackage.getEString(), "getTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(segmentChildrenEClass, SegmentChildren.class, "SegmentChildren", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSegmentChildren_Segments(), ecorePackage.getEFeatureMapEntry(), "segments", null, 0, -1, SegmentChildren.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSegmentChildren_Parent(), this.getSegment(), null, "parent", null, 0, 1, SegmentChildren.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(segmentChildrenEClass, null, "get", 0, 1, IS_UNIQUE, IS_ORDERED);
		t1 = addETypeParameter(op, "S");
		g1 = createEGenericType(this.getSegment());
		t1.getEBounds().add(g1);
		addEParameter(op, ecorePackage.getEString(), "segmentType", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getSegmentList());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		addEOperation(segmentChildrenEClass, ecorePackage.getEString(), "getTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(segmentListEClass, SegmentList.class, "SegmentList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(segmentListEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(segmentListEClass_S);
		initEOperation(op, g1);

		op = addEOperation(segmentListEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "index", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(segmentListEClass_S);
		initEOperation(op, g1);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (getSegmentChildren_Segments(), 
		   source, 
		   new String[] {
			 "kind", "group"
		   });
	}

} //IdocPackageImpl
