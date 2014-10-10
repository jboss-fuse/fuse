package org.fusesource.camel.component.sap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.fusesource.camel.component.sap.converter.StructureConverter;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.model.rfc.Table;
import org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl;
import org.fusesource.camel.component.sap.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.mockpolicies.Slf4jMockPolicy;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServerFactory;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@MockPolicy({Slf4jMockPolicy.class})
@PrepareForTest({ JCoDestinationManager.class, Environment.class, JCoServerFactory.class })
public class StructureConverterTest extends SapRfcTestSupport {

	public static final String REQUEST_STRING = 
			"<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
			"<TEST_FUNCTION_MODULE:Request xmlns:TEST_FUNCTION_MODULE=\"http://sap.fusesource.org/rfc/TEST_REPOSITORY/TEST_FUNCTION_MODULE\" PARAM_LIST_CHAR_PARAM=\"ABCDEFGHIJ\" PARAM_LIST_NUM_PARAM=\"0123456789\" PARAM_LIST_INT_PARAM=\"1968526677\" PARAM_LIST_FLOAT_PARAM=\"1.0E38\" PARAM_LIST_BCD_PARAM=\"100.00000000000001\" PARAM_LIST_BINARY_PARAM=\"55\" PARAM_LIST_BINARY_ARRAY_PARAM=\"FF0F1E2D3C4B5A607988\" PARAM_LIST_DATE_PARAM=\"" + DATE_PARAM_IN_VAL_STR + "\" PARAM_LIST_TIME_PARAM=\"" + TIME_PARAM_IN_VAL_STR + "\" PARAM_LIST_STRING_PARAM=\"Four score and seven years ago ...\">" +
			  "<PARAM_LIST_STRUCTURE_PARAM CHAR_PARAM=\"ABCDEFGHIJ\" NUM_PARAM=\"0123456789\" INT_PARAM=\"1968526677\" FLOAT_PARAM=\"1.0E38\" BCD_PARAM=\"100.00000000000001\" BINARY_PARAM=\"55\" BINARY_ARRAY_PARAM=\"FF0F1E2D3C4B5A607988\" DATE_PARAM=\"" + DATE_PARAM_IN_VAL_STR + "\" TIME_PARAM=\"" + TIME_PARAM_IN_VAL_STR + "\" STRING_PARAM=\"Four score and seven years ago ...\"/>" +
			  "<PARAM_LIST_TABLE_PARAM>" +
			    "<row CHAR_PARAM=\"ABCDEFGHIJ\" NUM_PARAM=\"0123456789\" INT_PARAM=\"1968526677\" FLOAT_PARAM=\"1.0E38\" BCD_PARAM=\"100.00000000000001\" BINARY_PARAM=\"55\" BINARY_ARRAY_PARAM=\"FF0F1E2D3C4B5A607988\" DATE_PARAM=\"" + DATE_PARAM_IN_VAL_STR + "\" TIME_PARAM=\"" + TIME_PARAM_IN_VAL_STR + "\" STRING_PARAM=\"Four score and seven years ago ...\"/>" +
			  "</PARAM_LIST_TABLE_PARAM>" +
			"</TEST_FUNCTION_MODULE:Request>";

	@SuppressWarnings("deprecation")
	@Override
	public void doPreSetup() throws Exception {
		super.doPreSetup();
		
		PowerMockito.mockStatic(JCoDestinationManager.class, JCoServerFactory.class);
		when(JCoDestinationManager.getDestination(DESTINATION_NAME)).thenReturn(mockDestination);
		when(JCoServerFactory.get()).thenReturn(mockServerFactory);
		when(JCoServerFactory.getServer(SERVER_NAME)).thenReturn(mockServer);
		
	}

	@Test
	public void testToStructureFromString() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		
		//
		// When
		//
		
		Structure request = StructureConverter.toStructure(REQUEST_STRING); 

		//
		// Then
		//
		
