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

import java.util.Date;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getArchiveKey <em>Archive Key</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getClient <em>Client</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessage <em>EDI Message</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageGroup <em>EDI Message Group</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageType <em>EDI Message Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardFlag <em>EDI Standard Flag</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardVersion <em>EDI Standard Version</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDITransmissionFile <em>EDI Transmission File</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocCompoundType <em>IDoc Compound Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocNumber <em>IDoc Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocSAPRelease <em>IDoc SAP Release</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocType <em>IDoc Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocTypeExtension <em>IDoc Type Extension</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageCode <em>Message Code</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageFunction <em>Message Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getOutputMode <em>Output Mode</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientAddress <em>Recipient Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientLogicalAddress <em>Recipient Logical Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerFunction <em>Recipient Partner Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerNumber <em>Recipient Partner Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerType <em>Recipient Partner Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPort <em>Recipient Port</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderAddress <em>Sender Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderLogicalAddress <em>Sender Logical Address</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerFunction <em>Sender Partner Function</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerNumber <em>Sender Partner Number</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerType <em>Sender Partner Type</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPort <em>Sender Port</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getSerialization <em>Serialization</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getStatus <em>Status</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getTestFlag <em>Test Flag</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.idoc.Document#getRootSegment <em>Root Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument()
 * @model
 * @generated
 */
public interface Document extends EObject {
	/**
	 * Returns the value of the '<em><b>Archive Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archive Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archive Key</em>' attribute.
	 * @see #setArchiveKey(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_ArchiveKey()
	 * @model
	 * @generated
	 */
	String getArchiveKey();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getArchiveKey <em>Archive Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Archive Key</em>' attribute.
	 * @see #getArchiveKey()
	 * @generated
	 */
	void setArchiveKey(String value);

