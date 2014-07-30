package org.fusesource.camel.component.sap.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.xml.sax.InputSource;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoRequest;

import static org.fusesource.camel.component.sap.model.idoc.IdocPackage.eNS_URI;

/**
 * @author punkhorn
 *
 */
public class Util {

	public static final Registry registry = EPackage.Registry.INSTANCE;

	/**
	 * Marshals the given {@link EObject} into a string.
	 * 
	 * @param eObject
	 *            - the {@link EObject} to be marshaled.
	 * @return The marshaled content of {@link EObject}.
	 * @throws IOException
	 */
	public static String marshal(EObject eObject) throws IOException {
		URI uri = URI.createFileURI("/"); // ensure relative reference URIs
		XMLResource resource = new XMLResourceImpl(uri);
		eObject = EcoreUtil.copy(eObject);
		resource.getContents().add(eObject);
		StringWriter out = new StringWriter();

		Map<String, Object> options = new HashMap<String, Object>();
		List<Object> lookupTable = new ArrayList<Object>();
		options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
		options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
		options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		resource.save(out, options);
		return out.toString();
	}

	/**
	 * Unmarshals the given string content into an {@link EObject} instance.
	 * 
	 * @param string
	 *            - the string content to unmarshal.
	 * @return The {@link EObject} instance unmarshaled from the string
	 *         content.
	 * @throws IOException
	 */
	public static EObject unmarshal(String string) throws IOException {
		URI uri = URI.createFileURI("/"); // ensure relative reference URIs
		XMLResource resource = new XMLResourceImpl(uri);
		StringReader in = new StringReader(string);

		Map<String, Object> options = new HashMap<String, Object>();
		XMLParserPool parserPool = new XMLParserPoolImpl();
		Map<Object, Object> nameToFeatureMap = new HashMap<Object, Object>();
		options.put(XMIResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		options.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_PARSER_POOL, parserPool);
		options.put(XMIResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);
		options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		resource.load(new InputSource(in), options);
		return resource.getContents().get(0);
	}

	/**
	 * Saves the given <code>eObject</code> to the <code>file</code>.
	 * 
	 * @param file
	 *            - the file to save <code>eObject</code> to.
	 * @param eObject
	 *            - the object to save.
	 * @throws IOException
	 */
	public static void save(File file, EObject eObject) throws IOException {
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = new XMLResourceImpl(uri);
		eObject = EcoreUtil.copy(eObject);
		res.getContents().add(eObject);
		Map<String, Object> options = new HashMap<String, Object>();
		List<Object> lookupTable = new ArrayList<Object>();
		options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
		options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
		options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		res.save(options);
	}

	/**
	 * Loads an {@link EObject} from the <code>file</code>.
	 * 
	 * @param file
	 *            - the file to load {@link EObject} from.
	 * @return The {@link EObject} loaded from <code>file</code>.
	 * @throws IOException
	 */
	public static EObject load(File file) throws IOException {
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = new XMLResourceImpl(uri);

		Map<String, Object> options = new HashMap<String, Object>();
		XMLParserPool parserPool = new XMLParserPoolImpl();
		Map<Object, Object> nameToFeatureMap = new HashMap<Object, Object>();
		options.put(XMIResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		options.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_PARSER_POOL, parserPool);
		options.put(XMIResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, nameToFeatureMap);
		options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		res.load(options);
		return res.getContents().get(0);
	}

	/**
	 * Sets the given <code>value</code> on the named feature of
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            - the object to set <code>value</code> on.
	 * @param featureName
	 *            - the name of the feature to set.
	 * @param value
	 *            - the value to set.
	 * @return <code>true</code> if the <code>value</code> was set;
	 *         <code>false</code> otherwise.
	 */
	public static boolean setValue(EObject eObject, String featureName, Object value) {
		EStructuralFeature feature = eObject.eClass().getEStructuralFeature(featureName);
		if (feature == null)
			return false;
		return setValue(eObject, feature, value);
	}

	/**
	 * Sets the given <code>value</code> on the <code>feature</code> of
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            - the object to set <code>value</code> on.
	 * @param feature
	 *            - the feature to set.
	 * @param value
	 *            - the value to set.
	 * @return <code>true</code> if the <code>value</code> was set;
	 *         <code>false</code> otherwise.
	 */
	public static boolean setValue(EObject eObject, EStructuralFeature feature, Object value) {
		try {
			EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
			if (editingDomain == null) {
				eObject.eSet(feature, value);
			} else {
				Command setCommand = SetCommand.create(editingDomain, eObject, feature, value);
				editingDomain.getCommandStack().execute(setCommand);
			}
			return true;
		} catch (Throwable exception) {
			return false;
		}
	}

