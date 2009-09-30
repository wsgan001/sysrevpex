/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.view.tools;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import visualizer.graph.Graph;
import visualizer.graph.Scalar;
import visualizer.graph.Vertex;
import visualizer.util.PExConstants;
import visualizer.view.Viewer;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class JoinScalars extends javax.swing.JDialog {

    /** Creates new form EqualMappingView */
    private JoinScalars(java.awt.Frame parent) {
        super(parent);
        this.setModal(true);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        framePanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        joinButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Mappings");

        framePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Scalars"));
        framePanel.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(framePanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        joinButton.setText("Join");
        joinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(joinButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

    private void joinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinButtonActionPerformed
        Scalar scalar = graph.addScalar(PExConstants.JOIN);

        for(Vertex v : graph.getVertex()) {
            float max = Float.NEGATIVE_INFINITY;
            for (int i = 0; i < this.tojoin.size(); i++) {
                max = Math.max(max, v.getScalar(this.tojoin.get(i)));
            }

            v.setScalar(scalar, max);
        }

        this.view.updateScalars(scalar);
        this.view.updateImage();
        this.view.getProjectionExplorerView().repaint();
        this.setVisible(false);
    }//GEN-LAST:event_joinButtonActionPerformed

    public static JoinScalars getInstance(javax.swing.JFrame parent) {
        return new JoinScalars(parent);
    }

    public void display(Viewer view) {
        this.view = view;
        this.graph = view.getGraph();

        this.setScalars(graph);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void setScalars(Graph graph) {
        ArrayList<Scalar> scalars = graph.getScalars();

        for (int i = 0; i < scalars.size(); i++) {
            if (!scalars.get(i).getName().equals(PExConstants.TOPICS) &&
                    !scalars.get(i).getName().equals(PExConstants.CDATA) &&
                    !scalars.get(i).getName().equals(PExConstants.DOTS) &&
                    !scalars.get(i).getName().equals(PExConstants.JOIN) &&
                    !scalars.get(i).getName().equals(PExConstants.PIVOTS)) {

                JCheckBox check = new JCheckBox(scalars.get(i).toString());
                check.addActionListener(new CheckBoxListener(scalars.get(i)));

                GridBagConstraints cons = new GridBagConstraints();
                cons.anchor = GridBagConstraints.WEST;
                cons.gridx = 0;
                cons.gridy = i;
                this.framePanel.add(check, cons);
            }
        }

        this.pack();
    }
    
    class CheckBoxListener implements ActionListener {

        public CheckBoxListener(Scalar scalar) {
            this.scalar = scalar;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JCheckBox) {
                if (((JCheckBox) e.getSource()).isSelected()) {
                    tojoin.add(this.scalar);
                } else {
                    tojoin.remove(this.scalar);
                }
            }
        }

        private Scalar scalar;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JoinScalars(new javax.swing.JFrame()).setVisible(true);
            }

        });
    }

    private Viewer view;
    private Graph graph;
    private ArrayList<Scalar> tojoin = new ArrayList<Scalar>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel framePanel;
    private javax.swing.JButton joinButton;
    // End of variables declaration//GEN-END:variables
}
