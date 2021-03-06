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

package visualizer.graph.coodination;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import visualizer.view.Viewer;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class DistanceMappingView extends javax.swing.JDialog {

    /** Creates new form DistanceMappingView */
    private DistanceMappingView(javax.swing.JFrame parent) {
        super(parent);
        this.setModal(true);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" C�digo Gerado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        framesPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Mappings");
        framesPanel.setLayout(new java.awt.GridBagLayout());

        framesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Mappings"));
        getContentPane().add(framesPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    public static DistanceMappingView getInstance(javax.swing.JFrame parent) {
        instance = new DistanceMappingView(parent);
        return instance;
    }

    public void display(JInternalFrame[] frames) {
        ArrayList<Viewer> gvFrames = new ArrayList<Viewer>();

        for (JInternalFrame jif : frames) {
            if (jif instanceof Viewer) {
                gvFrames.add((Viewer) jif);
            }
        }

        this.setProjectionsNames(gvFrames);
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private void setProjectionsNames(ArrayList<Viewer> frames) {
        for (int i = 0; i < frames.size(); i++) {
            for (int j = 0; j < frames.size(); j++) {
                if (i == j) {
                    continue;
                }

                JCheckBox check = new JCheckBox("Projection [" + frames.get(i).getId() + "] >>> " +
                        "Projection [" + frames.get(j).getId() + "]");
                check.addActionListener(new CheckBoxListener(frames.get(i), frames.get(j)));

                GridBagConstraints cons = new GridBagConstraints();
                cons.anchor = GridBagConstraints.WEST;
                cons.gridx = 0;
                cons.gridy = (i * frames.size()) + j;

                this.framesPanel.add(check, cons);
            }
        }

        this.pack();
    }

    class CheckBoxListener implements ActionListener {

        public CheckBoxListener(Viewer from, Viewer to) {
            this.from = from;
            this.to = to;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JCheckBox &&
                    ((JCheckBox) e.getSource()).isSelected()) {
                ParametersDistanceMapping pdm = ParametersDistanceMapping.getInstance(instance);
                pdm.display(this.to, this.from);
            }
        }

        private Viewer from;
        private Viewer to;
    }

    private static DistanceMappingView instance;
    // Declara��o de vari�veis - n�o modifique//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel framesPanel;
    // Fim da declara��o de vari�veis//GEN-END:variables
}
