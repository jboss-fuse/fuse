/**
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package org.apache.camel.component.paypal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paypal.internal.CaptureApiMethod;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for com.paypal.api.payments.Capture APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class CaptureIntegrationTest extends AbstractPaypalIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaptureIntegrationTest.class);
    private static final String PATH_PREFIX = PaypalApiCollection.getCollection().getApiName(CaptureApiMethod.class).getName();

    @Test
    public void testGet() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is String
        headers.put("CamelPaypal.captureId", "72C31133R8046845G");
        com.paypal.api.payments.Capture result = (com.paypal.api.payments.Capture) template().requestBodyAndHeaders("direct://GET", null, headers);

        LOG.debug("get :" + result);

        assertNotNull(result);
    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testRefund() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is com.paypal.api.payments.Refund
//        headers.put("CamelPaypal.refund", null);
//        com.paypal.api.payments.Refund result = (com.paypal.api.payments.Refund) template().requestBodyAndHeaders("direct://REFUND", null, headers);
//
//        LOG.debug("refund :" + result);
//    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for get
                from("direct://GET")
                        .to("paypal://" + PATH_PREFIX + "/get");

                // test route for refund
                from("direct://REFUND")
                        .to("paypal://" + PATH_PREFIX + "/refund");

            }
        };
    }

}
