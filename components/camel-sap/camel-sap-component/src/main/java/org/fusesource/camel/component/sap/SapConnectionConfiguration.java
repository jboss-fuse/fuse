package org.fusesource.camel.component.sap;

import java.util.Map;

import org.fusesource.camel.component.sap.model.rfc.DestinationData;
import org.fusesource.camel.component.sap.model.rfc.DestinationDataStore;
import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.model.rfc.RepositoryDataStore;
import org.fusesource.camel.component.sap.model.rfc.RfcFactory;
import org.fusesource.camel.component.sap.model.rfc.ServerData;
import org.fusesource.camel.component.sap.model.rfc.ServerDataStore;
import org.fusesource.camel.component.sap.util.ComponentDestinationDataProvider;
import org.fusesource.camel.component.sap.util.ComponentRepositoryDataProvider;
import org.fusesource.camel.component.sap.util.ComponentServerDataProvider;

public class SapConnectionConfiguration {

	protected final DestinationDataStore destinationDataStore = RfcFactory.eINSTANCE.createDestinationDataStore();

	protected final ServerDataStore serverDataStore = RfcFactory.eINSTANCE.createServerDataStore();
	
	protected final RepositoryDataStore repositoryDataStore = RfcFactory.eINSTANCE.createRepositoryDataStore();

	public SapConnectionConfiguration() {
	   	ComponentDestinationDataProvider.INSTANCE.addDestinationDataStore(destinationDataStore);
    	ComponentServerDataProvider.INSTANCE.addServerDataStore(serverDataStore);
    	ComponentRepositoryDataProvider.INSTANCE.addRepositoryDataStore(repositoryDataStore);
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
    
    public Map<String, RepositoryData> getRepositoryDataStore() {
    	return repositoryDataStore.getEntries().map();
    }
    
    public void setRepositoryDataStore( Map<String, RepositoryData> repositoryDataEntries) {
    	repositoryDataStore.getEntries().clear();
    	repositoryDataStore.getEntries().putAll(repositoryDataEntries);
    }

}
