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

package org.netbeans.spi.java.project.support.ui;

import org.openide.nodes.Node;

/**
 * This handler is used by PackageViewChildren.PackageNode.setName() method.
 * PackageNode.setName() uses  Lookup.getDefault() to lookup for instances of
 * packageRenameHandler. If there is one instance found, it's handleRename(...)
 * method is called to handle rename request. More than one instance of
 * PackageRenameHandler is not allowed.
 *
 * @since 1.5
 * @author Jan Becicka
 */
public interface PackageRenameHandler {
    /**
     * @param node on this node rename was requested
     * @param newName new name of node
     * @throws java.lang.IllegalArgumentException thrown if rename cannot be performed
     */
    void handleRename(Node node, String newName) throws IllegalArgumentException;
}
