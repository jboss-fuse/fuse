package org.fusesource.camel.component.sap.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.fusesource.camel.component.sap.model.rfc.Request;
import org.fusesource.camel.component.sap.util.Util;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Integration test cases for server RFC calls.
 * 
 * @author William Collins <punkhornsw@gmail.com>
 *
 */
public class ITestSynchronousServerCall extends CamelSpringTestSupport {

	@Test
	public void test() throws Exception {
		while(true) {
			Request request = (Request) consumer.receiveBody("direct:out");
			Util.print(request);
		}
	}

	@Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("sap-srfc-server:nplServer:PARAM_TEST").to("direct:out");
            }
        };
    }

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"org/fusesource/camel/component/sap/integration/ITestCallConfig.xml");
	}

}
