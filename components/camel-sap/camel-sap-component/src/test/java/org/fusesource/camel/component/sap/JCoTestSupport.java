package org.fusesource.camel.component.sap;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.powermock.api.mockito.PowerMockito;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;

public abstract class JCoTestSupport extends CamelSpringTestSupport {

	static  {

		PowerMockito.mockStatic(Environment.class);
		PowerMockito.mockStatic(JCoIDoc.class);
		PowerMockito.mockStatic(JCoDestinationManager.class);
		
	}


}
