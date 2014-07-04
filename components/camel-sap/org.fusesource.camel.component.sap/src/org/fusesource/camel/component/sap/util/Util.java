package org.fusesource.camel.component.sap.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLParserPool;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.xml.sax.InputSource;

public class Util {

	/**
	 * Marshals the given {@link EObject} into a string.
	 * 
	 * @param eObject
	 *            - the {@link EObject} to be marshalled.
	 * @return The marshaled content of {@link EObject}.
	 * @throws IOException
	 */
	public static String marshal(EObject eObject) throws IOException {
		URI uri = URI.createFileURI("/"); // ensure relative reference URIs
		XMLResource resource = new XMLResourceImpl(uri);
		eObject = EcoreUtil.copy(eObject);
		resource.getContents().add(eObject);
		StringWriter out = new StringWriter();

		Map<String,Object> options = new HashMap<String,Object>();
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
	 * @return The {@link EObject} instance unmarshalled from the string
	 *         content.
	 * @throws IOException
	 */
	public static EObject unmarshal(String string) throws IOException {
		URI uri = URI.createFileURI("/"); // ensure relative reference URIs
		XMLResource resource = new XMLResourceImpl(uri);
		StringReader in = new StringReader(string);
		
        Map<String,Object> options = new HashMap<String,Object>();
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
	
	public static void save(File file, EObject eObject) throws IOException {
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = new XMLResourceImpl(uri);
		eObject = EcoreUtil.copy(eObject);
		res.getContents().add(eObject);
        Map<String,Object> options = new HashMap<String,Object>();
        List<Object> lookupTable = new ArrayList<Object>();
        options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
        options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
        options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
        options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		res.save(options);
	}
	
	public static EObject load(File file) throws IOException {
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = new XMLResourceImpl(uri);
		
        Map<String,Object> options = new HashMap<String,Object>();
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

}
