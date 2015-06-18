/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.camel.component.sap;

import org.apache.camel.ComponentConfiguration;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.Ignore;

public class SapTransactionalIDocListServerComponentConfigurationAndDocumentationTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        return false;
    }

    @Ignore("ENTESB-3457")
    @Test
    public void testComponentConfiguration() throws Exception {
        SapTransactionalIDocListServerComponent comp = context.getComponent("sap-idoclist-server", SapTransactionalIDocListServerComponent.class);
        EndpointConfiguration conf = comp.createConfiguration("sap-idoclist-server:serverName:rfcName?stateful=true&propagateExceptions=false");

        assertEquals("true", conf.getParameter("stateful"));
        assertEquals("false", conf.getParameter("propagateExceptions"));

        ComponentConfiguration compConf = comp.createComponentConfiguration();
        String json = compConf.createParameterJsonSchema();
        assertNotNull(json);

        assertTrue(json.contains("\"server\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the server this endpoint receives an IDoc from\" }"));
        assertTrue(json.contains("\"idocType\": { \"kind\": \"path\", \"required\": \"true\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the Basic IDoc Type of an IDoc consumed by this endpoint\" }"));
        assertTrue(json.contains("\"idocTypeExtension\": { \"kind\": \"path\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the IDoc Type Extension if any of an IDoc consumed by this endpoint\" }"));
        assertTrue(json.contains("\"systemRelease\": { \"kind\": \"path\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifies the associated SAP Basis Release if any of an IDoc consumed by this endpoint\" }"));
        assertTrue(json.contains("\"applicationRelease\": { \"kind\": \"path\", \"type\": \"string\", \"javaType\": \"java.lang.String\", \"deprecated\": \"false\", \"description\": \"Specifes the associated Application Release if any of an IDoc consumed by this endpoint\" }"));
        assertTrue(json.contains("\"propagateExceptions\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will propagate exceptions back to the caller in SAP instead of the exchange's exception handler\" }"));
        assertTrue(json.contains("\"stateful\": { \"kind\": \"parameter\", \"type\": \"boolean\", \"javaType\": \"boolean\", \"deprecated\": \"false\", \"defaultValue\": \"false\", \"description\": \"When true specifies that this endpoint will initiate an SAP stateful session\" }"));
    }

}
