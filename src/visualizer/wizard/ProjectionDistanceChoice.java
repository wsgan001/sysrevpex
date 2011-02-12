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

package visualizer.wizard;

import javax.swing.JFileChooser;
import visualizer.projection.ProjectionData;
import visualizer.projection.ProjectionType;
import visualizer.projection.SourceType;
import visualizer.projection.distance.DissimilarityType;
import visualizer.projection.distance.kolmogorov.CompressorType;
import visualizer.util.SaveDialog;
import visualizer.util.filefilter.DMATFilter;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class ProjectionDistanceChoice extends WizardPanel {

    private ProjectionData pdata;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bzip2RadioButton;
    private javax.swing.JPanel chooseDistanceTypePanel;
    private javax.swing.JPanel chooseProjectionTechniquePanel;
    private javax.swing.ButtonGroup compressTypeButtonGroup;
    private javax.swing.ButtonGroup distanceButtonGroup;
    private javax.swing.JComboBox distanceComboBox;
    private javax.swing.JRadioButton distanceRadioButton;
    private javax.swing.JRadioButton gzipRadioButton;
    private javax.swing.JRadioButton ncdRadioButton;
    private javax.swing.JComboBox projComboBox;
    private javax.swing.JCheckBox saveDistanceMatrixCheckBox;
    private javax.swing.JLabel spaceLabel1;
    private javax.swing.JLabel spaceLabel2;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form ProjectionData
     * 
     * @param pdata 
     */
    public ProjectionDistanceChoice(ProjectionData pdata) {
        this.pdata = pdata;
        initComponents();

        for (DissimilarityType disstype : DissimilarityType.getTypes()) {
            if (disstype != DissimilarityType.NONE) {
                distanceComboBox.addItem(disstype);
            }
        }

        for (ProjectionType pt : ProjectionType.getTypes()) {
            if (pt != ProjectionType.NONE) {
                this.projComboBox.addItem(pt);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        distanceButtonGroup = new javax.swing.ButtonGroup();
        compressTypeButtonGroup = new javax.swing.ButtonGroup();
        chooseProjectionTechniquePanel = new javax.swing.JPanel();
        projComboBox = new javax.swing.JComboBox();
        chooseDistanceTypePanel = new javax.swing.JPanel();
        gzipRadioButton = new javax.swing.JRadioButton();
        bzip2RadioButton = new javax.swing.JRadioButton();
        spaceLabel1 = new javax.swing.JLabel();
        spaceLabel2 = new javax.swing.JLabel();
        distanceRadioButton = new javax.swing.JRadioButton();
        distanceComboBox = new javax.swing.JComboBox();
        ncdRadioButton = new javax.swing.JRadioButton();
        saveDistanceMatrixCheckBox = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setLayout(new java.awt.GridBagLayout());

        chooseProjectionTechniquePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose the Projection Technique"));
        chooseProjectionTechniquePanel.setLayout(new java.awt.GridBagLayout());

        projComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        chooseProjectionTechniquePanel.add(projComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(chooseProjectionTechniquePanel, gridBagConstraints);

        chooseDistanceTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose the Distance Type"));
        chooseDistanceTypePanel.setLayout(new java.awt.GridBagLayout());

        compressTypeButtonGroup.add(gzipRadioButton);
        gzipRadioButton.setSelected(true);
        gzipRadioButton.setText("gzip");
        gzipRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gzipRadioButton.setEnabled(false);
        gzipRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        chooseDistanceTypePanel.add(gzipRadioButton, gridBagConstraints);

        compressTypeButtonGroup.add(bzip2RadioButton);
        bzip2RadioButton.setText("bzip2");
        bzip2RadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bzip2RadioButton.setEnabled(false);
        bzip2RadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        chooseDistanceTypePanel.add(bzip2RadioButton, gridBagConstraints);

        spaceLabel1.setText("          ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        chooseDistanceTypePanel.add(spaceLabel1, gridBagConstraints);

        spaceLabel2.setText("          ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        chooseDistanceTypePanel.add(spaceLabel2, gridBagConstraints);

        distanceButtonGroup.add(distanceRadioButton);
        distanceRadioButton.setSelected(true);
        distanceRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distanceRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        chooseDistanceTypePanel.add(distanceRadioButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        chooseDistanceTypePanel.add(distanceComboBox, gridBagConstraints);

        distanceButtonGroup.add(ncdRadioButton);
        ncdRadioButton.setText("  Normal Compress Distance (NCD)");
        ncdRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ncdRadioButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        chooseDistanceTypePanel.add(ncdRadioButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(chooseDistanceTypePanel, gridBagConstraints);

        saveDistanceMatrixCheckBox.setText("Save the distance matrix");
        saveDistanceMatrixCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        saveDistanceMatrixCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        saveDistanceMatrixCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDistanceMatrixCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(saveDistanceMatrixCheckBox, gridBagConstraints);
    }
    
    // </editor-fold>//GEN-END:initComponents
    private void saveDistanceMatrixCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDistanceMatrixCheckBoxActionPerformed
        if (this.saveDistanceMatrixCheckBox.isSelected()) {
            String filename = this.pdata.getSourceFile();

            int result = SaveDialog.showSaveDialog(new DMATFilter(), this, filename);

            if (result == JFileChooser.APPROVE_OPTION) {
                filename = SaveDialog.getFilename();
                this.pdata.setDistanceMatrixFilename(filename);
            } else {
                this.saveDistanceMatrixCheckBox.setSelected(false);
            }
        } else {
            this.pdata.setDistanceMatrixFilename("");
        }
    }//GEN-LAST:event_saveDistanceMatrixCheckBoxActionPerformed

    private void distanceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distanceRadioButtonActionPerformed
        this.distanceComboBox.setEnabled(true);
        this.gzipRadioButton.setEnabled(false);
        this.bzip2RadioButton.setEnabled(false);
    }//GEN-LAST:event_distanceRadioButtonActionPerformed

    private void ncdRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ncdRadioButtonActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this,
                "The NCD distances calculation is a very expensive \n projectionData. " +
                "It will take several minutes!", "WARNING", javax.swing.JOptionPane.WARNING_MESSAGE);

        this.distanceComboBox.setEnabled(false);
        this.gzipRadioButton.setEnabled(true);
        this.bzip2RadioButton.setEnabled(true);
    }//GEN-LAST:event_ncdRadioButtonActionPerformed

    private void projComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projComboBoxActionPerformed
        this.reset();
    }//GEN-LAST:event_projComboBoxActionPerformed

    
    public void reset() {
        // Remove techniques which are not based on distance matrices
        if (pdata.getSourceType() == SourceType.DISTANCE_MATRIX) {
            projComboBox.removeAllItems();
            for (ProjectionType pt : ProjectionType.getTypes()) {
                if (pt != ProjectionType.NONE && pt.isDistanceBased()) {
                    projComboBox.addItem(pt);
                }
            }
        }

        ProjectionType type = (ProjectionType) projComboBox.getSelectedItem();
        if (type != null && type.isDistanceBased()) {
            distanceRadioButton.setEnabled(true);
            distanceComboBox.setEnabled(true);

            if (type.isAttributesNeeded()) {
                distanceRadioButton.setSelected(true);
                ncdRadioButton.setEnabled(false);
                gzipRadioButton.setEnabled(false);
                bzip2RadioButton.setEnabled(false);
            } else {
                if (pdata.getSourceType() == SourceType.CORPUS || pdata.getSourceType() == SourceType.BIBTEX) {
                    ncdRadioButton.setEnabled(true);
                    if (ncdRadioButton.isSelected()) {
                        gzipRadioButton.setEnabled(true);
                        bzip2RadioButton.setEnabled(true);
                    } else {
                        gzipRadioButton.setEnabled(false);
                        bzip2RadioButton.setEnabled(false);
                    }
                } else {
                    distanceRadioButton.setSelected(true);
                    ncdRadioButton.setEnabled(false);
                    gzipRadioButton.setEnabled(false);
                    bzip2RadioButton.setEnabled(false);
                }
            }

            if (pdata.getSourceType() == SourceType.DISTANCE_MATRIX) {
                distanceComboBox.setEnabled(false);
                distanceRadioButton.setEnabled(false);
                ncdRadioButton.setEnabled(false);
                gzipRadioButton.setEnabled(false);
                bzip2RadioButton.setEnabled(false);
            }
        } else {
            distanceComboBox.setEnabled(false);
            distanceRadioButton.setEnabled(false);
            ncdRadioButton.setEnabled(false);
            gzipRadioButton.setEnabled(false);
            bzip2RadioButton.setEnabled(false);
        }

        if (type != null && type.isGenerateDistanceMatrix() && (pdata.getSourceType() == SourceType.POINTS || pdata.getSourceType() == SourceType.CORPUS || pdata.getSourceType() == SourceType.BIBTEX)) {
            saveDistanceMatrixCheckBox.setEnabled(true);
        } else {
            saveDistanceMatrixCheckBox.setEnabled(false);
        }
    }

    @Override
    public void refreshData() {
        //Setting the projection type
        ProjectionType projtype = (ProjectionType) projComboBox.getSelectedItem();
        pdata.setProjectionType(projtype);

        //Setting the type of distance
        if (ncdRadioButton.isSelected()) {
            pdata.setDissimilarityType(DissimilarityType.KOLMOGOROV);

            //Setting the compressor type
            if (gzipRadioButton.isSelected()) {
                pdata.setCompressorType(CompressorType.GZIP);
            } else {
                pdata.setCompressorType(CompressorType.BZIP2);
            }
        } else {
            DissimilarityType metrictype = (DissimilarityType) this.distanceComboBox.getSelectedItem();
            pdata.setDissimilarityType(metrictype);
        }
    }
}
