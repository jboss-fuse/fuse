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

/**
 * Constants for Paypal component.
 */
public interface PaypalConstants {

    // suffix for parameters when passed as exchange header properties
    String PROPERTY_PREFIX = "CamelPaypal.";

    // thread profile name for this component
    String THREAD_PROFILE_NAME = "CamelPaypal";

    String ACCESS_TOKEN = "accessToken";

    String API_CONTEXT = "apiContext";


}