	/**
	 * Gets the given <code>value</code> of the <code>feature</code> from
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            - the object to get <code>value</code> from.
	 * @param feature
	 *            - the feature to get.
	 * @return The value of the feature.
	 */
	public static Object getValue(EObject eObject, EStructuralFeature feature) {
		try {
			Object value = eObject.eGet(feature);
			if (value == null && feature instanceof EReference) {
				EClass eClass = ((EReference) feature).getEReferenceType();
				value = eClass.getEPackage().getEFactoryInstance().create(eClass);
				setValue(eObject, feature, value);
			}
			return value;
		} catch (Throwable exception) {
			return null;
		}
	}

	/**
	 * Gets the given <code>value</code> of the named feature from
	 * <code>eObject</code>.
	 * 
	 * @param eObject
	 *            - the object to get <code>value</code> from.
	 * @param featureName
	 *            - the name of feature to get.
	 * @return The value of the feature.
	 */
	public static Object getValue(EObject object, String featureName) {
		EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
		if (feature == null)
			return null;
		return getValue(object, feature);
	}

	/**
	 * Serializes <code>eObject</code> to returned output stream.
	 * 
	 * @param eObject
	 *            - the object to serialize.
	 * @return The output stream containing serialized object.
	 * @throws IOException
	 */
	public static OutputStream toOutputStream(EObject eObject) throws IOException {
		XMLResource resource = new XMLResourceImpl();
		eObject = EcoreUtil.copy(eObject);
		resource.getContents().add(eObject);
		OutputStream out = new ByteArrayOutputStream();
		resource.save(out, null);
		return out;
	}

	/**
	 * Prints <code>eObject</code> to standard out.
	 * 
	 * @param eObject
	 *            - the object to print.
	 * @throws IOException
	 */
	public static void print(EObject eObject) throws IOException {
		XMLResource resource = new XMLResourceImpl();
		resource.getContents().add(eObject);
		resource.save(System.out, null);
	}

	/**
	 * Serializes <code>eObject</code> to returned input stream.
	 * 
	 * @param eObject
	 *            - the object to serialize.
	 * @return The input stream containing serialized object.
	 * @throws IOException
	 */
	public static InputStream toInputStream(EObject eObject) throws IOException {
		String string = marshal(eObject);
		ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
		return in;
	}

	/**
	 * Unserializes object from <code>in</code>.
	 * 
	 * @param in
	 *            - The input steam containing serialized object.
	 * @return The unserialized object.
	 * @throws IOException
	 */
	public static EObject fromInputStream(InputStream in) throws IOException {
		XMLResource resource = new XMLResourceImpl();
		resource.load(in, null);
		return resource.getContents().get(0);
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

	/**
	 * Returns the value of the annotation from <code>modelElement</code>
	 * identified by <code>source</code> and <code>key</code>.
	 * 
	 * @param modelElement
	 *            - the annotated model element.
	 * @param source
	 *            - the namespace of annotation.
	 * @param key
	 *            - the key of annotation.
	 * @return The value of annotation.
	 */
	public static String getAnnotation(EModelElement modelElement, String source, String key) {
		EAnnotation annotation = modelElement.getEAnnotation(source);
		if (annotation == null) {
			return null;
		}
		return annotation.getDetails().get(key);
	}

	/**
	 * Begin transaction on given destination.
	 * 
	 * @param jcoDestination
	 *            = the destination to start transaction on.
	 */
	public static void beginTransaction(JCoDestination jcoDestination) {
		JCoContext.begin(jcoDestination);
	}

	/**
	 * Commit transaction on given transaction.
	 * 
	 * @param jcoDestination
	 *            = the destination to commit transaction on.
	 * @throws JCoException
	 */
	public static void commitTransaction(JCoDestination jcoDestination) throws JCoException {
		try {
			JCoRequest request = jcoDestination.getRepository().getRequest("BAPI_TRANSACTION_COMMIT");
			request.setValue("WAIT", "X");
			request.execute(jcoDestination);
		} finally {
			JCoContext.end(jcoDestination);
		}
	}

	/**
	 * Rollback transaction on given transaction.
	 * 
	 * @param jcoDestination
	 *            = the destination to rollback transaction on.
	 * @throws JCoException
	 */
	public static void rollbackTransaction(JCoDestination jcoDestination) throws JCoException {
		try {
			JCoRequest request = jcoDestination.getRepository().getRequest("BAPI_TRANSACTION_ROLLBACK");
			request.execute(jcoDestination);
		} finally {
			JCoContext.end(jcoDestination);
		}
	}

	/**
	 * Save packages in Global Package Repository to given file.
	 * 
	 * @param file
	 *            - The file to save packages into. NB: this file must end with
	 *            '.ecore' extension.
	 * @throws IOException
	 */
	public static void saveRegistry(File file) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

		URI fileURI = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(fileURI);

		Set<String> nsURIs = new HashSet<String>();
		nsURIs.addAll(registry.keySet());
		for (String nsURI : nsURIs) {
			if (nsURI.startsWith(IdocPackage.eNS_URI) || nsURI.startsWith(RfcPackage.eNS_URI)) {
				EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
				resource.getContents().add(ePackage);
			}
		}

		Map<String, Object> options = new HashMap<String, Object>();
		List<Object> lookupTable = new ArrayList<Object>();
		options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
		options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
		resource.save(options);
	}

/**
	 * Loads packages stored in given file into Global Package Repository.
	 * 
	 * @param file
	 *            - The file to load packages from. NB: this file must end with
	 *            '.ecore' extension.
	 * @throws IOException
	 */
	public static void loadRegistry(File file) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(eNS_URI, IdocPackage.eINSTANCE);

