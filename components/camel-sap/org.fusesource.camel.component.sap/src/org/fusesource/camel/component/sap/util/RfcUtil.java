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
package org.fusesource.camel.component.sap.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.fusesource.camel.component.sap.model.rfc.AbapException;
import org.fusesource.camel.component.sap.model.rfc.DataType;
import org.fusesource.camel.component.sap.model.rfc.Destination;
import org.fusesource.camel.component.sap.model.rfc.FieldMetaData;
import org.fusesource.camel.component.sap.model.rfc.FunctionTemplate;
import org.fusesource.camel.component.sap.model.rfc.ListFieldMetaData;
import org.fusesource.camel.component.sap.model.rfc.RFC;
import org.fusesource.camel.component.sap.model.rfc.RecordMetaData;
import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.model.rfc.Request;
import org.fusesource.camel.component.sap.model.rfc.Response;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.model.rfc.Table;

import com.sap.conn.jco.JCo;
import com.sap.conn.jco.JCoCustomRepository;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoListMetaData;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoRecordMetaData;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;

import static org.fusesource.camel.component.sap.model.rfc.RfcPackage.eNS_URI;

/**
 * Utility routines to create and manipulate Data Layer RFC Request and Response
 * types and instances.
 * 
 * @author punkhorn
 * 
 */
public class RfcUtil extends Util {

	public static final String ROW = "row";

	public static final String GenNS_URI = "http://www.eclipse.org/emf/2002/GenModel";

	public static final String GenNS_DOCUMENTATION_KEY = "documentation";
	
	/**
	 * Details key for a structure annotation providing the name of
	 * the structure.
	 */
	public static final String RfcNS_NAME_KEY = "name";

	/**
	 * Details key for a structure annotation providing the number of
	 * fields in the structure.
	 */
	public static final String RfcNS_FIELD_COUNT_KEY = "fieldCount";

	/**
	 * Details key for a table annotation providing the line type of
	 * table
	 */
	public static final String RfcNS_LINE_TYPE_KEY = "lineType";

	/**
	 * Details key for a structure annotation providing the record length
	 * of structure.
	 */
	public static final String RfcNS_RECORD_LENGTH_KEY = "recordLength";

	/**
	 * Details key for a structure annotation providing the unicode record length
	 * of structure.
	 */
	public static final String RfcNS_UNICODE_RECORD_LENGTH_KEY = "unicodeRecordLength";

	/**
	 * Details key for a structure annotation indicating whether structure
	 * represents a nested <code>TYPE1</code> JCo structure. A
	 * <code>TYPE1</code> JCo structure has a fixed length in memory, but with a
	 * nested structure.
	 * <p>
	 * The details value is <code>true</code> if the structure represents a
	 * <code>TYPE1</code> structure; <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY = "isNestedType1Structure";

	/**
	 * Details key for a parameter list entry annotation providing the
	 * description of the underlying JCo data field represented by the annotated
	 * parameter list entry. The details value is the description of the
	 * parameter list entry and its underlying JCo data field.
	 */
	public static final String RfcNS_DESCRIPTION_KEY = "description";

	/**
	 * Details key for a parameter list entry annotation providing the
	 * fully-qualified Java classname of the annotated parameter list entry. The
	 * details value is the fully-qualified Java classname of the parameter list
	 * entry.
	 */
	public static final String RfcNS_CLASS_NAME_OF_FIELD_KEY = "classNameOfField";

	/**
	 * Details key for a parameter list entry annotation indicating the table,
	 * structure or data element name of the underlying JCo data field
	 * represented by the annotated parameter list entry. The details value is
	 * the name of the table, structure or data element of the underlying JCo
	 * data field. The details values is <code>null</code> if the data element
	 * name is unavailable.
	 */
	public static final String RfcNS_RECORD_TYPE_NAME_KEY = "recordTypeName";

	/**
	 * Details key for a parameter list entry annotation providing the JCo data
	 * type of the underlying data field represented by the annotated parameter
	 * list entry. The details value is the JCo data type of the underlying data
	 * field represented by the parameter list entry.
	 */
	public static final String RfcNS_TYPE_KEY = "type";

	/**
	 * Details key for a parameter list entry annotation providing the String
	 * representation of the JCo data type of the underlying data field
	 * represented by the annotated parameter list entry. The details value is
	 * the String representation of the JCo data type of the underlying data
	 * field represented by the parameter list entry.
	 */
	public static final String RfcNS_TYPE_AS_STRING_KEY = "typeAsString";

	/**
	 * Details key for a record entry annotation providing the length of the
	 * underlying data field for Unicode layout represented by the annotated
	 * record entry. The details value is the length of the underlying data
	 * field for Unicode layout represented by the record entry.
	 */
	public static final String RfcNS_UNICODE_BYTE_LENGTH_KEY = "unicodeByteLength";

	/**
	 * Details key for a record entry annotation providing the offset of the
	 * underlying data field in a Unicode layout represented by the annotated
	 * record entry. The details value is the byte offset of the underlying data
	 * field in a Non-Unicode layout represented by the record entry.
	 */
	public static final String RfcNS_UNICODE_BYTE_OFFSET_KEY = "unicodeByteOffset";

	/**
	 * Details key for a parameter list entry annotation providing the byte
	 * length of the underlying data field for Non-Unicode layout represented by
	 * the annotated record entry. The details value is the byte length of the
	 * underlying data field for Non-Unicode layout represented by the parameter
	 * list entry.
	 */
	public static final String RfcNS_BYTE_LENGTH_KEY = "byteLength";

	/**
	 * Details key for a record entry annotation providing the offset of the
	 * underlying data field in a Non-Unicode layout represented by the
	 * annotated parameter list entry. The details value is the byte offset of
	 * the underlying data field in a Non-Unicode layout represented by the
	 * record entry.
	 */
	public static final String RfcNS_BYTE_OFFSET_KEY = "byteOffset";

	/**
	 * Details key for a parameter list entry annotation providing the max
	 * length of the underlying data field represented by the annotated
	 * parameter list entry. The details value is the max length of the
	 * underlying data field represented by the parameter list entry.
	 * 
	 * <ul>
	 * <li>
	 * <p>
	 * For character based data element types the length is the char length.
	 * <li>
	 * <p>
	 * For <em><b>STRING</b></em> and <em><b>XSTRING</b></em> data element types
	 * the length is <em><b>0</b></em>.</li>
	 * <li>
	 * <p>
	 * For <em><b>STRUCTURE</b></em> and <em><b>TABLE</b></em> data element
	 * types the length is <em><b>0</b></em>.</li>
	 * <li>
	 * <p>
	 * For numerical based data element types the length is the byte length.
	 * </ul>
	 */
	public static final String RfcNS_LENGTH_KEY = "length";

