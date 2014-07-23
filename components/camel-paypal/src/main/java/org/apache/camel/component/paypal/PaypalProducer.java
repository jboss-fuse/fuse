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

import com.paypal.api.payments.Invoice;
import com.paypal.core.rest.PayPalResource;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.paypal.internal.PaypalApiName;
import org.apache.camel.component.paypal.internal.PaypalConstants;
import org.apache.camel.component.paypal.internal.PaypalPropertiesHelper;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.util.component.AbstractApiProducer;
import org.apache.camel.util.component.ApiMethod;
import org.apache.camel.util.component.ApiMethodHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * The Paypal producer.
 */
public class PaypalProducer extends AbstractApiProducer<PaypalApiName, PaypalConfiguration> {

    public PaypalProducer(PaypalEndpoint endpoint) {
        super(endpoint, PaypalPropertiesHelper.getHelper());
    }

    @Override
    protected Object doInvokeMethod(ApiMethod method, Map<String, Object> properties) throws RuntimeCamelException {
        synchronized (this) {
            try {
                return ApiMethodHelper.invokeMethod(endpoint.getApiProxy(method, properties), method, properties);
            } catch (Throwable t) {
                throw ObjectHelper.wrapRuntimeCamelException(t);
            }
        }
    }

}
