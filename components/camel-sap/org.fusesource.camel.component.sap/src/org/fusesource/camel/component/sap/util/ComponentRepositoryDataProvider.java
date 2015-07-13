/**
 * Copyright 2013 Red Hat, Inc.
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
package org.fusesource.camel.component.sap.util;

import java.util.HashSet;
import java.util.Set;

import org.fusesource.camel.component.sap.model.rfc.RepositoryData;
import org.fusesource.camel.component.sap.model.rfc.RepositoryDataStore;

public class ComponentRepositoryDataProvider  {

	public static final ComponentRepositoryDataProvider INSTANCE = new ComponentRepositoryDataProvider();

	Set<RepositoryDataStore> stores = new HashSet<RepositoryDataStore>();

	private ComponentRepositoryDataProvider() {
	}

	/**
	 * @param store
	 */
	public void addRepositoryDataStore(RepositoryDataStore store) {

		// Remember store
		stores.add(store);

	}

	public void removeRepositoryDataStore(
			RepositoryDataStore store) {

		// Forget store
		stores.remove(store);

	}

	public RepositoryData getRepositoryData(String repositoryName) {
		for (RepositoryDataStore store : stores) {
			RepositoryData repositorynData = store.getEntries().get(repositoryName);
			if (repositorynData != null) {
				return repositorynData;
			}
		}
		return null;
	}

}
