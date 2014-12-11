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
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by pantinor on 27/06/14.
 */
public class AbstractPaypalIntegrationTest extends CamelTestSupport {


    @Override
    public CamelContext createCamelContext() {
        CamelContext context = new DefaultCamelContext();

        PaypalComponent paypal = new PaypalComponent();

        PaypalConfiguration configuration = new PaypalConfiguration();

        Properties p = new Properties();
        try {
            p.load(AbstractPaypalIntegrationTest.class.getResourceAsStream("/paypal_api.properties"));
        } catch (IOException e) {
            log.warn("paypal_api.properties not available in the classpath, properties set programmatically", e);
        }
        configuration.setClientId(p.getProperty(PaypalConfiguration.CLIENT_ID));
        configuration.setClientSecret(p.getProperty(PaypalConfiguration.CLIENT_SECRET));
        configuration.setServiceEndpoint(p.getProperty(PaypalConfiguration.SERVICE_END_POINT));
        configuration.setHttpRetry(Integer.valueOf(p.getProperty(PaypalConfiguration.HTTP_RETRY)));

        paypal.setConfiguration(configuration);

        context.addComponent("paypal", paypal);

        return context;
    }


    @Override
    public boolean isCreateCamelContextPerClass() {
        // only create the context once for this class
        return true;
    }
}