	/**
	 * Details key for a parameter list entry annotation providing the number of
	 * decimals in the the underlying data field represented by the annotated
	 * parameter list entry. The details value is the number of decimals in the
	 * the underlying data field represented by the parameter list entry.
	 * 
	 * <p>
	 * The details value is possibly non-zero only for record entries whose
	 * underlying data field has the JCo data type <em><b>BCD</b></em> or
	 * <em><b>FLOAT</b></em>.
	 */
	public static final String RfcNS_DECIMALS_KEY = "decimals";

	/**
	 * Details key for a parameter list entry annotation providing the default
	 * value of the annotated parameter list entry. The details value is the
	 * default value of the parameter list entry.
	 */
	public static final String RfcNS_DEFAULT_KEY = "default";

	/**
	 * Details key for a parameter list entry annotation providing the field
	 * name in an underlying JCo structure or table represented by the annotated
	 * parameter list entry. The details value is the name of the field in the
	 * underlying JCo structure or table if the JCo parameter represented by the
	 * entry is defined by referencing that field. The details values is
	 * <code>null</code> otherwise.
	 */
	public static final String RfcNS_RECORD_FIELD_NAME_KEY = "recordFieldName";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * annotated parameter list entry represents an underlying JCo
	 * <code>ABAP Object</code>. The details value is <code>true</code> if the
	 * parameter list entry represents an <code>ABAP Object</code>;
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_ABAP_OBJECT_KEY = "isAbapObject";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * annotated parameter list entry is a <em><b>structure</b></em> type entry;
	 * i.e. is a {@link MappedRecord}. The details value is <code>true</code> if
	 * the parameter list entry is a {@link MappedRecord}; <code>false</code>
	 * otherwise.
	 */
	public static final String RfcNS_IS_STRUCTURE_KEY = "isStructure";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * annotated parameter list entry is a <em><b>table</b></em> type entry;
	 * i.e. is an {@link IndexedRecord}. The details value is <code>true</code>
	 * if the parameter list entry is a {@link IndexedRecord};
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_TABLE_KEY = "isTable";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * underlying data field represented by the annotated parameter list entry
	 * is an <em><b>import</b></em> parameter. The details value is
	 * <code>true</code> if the underlying data field is an import parameter;
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_IMPORT_KEY = "isImport";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * underlying data field represented by the annotated parameter list entry
	 * is an <em><b>export</b></em> parameter. The details value is
	 * <code>true</code> if the underlying data field is an export parameter;
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_EXPORT_KEY = "isExport";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * underlying data field represented by the annotated parameter list entry
	 * is a <em><b>changing</b></em> parameter. The details value is
	 * <code>true</code> if the underlying data field is a changing parameter;
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_CHANGING_KEY = "isChanging";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * underlying data field represented by the annotated parameter list entry
	 * is an <em><b>exception</b></em>. The details value is <code>true</code>
	 * if the underlying data field is an exception; <code>false</code>
	 * otherwise.
	 */
	public static final String RfcNS_IS_EXCEPTION_KEY = "isException";

	/**
	 * Details key for a parameter list entry annotation indicating whether the
	 * underlying data field represented by the annotated parameter list entry
	 * is an <em><b>optional</b></em> parameter. The details value is
	 * <code>true</code> if the underlying data field is an optional parameter;
	 * <code>false</code> otherwise.
	 */
	public static final String RfcNS_IS_OPTIONAL_KEY = "isOptional";

	private static final String STEXT_PARAM = "STEXT";
	private static final String FUNCTIONS_TABLE = "FUNCTIONS";
	private static final String FUNCNAME_PARAM = "FUNCNAME";
	private static final String RFC_FUNCTION_SEARCH_FUNCTION = "RFC_FUNCTION_SEARCH";
	private static final String GROUPNAME_PARAM = "GROUPNAME";

	/**
	 * Returns {@link Destination} designated by <code>destinationName</code>.
	 * 
	 * @param destinationName
	 *            - the name of destination.
	 * @return The designated destination or <code>null</code> if none exists.
	 */
	public static Destination getDestination(String destinationName) {
		try {
			JCoDestination jcoDestination = JCoDestinationManager.getDestination(destinationName);
			Destination destination = RfcFactory.eINSTANCE.createDestination();
			String repositoryName = jcoDestination.getRepository().getName();
			destination.setName(destinationName);
			destination.setRepositoryName(repositoryName);
			return destination;
		} catch (JCoException e) {
			return null;
		}
	}

	/**
	 * Returns the filtered list of remote function modules defined in
	 * <code>jcoDestination</code>.
	 * 
	 * @param jcoDestination
	 *            - the destination containing remote function modules.
	 * @param functionNameFilter
	 *            - the function name filter
	 * @param groupNameFilter
	 *            - the group name filter.
	 * @return The list of filtered remote function modules.
	 */
	public static List<RFC> getRFCs(JCoDestination jcoDestination, String functionNameFilter, String groupNameFilter) {
		List<RFC> rfcs = new ArrayList<RFC>();
		try {
			JCoFunction jcoFunction = jcoDestination.getRepository().getFunction(RFC_FUNCTION_SEARCH_FUNCTION);
			jcoFunction.getImportParameterList().setValue(FUNCNAME_PARAM, functionNameFilter);
			jcoFunction.getImportParameterList().setValue(GROUPNAME_PARAM, groupNameFilter);
			jcoFunction.execute(jcoDestination);
			JCoTable sapFunctions = jcoFunction.getTableParameterList().getTable(FUNCTIONS_TABLE);

			if (sapFunctions.getNumRows() > 0) {
				sapFunctions.firstRow();
				do {
					RFC rfc = RfcFactory.eINSTANCE.createRFC();
					String functionName = sapFunctions.getString(FUNCNAME_PARAM);
					String groupName = sapFunctions.getString(GROUPNAME_PARAM);
					rfc.setName(functionName);
					rfc.setGroup(groupName);
					String functionDescription = sapFunctions.getString(STEXT_PARAM);
					rfc.setDescription(functionDescription);
					rfcs.add(rfc);
				} while (sapFunctions.nextRow());
			}
		} catch (JCoException e) {
			// Assume No Function Found
		}
		return rfcs;
	}

	/**
	 * Performs SRFC call of named remote function module with <code>request</code> at
	 * <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination containing remote function module.
	 * @param functionName
	 *            - name of remote function module.
	 * @param request
	 *            - the request passed.
	 * @return The response.
	 * @throws JCoException
	 */
	public static Structure executeFunction(JCoDestination destination, String functionName, Structure request) throws JCoException {
		JCoFunction jcoFunction = destination.getRepository().getFunction(functionName);
		fillJCoParameterListsFromRequest(request, jcoFunction);

		jcoFunction.execute(destination);

		Structure response = getResponse(destination.getRepository(), functionName);
		extractJCoParameterListsIntoResponse(jcoFunction, response);

		return response;
	}

