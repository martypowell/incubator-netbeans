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

package org.netbeans.core.actions;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallbackSystemAction;

/**
 * A skeleton action, useful just as a placeholder for global shortcut.
 * Components wishing to use it's shortcut should place the "NextViewAction" key into their action maps.
 *
 * @author mkleint
 */
public class NextViewCallbackAction extends CallbackSystemAction {

    /** Creates a new instance of NextViewCallbackAction */
    public NextViewCallbackAction() {
    }

    public String getName() {
        return NbBundle.getMessage(NextViewCallbackAction.class, "LBL_NextViewCallbackAction");
    }

    public Object getActionMapKey() {
        return "NextViewAction"; //NOI18N
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    protected boolean asynchronous() {
        return false;
    }
    
}
