package org.fusesource.camel.component.sap.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.Request;
import org.fusesource.camel.component.sap.model.rfc.Response;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.model.rfc.Table;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoRepository;

import static org.hamcrest.CoreMatchers.instanceOf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RfcUtilTest {

	protected DestinationDataStore destinationDataStore;

	@Before
	public void setUp() throws Exception {
		
		DestinationData destinationData = RfcFactory.eINSTANCE.createDestinationData();
		destinationData.setAshost("nplhost");
		destinationData.setSysnr("42");
		destinationData.setClient("001");
		destinationData.setUser("developer");
		destinationData.setPasswd("ch4ngeme");
		destinationData.setLang("en");
		
		destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
		destinationDataStore.getEntries().put("TestDestination", destinationData);
		
		ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
	}

	/**
	 * Creates and saves Test Registry for off-line tests
	 * @throws Exception
	 */
	//@Test
	public void createTestRfcRegistry() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		JCoRepository repository = jcoDestination.getRepository();
		RfcUtil.getEPackage(repository, "http://sap.fusesource.org/rfc");
		RfcUtil.getEPackage(repository, "http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
        File file = new File("data/testRfcRegistry.ecore");
		Util.saveRegistry(file);
	}

	//@Test
	public void testPackage() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		EPackage ePackage = RfcUtil.getEPackage(jcoDestination.getRepository(), "http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		Util.print(ePackage);
	}
	
	/**
	 * Tests the saving and loading of Rfc packages into global package registry.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegistrySaveAndLoad() throws Exception {
		
		// Load base and derived Rfc packages in global registry from test file.  
        File file = new File("data/testRfcRegistry.ecore");
		Util.loadRegistry(file);
		
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc");
		assertNotNull("Failded to load base RFC package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to load derived Rfc package", ePackage);
		
		// Save base and derived IDoc packages to new test file.
        file = new File("data/savedRfcTestRegistry.ecore");
		Util.saveRegistry(file);
		
		// Clear IDoc packages from registry.
		EPackage.Registry.INSTANCE.remove("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");

		// Make sure they were cleared.
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNull("Failed to clear derived Rfc package", ePackage);
		
		Util.loadRegistry(file);
		
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc");
		assertNotNull("Failded to load base RFC package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to load derived RFC package", ePackage);
	}
	
	@Test
	public void testCreateAndBuildRequest() throws Exception {
		// Load base and derived RFC packages in global registry from test file.  
        File file = new File("data/testRfcRegistry.ecore");
		IDocUtil.loadRegistry(file);

		// Test that packages loaded.
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc");
		assertNotNull("Failded to load base RFC package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to load derived RFC package", ePackage);

		// Test create request
		Request request = RfcUtil.getRequest("NPL", "BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to create request", request);
		assertEquals("New request has incorrect name", "Request", request.getName());
		assertEquals("New request has incorrect field count", 9, request.getFieldCount());
		assertEquals("New request has incorrect record length", -1, request.getRecordLength());
		assertEquals("New request has incorrect unicode record length", -1, request.getUnicodeRecordLength());
		assertEquals("New request has incorrect nested type1 structure flag", false, request.isNestedType1Structure());
		
		// Test field values and type
		try {
			request.put("CONNECTIONNUMBER", "1234");
		} catch (IllegalArgumentException e) {
			fail("New request field CONNECTIONNUMBER does not exist");
		}
		assertThat("New request field CONNECTIONNUMBER has incorrect type", request.get("CONNECTIONNUMBER"), instanceOf(String.class));
		try {
			request.put("FLIGHTDATE", new Date());
		} catch (IllegalArgumentException e) {
			fail("New request field FLIGHTDATE does not exist");
		}
		assertThat("New request field FLIGHTDATE has incorrect type", request.get("FLIGHTDATE"), instanceOf(Date.class));
		try {
			request.put("NO_AVAILIBILITY", "X");
		} catch (IllegalArgumentException e) {
			fail("New request field NO_AVAILIBILITY does not exist");
		}
		assertThat("New request field NO_AVAILIBILITY has incorrect type", request.get("NO_AVAILIBILITY"), instanceOf(String.class));
		try {
			request.put("TRAVELAGENCYNUMBER", "12345678");
		} catch (IllegalArgumentException e) {
			fail("New request field TRAVELAGENCYNUMBER does not exist");
		}
		assertThat("New request field TRAVELAGENCYNUMBER has incorrect type", request.get("TRAVELAGENCYNUMBER"), instanceOf(String.class));
		assertThat("New request field AVAILIBILITY has incorrect type", request.get("AVAILIBILITY"), instanceOf(Table.class));
		assertThat("New request field EXTENSION_IN has incorrect type", request.get("EXTENSION_IN"), instanceOf(Table.class));
		assertThat("New request field EXTENSION_OUT has incorrect type", request.get("EXTENSION_OUT"), instanceOf(Table.class));
		assertThat("New request field FLIGHT_HOP_LIST has incorrect type", request.get("FLIGHT_HOP_LIST"), instanceOf(Table.class));
		@SuppressWarnings("rawtypes")
		Table rtn = request.get("RETURN", Table.class);
		assertThat("New request field RETURN has incorrect type", rtn, instanceOf(Table.class));
		Structure bapiRtn2 = rtn.add();
		assertNotNull("Failed to creat row in RETURN table", bapiRtn2);
		try {
			bapiRtn2.put("TYPE", "S");
		} catch (IllegalArgumentException e) {
			fail("RETURN field TYPE does not exist");
		}
		assertThat("New request field TYPE has incorrect type", bapiRtn2.get("TYPE"), instanceOf(String.class));
		assertEquals("New request field TYPE has incorrect value", "S", bapiRtn2.get("TYPE"));
		try {
			bapiRtn2.put("ID", "ABCDEFGHIJ0123456789");
		} catch (IllegalArgumentException e) {
			fail("RETURN field ID does not exist");
		}
		assertThat("New request field ID has incorrect type", bapiRtn2.get("ID"), instanceOf(String.class));
		assertEquals("New request field ID has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("ID"));
		try {
			bapiRtn2.put("MESSAGE", "Four score and seven years ago our fathers brought forth on this continent a new nation ...");
		} catch (IllegalArgumentException e) {
			fail("RETURN field MESSAGE does not exist");
		}
		assertThat("New request field MESSAGE has incorrect type", bapiRtn2.get("MESSAGE"), instanceOf(String.class));
		assertEquals("New request field MESSAGE has incorrect value", "Four score and seven years ago our fathers brought forth on this continent a new nation ...", bapiRtn2.get("MESSAGE"));
		try {
			bapiRtn2.put("LOG_NO", "ABCDEFGHIJ0123456789");
		} catch (IllegalArgumentException e) {
			fail("RETURN field LOG_NO does not exist");
		}
		assertThat("New request field LOG_NO has incorrect type", bapiRtn2.get("LOG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_NO has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("LOG_NO"));
		try {
			bapiRtn2.put("LOG_MSG_NO", "012345");
		} catch (IllegalArgumentException e) {
			fail("RETURN field LOG_MSG_NO does not exist");
		}
		assertThat("New request field LOG_MSG_NO has incorrect type", bapiRtn2.get("LOG_MSG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_MSG_NO has incorrect value", "012345", bapiRtn2.get("LOG_MSG_NO"));
		try {
			bapiRtn2.put("PARAMETER", "FLIGHT_HOP_LIST");
		} catch (IllegalArgumentException e) {
			fail("RETURN field PARAMETER does not exist");
		}
		assertThat("New request field PARAMETER has incorrect type", bapiRtn2.get("PARAMETER"), instanceOf(String.class));
		assertEquals("New request field PARAMETER has incorrect value", "FLIGHT_HOP_LIST", bapiRtn2.get("PARAMETER"));
		try {
			bapiRtn2.put("ROW", 0);
		} catch (IllegalArgumentException e) {
			fail("RETURN field ROW does not exist");
		}
		assertThat("New request field ROW has incorrect type", bapiRtn2.get("ROW"), instanceOf(Integer.class));
		assertEquals("New request field ROW has incorrect value", 0, bapiRtn2.get("ROW"));
		try {
			bapiRtn2.put("FIELD", "HOP");
		} catch (IllegalArgumentException e) {
			fail("RETURN field FIELD does not exist");
		}
		assertThat("New request field FIELD has incorrect type", bapiRtn2.get("FIELD"), instanceOf(String.class));
		assertEquals("New request field FIELD has incorrect value", "HOP", bapiRtn2.get("FIELD"));
		try {
			bapiRtn2.put("SYSTEM", "NPL");
		} catch (IllegalArgumentException e) {
			fail("RETURN field SYSTEM does not exist");
		}
		assertThat("New request field SYSTEM has incorrect type", bapiRtn2.get("SYSTEM"), instanceOf(String.class));
		assertEquals("New request field SYSTEM has incorrect value", "NPL", bapiRtn2.get("SYSTEM"));
	
	}

	@Test
	public void testCreateResponse() throws Exception {
		// Load base and derived RFC packages in global registry from test file.  
        File file = new File("data/testRfcRegistry.ecore");
		IDocUtil.loadRegistry(file);

		// Test that packages loaded.
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc");
		assertNotNull("Failded to load base RFC package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to load derived RFC package", ePackage);

		Response response = RfcUtil.getResponse("NPL", "BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to create response", response);
		assertEquals("New response has incorrect name", "Response", response.getName());
		assertEquals("New response has incorrect field count", 7, response.getFieldCount());
		assertEquals("New response has incorrect record length", -1, response.getRecordLength());
		assertEquals("New response has incorrect unicode record length", -1, response.getUnicodeRecordLength());
		assertEquals("New response has incorrect nested type1 structure flag", false, response.isNestedType1Structure());
		
		assertThat("New request field CONNECTION_DATA has incorrect type", response.get("CONNECTION_DATA"), instanceOf(Structure.class));
		assertThat("New request field PRICE_INFO has incorrect type", response.get("PRICE_INFO"), instanceOf(Structure.class));
		assertThat("New request field AVAILIBILITY has incorrect type", response.get("AVAILIBILITY"), instanceOf(Table.class));
		assertThat("New request field EXTENSION_IN has incorrect type", response.get("EXTENSION_IN"), instanceOf(Table.class));
		assertThat("New request field EXTENSION_OUT has incorrect type", response.get("EXTENSION_OUT"), instanceOf(Table.class));
		assertThat("New request field FLIGHT_HOP_LIST has incorrect type", response.get("FLIGHT_HOP_LIST"), instanceOf(Table.class));
		@SuppressWarnings("rawtypes")
		Table rtn = response.get("RETURN", Table.class);
		assertThat("New request field RETURN has incorrect type", rtn, instanceOf(Table.class));
		Structure bapiRtn2 = rtn.add();
		assertNotNull("Failed to creat row in RETURN table", bapiRtn2);
		try {
			bapiRtn2.put("TYPE", "S");
		} catch (IllegalArgumentException e) {
			fail("RETURN field TYPE does not exist");
		}
		assertThat("New request field TYPE has incorrect type", bapiRtn2.get("TYPE"), instanceOf(String.class));
		assertEquals("New request field TYPE has incorrect value", "S", bapiRtn2.get("TYPE"));
		try {
			bapiRtn2.put("ID", "ABCDEFGHIJ0123456789");
		} catch (IllegalArgumentException e) {
			fail("RETURN field ID does not exist");
		}
		assertThat("New request field ID has incorrect type", bapiRtn2.get("ID"), instanceOf(String.class));
		assertEquals("New request field ID has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("ID"));
		try {
			bapiRtn2.put("MESSAGE", "Four score and seven years ago our fathers brought forth on this continent a new nation ...");
		} catch (IllegalArgumentException e) {
			fail("RETURN field MESSAGE does not exist");
		}
		assertThat("New request field MESSAGE has incorrect type", bapiRtn2.get("MESSAGE"), instanceOf(String.class));
		assertEquals("New request field MESSAGE has incorrect value", "Four score and seven years ago our fathers brought forth on this continent a new nation ...", bapiRtn2.get("MESSAGE"));
		try {
			bapiRtn2.put("LOG_NO", "ABCDEFGHIJ0123456789");
		} catch (IllegalArgumentException e) {
			fail("RETURN field LOG_NO does not exist");
		}
		assertThat("New request field LOG_NO has incorrect type", bapiRtn2.get("LOG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_NO has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("LOG_NO"));
		try {
			bapiRtn2.put("LOG_MSG_NO", "012345");
		} catch (IllegalArgumentException e) {
			fail("RETURN field LOG_MSG_NO does not exist");
		}
		assertThat("New request field LOG_MSG_NO has incorrect type", bapiRtn2.get("LOG_MSG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_MSG_NO has incorrect value", "012345", bapiRtn2.get("LOG_MSG_NO"));
		try {
			bapiRtn2.put("PARAMETER", "FLIGHT_HOP_LIST");
		} catch (IllegalArgumentException e) {
			fail("RETURN field PARAMETER does not exist");
		}
		assertThat("New request field PARAMETER has incorrect type", bapiRtn2.get("PARAMETER"), instanceOf(String.class));
		assertEquals("New request field PARAMETER has incorrect value", "FLIGHT_HOP_LIST", bapiRtn2.get("PARAMETER"));
		try {
			bapiRtn2.put("ROW", 0);
		} catch (IllegalArgumentException e) {
			fail("RETURN field ROW does not exist");
		}
		assertThat("New request field ROW has incorrect type", bapiRtn2.get("ROW"), instanceOf(Integer.class));
		assertEquals("New request field ROW has incorrect value", 0, bapiRtn2.get("ROW"));
		try {
			bapiRtn2.put("FIELD", "HOP");
		} catch (IllegalArgumentException e) {
			fail("RETURN field FIELD does not exist");
		}
		assertThat("New request field FIELD has incorrect type", bapiRtn2.get("FIELD"), instanceOf(String.class));
		assertEquals("New request field FIELD has incorrect value", "HOP", bapiRtn2.get("FIELD"));
		try {
			bapiRtn2.put("SYSTEM", "NPL");
		} catch (IllegalArgumentException e) {
			fail("RETURN field SYSTEM does not exist");
		}
		assertThat("New request field SYSTEM has incorrect type", bapiRtn2.get("SYSTEM"), instanceOf(String.class));
		assertEquals("New request field SYSTEM has incorrect value", "NPL", bapiRtn2.get("SYSTEM"));

	}
	
	@Test
	public void testSaveAndLoadResponse() throws Exception {
		// Load base and derived RFC packages in global registry from test file.
		File file = new File("data/testRfcRegistry.ecore");
		IDocUtil.loadRegistry(file);

		// Test that packages loaded.
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc");
		assertNotNull("Failded to load base RFC package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/rfc/NPL/BAPI_FLCONN_GETDETAIL");
		assertNotNull("Failed to load derived RFC package", ePackage);

		Response response = RfcUtil.getResponse("NPL", "BAPI_FLCONN_GETDETAIL");
		@SuppressWarnings("rawtypes")
		Table rtn = response.get("RETURN", Table.class);
		assertThat("New request field RETURN has incorrect type", rtn, instanceOf(Table.class));
		Structure bapiRtn2 = rtn.add();
		assertNotNull("Failed to creat row in RETURN table", bapiRtn2);
		bapiRtn2.put("TYPE", "S");
		bapiRtn2.put("ID", "ABCDEFGHIJ0123456789");
		bapiRtn2.put("MESSAGE", "Four score and seven years ago our fathers brought forth on this continent a new nation ...");
		bapiRtn2.put("LOG_NO", "ABCDEFGHIJ0123456789");
		bapiRtn2.put("LOG_MSG_NO", "012345");
		bapiRtn2.put("PARAMETER", "FLIGHT_HOP_LIST");
		bapiRtn2.put("ROW", 0);
		bapiRtn2.put("FIELD", "HOP");
		bapiRtn2.put("SYSTEM", "NPL");
		
		// Save IDoc document
		file = new File("data/testResponse.xml");
		IDocUtil.save(file, response);
		
		// Load saved IDoc document
		response = (Response) IDocUtil.load(file);
		
		rtn = response.get("RETURN", Table.class);
		assertThat("New request field RETURN has incorrect type", rtn, instanceOf(Table.class));
		bapiRtn2 = rtn.getRow(0);
		assertNotNull("Failed to creat row in RETURN table", bapiRtn2);
		assertThat("New request field ID has incorrect type", bapiRtn2.get("ID"), instanceOf(String.class));
		assertEquals("New request field ID has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("ID"));
		assertThat("New request field MESSAGE has incorrect type", bapiRtn2.get("MESSAGE"), instanceOf(String.class));
		assertEquals("New request field MESSAGE has incorrect value", "Four score and seven years ago our fathers brought forth on this continent a new nation ...", bapiRtn2.get("MESSAGE"));
		assertThat("New request field LOG_NO has incorrect type", bapiRtn2.get("LOG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_NO has incorrect value", "ABCDEFGHIJ0123456789", bapiRtn2.get("LOG_NO"));
		assertThat("New request field LOG_MSG_NO has incorrect type", bapiRtn2.get("LOG_MSG_NO"), instanceOf(String.class));
		assertEquals("New request field LOG_MSG_NO has incorrect value", "012345", bapiRtn2.get("LOG_MSG_NO"));
		assertThat("New request field PARAMETER has incorrect type", bapiRtn2.get("PARAMETER"), instanceOf(String.class));
		assertEquals("New request field PARAMETER has incorrect value", "FLIGHT_HOP_LIST", bapiRtn2.get("PARAMETER"));
		assertThat("New request field ROW has incorrect type", bapiRtn2.get("ROW"), instanceOf(Integer.class));
		assertEquals("New request field ROW has incorrect value", 0, bapiRtn2.get("ROW"));
		assertThat("New request field FIELD has incorrect type", bapiRtn2.get("FIELD"), instanceOf(String.class));
		assertEquals("New request field FIELD has incorrect value", "HOP", bapiRtn2.get("FIELD"));
		assertThat("New request field SYSTEM has incorrect type", bapiRtn2.get("SYSTEM"), instanceOf(String.class));
		assertEquals("New request field SYSTEM has incorrect value", "NPL", bapiRtn2.get("SYSTEM"));
	}
	
	//@Test
	public void testFunctionCall() throws JCoException {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination"); 
		
		Structure request = RfcUtil.getRequest(jcoDestination.getRepository(), "STFC_CONNECTION");
		RfcUtil.setValue(request, "REQUTEXT", "Hello, SAP!");
		
		Structure response = RfcUtil.executeFunction(jcoDestination, "STFC_CONNECTION", request);
		
		String echoText = (String) RfcUtil.getValue(response, "ECHOTEXT");
		String respText = (String) RfcUtil.getValue(response, "RESPTEXT");
		
		assertEquals("ECHOTEXT of response different from REQUTEXT of request", "Hello, SAP!", echoText);
		System.out.println("RESPTEXT: " + respText);
	}
	
	//@Test
	public void testMashalling() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		
		Structure customerData = (Structure) RfcUtil.createInstance(jcoDestination.getRepository(), "BAPI_FLCUST_GETLIST", "BAPISCUDAT");
		EList<EStructuralFeature> list = customerData.eClass().getEAllStructuralFeatures();
		for(EStructuralFeature f: list) {
			System.out.println(f);
		}
		
		Structure request = RfcUtil.getRequest(jcoDestination.getRepository(), "BAPI_FLCUST_GETLIST");
		Table<?> table = (Table<?>) RfcUtil.getValue(request, "CUSTOMER_LIST");
		System.out.println("Table type name: " + table.eClass().getName());
		System.out.println("Table type Generic SuperTypes: " + table.eClass().getEGenericSuperTypes());
		EGenericType genericSuperType = table.eClass().getEGenericSuperTypes().get(0);
		System.out.println("Generic SuperType Type Arguments: " + genericSuperType.getETypeArguments());
		EGenericType typeArgument = genericSuperType.getETypeArguments().get(0);
		System.out.println("Type Argument Classifier: " + typeArgument.getEClassifier());
		
		EStructuralFeature feature = table.eClass().getEStructuralFeature("row");
		EClass rowType = ((EReference) feature).getEReferenceType();
		System.out.println("Row Type: " + rowType);
		System.out.println("Row Generic Type" + ((EReference) feature).getEGenericType());
		list = rowType.getEAllStructuralFeatures();
		for(EStructuralFeature f: list) {
			System.out.println(f);
		}
		@SuppressWarnings("unused")
		Structure newRow = (Structure) rowType.getEPackage().getEFactoryInstance().create(rowType);
		
		EClass tableClass = RfcPackage.eINSTANCE.getTable();
		System.out.println("Table class: " + tableClass);
		EList<ETypeParameter> tableClassTypeParameters = tableClass.getETypeParameters();
		System.out.println("Table class type parameters " + tableClassTypeParameters);
		@SuppressWarnings("unused")
		ETypeParameter typeParameter = tableClassTypeParameters.get(0);
	}
		
	//@Test
	public void testRequest() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		Structure request = RfcUtil.getRequest(jcoDestination.getRepository(), "BAPI_FLCONN_GETDETAIL");
		request.put("TRAVELAGENCYNUMBER", "00000110");
		request.put("CONNECTIONNUMBER", "0002");
		request.put("FLIGHTDATE", new GregorianCalendar(2012, 01, 01).getTime());
		
        Resource res = new XMLResourceImpl();
        res.getContents().add(request);
        res.save(System.out, null);
	}
	
	//@Test
	public void testFlightConnectionGetListRequest() throws Exception {

		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		Structure request = RfcUtil.getRequest(jcoDestination.getRepository(), "BAPI_FLCONN_GETLIST");
		request.put("TRAVELAGENCY", "00000110");

		@SuppressWarnings("unchecked")
		Table<Structure> table = (Table<Structure>) request.get("DATE_RANGE");
		Structure date_range = table.add();
		date_range.put("SIGN", "I");
		date_range.put("OPTION", "EQ");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date flightDate = dateFormat.parse("2012-02-01T00:00:00.000-0500");
		date_range.put("LOW", flightDate);
		
		Structure destination_from = (Structure) request.get("DESTINATION_FROM");
		destination_from.put("AIRPORTID", "SFO");
		
		Structure destination_to = (Structure) request.get("DESTINATION_TO");
		destination_to.put("AIRPORTID", "FRA");
		
		String requestString = RfcUtil.marshal(request);
		
		System.out.println(requestString);
	}

}
