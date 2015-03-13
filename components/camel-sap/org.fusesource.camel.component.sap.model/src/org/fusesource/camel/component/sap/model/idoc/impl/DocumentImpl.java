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

import java.util.Date;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getArchiveKey <em>Archive Key</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getClient <em>Client</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDIMessage <em>EDI Message</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDIMessageGroup <em>EDI Message Group</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDIMessageType <em>EDI Message Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDIStandardFlag <em>EDI Standard Flag</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDIStandardVersion <em>EDI Standard Version</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getEDITransmissionFile <em>EDI Transmission File</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getIDocCompoundType <em>IDoc Compound Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getIDocNumber <em>IDoc Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getIDocSAPRelease <em>IDoc SAP Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getIDocType <em>IDoc Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getIDocTypeExtension <em>IDoc Type Extension</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getMessageCode <em>Message Code</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getMessageFunction <em>Message Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getOutputMode <em>Output Mode</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientAddress <em>Recipient Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientLogicalAddress <em>Recipient Logical Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientPartnerFunction <em>Recipient Partner Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientPartnerNumber <em>Recipient Partner Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientPartnerType <em>Recipient Partner Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRecipientPort <em>Recipient Port</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderAddress <em>Sender Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderLogicalAddress <em>Sender Logical Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderPartnerFunction <em>Sender Partner Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderPartnerNumber <em>Sender Partner Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderPartnerType <em>Sender Partner Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSenderPort <em>Sender Port</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getSerialization <em>Serialization</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getTestFlag <em>Test Flag</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl#getRootSegment <em>Root Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentImpl extends EObjectImpl implements Document {
	
	/**
	 * The default value of the '{@link #getArchiveKey() <em>Archive Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchiveKey()
	 * @generated
	 * @ordered
	 */
	protected static final String ARCHIVE_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArchiveKey() <em>Archive Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchiveKey()
	 * @generated
	 * @ordered
	 */
	protected String archiveKey = ARCHIVE_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getClient() <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClient() <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClient()
	 * @generated
	 * @ordered
	 */
	protected String client = CLIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date CREATION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected Date creationDate = CREATION_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date CREATION_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected Date creationTime = CREATION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final String DIRECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected String direction = DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDIMessage() <em>EDI Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDIMessage() <em>EDI Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessage()
	 * @generated
	 * @ordered
	 */
	protected String ediMessage = EDI_MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDIMessageGroup() <em>EDI Message Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessageGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_MESSAGE_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDIMessageGroup() <em>EDI Message Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessageGroup()
	 * @generated
	 * @ordered
	 */
	protected String ediMessageGroup = EDI_MESSAGE_GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDIMessageType() <em>EDI Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessageType()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_MESSAGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDIMessageType() <em>EDI Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIMessageType()
	 * @generated
	 * @ordered
	 */
	protected String ediMessageType = EDI_MESSAGE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDIStandardFlag() <em>EDI Standard Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIStandardFlag()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_STANDARD_FLAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDIStandardFlag() <em>EDI Standard Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIStandardFlag()
	 * @generated
	 * @ordered
	 */
	protected String ediStandardFlag = EDI_STANDARD_FLAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDIStandardVersion() <em>EDI Standard Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIStandardVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_STANDARD_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDIStandardVersion() <em>EDI Standard Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDIStandardVersion()
	 * @generated
	 * @ordered
	 */
	protected String ediStandardVersion = EDI_STANDARD_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEDITransmissionFile() <em>EDI Transmission File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDITransmissionFile()
	 * @generated
	 * @ordered
	 */
	protected static final String EDI_TRANSMISSION_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEDITransmissionFile() <em>EDI Transmission File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEDITransmissionFile()
	 * @generated
	 * @ordered
	 */
	protected String ediTransmissionFile = EDI_TRANSMISSION_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIDocCompoundType() <em>IDoc Compound Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocCompoundType()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_COMPOUND_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIDocCompoundType() <em>IDoc Compound Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocCompoundType()
	 * @generated
	 * @ordered
	 */
	protected String iDocCompoundType = IDOC_COMPOUND_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIDocNumber() <em>IDoc Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIDocNumber() <em>IDoc Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocNumber()
	 * @generated
	 * @ordered
	 */
	protected String iDocNumber = IDOC_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getIDocSAPRelease() <em>IDoc SAP Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocSAPRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_SAP_RELEASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIDocSAPRelease() <em>IDoc SAP Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocSAPRelease()
	 * @generated
	 * @ordered
	 */
	protected String iDocSAPRelease = IDOC_SAP_RELEASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIDocType() <em>IDoc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocType()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIDocType() <em>IDoc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocType()
	 * @generated
	 * @ordered
	 */
	protected String iDocType = IDOC_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIDocTypeExtension() <em>IDoc Type Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocTypeExtension()
	 * @generated
	 * @ordered
	 */
	protected static final String IDOC_TYPE_EXTENSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIDocTypeExtension() <em>IDoc Type Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIDocTypeExtension()
	 * @generated
	 * @ordered
	 */
	protected String iDocTypeExtension = IDOC_TYPE_EXTENSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageCode() <em>Message Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageCode()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageCode() <em>Message Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageCode()
	 * @generated
	 * @ordered
	 */
	protected String messageCode = MESSAGE_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageFunction() <em>Message Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageFunction()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_FUNCTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageFunction() <em>Message Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageFunction()
	 * @generated
	 * @ordered
	 */
	protected String messageFunction = MESSAGE_FUNCTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected String messageType = MESSAGE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutputMode() <em>Output Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputMode()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputMode() <em>Output Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputMode()
	 * @generated
	 * @ordered
	 */
	protected String outputMode = OUTPUT_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientAddress() <em>Recipient Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientAddress() <em>Recipient Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientAddress()
	 * @generated
	 * @ordered
	 */
	protected String recipientAddress = RECIPIENT_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientLogicalAddress() <em>Recipient Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientLogicalAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_LOGICAL_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientLogicalAddress() <em>Recipient Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientLogicalAddress()
	 * @generated
	 * @ordered
	 */
	protected String recipientLogicalAddress = RECIPIENT_LOGICAL_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientPartnerFunction() <em>Recipient Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerFunction()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_PARTNER_FUNCTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientPartnerFunction() <em>Recipient Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerFunction()
	 * @generated
	 * @ordered
	 */
	protected String recipientPartnerFunction = RECIPIENT_PARTNER_FUNCTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientPartnerNumber() <em>Recipient Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_PARTNER_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientPartnerNumber() <em>Recipient Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerNumber()
	 * @generated
	 * @ordered
	 */
	protected String recipientPartnerNumber = RECIPIENT_PARTNER_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientPartnerType() <em>Recipient Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerType()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_PARTNER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientPartnerType() <em>Recipient Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPartnerType()
	 * @generated
	 * @ordered
	 */
	protected String recipientPartnerType = RECIPIENT_PARTNER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecipientPort() <em>Recipient Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPort()
	 * @generated
	 * @ordered
	 */
	protected static final String RECIPIENT_PORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRecipientPort() <em>Recipient Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipientPort()
	 * @generated
	 * @ordered
	 */
	protected String recipientPort = RECIPIENT_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderAddress() <em>Sender Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderAddress() <em>Sender Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderAddress()
	 * @generated
	 * @ordered
	 */
	protected String senderAddress = SENDER_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderLogicalAddress() <em>Sender Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderLogicalAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_LOGICAL_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderLogicalAddress() <em>Sender Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderLogicalAddress()
	 * @generated
	 * @ordered
	 */
	protected String senderLogicalAddress = SENDER_LOGICAL_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderPartnerFunction() <em>Sender Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerFunction()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_PARTNER_FUNCTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderPartnerFunction() <em>Sender Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerFunction()
	 * @generated
	 * @ordered
	 */
	protected String senderPartnerFunction = SENDER_PARTNER_FUNCTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderPartnerNumber() <em>Sender Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_PARTNER_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderPartnerNumber() <em>Sender Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerNumber()
	 * @generated
	 * @ordered
	 */
	protected String senderPartnerNumber = SENDER_PARTNER_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderPartnerType() <em>Sender Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerType()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_PARTNER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderPartnerType() <em>Sender Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPartnerType()
	 * @generated
	 * @ordered
	 */
	protected String senderPartnerType = SENDER_PARTNER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSenderPort() <em>Sender Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPort()
	 * @generated
	 * @ordered
	 */
	protected static final String SENDER_PORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSenderPort() <em>Sender Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenderPort()
	 * @generated
	 * @ordered
	 */
	protected String senderPort = SENDER_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSerialization() <em>Serialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerialization()
	 * @generated
	 * @ordered
	 */
	protected static final String SERIALIZATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSerialization() <em>Serialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerialization()
	 * @generated
	 * @ordered
	 */
	protected String serialization = SERIALIZATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final String STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected String status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestFlag() <em>Test Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestFlag()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_FLAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestFlag() <em>Test Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestFlag()
	 * @generated
	 * @ordered
	 */
	protected String testFlag = TEST_FLAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRootSegment() <em>Root Segment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootSegment()
	 * @generated
	 * @ordered
	 */
	protected Segment rootSegment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdocPackage.Literals.DOCUMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArchiveKey() {
		return archiveKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchiveKey(String newArchiveKey) {
		String oldArchiveKey = archiveKey;
		archiveKey = newArchiveKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__ARCHIVE_KEY, oldArchiveKey, archiveKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClient() {
		return client;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClient(String newClient) {
		String oldClient = client;
		client = newClient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__CLIENT, oldClient, client));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * 
	 * @param newCreationDate
	 * @generated 
	 */
	public void setCreationDate(Date newCreationDate) {
		Date oldCreationDate = creationDate;
		creationDate = newCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationTime(Date newCreationTime) {
		Date oldCreationTime = creationTime;
		creationTime = newCreationTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__CREATION_TIME, oldCreationTime, creationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(String newDirection) {
		String oldDirection = direction;
		direction = newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDIMessage() {
		return ediMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDIMessage(String newEDIMessage) {
		String oldEDIMessage = ediMessage;
		ediMessage = newEDIMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_MESSAGE, oldEDIMessage, ediMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDIMessageGroup() {
		return ediMessageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDIMessageGroup(String newEDIMessageGroup) {
		String oldEDIMessageGroup = ediMessageGroup;
		ediMessageGroup = newEDIMessageGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP, oldEDIMessageGroup, ediMessageGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDIMessageType() {
		return ediMessageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDIMessageType(String newEDIMessageType) {
		String oldEDIMessageType = ediMessageType;
		ediMessageType = newEDIMessageType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE, oldEDIMessageType, ediMessageType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDIStandardFlag() {
		return ediStandardFlag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDIStandardFlag(String newEDIStandardFlag) {
		String oldEDIStandardFlag = ediStandardFlag;
		ediStandardFlag = newEDIStandardFlag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_STANDARD_FLAG, oldEDIStandardFlag, ediStandardFlag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDIStandardVersion() {
		return ediStandardVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDIStandardVersion(String newEDIStandardVersion) {
		String oldEDIStandardVersion = ediStandardVersion;
		ediStandardVersion = newEDIStandardVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_STANDARD_VERSION, oldEDIStandardVersion, ediStandardVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEDITransmissionFile() {
		return ediTransmissionFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEDITransmissionFile(String newEDITransmissionFile) {
		String oldEDITransmissionFile = ediTransmissionFile;
		ediTransmissionFile = newEDITransmissionFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE, oldEDITransmissionFile, ediTransmissionFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDocCompoundType() {
		return iDocCompoundType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIDocCompoundType(String newIDocCompoundType) {
		String oldIDocCompoundType = iDocCompoundType;
		iDocCompoundType = newIDocCompoundType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE, oldIDocCompoundType, iDocCompoundType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDocNumber() {
		return iDocNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIDocNumber(String newIDocNumber) {
		String oldIDocNumber = iDocNumber;
		iDocNumber = newIDocNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__IDOC_NUMBER, oldIDocNumber, iDocNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDocSAPRelease() {
		return iDocSAPRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIDocSAPRelease(String newIDocSAPRelease) {
		String oldIDocSAPRelease = iDocSAPRelease;
		iDocSAPRelease = newIDocSAPRelease;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__IDOC_SAP_RELEASE, oldIDocSAPRelease, iDocSAPRelease));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDocType() {
		return iDocType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIDocType(String newIDocType) {
		String oldIDocType = iDocType;
		iDocType = newIDocType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__IDOC_TYPE, oldIDocType, iDocType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIDocTypeExtension() {
		return iDocTypeExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIDocTypeExtension(String newIDocTypeExtension) {
		String oldIDocTypeExtension = iDocTypeExtension;
		iDocTypeExtension = newIDocTypeExtension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION, oldIDocTypeExtension, iDocTypeExtension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageCode(String newMessageCode) {
		String oldMessageCode = messageCode;
		messageCode = newMessageCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__MESSAGE_CODE, oldMessageCode, messageCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageFunction() {
		return messageFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageFunction(String newMessageFunction) {
		String oldMessageFunction = messageFunction;
		messageFunction = newMessageFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__MESSAGE_FUNCTION, oldMessageFunction, messageFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageType(String newMessageType) {
		String oldMessageType = messageType;
		messageType = newMessageType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__MESSAGE_TYPE, oldMessageType, messageType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutputMode() {
		return outputMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputMode(String newOutputMode) {
		String oldOutputMode = outputMode;
		outputMode = newOutputMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__OUTPUT_MODE, oldOutputMode, outputMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientAddress() {
		return recipientAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientAddress(String newRecipientAddress) {
		String oldRecipientAddress = recipientAddress;
		recipientAddress = newRecipientAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_ADDRESS, oldRecipientAddress, recipientAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientLogicalAddress() {
		return recipientLogicalAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientLogicalAddress(String newRecipientLogicalAddress) {
		String oldRecipientLogicalAddress = recipientLogicalAddress;
		recipientLogicalAddress = newRecipientLogicalAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS, oldRecipientLogicalAddress, recipientLogicalAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientPartnerFunction() {
		return recipientPartnerFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientPartnerFunction(String newRecipientPartnerFunction) {
		String oldRecipientPartnerFunction = recipientPartnerFunction;
		recipientPartnerFunction = newRecipientPartnerFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION, oldRecipientPartnerFunction, recipientPartnerFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientPartnerNumber() {
		return recipientPartnerNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientPartnerNumber(String newRecipientPartnerNumber) {
		String oldRecipientPartnerNumber = recipientPartnerNumber;
		recipientPartnerNumber = newRecipientPartnerNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER, oldRecipientPartnerNumber, recipientPartnerNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientPartnerType() {
		return recipientPartnerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientPartnerType(String newRecipientPartnerType) {
		String oldRecipientPartnerType = recipientPartnerType;
		recipientPartnerType = newRecipientPartnerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE, oldRecipientPartnerType, recipientPartnerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRecipientPort() {
		return recipientPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecipientPort(String newRecipientPort) {
		String oldRecipientPort = recipientPort;
		recipientPort = newRecipientPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__RECIPIENT_PORT, oldRecipientPort, recipientPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderAddress(String newSenderAddress) {
		String oldSenderAddress = senderAddress;
		senderAddress = newSenderAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_ADDRESS, oldSenderAddress, senderAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderLogicalAddress() {
		return senderLogicalAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderLogicalAddress(String newSenderLogicalAddress) {
		String oldSenderLogicalAddress = senderLogicalAddress;
		senderLogicalAddress = newSenderLogicalAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS, oldSenderLogicalAddress, senderLogicalAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderPartnerFunction() {
		return senderPartnerFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderPartnerFunction(String newSenderPartnerFunction) {
		String oldSenderPartnerFunction = senderPartnerFunction;
		senderPartnerFunction = newSenderPartnerFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION, oldSenderPartnerFunction, senderPartnerFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderPartnerNumber() {
		return senderPartnerNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderPartnerNumber(String newSenderPartnerNumber) {
		String oldSenderPartnerNumber = senderPartnerNumber;
		senderPartnerNumber = newSenderPartnerNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER, oldSenderPartnerNumber, senderPartnerNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderPartnerType() {
		return senderPartnerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderPartnerType(String newSenderPartnerType) {
		String oldSenderPartnerType = senderPartnerType;
		senderPartnerType = newSenderPartnerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE, oldSenderPartnerType, senderPartnerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSenderPort() {
		return senderPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSenderPort(String newSenderPort) {
		String oldSenderPort = senderPort;
		senderPort = newSenderPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SENDER_PORT, oldSenderPort, senderPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSerialization() {
		return serialization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSerialization(String newSerialization) {
		String oldSerialization = serialization;
		serialization = newSerialization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__SERIALIZATION, oldSerialization, serialization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(String newStatus) {
		String oldStatus = status;
		status = newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTestFlag() {
		return testFlag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestFlag(String newTestFlag) {
		String oldTestFlag = testFlag;
		testFlag = newTestFlag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__TEST_FLAG, oldTestFlag, testFlag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment getRootSegment() {
		return rootSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRootSegment(Segment newRootSegment, NotificationChain msgs) {
		Segment oldRootSegment = rootSegment;
		rootSegment = newRootSegment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__ROOT_SEGMENT, oldRootSegment, newRootSegment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootSegment(Segment newRootSegment) {
		if (newRootSegment != rootSegment) {
			NotificationChain msgs = null;
			if (rootSegment != null)
				msgs = ((InternalEObject)rootSegment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IdocPackage.DOCUMENT__ROOT_SEGMENT, null, msgs);
			if (newRootSegment != null)
				msgs = ((InternalEObject)newRootSegment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IdocPackage.DOCUMENT__ROOT_SEGMENT, null, msgs);
			msgs = basicSetRootSegment(newRootSegment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdocPackage.DOCUMENT__ROOT_SEGMENT, newRootSegment, newRootSegment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				return basicSetRootSegment(null, msgs);
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
			case IdocPackage.DOCUMENT__ARCHIVE_KEY:
				return getArchiveKey();
			case IdocPackage.DOCUMENT__CLIENT:
				return getClient();
			case IdocPackage.DOCUMENT__CREATION_DATE:
				return getCreationDate();
			case IdocPackage.DOCUMENT__CREATION_TIME:
				return getCreationTime();
			case IdocPackage.DOCUMENT__DIRECTION:
				return getDirection();
			case IdocPackage.DOCUMENT__EDI_MESSAGE:
				return getEDIMessage();
			case IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP:
				return getEDIMessageGroup();
			case IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE:
				return getEDIMessageType();
			case IdocPackage.DOCUMENT__EDI_STANDARD_FLAG:
				return getEDIStandardFlag();
			case IdocPackage.DOCUMENT__EDI_STANDARD_VERSION:
				return getEDIStandardVersion();
			case IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE:
				return getEDITransmissionFile();
			case IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE:
				return getIDocCompoundType();
			case IdocPackage.DOCUMENT__IDOC_NUMBER:
				return getIDocNumber();
			case IdocPackage.DOCUMENT__IDOC_SAP_RELEASE:
				return getIDocSAPRelease();
			case IdocPackage.DOCUMENT__IDOC_TYPE:
				return getIDocType();
			case IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION:
				return getIDocTypeExtension();
			case IdocPackage.DOCUMENT__MESSAGE_CODE:
				return getMessageCode();
			case IdocPackage.DOCUMENT__MESSAGE_FUNCTION:
				return getMessageFunction();
			case IdocPackage.DOCUMENT__MESSAGE_TYPE:
				return getMessageType();
			case IdocPackage.DOCUMENT__OUTPUT_MODE:
				return getOutputMode();
			case IdocPackage.DOCUMENT__RECIPIENT_ADDRESS:
				return getRecipientAddress();
			case IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS:
				return getRecipientLogicalAddress();
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION:
				return getRecipientPartnerFunction();
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER:
				return getRecipientPartnerNumber();
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE:
				return getRecipientPartnerType();
			case IdocPackage.DOCUMENT__RECIPIENT_PORT:
				return getRecipientPort();
			case IdocPackage.DOCUMENT__SENDER_ADDRESS:
				return getSenderAddress();
			case IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS:
				return getSenderLogicalAddress();
			case IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION:
				return getSenderPartnerFunction();
			case IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER:
				return getSenderPartnerNumber();
			case IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE:
				return getSenderPartnerType();
			case IdocPackage.DOCUMENT__SENDER_PORT:
				return getSenderPort();
			case IdocPackage.DOCUMENT__SERIALIZATION:
				return getSerialization();
			case IdocPackage.DOCUMENT__STATUS:
				return getStatus();
			case IdocPackage.DOCUMENT__TEST_FLAG:
				return getTestFlag();
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				return getRootSegment();
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
			case IdocPackage.DOCUMENT__ARCHIVE_KEY:
				setArchiveKey((String)newValue);
				return;
			case IdocPackage.DOCUMENT__CLIENT:
				setClient((String)newValue);
				return;
			case IdocPackage.DOCUMENT__CREATION_DATE:
				setCreationDate((Date)newValue);
				return;
			case IdocPackage.DOCUMENT__CREATION_TIME:
				setCreationTime((Date)newValue);
				return;
			case IdocPackage.DOCUMENT__DIRECTION:
				setDirection((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE:
				setEDIMessage((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP:
				setEDIMessageGroup((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE:
				setEDIMessageType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_STANDARD_FLAG:
				setEDIStandardFlag((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_STANDARD_VERSION:
				setEDIStandardVersion((String)newValue);
				return;
			case IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE:
				setEDITransmissionFile((String)newValue);
				return;
			case IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE:
				setIDocCompoundType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__IDOC_NUMBER:
				setIDocNumber((String)newValue);
				return;
			case IdocPackage.DOCUMENT__IDOC_SAP_RELEASE:
				setIDocSAPRelease((String)newValue);
				return;
			case IdocPackage.DOCUMENT__IDOC_TYPE:
				setIDocType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION:
				setIDocTypeExtension((String)newValue);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_CODE:
				setMessageCode((String)newValue);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_FUNCTION:
				setMessageFunction((String)newValue);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_TYPE:
				setMessageType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__OUTPUT_MODE:
				setOutputMode((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_ADDRESS:
				setRecipientAddress((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS:
				setRecipientLogicalAddress((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION:
				setRecipientPartnerFunction((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER:
				setRecipientPartnerNumber((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE:
				setRecipientPartnerType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PORT:
				setRecipientPort((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_ADDRESS:
				setSenderAddress((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS:
				setSenderLogicalAddress((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION:
				setSenderPartnerFunction((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER:
				setSenderPartnerNumber((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE:
				setSenderPartnerType((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SENDER_PORT:
				setSenderPort((String)newValue);
				return;
			case IdocPackage.DOCUMENT__SERIALIZATION:
				setSerialization((String)newValue);
				return;
			case IdocPackage.DOCUMENT__STATUS:
				setStatus((String)newValue);
				return;
			case IdocPackage.DOCUMENT__TEST_FLAG:
				setTestFlag((String)newValue);
				return;
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				setRootSegment((Segment)newValue);
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
			case IdocPackage.DOCUMENT__ARCHIVE_KEY:
				setArchiveKey(ARCHIVE_KEY_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__CLIENT:
				setClient(CLIENT_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__CREATION_TIME:
				setCreationTime(CREATION_TIME_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE:
				setEDIMessage(EDI_MESSAGE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP:
				setEDIMessageGroup(EDI_MESSAGE_GROUP_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE:
				setEDIMessageType(EDI_MESSAGE_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_STANDARD_FLAG:
				setEDIStandardFlag(EDI_STANDARD_FLAG_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_STANDARD_VERSION:
				setEDIStandardVersion(EDI_STANDARD_VERSION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE:
				setEDITransmissionFile(EDI_TRANSMISSION_FILE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE:
				setIDocCompoundType(IDOC_COMPOUND_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__IDOC_NUMBER:
				setIDocNumber(IDOC_NUMBER_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__IDOC_SAP_RELEASE:
				setIDocSAPRelease(IDOC_SAP_RELEASE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__IDOC_TYPE:
				setIDocType(IDOC_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION:
				setIDocTypeExtension(IDOC_TYPE_EXTENSION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_CODE:
				setMessageCode(MESSAGE_CODE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_FUNCTION:
				setMessageFunction(MESSAGE_FUNCTION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__MESSAGE_TYPE:
				setMessageType(MESSAGE_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__OUTPUT_MODE:
				setOutputMode(OUTPUT_MODE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_ADDRESS:
				setRecipientAddress(RECIPIENT_ADDRESS_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS:
				setRecipientLogicalAddress(RECIPIENT_LOGICAL_ADDRESS_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION:
				setRecipientPartnerFunction(RECIPIENT_PARTNER_FUNCTION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER:
				setRecipientPartnerNumber(RECIPIENT_PARTNER_NUMBER_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE:
				setRecipientPartnerType(RECIPIENT_PARTNER_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__RECIPIENT_PORT:
				setRecipientPort(RECIPIENT_PORT_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_ADDRESS:
				setSenderAddress(SENDER_ADDRESS_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS:
				setSenderLogicalAddress(SENDER_LOGICAL_ADDRESS_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION:
				setSenderPartnerFunction(SENDER_PARTNER_FUNCTION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER:
				setSenderPartnerNumber(SENDER_PARTNER_NUMBER_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE:
				setSenderPartnerType(SENDER_PARTNER_TYPE_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SENDER_PORT:
				setSenderPort(SENDER_PORT_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__SERIALIZATION:
				setSerialization(SERIALIZATION_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__TEST_FLAG:
				setTestFlag(TEST_FLAG_EDEFAULT);
				return;
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				setRootSegment((Segment)null);
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
			case IdocPackage.DOCUMENT__ARCHIVE_KEY:
				return ARCHIVE_KEY_EDEFAULT == null ? archiveKey != null : !ARCHIVE_KEY_EDEFAULT.equals(archiveKey);
			case IdocPackage.DOCUMENT__CLIENT:
				return CLIENT_EDEFAULT == null ? client != null : !CLIENT_EDEFAULT.equals(client);
			case IdocPackage.DOCUMENT__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case IdocPackage.DOCUMENT__CREATION_TIME:
				return CREATION_TIME_EDEFAULT == null ? creationTime != null : !CREATION_TIME_EDEFAULT.equals(creationTime);
			case IdocPackage.DOCUMENT__DIRECTION:
				return DIRECTION_EDEFAULT == null ? direction != null : !DIRECTION_EDEFAULT.equals(direction);
			case IdocPackage.DOCUMENT__EDI_MESSAGE:
				return EDI_MESSAGE_EDEFAULT == null ? ediMessage != null : !EDI_MESSAGE_EDEFAULT.equals(ediMessage);
			case IdocPackage.DOCUMENT__EDI_MESSAGE_GROUP:
				return EDI_MESSAGE_GROUP_EDEFAULT == null ? ediMessageGroup != null : !EDI_MESSAGE_GROUP_EDEFAULT.equals(ediMessageGroup);
			case IdocPackage.DOCUMENT__EDI_MESSAGE_TYPE:
				return EDI_MESSAGE_TYPE_EDEFAULT == null ? ediMessageType != null : !EDI_MESSAGE_TYPE_EDEFAULT.equals(ediMessageType);
			case IdocPackage.DOCUMENT__EDI_STANDARD_FLAG:
				return EDI_STANDARD_FLAG_EDEFAULT == null ? ediStandardFlag != null : !EDI_STANDARD_FLAG_EDEFAULT.equals(ediStandardFlag);
			case IdocPackage.DOCUMENT__EDI_STANDARD_VERSION:
				return EDI_STANDARD_VERSION_EDEFAULT == null ? ediStandardVersion != null : !EDI_STANDARD_VERSION_EDEFAULT.equals(ediStandardVersion);
			case IdocPackage.DOCUMENT__EDI_TRANSMISSION_FILE:
				return EDI_TRANSMISSION_FILE_EDEFAULT == null ? ediTransmissionFile != null : !EDI_TRANSMISSION_FILE_EDEFAULT.equals(ediTransmissionFile);
			case IdocPackage.DOCUMENT__IDOC_COMPOUND_TYPE:
				return IDOC_COMPOUND_TYPE_EDEFAULT == null ? iDocCompoundType != null : !IDOC_COMPOUND_TYPE_EDEFAULT.equals(iDocCompoundType);
			case IdocPackage.DOCUMENT__IDOC_NUMBER:
				return IDOC_NUMBER_EDEFAULT == null ? iDocNumber != null : !IDOC_NUMBER_EDEFAULT.equals(iDocNumber);
			case IdocPackage.DOCUMENT__IDOC_SAP_RELEASE:
				return IDOC_SAP_RELEASE_EDEFAULT == null ? iDocSAPRelease != null : !IDOC_SAP_RELEASE_EDEFAULT.equals(iDocSAPRelease);
			case IdocPackage.DOCUMENT__IDOC_TYPE:
				return IDOC_TYPE_EDEFAULT == null ? iDocType != null : !IDOC_TYPE_EDEFAULT.equals(iDocType);
			case IdocPackage.DOCUMENT__IDOC_TYPE_EXTENSION:
				return IDOC_TYPE_EXTENSION_EDEFAULT == null ? iDocTypeExtension != null : !IDOC_TYPE_EXTENSION_EDEFAULT.equals(iDocTypeExtension);
			case IdocPackage.DOCUMENT__MESSAGE_CODE:
				return MESSAGE_CODE_EDEFAULT == null ? messageCode != null : !MESSAGE_CODE_EDEFAULT.equals(messageCode);
			case IdocPackage.DOCUMENT__MESSAGE_FUNCTION:
				return MESSAGE_FUNCTION_EDEFAULT == null ? messageFunction != null : !MESSAGE_FUNCTION_EDEFAULT.equals(messageFunction);
			case IdocPackage.DOCUMENT__MESSAGE_TYPE:
				return MESSAGE_TYPE_EDEFAULT == null ? messageType != null : !MESSAGE_TYPE_EDEFAULT.equals(messageType);
			case IdocPackage.DOCUMENT__OUTPUT_MODE:
				return OUTPUT_MODE_EDEFAULT == null ? outputMode != null : !OUTPUT_MODE_EDEFAULT.equals(outputMode);
			case IdocPackage.DOCUMENT__RECIPIENT_ADDRESS:
				return RECIPIENT_ADDRESS_EDEFAULT == null ? recipientAddress != null : !RECIPIENT_ADDRESS_EDEFAULT.equals(recipientAddress);
			case IdocPackage.DOCUMENT__RECIPIENT_LOGICAL_ADDRESS:
				return RECIPIENT_LOGICAL_ADDRESS_EDEFAULT == null ? recipientLogicalAddress != null : !RECIPIENT_LOGICAL_ADDRESS_EDEFAULT.equals(recipientLogicalAddress);
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_FUNCTION:
				return RECIPIENT_PARTNER_FUNCTION_EDEFAULT == null ? recipientPartnerFunction != null : !RECIPIENT_PARTNER_FUNCTION_EDEFAULT.equals(recipientPartnerFunction);
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_NUMBER:
				return RECIPIENT_PARTNER_NUMBER_EDEFAULT == null ? recipientPartnerNumber != null : !RECIPIENT_PARTNER_NUMBER_EDEFAULT.equals(recipientPartnerNumber);
			case IdocPackage.DOCUMENT__RECIPIENT_PARTNER_TYPE:
				return RECIPIENT_PARTNER_TYPE_EDEFAULT == null ? recipientPartnerType != null : !RECIPIENT_PARTNER_TYPE_EDEFAULT.equals(recipientPartnerType);
			case IdocPackage.DOCUMENT__RECIPIENT_PORT:
				return RECIPIENT_PORT_EDEFAULT == null ? recipientPort != null : !RECIPIENT_PORT_EDEFAULT.equals(recipientPort);
			case IdocPackage.DOCUMENT__SENDER_ADDRESS:
				return SENDER_ADDRESS_EDEFAULT == null ? senderAddress != null : !SENDER_ADDRESS_EDEFAULT.equals(senderAddress);
			case IdocPackage.DOCUMENT__SENDER_LOGICAL_ADDRESS:
				return SENDER_LOGICAL_ADDRESS_EDEFAULT == null ? senderLogicalAddress != null : !SENDER_LOGICAL_ADDRESS_EDEFAULT.equals(senderLogicalAddress);
			case IdocPackage.DOCUMENT__SENDER_PARTNER_FUNCTION:
				return SENDER_PARTNER_FUNCTION_EDEFAULT == null ? senderPartnerFunction != null : !SENDER_PARTNER_FUNCTION_EDEFAULT.equals(senderPartnerFunction);
			case IdocPackage.DOCUMENT__SENDER_PARTNER_NUMBER:
				return SENDER_PARTNER_NUMBER_EDEFAULT == null ? senderPartnerNumber != null : !SENDER_PARTNER_NUMBER_EDEFAULT.equals(senderPartnerNumber);
			case IdocPackage.DOCUMENT__SENDER_PARTNER_TYPE:
				return SENDER_PARTNER_TYPE_EDEFAULT == null ? senderPartnerType != null : !SENDER_PARTNER_TYPE_EDEFAULT.equals(senderPartnerType);
			case IdocPackage.DOCUMENT__SENDER_PORT:
				return SENDER_PORT_EDEFAULT == null ? senderPort != null : !SENDER_PORT_EDEFAULT.equals(senderPort);
			case IdocPackage.DOCUMENT__SERIALIZATION:
				return SERIALIZATION_EDEFAULT == null ? serialization != null : !SERIALIZATION_EDEFAULT.equals(serialization);
			case IdocPackage.DOCUMENT__STATUS:
				return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
			case IdocPackage.DOCUMENT__TEST_FLAG:
				return TEST_FLAG_EDEFAULT == null ? testFlag != null : !TEST_FLAG_EDEFAULT.equals(testFlag);
			case IdocPackage.DOCUMENT__ROOT_SEGMENT:
				return rootSegment != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (archiveKey: ");
		result.append(archiveKey);
		result.append(", client: ");
		result.append(client);
		result.append(", creationDate: ");
		result.append(creationDate);
		result.append(", creationTime: ");
		result.append(creationTime);
		result.append(", direction: ");
		result.append(direction);
		result.append(", EDIMessage: ");
		result.append(ediMessage);
		result.append(", EDIMessageGroup: ");
		result.append(ediMessageGroup);
		result.append(", EDIMessageType: ");
		result.append(ediMessageType);
		result.append(", EDIStandardFlag: ");
		result.append(ediStandardFlag);
		result.append(", EDIStandardVersion: ");
		result.append(ediStandardVersion);
		result.append(", EDITransmissionFile: ");
		result.append(ediTransmissionFile);
		result.append(", iDocCompoundType: ");
		result.append(iDocCompoundType);
		result.append(", iDocNumber: ");
		result.append(iDocNumber);
		result.append(", iDocSAPRelease: ");
		result.append(iDocSAPRelease);
		result.append(", iDocType: ");
		result.append(iDocType);
		result.append(", iDocTypeExtension: ");
		result.append(iDocTypeExtension);
		result.append(", messageCode: ");
		result.append(messageCode);
		result.append(", messageFunction: ");
		result.append(messageFunction);
		result.append(", messageType: ");
		result.append(messageType);
		result.append(", outputMode: ");
		result.append(outputMode);
		result.append(", recipientAddress: ");
		result.append(recipientAddress);
		result.append(", recipientLogicalAddress: ");
		result.append(recipientLogicalAddress);
		result.append(", recipientPartnerFunction: ");
		result.append(recipientPartnerFunction);
		result.append(", recipientPartnerNumber: ");
		result.append(recipientPartnerNumber);
		result.append(", recipientPartnerType: ");
		result.append(recipientPartnerType);
		result.append(", recipientPort: ");
		result.append(recipientPort);
		result.append(", senderAddress: ");
		result.append(senderAddress);
		result.append(", senderLogicalAddress: ");
		result.append(senderLogicalAddress);
		result.append(", senderPartnerFunction: ");
		result.append(senderPartnerFunction);
		result.append(", senderPartnerNumber: ");
		result.append(senderPartnerNumber);
		result.append(", senderPartnerType: ");
		result.append(senderPartnerType);
		result.append(", senderPort: ");
		result.append(senderPort);
		result.append(", serialization: ");
		result.append(serialization);
		result.append(", status: ");
		result.append(status);
		result.append(", testFlag: ");
		result.append(testFlag);
		result.append(')');
		return result.toString();
	}

} //DocumentImpl