		verifyStructure(request);

	}

	@Test
	public void testToStructureFormInputStream() throws Exception{

		//
		// Given
		//
		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		ByteArrayInputStream bais = new ByteArrayInputStream(REQUEST_STRING.getBytes());
		
		//
		// When
		//
		
		Structure request = StructureConverter.toStructure(bais);

		//
		// Then
		//

		verifyStructure(request);

	}

	@Test
	public void testToStructureFromByteArray() throws Exception {
		
		//
		// Given
		//
		
		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		
		//
		// When
		//
		
		Structure request = StructureConverter.toStructure(REQUEST_STRING.getBytes());

		//
		// Then
		//

		verifyStructure(request);

	}

	@Test
	public void testToString() throws Exception {

		//
		// Given
		//

		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		Structure request = createAndPopulateRequest();
		
		//
		// When
		//
		
		String requestString = StructureConverter.toString((StructureImpl)request);
		
		//
		// Then
		//

		request = StructureConverter.toStructure(requestString);
		verifyStructure(request);

	}

	@Test
	public void testToOutputStream() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		Structure request = createAndPopulateRequest();
		
		//
		// When
		//

		OutputStream os = StructureConverter.toOutputStream((StructureImpl)request);
		
		//
		// Then
		//
		
		byte[] bytes = ((ByteArrayOutputStream)os).toByteArray();
		request = StructureConverter.toStructure(bytes);
		verifyStructure(request);
		
	}

	@Test
	public void testToInputStream() throws Exception {

		//
		// Given
		//
		
		File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		Structure request = createAndPopulateRequest();
		
		//
		// When
		//
		
		InputStream is = StructureConverter.toInputStream((StructureImpl)request);
		
		//
		// Then
		//

		request = StructureConverter.toStructure(is);
		verifyStructure(request);

	}
	
	public void verifyStructure(Structure request) throws Exception {
		
		assertThat("The response returned by route is an unexpected null value", request, notNullValue());
		
		assertThat("request.get(PARAM_LIST_CHAR_PARAM) returned '" +  request.get(PARAM_LIST_CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_IN_VAL + "'", (String) request.get(PARAM_LIST_CHAR_PARAM), is(CHAR_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_NUM_PARAM) returned '" +  request.get(PARAM_LIST_NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_IN_VAL + "'", (String) request.get(PARAM_LIST_NUM_PARAM), is(NUM_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_INT_PARAM) returned '" +  request.get(PARAM_LIST_INT_PARAM) + "' instead of expected value '" + INT_PARAM_IN_VAL + "'", (Integer) request.get(PARAM_LIST_INT_PARAM), is(INT_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_FLOAT_PARAM) returned '" +  request.get(PARAM_LIST_FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_IN_VAL + "'", (Double) request.get(PARAM_LIST_FLOAT_PARAM), is(FLOAT_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_BCD_PARAM) returned '" +  request.get(PARAM_LIST_BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_IN_VAL + "'", (BigDecimal) request.get(PARAM_LIST_BCD_PARAM), is(BCD_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_BINARY_PARAM) returned '" +  request.get(PARAM_LIST_BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_IN_VAL + "'", (byte[]) request.get(PARAM_LIST_BINARY_PARAM), is(BINARY_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_BINARY_ARRAY_PARAM) returned '" +  request.get(PARAM_LIST_BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_IN_VAL + "'", (byte[]) request.get(PARAM_LIST_BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_DATE_PARAM) returned '" +  request.get(PARAM_LIST_DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_IN_VAL + "'", (Date) request.get(PARAM_LIST_DATE_PARAM), is(DATE_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_TIME_PARAM) returned '" +  request.get(PARAM_LIST_TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_IN_VAL + "'", (Date) request.get(PARAM_LIST_TIME_PARAM), is(TIME_PARAM_IN_VAL));
		assertThat("request.get(PARAM_LIST_STRING_PARAM) returned '" +  request.get(PARAM_LIST_STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_IN_VAL + "'", (String) request.get(PARAM_LIST_STRING_PARAM), is(STRING_PARAM_IN_VAL));
		
		Structure structure = request.get(PARAM_LIST_STRUCTURE_PARAM, Structure.class);
		assertThat("structure.get(PARAM_LIST_STRUCTURE_PARAM) returned unexpected null value", structure, notNullValue());
		assertThat("structure.get(CHAR_PARAM) returned '" +  structure.get(CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_IN_VAL + "'", (String) structure.get(CHAR_PARAM), is(CHAR_PARAM_IN_VAL));
		assertThat("structure.get(NUM_PARAM) returned '" +  structure.get(NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_IN_VAL + "'", (String) structure.get(NUM_PARAM), is(NUM_PARAM_IN_VAL));
		assertThat("structure.get(INT_PARAM) returned '" +  structure.get(INT_PARAM) + "' instead of expected value '" + INT_PARAM_IN_VAL + "'", (Integer) structure.get(INT_PARAM), is(INT_PARAM_IN_VAL));
		assertThat("structure.get(FLOAT_PARAM) returned '" +  structure.get(FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_IN_VAL + "'", (Double) structure.get(FLOAT_PARAM), is(FLOAT_PARAM_IN_VAL));
		assertThat("structure.get(BCD_PARAM) returned '" +  structure.get(BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_IN_VAL + "'", (BigDecimal) structure.get(BCD_PARAM), is(BCD_PARAM_IN_VAL));
		assertThat("structure.get(BINARY_PARAM) returned '" +  structure.get(BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_IN_VAL + "'", (byte[]) structure.get(BINARY_PARAM), is(BINARY_PARAM_IN_VAL));
		assertThat("structure.get(BINARY_ARRAY_PARAM) returned '" +  structure.get(BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_IN_VAL + "'", (byte[]) structure.get(BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_IN_VAL));
		assertThat("structure.get(DATE_PARAM) returned '" +  structure.get(DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_IN_VAL + "'", (Date) structure.get(DATE_PARAM), is(DATE_PARAM_IN_VAL));
		assertThat("structure.get(TIME_PARAM) returned '" +  structure.get(TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_IN_VAL + "'", (Date) structure.get(TIME_PARAM), is(TIME_PARAM_IN_VAL));
		assertThat("structure.get(STRING_PARAM) returned '" +  structure.get(STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_IN_VAL + "'", (String) structure.get(STRING_PARAM), is(STRING_PARAM_IN_VAL));
		
		@SuppressWarnings("unchecked")
		Table<? extends Structure> table = request.get(PARAM_LIST_TABLE_PARAM, Table.class);
		assertThat("request.get(PARAM_LIST_TABLE_PARAM) returned unexpected null value", table, notNullValue());
		List<? extends Structure> rows = table.getRows();
		assertThat("rows.size() returned '" + rows.size() + "' instead of expected value of '1'", rows.size(), is(1));
		Structure tableRow = rows.get(0);
		assertThat("tableRow.get(CHAR_PARAM) returned '" +  tableRow.get(CHAR_PARAM) + "' instead of expected value '" + CHAR_PARAM_IN_VAL + "'", (String) tableRow.get(CHAR_PARAM), is(CHAR_PARAM_IN_VAL));
		assertThat("tableRow.get(NUM_PARAM) returned '" +  tableRow.get(NUM_PARAM) + "' instead of expected value '" + NUM_PARAM_IN_VAL + "'", (String) tableRow.get(NUM_PARAM), is(NUM_PARAM_IN_VAL));
		assertThat("tableRow.get(INT_PARAM) returned '" +  tableRow.get(INT_PARAM) + "' instead of expected value '" + INT_PARAM_IN_VAL + "'", (Integer) tableRow.get(INT_PARAM), is(INT_PARAM_IN_VAL));
		assertThat("tableRow.get(FLOAT_PARAM) returned '" +  tableRow.get(FLOAT_PARAM) + "' instead of expected value '" + FLOAT_PARAM_IN_VAL + "'", (Double) tableRow.get(FLOAT_PARAM), is(FLOAT_PARAM_IN_VAL));
		assertThat("tableRow.get(BCD_PARAM) returned '" +  tableRow.get(BCD_PARAM) + "' instead of expected value '" + BCD_PARAM_IN_VAL + "'", (BigDecimal) tableRow.get(BCD_PARAM), is(BCD_PARAM_IN_VAL));
		assertThat("tableRow.get(BINARY_PARAM) returned '" +  tableRow.get(BINARY_PARAM) + "' instead of expected value '" + BINARY_PARAM_IN_VAL + "'", (byte[]) tableRow.get(BINARY_PARAM), is(BINARY_PARAM_IN_VAL));
		assertThat("tableRow.get(BINARY_ARRAY_PARAM) returned '" +  tableRow.get(BINARY_ARRAY_PARAM) + "' instead of expected value '" + BINARY_ARRAY_PARAM_IN_VAL + "'", (byte[]) tableRow.get(BINARY_ARRAY_PARAM), is(BINARY_ARRAY_PARAM_IN_VAL));
		assertThat("tableRow.get(DATE_PARAM) returned '" +  tableRow.get(DATE_PARAM) + "' instead of expected value '" + DATE_PARAM_IN_VAL + "'", (Date) tableRow.get(DATE_PARAM), is(DATE_PARAM_IN_VAL));
		assertThat("tableRow.get(TIME_PARAM) returned '" +  tableRow.get(TIME_PARAM) + "' instead of expected value '" + TIME_PARAM_IN_VAL + "'", (Date) tableRow.get(TIME_PARAM), is(TIME_PARAM_IN_VAL));
		assertThat("tableRow.get(STRING_PARAM) returned '" +  tableRow.get(STRING_PARAM) + "' instead of expected value '" + STRING_PARAM_IN_VAL + "'", (String) tableRow.get(STRING_PARAM), is(STRING_PARAM_IN_VAL));
	}

}
