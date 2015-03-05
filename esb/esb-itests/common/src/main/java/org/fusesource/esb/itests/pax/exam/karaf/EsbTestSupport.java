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

package org.fusesource.esb.itests.pax.exam.karaf;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import io.fabric8.tooling.testing.pax.exam.karaf.FabricKarafTestSupport;
import org.apache.karaf.jaas.boot.principal.RolePrincipal;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

public class EsbTestSupport extends FabricKarafTestSupport {
    static final String GROUP_ID = "org.jboss.fuse";
    static final String ARTIFACT_ID = "jboss-fuse-full";
    static final Set<RolePrincipal> ROLES = new HashSet<>();
    static {
        ROLES.add(new RolePrincipal("Administrator"));
        ROLES.add(new RolePrincipal("SuperUser"));
    }


    private String version = System.getProperty("project.version");

    /**
     * Execute a command on the JBoss Fuse/A-MQ console as a user with administrative privileges
     */
    public static String executeCommandAsAdmin(String command) {
        return executeCommand(ROLES, command);
    }

    protected void installQuickstartBundle(String bundle) throws Exception {
        String featureInstallOutput = executeCommandAsAdmin("osgi:install -s mvn:org.jboss.quickstarts.fuse/" + bundle + "/" + version);
        System.out.println(featureInstallOutput);
        assertFalse(featureInstallOutput.isEmpty());
        String featureListOutput = executeCommandAsAdmin("osgi:list -l | grep " + bundle);
        System.out.println(featureListOutput);
        assertFalse(featureListOutput.isEmpty());
    }
    
    protected void installUninstallCommand(String feature) throws Exception {
        installUninstallCommand(feature, false);
    }
    
    protected void installUninstallCommand(String feature, boolean refresh) throws Exception {
        String installFeatureCmd = "features:install -v ";
        if (!refresh) {
            installFeatureCmd = installFeatureCmd + "-r ";
        }
        String featureInstallOutput = executeCommandAsAdmin(installFeatureCmd + feature);
        System.out.println(featureInstallOutput);
        assertFalse(featureInstallOutput.isEmpty());
        String featureListOutput = executeCommandAsAdmin("features:list -i | grep " + feature);
        System.out.println(featureListOutput);
        assertFalse(featureListOutput.isEmpty());
        System.out.println(executeCommandAsAdmin("features:uninstall " + feature));
        featureListOutput = executeCommandAsAdmin("features:list -i | grep " + feature);
        System.out.println(featureListOutput);
        assertTrue(featureListOutput.isEmpty());
    }
    
    protected Option[] esbDistributionConfiguration() {
        return esbDistributionConfiguration(null);
    }

    /**
     * Create an {@link org.ops4j.pax.exam.Option} for using a ESB distribution.
     *
     * @return
     */
    protected Option[] esbDistributionConfiguration(String distroArtifactId) {
        if (distroArtifactId == null) {
            distroArtifactId = ARTIFACT_ID;
        }
        return new Option[] {
                karafDistributionConfiguration().frameworkUrl(maven().groupId("org.jboss.fuse").artifactId(distroArtifactId).versionAsInProject().type("zip"))
                .karafVersion(MavenUtils.getArtifactVersion("org.jboss.fuse", distroArtifactId)).name("JBoss Fuse").unpackDirectory(new File("target/exam")).useDeployFolder(false),
                useOwnExamBundlesStartLevel(50),
                editConfigurationFilePut("etc/config.properties", "karaf.startlevel.bundle", "50"),
                editConfigurationFilePut("etc/config.properties", "karaf.startup.message", "Loading Fuse from: ${karaf.home}"),
                editConfigurationFilePut("etc/users.properties", "admin", "admin,admin,manager,viewer,Operator, Maintainer, Deployer, Auditor, Administrator, SuperUser"),
                editConfigurationFilePut("etc/system.properties", "project.version", MavenUtils.getArtifactVersion(GROUP_ID, ARTIFACT_ID)),
                mavenBundle("io.fabric8.tooling.testing", "pax-exam-karaf", MavenUtils.getArtifactVersion("io.fabric8.tooling.testing", "pax-exam-karaf")),
                mavenBundle("org.jboss.fuse.itests", "esb-itests-common", MavenUtils.getArtifactVersion("org.jboss.fuse.itests", "esb-itests-common")),
                wrappedBundle(mavenBundle("io.fabric8.itests", "fabric-itests-common", MavenUtils.getArtifactVersion("io.fabric8.itests", "fabric-itests-common"))),
                keepRuntimeFolder(),
                logLevel(LogLevelOption.LogLevel.ERROR)
        };
    }

    protected String getEsbVersion() {
        return version;
    }
}
