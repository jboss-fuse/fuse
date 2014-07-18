/**
 * Copyright 2014 Red Hat, Inc.
 * 
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap;

import org.apache.camel.ExchangePattern;
import org.apache.camel.impl.DefaultEndpoint;
import org.fusesource.camel.component.sap.model.idoc.Document;

public abstract class IDocEndpoint extends DefaultEndpoint {

	protected String idocType;
	protected String idocTypeExtension;
	protected String systemRelease;
	protected String applicationRelease;
	
	protected ExchangePattern mep = ExchangePattern.InOnly;
	
	public IDocEndpoint() {
	}

	public IDocEndpoint(String uri, SAPComponent component) {
		super(uri,component);
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getIdocType() {
		return idocType;
	}

	public void setIdocType(String idocType) {
		this.idocType = idocType;
	}

	public String getIdocTypeExtension() {
		return idocTypeExtension;
	}

	public void setIdocTypeExtension(String idocTypeExtension) {
		this.idocTypeExtension = idocTypeExtension;
	}

	public String getSystemRelease() {
		return systemRelease;
	}

	public void setSystemRelease(String systemRelease) {
		this.systemRelease = systemRelease;
	}

	public String getApplicationRelease() {
		return applicationRelease;
	}

	public void setApplicationRelease(String applicationRelease) {
		this.applicationRelease = applicationRelease;
	}

	public ExchangePattern getMep() {
		return mep;
	}

	public void setMep(ExchangePattern mep) {
		this.mep = mep;
	}
	
	public abstract Document getDocument() throws Exception;

}