	/**
	 * Performs TRFC call of named remote function module with
	 * <code>request</code> at <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination containing remote function module.
	 * @param functionName
	 *            - name of remote function module.
	 * @param request
	 *            - the request passed.
	 * @param tid
	 *            - the transaction ID.
	 * @throws JCoException
	 */
	public static void executeFunction(JCoDestination destination, String functionName, Structure request, String tid) throws JCoException {

		JCoFunction jcoFunction = destination.getRepository().getFunction(functionName);
		fillJCoParameterListsFromRequest(request, jcoFunction);

		jcoFunction.execute(destination, tid);
	}

	/**
	 * Performs QRFC call of named remote function module with
	 * <code>request</code> at <code>destination</code>.
	 * 
	 * @param destination
	 *            - the destination containing remote function module.
	 * @param functionName
	 *            - name of remote function module.
	 * @param request
	 *            - the request passed.
	 * @param tid
	 *            - the transaction ID.
	 * @param queueName
	 *            - the name of queue
	 * @throws JCoException
	 */
	public static void executeFunction(JCoDestination destination, String functionName, Structure request, String tid, String queueName) throws JCoException {
		JCoFunction jcoFunction = destination.getRepository().getFunction(functionName);
		fillJCoParameterListsFromRequest(request, jcoFunction);

		jcoFunction.execute(destination, tid, queueName);
	}

	/**
	 * Fill <code>jcoFunction</code> parameter lists with parameters from
	 * <code>request</code>.
	 * 
	 * @param request
	 *            - the request containing the values.
	 * @param jcoFunction
	 *            - the function with parameters list to be filled.
	 */
	public static void fillJCoParameterListsFromRequest(Structure request, JCoFunction jcoFunction) {
		fillJCoRecordFromStructure(request, jcoFunction.getImportParameterList());
		fillJCoRecordFromStructure(request, jcoFunction.getChangingParameterList());
		fillJCoRecordFromStructure(request, jcoFunction.getTableParameterList());
	}

	/**
	 * Fill <code>jcoFunction</code> parameter lists with parameters from
	 * <code>response</code>.
	 * 
	 * @param response
	 *            - the response containing the values.
	 * @param jcoFunction
	 *            - the function with parameters list to be filled.
	 */
	public static void fillJCoParameterListsFromResponse(Structure response, JCoFunction jcoFunction) {
		fillJCoRecordFromStructure(response, jcoFunction.getChangingParameterList());
		fillJCoRecordFromStructure(response, jcoFunction.getTableParameterList());
		fillJCoRecordFromStructure(response, jcoFunction.getExportParameterList());
	}

	/**
	 * Extract parameters from <code>jcoFunction</code> parameter lists to
	 * <code>request</code>.
	 * 
	 * @param jcoFunction
	 *            - the function containing the values.
	 * @param request
	 *            - the request to be filled with values.
	 */
	public static void extractJCoParameterListsIntoRequest(JCoFunction jcoFunction, Structure request) {
		extractJCoRecordIntoStructure(jcoFunction.getImportParameterList(), request);
		extractJCoRecordIntoStructure(jcoFunction.getChangingParameterList(), request);
		extractJCoRecordIntoStructure(jcoFunction.getTableParameterList(), request);
	}

	/**
	 * Extract parameters from <code>jcoFunction</code> parameter lists to
	 * <code>response</code>.
	 * 
	 * @param jcoFunction
	 *            - the function containing the values.
	 * @param response
	 *            - the response to be filled with values.
	 */
	public static void extractJCoParameterListsIntoResponse(JCoFunction jcoFunction, Structure response) {
		extractJCoRecordIntoStructure(jcoFunction.getChangingParameterList(), response);
		extractJCoRecordIntoStructure(jcoFunction.getTableParameterList(), response);
		extractJCoRecordIntoStructure(jcoFunction.getExportParameterList(), response);
	}

	/**
	 * Adds new row to table.
	 * 
	 * @param table
	 *            - the table to be added to.
	 * @return The row added to table.
	 */
	public static Structure addTableRow(Table<? extends Structure> table) {
		EStructuralFeature feature = table.eClass().getEStructuralFeature(ROW);
		if (feature == null || !(feature instanceof EReference)) {
			return null;
		}
		EClass rowType = ((EReference) feature).getEReferenceType();
		@SuppressWarnings("unchecked")
		EList<Structure> records = (EList<Structure>) getValue(table, feature);

		Structure newRow = (Structure) rowType.getEPackage().getEFactoryInstance().create(rowType);
		records.add(newRow);
		return newRow;
	}

	/**
	 * Adds new row to table at given <code>index</code>.
	 * 
	 * @param table
	 *            - the table to be added to.
	 * @param index
	 *            - the index in table to add new row.
	 * @return The row added to table.
	 */
	public static Structure addTableRow(Table<? extends Structure> table, int index) {
		EStructuralFeature feature = table.eClass().getEStructuralFeature(ROW);
		if (feature == null || !(feature instanceof EReference)) {
			return null;
		}
		EClass rowType = ((EReference) feature).getEReferenceType();
		@SuppressWarnings("unchecked")
		EList<Structure> records = (EList<Structure>) getValue(table, feature);

		Structure newRow = (Structure) rowType.getEPackage().getEFactoryInstance().create(rowType);
		records.add(index, newRow);
		return newRow;
	}

	/**
	 * Extract parameters from <code>jrecord</code> to <code>structure</code>.
	 * 
	 * @param jrecord
	 *            - the JCo record containing the values.
	 * @param structure
	 *            - the structure to be filled with values.
	 */
	@SuppressWarnings("unchecked")
	public static void extractJCoRecordIntoStructure(JCoRecord jrecord, Structure structure) {
		if (jrecord == null || structure == null)
			return;

		EClass eClass = structure.eClass();
		JCoFieldIterator iterator = jrecord.getFieldIterator();
		while (iterator.hasNextField()) {
			JCoField field = iterator.nextField();
			EStructuralFeature feature = eClass.getEStructuralFeature(field.getName());
			Object value = getValue(structure, feature);
			if (field.isStructure()) {
				if (value == null || !(value instanceof EObject))
					continue;
				extractJCoRecordIntoStructure(field.getStructure(), (Structure) value);
			} else if (field.isTable()) {
				if (value == null || !(value instanceof EObject))
					continue;
				extractJCoTableIntoTable((JCoTable) field.getTable(), (Table<? extends Structure>) value);
			} else {
				setValue(structure, feature, field.getValue());
			}
		}
	}

