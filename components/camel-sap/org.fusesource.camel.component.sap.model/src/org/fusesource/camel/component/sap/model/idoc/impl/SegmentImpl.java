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

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.SegmentChildren;
import org.fusesource.camel.component.sap.model.idoc.SegmentList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getDocument <em>Document</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getHierarchyLevel <em>Hierarchy Level</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getIdocType <em>Idoc Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getIdocTypeExtension <em>Idoc Type Extension</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getSystemRelease <em>System Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getApplicationRelease <em>Application Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getNumFields <em>Num Fields</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getMaxOccurrence <em>Max Occurrence</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getMinOccurrence <em>Min Occurrence</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#isQualified <em>Qualified</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl#getRecordLength <em>Record Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SegmentImpl extends EObjectImpl implements Segment {
	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected Segment parent;

	/**
	 * The cached value of the '{@link #getDocument() <em>Document</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocument()
	 * @generated
	 * @ordered
	 */
	protected Document document;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getHierarchyLevel() <em>Hierarchy Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHierarchyLevel()
	 * @generated NOT
	 * @ordered
	 */
	protected static final int HIERARCHY_LEVEL_EDEFAULT = -1;

	/**
	 * The default value of the '{@link #getIdocType() <em>Idoc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdocType()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_TYPE_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getIdocTypeExtension() <em>Idoc Type Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdocTypeExtension()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_TYPE_EXTENSION_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getSystemRelease() <em>System Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String SYSTEM_RELEASE_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getApplicationRelease() <em>Application Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String APPLICATION_RELEASE_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getNumFields() <em>Num Fields</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumFields()
	 * @generated
	 * @ordered
	 */
	protected static final int NUM_FIELDS_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getMaxOccurrence() <em>Max Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxOccurrence()
	 * @generated
	 * @ordered
	 */
	protected static final long MAX_OCCURRENCE_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #getMinOccurrence() <em>Min Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinOccurrence()
	 * @generated
	 * @ordered
	 */
	protected static final long MIN_OCCURRENCE_EDEFAULT = 0L;

	/**
	 * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isQualified() <em>Qualified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQualified()
	 * @generated
	 * @ordered
	 */
	protected static final boolean QUALIFIED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getRecordLength() <em>Record Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecordLength()
	 * @generated
	 * @ordered
	 */
	protected static final int RECORD_LENGTH_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected SegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdocPackage.Literals.SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (Segment)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdocPackage.SEGMENT__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(Segment newParent) {
		Segment oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.SEGMENT__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public <S extends Segment> EList<S> getChildren() {
		SegmentChildren segmentChildren = getSegmentChildren();
		FeatureMap segments = segmentChildren.getSegments();
		EList<S> list = new BasicEList<S>();
		for (FeatureMap.Entry entry: segments) {
			list.add((S) entry.getValue());
		}
		return ECollections.unmodifiableEList(list);
	}
	
	/**
	 * Returns the Segment Children of the {@link Segment}.
	 * 
	 * @return The the Segment Children of the {@link Segment}.
	 * @generated NOT
	 */
	public SegmentChildren getSegmentChildren() {
		EStructuralFeature feature = eClass().getEStructuralFeature("segmentChildren");
		if (feature == null) {
			return null;
		}
		Object value = eGet(feature);
		if (value == null) {
			EClass eClass = ((EReference) feature).getEReferenceType();
			value = eClass.getEPackage().getEFactoryInstance().create(eClass);
			((SegmentChildrenImpl)value).setParent(this);
			eSet(feature, value);
		}
		return (SegmentChildren) value;
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public <S extends Segment> SegmentList<S> getChildren(String segmentType) {
		SegmentChildren segmentChildren = getSegmentChildren();
		return segmentChildren.get(segmentType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<String> getTypes() {
		return getSegmentChildren().getTypes();	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Document getDocument() {
		if (document != null && document.eIsProxy()) {
			InternalEObject oldDocument = (InternalEObject)document;
			document = (Document)eResolveProxy(oldDocument);
			if (document != oldDocument) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IdocPackage.SEGMENT__DOCUMENT, oldDocument, document));
			}
		}
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Document basicGetDocument() {
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocument(Document newDocument) {
		Document oldDocument = document;
		document = newDocument;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.SEGMENT__DOCUMENT, oldDocument, document));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated  NOT
	 */
	public String getDescription() {
		String description = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			description = idocAnnotation.getDetails().get("description");
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated  NOT
	 */
	public void setDescription(String newDescription) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated  NOT
	 */
	public String getType() {
		String type = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			type = idocAnnotation.getDetails().get("type");
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated  NOT
	 */
	public void setType(String newType) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getDefinition() {
		String definition = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			definition = idocAnnotation.getDetails().get("definition");
		}
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated  NOT
	 */
	public void setDefinition(String newDefinition) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getHierarchyLevel() {
		int hierarchyLevel = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String hierarchyLevelString = idocAnnotation.getDetails().get("hierarchyLevel");
			try {
				hierarchyLevel = Integer.parseInt(hierarchyLevelString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return hierarchyLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setHierarchyLevel(int newHierarchyLevel) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getIdocType() {
		String idocType = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			idocType = idocAnnotation.getDetails().get("idocType");
		}
		return idocType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setIdocType(String newIdocType) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getIdocTypeExtension() {
		String idocTypeExtension = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			idocTypeExtension = idocAnnotation.getDetails().get("idocTypeExtension");
		}
		return idocTypeExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setIdocTypeExtension(String newIdocTypeExtension) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getSystemRelease() {
		String systemRelease = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			systemRelease = idocAnnotation.getDetails().get("systemRelease");
		}
		return systemRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSystemRelease(String newSystemRelease) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getApplicationRelease() {
		String applicationRelease = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			applicationRelease = idocAnnotation.getDetails().get("applicationRelease");
		}
		return applicationRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setApplicationRelease(String newApplicationRelease) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getNumFields() {
		int numFields = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String numFieldsString = idocAnnotation.getDetails().get("numFields");
			try {
				numFields = Integer.parseInt(numFieldsString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return numFields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setNumFields(int newNumFields) {
		// NOOP	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public long getMaxOccurrence() {
		long maxOccurrence = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String maxOccurrenceString = idocAnnotation.getDetails().get("maxOccurrence");
			try {
				maxOccurrence = Long.parseLong(maxOccurrenceString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return maxOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setMaxOccurrence(long newMaxOccurrence) { 
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public long getMinOccurrence() {
		long minOccurrence = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String minOccurrenceString = idocAnnotation.getDetails().get("minOccurrence");
			try {
				minOccurrence = Long.parseLong(minOccurrenceString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return minOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setMinOccurrence(long newMinOccurrence) {
		// NOOP	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isMandatory() {
		boolean isMandatory = false;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String isManadatoryString = idocAnnotation.getDetails().get("isMandatory");
			try {
				isMandatory = Boolean.parseBoolean(isManadatoryString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return isMandatory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setMandatory(boolean newMandatory) {
		// NOOP	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isQualified() {
		boolean isQualified = false;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String isQualifiedString = idocAnnotation.getDetails().get("isQualified");
			try {
				isQualified = Boolean.parseBoolean(isQualifiedString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return isQualified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setQualified(boolean newQualified) {
		// NOOP	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getRecordLength() {
		int recordLength = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(IdocPackage.eNS_URI);
		if (idocAnnotation != null) {
			String recordLengthString = idocAnnotation.getDetails().get("recordLength");
			try {
				recordLength = Integer.parseInt(recordLengthString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return recordLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setRecordLength(int newRecordLength) {
		// NOOP	
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdocPackage.SEGMENT__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case IdocPackage.SEGMENT__DOCUMENT:
				if (resolve) return getDocument();
				return basicGetDocument();
			case IdocPackage.SEGMENT__DESCRIPTION:
				return getDescription();
			case IdocPackage.SEGMENT__TYPE:
				return getType();
			case IdocPackage.SEGMENT__DEFINITION:
				return getDefinition();
			case IdocPackage.SEGMENT__HIERARCHY_LEVEL:
				return getHierarchyLevel();
			case IdocPackage.SEGMENT__IDOC_TYPE:
				return getIdocType();
			case IdocPackage.SEGMENT__IDOC_TYPE_EXTENSION:
				return getIdocTypeExtension();
			case IdocPackage.SEGMENT__SYSTEM_RELEASE:
				return getSystemRelease();
			case IdocPackage.SEGMENT__APPLICATION_RELEASE:
				return getApplicationRelease();
			case IdocPackage.SEGMENT__NUM_FIELDS:
				return getNumFields();
			case IdocPackage.SEGMENT__MAX_OCCURRENCE:
				return getMaxOccurrence();
			case IdocPackage.SEGMENT__MIN_OCCURRENCE:
				return getMinOccurrence();
			case IdocPackage.SEGMENT__MANDATORY:
				return isMandatory();
			case IdocPackage.SEGMENT__QUALIFIED:
				return isQualified();
			case IdocPackage.SEGMENT__RECORD_LENGTH:
				return getRecordLength();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IdocPackage.SEGMENT__PARENT:
				setParent((Segment)newValue);
				return;
			case IdocPackage.SEGMENT__DOCUMENT:
				setDocument((Document)newValue);
				return;
			case IdocPackage.SEGMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case IdocPackage.SEGMENT__TYPE:
				setType((String)newValue);
				return;
			case IdocPackage.SEGMENT__DEFINITION:
				setDefinition((String)newValue);
				return;
			case IdocPackage.SEGMENT__HIERARCHY_LEVEL:
				setHierarchyLevel((Integer)newValue);
				return;
			case IdocPackage.SEGMENT__IDOC_TYPE:
				setIdocType((String)newValue);
				return;
			case IdocPackage.SEGMENT__IDOC_TYPE_EXTENSION:
				setIdocTypeExtension((String)newValue);
				return;
			case IdocPackage.SEGMENT__SYSTEM_RELEASE:
				setSystemRelease((String)newValue);
				return;
			case IdocPackage.SEGMENT__APPLICATION_RELEASE:
				setApplicationRelease((String)newValue);
				return;
			case IdocPackage.SEGMENT__NUM_FIELDS:
				setNumFields((Integer)newValue);
				return;
			case IdocPackage.SEGMENT__MAX_OCCURRENCE:
				setMaxOccurrence((Long)newValue);
				return;
			case IdocPackage.SEGMENT__MIN_OCCURRENCE:
				setMinOccurrence((Long)newValue);
				return;
			case IdocPackage.SEGMENT__MANDATORY:
				setMandatory((Boolean)newValue);
				return;
			case IdocPackage.SEGMENT__QUALIFIED:
				setQualified((Boolean)newValue);
				return;
			case IdocPackage.SEGMENT__RECORD_LENGTH:
				setRecordLength((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IdocPackage.SEGMENT__PARENT:
				setParent((Segment)null);
				return;
			case IdocPackage.SEGMENT__DOCUMENT:
				setDocument((Document)null);
				return;
			case IdocPackage.SEGMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__HIERARCHY_LEVEL:
				setHierarchyLevel(HIERARCHY_LEVEL_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__IDOC_TYPE:
				setIdocType(IDOC_TYPE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__IDOC_TYPE_EXTENSION:
				setIdocTypeExtension(IDOC_TYPE_EXTENSION_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__SYSTEM_RELEASE:
				setSystemRelease(SYSTEM_RELEASE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__APPLICATION_RELEASE:
				setApplicationRelease(APPLICATION_RELEASE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__NUM_FIELDS:
				setNumFields(NUM_FIELDS_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__MAX_OCCURRENCE:
				setMaxOccurrence(MAX_OCCURRENCE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__MIN_OCCURRENCE:
				setMinOccurrence(MIN_OCCURRENCE_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__MANDATORY:
				setMandatory(MANDATORY_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__QUALIFIED:
				setQualified(QUALIFIED_EDEFAULT);
				return;
			case IdocPackage.SEGMENT__RECORD_LENGTH:
				setRecordLength(RECORD_LENGTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IdocPackage.SEGMENT__PARENT:
				return parent != null;
			case IdocPackage.SEGMENT__DOCUMENT:
				return document != null;
			case IdocPackage.SEGMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case IdocPackage.SEGMENT__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case IdocPackage.SEGMENT__DEFINITION:
				return DEFINITION_EDEFAULT == null ? getDefinition() != null : !DEFINITION_EDEFAULT.equals(getDefinition());
			case IdocPackage.SEGMENT__HIERARCHY_LEVEL:
				return getHierarchyLevel() != HIERARCHY_LEVEL_EDEFAULT;
			case IdocPackage.SEGMENT__IDOC_TYPE:
				return IDOC_TYPE_EDEFAULT == null ? getIdocType() != null : !IDOC_TYPE_EDEFAULT.equals(getIdocType());
			case IdocPackage.SEGMENT__IDOC_TYPE_EXTENSION:
				return IDOC_TYPE_EXTENSION_EDEFAULT == null ? getIdocTypeExtension() != null : !IDOC_TYPE_EXTENSION_EDEFAULT.equals(getIdocTypeExtension());
			case IdocPackage.SEGMENT__SYSTEM_RELEASE:
				return SYSTEM_RELEASE_EDEFAULT == null ? getSystemRelease() != null : !SYSTEM_RELEASE_EDEFAULT.equals(getSystemRelease());
			case IdocPackage.SEGMENT__APPLICATION_RELEASE:
				return APPLICATION_RELEASE_EDEFAULT == null ? getApplicationRelease() != null : !APPLICATION_RELEASE_EDEFAULT.equals(getApplicationRelease());
			case IdocPackage.SEGMENT__NUM_FIELDS:
				return getNumFields() != NUM_FIELDS_EDEFAULT;
			case IdocPackage.SEGMENT__MAX_OCCURRENCE:
				return getMaxOccurrence() != MAX_OCCURRENCE_EDEFAULT;
			case IdocPackage.SEGMENT__MIN_OCCURRENCE:
				return getMinOccurrence() != MIN_OCCURRENCE_EDEFAULT;
			case IdocPackage.SEGMENT__MANDATORY:
				return isMandatory() != MANDATORY_EDEFAULT;
			case IdocPackage.SEGMENT__QUALIFIED:
				return isQualified() != QUALIFIED_EDEFAULT;
			case IdocPackage.SEGMENT__RECORD_LENGTH:
				return getRecordLength() != RECORD_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public <T> T get(Object key, Class<T> type) {
		Object value = get(key);
		if (value == null) {
			return null;
		}
		if (type.isInstance(value)) {
			return type.cast(value);
		} else {
			throw new IllegalArgumentException("The value is not of type: " + type + " but is : " + value.getClass().getCanonicalName());
		}
	}

	public static class Entry implements Map.Entry<String,Object> {
		
		private String key;
		private Object value;
		
		Entry(String key, Object value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public Object setValue(Object newValue) {
            Object oldValue = value;
            value = newValue;
            return oldValue;
		}
		
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            @SuppressWarnings("unchecked")
			Map.Entry<Object,Object> e = (Map.Entry<Object,Object>)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }
        
        public final int hashCode() {
            return (key==null   ? 0 : key.hashCode()) ^
                   (value==null ? 0 : value.hashCode());
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
	}
	
	private abstract class FeatureIterator<E> implements Iterator<E> {
		
		private final Iterator<EStructuralFeature> iterator;
		private EStructuralFeature current;
		
		public FeatureIterator() {
			iterator = getFilteredFeatureMap().values().iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		EStructuralFeature nextEntry() {
			current = iterator.next(); 
			return current;
		}

		@Override
		public void remove() {
			if (current == null) 
				throw new IllegalStateException();
			String key = current.getName();
			current = null;
			SegmentImpl.this.remove(key);
		}
		
	}
	
	private class ValueIterator extends FeatureIterator<Object> {
		@Override
		public Object next() {
			EStructuralFeature feature = nextEntry();
			return get(feature.getName());
		}
	}
	
	private class KeyIterator extends FeatureIterator<String> {
		@Override
		public String next() {
			return nextEntry().getName();
		}
	}
	
	private class EntryIterator extends FeatureIterator<java.util.Map.Entry<String, Object>> {
		@Override
		public Entry next() {
			EStructuralFeature feature = nextEntry();
			Object value = get(feature.getName());
			return new Entry(feature.getName(), value);
		}
	}
	
	private class EntrySet extends AbstractSet<java.util.Map.Entry<String, Object>> {

		@Override
		public Iterator<java.util.Map.Entry<String, Object>> iterator() {
			return new EntryIterator();
		}
		
		@Override
		public boolean contains(Object o) {
            if (!(o instanceof Entry))
                return false;
            Entry e = (Entry)o;
            return SegmentImpl.this.containsKey(e.getKey());
		}
		
		@Override
		public boolean remove(Object o) {
            if (!(o instanceof Entry))
                return false;
            Entry e = (Entry)o;
            Object value = SegmentImpl.this.remove(e.getKey());
            return value != null; 
		}

		@Override
		public int size() {
			return SegmentImpl.this.size();
		}
		
		@Override
		public void clear() {
			SegmentImpl.this.clear();
		}
		
	}

	private class KeySet extends AbstractSet<String> {

		@Override
		public Iterator<String> iterator() {
			return new KeyIterator();
		}

		@Override
		public int size() {
			return SegmentImpl.this.size();
		}
		
		@Override
		public boolean contains(Object key) {
			return containsKey(key);
		}
		
		@Override
		public boolean remove(Object key) {
			if (containsKey(key)) {
				SegmentImpl.this.remove(key);
				return true;
			}
			else
				return false;
		}
		
		@Override
		public void clear() {
			SegmentImpl.this.clear();
		}
		
	}

    private class Values extends AbstractCollection<Object> {

		@Override
		public Iterator<Object> iterator() {
			return new ValueIterator();
		}

		@Override
		public int size() {
			return SegmentImpl.this.size();
		}
		
		@Override
		public boolean contains(Object o) {
            return containsValue(o);
		}
		
		@Override
		public void clear() {
			SegmentImpl.this.clear();
		}
    }	
    
	private transient volatile EntrySet entrySet = null;
	
	private transient volatile KeySet keySet = null;
	
	private transient volatile Values values = null;
	
	private transient volatile Map<String,EStructuralFeature> filteredFeatureMap;
	
	@Override
	public int size() {
		return getFilteredFeatureMap().size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof String)) 
			return false;
		String featureName = (String) key;
		return getFilteredFeatureMap().get(featureName) == null ? false : true;
	}

	@Override
	public boolean containsValue(Object value) {
		for(EStructuralFeature eFeature: getFilteredFeatureMap().values()) {
			if (eGet(eFeature).equals(value))
				return true;
		}
		return false;
	}

	@Override
	public Object get(Object key) {
		if (!(key instanceof String)) 
			return null;
		String featureName = (String) key;
		EStructuralFeature eFeature = getFilteredFeatureMap().get(featureName);
		if (eFeature == null)
			return null;
		Object value = eGet(eFeature);
		return value;
	}

	@Override
	public Object put(String key, Object value) {
		if (key == null) 
			throw new NullPointerException("Key can not be null");
		EStructuralFeature eFeature = getFilteredFeatureMap().get(key);
		if (eFeature == null)
			throw new IllegalArgumentException("Key '" + key + "' is not valid for this segment");
		Object returnValue = eGet(eFeature);
		eSet(eFeature, value);
		return returnValue;
	}

	@Override
	public Object remove(Object key) {
		if (!(key instanceof String)) 
			return null;
		String featureName = (String) key;
		EStructuralFeature eFeature = getFilteredFeatureMap().get(featureName);
		if (eFeature == null)
			return null;
		Object returnValue = eGet(eFeature);
		eUnset(eFeature);
		return returnValue;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for(String key: m.keySet()) {
			Object value = m.get(key);
			put(key, value);
		}
	}

	@Override
	public void clear() {
		for(EStructuralFeature eFeature: getFilteredFeatureMap().values()) {
			eUnset(eFeature);
		}
	}

	@Override
	public Set<String> keySet() {
		KeySet ks = keySet;
		return (ks != null ? ks : (keySet = new KeySet()));
	}

	@Override
	public Collection<Object> values() {
		Values vs = values;
		return (vs != null ? vs : (values = new Values()));
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		EntrySet es = entrySet;
		return (es != null ? es : (entrySet = new EntrySet()));
	}
	
	private void initFilteredFeatures() {
		Map<String, EStructuralFeature> map = new LinkedHashMap<String, EStructuralFeature>();
		for ( EStructuralFeature feature: eClass().getEStructuralFeatures()) {
			String key = feature.getName();
			if ("segmentChildren".equals(key)) {
				continue;
			}
			EStructuralFeature duplicate = map.put(key, feature);
			if (duplicate != null) {
				System.out.println("Adding " + key);
				map.put(key, duplicate);
			}
		}
		filteredFeatureMap = map;
	}
	
	protected Map<String,EStructuralFeature> getFilteredFeatureMap() {
		if (filteredFeatureMap == null) {
			initFilteredFeatures();
		}
		return filteredFeatureMap;
	}
	
} //SegmentImpl
