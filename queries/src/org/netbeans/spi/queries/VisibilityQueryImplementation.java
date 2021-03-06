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

package org.netbeans.spi.queries;

import org.openide.filesystems.FileObject;

import javax.swing.event.ChangeListener;

/**
 * Determine whether files should be hidden in views presented to the user.
 * <p>
 * Global lookup is used to find all instances of VisibilityQueryImplementation.
 * </p>
 * <p>
 * Threading note: implementors should avoid acquiring locks that might be held
 * by other threads. Generally treat this interface similarly to SPIs in
 * {@link org.openide.filesystems} with respect to threading semantics.
 * </p>
 * @see org.netbeans.api.queries.VisibilityQuery
 * @author Radek Matous 
 */ 
public interface VisibilityQueryImplementation {
    /**
     * Check whether a file is recommended to be visible.
     * @param file a file to considered
     * @return true if it is recommended to display this file
     */ 
    boolean isVisible(FileObject file);

    /**
     * Add a listener to changes.
     * @param l a listener to add
     */
    void addChangeListener(ChangeListener l);
        
    /**
     * Stop listening to changes.
     * @param l a listener to remove
     */
    void removeChangeListener(ChangeListener l);    
}
