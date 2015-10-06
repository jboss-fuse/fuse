/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
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
public class ITestSynchronousStatefulServerCall extends CamelSpringTestSupport {

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
                from("sap-srfc-server:nplServer:PARAM_TEST").to("bean:counter").to("direct:out");
            }
        };
    }

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"org/fusesource/camel/component/sap/integration/ITestCallConfig.xml");
	}

}