	/**
	 * Fill parameters from <code>jrecord</code> from <code>structure</code>.
	 * 
	 * @param structure
	 *            - the stucture containing the values.
	 * @param jcoRecord
	 *            - the JCo record to be filled with values.
	 */
	@SuppressWarnings("unchecked")
	public static void fillJCoRecordFromStructure(Structure structure, JCoRecord jcoRecord) {
		if (jcoRecord == null || structure == null)
			return;

		EClass eClass = structure.eClass();
		JCoFieldIterator iterator = jcoRecord.getFieldIterator();
		while (iterator.hasNextField()) {
			JCoField field = iterator.nextField();
			EStructuralFeature feature = eClass.getEStructuralFeature(field.getName());
			Object value = getValue(structure, feature);
			if (field.isStructure()) {
				if (value == null || !(value instanceof Structure))
					continue;
				fillJCoRecordFromStructure((Structure) value, field.getStructure());
			} else if (field.isTable()) {
				if (value == null || !(value instanceof Table))
					continue;
				fillJCoTableFromTable((Table<? extends Structure>) value, field.getTable());
			} else {
				field.setValue(value);
			}
		}

	}

	/**
	 * Extract rows from <code>table</code> from <code>jcoTable</code>.
	 * 
	 * @param jcoTable
	 *            - the JCo table containing the rows.
	 * @param table
	 *            - the table to be filled with rows.
	 */
	public static void extractJCoTableIntoTable(JCoTable jcoTable, Table<? extends Structure> table) {
		if (table == null || jcoTable == null)
			return;

		EStructuralFeature feature = table.eClass().getEStructuralFeature(ROW);
		if (feature == null || !(feature instanceof EReference)) {
			return;
		}
		EClass rowType = ((EReference) feature).getEReferenceType();
		@SuppressWarnings("unchecked")
		EList<Structure> records = (EList<Structure>) getValue(table, feature);

		jcoTable.firstRow();
		for (int i = 0; i < jcoTable.getNumRows(); i++, jcoTable.nextRow()) {
			Structure newRow = (Structure) rowType.getEPackage().getEFactoryInstance().create(rowType);
			records.add(newRow);
			extractJCoRecordIntoStructure(jcoTable, newRow);
		}
	}

	/**
	 * Fill <code>jcoTable</code> with rows from <code>table</code>.
	 * 
	 * @param table
	 *            - the table containing the rows.
	 * @param jcoTable
	 *            - the JCo table to be filled with rows.
	 */
	public static void fillJCoTableFromTable(Table<? extends Structure> table, JCoTable jcoTable) {
		if (table == null || jcoTable == null)
			return;

		EStructuralFeature feature = table.eClass().getEStructuralFeature(ROW);
		@SuppressWarnings("unchecked")
		EList<Structure> records = (EList<Structure>) getValue(table, feature);
		for (Structure row : records) {
			jcoTable.appendRow();
			fillJCoRecordFromStructure(row, (JCoRecord) jcoTable);
		}
	}

	/**
	 * Returns a new request structure to pass to remote function module
	 * designated by <code>functionModuleName</code> defined in
	 * <code>repository</code>.
	 * 
	 * @param repository
	 *            - the repository containing meta-data of function module.
	 * @param functionModuleName
	 *            - the name designating the remote function module.
	 * @return The new request structure.
	 */
	public static Request getRequest(JCoRepository repository, String functionModuleName) {
		return (Request) createInstance(repository, functionModuleName, "Request");
	}

	/**
	 * Returns a new request structure to pass to remote function module
	 * designated by <code>functionModuleName</code> defined in
	 * <code>repositoryName</code> repository.
	 * 
	 * @param repositoryName
	 *            - the name of the repository containing meta-data of function module.
	 * @param functionModuleName
	 *            - the name designating the remote function module.
	 * @return The new request structure.
	 */
	public static Request getRequest(String repositoryName, String functionModuleName) {
		return (Request) createInstance(repositoryName, functionModuleName, "Request");
	}

	/**
	 * Returns a new response structure to pass to remote function module
	 * designated by <code>functionModuleName</code> defined in
	 * <code>repository</code>.
	 * 
	 * @param repository
	 *            - the repository containing meta-data of function module.
	 * @param functionModuleName
	 *            - the name designating the remote function module.
	 * @return The new response structure.
	 */
	public static Response getResponse(JCoRepository repository, String functionModuleName) {
		return (Response) createInstance(repository, functionModuleName, "Response");
	}

	/**
	 * Returns a new response structure to pass to remote function module
	 * designated by <code>functionModuleName</code> defined in
	 * <code>repositoryName</code> repository.
	 * 
	 * @param repositoryName
	 *            - the name of the repository containing meta-data of function module.
	 * @param functionModuleName
	 *            - the name designating the remote function module.
	 * @return The new response structure.
	 */
	public static Response getResponse(String repositoryName, String functionModuleName) {
		return (Response) createInstance(repositoryName, functionModuleName, "Response");
	}

	/**
	 * Creates new instance of {@link EClass} with
	 * the name <code>eClassName</code> in the {@link EPackage} 
	 * <code>packageName</code> described in the
	 * <code>repository</code>.
	 * 
	 * @param repository
	 *            - the {@link JCoRepository} containing function module meta-data.
	 * @param packageName - the package name containing class
	 * @param eClassName - the name of the class. 
	 * @return The instance or <code>null</code> if <code>eClassName</code> not found.
	 */
	public static EObject createInstance(JCoRepository repository, String packageName, String eClassName) {
		String nsURI = eNS_URI + "/" + repository.getName() + "/" + packageName;

		EPackage ePackage = getEPackage(repository, nsURI);
		if (ePackage == null) 
			return null;
		EClassifier classifier = ePackage.getEClassifier(eClassName);
		if (!(classifier instanceof EClass))
			return null;

		EClass eClass = (EClass) classifier;
		EObject eObject = ePackage.getEFactoryInstance().create(eClass);

		return eObject;
	}

	/**
	 * Creates new instance of {@link EClass} with
	 * the name <code>eClassName</code> in the {@link EPackage} 
	 * <code>packageName</code> described in the
	 * <code>repositoryName</code> repository.
	 * 
	 * 
	 * @param repositoryName
	 *            - the name of the repository containing function module meta-data.
	 * @param packageName  - the package name containing class
	 * @param eClassName - the name of the class. 
	 * @return The instance or <code>null</code> if <code>eClassName</code> not found.
	 */
	public static EObject createInstance(String repositoryName, String packageName, String eClassName) {
		String nsURI = eNS_URI + "/" + repositoryName + "/" + packageName;

		EPackage ePackage = registry.getEPackage(nsURI);
		if (ePackage == null) 
			return null;
		EClassifier classifier = ePackage.getEClassifier(eClassName);
		if (!(classifier instanceof EClass))
			return null;

		EClass eClass = (EClass) classifier;
		EObject eObject = ePackage.getEFactoryInstance().create(eClass);

		return eObject;
	}

