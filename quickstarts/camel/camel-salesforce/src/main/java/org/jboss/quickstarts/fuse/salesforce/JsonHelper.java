/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.jboss.quickstarts.fuse.salesforce;

import java.io.IOException;

import org.apache.camel.salesforce.dto.Cheese__c;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * Helper class to convert JSON data files to SObjects.
 */
public final class JsonHelper {

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private JsonHelper() {
    }

    public static Cheese__c readFile(String contents) throws IOException {
        return (Cheese__c) MAPPER.readValue(contents, Cheese__c.class);
    }

    public static String writeFile(Cheese__c cheese) throws IOException {
        return MAPPER.writeValueAsString(cheese);
    }
}