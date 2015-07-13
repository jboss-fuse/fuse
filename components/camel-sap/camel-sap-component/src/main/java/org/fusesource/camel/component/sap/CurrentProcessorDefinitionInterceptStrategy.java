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
