package org.fusesource.camel.component.sap.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.IdocFactory;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.SegmentChildren;
import org.fusesource.camel.component.sap.model.idoc.impl.DocumentImpl;
import org.fusesource.camel.component.sap.model.idoc.impl.SegmentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocConversionException;
import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocFactory;
import com.sap.conn.idoc.IDocMetaDataUnavailableException;
import com.sap.conn.idoc.IDocRecordMetaData;
import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.IDocSegment;
import com.sap.conn.idoc.IDocSegmentMetaData;
import com.sap.conn.idoc.IDocSyntaxException;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;

import static org.fusesource.camel.component.sap.model.idoc.IdocPackage.eNS_URI;

public class IDocUtil extends Util {

	private static final Logger LOG = LoggerFactory.getLogger(IDocUtil.class);

	public static final String GenNS_URI = "http://www.eclipse.org/emf/2002/GenModel";

	public static final String GenNS_DOCUMENTATION_KEY = "documentation";
	
	public static final Registry registry = EPackage.Registry.INSTANCE;

	/**
	 * Details key for a root segment annotation providing the
	 * compound type of the document.
	 */
	public static final String IDocNS_COMPOUND_TYPE_KEY = "compoundType";

	/**
	 * Details key for a segment annotation providing the
	 * name of the segment.
	 */
	public static final String IDocNS_NAME_KEY = "name";

	/**
	 * Details key for a segment annotation providing the
	 * definition of the segment.
	 */
	public static final String IDocNS_DEFINITION_KEY = "definition";

	/**
	 * Details key for a segment annotation providing the
	 * hierarchy level of the segment.
	 */
	public static final String IDocNS_HIERARCHY_LEVEL_KEY = "hierarchyLevel";

	/**
	 * Details key for a segment annotation providing the
	 * IDoc type of the segment.
	 */
	public static final String IDocNS_IDOC_TYPE_KEY = "idocType";

	/**
	 * Details key for a segment annotation providing the
	 * IDoc type extension of the segment.
	 */
	public static final String IDocNS_IDOC_TYPE_EXTENSION_KEY = "idocTypeExtension";

	/**
	 * Details key for a segment annotation providing the
	 * key of the segment.
	 */
	public static final String IDocNS_KEY_KEY = "key";

	/**
	 * Details key for a segment annotation providing the
	 * system release of the segment.
	 */
	public static final String IDocNS_SYSTEM_RELEASE_KEY = "systemRelease";

	/**
	 * Details key for a segment annotation providing the
	 * application release of the segment.
	 */
	public static final String IDocNS_APPLICATION_RELEASE_KEY = "applicationRelease";

	/**
	 * Details key for a segment annotation providing the
	 * type of the segment.
	 */
	public static final String IDocNS_TYPE_KEY = "type";

	/**
	 * Details key for a segment annotation providing the
	 * max number of occurrences of the segment.
	 */
	public static final String IDocNS_MAX_OCCURRENCE_KEY = "maxOccurrence";

	/**
	 * Details key for a segment annotation providing the
	 * min number of occurrences of the segment.
	 */
	public static final String IDocNS_MIN_OCCURRENCE_KEY = "minOccurrence";

	/**
	 * Details key for a segment annotation providing the
	 * mandatory state of the segment.
	 */
	public static final String IDocNS_IS_MANDATORY_KEY = "isMandatory";

	/**
	 * Details key for a segment annotation providing the
	 * qualified state of the segment.
	 */
	public static final String IDocNS_IS_QUALIFIED_KEY = "isQualified";

	/**
	 * Details key for a segment annotation providing the
	 * number of fields in the segment.
	 */
	public static final String IDocNS_NUM_FIELDS_KEY = "numFields";

	/**
	 * Details key for a segment annotation providing the
	 * locked state of the segment.
	 */
	public static final String IDocNS_IS_LOCKED_KEY = "isLocked";

	/**
	 * Details key for a segment annotation providing the
	 * record length of the segment.
	 */
	public static final String IDocNS_RECORD_LENGTH_KEY = "recordLength";

	/**
	 * Details key for a segment field annotation providing the
	 * description of the field. 
	 */
	public static final String IDocNS_DESCRIPTION_KEY = "description";

	/**
	 * Details key for a segment field annotation providing the
	 * fully-qualified Java classname of the field.
	 */
	public static final String IDocNS_CLASS_NAME_OF_FIELD_KEY = "classNameOfField";

	/**
	 * Details key for a segment field annotation providing the
	 * check table name of the field.
	 */
	public static final String IDocNS_CHECK_TABLE_NAME_KEY = "checkTableName";

	/**
	 * Details key for a segment field annotation providing the
	 * data element name of the field.
	 */
	public static final String IDocNS_DATA_ELEMENT_NAME_KEY = "dataElementName";

	/**
	 * Details key for a segment field annotation providing the
	 * data type of the field.
	 */
	public static final String IDocNS_DATA_TYPE_KEY = "dataType";

	/**
	 * Details key for a segment field annotation providing the
	 * data type name of the field.
	 */
	public static final String IDocNS_DATA_TYPE_NAME_KEY = "dataTypeName";

	/**
	 * Details key for a segment field annotation providing the
	 * domain name of the field.
	 */
	public static final String IDocNS_DOMAIN_NAME_KEY = "domainName";

	/**
	 * Details key for a segment field annotation providing the
	 * internal lenght of the field.
	 */
	public static final String IDocNS_INTERNAL_LENGTH_KEY = "internalLength";

	/**
	 * Details key for a segment field annotation providing the
	 * length of the field.
	 */
	public static final String IDocNS_LENGTH_KEY = "length";

	/**
	 * Details key for a segment field annotation providing the
	 * offset of the field.
	 */
	public static final String IDocNS_OFFSET_KEY = "offset";

	/**
	 * Details key for a segment field annotation providing the
	 * output length of the field.
	 */
	public static final String IDocNS_OUTPUT_LENGTH_KEY = "outputLength";

