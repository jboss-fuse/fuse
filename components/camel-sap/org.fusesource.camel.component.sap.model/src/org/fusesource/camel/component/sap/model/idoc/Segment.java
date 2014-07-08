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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * @extends java.util.Map<String,Object>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getParent <em>Parent</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDocument <em>Document</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDescription <em>Description</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getType <em>Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getHierarchyLevel <em>Hierarchy Level</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getIdocType <em>Idoc Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getIdocTypeExtension <em>Idoc Type Extension</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getSystemRelease <em>System Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getApplicationRelease <em>Application Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getNumFields <em>Num Fields</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getMaxOccurrence <em>Max Occurrence</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getMinOccurrence <em>Min Occurrence</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#isQualified <em>Qualified</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Segment#getRecordLength <em>Record Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends EObject, java.util.Map<String, Object> {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Parent()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	Segment getParent();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * Return a immutable list of all child segments
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	<S extends Segment> EList<S> getChildren();

	/**
	 * <!-- begin-user-doc -->
	 * Returns a list of child segments of the specified segment type. 
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	<S extends Segment> SegmentList<S> getChildren(String segmentType);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getTypes();

	/**
	 * Returns the value of the '<em><b>Document</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Document</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Document</em>' reference.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Document()
	 * @model suppressedSetVisibility="true"
	 * @generated
	 */
	Document getDocument();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Description()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getDescription();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Type()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Definition()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getDefinition();

	/**
	 * Returns the value of the '<em><b>Hierarchy Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hierarchy Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hierarchy Level</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_HierarchyLevel()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getHierarchyLevel();

	/**
	 * Returns the value of the '<em><b>Idoc Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idoc Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idoc Type</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_IdocType()
	 * @model default="" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getIdocType();

	/**
	 * Returns the value of the '<em><b>Idoc Type Extension</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idoc Type Extension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idoc Type Extension</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_IdocTypeExtension()
	 * @model default="" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getIdocTypeExtension();

	/**
	 * Returns the value of the '<em><b>System Release</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Release</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_SystemRelease()
	 * @model default="" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getSystemRelease();

	/**
	 * Returns the value of the '<em><b>Application Release</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application Release</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_ApplicationRelease()
	 * @model default="" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getApplicationRelease();

	/**
	 * Returns the value of the '<em><b>Num Fields</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Fields</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Fields</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_NumFields()
	 * @model default="0" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getNumFields();

	/**
	 * Returns the value of the '<em><b>Max Occurrence</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Occurrence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Occurrence</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_MaxOccurrence()
	 * @model default="0" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	long getMaxOccurrence();

	/**
	 * Returns the value of the '<em><b>Min Occurrence</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Occurrence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Occurrence</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_MinOccurrence()
	 * @model default="0" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	long getMinOccurrence();

	/**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Mandatory()
	 * @model default="false" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * Returns the value of the '<em><b>Qualified</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_Qualified()
	 * @model default="false" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	boolean isQualified();

	/**
	 * Returns the value of the '<em><b>Record Length</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Record Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Record Length</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getSegment_RecordLength()
	 * @model default="0" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getRecordLength();

	/**
	 * <!-- begin-user-doc -->
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this {@link Segment} contains no mapping for the key.
	 * @param key - the key whose associated value is to be returned
	 * @param type - the type of required value
	 * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	<T> T get(Object key, Class<T> type);

} // Segment
