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
package org.fusesource.camel.component.sap.integration;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.fusesource.camel.component.sap.SapTransactionalIDocDestinationEndpoint;
import org.fusesource.camel.component.sap.model.idoc.Document;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Integration test cases for sending IDoc documents.
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class ITestSendIDoc extends CamelSpringTestSupport {
	
	@Test
	public void testSendIDoc() throws Exception {
		
        SapTransactionalIDocDestinationEndpoint endpoint = (SapTransactionalIDocDestinationEndpoint) context().getEndpoint("sap-idoc-destination:nplDest:FLCUSTOMER_CREATEFROMDATA01");
        
        // Create document and initialize transmission parameters
        Document document = endpoint.createDocument();
		document.setMessageType("FLCUSTOMER_CREATEFROMDATA");
		document.setRecipientPartnerNumber("NPLCLNT002");
		document.setRecipientPartnerType("LS");
		document.setSenderPartnerNumber("JCOCLIENT");
		document.setSenderPartnerType("LS");
		
		// Retrieve document segments.
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
		
        
        template.sendBody("direct:out", document);
        
}

	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct:out")
                  .to("sap-idoc-destination:nplDest:FLCUSTOMER_CREATEFROMDATA01");
            }
        };
    }

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"org/fusesource/camel/component/sap/integration/ITestIDocConfig.xml");
	}

}
