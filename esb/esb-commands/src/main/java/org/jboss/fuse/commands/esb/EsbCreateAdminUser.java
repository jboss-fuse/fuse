/**
 *  Copyright 2005-2016 Red Hat, Inc.
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
package org.jboss.fuse.commands.esb;

import org.apache.felix.gogo.commands.Command;
import org.jboss.fuse.commands.CreateAdminUser;

@Command(name = "create-admin-user", scope = "esb", description = "Creates a new admin user if one doesn't exist")
public class EsbCreateAdminUser extends CreateAdminUser {

}
