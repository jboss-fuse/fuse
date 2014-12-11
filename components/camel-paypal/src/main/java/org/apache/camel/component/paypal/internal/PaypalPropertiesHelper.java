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
package org.apache.camel.component.paypal.internal;

import org.apache.camel.component.paypal.PaypalConfiguration;
import org.apache.camel.util.component.ApiMethodPropertiesHelper;

/**
 * Singleton {@link ApiMethodPropertiesHelper} for Paypal component.
 */
public final class PaypalPropertiesHelper extends ApiMethodPropertiesHelper<PaypalConfiguration> {

    private static PaypalPropertiesHelper helper;

    private PaypalPropertiesHelper() {
        super(PaypalConfiguration.class, PaypalConstants.PROPERTY_PREFIX);
    }

    public static synchronized PaypalPropertiesHelper getHelper() {
        if (helper == null) {
            helper = new PaypalPropertiesHelper();
        }
        return helper;
    }
}
