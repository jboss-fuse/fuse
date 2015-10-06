/**
 *  Copyright 2005-2015 Red Hat, Inc.
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
package org.fusesource.camel.component.sap;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.powermock.api.mockito.PowerMockito;

import com.sap.conn.idoc.jco.JCoIDoc;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServerFactory;

public abstract class JCoTestSupport extends CamelSpringTestSupport {

	static  {

		PowerMockito.mockStatic(Environment.class);
		PowerMockito.mockStatic(JCoIDoc.class);
		PowerMockito.mockStatic(JCoDestinationManager.class);
		PowerMockito.mockStatic(JCoServerFactory.class);

	}


}
