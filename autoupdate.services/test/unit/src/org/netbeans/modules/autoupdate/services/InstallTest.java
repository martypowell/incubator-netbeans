/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.autoupdate.services;

import java.io.File;
import org.netbeans.api.autoupdate.UpdateUnit;
import org.netbeans.junit.RandomlyFails;

/**
 *
 * @author Radek Matous
 */
public class InstallTest extends OperationsTestImpl {
    public InstallTest(String testName) {
        super(testName);
    }
    
    protected String moduleCodeNameBaseForTest() {
        return "org.yourorghere.independent";//NOI18N
    } 

    @RandomlyFails
    public void testSelf () throws Exception {
        File pf = getWorkDir ();
        File lastModified = new File (pf, ".lastModified"); // NOI18N
        lastModified.createNewFile ();
        assertTrue ("Check mark created", lastModified.exists ());

        long before = lastModified.lastModified ();
        Thread.sleep (1000);

        UpdateUnit toInstall = UpdateManagerImpl.getInstance ().getUpdateUnit (moduleCodeNameBaseForTest ());
        installModule (toInstall, null);

        if (before >= lastModified.lastModified ()) {
            fail ("The file shall have newer timestamp: " + lastModified.lastModified ());
        }
    }
    
}

