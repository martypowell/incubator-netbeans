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

package org.netbeans.modules.debugger.jpda.ui.debugging;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.api.debugger.jpda.JPDAThread;
import org.netbeans.modules.debugger.jpda.models.JPDAThreadImpl;
import org.netbeans.modules.debugger.jpda.util.WeakCacheMap;
import org.netbeans.spi.debugger.ui.DebuggingView.DVSupport;
import org.netbeans.spi.debugger.ui.DebuggingView.DVThread;

/**
 *
 * @author Martin Entlicher
 */
public class JPDADVThread implements DVThread, WeakCacheMap.KeyedValue<JPDAThreadImpl> {
    
    private final DebuggingViewSupportImpl dvSupport;
    private final JPDAThreadImpl t;
    private PropertyChangeProxyListener proxyListener;
    
    public JPDADVThread(DebuggingViewSupportImpl dvSupport, JPDAThreadImpl t) {
        this.dvSupport = dvSupport;
        this.t = t;
    }

    @Override
    public String getName() {
        return t.getName();
    }

    @Override
    public boolean isSuspended() {
        return t.isSuspended();
    }

    @Override
    public void resume() {
        t.resume();
    }

    @Override
    public void suspend() {
        t.suspend();
    }

    @Override
    public void makeCurrent() {
        t.makeCurrent();
    }

    @Override
    public DVSupport getDVSupport() {
        return dvSupport;
    }

    @Override
    public List<DVThread> getLockerThreads() {
        List<JPDAThread> lockerThreads = t.getLockerThreads();
        if (lockerThreads == null) {
            return null;
        }
        return new ThreadListDelegate(lockerThreads);
    }

    @Override
    public void resumeBlockingThreads() {
        t.resumeBlockingThreads();
    }

    @Override
    public Breakpoint getCurrentBreakpoint() {
        return t.getCurrentBreakpoint();
    }

    @Override
    public boolean isInStep() {
        return t.isInStep();
    }

    @Override
    public synchronized void addPropertyChangeListener(PropertyChangeListener pcl) {
        if (proxyListener == null) {
            proxyListener = new PropertyChangeProxyListener();
            t.addPropertyChangeListener(proxyListener);
        }
        proxyListener.add(pcl);
    }

    @Override
    public synchronized void removePropertyChangeListener(PropertyChangeListener pcl) {
        if (proxyListener != null) {
            proxyListener.remove(pcl);
            if (proxyListener.isEmpty()) {
                t.removePropertyChangeListener(proxyListener);
                proxyListener = null;
            }
        }
    }

    @Override
    public JPDAThreadImpl getKey() {
        return t;
    }
    
    private class ThreadListDelegate extends AbstractList<DVThread> {

        private final List<JPDAThread> threads;
        
        public ThreadListDelegate(List<JPDAThread> threads) {
            this.threads = threads;
        }

        @Override
        public DVThread get(int index) {
            return dvSupport.get((JPDAThreadImpl) threads.get(index));
        }

        @Override
        public int size() {
            return threads.size();
        }

    }


    
    private class PropertyChangeProxyListener implements PropertyChangeListener {
        
        private final List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<>();

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            PropertyChangeEvent proxyEvent = new PropertyChangeEvent(JPDADVThread.this, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
            proxyEvent.setPropagationId(evt.getPropagationId());
            for (PropertyChangeListener pchl : listeners) {
                pchl.propertyChange(proxyEvent);
            }
        }
        
        void add(PropertyChangeListener pcl) {
            listeners.add(pcl);
        }
        
        void remove(PropertyChangeListener pcl) {
            listeners.remove(pcl);
        }
        
        boolean isEmpty() {
            return listeners.isEmpty();
        }
        
    }
    
}
