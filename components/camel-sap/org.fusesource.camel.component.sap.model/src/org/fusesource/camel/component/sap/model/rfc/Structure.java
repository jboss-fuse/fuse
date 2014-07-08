/**
 * Copyright 2013 Red Hat, Inc.
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
 *  implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap.model.rfc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structure</b></em>'.
 * @extends java.util.Map<String,Object>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.Structure#getName <em>Name</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.Structure#getFieldCount <em>Field Count</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.Structure#getRecordLength <em>Record Length</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.Structure#getUnicodeRecordLength <em>Unicode Record Length</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.Structure#isNestedType1Structure <em>Nested Type1 Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure()
 * @model
 * @generated
 */
public interface Structure extends EObject, java.util.Map<String, Object> {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure_Name()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Field Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Count</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure_FieldCount()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getFieldCount();

	/**
	 * Returns the value of the '<em><b>Record Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Record Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Record Length</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure_RecordLength()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getRecordLength();

	/**
	 * Returns the value of the '<em><b>Unicode Record Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unicode Record Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unicode Record Length</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure_UnicodeRecordLength()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	int getUnicodeRecordLength();

	/**
	 * Returns the value of the '<em><b>Nested Type1 Structure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Type1 Structure</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested Type1 Structure</em>' attribute.
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getStructure_NestedType1Structure()
	 * @model transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	boolean isNestedType1Structure();

	/**
	 * <!-- begin-user-doc -->
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this {@link Structure} contains no mapping for the key.
	 * @param key - the key whose associated value is to be returned
	 * @param type - the type of required value
	 * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	<T> T get(Object key, Class<T> type);
} // Structure