	/**
	 * Details key for a segment field annotation providing the
	 * type of the field.
	 */
	public static final String IDocNS_RECORD_TYPE_KEY = "recordType";

	/**
	 * Details key for a segment field annotation providing the
	 * type as string of the field.
	 */
	public static final String IDocNS_TYPE_AS_STRING_KEY = "typeAsString";

	/**
	 * Details key for a segment field annotation providing the
	 * value descriptions of the field.
	 */
	public static final String IDocNS_VALUE_DESCRIPTIONS_KEY = "valueDescriptions";

	/**
	 * Details key for a segment field annotation providing the
	 * value ranges of the field.
	 */
	public static final String IDocNS_VALUE_RANGES_KEY = "valueRanges";

	/**
	 * Details key for a segment field annotation providing the
	 * values of the field.
	 */
	public static final String IDocNS_VALUES_KEY = "values";

	/**
	 * Details key for a segment field annotation providing the
	 * is ISO code of the field.
	 */
	public static final String IDocNS_IS_ISO_CODE_KEY = "isoCode";

	/**
	 * Details key for a segment field annotation providing the
	 * position of the field.
	 */
	public static final String IDocNS_POSITION_KEY = "position";
	
	public static void sendIDoc(JCoDestination destination, Document document) throws JCoException, IDocMetaDataUnavailableException {
		IDocRepository iDocRepository = JCoIDoc.getIDocRepository(destination);
		String tid = destination.createTID();
		IDocFactory iDocFactory = JCoIDoc.getIDocFactory();

		// Create IDoc
		Segment rootSegment = document.getRootSegment();
		IDocDocument iDocDocument = iDocFactory.createIDocDocument(iDocRepository, rootSegment.getIdocType(), rootSegment.getIdocTypeExtension(), rootSegment.getSystemRelease(), rootSegment.getApplicationRelease());
		
		// Fill IDoc Document 
		fillIDocDocumentFromDocument(document, iDocDocument);
		
		// Send IDoc
		JCoIDoc.send(iDocDocument, IDocFactory.IDOC_VERSION_DEFAULT, destination, tid);
		destination.confirmTID(tid);
	}
	
