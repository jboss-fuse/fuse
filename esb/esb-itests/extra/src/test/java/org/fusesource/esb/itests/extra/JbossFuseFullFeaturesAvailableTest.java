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
package org.fusesource.esb.itests.extra;

import java.io.File;
import java.io.PrintStream;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

import org.apache.karaf.features.BundleInfo;
import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.fusesource.esb.itests.pax.exam.karaf.EsbTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.options.extra.VMOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.Bundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class JbossFuseFullFeaturesAvailableTest extends EsbTestSupport {

    private static final Set<String> criticalFeatures = new HashSet<>();

    static {
        criticalFeatures.add("karaf");
        criticalFeatures.add("karaf-framework");
        criticalFeatures.add("obr");
        criticalFeatures.add("jpa");
        criticalFeatures.add("jndi");
        criticalFeatures.add("transaction");
        criticalFeatures.add("gemini-blueprint");
        criticalFeatures.add("application-without-isolation");
        criticalFeatures.add("activemq-client");
        criticalFeatures.add("activemq");
        criticalFeatures.add("http");
        criticalFeatures.add("http-whiteboard");
        criticalFeatures.add("war");
        criticalFeatures.add("jetty");
        criticalFeatures.add("fabric-hawtio");
        criticalFeatures.add("fabric-ldap-jaas");
    }

    @Inject
    private FeaturesService featuresService;

    @Test
    @Ignore("FABRIC-730: Run manually and check esb/esb-itests/extra/target/exam/<uuid>/data/log/test.log file")
    public void testFeatures() throws Exception {
        checkAllNotInstalledFeatures();
    }

    @Configuration
    public Option[] config() {
        return new Option[] {
                new DefaultCompositeOption(esbDistributionConfiguration("jboss-fuse-full")),
                CoreOptions.systemTimeout(600000),
                new VMOption("-XX:MaxPermSize=512M"),
        };
    }

    /**
     * <p>Using list of features obtained from {@link FeaturesService}, this methods tries to install
     * and uninstall each feature.</p>
     * <p/>
     * <p>Note that there are several <i>critical</i> features which are not installed by default in
     * particular product, but installing them and then uninstalling may cause several system damages
     * (such as loss of some critical OSGi services).</p>
     *
     * @throws Exception
     */
    protected void checkAllNotInstalledFeatures() throws Exception {
        File logFile = new File(System.getProperty("user.dir") + "/data/log", "test.log");
        logFile.getParentFile().mkdir();
        PrintStream log = new PrintStream(logFile);

        log.println("=== Bundles installed at start ===");
        Map<String, Bundle> bundles = new HashMap<>();
        for (Bundle b : this.bundleContext.getBundles()) {
            log.println("-- Bundle: " + b.getLocation());
            bundles.put(b.getLocation(), b);
        }

        Set<String> bundlesWithoutFeatures = new HashSet<>(bundles.keySet());
        log.println("=== Features installed at start ===");
        Set<String> installedFeatures = new HashSet<>();
        for (Feature f : this.featuresService.listInstalledFeatures()) {
            log.println("Boot-time installed feature: " + f.getId());
            for (BundleInfo bi : f.getBundles()) {
                log.println("-- Bundle: " + bi.getLocation());
                bundlesWithoutFeatures.remove(bi.getLocation());
            }
            installedFeatures.add(f.getName() + "/" + f.getVersion());
        }

        log.println("=== Bundles installed at start, which are not part of any feature ===");
        for (String bl : bundlesWithoutFeatures) {
            log.println("-- Bundle: " + bl);
        }

        List<String> exceptions = new LinkedList<>();
        Feature[] features = this.featuresService.listFeatures();
        int fn = 0;
        int fcount = features.length;
        for (Feature f : features) {
            fn++;
            if (installedFeatures.contains(f.getName() + "/" + f.getVersion()) || criticalFeatures.contains(f.getName())) {
                log.println("Feature (" + fn + "/" + fcount + "): " + f.getId() + " ignored");
                continue;
            }
            try {
                log.println("Feature (" + fn + "/" + fcount + "): " + f.getId());
                this.featuresService.installFeature(f, EnumSet.of(FeaturesService.Option.NoAutoRefreshBundles/*, FeaturesService.Option.Verbose*/));
                List<String> importantBundles = new LinkedList<>();
                boolean uninstall = true;
                for (BundleInfo bi : f.getBundles()) {
                    if (bundlesWithoutFeatures.contains(bi.getLocation())) {
                        uninstall = false;
                        importantBundles.add(bi.getLocation());
                    }
                }
                if (uninstall) {
                    this.featuresService.uninstallFeature(f.getName(), f.getVersion(), EnumSet.of(FeaturesService.Option.NoAutoRefreshBundles/*, FeaturesService.Option.Verbose*/));
                } else {
                    log.println("Feature " + f.getId() + " *won't* be uninstalled because it contains important bundles:");
                    for (String ib : importantBundles) {
                        log.println(">> " + ib);
                    }
                }
            } catch (Exception e) {
                log.println("Feature (un)installation error: " + e.getMessage());
                exceptions.add("=======\nfeature: " + f.getName() + "/" + f.getVersion() + "\n " + e.getMessage());
            }
        }
        for (String e : exceptions) {
            log.println(e);
        }
        log.close();
        assertThat("There should be no feature installation exceptions", exceptions.size(), is(0));
    }

}
