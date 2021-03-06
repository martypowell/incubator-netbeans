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

package org.netbeans.modules.editor.codegen;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Utilities;

/**
 *
 * @author Dusan Balek, Jan Lahoda
 */
public class GenerateCodePanel extends javax.swing.JPanel {

    private JTextComponent component;
    
    /** Creates new form GenerateCodePanel */
    public GenerateCodePanel(JTextComponent component, List<? extends CodeGenerator> generators) {
        this.component = component;
        initComponents();
        setFocusable(false);
        setNextFocusableComponent(jList1);
        setBackground(jList1.getBackground());
        jScrollPane1.setBackground(jList1.getBackground());
        jList1.setModel(createModel(generators));
        jList1.setSelectedIndex(0);
        jList1.setVisibleRowCount(generators.size() > 16 ? 16 : generators.size());
        jList1.setCellRenderer(new Renderer(jList1));
        jList1.grabFocus();
        jList1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                PopupUtil.hidePopup();
            }
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 64, 64)));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 4, 4));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listMouseReleased(evt);
            }
        });
        jList1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                listMouseMoved(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(GenerateCodePanel.class, "LBL_generate_code")); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jLabel1.setOpaque(true);
        add(jLabel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void listMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseReleased
        invokeSelected();
    }//GEN-LAST:event_listMouseReleased

    private void listMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseMoved
        int idx = jList1.locationToIndex(evt.getPoint());
        if (idx != jList1.getSelectedIndex())
            jList1.setSelectedIndex(idx);
    }//GEN-LAST:event_listMouseMoved

    private void listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listKeyPressed
        KeyStroke ks = KeyStroke.getKeyStrokeForEvent(evt);
        if (ks.getKeyCode() == KeyEvent.VK_ENTER || ks.getKeyCode() == KeyEvent.VK_SPACE) {
            invokeSelected();
        } else if (ks.getKeyCode() == KeyEvent.VK_DOWN) {
            int size = jList1.getModel().getSize();
            if (size > 0) {
                int idx = (jList1.getSelectedIndex() + 1) % size;
                if (idx == size)
                    idx = 0;
                jList1.setSelectedIndex(idx);
                jList1.ensureIndexIsVisible(idx);
                evt.consume();
            }
        } else if (ks.getKeyCode() == KeyEvent.VK_UP) {
            int size = jList1.getModel().getSize();
            if (size > 0) {
                int idx = (jList1.getSelectedIndex() - 1 + size) % size;
                jList1.setSelectedIndex(idx);
                jList1.ensureIndexIsVisible(idx);
                evt.consume();
            }
        }
    }//GEN-LAST:event_listKeyPressed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel1;
    public javax.swing.JList jList1;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private DefaultListModel createModel(List<? extends CodeGenerator> generators) {
        DefaultListModel model = new DefaultListModel();
        for (CodeGenerator generator : generators)
            model.addElement(generator);
        return model;
    }
    
    private void invokeSelected() {
        PopupUtil.hidePopup();
        if (Utilities.isMac()) {
            // see issue #115106
            component.requestFocus();
        }
        Object value = jList1.getSelectedValue();
        if (value instanceof CodeGenerator)
            ((CodeGenerator)value).invoke();
    }
            
    private static class Renderer extends DefaultListCellRenderer {
        
        private static int DARKER_COLOR_COMPONENT = 5;
        
        private Color fgColor;
        private Color bgColor;
        private Color bgColorDarker;
        private Color bgSelectionColor;
        private Color fgSelectionColor;
        
        public Renderer(JList list) {
            setFont(list.getFont());
            fgColor = list.getForeground();
            bgColor = list.getBackground();
            bgColorDarker = new Color(Math.abs(bgColor.getRed() - DARKER_COLOR_COMPONENT),
                    Math.abs(bgColor.getGreen() - DARKER_COLOR_COMPONENT),
                    Math.abs(bgColor.getBlue() - DARKER_COLOR_COMPONENT));
            bgSelectionColor = list.getSelectionBackground();
            fgSelectionColor = list.getSelectionForeground();
        }
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean hasFocus) {
            if (isSelected) {
                setForeground(fgSelectionColor);
                setBackground(bgSelectionColor);
            } else {
                setForeground(fgColor);
                setBackground(index % 2 == 0 ? bgColor : bgColorDarker);
            }            
            setText(value instanceof CodeGenerator ? ((CodeGenerator)value).getDisplayName() : value.toString());
            return this;
        }        
    }
}