		URI fileURI = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(fileURI);

		Map<String, Object> options = new HashMap<String, Object>();
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
				if (!(IdocPackage.eNS_URI.endsWith(ePackage.getNsURI()) || RfcPackage.eNS_URI.endsWith(ePackage.getNsURI()))) {
					// Only add non static packages to registry.
					reattachStaticPackageSuperTypes(ePackage);
					registry.put(ePackage.getNsURI(), ePackage);
				}
			}
		}

	}

	/**
	 * Re-attaches super types of Dynamic types derived from classes defined in static packaged.
	 * This operation is necessary when loading a
	 * package from storage since its EClasses reference the stored instance of
	 * static base package classes instead of static package in runtime.
	 * 
	 * @param ePackage
	 *            - package containing classes whose super types will be
	 *            re-attached.
	 */
	public static void reattachStaticPackageSuperTypes(EPackage ePackage) {
		if (ePackage.getNsURI().startsWith(IdocPackage.eNS_URI)) {
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					EList<EClass> superTypes = eClass.getESuperTypes();
					for (int i = 0; i < superTypes.size(); i++) {
						EClass superClass = superTypes.get(i);
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
		} else if (ePackage.getNsURI().startsWith(RfcPackage.eNS_URI)) {
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					EList<EClass> superTypes = eClass.getESuperTypes();
					for (int i = 0; i < superTypes.size(); i++) {
						EClass superClass = superTypes.get(i);
						switch (superClass.getName()) {
						case "AbapException":
							superTypes.set(i, RfcPackage.eINSTANCE.getAbapException());
							continue;
						case "Destination":
							superTypes.set(i, RfcPackage.eINSTANCE.getDestination());
							continue;
						case "DestinationData":
							superTypes.set(i, RfcPackage.eINSTANCE.getDestinationData());
							continue;
						case "DestinationDataEntry":
							superTypes.set(i, RfcPackage.eINSTANCE.getDestinationDataEntry());
							continue;
						case "DestinationDataStore":
							superTypes.set(i, RfcPackage.eINSTANCE.getDestinationDataStore());
							continue;
						case "DestinationDataStoreEntry":
							superTypes.set(i, RfcPackage.eINSTANCE.getDestinationDataStoreEntry());
							continue;
						case "FieldMetaData":
							superTypes.set(i, RfcPackage.eINSTANCE.getFieldMetaData());
							continue;
						case "FunctionTemplate":
							superTypes.set(i, RfcPackage.eINSTANCE.getFunctionTemplate());
							continue;
						case "ListFieldMetaData":
							superTypes.set(i, RfcPackage.eINSTANCE.getListFieldMetaData());
							continue;
						case "RecordMetaData":
							superTypes.set(i, RfcPackage.eINSTANCE.getRecordMetaData());
							continue;
						case "RepositoryData":
							superTypes.set(i, RfcPackage.eINSTANCE.getRepositoryData());
							continue;
						case "RepositoryDataStore":
							superTypes.set(i, RfcPackage.eINSTANCE.getRepositoryDataStore());
							continue;
						case "RepositoryDataEntry":
							superTypes.set(i, RfcPackage.eINSTANCE.getRepositoryDataEntry());
							continue;
						case "RepositoryDataStoreEntry":
							superTypes.set(i, RfcPackage.eINSTANCE.getRepositoryDataStoreEntry());
							continue;
						case "RFC":
							superTypes.set(i, RfcPackage.eINSTANCE.getRFC());
							continue;
						case "Server":
							superTypes.set(i, RfcPackage.eINSTANCE.getServer());
							continue;
						case "ServerData":
							superTypes.set(i, RfcPackage.eINSTANCE.getServerData());
							continue;
						case "ServerDataEntry":
							superTypes.set(i, RfcPackage.eINSTANCE.getServerDataEntry());
							continue;
						case "ServerDataStore":
							superTypes.set(i, RfcPackage.eINSTANCE.getServerDataStore());
							continue;
						case "Request":
							superTypes.set(i, RfcPackage.eINSTANCE.getRequest());
							continue;
						case "Response":
							superTypes.set(i, RfcPackage.eINSTANCE.getResponse());
							continue;
						case "Structure":
							superTypes.set(i, RfcPackage.eINSTANCE.getStructure());
							continue;
						case "Table":
							superTypes.set(i, RfcPackage.eINSTANCE.getTable());
							continue;
						}
					}
				}
			}
		}
	}

}