	/**
	 * Returns (and creates if necessary) the {@link EPackage} instance
	 * containing the definition of the input and output {@link Structure}s
	 * passed to the {@link JcoFunction} designated by <code>nsURi</code> and
	 * described in the given <code>repository</code>.
	 * 
	 * @param repository
	 *            - the {@link JCoRepository} containing the meta data *
	 *            describing the designated {@link JCoFunction}.
	 * @param nsURI
	 *            - the URI designating {@link JCoFunction}. The URI format is
	 *            of the form:
	 *            http://sap.fusesource.org/rfc/{repository-name}/{jco
	 *            -function-name}.
	 * @return The {@link EPackage} instance.
	 */
	public static EPackage getEPackage(JCoRepository repository, String nsURI) {

		// Check whether the requested package has already been built.
		EPackage ePackage = (EPackage) EPackage.Registry.INSTANCE.get(nsURI);
		if (ePackage != null) {
			return ePackage;
		}

		// Check whether the requested package is defined by the destination's
		// repository.
		if (nsURI.startsWith(eNS_URI + "/" + repository.getName())) {

			// Extract the function module name from the URI.
			int prefixLength = eNS_URI.length() + repository.getName().length() + 2; // Length
																						// of
																						// "http://sap.fusesource.org/<repo-name>/"
																						// prefix.
			String functionModuleName = nsURI.substring(prefixLength);

			// Retrieve the function module's meta-data.
			JCoFunctionTemplate functionTemplate;
			try {
				functionTemplate = repository.getFunctionTemplate(functionModuleName);
			} catch (JCoException e) {
				return null;
			}
			JCoListMetaData importParameterListMetaData = functionTemplate.getImportParameterList();
			JCoListMetaData changingParameterListMetaData = functionTemplate.getChangingParameterList();
			JCoListMetaData tableParameterListMetaData = functionTemplate.getTableParameterList();
			JCoListMetaData exportParameterListMetaData = functionTemplate.getExportParameterList();

			// Create and initialize package
			EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
			ePackage = ecoreFactory.createEPackage();
			ePackage.setName(functionModuleName);
			ePackage.setNsPrefix(functionModuleName);
			ePackage.setNsURI(nsURI);

			// Create Request Class
			EClass requestClass = ecoreFactory.createEClass();
			ePackage.getEClassifiers().add(requestClass);
			requestClass.setName("Request");
			requestClass.getESuperTypes().add(RfcPackage.eINSTANCE.getRequest());
			addListMetaData(requestClass, importParameterListMetaData);
			addListMetaData(requestClass, changingParameterListMetaData);
			addListMetaData(requestClass, tableParameterListMetaData);
			addAnnotation(requestClass, GenNS_URI, GenNS_DOCUMENTATION_KEY, "Request for " + functionModuleName);
			addListMetaDataToRequest(requestClass, importParameterListMetaData, changingParameterListMetaData, tableParameterListMetaData);

			// Create Response Class
			EClass responseClass = ecoreFactory.createEClass();
			ePackage.getEClassifiers().add(responseClass);
			responseClass.setName("Response");
			responseClass.getESuperTypes().add(RfcPackage.eINSTANCE.getResponse());
			addListMetaData(responseClass, exportParameterListMetaData);
			addListMetaData(responseClass, changingParameterListMetaData);
			addListMetaData(responseClass, tableParameterListMetaData);
			addAnnotation(responseClass, GenNS_URI, GenNS_DOCUMENTATION_KEY, "Response for " + functionModuleName);
			addListMetaDataToResponse(responseClass, changingParameterListMetaData, tableParameterListMetaData, exportParameterListMetaData);

			// Register Package
			EPackage.Registry.INSTANCE.put(nsURI, ePackage);
		}
		return ePackage;
	}
	
	public static void addListMetaDataToRequest(EClass structure, JCoListMetaData importListMetaData, JCoListMetaData changingListMetaData, JCoListMetaData tableListMetaData) {
		addAnnotation(structure, eNS_URI, RfcNS_NAME_KEY, "Request");
		int fieldCount = (importListMetaData == null ? 0 : importListMetaData.getFieldCount())
				+ (changingListMetaData == null ? 0 : changingListMetaData.getFieldCount())
				+ (tableListMetaData == null ? 0 : tableListMetaData.getFieldCount());
		addAnnotation(structure, eNS_URI, RfcNS_FIELD_COUNT_KEY, Integer.toString(fieldCount));
		addAnnotation(structure, eNS_URI, RfcNS_RECORD_LENGTH_KEY, "-1");
		addAnnotation(structure, eNS_URI, RfcNS_UNICODE_RECORD_LENGTH_KEY, "-1");
		addAnnotation(structure, eNS_URI, RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY, "false");
	}

	public static void addListMetaDataToResponse(EClass structure, JCoListMetaData changingListMetaData, JCoListMetaData tableListMetaData, JCoListMetaData exportListMetaData) {
		addAnnotation(structure, eNS_URI, RfcNS_NAME_KEY, "Response");
		int fieldCount = (changingListMetaData == null ? 0 : changingListMetaData.getFieldCount())
				+ (tableListMetaData == null ? 0 : tableListMetaData.getFieldCount()) + (exportListMetaData == null ? 0 : exportListMetaData.getFieldCount());
		addAnnotation(structure, eNS_URI, RfcNS_FIELD_COUNT_KEY, Integer.toString(fieldCount));
		addAnnotation(structure, eNS_URI, RfcNS_RECORD_LENGTH_KEY, "-1");
		addAnnotation(structure, eNS_URI, RfcNS_UNICODE_RECORD_LENGTH_KEY, "-1");
		addAnnotation(structure, eNS_URI, RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY, "false");
	}

