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
package org.netbeans.modules.versioning.util.common;

import javax.swing.JComponent;
import org.openide.cookies.EditorCookie;
import org.openide.nodes.Node;

/**
 *
 * @author Ondrej Vrabec
 */
public interface FileViewComponent<T extends Node> {

    public T getSelectedNode ();

    public void setSelectedNode (T toSelect);

    public T getNodeAtPosition (int position);

    public T[] getNeighbouringNodes (T node, int boundary);

    public T getNextNode (T node);

    public T getPreviousNode (T node);

    public boolean hasNextNode (T node);

    public boolean hasPreviousNode (T node);

    public void focus ();

    int getPreferredHeaderHeight ();

    JComponent getComponent ();
    
    int getPreferredHeight ();

    Object prepareModel (T[] nodes);
    
    void setModel (T[] nodes, EditorCookie[] editorCookies, Object modelData);
    
}
