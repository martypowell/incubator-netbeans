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
package org.netbeans.modules.spring.beans.completion.completors;

import org.netbeans.modules.spring.beans.completion.Completor;
import org.netbeans.modules.spring.beans.completion.CompletorFactory;


/**
 *
 * @author Rohan Ranade (Rohan.Ranade@Sun.COM)
 */
public class AttributeValueCompletorFactory implements CompletorFactory {

    private String[] itemTextAndDocs;

    public AttributeValueCompletorFactory(String[] itemTextAndDocs) {
        this.itemTextAndDocs = itemTextAndDocs;
    }

    public Completor createCompletor(int invocationOffset) {
        return new AttributeValueCompletor(itemTextAndDocs, invocationOffset);
    }
}