	/**
	 * Returns the value of the '<em><b>Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Client</em>' attribute.
	 * @see #setClient(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_Client()
	 * @model
	 * @generated
	 */
	String getClient();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getClient <em>Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client</em>' attribute.
	 * @see #getClient()
	 * @generated
	 */
	void setClient(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(Date)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_CreationDate()
	 * @model
	 * @generated
	 */
	Date getCreationDate();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated NOT
	 */
	void setCreationDate(Date value);

	/**
	 * Returns the value of the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Time</em>' attribute.
	 * @see #setCreationTime(Date)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_CreationTime()
	 * @model
	 * @generated
	 */
	Date getCreationTime();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getCreationTime <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Time</em>' attribute.
	 * @see #getCreationTime()
	 * @generated NOT
	 */
	void setCreationTime(Date value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see #setDirection(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_Direction()
	 * @model
	 * @generated
	 */
	String getDirection();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(String value);

	/**
	 * Returns the value of the '<em><b>EDI Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Message</em>' attribute.
	 * @see #setEDIMessage(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDIMessage()
	 * @model
	 * @generated
	 */
	String getEDIMessage();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessage <em>EDI Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Message</em>' attribute.
	 * @see #getEDIMessage()
	 * @generated
	 */
	void setEDIMessage(String value);

	/**
	 * Returns the value of the '<em><b>EDI Message Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Message Group</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Message Group</em>' attribute.
	 * @see #setEDIMessageGroup(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDIMessageGroup()
	 * @model
	 * @generated
	 */
	String getEDIMessageGroup();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageGroup <em>EDI Message Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Message Group</em>' attribute.
	 * @see #getEDIMessageGroup()
	 * @generated
	 */
	void setEDIMessageGroup(String value);

	/**
	 * Returns the value of the '<em><b>EDI Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Message Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Message Type</em>' attribute.
	 * @see #setEDIMessageType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDIMessageType()
	 * @model
	 * @generated
	 */
	String getEDIMessageType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIMessageType <em>EDI Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Message Type</em>' attribute.
	 * @see #getEDIMessageType()
	 * @generated
	 */
	void setEDIMessageType(String value);

	/**
	 * Returns the value of the '<em><b>EDI Standard Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Standard Flag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Standard Flag</em>' attribute.
	 * @see #setEDIStandardFlag(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDIStandardFlag()
	 * @model
	 * @generated
	 */
	String getEDIStandardFlag();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardFlag <em>EDI Standard Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Standard Flag</em>' attribute.
	 * @see #getEDIStandardFlag()
	 * @generated
	 */
	void setEDIStandardFlag(String value);

	/**
	 * Returns the value of the '<em><b>EDI Standard Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Standard Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Standard Version</em>' attribute.
	 * @see #setEDIStandardVersion(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDIStandardVersion()
	 * @model
	 * @generated
	 */
	String getEDIStandardVersion();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDIStandardVersion <em>EDI Standard Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Standard Version</em>' attribute.
	 * @see #getEDIStandardVersion()
	 * @generated
	 */
	void setEDIStandardVersion(String value);

	/**
	 * Returns the value of the '<em><b>EDI Transmission File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EDI Transmission File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EDI Transmission File</em>' attribute.
	 * @see #setEDITransmissionFile(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_EDITransmissionFile()
	 * @model
	 * @generated
	 */
	String getEDITransmissionFile();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getEDITransmissionFile <em>EDI Transmission File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EDI Transmission File</em>' attribute.
	 * @see #getEDITransmissionFile()
	 * @generated
	 */
	void setEDITransmissionFile(String value);

	/**
	 * Returns the value of the '<em><b>IDoc Compound Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IDoc Compound Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IDoc Compound Type</em>' attribute.
	 * @see #setIDocCompoundType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_IDocCompoundType()
	 * @model
	 * @generated
	 */
	String getIDocCompoundType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocCompoundType <em>IDoc Compound Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IDoc Compound Type</em>' attribute.
	 * @see #getIDocCompoundType()
	 * @generated
	 */
	void setIDocCompoundType(String value);

	/**
	 * Returns the value of the '<em><b>IDoc Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IDoc Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IDoc Number</em>' attribute.
	 * @see #setIDocNumber(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_IDocNumber()
	 * @model
	 * @generated
	 */
	String getIDocNumber();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocNumber <em>IDoc Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IDoc Number</em>' attribute.
	 * @see #getIDocNumber()
	 * @generated
	 */
	void setIDocNumber(String value);

	/**
	 * Returns the value of the '<em><b>IDoc SAP Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IDoc SAP Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IDoc SAP Release</em>' attribute.
	 * @see #setIDocSAPRelease(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_IDocSAPRelease()
	 * @model
	 * @generated
	 */
	String getIDocSAPRelease();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocSAPRelease <em>IDoc SAP Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IDoc SAP Release</em>' attribute.
	 * @see #getIDocSAPRelease()
	 * @generated
	 */
	void setIDocSAPRelease(String value);

	/**
	 * Returns the value of the '<em><b>IDoc Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IDoc Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IDoc Type</em>' attribute.
	 * @see #setIDocType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_IDocType()
	 * @model
	 * @generated
	 */
	String getIDocType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocType <em>IDoc Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IDoc Type</em>' attribute.
	 * @see #getIDocType()
	 * @generated
	 */
	void setIDocType(String value);

	/**
	 * Returns the value of the '<em><b>IDoc Type Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IDoc Type Extension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IDoc Type Extension</em>' attribute.
	 * @see #setIDocTypeExtension(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_IDocTypeExtension()
	 * @model
	 * @generated
	 */
	String getIDocTypeExtension();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getIDocTypeExtension <em>IDoc Type Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IDoc Type Extension</em>' attribute.
	 * @see #getIDocTypeExtension()
	 * @generated
	 */
	void setIDocTypeExtension(String value);

	/**
	 * Returns the value of the '<em><b>Message Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Code</em>' attribute.
	 * @see #setMessageCode(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_MessageCode()
	 * @model
	 * @generated
	 */
	String getMessageCode();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageCode <em>Message Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Code</em>' attribute.
	 * @see #getMessageCode()
	 * @generated
	 */
	void setMessageCode(String value);

	/**
	 * Returns the value of the '<em><b>Message Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Function</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Function</em>' attribute.
	 * @see #setMessageFunction(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_MessageFunction()
	 * @model
	 * @generated
	 */
	String getMessageFunction();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageFunction <em>Message Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Function</em>' attribute.
	 * @see #getMessageFunction()
	 * @generated
	 */
	void setMessageFunction(String value);

	/**
	 * Returns the value of the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Type</em>' attribute.
	 * @see #setMessageType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_MessageType()
	 * @model
	 * @generated
	 */
	String getMessageType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getMessageType <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Type</em>' attribute.
	 * @see #getMessageType()
	 * @generated
	 */
	void setMessageType(String value);

	/**
	 * Returns the value of the '<em><b>Output Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Mode</em>' attribute.
	 * @see #setOutputMode(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_OutputMode()
	 * @model
	 * @generated
	 */
	String getOutputMode();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getOutputMode <em>Output Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Mode</em>' attribute.
	 * @see #getOutputMode()
	 * @generated
	 */
	void setOutputMode(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Address</em>' attribute.
	 * @see #setRecipientAddress(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientAddress()
	 * @model
	 * @generated
	 */
	String getRecipientAddress();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientAddress <em>Recipient Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Address</em>' attribute.
	 * @see #getRecipientAddress()
	 * @generated
	 */
	void setRecipientAddress(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Logical Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Logical Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Logical Address</em>' attribute.
	 * @see #setRecipientLogicalAddress(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientLogicalAddress()
	 * @model
	 * @generated
	 */
	String getRecipientLogicalAddress();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientLogicalAddress <em>Recipient Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Logical Address</em>' attribute.
	 * @see #getRecipientLogicalAddress()
	 * @generated
	 */
	void setRecipientLogicalAddress(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Partner Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Partner Function</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Partner Function</em>' attribute.
	 * @see #setRecipientPartnerFunction(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientPartnerFunction()
	 * @model
	 * @generated
	 */
	String getRecipientPartnerFunction();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerFunction <em>Recipient Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Partner Function</em>' attribute.
	 * @see #getRecipientPartnerFunction()
	 * @generated
	 */
	void setRecipientPartnerFunction(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Partner Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Partner Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Partner Number</em>' attribute.
	 * @see #setRecipientPartnerNumber(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientPartnerNumber()
	 * @model
	 * @generated
	 */
	String getRecipientPartnerNumber();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerNumber <em>Recipient Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Partner Number</em>' attribute.
	 * @see #getRecipientPartnerNumber()
	 * @generated
	 */
	void setRecipientPartnerNumber(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Partner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Partner Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Partner Type</em>' attribute.
	 * @see #setRecipientPartnerType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientPartnerType()
	 * @model
	 * @generated
	 */
	String getRecipientPartnerType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPartnerType <em>Recipient Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Partner Type</em>' attribute.
	 * @see #getRecipientPartnerType()
	 * @generated
	 */
	void setRecipientPartnerType(String value);

	/**
	 * Returns the value of the '<em><b>Recipient Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recipient Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipient Port</em>' attribute.
	 * @see #setRecipientPort(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RecipientPort()
	 * @model
	 * @generated
	 */
	String getRecipientPort();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getRecipientPort <em>Recipient Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recipient Port</em>' attribute.
	 * @see #getRecipientPort()
	 * @generated
	 */
	void setRecipientPort(String value);

	/**
	 * Returns the value of the '<em><b>Sender Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Address</em>' attribute.
	 * @see #setSenderAddress(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderAddress()
	 * @model
	 * @generated
	 */
	String getSenderAddress();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderAddress <em>Sender Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Address</em>' attribute.
	 * @see #getSenderAddress()
	 * @generated
	 */
	void setSenderAddress(String value);

	/**
	 * Returns the value of the '<em><b>Sender Logical Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Logical Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Logical Address</em>' attribute.
	 * @see #setSenderLogicalAddress(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderLogicalAddress()
	 * @model
	 * @generated
	 */
	String getSenderLogicalAddress();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderLogicalAddress <em>Sender Logical Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Logical Address</em>' attribute.
	 * @see #getSenderLogicalAddress()
	 * @generated
	 */
	void setSenderLogicalAddress(String value);

	/**
	 * Returns the value of the '<em><b>Sender Partner Function</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Partner Function</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Partner Function</em>' attribute.
	 * @see #setSenderPartnerFunction(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderPartnerFunction()
	 * @model
	 * @generated
	 */
	String getSenderPartnerFunction();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerFunction <em>Sender Partner Function</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Partner Function</em>' attribute.
	 * @see #getSenderPartnerFunction()
	 * @generated
	 */
	void setSenderPartnerFunction(String value);

	/**
	 * Returns the value of the '<em><b>Sender Partner Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Partner Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Partner Number</em>' attribute.
	 * @see #setSenderPartnerNumber(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderPartnerNumber()
	 * @model
	 * @generated
	 */
	String getSenderPartnerNumber();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerNumber <em>Sender Partner Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Partner Number</em>' attribute.
	 * @see #getSenderPartnerNumber()
	 * @generated
	 */
	void setSenderPartnerNumber(String value);

	/**
	 * Returns the value of the '<em><b>Sender Partner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Partner Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Partner Type</em>' attribute.
	 * @see #setSenderPartnerType(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderPartnerType()
	 * @model
	 * @generated
	 */
	String getSenderPartnerType();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPartnerType <em>Sender Partner Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Partner Type</em>' attribute.
	 * @see #getSenderPartnerType()
	 * @generated
	 */
	void setSenderPartnerType(String value);

	/**
	 * Returns the value of the '<em><b>Sender Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender Port</em>' attribute.
	 * @see #setSenderPort(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_SenderPort()
	 * @model
	 * @generated
	 */
	String getSenderPort();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSenderPort <em>Sender Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender Port</em>' attribute.
	 * @see #getSenderPort()
	 * @generated
	 */
	void setSenderPort(String value);

	/**
	 * Returns the value of the '<em><b>Serialization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Serialization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Serialization</em>' attribute.
	 * @see #setSerialization(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_Serialization()
	 * @model
	 * @generated
	 */
	String getSerialization();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getSerialization <em>Serialization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Serialization</em>' attribute.
	 * @see #getSerialization()
	 * @generated
	 */
	void setSerialization(String value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_Status()
	 * @model
	 * @generated
	 */
	String getStatus();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(String value);

	/**
	 * Returns the value of the '<em><b>Test Flag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Flag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Flag</em>' attribute.
	 * @see #setTestFlag(String)
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_TestFlag()
	 * @model
	 * @generated
	 */
	String getTestFlag();

	/**
	 * Sets the value of the '{@link org.fusesource.camel.component.sap.model.idoc.Document#getTestFlag <em>Test Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Flag</em>' attribute.
	 * @see #getTestFlag()
	 * @generated
	 */
	void setTestFlag(String value);

	/**
	 * Returns the value of the '<em><b>Root Segment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Segment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Segment</em>' containment reference.
	 * @see org.fusesource.camel.component.sap.model.idoc.IdocPackage#getDocument_RootSegment()
	 * @model containment="true" suppressedSetVisibility="true"
	 * @generated
	 */
	Segment getRootSegment();

} // Document
