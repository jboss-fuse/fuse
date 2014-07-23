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
import org.apache.camel.component.paypal.internal.AuthorizationApiMethod;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for com.paypal.api.payments.Authorization APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class AuthorizationIntegrationTest extends AbstractPaypalIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationIntegrationTest.class);
    private static final String PATH_PREFIX = PaypalApiCollection.getCollection().getApiName(AuthorizationApiMethod.class).getName();

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testCapture() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is com.paypal.api.payments.Capture
//        headers.put("CamelPaypal.capture", null);
//        com.paypal.api.payments.Capture result = (com.paypal.api.payments.Capture) template().requestBodyAndHeaders("direct://CAPTURE", null, headers);
//
//        LOG.debug("capture :" + result);
//    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testDoVoid() throws Exception {
//        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
//        com.paypal.api.payments.Authorization result = (com.paypal.api.payments.Authorization) template().requestBody("direct://DOVOID", (com.paypal.core.rest.APIContext) null);
//
//        LOG.debug("doVoid :" + result);
//    }

    @Test
    public void testGet() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is String
        final String id = "72C31133R8046845G";
        headers.put("CamelPaypal.authorizationId", id);
        com.paypal.api.payments.Authorization result = (com.paypal.api.payments.Authorization) template().requestBodyAndHeaders("direct://GET", null, headers);

        LOG.debug("get :" + result);

        assertNotNull(result.getId());
        assertEquals("Wrong id returned", id, result.getId());

    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testReauthorize() throws Exception {
//        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
//        com.paypal.api.payments.Authorization result = (com.paypal.api.payments.Authorization) template().requestBody("direct://REAUTHORIZE", (com.paypal.core.rest.APIContext) null);
//
//        LOG.debug("reauthorize :" + result);
//    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for capture
                from("direct://CAPTURE")
                        .to("paypal://" + PATH_PREFIX + "/capture");

                // test route for doVoid
                from("direct://DOVOID")
                        .to("paypal://" + PATH_PREFIX + "/doVoid");

                // test route for get
                from("direct://GET")
                        .to("paypal://" + PATH_PREFIX + "/get");

                // test route for reauthorize
                from("direct://REAUTHORIZE")
                        .to("paypal://" + PATH_PREFIX + "/reauthorize");

            }
        };
    }

}