	public static void fillIDocDocumentFromDocument(Document document, IDocDocument idocDocument) {
		if (idocDocument == null || document == null) {
			LOG.warn("IDoc Document'" + idocDocument + "' not filled from document'" + document + "'");
			return;
		}
		
		try {
			idocDocument.setArchiveKey(document.getArchiveKey());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill ArchiveKey attribute with value '" + document.getArchiveKey() + "' of IDoc Document");
		}
		try {
			idocDocument.setClient(document.getClient());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill Client attribute with value '" + document.getClient() + "' of IDoc Document");
		}
		try {
			idocDocument.setCreationDate(document.getCreationDate());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill CreationDate attribute with value '" + document.getCreationDate() + "' of IDoc Document");
		}
		try {
			idocDocument.setCreationTime(document.getCreationTime());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill CreationTime attribute with value '" + document.getCreationTime() + "' of IDoc Document");
		}
		try {
			idocDocument.setDirection(document.getDirection());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill Direction attribute with value '" + document.getDirection() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDIMessage(document.getEDIMessage());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDIMessage attribute with value '" + document.getEDIMessage() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDIMessageGroup(document.getEDIMessageGroup());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDIMessageGroup attribute with value '" + document.getEDIMessageGroup() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDIMessageType(document.getEDIMessageType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDIMessageType attribute with value '" + document.getEDIMessageType() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDIStandardFlag(document.getEDIStandardFlag());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDIStandardFlag attribute with value '" + document.getEDIStandardFlag() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDIStandardVersion(document.getEDIStandardVersion());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDIStandardVersion attribute with value '" + document.getEDIStandardVersion() + "' of IDoc Document");
		}
		try {
			idocDocument.setEDITransmissionFile(document.getEDITransmissionFile());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill EDITransmissionFile attribute with value '" + document.getEDITransmissionFile() + "' of IDoc Document");
		}
		try {
			idocDocument.setIDocCompoundType(document.getIDocCompoundType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill IDocCompoundType attribute with value '" + document.getIDocCompoundType() + "' of IDoc Document");
		}
		try {
			idocDocument.setIDocNumber(document.getIDocNumber());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill IDocNumber attribute with value '" + document.getIDocNumber() + "' of IDoc Document");
		}
		try {
			idocDocument.setIDocSAPRelease(document.getIDocSAPRelease());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill IDocSAPRelease attribute with value '" + document.getIDocSAPRelease() + "' of IDoc Document");
		}
		try {
			idocDocument.setIDocType(document.getIDocType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill IDocType attribute with value '" + document.getIDocType() + "' of IDoc Document");
		}
		try {
			idocDocument.setIDocTypeExtension(document.getIDocTypeExtension());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill IDocTypeExtension attribute with value '" + document.getIDocTypeExtension() + "' of IDoc Document");
		}
		try {
			idocDocument.setMessageCode(document.getMessageCode());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill MessageCode attribute with value '" + document.getMessageCode() + "' of IDoc Document");
		}
		try {
			idocDocument.setMessageFunction(document.getMessageFunction());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill MessageFunction attribute with value '" + document.getMessageFunction() + "' of IDoc Document");
		}
		try {
			idocDocument.setMessageType(document.getMessageType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill MessageType attribute with value '" + document.getMessageType() + "' of IDoc Document");
		}
		try {
			idocDocument.setOutputMode(document.getOutputMode());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill OutputMode attribute with value '" + document.getOutputMode() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientAddress(document.getRecipientAddress());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientAddress attribute with value '" + document.getRecipientAddress() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientLogicalAddress(document.getRecipientLogicalAddress());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientLogicalAddress attribute with value '" + document.getRecipientLogicalAddress() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientPartnerFunction(document.getRecipientPartnerFunction());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientPartnerFunction attribute with value '" + document.getRecipientPartnerFunction() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientPartnerType(document.getRecipientPartnerType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientPartnerType attribute with value '" + document.getRecipientPartnerType() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientPartnerNumber(document.getRecipientPartnerNumber());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientPartnerNumber attribute with value '" + document.getRecipientPartnerNumber() + "' of IDoc Document");
		}
		try {
			idocDocument.setRecipientPort(document.getRecipientPort());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill RecipientPort attribute with value '" + document.getRecipientPort() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderAddress(document.getSenderAddress());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderAddress attribute with value '" + document.getSenderAddress() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderLogicalAddress(document.getSenderLogicalAddress());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderLogicalAddress attribute with value '" + document.getSenderLogicalAddress() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderPartnerFunction(document.getSenderPartnerFunction());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderPartnerFunction attribute with value '" + document.getSenderPartnerFunction() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderPartnerNumber(document.getSenderPartnerNumber());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderPartnerNumber attribute with value '" + document.getSenderPartnerNumber() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderPartnerType(document.getSenderPartnerType());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderPartnerType attribute with value '" + document.getSenderPartnerType() + "' of IDoc Document");
		}
		try {
			idocDocument.setSenderPort(document.getSenderPort());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill SenderPort attribute with value '" + document.getSenderPort() + "' of IDoc Document");
		}
		try {
			idocDocument.setSerialization(document.getSerialization());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill Serialization attribute with value '" + document.getSerialization() + "' of IDoc Document");
		}
		try {
			idocDocument.setStatus(document.getStatus());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill Status attribute with value '" + document.getStatus() + "' of IDoc Document");
		}
		try {
			idocDocument.setTestFlag(document.getTestFlag());
		} catch (IDocConversionException | IDocSyntaxException e) {
			LOG.warn("Failed to fill TestFlag attribute with value '" + document.getTestFlag() + "' of IDoc Document");
		}
		
		fillIDocSegmentFromSegment(document.getRootSegment(), idocDocument.getRootSegment());

	}
	
	public static void fillIDocSegmentFromSegment(Segment segment, IDocSegment idocSegment) {
		if (segment == null || idocSegment == null) {
			LOG.warn("IDoc Segment '" + idocSegment + "' not filled from segment '" + segment + "'");
			return;
		}
		
		// Fill segment fields
		Iterator<String> it = segment.keySet().iterator();
		while(it.hasNext()) {
			String fieldName = it.next();
			Object value = segment.get(fieldName);
			try {
				idocSegment.setValue(fieldName, value);
			} catch (Exception e) {
				LOG.warn("Failed to fill '" + fieldName + "' attribute with value '" + value + "' of IDoc Document");
			}
		}

		// Fill child segments
		SegmentChildren segmentChildren = ((SegmentImpl)segment).getSegmentChildren();
		for(String segmentType: segmentChildren.getTypes()) {
			for (Segment childSegment: segmentChildren.get(segmentType)) {
				try {
					IDocSegment childIDocSegment = idocSegment.addChild(segmentType);
					fillIDocSegmentFromSegment(childSegment, childIDocSegment);
				} catch (Exception e) {
					LOG.warn("Failed to create and fill child IDoc segment '" + segmentType + "' with child segment '" + childSegment + "'");
				}
			}
			
		}
	}
	
	public static void extractIDocDocumentIntoDocument(IDocDocument idocDocument, Document document) {
		if (document == null || idocDocument == null) {
			LOG.warn("IDoc Document '" + idocDocument + "' not extracted to document '" + document + "'");
			return;
		}
		
		document.setArchiveKey(idocDocument.getArchiveKey());
		document.setClient(idocDocument.getClient());
		document.setCreationDate(idocDocument.getCreationDate());
		document.setCreationTime(idocDocument.getCreationTime());
		document.setEDIMessage(idocDocument.getEDIMessage());
		document.setEDIMessageGroup(idocDocument.getEDIMessageGroup());
		document.setEDIMessageType(idocDocument.getEDIMessageType());
		document.setEDIStandardFlag(idocDocument.getEDIStandardFlag());
		document.setEDIStandardVersion(idocDocument.getEDIStandardVersion());
		document.setEDITransmissionFile(idocDocument.getEDITransmissionFile());
		document.setIDocCompoundType(idocDocument.getIDocCompoundType());
		document.setIDocNumber(idocDocument.getIDocNumber());
		document.setIDocSAPRelease(idocDocument.getIDocSAPRelease());
		document.setIDocType(idocDocument.getIDocType());
		document.setIDocTypeExtension(idocDocument.getIDocTypeExtension());
		document.setMessageCode(idocDocument.getMessageCode());
		document.setMessageFunction(idocDocument.getMessageFunction());
		document.setOutputMode(idocDocument.getOutputMode());
		document.setRecipientAddress(idocDocument.getRecipientAddress());
		document.setRecipientLogicalAddress(idocDocument.getRecipientLogicalAddress());
		document.setRecipientPartnerFunction(idocDocument.getRecipientPartnerFunction());
		document.setRecipientPartnerType(idocDocument.getRecipientPartnerType());
		document.setRecipientPartnerNumber(idocDocument.getRecipientPartnerNumber());
		document.setRecipientPort(idocDocument.getRecipientPort());
		document.setSenderAddress(idocDocument.getSenderAddress());
		document.setSenderLogicalAddress(idocDocument.getSenderLogicalAddress());
		document.setSenderPartnerFunction(idocDocument.getSenderPartnerFunction());
		document.setSenderPartnerNumber(idocDocument.getSenderPartnerNumber());
		document.setSenderPartnerType(idocDocument.getSenderPartnerType());
		document.setSenderPort(idocDocument.getSenderPort());
		document.setSerialization(idocDocument.getSerialization());
		document.setStatus(idocDocument.getStatus());
		document.setTestFlag(idocDocument.getTestFlag());
		
		extractIDocSegmentIntoSegment(idocDocument.getRootSegment(), document.getRootSegment());
		
	}
	
	public static void extractIDocSegmentIntoSegment(IDocSegment idocSegment, Segment segment) {
		if (segment == null || idocSegment == null) {
			LOG.warn("IDoc Document '" + idocSegment + "' not extracted to segment '" + segment + "'");
			return;
		}

		// Fill segment fields
		Iterator<String> it = segment.keySet().iterator();
		while(it.hasNext()) {
			String fieldName = it.next();
			try {
				Object value = idocSegment.getValue(fieldName);
				setValue(segment, fieldName, value);
			} catch (Exception e) {
				LOG.warn("Failed to extract value from field '" + fieldName + "' from IDoc segment to segment");
			}
		}
		
		// Fill child segments
		SegmentChildren segmentChildren = ((SegmentImpl)segment).getSegmentChildren();
		for(String segmentType: segmentChildren.getTypes()) {
			for (IDocSegment childIDocSegment: idocSegment.getChildren(segmentType)) {
				Segment childSegment = segmentChildren.get(segmentType).add();
				extractIDocSegmentIntoSegment(childIDocSegment, childSegment);
			}
			
		}
	}
	
	/**
	 * Creates instance of given IDoc type.
	 * 
	 * @param repository
	 * @param iDocType
	 * @param iDocTypeExtension
	 * @param systemRelease
	 * @param applicationRelease
	 * @return
	 */
	public static Document createIDoc(IDocRepository repository, String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
		// Check that at least IDoc Type has been specified.
		if (iDocType == null || iDocType.length() == 0) {
			throw new IllegalArgumentException("IDoc Type must be specified");
		}
		
		// Convert nulls to empty strings if necessary.
		iDocTypeExtension = iDocTypeExtension == null ? "" : iDocTypeExtension;
		systemRelease = systemRelease == null ? "" : systemRelease;
		applicationRelease = applicationRelease == null ? "" : applicationRelease;
		
		// Get package for IDoc type
		IDocID iDocID = new IDocID(repository.getName(), iDocType, iDocTypeExtension, systemRelease, applicationRelease);
		EPackage ePackage = getEPackage(repository, iDocID.getPackageNamespaceURI());
		if (ePackage == null) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
		}
		
        EClassifier classifier = ePackage.getEClassifier("ROOT");
        if (classifier == null || !(classifier instanceof EClass) ) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
        }
		EClass eClass = (EClass) classifier;
		if (!IdocPackage.eINSTANCE.getSegment().isSuperTypeOf(eClass)) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
		}
		
		Segment segment = (Segment) ePackage.getEFactoryInstance().create(eClass);
		DocumentImpl iDoc = (DocumentImpl) IdocFactory.eINSTANCE.createDocument();
		iDoc.setRootSegment(segment);
		((SegmentImpl)segment).setDocument(iDoc);
		iDoc.setIDocType(iDocType);
		iDoc.setIDocTypeExtension(iDocTypeExtension);
		Date now = new Date();
		iDoc.setCreationDate(now);
		iDoc.setCreationTime(now);
		String idocCompoundType = getAnnotation(segment.eClass(), eNS_URI, IDocNS_COMPOUND_TYPE_KEY);
		if (idocCompoundType == null) {
			iDoc.setIDocCompoundType(idocCompoundType);
		}
		
		return iDoc;
	}
	
	/**
	 * Creates instance of given IDoc type.
	 * 
	 * <p>NB: This is off-line version of method; package of IDoc type must already be already loaded in global package registry. 
	 * @param repositoryName
	 * @param iDocType
	 * @param iDocTypeExtension
	 * @param systemRelease
	 * @param applicationRelease
	 * @return
	 */
	public static Document createIDoc(String repositoryName, String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
		// Check that at lease IDoc Type has been specified.
		if (iDocType == null || iDocType.length() == 0) {
			throw new IllegalArgumentException("IDoc Type must be specified");
		}
		
		// Convert nulls to empty strings if necessary.
		iDocTypeExtension = iDocTypeExtension == null ? "" : iDocTypeExtension;
		systemRelease = systemRelease == null ? "" : systemRelease;
		applicationRelease = applicationRelease == null ? "" : applicationRelease;
		
		// Get package for IDoc type
		IDocID iDocID = new IDocID(repositoryName, iDocType, iDocTypeExtension, systemRelease, applicationRelease);
		EPackage ePackage = registry.getEPackage(iDocID.getPackageNamespaceURI());
		if (ePackage == null) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
		}
		
        EClassifier classifier = ePackage.getEClassifier("ROOT");
        if (classifier == null || !(classifier instanceof EClass) ) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
        }
		EClass eClass = (EClass) classifier;
		if (!IdocPackage.eINSTANCE.getSegment().isSuperTypeOf(eClass)) {
			throw new RuntimeException("Can not create IDoc: meta-data for IDoc type '" + iDocID.getPackageName() + "' does not exist");
		}
		
		Segment segment = (Segment) ePackage.getEFactoryInstance().create(eClass);
		DocumentImpl iDoc = (DocumentImpl) IdocFactory.eINSTANCE.createDocument();
		iDoc.setRootSegment(segment);
		iDoc.setIDocType(iDocType);
		iDoc.setIDocTypeExtension(iDocTypeExtension);
		Date now = new Date();
		iDoc.setCreationDate(now);
		iDoc.setCreationTime(now);
		String idocCompoundType = getAnnotation(segment.eClass(), eNS_URI, IDocNS_COMPOUND_TYPE_KEY);
		if (idocCompoundType == null) {
			iDoc.setIDocCompoundType(idocCompoundType);
		}
		
		return iDoc;
	}
	
	/**
	 * Save IDoc Packages in Global Package Repository to given file.
	 * 
	 * @param file - The file to save packages into. NB: this file must end with '.ecore' extension.
	 * @throws IOException
	 */
	public static void saveRegistry(File file) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".ecore", new EcoreResourceFactoryImpl());
        
        URI fileURI = URI.createFileURI(file.getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);
        
        Set<String> nsURIs = new HashSet<String>();
        nsURIs.addAll(registry.keySet());
		for (String nsURI : nsURIs) {
			if (nsURI.startsWith(IdocPackage.eNS_URI)) {
				EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
				resource.getContents().add(ePackage);
			}
		}
		
        Map<String,Object> options = new HashMap<String,Object>();
        List<Object> lookupTable = new ArrayList<Object>();
        options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
        options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
        options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
        resource.save(options);
	}
	
	/**
	 * Loads IDoc packages stored in given file into Global Package Repository.
	 * @param file - The file to load packages from. NB: this file must end with '.ecore' extension.
	 * @throws IOException
	 */
	public static void loadRegistry(File file) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".ecore", new EcoreResourceFactoryImpl());
        resourceSet.getPackageRegistry().put(eNS_URI, IdocPackage.eINSTANCE);
        
        URI fileURI = URI.createFileURI(file.getAbsolutePath());
        Resource resource = resourceSet.createResource(fileURI);

        Map<String,Object> options = new HashMap<String,Object>();
        XMLParserPool parserPool = new XMLParserPoolImpl();
        Map<Object, Object> nameToFeatureMap = new HashMap<Object, Object>();
        options.put(XMIResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        options.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        options.put(XMIResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        options.put(XMIResource.OPTION_USE_PARSER_POOL, parserPool);
        options.put(XMIResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);
        resource.load(null);
        
        ListIterator<EObject> it = resource.getContents().listIterator();
        while (it.hasNext()) {
        	EObject eObj = it.next();
        	if (eObj instanceof EPackage) {
        		EPackage ePackage = (EPackage) eObj;
        		if (!eNS_URI.endsWith(ePackage.getNsURI())) { // Do not add base IDoc package
        			reattachIDocSuperTypes(ePackage);
        			registry.put(ePackage.getNsURI(), ePackage);
        		}
        	}
        }
        
	}
	
	/**
	 * Re-attaches super types of derived IDoc Documents, Segments,
	 * SegmentChildren and SegmentList types to EClass instances defined by
	 * static IDoc base package. This operation is necessary when loading a
	 * package from storage since its EClasses reference the stored instance of IDoc base
	 * package instead of static package in runtime.
	 * 
	 * @param ePackage - package containing classes whose super types will be re-attached.
	 */
	public static void reattachIDocSuperTypes(EPackage ePackage) {
		if (ePackage.getNsURI().startsWith(IdocPackage.eNS_URI)) {
			for (EClassifier eClassifier: ePackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					EList<EClass> superTypes = eClass.getESuperTypes();
					for (int i = 0; i < superTypes.size(); i++) {
						EClass superClass =  superTypes.get(i);
						switch (superClass.getName()) {
						case "Document":
							superTypes.set(i, IdocPackage.eINSTANCE.getDocument());
							continue;
						case "Segment":
							superTypes.set(i, IdocPackage.eINSTANCE.getSegment());
							continue;
						case "SegmentChildren":
							superTypes.set(i, IdocPackage.eINSTANCE.getSegmentChildren());
							continue;
						case "SegmentList":
							superTypes.set(i, IdocPackage.eINSTANCE.getSegmentList());
							continue;
						}
					}
				}
			}
		}
	}

	/**
	 * Returns (and creates if necessary) the {@link EPackage} instance
	 * containing the root and child {@link Segment} definitions of an
	 * {@link IDocDocument} designated by <code>nsURI</code> and described in
	 * the given <code>repository</code>.
	 * 
	 * @param repository
	 *            - the {@link IDocRepository} containing the meta data
	 *            describing the designated {@link IDocDocument} type.
	 * @param nsURI
	 *            - the URI designating the type of {@link IDocDocument}. The
	 *            URI format is of the form:
	 *            http://sap.fusesource.org/idoc/{repository-name}/{idoc-type}/{idoc-type-extension}/{system-release}/{application-release}.
	 *            <p>Note: Only {repository-name} and {idoc-type} are required; other components can be empty. <b>NB:</b> All '/' however are required.
	 * @return The {@link EPackage} instance.
	 */
	public static EPackage getEPackage(IDocRepository repository, String nsURI) {

		// Check whether the requested package has already been built.
		EPackage ePackage = (EPackage) registry.get(nsURI);
		if (ePackage != null) {
			return ePackage;
		}

		// Retrieve the iDoc's meta data
		IDocID iDocID;
		IDocSegmentMetaData rootSegmentMetaData;
		try {

			iDocID = new IDocID(nsURI);
			// Check whether the requested package is defined by the
			// destination's
			// repository.
			if (!repository.getName().equals(iDocID.repositoryName)) {
				LOG.warn("Repository '" + repository.getName()
						+ "' does not match requested repository '"
						+ iDocID.repositoryName + "' for IDoc type : "
						+ iDocID.iDocType + ", IDoc type extension : "
						+ iDocID.iDocTypeExtension + ", system release : "
						+ iDocID.systemRelease + ", application release : "
						+ iDocID.applicationRelease);
				return null;
			}

			rootSegmentMetaData = repository.getRootSegmentMetaData(
					iDocID.iDocType, iDocID.iDocTypeExtension,
					iDocID.systemRelease, iDocID.applicationRelease);
			if (rootSegmentMetaData == null) {
				LOG.warn("Repository '" + repository.getName()
						+ "' does not contain meta data for IDoc type : "
						+ iDocID.iDocType + ", IDoc type extension : "
						+ iDocID.iDocTypeExtension + ", system release : "
						+ iDocID.systemRelease + ", application release : "
						+ iDocID.applicationRelease);
				return null;
			}
		} catch (Exception e) {
			LOG.warn(e.getLocalizedMessage(), e);
			return null;
		}

		// Create and initialize package
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		ePackage = ecoreFactory.createEPackage();
		ePackage.setName(iDocID.getPackageName());
		ePackage.setNsPrefix(iDocID.getPackageNamespacePrefix());
		ePackage.setNsURI(iDocID.getPackageNamespaceURI());

		// Create ROOT Segment Class
		EClass rootClass = ecoreFactory.createEClass();
		ePackage.getEClassifiers().add(rootClass);
		rootClass.setName("ROOT");
		rootClass.getESuperTypes().add(IdocPackage.eINSTANCE.getSegment());
		addSegmentMetaData(rootClass, rootSegmentMetaData);
		addAnnotation(rootClass, GenNS_URI, GenNS_DOCUMENTATION_KEY, "Segment type for " + iDocID.getPackageName());
		addAnnotation(rootClass, eNS_URI, IDocNS_COMPOUND_TYPE_KEY, repository.getIDocCompoundType(iDocID.getiDocType(), iDocID.getiDocTypeExtension()));

		// Register Package
		registry.put(nsURI, ePackage);

		return ePackage;
	}
	
	public static void addSegmentMetaData(EClass eClass, IDocSegmentMetaData idocSegmentMetaData) {
		if (idocSegmentMetaData == null) {
			return;
		}

		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = eClass.getEPackage();
		
		IDocRecordMetaData idocRecordMetaData = idocSegmentMetaData.getRecordMetaData();
		addAnnotation(eClass, eNS_URI, IDocNS_NAME_KEY, idocRecordMetaData.getName());
		addAnnotation(eClass, eNS_URI, IDocNS_TYPE_KEY, idocSegmentMetaData.getType());
		addAnnotation(eClass, eNS_URI, IDocNS_KEY_KEY, idocSegmentMetaData.getKey());
		addAnnotation(eClass, eNS_URI, IDocNS_DEFINITION_KEY, idocSegmentMetaData.getDefinition());
		addAnnotation(eClass, eNS_URI, IDocNS_DESCRIPTION_KEY, idocSegmentMetaData.getDescription());
		addAnnotation(eClass, eNS_URI, IDocNS_HIERARCHY_LEVEL_KEY, Integer.toString(idocSegmentMetaData.getHierarchyLevel()));
		addAnnotation(eClass, eNS_URI, IDocNS_IDOC_TYPE_KEY, idocSegmentMetaData.getIDocType());
		addAnnotation(eClass, eNS_URI, IDocNS_IDOC_TYPE_EXTENSION_KEY, idocSegmentMetaData.getIDocTypeExtension());
		addAnnotation(eClass, eNS_URI, IDocNS_SYSTEM_RELEASE_KEY, idocSegmentMetaData.getSystemRelease());
		addAnnotation(eClass, eNS_URI, IDocNS_APPLICATION_RELEASE_KEY, idocSegmentMetaData.getApplicationRelease());
		addAnnotation(eClass, eNS_URI, IDocNS_NUM_FIELDS_KEY, Integer.toString(idocRecordMetaData.getNumFields()));
		addAnnotation(eClass, eNS_URI, IDocNS_MAX_OCCURRENCE_KEY, Long.toString(idocSegmentMetaData.getMaxOccurrence()));
		addAnnotation(eClass, eNS_URI, IDocNS_MIN_OCCURRENCE_KEY, Long.toString(idocSegmentMetaData.getMinOccurrence()));
		addAnnotation(eClass, eNS_URI, IDocNS_IS_LOCKED_KEY, Boolean.toString(idocRecordMetaData.isLocked()));
		addAnnotation(eClass, eNS_URI, IDocNS_IS_MANDATORY_KEY, Boolean.toString(idocSegmentMetaData.isMandatory()));
		addAnnotation(eClass, eNS_URI, IDocNS_IS_QUALIFIED_KEY, Boolean.toString(idocSegmentMetaData.isQualified()));
		addAnnotation(eClass, eNS_URI, IDocNS_IS_LOCKED_KEY, Boolean.toString(idocSegmentMetaData.isLocked()));
		addAnnotation(eClass, eNS_URI, IDocNS_RECORD_LENGTH_KEY, Integer.toString(idocRecordMetaData.getRecordLength()));
		for (int i = 0; i < idocRecordMetaData.getNumFields(); i++) {
			EAttribute attribute = ecoreFactory.createEAttribute();
			attribute.setEType(getEDataType(idocRecordMetaData.getType(i)));
			attribute.setName(idocRecordMetaData.getName(i));
			addAnnotation(attribute, GenNS_URI, GenNS_DOCUMENTATION_KEY, idocRecordMetaData.getDescription(i));
			addAnnotation(attribute, eNS_URI, IDocNS_CLASS_NAME_OF_FIELD_KEY, getClassName(idocRecordMetaData.getType(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_POSITION_KEY, Integer.toString(i));
			addAnnotation(attribute, eNS_URI, IDocNS_DESCRIPTION_KEY, idocRecordMetaData.getDescription(i));
			addAnnotation(attribute, eNS_URI, IDocNS_CHECK_TABLE_NAME_KEY, idocRecordMetaData.getCheckTableName(i));
			addAnnotation(attribute, eNS_URI, IDocNS_DATA_ELEMENT_NAME_KEY, idocRecordMetaData.getDataElementName(i));
			addAnnotation(attribute, eNS_URI, IDocNS_DATA_TYPE_KEY, Integer.toString(idocRecordMetaData.getDatatype(i).ordinal()));
			addAnnotation(attribute, eNS_URI, IDocNS_DATA_TYPE_NAME_KEY, idocRecordMetaData.getDataTypeName(i));
			addAnnotation(attribute, eNS_URI, IDocNS_DOMAIN_NAME_KEY, idocRecordMetaData.getDomainName(i));
			addAnnotation(attribute, eNS_URI, IDocNS_INTERNAL_LENGTH_KEY, Integer.toString(idocRecordMetaData.getInternalLength(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_LENGTH_KEY, Integer.toString(idocRecordMetaData.getLength(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_OFFSET_KEY, Integer.toString(idocRecordMetaData.getOffset(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_OUTPUT_LENGTH_KEY, Integer.toString(idocRecordMetaData.getOutputLength(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_RECORD_TYPE_KEY, Integer.toString(idocRecordMetaData.getType(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_TYPE_AS_STRING_KEY, idocRecordMetaData.getTypeAsString(i));
			addAnnotation(attribute, eNS_URI, IDocNS_VALUES_KEY, Arrays.toString(idocRecordMetaData.getValues(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_VALUE_DESCRIPTIONS_KEY, Arrays.toString(idocRecordMetaData.getValueDescriptions(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_VALUE_RANGES_KEY, Arrays.deepToString(idocRecordMetaData.getValueRanges(i)));
			addAnnotation(attribute, eNS_URI, IDocNS_IS_ISO_CODE_KEY, Boolean.toString(idocRecordMetaData.isISOCode(i)));
			eClass.getEStructuralFeatures().add(i, attribute);
		}
		
		// Add Segment Meta Data for Child segments
		EClass segmentListsClass = getSegmentChildrenClass(ePackage, idocSegmentMetaData);
		EReference childrenReference = ecoreFactory.createEReference();
		childrenReference.setEType(segmentListsClass);
		childrenReference.setName("segmentChildren");
		childrenReference.setContainment(true);
		childrenReference.setLowerBound(0);
		childrenReference.setUpperBound(1);
		eClass.getEStructuralFeatures().add(childrenReference);
	}
	
	public static EClass getSegmentChildrenClass(EPackage ePackage, IDocSegmentMetaData iDocSegmentMetaData) {
		
		// Check package to see if segment lists class has already been defined.
		EClassifier segmentListsClass = ePackage.getEClassifier(iDocSegmentMetaData.getName() + "_CHILDREN");

		// Build segment lists class if not already built.
		if (!(segmentListsClass instanceof EClass)) {

			// Create sub-type of SegmentLists for this segment type.
			EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
			segmentListsClass = ecoreFactory.createEClass();
			ePackage.getEClassifiers().add(segmentListsClass);
			segmentListsClass.setName(iDocSegmentMetaData.getType() + "_CHILDREN");
			((EClass) segmentListsClass).getESuperTypes().add(IdocPackage.eINSTANCE.getSegmentChildren());
			
			// Create list for each child segment type
			EAttribute segmentsAttribute = IdocPackage.eINSTANCE.getSegmentChildren_Segments();
			for (IDocSegmentMetaData childIDocSegmentMetaData : iDocSegmentMetaData.getChildren()) {
				EClass childSegmentClass = getSegmentClass(ePackage, childIDocSegmentMetaData);
				EReference reference = ecoreFactory.createEReference();
				((EClass)segmentListsClass).getEStructuralFeatures().add(reference);
				reference.setName(childIDocSegmentMetaData.getType());
				reference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
				reference.setEType(childSegmentClass);
				reference.setContainment(true);
				reference.setVolatile(true);
				reference.setTransient(true);
				reference.setDerived(true);
				ExtendedMetaData.INSTANCE.setGroup(reference, segmentsAttribute);
			}

		}

		return (EClass) segmentListsClass;
	}
	
	public static EClass getSegmentClass(EPackage ePackage, IDocSegmentMetaData idocSegmentMetaData) {
		// Check package to see if structure class has already been defined.
		EClassifier structureClass = ePackage.getEClassifier(idocSegmentMetaData.getName());
		
		// Build Segment class if not already built.
		if (!(structureClass instanceof EClass)) {

			structureClass = EcoreFactory.eINSTANCE.createEClass();
			ePackage.getEClassifiers().add(structureClass);
			structureClass.setName(idocSegmentMetaData.getType());
			((EClass)structureClass).getESuperTypes().add(IdocPackage.eINSTANCE.getSegment());
			addSegmentMetaData(((EClass) structureClass), idocSegmentMetaData);
		}
		return (EClass) structureClass;
	}

	/**
	 * IDoc ID - Validates IDoc package URL and identifies the name, namespace prefix and namespace uri of package.
	 * 
	 * @author punkhorn
	 */
	public static class IDocID {
		private String repositoryName;
		private String iDocType;
		private String iDocTypeExtension;
		private String systemRelease;
		private String applicationRelease;

		public IDocID(String uri) throws Exception {
			if (uri == null || !uri.startsWith(eNS_URI)) {
				throw new IllegalArgumentException(
						"Invalid IDoc namespace uri: " + uri);
			}

			uri = uri.substring(eNS_URI.length());
			String[] components = uri.split("/", 6);
			if (components.length != 6) {
				throw new IllegalArgumentException(
						"Invalid IDoc namespace uri: " + uri);
			}
			repositoryName = components[1];
			iDocType = components[2];
			iDocTypeExtension = components[3];
			systemRelease = components[4];
			applicationRelease = components[5];
		}
		
		
		public IDocID(String repositoryName, String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
			this.repositoryName = repositoryName;
			this.iDocType = iDocType;
			this.iDocTypeExtension = iDocTypeExtension;
			this.systemRelease = systemRelease;
			this.applicationRelease = applicationRelease;
		}


		public String getRepositoryName() {
			return repositoryName;
		}

		public void setRepositoryName(String repositoryName) {
			this.repositoryName = repositoryName;
		}

		public String getiDocType() {
			return iDocType;
		}

		public void setiDocType(String iDocType) {
			this.iDocType = iDocType;
		}

		public String getiDocTypeExtension() {
			return iDocTypeExtension;
		}

		public void setiDocTypeExtension(String iDocTypeExtension) {
			this.iDocTypeExtension = iDocTypeExtension;
		}

		public String getSystemRelease() {
			return systemRelease;
		}

		public void setSystemRelease(String systemRelease) {
			this.systemRelease = systemRelease;
		}

		public String getApplicationRelease() {
			return applicationRelease;
		}

		public void setApplicationRelease(String applicationRelease) {
			this.applicationRelease = applicationRelease;
		}

		public String getPackageName() {
			return iDocType + "_" + iDocTypeExtension + "_" + systemRelease + "_" + applicationRelease;
		}

		public String getPackageNamespacePrefix() {
			return iDocType + "-" + iDocTypeExtension + "-" + systemRelease + "-" + applicationRelease;
		}

		public String getPackageNamespaceURI() {
			return eNS_URI + "/" + repositoryName + "/" + iDocType + "/" + iDocTypeExtension + "/" + systemRelease + "/" + applicationRelease;
		}
	}

	/**
	 * Return the {@link EClassifier} corresponding to the given IDoc Segment Field Type.
	 * 
	 * @param segmentFieldType
	 *            - the IDoc Segment Field Type.
	 * @return the {@link EClassifier} corresponding to the given IDoc Segment Field Type.
	 */
	public static EClassifier getEDataType(int segmentFieldType) {
		switch (segmentFieldType) {

		case IDocRecordMetaData.TYPE_INT:
		case IDocRecordMetaData.TYPE_INT1:
		case IDocRecordMetaData.TYPE_INT2:
			return EcorePackage.Literals.EINT;

		case IDocRecordMetaData.TYPE_CHAR:
			return EcorePackage.Literals.ESTRING;

		case IDocRecordMetaData.TYPE_NUM:
			return EcorePackage.Literals.ESTRING;

		case IDocRecordMetaData.TYPE_BCD:
			return EcorePackage.Literals.EBIG_DECIMAL;

		case IDocRecordMetaData.TYPE_DATE:
			return EcorePackage.Literals.EDATE;

		case IDocRecordMetaData.TYPE_TIME:
			return EcorePackage.Literals.EDATE;

		case IDocRecordMetaData.TYPE_FLOAT:
			return EcorePackage.Literals.EDOUBLE;

		case IDocRecordMetaData.TYPE_BYTE:
			return EcorePackage.Literals.EBYTE_ARRAY;

		case IDocRecordMetaData.TYPE_STRING:
			return EcorePackage.Literals.ESTRING;

		case IDocRecordMetaData.TYPE_XSTRING:
			return EcorePackage.Literals.EBYTE_ARRAY;

		default:
			return EcorePackage.Literals.EBYTE_ARRAY;
		}
	}

	/**
	 * Return the the fully-qualified name of Java class corresponding to the given IDoc Segment Field Type.
	 * 
	 * @param segmentFieldType
	 *            - the IDoc Segment Field Type.
	 * @return the fully-qualified name of Java class corresponding to the given IDoc Segment Field Type.
	 */
	public static String getClassName(int segmentFieldType) {
		switch (segmentFieldType) {

		case IDocRecordMetaData.TYPE_INT:
		case IDocRecordMetaData.TYPE_INT1:
		case IDocRecordMetaData.TYPE_INT2:
			return Integer.class.getName();

		case IDocRecordMetaData.TYPE_CHAR:
			return String.class.getName();

		case IDocRecordMetaData.TYPE_NUM:
			return String.class.getName();

		case IDocRecordMetaData.TYPE_BCD:
			return BigDecimal.class.getName();

		case IDocRecordMetaData.TYPE_DATE:
			return Date.class.getName();

		case IDocRecordMetaData.TYPE_TIME:
			return Date.class.getName();

		case IDocRecordMetaData.TYPE_FLOAT:
			return Double.class.getName();

		case IDocRecordMetaData.TYPE_BYTE:
			return byte[].class.getName();

		case IDocRecordMetaData.TYPE_STRING:
			return String.class.getName();

		case IDocRecordMetaData.TYPE_XSTRING:
			return byte[].class.getName();

		default:
			return byte[].class.getName();
		}
	}

	/**
	 * Adds detail entry to designated annotation of given model element.
	 * 
	 * @param modelElement
	 *            - the model element to be annotated.
	 * @param source
	 *            - the source URL of annotation to be added to.
	 * @param key
	 *            - the key of the detail entry to be added to annotation.
	 * @param value
	 *            - the value of the detail entry to added to annotation.
	 */
	public static void addAnnotation(EModelElement modelElement, String source, String key, String value) {
		EAnnotation annotation = modelElement.getEAnnotation(source);
		if (annotation == null) {
			annotation = EcoreFactory.eINSTANCE.createEAnnotation();
			annotation.setSource(source);
			annotation.setEModelElement(modelElement);
		}
		annotation.getDetails().put(key, value);
	}
	
	public static String getAnnotation(EModelElement modelElement, String source, String key) {
		EAnnotation annotation = modelElement.getEAnnotation(source);
		if (annotation == null) {
			return null;
		}
		return annotation.getDetails().get(key);
	}

	public static int segmentOccurrences(long longValue) {
		if (longValue > Integer.MAX_VALUE) {
			return ETypedElement.UNBOUNDED_MULTIPLICITY;
		} else if (longValue < 0) {
			return 0;
		}
		return (int) longValue;
	}
	public static boolean setValue(EObject object, String featureName, Object value) {
		EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
		if (feature == null)
			return false;
		return setValue(object, feature, value);
	}

	public static boolean setValue(EObject object, EStructuralFeature feature, Object value) {
		try {
			object.eSet(feature, value);
			return true;
		} catch (Throwable exception) {
			LOG.warn("Failed to set value '" + value + "' of attribute '" + feature.getName() + "' on '" + object);
			return false;
		}
	}
	
	/**
	 * Recursively read all objects which are referenced from the passed
	 * <code>eobject</code>. The objects which do not have an econtainer are added to the
	 * <code>rootList</code>. The resulting rootList can be added to the contents of a
	 * resource.
	 * 
	 * @param eobject - object to read references of.
	 * @param preventCycles - set to track sub-objects already read.
	 * @param rootList - all referenced root objects.
	 */
	public static void readReferences(EObject eobject, Set<EObject> preventCycles, List<EObject> rootList) {
		if (preventCycles.contains(eobject)) { // been here get away
			return;
		}
		preventCycles.add(eobject);
		if (eobject.eContainer() != null) {
			readReferences(eobject.eContainer(), preventCycles, rootList);
		} else { // a root object
			rootList.add(eobject);
		}
		for (Object erefObj : eobject.eClass().getEAllReferences()) {
			EReference eref = (EReference) erefObj;
			final Object value = eobject.eGet(eref);
			if (value == null) {
				continue;
			}
			if (value instanceof List) {
				for (Object obj : (List<?>) value) {
					readReferences((EObject) obj, preventCycles, rootList);
				}
			} else { // an eobject
				readReferences((EObject) value, preventCycles, rootList);
			}
		}
	}
}
