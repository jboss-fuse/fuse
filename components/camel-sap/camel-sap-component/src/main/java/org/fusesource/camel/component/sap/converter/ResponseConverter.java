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
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.camel.Converter;
import org.eclipse.emf.ecore.EObject;
import org.fusesource.camel.component.sap.model.rfc.Response;
import org.fusesource.camel.component.sap.model.rfc.impl.ResponseImpl;
import org.fusesource.camel.component.sap.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Type Converter for SAP Response objects.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
@Converter
public enum ResponseConverter {
	INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(ResponseConverter.class);

	@Converter
	public static Response toResponse(String string) {
		try {
			EObject eObject = Util.unmarshal(string);
			
			if (ResponseImpl.class.isInstance(eObject)) {
				return (ResponseImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert String to Response", e);
		} 
		return null; 
	}

	@Converter
	public static Response toResponse(InputStream in) {
		try {
			EObject eObject = Util.fromInputStream(in);
			
			if (ResponseImpl.class.isInstance(eObject)) {
				return (ResponseImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert InputStream to Response", e);
		} 
		return null; 
	}

	@Converter
	public static Response toResponse(byte[] byteArray) {
		try {
			EObject eObject = Util.unmarshal(new String(byteArray));
			
			if (ResponseImpl.class.isInstance(eObject)) {
				return (ResponseImpl) eObject;
			}
		} catch (IOException e) {
			// Ignore
			LOG.warn("Failed to convert byte array to Response", e);
		} 
		return null; 
	}

	@Converter
	public static String toString(ResponseImpl structure) {
		try {
			return Util.marshal(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Response to String", e);
			return null;
		}
	}
	
	@Converter
	public static OutputStream toOutputStream(ResponseImpl structure) {
		try {
			return Util.toOutputStream(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Response to OutputStream", e);
			return null;
		}
	}
	
	@Converter
	public static InputStream toInputStream(ResponseImpl structure) {
		try {
			return Util.toInputStream(structure);
		} catch (IOException e) {
			LOG.warn("Failed to convert Response to InputStream", e);
			return null;
		}
	}
	
}
