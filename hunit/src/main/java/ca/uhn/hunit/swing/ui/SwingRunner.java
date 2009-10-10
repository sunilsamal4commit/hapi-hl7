/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  "GPL"), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SwingRunner.java
 *
 * Created on 22-Jul-2009, 10:57:14 PM
 */
package ca.uhn.hunit.swing.ui;

import ca.uhn.hunit.iface.JmsInterfaceImpl;
import javax.swing.tree.DefaultTreeModel;

import ca.uhn.hunit.iface.MllpHl7V2InterfaceImpl;
import ca.uhn.hunit.msg.Hl7V2MessageImpl;
import ca.uhn.hunit.swing.controller.ctx.AbstractContextController;
import ca.uhn.hunit.swing.controller.ctx.Hl7V2MessageEditorController;
import ca.uhn.hunit.swing.controller.ctx.JmsInterfaceContextController;
import ca.uhn.hunit.swing.controller.ctx.MllpHl7v2InterfaceEditorContextController;
import ca.uhn.hunit.swing.controller.ctx.SwingRunnerController;
import ca.uhn.hunit.swing.model.InterfaceTreeNode;
import ca.uhn.hunit.swing.model.InterfacesTreeRenderer;
import ca.uhn.hunit.swing.model.MessageTreeNode;
import ca.uhn.hunit.swing.model.TestBatteryTreeNode;
import ca.uhn.hunit.test.TestBatteryImpl;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class SwingRunner extends javax.swing.JFrame {

    private TestBatteryImpl myBattery;
    private final SwingRunnerController myController;

    /** Creates new form SwingRunner
     * @param theBatteryImpl */
    public SwingRunner(SwingRunnerController theController, TestBatteryImpl theBatteryImpl) {
        this.myBattery = theBatteryImpl;
        this.myController = theController;

        initComponents();

        myTestTree.setModel(new DefaultTreeModel(new TestBatteryTreeNode(myBattery), true));
        myTestTree.setCellRenderer(new InterfacesTreeRenderer());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        myProgressBar = new javax.swing.JProgressBar();
        myStatusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTestTree = new javax.swing.JTree();
        myTestContextPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        myFileMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        myStatusLabel.setText("jLabel1");

        myTestTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                myTestTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(myTestTree);

        myTestContextPanel.setLayout(new java.awt.BorderLayout());

        myFileMenu.setText("File");
        jMenuBar1.add(myFileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(myStatusLabel)
                    .addComponent(myTestContextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(myTestContextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(myProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void myTestTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_myTestTreeValueChanged
        Object selectedModelObject = evt.getPath().getLastPathComponent();
        AbstractContextController<?> ctxController = null;

        if (selectedModelObject instanceof InterfaceTreeNode) {
            InterfaceTreeNode interfaceTreeNode = (InterfaceTreeNode) selectedModelObject;
            if (interfaceTreeNode.getUserObject() instanceof MllpHl7V2InterfaceImpl) {
                ctxController = new MllpHl7v2InterfaceEditorContextController((MllpHl7V2InterfaceImpl) interfaceTreeNode.getUserObject());
            } else if (interfaceTreeNode.getUserObject() instanceof JmsInterfaceImpl) {
                ctxController = new JmsInterfaceContextController((JmsInterfaceImpl) interfaceTreeNode.getUserObject());
            } else {
                System.out.println("Unknown interface: " + selectedModelObject);
            }
        } else if (selectedModelObject instanceof MessageTreeNode) {
            MessageTreeNode treeNode = (MessageTreeNode) selectedModelObject;
            if (treeNode.getUserObject() instanceof Hl7V2MessageImpl) {
                ctxController = new Hl7V2MessageEditorController((Hl7V2MessageImpl) treeNode.getUserObject());
            } else {
                System.out.println("Unknown message: " + selectedModelObject);
            }

        } else {
            System.out.println(selectedModelObject);
        }

        myTestContextPanel.removeAll();
        if (ctxController != null) {
            final JPanel view = ctxController.getView();
            myTestContextPanel.add(view, BorderLayout.CENTER);
            myTestContextPanel.validate();
        }
    }//GEN-LAST:event_myTestTreeValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu myFileMenu;
    private javax.swing.JProgressBar myProgressBar;
    private javax.swing.JLabel myStatusLabel;
    private javax.swing.JPanel myTestContextPanel;
    private javax.swing.JTree myTestTree;
    // End of variables declaration//GEN-END:variables
}
