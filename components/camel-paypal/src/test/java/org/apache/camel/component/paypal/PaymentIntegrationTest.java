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
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paypal.internal.PaymentApiMethod;
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
 * Test class for com.paypal.api.payments.Payment APIs.
 * TODO Move the file to src/test/java, populate parameter values, and remove @Ignore annotations.
 * The class source won't be generated again if the generator MOJO finds it under src/test/java.
 */
public class PaymentIntegrationTest extends AbstractPaypalIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentIntegrationTest.class);
    private static final String PATH_PREFIX = PaypalApiCollection.getCollection().getApiName(PaymentApiMethod.class).getName();


    @Test
    public void testCreate() throws Exception {
        Payment payment = getTestPayment();

        com.paypal.api.payments.Payment result = (com.paypal.api.payments.Payment) template().requestBody("direct://CREATE_IN_BODY", payment);

        LOG.debug("create :" + result);

        assertNotNull("PaymentId is not set",result.getId());


        Map<String,Object> headers = new HashMap();
        headers.put("CamelPaypal.payment", getTestPayment());
        result = (com.paypal.api.payments.Payment) template().requestBodyAndHeaders("direct://CREATE", null, headers);

        LOG.debug("create :" + result);
        assertNotNull("PaymentId is not set",result.getId());


    }


    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testExecute() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is com.paypal.api.payments.PaymentExecution
//        headers.put("CamelPaypal.paymentExecution", null);
//        com.paypal.api.payments.Payment result = (com.paypal.api.payments.Payment) template().requestBodyAndHeaders("direct://EXECUTE", null, headers);
//
//        LOG.debug("execute :" + result);
//    }


    @Test
    public void testGet() throws Exception {
        final Map<String, Object> headers = new HashMap<String, Object>();
        // parameter type is String
        final String id = "PAY-78055776MC9174139KOZHYDA";
        headers.put("CamelPaypal.paymentId", id);
        com.paypal.api.payments.Payment result = (com.paypal.api.payments.Payment) template().requestBodyAndHeaders("direct://GET", null, headers);

        LOG.debug("get :" + result);

        assertNotNull("PaymentId is not set",result.getId());
        assertEquals("Return wrong Payment id", id, result.getId());
    }

    // Placeholder for future test
//    @Ignore
//    @Test
//    public void testList() throws Exception {
//        final Map<String, Object> headers = new HashMap<String, Object>();
//        // parameter type is com.paypal.core.rest.APIContext
//        headers.put("CamelPaypal.apiContext", null);
//        // parameter type is java.util.Map
//        headers.put("CamelPaypal.containerMap", null);
//        com.paypal.api.payments.PaymentHistory result = (com.paypal.api.payments.PaymentHistory) template().requestBodyAndHeaders("direct://LIST", null, headers);
//
//        LOG.debug("list :" + result);
//    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                // test route for create
                from("direct://CREATE")
                        .to("paypal://" + PATH_PREFIX + "/create");

                from("direct://CREATE_IN_BODY")
                        .to("paypal://" + PATH_PREFIX + "/create?inBody=payment");

                // test route for execute
                from("direct://EXECUTE")
                        .to("paypal://" + PATH_PREFIX + "/execute");

                // test route for get
                from("direct://GET")
                        .to("paypal://" + PATH_PREFIX + "/get");

                // test route for list
                from("direct://LIST")
                        .to("paypal://" + PATH_PREFIX + "/list");

            }
        };
    }



    private Payment getTestPayment() {
        // using com.paypal.core.rest.APIContext message body for single parameter "apiContext"
        Address billingAddress = new Address();
        billingAddress.setLine1("52 N Main ST");
        billingAddress.setCity("Johnstown");
        billingAddress.setCountryCode("US");
        billingAddress.setPostalCode("43210");
        billingAddress.setState("OH");

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("4417119669820331");
        creditCard.setType("visa");
        creditCard.setExpireMonth(11);
        creditCard.setExpireYear(2018);
        creditCard.setCvv2("874");
        creditCard.setFirstName("Joe");
        creditCard.setLastName("Shopper");
        creditCard.setBillingAddress(billingAddress);

        Details amountDetails = new Details();
        amountDetails.setSubtotal("7.41");
        amountDetails.setTax("0.03");
        amountDetails.setShipping("0.03");

        Amount amount = new Amount();
        amount.setTotal("7.47");
        amount.setCurrency("USD");
        amount.setDetails(amountDetails);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description.");

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        FundingInstrument fundingInstrument = new FundingInstrument();
        fundingInstrument.setCreditCard(creditCard);

        List<FundingInstrument> fundingInstruments = new ArrayList<FundingInstrument>();
        fundingInstruments.add(fundingInstrument);

        Payer payer = new Payer();
        payer.setFundingInstruments(fundingInstruments);
        payer.setPaymentMethod("credit_card");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        return payment;
    }

}
