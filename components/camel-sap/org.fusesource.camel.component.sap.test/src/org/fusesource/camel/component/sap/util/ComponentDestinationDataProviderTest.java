package org.fusesource.camel.component.sap.util;

import java.util.Properties;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

import static org.junit.Assert.assertEquals;

public class ComponentDestinationDataProviderTest {

	protected DestinationDataStore destinationDataStore;
	
	protected DestinationData destinationData;

	@Before
	public void setUp() throws Exception {
		
		destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();
		
		ComponentDestinationDataProvider.INSTANCE.setDestinationDataEventListener(new DestinationDataEventListener() {

			@Override
			public void deleted(String destinationName) {
				System.out.println("Destination '" + destinationName + "' deleted");
			}

			@Override
			public void updated(String destinationName) {
				System.out.println("Destination '" + destinationName + "' updated");
			}
			
		});
		
		destinationData = RfcFactory.eINSTANCE.createDestinationData();
		destinationData.setAshost("nplhost");
		destinationData.setSysnr("42");
		destinationData.setClient("001");
		destinationData.setUser("developer");
		destinationData.setPasswd("ch4ngeme");
		destinationData.setLang("en");
		
		destinationDataStore.getEntries().put("TestDestination", destinationData);

		ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
	
}

	@Test
	public void testComponentDestinationDataProvider() {
		Properties destinationProps = ComponentDestinationDataProvider.INSTANCE.getDestinationProperties("TestDestination");
		assertEquals("Unexpected JCO_ASHOST returned", "nplhost", destinationProps.get(DestinationDataProvider.JCO_ASHOST));
		assertEquals("Unexpected JCO_SYSNR returned", "42", destinationProps.get(DestinationDataProvider.JCO_SYSNR));
		assertEquals("Unexpected JCO_CLIENT returned", "001", destinationProps.get(DestinationDataProvider.JCO_CLIENT));
		assertEquals("Unexpected JCO_USER returned", "developer", destinationProps.get(DestinationDataProvider.JCO_USER));
		assertEquals("Unexpected JCO_PASSWD returned", "ch4ngeme", destinationProps.get(DestinationDataProvider.JCO_PASSWD));
		assertEquals("Unexpected JCO_LANG returned", "en", destinationProps.get(DestinationDataProvider.JCO_LANG));
		
		destinationData.setAshost("tsohlpn");
		destinationData.setSysnr("24");
		destinationData.setClient("100");
		destinationData.setUser("repoleved");
		destinationData.setPasswd("emegn4hc");
		destinationData.setLang("ne");
//		
//		destinationProps = ComponentDestinationDataProvider.INSTANCE.getDestinationProperties("TestDestination");
//		assertEquals("Unexpected JCO_ASHOST returned", "tsohlpn", destinationProps.get(DestinationDataProvider.JCO_ASHOST));
//		assertEquals("Unexpected JCO_SYSNR returned", "24", destinationProps.get(DestinationDataProvider.JCO_SYSNR));
//		assertEquals("Unexpected JCO_CLIENT returned", "100", destinationProps.get(DestinationDataProvider.JCO_CLIENT));
//		assertEquals("Unexpected JCO_USER returned", "repoleved", destinationProps.get(DestinationDataProvider.JCO_USER));
//		assertEquals("Unexpected JCO_PASSWD returned", "emegn4hc", destinationProps.get(DestinationDataProvider.JCO_PASSWD));
//		assertEquals("Unexpected JCO_LANG returned", "ne", destinationProps.get(DestinationDataProvider.JCO_LANG));

	}
	
	public class DestinationDataListener extends EContentAdapter {

		private String destinationName;

		public DestinationDataListener(String destinationName) {
			this.destinationName = destinationName;
		}

		@Override
		public void notifyChanged(Notification msg) {
			switch (msg.getEventType()) {
			case Notification.SET:
			case Notification.ADD:
			case Notification.ADD_MANY: {
				System.out.println("Destination '" + destinationName + "' property '" + msg.getFeature().toString() + "'  updated: " + msg.getNewValue());
				break;
			}
			case Notification.REMOVE:
			case Notification.REMOVE_MANY: {
				System.out.println("Destination '" + destinationName + "' property '" + msg.getFeature().toString() + "'  removed: " + msg.getOldValue());
				System.out.println("Destination '" + destinationName + "' removed: " + msg.getNewValue());
				break;
			}
			}
			super.notifyChanged(msg);
		}
	}

//	@Test
	public void testDestinationDataListener() {
		destinationData.eAdapters().add(new DestinationDataListener("TestDestination"));
		
		destinationData.setAshost("tsohlpn");
		destinationData.setSysnr("24");
		destinationData.setClient("100");
		destinationData.setUser("repoleved");
		destinationData.setPasswd("emegn4hc");
		destinationData.setLang("ne");
		
	}

}
