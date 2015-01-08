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
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class EsbExampleFeaturesTest extends EsbTestSupport {

    @Test
    public void testCbr() throws Exception {
        installQuickstartBundle("beginner-camel-cbr");
    }

    @Test
    public void testEip() throws Exception {
        installQuickstartBundle("beginner-camel-eips");
    }

    @Test
    public void testErrors() throws Exception {
        installQuickstartBundle("beginner-camel-errorhandler");
    }

    @Ignore("See ENTESB-2124")
    @Test
    public void testJms() throws Exception {
        executeCommand("shell:exec cp quickstarts/jms/src/main/resources/etc/org.fusesource.mq.fabric.cf-default.cfg etc/");
        executeCommand("features:addurl mvn:org.jboss.quickstarts.fuse/jms/" + getEsbVersion() + "/xml/features");
        installUninstallCommand("quickstart-jms");
    }

    @Test
    public void testRest() throws Exception {
        installQuickstartBundle("cxf-rest");
    }

    @Test
    public void testSecureRest() throws Exception {
        installQuickstartBundle("cxf-secure-rest");
    }

    @Test
    public void testSoap() throws Exception {
        installQuickstartBundle("cxf-soap");
    }

    @Ignore("ENTESB-1831")
    @Test
    public void testSecureSoap() throws Exception {
        installAndCheckFeature("cxf-ws-security");
        installQuickstartBundle("cxf-secure-soap");
    }

    @Configuration
    public Option[] config() {
        return new Option[] {
                new DefaultCompositeOption(esbDistributionConfiguration("jboss-fuse-medium")),
                mavenBundle("org.apache.geronimo.specs", "geronimo-ws-metadata_2.0_spec")
        };
    }

}
