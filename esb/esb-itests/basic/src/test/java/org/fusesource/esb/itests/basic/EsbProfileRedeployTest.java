/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.esb.itests.basic;

import io.fabric8.api.Container;
import io.fabric8.api.FabricService;
import io.fabric8.api.ServiceProxy;
import io.fabric8.groups.MultiGroup;
import io.fabric8.groups.NodeState;
import io.fabric8.groups.internal.ManagedGroupFactory;
import io.fabric8.groups.internal.ManagedGroupFactoryBuilder;
import io.fabric8.groups.internal.ZooKeeperMultiGroup;
import io.fabric8.groups.internal.ZooKeeperMultiGroup;
import io.fabric8.itests.paxexam.support.ContainerBuilder;
import io.fabric8.itests.paxexam.support.ContainerProxy;
import io.fabric8.itests.paxexam.support.Provision;
import io.fabric8.mq.fabric.discovery.FabricDiscoveryAgent;
import org.apache.curator.framework.CuratorFramework;
import org.fusesource.esb.itests.pax.exam.karaf.EsbTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class EsbProfileRedeployTest extends EsbTestSupport {

    private long timeout = 30 * 1000L;

    //@Ignore("ENTESB-3040")
    @Test
    public void testProfileRedeploy() throws Exception {
        executeCommandAsAdmin("fabric:create -n");
        ServiceProxy<FabricService> fabricProxy = ServiceProxy.createServiceProxy(bundleContext, FabricService.class);
        try {
            FabricService fabricService = fabricProxy.getService();

            Set<ContainerProxy> containers = ContainerBuilder.create(fabricProxy, 1).withJvmOpts("-Xmx1536m -XX:MaxPermSize=256m")
                    .withName("node").withProfiles("jboss-fuse-full").assertProvisioningResult().build();
            try {
                Container node = containers.iterator().next();
                Provision.provisioningSuccess(Arrays.asList(node), PROVISION_TIMEOUT);

                CuratorFramework curator = fabricService.adapt(CuratorFramework.class);
                ManagedGroupFactory factory = ManagedGroupFactoryBuilder.create(curator, getClass().getClassLoader(), new Callable<CuratorFramework>() {
                    @Override
                    public CuratorFramework call() throws Exception {
                        throw new Exception("Shouldn't be called");
                    }
                });
                final MultiGroup group = (MultiGroup) factory.createMultiGroup("/fabric/registry/clusters/amq/default", FabricDiscoveryAgent.ActiveMQNode.class);
                ZooKeeperMultiGroup wtf =(ZooKeeperMultiGroup) group;  // TODO remove?
                group.start();
                FabricDiscoveryAgent.ActiveMQNode master = null;
                Provision.waitForCondition(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        while (group.slaves().isEmpty()) {
                            Thread.sleep(1000);
                        }
                        return true;
                    }
                }, timeout);

                master = (FabricDiscoveryAgent.ActiveMQNode) group.master();
                String masterContainer = master.getContainer();
                assertEquals("root", masterContainer);
                assertTrue(getSlaveNames(group).contains("node"));

                for (int i = 0; i < 5; i++) {

                    Thread.sleep(5000);

                    executeCommandAsAdmin("container-remove-profile node jboss-fuse-full");
                    Provision.provisioningSuccess(Arrays.asList(fabricService.getContainers()), PROVISION_TIMEOUT);

                    Provision.waitForCondition(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            while (!group.slaves().isEmpty()) {
                                Thread.sleep(1000);
                            }
                            return true;
                        }
                    }, timeout);
                    master = (FabricDiscoveryAgent.ActiveMQNode) group.master();
                    assertEquals("root", master.getContainer());
                    assertFalse(getSlaveNames(group).contains("node"));

                    Thread.sleep(5000);

                    executeCommandAsAdmin("container-add-profile node jboss-fuse-full");
                    Provision.provisioningSuccess(Arrays.asList(fabricService.getContainers()), PROVISION_TIMEOUT);

                    Provision.waitForCondition(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            while (group.slaves().isEmpty()) {
                                Thread.sleep(1000);
                            }
                            return true;
                        }
                    }, timeout);

                    master = (FabricDiscoveryAgent.ActiveMQNode) group.master();
                    masterContainer = master.getContainer();
                    assertEquals("root", masterContainer);
                    assertTrue(getSlaveNames(group).contains("node"));
                }
            } finally {
                ContainerBuilder.destroy(containers);
            }
        } finally {
            fabricProxy.close();
        }
    }

    private List<String> getSlaveNames(MultiGroup group) {
        List<String> slaves = new LinkedList<>();
        for (NodeState state : (List<NodeState>) group.slaves()) {
            slaves.add(state.getContainer());
        }

        return slaves;
    }

    @Configuration
   	public Option[] config() {
   		return new Option[]{
                new DefaultCompositeOption(esbDistributionConfiguration("jboss-fuse-full")),
                features("default", "mq-fabric"),
                mavenBundle("io.fabric8.itests.paxexam", "fabric-itests-paxexam-common", MavenUtils.getArtifactVersion("io.fabric8.itests.paxexam", "fabric-itests-paxexam-common")),
                mavenBundle("io.fabric8.tooling.testing", "pax-exam-karaf", MavenUtils.getArtifactVersion("io.fabric8.tooling.testing", "pax-exam-karaf")),
                mavenBundle("org.jboss.fuse.itests", "esb-itests-common", MavenUtils.getArtifactVersion("org.jboss.fuse.itests", "esb-itests-common")),
                wrappedBundle(mavenBundle("io.fabric8.itests", "fabric-itests-common", MavenUtils.getArtifactVersion("io.fabric8.itests", "fabric-itests-common"))),
   		};
   	}

}
