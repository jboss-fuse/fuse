package org.fusesource.camel.component.sap;

import java.util.Map;

import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.ServerData;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;
import org.fusesource.camel.component.sap.util.ComponentDestinationDataProvider;
import org.fusesource.camel.component.sap.util.ComponentServerDataProvider;

public class SapConnectionConfiguration {

	protected final DestinationDataStore destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();

	protected final ServerDataStore serverDataStore = RfcFactory.eINSTANCE.createServerDataStore();

	public SapConnectionConfiguration() {
	   	ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
    	ComponentServerDataProvider.INSTANCE.addServerDataStore(serverDataStore);
	}

    public Map<String, DestinationData> getDestinationDataStore() {
    	return destinationDataStore.getEntries().map();
    }

    public void setDestinationDataStore(Map<String, DestinationData> destinationDataEntries) {
    	destinationDataStore.getEntries().clear();
    	destinationDataStore.getEntries().putAll(destinationDataEntries);
    }
    
   public Map<String, ServerData> getServerDataStore() {
    	return serverDataStore.getEntries().map();
    }
    
    public void setServerDataStore(Map<String, ServerData> serverDataEntries) {
    	serverDataStore.getEntries().clear();
    	serverDataStore.getEntries().putAll(serverDataEntries);
    }

}
