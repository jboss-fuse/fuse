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
package org.fusesource.esb.itests.basic.fabric;

import io.fabric8.api.*;
import io.fabric8.itests.paxexam.support.ContainerBuilder;
import io.fabric8.itests.paxexam.support.ContainerCondition;
import io.fabric8.itests.paxexam.support.ContainerProxy;

import java.util.Set;

import io.fabric8.itests.paxexam.support.Provision;
import io.fabric8.zookeeper.ZkPath;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;

import static io.fabric8.zookeeper.utils.ZooKeeperUtils.setData;
import static org.fusesource.esb.itests.pax.exam.karaf.EsbTestSupport.executeCommandAsAdmin;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class EsbProfileLongTest extends EsbFeatureTest {

    @Test
    public void testFeatures() throws Exception {
        System.err.println(executeCommandAsAdmin("fabric:create -n"));
        ServiceProxy<FabricService> fabricProxy = ServiceProxy.createServiceProxy(bundleContext, FabricService.class);
        try {
            FabricService fabricService = fabricProxy.getService();
            CuratorFramework curator = fabricService.adapt(CuratorFramework.class);

            Set<ContainerProxy> containers = ContainerBuilder.create(fabricProxy).withName("esb").withProfiles("jboss-fuse-full").assertProvisioningResult().build();
            try {
                prepareFeaturesForTesting(containers, "connector", "jboss-fuse-full", "geronimo-connector");
                prepareFeaturesForTesting(containers, "saaj", "jboss-fuse-full", "saaj-impl");

                assertFeatures(fabricService, curator);
            } finally {
                ContainerBuilder.destroy(containers);
            }
        } finally {
            fabricProxy.close();
        }
    }

    /*
     * Override as
     */
    protected void assertProvisionedFeature(FabricService fabricService, CuratorFramework curator, Set<? extends Container> containers, String featureNames, String profileName, final String expectedSymbolicNames) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Container container : containers) {
            sb.append(container.getId()).append(" ");
        }
        sb.append("]");

        System.out.println("Testing profile:" + profileName + " on container:" + sb.toString() + " by adding feature:" + featureNames);
        Version version = fabricService.getRequiredDefaultVersion();

        Profile defaultProfile = version.getRequiredProfile("default");
        Profile targetProfile = version.getRequiredProfile(profileName);

        for (Container container : containers) {
            //We set container to default to clean the container up.
            container.setProfiles(new Profile[]{defaultProfile});
        }
        Provision.containerStatus(containers, PROVISION_TIMEOUT);

        for (String featureName : featureNames.split(" ")) {
            System.out.println(executeCommandAsAdmin("fabric:profile-edit --feature "+featureName+" "+targetProfile.getId()));
        }

        System.out.println(executeCommandAsAdmin("fabric:profile-display "+ profileName));

        for (Container container : containers) {
            //Test the modified profile.
            setData(curator, ZkPath.CONTAINER_PROVISION_RESULT.getPath(container.getId()), "switching profile");
            container.setProfiles(new Profile[]{targetProfile});
        }

        Provision.containerStatus(containers, PROVISION_TIMEOUT);
        System.out.println(executeCommandAsAdmin("fabric:container-list"));

        Assert.assertTrue(Provision.waitForCondition(containers, new ContainerCondition() {
            @Override
            public Boolean checkConditionOnContainer(Container c) {
                for (String symbolicName : expectedSymbolicNames.split(" ")) {
                    String bundles = executeCommandAsAdmin("container-connect -u admin -p admin " + c.getId() + " osgi:list -s -t 0 | grep " + symbolicName);
                    if (bundles != null) {
                        return bundles.contains(symbolicName);
                    }
                }
                return false;
            }
        }, PROVISION_TIMEOUT));

        for (Container container : containers) {
            //We set the container to default to clean up the profile.
            if (!defaultProfile.equals(targetProfile)) {
                setData(curator, ZkPath.CONTAINER_PROVISION_RESULT.getPath(container.getId()), "switching profile");
            }
            container.setProfiles(new Profile[]{defaultProfile});
        }

        Provision.containerStatus(containers, PROVISION_TIMEOUT);
        for (String featureName : featureNames.split(" ")) {
            System.out.println(executeCommandAsAdmin("fabric:profile-edit --delete --feature "+featureName+" "+targetProfile.getId()));
        }
    }
}
