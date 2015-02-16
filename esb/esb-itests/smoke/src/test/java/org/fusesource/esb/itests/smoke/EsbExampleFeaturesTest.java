/*
 * Copyright (C) FuseSource, Inc.
 *   http://fusesource.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.fusesource.esb.itests.smoke;

import org.fusesource.esb.itests.pax.exam.karaf.EsbTestSupport;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class EsbExampleFeaturesTest extends EsbTestSupport {
    protected static final Logger LOG = LoggerFactory.getLogger(EsbExampleFeaturesTest.class);

    @Ignore("ENTESB-2429")
    @Test
    public void testCamelBox() throws Exception {
        installQuickstartBundle("camel-box");
    }

    @Ignore("ENTESB-2429")
    @Test
    public void testCamelLinkedIn() throws Exception {
        installQuickstartBundle("camel-linkedin");
    }

    @Ignore("ENTESB-2429")
    @Test
    public void testCamelOdata() throws Exception {
	installQuickstartBundle("camel-odata");
    }

    @Ignore("ENTESB-2429")
    @Test
    public void testCamelSalesForce() throws Exception {
	installQuickstartBundle("camel-salesforce");
    }

    @Ignore("ENTESB-2124")
    @Test
    public void testCamelSap() throws Exception {
        installQuickstartBundle("camel-sap");
    }

    @Configuration
    public Option[] config() {
        return new Option[] {
                new DefaultCompositeOption(esbDistributionConfiguration("jboss-fuse-full")),
                mavenBundle("org.apache.geronimo.specs", "geronimo-ws-metadata_2.0_spec")
        };
    }

}
