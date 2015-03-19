/**
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import org.apache.camel.component.salesforce.api.MultiSelectPicklistConverter;
import org.apache.camel.component.salesforce.api.MultiSelectPicklistDeserializer;
import org.apache.camel.component.salesforce.api.MultiSelectPicklistSerializer;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Salesforce DTO for SObject Cheese__c
 */
@XStreamAlias("Cheese__c")
public class Cheese__c extends AbstractSObjectBase {

    // Country__c
    private String Country__c;

    @JsonProperty("Country__c")
    public String getCountry__c() {
        return this.Country__c;
    }

    @JsonProperty("Country__c")
    public void setCountry__c(String Country__c) {
        this.Country__c = Country__c;
    }

    // Description__c
    private String Description__c;

    @JsonProperty("Description__c")
    public String getDescription__c() {
        return this.Description__c;
    }

    @JsonProperty("Description__c")
    public void setDescription__c(String Description__c) {
        this.Description__c = Description__c;
    }

    // Milk__c
    @XStreamConverter(MultiSelectPicklistConverter.class)
    private org.apache.camel.salesforce.dto.MilkEnum[] Milk__c;

    @JsonProperty("Milk__c")
    @JsonSerialize(using = MultiSelectPicklistSerializer.class)
    public MilkEnum[] getMilk__c() {
        return this.Milk__c;
    }

    @JsonProperty("Milk__c")
    @JsonDeserialize(using = MultiSelectPicklistDeserializer.class)
    public void setMilk__c(MilkEnum[] Milk__c) {
        this.Milk__c = Milk__c;
    }

}
