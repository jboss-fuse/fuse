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
import com.paypal.core.Constants;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.sdk.info.SDKVersionImpl;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.apache.camel.component.paypal.internal.PaypalApiName;
import org.apache.camel.component.paypal.internal.PaypalConstants;
import org.apache.camel.component.paypal.internal.PaypalPropertiesHelper;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.component.AbstractApiEndpoint;
import org.apache.camel.util.component.ApiMethod;
import org.apache.camel.util.component.ApiMethodPropertiesHelper;

import java.util.*;

/**
 * Represents a Paypal endpoint.
 */
@UriEndpoint(scheme = "paypal", consumerClass = PaypalConsumer.class, consumerPrefix = "consumer")
public class PaypalEndpoint extends AbstractApiEndpoint<PaypalApiName, PaypalConfiguration> {

    private static final List<String> inBodyInjectedNamesList;

    private Object apiProxy;
    private Properties properties;

    static {
        inBodyInjectedNamesList = Arrays.asList(new String[]{"authorization", "capture", "creditCard", "invoice", "payment", "refund", "sale"});
    }

    public PaypalEndpoint(String uri, PaypalComponent component,
                          PaypalApiName apiName, String methodName, PaypalConfiguration endpointConfiguration) {
        super(uri, component, apiName, methodName, PaypalApiCollection.getCollection().getHelper(apiName), endpointConfiguration);

    }

    public Producer createProducer() throws Exception {
        return new PaypalProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        // make sure inBody is not set for consumers
        if (inBody != null) {
            throw new IllegalArgumentException("Option inBody is not supported for consumer endpoint");
        }
        final PaypalConsumer consumer = new PaypalConsumer(this, processor);
        // also set consumer.* properties
        configureConsumer(consumer);
        return consumer;
    }

    @Override
    protected ApiMethodPropertiesHelper<PaypalConfiguration> getPropertiesHelper() {
        return PaypalPropertiesHelper.getHelper();
    }

    @Override
    protected void afterConfigureProperties() {
        configuration.validate();
        if (properties == null) {
            properties = buildProperties();
        }
    }

    private synchronized Properties buildProperties() {
        Properties p = new Properties();

        // Authentication Information
        p.setProperty(PaypalConfiguration.CLIENT_ID, configuration.getClientId());
        p.setProperty(PaypalConfiguration.CLIENT_SECRET, configuration.getClientSecret());
        p.setProperty(PaypalConfiguration.SERVICE_END_POINT, configuration.getServiceEndpoint());

        //Connection Information
        p.setProperty(PaypalConfiguration.HTTP_RETRY, String.valueOf(configuration.getHttpRetry()));
        p.setProperty(PaypalConfiguration.HTTP_CONNECTION_TIME_OUT, String.valueOf(configuration.getHttpConnectionTimeOut()));
        p.setProperty(PaypalConfiguration.HTTP_READ_TIME_OUT, String.valueOf(configuration.getHttpReadTimeOut()));
        p.setProperty(PaypalConfiguration.HTTP_MAX_CONNECTION, String.valueOf(configuration.getHttpMaxConnection()));

        // HTTP Proxy configuration

        p.setProperty(PaypalConfiguration.HTTP_USE_PROXY, String.valueOf(configuration.isHttpUseProxy()));
        if(ObjectHelper.isNotEmpty(configuration.getHttpProxyHost())) {
            p.setProperty(PaypalConfiguration.HTTP_PROXY_HOST, configuration.getHttpProxyHost());
        }
        p.setProperty(PaypalConfiguration.HTTP_PROXY_PORT, String.valueOf(configuration.getHttpProxyPort()));
        if(ObjectHelper.isNotEmpty(configuration.getHttpUserName())) {
            p.setProperty(PaypalConfiguration.HTTP_PROXY_USER_NAME, configuration.getHttpUserName());
        }
        if(ObjectHelper.isNotEmpty(configuration.getHttpProxyPassword())) {
            p.setProperty(PaypalConfiguration.HTTP_PROXY_PASSWORD, configuration.getHttpProxyPassword());
        }

        // Google App Engine
        p.setProperty(PaypalConfiguration.HTTP_GOOGLE_APP_ENGINE, String.valueOf(configuration.isHttpGoogleAppEngine()));

        return p;
    }

