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
 * A representation of the model object '<em><b>Document List</b></em>'.
 * @extends EList<Document>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocType <em>Idoc Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getIdocTypeExtension <em>Idoc Type Extension</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getSystemRelease <em>System Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getApplicationRelease <em>Application Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.DocumentList#getDocuments <em>Documents</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocumentList()
 * @model
 * @generated NOT
 */
public interface DocumentList extends EObject, EList<Document> {
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
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocumentList_IdocType()
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
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocumentList_IdocTypeExtension()
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
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocumentList_SystemRelease()
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
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocumentList_ApplicationRelease()
	 * @model default="" transient="true" volatile="true" derived="true" suppressedSetVisibility="true"
	 * @generated
	 */
	String getApplicationRelease();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Document add();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Document add(int index);

} // DocumentList
