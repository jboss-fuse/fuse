package org.fusesource.camel.component.sap.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.SegmentList;
import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.ServerData;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocDocumentList;
import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.idoc.jco.JCoIDocHandler;
import com.sap.conn.idoc.jco.JCoIDocHandlerFactory;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.idoc.jco.JCoIDocServerContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;
import com.sap.conn.jco.server.JCoServerTIDHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IDocUtilTest {

	private static final Logger LOG = LoggerFactory.getLogger(IDocUtilTest.class);

	protected DestinationDataStore destinationDataStore;
	protected ServerDataStore serverDataStore;

	@Before
	public void setUp() throws Exception {

		DestinationData destinationData = RfcFactory.eINSTANCE.createDestinationData();
		destinationData.setAshost("nplhost");
		destinationData.setSysnr("42");
		destinationData.setClient("002");
		destinationData.setUser("developer");
		destinationData.setPasswd("ch4ngeme");
		destinationData.setLang("en");

		destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
		destinationDataStore.getEntries().put("TestDestination", destinationData);

		ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);

		ServerData serverData = RfcFactory.eINSTANCE.createServerData();
		serverData.setProgid("IDOCSERVER01");
		serverData.setGwhost("nplhost");
		serverData.setGwserv("3342");
		serverData.setConnectionCount("1");
		serverData.setRepositoryDestination("TestDestination");

		serverDataStore = RfcFactory.eINSTANCE.createServerDataStore();
		serverDataStore.getEntries().put("TestServer", serverData);

		ComponentServerDataProvider.INSTANCE.addServerDataStore(serverDataStore);
	}

	/**
	 * Creates and saves Test Registry for off-line tests
	 * 
	 * @throws Exception
	 */
	//@Test
	public void createTestRegistry() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		IDocRepository repository = JCoIDoc.getIDocRepository(jcoDestination);
		IDocUtil.getEPackage(repository, "http://sap.fusesource.org/idoc");
		IDocUtil.getEPackage(repository, "http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		File file = new File("data/testRegistry.ecore");
		IDocUtil.saveRegistry(file);
	}

	// @Test
	public void testPackage() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		IDocRepository repository = JCoIDoc.getIDocRepository(jcoDestination);
		EPackage ePackage = IDocUtil.getEPackage(repository, "http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		Util.print(ePackage);
	}

	/**
	 * Tests the saving and loading of IDoc packages into global package
	 * registry.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRegistrySaveAndLoad() throws Exception {

		// Load base and derived IDoc packages in global registry from test
		// file.
		File file = new File("data/testRegistry.ecore");
		IDocUtil.loadRegistry(file);

		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc");
		assertNotNull("Failded to load base IDoc package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		assertNotNull("Failed to load derived IDoc package", ePackage);

		// Save base and derived IDoc packages to new test file.
		file = new File("data/savedTestRegistry.ecore");
		IDocUtil.saveRegistry(file);

		// Clear IDoc packages from registry.
		EPackage.Registry.INSTANCE.remove("http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");

		// Make sure they were cleared.
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		assertNull("Failed to clear derived IDoc package", ePackage);

		IDocUtil.loadRegistry(file);

		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc");
		assertNotNull("Failded to load base IDoc package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		assertNotNull("Failed to load derived IDoc package", ePackage);
	}

	@Test
	public void testCreateIDoc() throws Exception {
		// Load base and derived IDoc packages in global registry from test
		// file.
		File file = new File("data/testRegistry.ecore");
		IDocUtil.loadRegistry(file);

		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc");
		assertNotNull("Failded to load base IDoc package", ePackage);
		ePackage = EPackage.Registry.INSTANCE.getEPackage("http://sap.fusesource.org/idoc/NPL/HRMD_B01//31G/");
		assertNotNull("Failed to load derived IDoc package", ePackage);

		Document document = IDocUtil.createDocument("NPL", "HRMD_B01", null, "31G", null);
		assertNotNull("Failed to create IDoc document", document);
		assertEquals("New IDoc document has incorrect IDoc type", "HRMD_B01", document.getIDocType());
		assertEquals("New IDoc document has incorrect IDoc type extension", "", document.getIDocTypeExtension());

		Segment rootSegment = document.getRootSegment();
		assertEquals("New IDoc document has incorrect root segment type", "ROOT", rootSegment.getType());
		assertEquals("Root segment of new IDoc document has incorrect hierarchy level", 1, rootSegment.getHierarchyLevel());
	}

	@Test
	public void testBuildIDoc() throws Exception {
		// Load base and derived IDoc packages in global registry from test
		// file.
		File file = new File("data/testRegistry.ecore");
		IDocUtil.loadRegistry(file);

		Document document = IDocUtil.createDocument("NPL", "HRMD_B01", null, "31G", null);

		Segment rootSegment = document.getRootSegment();
		assertNotNull("Failed to retrieve ROOT segment", rootSegment);
		assertEquals("ROOT Segment has unexpected Type value", "ROOT", rootSegment.getType());
		assertEquals("ROOT Segment has unexpected Definition value", "ROOT", rootSegment.getDefinition());
		assertEquals("ROOT Segment has unexpected Description value", "General root segment", rootSegment.getDescription());
		assertEquals("ROOT Segment has unexpected Hierarchy Level value", 1, rootSegment.getHierarchyLevel());
		assertEquals("ROOT Segment has unexpected IDoc Type value", "HRMD_B01", rootSegment.getIdocType());
		assertEquals("ROOT Segment has unexpected IDoc Type Extension value", "", rootSegment.getIdocTypeExtension());
		assertEquals("ROOT Segment has unexpected System Release value", "31G", rootSegment.getSystemRelease());
		assertEquals("ROOT Segment has unexpected Application Release value", "", rootSegment.getApplicationRelease());
		assertEquals("ROOT Segment has unexpected Number of Fields value", 0, rootSegment.getNumFields());
		assertEquals("ROOT Segment has unexpected Maximum Occurrence of Segment value", 1L, rootSegment.getMaxOccurrence());
		assertEquals("ROOT Segment has unexpected Minimum Occurrence of Segment value", 1L, rootSegment.getMinOccurrence());
		assertEquals("ROOT Segment has unexpected Is Mandatory value", true, rootSegment.isMandatory());
		assertEquals("ROOT Segment has unexpected Is Qualified value", false, rootSegment.isQualified());
		assertEquals("ROOT Segment has unexpected Is Qualified value", 0, rootSegment.getRecordLength());

		SegmentList<Segment> e1plogiSegments = rootSegment.getChildren("E1PLOGI");
		Segment e1plogiSegment = e1plogiSegments.add();
		e1plogiSegment.put("PLVAR", "AB");
		e1plogiSegment.put("OTYPE", "AB");
		e1plogiSegment.put("OBJID", "12345678");
		e1plogiSegment.put("PROOF", "X");
		e1plogiSegment.put("OPERA", "X");

		e1plogiSegment = e1plogiSegments.add(0);
		e1plogiSegment.put("PLVAR", "BA");
		e1plogiSegment.put("OTYPE", "BA");
		e1plogiSegment.put("OBJID", "87654321");
		e1plogiSegment.put("PROOF", "Y");
		e1plogiSegment.put("OPERA", "Y");

		e1plogiSegment = rootSegment.getChildren("E1PLOGI").get(0);
		assertNotNull("Failed to retrieve E1PLOGI Segment", e1plogiSegment);
		assertEquals("E1PLOGI Segment has unexpected Type value", "E1PLOGI", e1plogiSegment.getType());
		assertEquals("E1PLOGI Segment has unexpected Definition value", "E2PLOGI", e1plogiSegment.getDefinition());
		assertEquals("E1PLOGI Segment has unexpected Description value", "Header for an HR Object (Master Data or Organizational Data)",
				e1plogiSegment.getDescription());
		assertEquals("E1PLOGI Segment has unexpected Hierarchy Level value", 2, e1plogiSegment.getHierarchyLevel());
		assertEquals("E1PLOGI Segment has unexpected IDoc Type value", "HRMD_B01", e1plogiSegment.getIdocType());
		assertEquals("E1PLOGI Segment has unexpected IDoc Type Extension value", "", e1plogiSegment.getIdocTypeExtension());
		assertEquals("E1PLOGI Segment has unexpected System Release value", "31G", e1plogiSegment.getSystemRelease());
		assertEquals("E1PLOGI Segment has unexpected Application Release value", "", e1plogiSegment.getApplicationRelease());
		assertEquals("E1PLOGI Segment has unexpected Number of Fields value", 5, e1plogiSegment.getNumFields());
		assertEquals("E1PLOGI Segment has unexpected Maximum Occurrence of Segment value", 9999999999L, e1plogiSegment.getMaxOccurrence());
		assertEquals("E1PLOGI Segment has unexpected Minimum Occurrence of Segment value", 1L, e1plogiSegment.getMinOccurrence());
		assertEquals("E1PLOGI Segment has unexpected Is Mandatory value", true, e1plogiSegment.isMandatory());
		assertEquals("E1PLOGI Segment has unexpected Is Qualified value", false, e1plogiSegment.isQualified());
		assertEquals("E1PLOGI Segment has unexpected Is Qualified value", 14, e1plogiSegment.getRecordLength());
		assertEquals("E1PLOGI Segment field PLVAR has unexpected value", "BA", e1plogiSegment.get("PLVAR"));
		assertEquals("E1PLOGI Segment field OTYPE has unexpected value", "BA", e1plogiSegment.get("OTYPE"));
		assertEquals("E1PLOGI Segment field OBJID has unexpected value", "87654321", e1plogiSegment.get("OBJID"));
		assertEquals("E1PLOGI Segment field PROOF has unexpected value", "Y", e1plogiSegment.get("PROOF"));
		assertEquals("E1PLOGI Segment field OPERA has unexpected value", "Y", e1plogiSegment.get("OPERA"));

		e1plogiSegment = rootSegment.getChildren("E1PLOGI").get(1);
		assertNotNull("Failed to retrieve E1PLOGI Segment", e1plogiSegment);
		assertEquals("E1PLOGI Segment has unexpected Type value", "E1PLOGI", e1plogiSegment.getType());
		assertEquals("E1PLOGI Segment has unexpected Definition value", "E2PLOGI", e1plogiSegment.getDefinition());
		assertEquals("E1PLOGI Segment has unexpected Description value", "Header for an HR Object (Master Data or Organizational Data)",
				e1plogiSegment.getDescription());
		assertEquals("E1PLOGI Segment has unexpected Hierarchy Level value", 2, e1plogiSegment.getHierarchyLevel());
		assertEquals("E1PLOGI Segment has unexpected IDoc Type value", "HRMD_B01", e1plogiSegment.getIdocType());
		assertEquals("E1PLOGI Segment has unexpected IDoc Type Extension value", "", e1plogiSegment.getIdocTypeExtension());
		assertEquals("E1PLOGI Segment has unexpected System Release value", "31G", e1plogiSegment.getSystemRelease());
		assertEquals("E1PLOGI Segment has unexpected Application Release value", "", e1plogiSegment.getApplicationRelease());
		assertEquals("E1PLOGI Segment has unexpected Number of Fields value", 5, e1plogiSegment.getNumFields());
		assertEquals("E1PLOGI Segment has unexpected Maximum Occurrence of Segment value", 9999999999L, e1plogiSegment.getMaxOccurrence());
		assertEquals("E1PLOGI Segment has unexpected Minimum Occurrence of Segment value", 1L, e1plogiSegment.getMinOccurrence());
		assertEquals("E1PLOGI Segment has unexpected Is Mandatory value", true, e1plogiSegment.isMandatory());
		assertEquals("E1PLOGI Segment has unexpected Is Qualified value", false, e1plogiSegment.isQualified());
		assertEquals("E1PLOGI Segment has unexpected Is Qualified value", 14, e1plogiSegment.getRecordLength());
		assertEquals("E1PLOGI Segment field PLVAR has unexpected value", "AB", e1plogiSegment.get("PLVAR"));
		assertEquals("E1PLOGI Segment field OTYPE has unexpected value", "AB", e1plogiSegment.get("OTYPE"));
		assertEquals("E1PLOGI Segment field OBJID has unexpected value", "12345678", e1plogiSegment.get("OBJID"));
		assertEquals("E1PLOGI Segment field PROOF has unexpected value", "X", e1plogiSegment.get("PROOF"));
		assertEquals("E1PLOGI Segment field OPERA has unexpected value", "X", e1plogiSegment.get("OPERA"));

	}

	public void testSendIDoc() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		IDocRepository repository = JCoIDoc.getIDocRepository(jcoDestination);
		Document document = IDocUtil.createDocument(repository, "FLCUSTOMER_CREATEFROMDATA01", null, null, null);

		Segment rootSegment = document.getRootSegment();
		Segment headerSegment = rootSegment.getChildren("E1SCU_CRE").add();
		Segment newCustomerSegment = headerSegment.getChildren("E1BPSCUNEW").add();

		// Fill in New Customer Info
		newCustomerSegment.put("CUSTNAME", "Fred Flintstoned");
		newCustomerSegment.put("FORM", "Mr.");
		newCustomerSegment.put("STREET", "123 Rubble Lane");
		newCustomerSegment.put("POSTCODE", "01234");
		newCustomerSegment.put("CITY", "Bedrock");
		newCustomerSegment.put("COUNTR", "US");
		newCustomerSegment.put("PHONE", "800-555-1212");
		newCustomerSegment.put("EMAIL", "fred@bedrock.com");
		newCustomerSegment.put("CUSTTYPE", "P");
		newCustomerSegment.put("DISCOUNT", "005");
		newCustomerSegment.put("LANGU", "E");

		document.setMessageType("FLCUSTOMER_CREATEFROMDATA");
		document.setRecipientPartnerNumber("NPLCLNT002");
		document.setRecipientPartnerType("LS");
		document.setSenderPartnerNumber("JCOCLIENT");
		document.setSenderPartnerType("LS");

		String tid = jcoDestination.createTID();
		IDocUtil.sendDocument(jcoDestination, document, tid);
		jcoDestination.confirmTID(tid);

	}

	/**
	 * Creates and saves Test Registry for off-line tests
	 * 
	 * @throws Exception
	 */
	// @Test
	public void createLoadIDocRegistry() throws Exception {
		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		IDocRepository repository = JCoIDoc.getIDocRepository(jcoDestination);
		IDocUtil.getEPackage(repository, "http://sap.fusesource.org/idoc");
		IDocUtil.getEPackage(repository, "http://sap.fusesource.org/idoc/NPL/FLCUSTOMER_CREATEFROMDATA01///");
		File file = new File("data/testLoadIDocRegistry.ecore");
		IDocUtil.saveRegistry(file);
	}

	@Test
	public void testSaveAndLoadIDoc() throws Exception {
		// Load base and derived IDoc packages in global registry from test
		// file.
		File file = new File("data/testLoadIDocRegistry.ecore");
		IDocUtil.loadRegistry(file);

		// Create IDoc document
		Document document = IDocUtil.createDocument("NPL", "FLCUSTOMER_CREATEFROMDATA01", null, null, null);

		Segment rootSegment = document.getRootSegment();
		Segment headerSegment = rootSegment.getChildren("E1SCU_CRE").add();
		Segment newCustomerSegment = headerSegment.getChildren("E1BPSCUNEW").add();

		// Fill in New Customer Info
		newCustomerSegment.put("CUSTNAME", "Fred Flintstone");
		newCustomerSegment.put("FORM", "Mr.");
		newCustomerSegment.put("STREET", "123 Rubble Lane");
		newCustomerSegment.put("POSTCODE", "01234");
		newCustomerSegment.put("CITY", "Bedrock");
		newCustomerSegment.put("COUNTR", "US");
		newCustomerSegment.put("PHONE", "800-555-1212");
		newCustomerSegment.put("EMAIL", "fred@bedrock.com");
		newCustomerSegment.put("CUSTTYPE", "P");
		newCustomerSegment.put("DISCOUNT", "005");
		newCustomerSegment.put("LANGU", "E");

		// Fill in control info
		document.setMessageType("FLCUSTOMER_CREATEFROMDATA");
		document.setRecipientPartnerNumber("NPLCLNT002");
		document.setRecipientPartnerType("LS");
		document.setSenderPartnerNumber("JCOCLIENT");
		document.setSenderPartnerType("LS");

		// Save IDoc document
		file = new File("data/testIDoc.xml");
		IDocUtil.save(file, document);

		// Load saved IDoc document
		document = (Document) IDocUtil.load(file);

		// Validate loaded document
		assertEquals("Loaded document has unexpected MessageType", "FLCUSTOMER_CREATEFROMDATA", document.getMessageType());
		assertEquals("Loaded document has unexpected RecipientPartnerNumber", "NPLCLNT002", document.getRecipientPartnerNumber());
		assertEquals("Loaded document has unexpected RecipientPartnerType", "LS", document.getRecipientPartnerType());
		assertEquals("Loaded document has unexpected SenderPartnerNumber", "JCOCLIENT", document.getSenderPartnerNumber());
		assertEquals("Loaded document has unexpected SenderPartnerType", "LS", document.getSenderPartnerType());

		rootSegment = document.getRootSegment();
		assertNotNull("Failed to load ROOT segment", rootSegment);

		assertNotNull("Failed to load Header Segment", rootSegment.getChildren("E1SCU_CRE"));
		headerSegment = rootSegment.getChildren("E1SCU_CRE").get(0);
		assertNotNull("Failed to load Header Segment", headerSegment);

		assertNotNull("Failed to load New Customer Segment", headerSegment.getChildren("E1BPSCUNEW"));
		newCustomerSegment = headerSegment.getChildren("E1BPSCUNEW").get(0);
		assertNotNull("Failed to load New Customer Segment", newCustomerSegment);

		assertEquals("Loaded New Customer Segment has unexpected CUSTNAME", "Fred Flintstone", newCustomerSegment.get("CUSTNAME"));
		assertEquals("Loaded New Customer Segment has unexpected FORM", "Mr.", newCustomerSegment.get("FORM"));
		assertEquals("Loaded New Customer Segment has unexpected STREET", "123 Rubble Lane", newCustomerSegment.get("STREET"));
		assertEquals("Loaded New Customer Segment has unexpected POSTCODE", "01234", newCustomerSegment.get("POSTCODE"));
		assertEquals("Loaded New Customer Segment has unexpected CITY", "Bedrock", newCustomerSegment.get("CITY"));
		assertEquals("Loaded New Customer Segment has unexpected COUNTR", "US", newCustomerSegment.get("COUNTR"));
		assertEquals("Loaded New Customer Segment has unexpected PHONE", "800-555-1212", newCustomerSegment.get("PHONE"));
		assertEquals("Loaded New Customer Segment has unexpected EMAIL", "fred@bedrock.com", newCustomerSegment.get("EMAIL"));
		assertEquals("Loaded New Customer Segment has unexpected CUSTTYPE", "P", newCustomerSegment.get("CUSTTYPE"));
		assertEquals("Loaded New Customer Segment has unexpected DISCOUNT", "005", newCustomerSegment.get("DISCOUNT"));
		assertEquals("Loaded New Customer Segment has unexpected LANGU", "E", newCustomerSegment.get("LANGU"));

	}

	public void testMarshalUnmarshalIDoc() throws Exception {

		JCoDestination jcoDestination = JCoDestinationManager.getDestination("TestDestination");
		IDocRepository repository = JCoIDoc.getIDocRepository(jcoDestination);
		Document document = IDocUtil.createDocument(repository, "FLCUSTOMER_CREATEFROMDATA01", null, null, null);

		Segment rootSegment = document.getRootSegment();
		Segment headerSegment = rootSegment.getChildren("E1SCU_CRE").add();
		Segment newCustomerSegment = headerSegment.getChildren("E1BPSCUNEW").add();

		// Fill in New Customer Info
		newCustomerSegment.put("CUSTNAME", "Fred Flintstone");
		newCustomerSegment.put("FORM", "Mr.");
		newCustomerSegment.put("STREET", "123 Rubble Lane");
		newCustomerSegment.put("POSTCODE", "01234");
		newCustomerSegment.put("CITY", "Bedrock");
		newCustomerSegment.put("COUNTR", "US");
		newCustomerSegment.put("PHONE", "800-555-1212");
		newCustomerSegment.put("EMAIL", "fred@bedrock.com");
		newCustomerSegment.put("CUSTTYPE", "P");
		newCustomerSegment.put("DISCOUNT", "005");
		newCustomerSegment.put("LANGU", "E");

		document.setMessageType("FLCUSTOMER_CREATEFROMDATA");
		document.setRecipientPartnerNumber("NPLCLNT002");
		document.setRecipientPartnerType("LS");
		document.setSenderPartnerNumber("JCOCLIENT");
		document.setSenderPartnerType("LS");

		String marshalledIDoc = IDocUtil.marshal(document);

		document = (Document) IDocUtil.unmarshal(marshalledIDoc);

		System.out.println(IDocUtil.marshal(document));
	}

	public static class IDocHandlerFactory implements JCoIDocHandlerFactory {

		private Map<String, JCoIDocHandler> iDocHandlers = new HashMap<String, JCoIDocHandler>();

		JCoIDocHandler iDocHandler = new JCoIDocHandler() {
			@Override
			public void handleRequest(JCoServerContext serverContext, IDocDocumentList iDocDocumentList) {
				String key = createKey(iDocDocumentList.getIDocType(), iDocDocumentList.getIDocTypeExtension(), iDocDocumentList.getSystemRelease(),
						iDocDocumentList.getApplicationRelease());
				JCoIDocHandler iDocHandler = iDocHandlers.get(key);
				if (iDocHandler != null) {
					iDocHandler.handleRequest(serverContext, iDocDocumentList);
				}
			}
		};

		public void registerHandler(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease, JCoIDocHandler iDocHandler) {
			iDocHandlers.put(createKey(iDocType, iDocTypeExtension, systemRelease, applicationRelease), iDocHandler);
		}

		public JCoIDocHandler unregisterHandler(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
			return iDocHandlers.remove(createKey(iDocType, iDocTypeExtension, systemRelease, applicationRelease));
		}

		@Override
		public JCoIDocHandler getIDocHandler(JCoIDocServerContext serverContext) {
			return iDocHandler;
		}

		/**
		 * Create key for JCoIDocHandler map
		 * <p>
		 * Key string in the form:
		 * "IDoc Type|IDoc Type Extension|System Release|Application Release".
		 * 
		 * @param iDocType
		 *            - IDoc type
		 * @param iDocTypeExtension
		 *            - IDoc type extenstion
		 * @param systemRelease
		 *            - System release
		 * @param applicationRelease
		 *            - Application release
		 * @return Key string in the form:
		 *         "IDoc Type|IDoc Type Extension|System Release|Application Release"
		 */
		protected String createKey(String iDocType, String iDocTypeExtension, String systemRelease, String applicationRelease) {
			StringBuilder key = new StringBuilder(100);

			key.append(iDocType);
			key.append("|");
			if (iDocTypeExtension != null)
				key.append(iDocTypeExtension);
			key.append("|");
			if (systemRelease != null)
				key.append(systemRelease.replace(".", ""));
			key.append("|");
			if (applicationRelease != null)
				key.append(applicationRelease.replace(".", ""));

			return key.toString();
		}

	}

	// TODO Refactor this as a model object
	private enum TIDState {
		CREATED, EXECUTED, COMMITTED, ROLLED_BACK, CONFIRMED;
	}

	public class ServerTIDHandler implements JCoServerTIDHandler {

		// TODO Create a TID repository model object and persist
		Map<String, TIDState> availableTIDs = new HashMap<String, TIDState>();

		@Override
		public boolean checkTID(JCoServerContext serverContext, String tid) {
			TIDState state = availableTIDs.get(tid);
			if (state == null) {
				availableTIDs.put(tid, TIDState.CREATED);
				LOG.debug("Checked TID '" + tid + "': true");
				return true;
			}

			if (state == TIDState.CREATED || state == TIDState.ROLLED_BACK) {
				LOG.debug("Checked TID '" + tid + "': true");
				return true;
			}

			LOG.debug("Checked TID '" + tid + "': false");
			return false;
		}

		@Override
		public void commit(JCoServerContext serverContext, String tid) {
			availableTIDs.put(tid, TIDState.COMMITTED);
			LOG.debug("Committed TID '" + tid + "'");
		}

		@Override
		public void rollback(JCoServerContext serverContext, String tid) {
			availableTIDs.put(tid, TIDState.ROLLED_BACK);
			LOG.debug("Rolledback TID '" + tid + "'");
		}

		@Override
		public void confirmTID(JCoServerContext serverContext, String tid) {
			availableTIDs.remove(tid);
			LOG.debug("Confirmed TID '" + tid + "'");
		}

		public void execute(JCoServerContext serverContext) {
			String tid = serverContext.getTID();
			if (tid != null) {
				availableTIDs.put(tid, TIDState.EXECUTED);
				LOG.debug("Executed TID '" + tid + "'");
			}
		}

	}

	public static class MyThrowableListener implements JCoServerErrorListener, JCoServerExceptionListener {

		public void serverErrorOccurred(JCoServer server, String connectionId, JCoServerContextInfo ctx, Error error) {
			System.out.println(">>> Error occured on " + server.getProgramID() + " connection " + connectionId);
			error.printStackTrace();
		}

		public void serverExceptionOccurred(JCoServer server, String connectionId, JCoServerContextInfo ctx, Exception error) {
			System.out.println(">>> Error occured on " + server.getProgramID() + " connection " + connectionId);
			error.printStackTrace();
		}
	}

	public static class MyStateChangeListener implements JCoServerStateChangedListener {

		@Override
		public void serverStateChangeOccurred(JCoServer jcoServer, JCoServerState oldState, JCoServerState newState) {
			System.out.println(">>>>>>> " + jcoServer.getProgramID() + " has changed state from '" + oldState + "' to '" + newState + "'");
		}

	}

	public static class FlightCustomerCreateIDocHandler implements JCoIDocHandler {

		public FlightCustomerCreateIDocHandler(IDocRepository repository) {
			super();
			this.repository = repository;
		}

		private IDocRepository repository;

		@Override
		public void handleRequest(JCoServerContext serverContext, IDocDocumentList idocList) {
			for (IDocDocument iDocDocument : idocList.toArray()) {
				Document document = IDocUtil.createDocument(repository, "FLCUSTOMER_CREATEFROMDATA01", null, null, null);
				IDocUtil.extractIDocDocumentIntoDocument(iDocDocument, document);

				try {
					URI uri = URI.createFileURI("/bogus");
					Resource res = new XMLResourceImpl(uri);
					res.getContents().add(document);
					Map<String, Object> options = new HashMap<String, Object>();
					List<Object> lookupTable = new ArrayList<Object>();
					options.put(XMIResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
					options.put(XMIResource.OPTION_USE_CACHED_LOOKUP_TABLE, lookupTable);
					options.put(XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
					options.put(XMIResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
					res.save(System.out, options);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	public void testReceiveIDoc() throws Exception {
		JCoIDocServer server = JCoIDoc.getServer("TestServer");
		IDocHandlerFactory handlerFactory = new IDocHandlerFactory();
		server.setIDocHandlerFactory(handlerFactory);
		server.setTIDHandler(new ServerTIDHandler());
		MyThrowableListener listener = new MyThrowableListener();
		server.addServerErrorListener(listener);
		server.addServerExceptionListener(listener);
		server.addServerStateChangedListener(new MyStateChangeListener());

		String repositoryDestination = server.getRepositoryDestination();
		JCoDestination destination = JCoDestinationManager.getDestination(repositoryDestination);
		IDocRepository iDocRepository = JCoIDoc.getIDocRepository(destination);
		server.setIDocRepository(iDocRepository);

		handlerFactory.registerHandler("FLCUSTOMER_CREATEFROMDATA01", null, null, null, new FlightCustomerCreateIDocHandler(iDocRepository));

		server.start();
		Thread.currentThread().join();
	}

	@Test
	public void testSaveMultipleIDoc() throws Exception {

		// Load base and derived IDoc packages in global registry from test
		// file.
		File file = new File("data/testLoadIDocRegistry.ecore");
		IDocUtil.loadRegistry(file);
		
		DocumentList documents = IDocUtil.createDocumentList("NPL", "FLCUSTOMER_CREATEFROMDATA01", null, null, null);

		for (int i = 0; i < 2; i++) {

			// Create IDoc document
			Document document = IDocUtil.createDocument("NPL", "FLCUSTOMER_CREATEFROMDATA01", null, null, null);

			Segment rootSegment = document.getRootSegment();
			Segment headerSegment = rootSegment.getChildren("E1SCU_CRE").add();
			Segment newCustomerSegment = headerSegment.getChildren("E1BPSCUNEW").add();

			// Fill in New Customer Info
			newCustomerSegment.put("CUSTNAME", "Fred Flintstone");
			newCustomerSegment.put("FORM", "Mr.");
			newCustomerSegment.put("STREET", "123 Rubble Lane");
			newCustomerSegment.put("POSTCODE", "01234");
			newCustomerSegment.put("CITY", "Bedrock");
			newCustomerSegment.put("COUNTR", "US");
			newCustomerSegment.put("PHONE", "800-555-1212");
			newCustomerSegment.put("EMAIL", "fred@bedrock.com");
			newCustomerSegment.put("CUSTTYPE", "P");
			newCustomerSegment.put("DISCOUNT", "005");
			newCustomerSegment.put("LANGU", "E");

			// Fill in control info
			document.setMessageType("FLCUSTOMER_CREATEFROMDATA");
			document.setRecipientPartnerNumber("NPLCLNT002");
			document.setRecipientPartnerType("LS");
			document.setSenderPartnerNumber("JCOCLIENT");
			document.setSenderPartnerType("LS");
			
			documents.add(document);
		}
		
		// Save IDoc document
		file = new File("data/testIDocs.xml");
		IDocUtil.save(file, documents);
	}

}