    protected APIContext requestAccessToken() {
        APIContext apiContext = null;
        String clientId = getComponent().getConfiguration().getClientId();
        String clientSecret = getComponent().getConfiguration().getClientSecret();
        try {
            // created each time since the returned access token has a fixed time to live
            String token = new OAuthTokenCredential(clientId, clientSecret, (Map) properties).getAccessToken();

            apiContext = new APIContext(token);
            apiContext.setHTTPHeaders(new HashMap<String, String>());
            apiContext.getHTTPHeaders().put(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
            apiContext.setSdkVersion(new SDKVersionImpl());
            return apiContext;
        } catch (PayPalRESTException e) {
            throw ObjectHelper.wrapRuntimeCamelException(e);
        }
    }

    @Override
    public Object getApiProxy(ApiMethod apiMethod, Map<String, Object> map) {
        final PaypalConfiguration componentConfiguration = getComponent().getConfiguration();

        Object header = null;
        switch (getApiName()) {
            case AUTHORIZATION:
                Authorization tmpAuthorization = null;
                Payment.initConfig(properties);

                header = map.get("authorization");
                if (header != null) {
                    tmpAuthorization = (Authorization) header;
                } else {
                    tmpAuthorization = new Authorization();
                }

                apiProxy = tmpAuthorization;
                break;
            case CAPTURE:
                Capture tmpCapture = null;
                Capture.initConfig(properties);

                header = map.get("capture");
                if (header != null) {
                    tmpCapture = (Capture) header;
                } else {
                    tmpCapture = new Capture();
                }

                apiProxy = tmpCapture;
                break;
            case CREDITCARD:
                CreditCard tmpCreditCard = null;
                CreditCard.initConfig(properties);

                header = map.get("creditCard");
                if (header != null) {
                    tmpCreditCard = (CreditCard) header;
                } else {
                    tmpCreditCard = new CreditCard();
                }

                apiProxy = tmpCreditCard;
                break;
            case INVOICE:
                Invoice tmpInvoice = null;
                Invoice.initConfig(properties);

                header = map.get("invoice");
                if (header != null) {
                    tmpInvoice = (Invoice) header;
                } else {
                    tmpInvoice = new Invoice();
                }

                apiProxy = tmpInvoice;
                break;

            case PAYMENT:
                Payment tmpPayment = null;
                Payment.initConfig(properties);

                header = map.get("payment");
                if (header != null) {
                    tmpPayment = (Payment) header;
                } else {
                    tmpPayment = new Payment();
                }

                apiProxy = tmpPayment;
                break;

            case REFUND:
                Refund tmpRefund = null;
                Refund.initConfig(properties);

                header = map.get("refund");
                if (header != null) {
                    tmpRefund = (Refund) header;
                } else {
                    tmpRefund = new Refund();
                }

                apiProxy = tmpRefund;
                break;

            case SALE:
                Sale tmpSale = null;
                Sale.initConfig(properties);

                header = map.get("sale");
                if (header != null) {
                    tmpSale = (Sale) header;
                } else {
                    tmpSale = new Sale();
                }

                apiProxy = tmpSale;
                break;


            default:
                throw new IllegalArgumentException("Invalid API name " + apiName);
        }

        return apiProxy;
    }

    @Override
    protected String getThreadProfileName() {
        return PaypalConstants.THREAD_PROFILE_NAME;
    }


    @Override
    public void interceptProperties(Map<String, Object> properties) {
        super.interceptProperties(properties);

        //request Access Token for each invocation since an AccessToken has a 8hs validity and there is no proper way to check if it's expired or not.
        properties.put(PaypalConstants.API_CONTEXT, requestAccessToken());
    }

    @Override
    public void interceptPropertyNames(Set<String> propertyNames) {
        super.interceptPropertyNames(propertyNames);
        propertyNames.add(PaypalConstants.API_CONTEXT);
        if (inBody != null) {
            propertyNames.removeAll(inBodyInjectedNamesList);
        }
    }

    @Override
    public PaypalComponent getComponent() {
        return (PaypalComponent) super.getComponent();
    }

}
