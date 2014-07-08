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
package org.fusesource.camel.component.sap.model.idoc.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.fusesource.camel.component.sap.model.SAPEditPlugin;

import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.IdocFactory;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;

/**
 * This is the item provider adapter for a {@link org.fusesource.camel.component.sap.model.idoc.Document} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addArchiveKeyPropertyDescriptor(object);
			addClientPropertyDescriptor(object);
			addCreationDatePropertyDescriptor(object);
			addCreationTimePropertyDescriptor(object);
			addDirectionPropertyDescriptor(object);
			addEDIMessagePropertyDescriptor(object);
			addEDIMessageGroupPropertyDescriptor(object);
			addEDIMessageTypePropertyDescriptor(object);
			addEDIStandardFlagPropertyDescriptor(object);
			addEDIStandardVersionPropertyDescriptor(object);
			addEDITransmissionFilePropertyDescriptor(object);
			addIDocCompoundTypePropertyDescriptor(object);
			addIDocNumberPropertyDescriptor(object);
			addIDocSAPReleasePropertyDescriptor(object);
			addIDocTypePropertyDescriptor(object);
			addIDocTypeExtensionPropertyDescriptor(object);
			addMessageCodePropertyDescriptor(object);
			addMessageFunctionPropertyDescriptor(object);
			addMessageTypePropertyDescriptor(object);
			addOutputModePropertyDescriptor(object);
			addRecipientAddressPropertyDescriptor(object);
			addRecipientLogicalAddressPropertyDescriptor(object);
			addRecipientPartnerFunctionPropertyDescriptor(object);
			addRecipientPartnerNumberPropertyDescriptor(object);
			addRecipientPartnerTypePropertyDescriptor(object);
			addRecipientPortPropertyDescriptor(object);
			addSenderAddressPropertyDescriptor(object);
			addSenderLogicalAddressPropertyDescriptor(object);
			addSenderPartnerFunctionPropertyDescriptor(object);
			addSenderPartnerNumberPropertyDescriptor(object);
			addSenderPartnerTypePropertyDescriptor(object);
			addSenderPortPropertyDescriptor(object);
			addSerializationPropertyDescriptor(object);
			addStatusPropertyDescriptor(object);
			addTestFlagPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Archive Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addArchiveKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_archiveKey_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_archiveKey_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__ARCHIVE_KEY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Client feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addClientPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_client_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_client_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__CLIENT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Creation Date feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCreationDatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_creationDate_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_creationDate_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__CREATION_DATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Creation Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCreationTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_creationTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_creationTime_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__CREATION_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Direction feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDirectionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_direction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_direction_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__DIRECTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Message feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDIMessagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDIMessage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDIMessage_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_MESSAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Message Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDIMessageGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDIMessageGroup_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDIMessageGroup_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_MESSAGE_GROUP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Message Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDIMessageTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDIMessageType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDIMessageType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_MESSAGE_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Standard Flag feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDIStandardFlagPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDIStandardFlag_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDIStandardFlag_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_STANDARD_FLAG,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Standard Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDIStandardVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDIStandardVersion_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDIStandardVersion_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_STANDARD_VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the EDI Transmission File feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEDITransmissionFilePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_EDITransmissionFile_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_EDITransmissionFile_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__EDI_TRANSMISSION_FILE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the IDoc Compound Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDocCompoundTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_iDocCompoundType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_iDocCompoundType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__IDOC_COMPOUND_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the IDoc Number feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDocNumberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_iDocNumber_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_iDocNumber_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__IDOC_NUMBER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the IDoc SAP Release feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDocSAPReleasePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_iDocSAPRelease_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_iDocSAPRelease_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__IDOC_SAP_RELEASE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the IDoc Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDocTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_iDocType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_iDocType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__IDOC_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the IDoc Type Extension feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDocTypeExtensionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_iDocTypeExtension_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_iDocTypeExtension_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__IDOC_TYPE_EXTENSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Message Code feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMessageCodePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_messageCode_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_messageCode_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__MESSAGE_CODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Message Function feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMessageFunctionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_messageFunction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_messageFunction_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__MESSAGE_FUNCTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Message Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMessageTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_messageType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_messageType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__MESSAGE_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Output Mode feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutputModePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_outputMode_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_outputMode_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__OUTPUT_MODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientAddress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientAddress_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Logical Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientLogicalAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientLogicalAddress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientLogicalAddress_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Partner Function feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientPartnerFunctionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientPartnerFunction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientPartnerFunction_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_PARTNER_FUNCTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Partner Number feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientPartnerNumberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientPartnerNumber_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientPartnerNumber_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_PARTNER_NUMBER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Partner Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientPartnerTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientPartnerType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientPartnerType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_PARTNER_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recipient Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecipientPortPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_recipientPort_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_recipientPort_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__RECIPIENT_PORT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderAddress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderAddress_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Logical Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderLogicalAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderLogicalAddress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderLogicalAddress_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_LOGICAL_ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Partner Function feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderPartnerFunctionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderPartnerFunction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderPartnerFunction_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_PARTNER_FUNCTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Partner Number feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderPartnerNumberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderPartnerNumber_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderPartnerNumber_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_PARTNER_NUMBER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Partner Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderPartnerTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderPartnerType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderPartnerType_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_PARTNER_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sender Port feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSenderPortPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_senderPort_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_senderPort_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SENDER_PORT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Serialization feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSerializationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_serialization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_serialization_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__SERIALIZATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStatusPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_status_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_status_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__STATUS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Test Flag feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTestFlagPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Document_testFlag_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Document_testFlag_feature", "_UI_Document_type"),
				 IdocPackage.Literals.DOCUMENT__TEST_FLAG,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(IdocPackage.Literals.DOCUMENT__ROOT_SEGMENT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Document.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Document"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Document)object).getArchiveKey();
		return label == null || label.length() == 0 ?
			getString("_UI_Document_type") :
			getString("_UI_Document_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Document.class)) {
			case IdocPackage.DOCUMENT__ARCHIVE_KEY:
			case IdocPackage.DOCUMENT__CLIENT:
			case IdocPackage.DOCUMENT__CREATION_DATE:
			case IdocPackage.DOCUMENT__CREATION_TIME:
			case IdocPackage.DOCUMENT__DIRECTION:
			case IdocPackage.DOCUMENT__EDI_MESSAGE:
			case IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP:
			case IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE:
			case IdocPackage.DOCUMENT__EDI_STANDARD_FLAG:
			case IdocPackage.DOCUMENT__EDI_STANDARD_VERSION:
			case IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE:
			case IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE:
			case IdocPackage.DOCUMENT__IDOC_NUMBER:
			case IdocPackage.DOCUMENT__IDOC_SAP_RELEASE:
			case IdocPackage.DOCUMENT__IDOC_TYPE:
			case IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION:
			case IdocPackage.DOCUMENT__MESSAGE_CODE:
			case IdocPackage.DOCUMENT__MESSAGE_FUNCTION:
			case IdocPackage.DOCUMENT__MESSAGE_TYPE:
			case IdocPackage.DOCUMENT__OUTPUT_MODE:
			case IdocPackage.DOCUMENT__RECIPIENT_ADDRESS:
			case IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS:
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION:
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER:
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE:
			case IdocPackage.DOCUMENT__RECIPIENT_PORT:
			case IdocPackage.DOCUMENT__SENDER_ADDRESS:
			case IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS:
			case IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION:
			case IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER:
			case IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE:
			case IdocPackage.DOCUMENT__SENDER_PORT:
			case IdocPackage.DOCUMENT__SERIALIZATION:
			case IdocPackage.DOCUMENT__STATUS:
			case IdocPackage.DOCUMENT__TEST_FLAG:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(IdocPackage.Literals.DOCUMENT__ROOT_SEGMENT,
				 IdocFactory.eINSTANCE.createSegment()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SAPEditPlugin.INSTANCE;
	}

}
