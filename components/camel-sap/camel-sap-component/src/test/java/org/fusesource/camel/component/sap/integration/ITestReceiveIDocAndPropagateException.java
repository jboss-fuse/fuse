package org.fusesource.camel.component.sap.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.fusesource.camel.component.sap.model.idoc.DocumentList;
import org.fusesource.camel.component.sap.util.IDocUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Integration test cases for receiving IDoc doucments
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class ITestReceiveIDocAndPropagateException extends CamelSpringTestSupport {

	@Test
	public void test() throws Exception {
		while(true) {
			DocumentList documentList = (DocumentList) consumer.receiveBody("direct:out");
			IDocUtil.print(documentList);
		}
	}

	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("sap-idoclist-server:nplServer:FLCUSTOMER_CREATEFROMDATA01?propagateExceptions=true").to("bean:throwUp").to("direct:out");
            }
        };
    }

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"org/fusesource/camel/component/sap/integration/ITestIDocConfig.xml");
	}

}
