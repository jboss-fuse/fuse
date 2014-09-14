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
package org.fusesource.camel.component.sap.model.rfc.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.SapConnectionConfiguration;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sap Connection Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.SapConnectionConfigurationImpl#getDestinationDataStore <em>Destination Data Store</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.SapConnectionConfigurationImpl#getServerDataStore <em>Server Data Store</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SapConnectionConfigurationImpl extends EObjectImpl implements SapConnectionConfiguration {
	/**
	 * The cached value of the '{@link #getDestinationDataStore() <em>Destination Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestinationDataStore()
	 * @generated
	 * @ordered
	 */
	protected DestinationDataStore destinationDataStore;

	/**
	 * The cached value of the '{@link #getServerDataStore() <em>Server Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerDataStore()
	 * @generated
	 * @ordered
	 */
	protected ServerDataStore serverDataStore;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected SapConnectionConfigurationImpl() {
		super();
		setDestinationDataStore(RfcFactory.eINSTANCE.createDestinationDataStore());
		setServerDataStore(RfcFactory.eINSTANCE.createServerDataStore());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RfcPackage.Literals.SAP_CONNECTION_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DestinationDataStore getDestinationDataStore() {
		return destinationDataStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDestinationDataStore(DestinationDataStore newDestinationDataStore, NotificationChain msgs) {
		DestinationDataStore oldDestinationDataStore = destinationDataStore;
		destinationDataStore = newDestinationDataStore;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE, oldDestinationDataStore, newDestinationDataStore);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestinationDataStore(DestinationDataStore newDestinationDataStore) {
		if (newDestinationDataStore != destinationDataStore) {
			NotificationChain msgs = null;
			if (destinationDataStore != null)
				msgs = ((InternalEObject)destinationDataStore).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE, null, msgs);
			if (newDestinationDataStore != null)
				msgs = ((InternalEObject)newDestinationDataStore).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE, null, msgs);
			msgs = basicSetDestinationDataStore(newDestinationDataStore, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE, newDestinationDataStore, newDestinationDataStore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServerDataStore getServerDataStore() {
		return serverDataStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServerDataStore(ServerDataStore newServerDataStore, NotificationChain msgs) {
		ServerDataStore oldServerDataStore = serverDataStore;
		serverDataStore = newServerDataStore;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE, oldServerDataStore, newServerDataStore);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerDataStore(ServerDataStore newServerDataStore) {
		if (newServerDataStore != serverDataStore) {
			NotificationChain msgs = null;
			if (serverDataStore != null)
				msgs = ((InternalEObject)serverDataStore).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE, null, msgs);
			if (newServerDataStore != null)
				msgs = ((InternalEObject)newServerDataStore).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE, null, msgs);
			msgs = basicSetServerDataStore(newServerDataStore, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE, newServerDataStore, newServerDataStore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE:
				return basicSetDestinationDataStore(null, msgs);
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE:
				return basicSetServerDataStore(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE:
				return getDestinationDataStore();
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE:
				return getServerDataStore();
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
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE:
				setDestinationDataStore((DestinationDataStore)newValue);
				return;
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE:
				setServerDataStore((ServerDataStore)newValue);
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
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE:
				setDestinationDataStore((DestinationDataStore)null);
				return;
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE:
				setServerDataStore((ServerDataStore)null);
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
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__DESTINATION_DATA_STORE:
				return destinationDataStore != null;
			case RfcPackage.SAP_CONNECTION_CONFIGURATION__SERVER_DATA_STORE:
				return serverDataStore != null;
		}
		return super.eIsSet(featureID);
	}

} //SapConnectionConfigurationImpl
