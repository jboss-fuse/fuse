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
package org.fusesource.camel.component.sap.model.rfc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sap Connection Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.SapConnectionConfiguration#getDestinationDataStore <em>Destination Data Store</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.SapConnectionConfiguration#getServerDataStore <em>Server Data Store</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getSapConnectionConfiguration()
 * @model
 * @generated
 */
public interface SapConnectionConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Destination Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination Data Store</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination Data Store</em>' containment reference.
	 * @see #setDestinationDataStore(DestinationDataStore)
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getSapConnectionConfiguration_DestinationDataStore()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DestinationDataStore getDestinationDataStore();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.rfc.SapConnectionConfiguration#getDestinationDataStore <em>Destination Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination Data Store</em>' containment reference.
	 * @see #getDestinationDataStore()
	 * @generated
	 */
	void setDestinationDataStore(DestinationDataStore value);

	/**
	 * Returns the value of the '<em><b>Server Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Data Store</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Data Store</em>' containment reference.
	 * @see #setServerDataStore(ServerDataStore)
	 * @see org.fusesource.camel.component.sap.model.rfc.RfcPackage#getSapConnectionConfiguration_ServerDataStore()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ServerDataStore getServerDataStore();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.rfc.SapConnectionConfiguration#getServerDataStore <em>Server Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Data Store</em>' containment reference.
	 * @see #getServerDataStore()
	 * @generated
	 */
	void setServerDataStore(ServerDataStore value);

} // SapConnectionConfiguration
