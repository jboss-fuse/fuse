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

package org.fusesource.esb.itests.basic;

import java.util.HashSet;
import java.util.Set;
import org.apache.karaf.jaas.boot.principal.RolePrincipal;
import org.fusesource.esb.itests.pax.exam.karaf.EsbTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;

import static org.junit.Assert.assertEquals;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class EsbBootTest extends EsbTestSupport {

    @Test
    public void testBoot() throws Exception {
        Set<RolePrincipal> set = new HashSet<RolePrincipal>();
        set.add(new RolePrincipal("Administrator"));
        String exceptions = executeCommand(set, "log:display-exception");
        System.out.println(exceptions);
        assertEquals("Expected log:display-exception to display no exceptions on startup","", exceptions);
    }

    @Configuration
    public Option[] config() {
        return new Option[]{
                new DefaultCompositeOption(esbDistributionConfiguration("jboss-fuse-full")),
        };
    }

}
