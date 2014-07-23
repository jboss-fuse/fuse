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
import org.apache.camel.component.paypal.internal.CreditCardApiMethod;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for com.paypal.api.payments.CreditCard APIs.
 * TODO Move the file to src/test/java, populate parameter values, and remove @Ignore annotations.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class CreditCardIntegrationTest extends AbstractPaypalIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardIntegrationTest.class);
    private static final String PATH_PREFIX = PaypalApiCollection.getCollection().getApiName(CreditCardApiMethod.class).getName();

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testCreate() throws Exception {
//        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
//        com.paypal.api.payments.CreditCard result = (com.paypal.api.payments.CreditCard) template().requestBody("direct://CREATE", (com.paypal.core.rest.APIContext) null);
//
//        LOG.debug("create :" + result);
//    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testDelete() throws Exception {
//        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
//        template().requestBody("direct://DELETE", (com.paypal.core.rest.APIContext) null);
//    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testGet() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is String
//        headers.put("CamelPaypal.creditCardId", null);
//        com.paypal.api.payments.CreditCard result = (com.paypal.api.payments.CreditCard) template().requestBodyAndHeaders("direct://GET", null, headers);
//
//        LOG.debug("get :" + result);
//    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testList() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is java.util.Map
//        headers.put("CamelPaypal.containerMap", null);
//        com.paypal.api.payments.CreditCardHistory result = (com.paypal.api.payments.CreditCardHistory) template().requestBodyAndHeaders("direct://LIST", null, headers);
//
//        LOG.debug("list :" + result);
//    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testUpdate() throws Exception {
//        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
//        com.paypal.api.payments.CreditCard result = (com.paypal.api.payments.CreditCard) template().requestBody("direct://UPDATE", (com.paypal.core.rest.APIContext) null);
//
//        LOG.debug("update :" + result);
//    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for create
                from("direct://CREATE")
                        .to("paypal://" + PATH_PREFIX + "/create");

                // test route for delete
                from("direct://DELETE")
                        .to("paypal://" + PATH_PREFIX + "/delete");

                // test route for get
                from("direct://GET")
                        .to("paypal://" + PATH_PREFIX + "/get");

                // test route for list
                from("direct://LIST")
                        .to("paypal://" + PATH_PREFIX + "/list");

                // test route for update
                from("direct://UPDATE")
                        .to("paypal://" + PATH_PREFIX + "/update");

            }
        };
    }

}
