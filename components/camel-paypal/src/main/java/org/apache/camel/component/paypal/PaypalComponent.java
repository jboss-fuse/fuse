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

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.paypal.internal.PaypalApiCollection;
import org.apache.camel.component.paypal.internal.PaypalApiName;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.component.AbstractApiComponent;

/**
 * Represents the component that manages {@link PaypalEndpoint}.
 */
@UriEndpoint(scheme = "paypal", consumerClass = PaypalConsumer.class, consumerPrefix = "consumer")
public class PaypalComponent extends AbstractApiComponent<PaypalApiName, PaypalConfiguration, PaypalApiCollection> {


    public PaypalComponent() {
        super(PaypalEndpoint.class, PaypalApiName.class, PaypalApiCollection.getCollection());
    }

    public PaypalComponent(CamelContext context) {
        super(context, PaypalEndpoint.class, PaypalApiName.class, PaypalApiCollection.getCollection());
    }

    public static void createClient(PaypalConfiguration configuration) {
    }

    @Override
    protected PaypalApiName getApiName(String apiNameStr) throws IllegalArgumentException {
        return PaypalApiName.fromValue(apiNameStr);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String methodName, PaypalApiName apiName,
                                      PaypalConfiguration endpointConfiguration) {
        return new PaypalEndpoint(uri, this, apiName, methodName, endpointConfiguration);
    }
}
