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

import org.apache.camel.AsyncCallback;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinitionHelper;
import org.apache.camel.processor.DelegateAsyncProcessor;
import org.apache.camel.spi.InterceptStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentProcessorDefinitionInterceptStrategy implements InterceptStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(CurrentProcessorDefinitionInterceptStrategy.class);

	public static final String CURRENT_PROCESSOR_DEFINITION = "org.fusesource.camel.component.sap.CurrentProcessorDefinition";

	@Override
	public Processor wrapProcessorInInterceptors(final CamelContext context, final ProcessorDefinition<?> definition, final Processor target, final Processor nextTarget)
			throws Exception {
		
        RouteDefinitionHelper.forceAssignIds(context, definition);

		return new DelegateAsyncProcessor(target) {
			@Override
			public boolean process(final Exchange exchange, final AsyncCallback callback) {
				LOG.debug("Setting current processor defintion '" + definition.getId() + "'");
				exchange.setProperty(CURRENT_PROCESSOR_DEFINITION, definition);
				return processor.process(exchange, callback);
			}
		};
		
	}

}
