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

import com.paypal.api.payments.*;
import com.paypal.core.rest.APIContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paypal.internal.InvoiceApiMethod;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for com.paypal.api.payments.Invoice APIs.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class InvoiceIntegrationTest extends AbstractPaypalIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceIntegrationTest.class);
    private static final String PATH_PREFIX = PaypalApiCollection.getCollection().getApiName(InvoiceApiMethod.class).getName();


    @Test
    public void testCancel() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is com.paypal.api.payments.CancelNotification
        headers.put("CamelPaypal.cancelNotification", new CancelNotification());
        Invoice i = new Invoice().setId("INV2-J3DB-2WJH-J2ZT-P44T");
        headers.put("CamelPaypal.invoice", i);
        template().requestBodyAndHeaders("direct://CANCEL", null, headers);
    }


    @Test
    public void testCreate() throws Exception {

        Invoice i = new Invoice();
        //i.setId("paolo_test");
        MerchantInfo mi = new MerchantInfo();
        mi.setEmail("sonicaaaa-facilitator@gmail.com");
        mi.setFirstName("aaaaa");
        mi.setLastName("bbbb");
        mi.setBusinessName("cccc");
        mi.setPhone(new Phone().setCountryCode("001").setNationalNumber("9999999"));
        Address a = new Address();
        a.setLine1("a b c d e");
        a.setCity("Portland");
        a.setState("OR");
        a.setPostalCode("97217");
        a.setCountryCode("US");
        mi.setAddress(a);

        i.setMerchantInfo(mi);

        List<BillingInfo> bi = new ArrayList<BillingInfo>();
        bi.add(new BillingInfo().setEmail("sonicaaaa-client@gmail.com"));

        i.setBillingInfo(bi);

        List<InvoiceItem> it = new ArrayList<InvoiceItem>();
        it.add(new InvoiceItem().setName("Sutures").setQuantity(Float.valueOf("100")).setUnitPrice(new Currency().setCurrency("USD").setValue(String.valueOf(5))));
        i.setItems(it);

        i.setNote("bla bla bla");
        i.setPaymentTerm(new PaymentTerm().setTermType("NET_45"));

        ShippingInfo si = new ShippingInfo();
        Address ad = new Address();
        ad.setLine1("line1").setCity("Portland").setState("OR").setPostalCode("97216").setCountryCode("US");
        si.setFirstName("first").setLastName("last").setBusinessName("busi").setAddress(ad);
        i.setShippingInfo(si);

        com.paypal.api.payments.Invoice result = (com.paypal.api.payments.Invoice) template().requestBody("direct://CREATE_IN_BODY", i);

        LOG.debug("create :" + result);
        assertNotNull("InvoiceId not set", result.getId());


        // using String message body for single parameter "accessToken"
        Map<String, Object> headers = new HashMap();

        headers.put("CamelPaypal.invoice", i);

        result = (com.paypal.api.payments.Invoice) template().requestBodyAndHeaders("direct://CREATE", null, headers);

        LOG.debug("create :" + result);
        assertNotNull("InvoiceId not set", result.getId());

    }


    @Test
    public void testDelete() throws Exception {

        Map<String, Object> headers = new HashMap();
        Invoice i = new Invoice();
        i.setId("INV2-DUA4-5TD6-9JQX-RRHB");
        headers.put("CamelPaypal.invoice", i);
        template().requestBodyAndHeaders("direct://DELETE", (APIContext) null, headers);


    }

    @Test
    public void testGet() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();

        headers.put("CamelPaypal.invoiceId", "INV2-J3DB-2WJH-J2ZT-P44T");
        com.paypal.api.payments.Invoice result = (com.paypal.api.payments.Invoice) template().requestBodyAndHeaders("direct://GET", null, headers);

        LOG.debug("get :" + result);
        assertNotNull(result);
        assertIsInstanceOf(com.paypal.api.payments.Invoice.class, result);
        assertNotNull("InvoiceId is null", result.getId());

    }

    @Test
    public void testGetAll() throws Exception {
        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
        com.paypal.api.payments.Invoices result = (com.paypal.api.payments.Invoices) template().requestBody("direct://GETALL", (com.paypal.core.rest.APIContext) null);

        LOG.debug("getAll :" + result);
        assertNotNull(result);
        assertIsInstanceOf(com.paypal.api.payments.Invoices.class, result);
        assertNotNull("Invoices is null", result.getInvoices());
    }

    @Test
    public void testRemind() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is com.paypal.core.rest.APIContext
        headers.put("CamelPaypal.apiContext", null);
        // parameter type is com.paypal.api.payments.Notification
        com.paypal.api.payments.Notification n = new Notification();
        n.setNote("remind!");
        n.setSendToMerchant(Boolean.TRUE);
        n.setSubject("No, really");
        headers.put("CamelPaypal.notification", n);

        Invoice i = new Invoice();
        i.setId("INV2-AKFU-3XVW-6R3L-2QS2");
        headers.put("CamelPaypal.invoice", i);
        template().requestBodyAndHeaders("direct://REMIND", i, headers);
    }

    @Test
    public void testSearch() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is com.paypal.api.payments.Search
        com.paypal.api.payments.Search s = new com.paypal.api.payments.Search();
        s.setStartInvoiceDate("2010-05-10 PST");
        s.setEndInvoiceDate("2016-05-11 PST");
        s.setPage(1);
        s.setPageSize(20);
        s.setTotalCountRequired(Boolean.TRUE);

        headers.put("CamelPaypal.search", s);
        com.paypal.api.payments.Invoices result = (com.paypal.api.payments.Invoices) template().requestBodyAndHeaders("direct://SEARCH", null, headers);

        LOG.debug("search :" + result);

        assertNotNull("Empty result set", result);
    }


    @Test
    public void testSend() throws Exception {


        Map headers = new HashMap();
        Invoice i = new Invoice().setId("INV2-J3DB-2WJH-J2ZT-P44T");
        headers.put("CamelPaypal.invoiceId", i);

        Object o = template().requestBodyAndHeaders("direct://SEND", null, headers);

        LOG.debug("Sent: " + o);
    }


    @Test
    public void testUpdate() throws Exception {

        Map headers = new HashMap();
        headers.put("CamelPaypal.invoiceId", "INV2-875C-8LNM-QVJE-4A7M");
        com.paypal.api.payments.Invoice result = (com.paypal.api.payments.Invoice) template().requestBodyAndHeaders("direct://GET", null, headers);

        String logoUrl = "http://www.google.com/test.jpg";
        result.setLogoUrl(logoUrl);

        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
        result = (com.paypal.api.payments.Invoice) template().requestBody("direct://UPDATE", result);

        LOG.debug("update :" + result);

        assertNotNull(result);
        assertEquals("Logo url not set", logoUrl, result.getLogoUrl());
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for cancel
                from("direct://CANCEL")
                        .to("paypal://" + PATH_PREFIX + "/cancel");

                // test route for create
                from("direct://CREATE_IN_BODY")
                        .to("paypal://" + PATH_PREFIX + "/create?inBody=invoice");
                from("direct://CREATE")
                        .to("paypal://" + PATH_PREFIX + "/create");
//
//                // test route for delete
                from("direct://DELETE")
                        .to("paypal://" + PATH_PREFIX + "/delete");
//
//                // test route for get
                from("direct://GET")
                        .to("paypal://" + PATH_PREFIX + "/get");
//
//                // test route for getAll
                from("direct://GETALL")
                        .to("paypal://" + PATH_PREFIX + "/getAll");
//                // test route for remind
                from("direct://REMIND")
                        .to("paypal://" + PATH_PREFIX + "/remind?inBody=invoice");
//
//                // test route for search
                from("direct://SEARCH")
                        .to("paypal://" + PATH_PREFIX + "/search");

//                // test route for send
                from("direct://SEND")
                        .to("paypal://" + PATH_PREFIX + "/send");
//
//                // test route for update
                from("direct://UPDATE")
                        .to("paypal://" + PATH_PREFIX + "/update?inBody=invoice");

            }
        };
    }

}