	/**
	 * Populate the given {@link EClass} with structural features and
	 * annotations derived from the meta-data of the given
	 * {@link JCoListMetaData}.
	 * 
	 * @param eClass
	 *            - the {@link EClass} populated with meta-data.
	 * @param jcoListMetaData
	 *            - the {@link JCoListMetaData} from which the meta-data is
	 *            derived.
	 */
	public static void addListMetaData(EClass eClass, JCoListMetaData jcoListMetaData) {
		if (jcoListMetaData == null)
			return;

		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = eClass.getEPackage();
		for (int i = 0; i < jcoListMetaData.getFieldCount(); i++) {
			EStructuralFeature structuralFeature;
			if (jcoListMetaData.isStructure(i)) {
				JCoRecordMetaData jcoRecordMetaData = jcoListMetaData.getRecordMetaData(i);
				EClass structureClass = getStructureClass(ePackage, jcoRecordMetaData);
				EReference reference = ecoreFactory.createEReference();
				reference.setEType(structureClass);
				reference.setContainment(true);
				structuralFeature = reference;
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, EObject.class.getName());
			} else if (jcoListMetaData.isTable(i)) {
				JCoRecordMetaData jcoRecordMetaData = jcoListMetaData.getRecordMetaData(i);
				EClass tableClass = getTableClass(ePackage, jcoRecordMetaData);
				EReference reference = ecoreFactory.createEReference();
				reference.setEType(tableClass);
				reference.setContainment(true);
				structuralFeature = reference;
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, EObject.class.getName());
			} else {
				EAttribute attribute = ecoreFactory.createEAttribute();
				attribute.setEType(getEDataType(jcoListMetaData.getType(i)));
				structuralFeature = attribute;
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, jcoListMetaData.getClassNameOfField(i));
			}
			structuralFeature.setName(jcoListMetaData.getName(i));
			if (!jcoListMetaData.isOptional(i))
				structuralFeature.setLowerBound(1);
			if (jcoListMetaData.getDefault(i) != null)
				structuralFeature.setDefaultValueLiteral(checkForKeywordLiterals(jcoListMetaData.getDefault(i)));
			addAnnotation(structuralFeature, GenNS_URI, GenNS_DOCUMENTATION_KEY, jcoListMetaData.getDescription(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_NAME_KEY, jcoListMetaData.getName(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_DESCRIPTION_KEY, jcoListMetaData.getDescription(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_TYPE_KEY, Integer.toString(jcoListMetaData.getType(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_TYPE_AS_STRING_KEY, jcoListMetaData.getTypeAsString(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_RECORD_TYPE_NAME_KEY, jcoListMetaData.getRecordTypeName(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_LENGTH_KEY, Integer.toString(jcoListMetaData.getLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_BYTE_LENGTH_KEY, Integer.toString(jcoListMetaData.getByteLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_UNICODE_BYTE_LENGTH_KEY, Integer.toString(jcoListMetaData.getUnicodeByteLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_DECIMALS_KEY, Integer.toString(jcoListMetaData.getDecimals(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_DEFAULT_KEY, checkForKeywordLiterals(jcoListMetaData.getDefault(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_RECORD_FIELD_NAME_KEY, jcoListMetaData.getRecordFieldName(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_ABAP_OBJECT_KEY, Boolean.toString(jcoListMetaData.isAbapObject(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY, Boolean.toString(jcoListMetaData.isNestedType1Structure(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_STRUCTURE_KEY, Boolean.toString(jcoListMetaData.isStructure(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_TABLE_KEY, Boolean.toString(jcoListMetaData.isTable(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_IMPORT_KEY, Boolean.toString(jcoListMetaData.isImport(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_EXCEPTION_KEY, Boolean.toString(jcoListMetaData.isException(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_EXPORT_KEY, Boolean.toString(jcoListMetaData.isExport(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_CHANGING_KEY, Boolean.toString(jcoListMetaData.isChanging(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_OPTIONAL_KEY, Boolean.toString(jcoListMetaData.isOptional(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_RECORD_FIELD_NAME_KEY, jcoListMetaData.getRecordFieldName(i));
			eClass.getEStructuralFeatures().add(structuralFeature);
		}
	}

	/**
	 * Create and return an {@link EClass} in <code>ePackage</code> deriving
	 * from {@link Structure} and representing a {@link JCoRecord} described by
	 * <code>jcoRecordMetaData</code>.
	 * 
	 * @param ePackage
	 *            - the {@link EPackage} instance containing the {@link EClass}
	 *            definition.
	 * @param jcoRecordMetaData
	 *            - the {@link JCoRecordMetaData} instance describing an
	 *            underlying {@link JCoRecord} instance.
	 * @return The {@link EClass} instance created.
	 */
	public static EClass getStructureClass(EPackage ePackage, JCoRecordMetaData jcoRecordMetaData) {

		// Check package to see if structure class has already been defined.
		EClassifier structureClass = ePackage.getEClassifier(jcoRecordMetaData.getName());

		// Build structure class if not already built.
		if (!(structureClass instanceof EClass)) {

			structureClass = EcoreFactory.eINSTANCE.createEClass();
			ePackage.getEClassifiers().add(structureClass);
			structureClass.setName(jcoRecordMetaData.getName());
			addAnnotation(structureClass, eNS_URI, RfcNS_NAME_KEY, jcoRecordMetaData.getName());
			addAnnotation(structureClass, eNS_URI, RfcNS_FIELD_COUNT_KEY, Integer.toString(jcoRecordMetaData.getFieldCount()));
			addAnnotation(structureClass, eNS_URI, RfcNS_RECORD_LENGTH_KEY, Integer.toString(jcoRecordMetaData.getRecordLength()));
			addAnnotation(structureClass, eNS_URI, RfcNS_UNICODE_RECORD_LENGTH_KEY, Integer.toString(jcoRecordMetaData.getUnicodeRecordLength()));
			addAnnotation(structureClass, eNS_URI, RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY, Boolean.toString(jcoRecordMetaData.isNestedType1Structure()));
			addRecordMetaData(((EClass) structureClass), jcoRecordMetaData);
			((EClass) structureClass).getESuperTypes().add(RfcPackage.eINSTANCE.getStructure());
		}
		return (EClass) structureClass;
	}

	/**
	 * Create and return an {@link EClass} in <code>ePackage</code> deriving
	 * from {@link Table<? extends Structure>} and representing a
	 * {@link JCoTable} described by <code>jcoRecordMetaData</code>.
	 * 
	 * @param ePackage
	 *            - the {@link EPackage} instance containing the {@link EClass}
	 *            definition.
	 * @param jcoRecordMetaData
	 *            - the {@link JCoRecordMetaData} instance describing an
	 *            underlying {@link JCoTable} instance.
	 * @return The {@link EClass} instance created.
	 */
	public static EClass getTableClass(EPackage ePackage, JCoRecordMetaData jcoRecordMetaData) {

		// Check package to see if table class has already been defined.
		EClassifier tableClass = ePackage.getEClassifier(jcoRecordMetaData.getName() + "_TABLE");

		// Build table class if not already built.
		if (!(tableClass instanceof EClass)) {

			// Create the super type inherited by this Table subclass: i.e.
			// 'Table<S extends Structure>'
			EGenericType tableGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType();
			EClass tableSuperClass = RfcPackage.eINSTANCE.getTable();
			tableGenericSuperType.setEClassifier(tableSuperClass);

			// Create type parameter for row type: i.e. the 'S' in 'S extends
			// Structure'
			EGenericType rowGenericType = EcoreFactory.eINSTANCE.createEGenericType();
			EClass structureType = getStructureClass(ePackage, jcoRecordMetaData);
			rowGenericType.setEClassifier(structureType);

			// Add the type parameter to super type: i.e. 'S'
			tableGenericSuperType.getETypeArguments().add(rowGenericType);

			// Create the Table subclass and add to package
			tableClass = EcoreFactory.eINSTANCE.createEClass();
			ePackage.getEClassifiers().add(tableClass);
			tableClass.setName(jcoRecordMetaData.getName() + "_TABLE");
			((EClass) tableClass).getEGenericSuperTypes().add(tableGenericSuperType);

			// Workaround for type erasure in EMF Generic feature.
			EReference rowReference = EcoreFactory.eINSTANCE.createEReference();
			rowReference.setEType(structureType);
			rowReference.setName(ROW);
			rowReference.setContainment(true);
			rowReference.setLowerBound(0);
			rowReference.setUpperBound(-1);
			addAnnotation(tableClass, eNS_URI, RfcNS_NAME_KEY, jcoRecordMetaData.getName() + "_TABLE");
			addAnnotation(tableClass, eNS_URI, RfcNS_LINE_TYPE_KEY, jcoRecordMetaData.getName());
			((EClass) tableClass).getEStructuralFeatures().add(rowReference);

		}
		return (EClass) tableClass;
	}

	/**
	 * Populate the given {@link EClass} with structural features and
	 * annotations derived from the meta-data of the given
	 * {@link JCoRecordMetaData}.
	 * 
	 * @param eClass
	 *            - the {@link EClass} populated with meta-data.
	 * @param jcoRecordMetaData
	 *            - the {@link JCoRecordMetaData} from which the meta-data is
	 *            derived.
	 */
	public static void addRecordMetaData(EClass eClass, JCoRecordMetaData jcoRecordMetaData) {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage ePackage = eClass.getEPackage();
		for (int i = 0; i < jcoRecordMetaData.getFieldCount(); i++) {
			EStructuralFeature structuralFeature;
			if (jcoRecordMetaData.isStructure(i)) {
				JCoRecordMetaData jcoSubRecordMetaData = jcoRecordMetaData.getRecordMetaData(i);
				EClass structureClass = getStructureClass(ePackage, jcoSubRecordMetaData);
				EReference reference = ecoreFactory.createEReference();
				structuralFeature = reference;
				reference.setEType(structureClass);
				reference.setContainment(true);
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, EObject.class.getName());
			} else if (jcoRecordMetaData.isTable(i)) {
				JCoRecordMetaData jcoSubRecordMetaData = jcoRecordMetaData.getRecordMetaData(i);
				EClass tableClass = getTableClass(ePackage, jcoSubRecordMetaData);
				EReference reference = ecoreFactory.createEReference();
				structuralFeature = reference;
				reference.setEType(tableClass);
				reference.setContainment(true);
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, EObject.class.getName());
			} else {
				EAttribute attribute = ecoreFactory.createEAttribute();
				structuralFeature = attribute;
				attribute.setEType(getEDataType(jcoRecordMetaData.getType(i)));
				addAnnotation(structuralFeature, eNS_URI, RfcNS_CLASS_NAME_OF_FIELD_KEY, jcoRecordMetaData.getClassNameOfField(i));
			}
			structuralFeature.setName(jcoRecordMetaData.getName(i));
			addAnnotation(structuralFeature, GenNS_URI, GenNS_DOCUMENTATION_KEY, jcoRecordMetaData.getDescription(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_NAME_KEY, jcoRecordMetaData.getName(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_DESCRIPTION_KEY, jcoRecordMetaData.getDescription(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_TYPE_KEY, Integer.toString(jcoRecordMetaData.getType(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_TYPE_AS_STRING_KEY, jcoRecordMetaData.getTypeAsString(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_RECORD_TYPE_NAME_KEY, jcoRecordMetaData.getRecordTypeName(i));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_LENGTH_KEY, Integer.toString(jcoRecordMetaData.getLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_BYTE_LENGTH_KEY, Integer.toString(jcoRecordMetaData.getByteLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_BYTE_OFFSET_KEY, Integer.toString(jcoRecordMetaData.getByteOffset(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_UNICODE_BYTE_LENGTH_KEY, Integer.toString(jcoRecordMetaData.getUnicodeByteLength(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_UNICODE_BYTE_OFFSET_KEY, Integer.toString(jcoRecordMetaData.getUnicodeByteOffset(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_DECIMALS_KEY, Integer.toString(jcoRecordMetaData.getDecimals(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_ABAP_OBJECT_KEY, Boolean.toString(jcoRecordMetaData.isAbapObject(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_NESTED_TYPE1_STRUCTURE_KEY, Boolean.toString(jcoRecordMetaData.isNestedType1Structure(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_STRUCTURE_KEY, Boolean.toString(jcoRecordMetaData.isStructure(i)));
			addAnnotation(structuralFeature, eNS_URI, RfcNS_IS_TABLE_KEY, Boolean.toString(jcoRecordMetaData.isTable(i)));
			eClass.getEStructuralFeatures().add(structuralFeature);

		}
	}

	/**
	 * Return the {@link EClassifier} corresponding to the given JCo data type.
	 * 
	 * @param jcoDataType
	 *            - the JCo data type.
	 * @return the {@link EClassifier} corresponding to the given JCo data type.
	 */
	public static EClassifier getEDataType(int jcoDataType) {
		switch (jcoDataType) {

		case JCoMetaData.TYPE_INT:
		case JCoMetaData.TYPE_INT1:
		case JCoMetaData.TYPE_INT2:
			return EcorePackage.Literals.EINT;

		case JCoMetaData.TYPE_CHAR:
			return EcorePackage.Literals.ESTRING;

		case JCoMetaData.TYPE_NUM:
			return EcorePackage.Literals.ESTRING;

		case JCoMetaData.TYPE_BCD:
			return EcorePackage.Literals.EBIG_DECIMAL;

		case JCoMetaData.TYPE_DATE:
			return EcorePackage.Literals.EDATE;

		case JCoMetaData.TYPE_TIME:
			return EcorePackage.Literals.EDATE;

		case JCoMetaData.TYPE_FLOAT:
			return EcorePackage.Literals.EDOUBLE;

		case JCoMetaData.TYPE_BYTE:
			return EcorePackage.Literals.EBYTE_ARRAY;

		case JCoMetaData.TYPE_STRING:
			return EcorePackage.Literals.ESTRING;

		case JCoMetaData.TYPE_XSTRING:
			return EcorePackage.Literals.EBYTE_ARRAY;

		case JCoMetaData.TYPE_DECF16:
			return EcorePackage.Literals.EBIG_DECIMAL;

		case JCoMetaData.TYPE_DECF34:
			return EcorePackage.Literals.EBIG_DECIMAL;

		case JCoMetaData.TYPE_STRUCTURE:
			return EcorePackage.Literals.EOBJECT;

		case JCoMetaData.TYPE_TABLE:
			return EcorePackage.Literals.EOBJECT;

		default:
			return EcorePackage.Literals.EBYTE_ARRAY;
		}
	}
	
	/**
	 * Creates custom repository named <code>repositoryName</code> and
	 * populates with <code>repositoryData</code>.
	 * 
	 * @param repositoryName
	 *            - the name of the custom repository.
	 * @param repositoryData
	 *            - the data to populate the repository with.
	 * @return The newly created and populated custom repository.
	 */
	public static JCoCustomRepository createRepository(String repositoryName, RepositoryData repositoryData) {
		JCoCustomRepository customRepository = JCo.createCustomRepository(repositoryName);
		for (String functionTemplateName: repositoryData.getEntries().keySet()) {
			FunctionTemplate functionTemplate = repositoryData.getEntries().get(functionTemplateName);
			JCoFunctionTemplate jcoFunctionTemplate = createJCoFunctionTemplate(functionTemplateName, functionTemplate);
			customRepository.addFunctionTemplateToCache(jcoFunctionTemplate);
		}
		return customRepository;
	}
	
	/**
	 * Creates JCo Function Template named <code>named</code> from meta-data in
	 * <code>functionTemplate</code>.
	 * 
	 * @param name
	 *            - the name of new JCo function template.
	 * @param functionTemplate
	 *            - the function template containing meta-data to populate new
	 *            function template with.
	 * @return The newly create JCo Function Template.
	 */
	public static JCoFunctionTemplate createJCoFunctionTemplate(String name, FunctionTemplate functionTemplate) {
		JCoListMetaData importsMetaData= createJCoListMetaData("IMPORTS", functionTemplate.getImportParameterList());
		JCoListMetaData exportsMetaData = createJCoListMetaData("EXPORTS", functionTemplate.getExportParameterList());
		JCoListMetaData changingMetaData = createJCoListMetaData("CHANGING", functionTemplate.getChangingParameterList());
		JCoListMetaData tablesMetaData = createJCoListMetaData("TABLES", functionTemplate.getTables());
		com.sap.conn.jco.AbapException[] jcoAbapExceptions = createAbapExceptions(functionTemplate.getExceptionList());
		JCoFunctionTemplate jcoFunctionTemplate = JCo.createFunctionTemplate(name, importsMetaData, exportsMetaData, changingMetaData, tablesMetaData, jcoAbapExceptions);
		
		return jcoFunctionTemplate;
	}

	/**
	 * Create JCo List Meta Data named <code>name</code> from meta-data in
	 * <code>listDescriptors</code>.
	 * 
	 * @param name
	 *            - the name of new JCo list meta data.
	 * @param listDescriptors
	 *            - the list descriptors containing meta-data to populate new
	 *            list meta data with.
	 * @return The newly create JCo List Meta Data.
	 */
	public static JCoListMetaData createJCoListMetaData(String name, List<ListFieldMetaData> listDescriptors) {
		JCoListMetaData jcoListMetaData = JCo.createListMetaData(name);

		for(ListFieldMetaData listFieldDescriptor: listDescriptors) {
			int flags = 0;
			flags |= listFieldDescriptor.isImport() ? JCoListMetaData.IMPORT_PARAMETER : 0;
			flags |= listFieldDescriptor.isExport() ? JCoListMetaData.EXPORT_PARAMETER : 0;
			flags |= listFieldDescriptor.isChanging() ? JCoListMetaData.CHANGING_PARAMETER : 0;
			flags |= listFieldDescriptor.isOptional() ? JCoListMetaData.OPTIONAL_PARAMETER : 0;
			switch(listFieldDescriptor.getType().getValue()) {
			case DataType.STRUCTURE_VALUE:
			case DataType.TABLE_VALUE:
				JCoRecordMetaData jcoRecordMetaData = createJCoRecordMetaData(listFieldDescriptor.getRecordMetaData());
				jcoListMetaData.add(listFieldDescriptor.getName(), listFieldDescriptor.getType().getValue(), jcoRecordMetaData, flags);
				break;
			default: 
				jcoListMetaData.add(listFieldDescriptor.getName(), listFieldDescriptor.getType().getValue(), listFieldDescriptor.getByteLength(), listFieldDescriptor.getUnicodeByteLength(), listFieldDescriptor.getDecimals(), listFieldDescriptor.getDescription(), null, flags, null, null);
			}
		}
		jcoListMetaData.lock();
		return jcoListMetaData;
	}

	/**
	 * Create JCo Record Meta Data named <code>name</code> from meta-data in
	 * <code>listDescriptors</code>.
	 * 
	 * @param recordMetaData
	 *            - the record meta data to populate into new JCo record meta
	 *            data.
	 * @return The newly created JCo Record Meta Data.
	 */
	public static JCoRecordMetaData createJCoRecordMetaData(RecordMetaData recordMetaData) {
		JCoRecordMetaData jcoRecordMetaData = JCo.createRecordMetaData(recordMetaData.getName());

		for (FieldMetaData fieldDescriptor : recordMetaData.getRecordFieldMetaData()) {
			switch (fieldDescriptor.getType().getValue()) {
			case DataType.STRUCTURE_VALUE:
			case DataType.TABLE_VALUE:
				JCoRecordMetaData recordMetaData2 = createJCoRecordMetaData(fieldDescriptor.getRecordMetaData());
				jcoRecordMetaData.add(fieldDescriptor.getName(), fieldDescriptor.getType().getValue(), fieldDescriptor.getByteOffset(),
						fieldDescriptor.getUnicodeByteOffset(), recordMetaData2);
				break;
			default:
				jcoRecordMetaData.add(fieldDescriptor.getName(), fieldDescriptor.getType().getValue(), fieldDescriptor.getByteLength(),
						fieldDescriptor.getByteOffset(), fieldDescriptor.getUnicodeByteLength(), fieldDescriptor.getUnicodeByteOffset(),
						fieldDescriptor.getDecimals(), fieldDescriptor.getDescription(), null, null);
			}
		}
		jcoRecordMetaData.lock();
		return jcoRecordMetaData;
	}
	
	/**
	 * Create JCo ABAP Exception list from meta-data in ABAP Exception list.
	 * 
	 * @param abapExceptions
	 *            - the list of meta data to populate into JCo ABAP exception
	 *            list
	 * @return The newly create JCo ABAP exception list
	 */
	public static com.sap.conn.jco.AbapException[] createAbapExceptions(List<AbapException> abapExceptions) {
		com.sap.conn.jco.AbapException[] jcoAbapExceptions = new com.sap.conn.jco.AbapException[abapExceptions.size()];
		for (int i = 0; i < abapExceptions.size(); i++) {
			AbapException abapException = abapExceptions.get(i);
			jcoAbapExceptions[i] = new com.sap.conn.jco.AbapException(abapException.getKey(), abapException.getMessage());
		}
		return jcoAbapExceptions;
	}
	
	/**
	 * Check for ABAP keyword literal values.
	 * 
	 * This is used to convert any ABAP keyword literal values such as 'SPACE'
	 * that may be set as defaults on parameter list fields to their actual
	 * value.
	 * 
	 * @param value
	 *            - the value to check
	 * @return If value is an ABAP keyword literal then it is converted to
	 *         actual value; otherwise the passed <code>value</code> is
	 *         returned.
	 */
	public static String checkForKeywordLiterals(String value) {
		if ("SPACE".equals(value)) {
			return " ";
		}
		return value;
	}
}
