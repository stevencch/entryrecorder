/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.frame;

import com.cch.aj.entryrecorder.common.AppContext;
import com.cch.aj.entryrecorder.common.AppHelper;
import com.cch.aj.entryrecorder.entities.Checkitem;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.services.EntrySearchService;
import com.cch.aj.entryrecorder.services.impl.EntrySearchServiceImpl;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author chacao
 */
public class SearchJFrame extends javax.swing.JFrame implements ListSelectionListener {

    EntrySearchService entrySearchService=new EntrySearchServiceImpl(Entry.class);
    /**
     * Creates new form SearchJFrame
     */
    public SearchJFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ListSelectionModel model = tblSearch.getSelectionModel();
        model.addListSelectionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtShift = new javax.swing.JTextField();
        txtProduct = new javax.swing.JTextField();
        txtBatch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jPanel18.setLayout(new java.awt.GridBagLayout());

        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EntryId", "Shift", "Product Code", "Created Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSearch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel18.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        jPanel17.add(jPanel18, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(btnSearch, gridBagConstraints);

        jLabel2.setText("SHIFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 9, 7);
        jPanel19.add(jLabel2, gridBagConstraints);

        jLabel3.setText("PRODUCT CODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 9, 7);
        jPanel19.add(jLabel3, gridBagConstraints);

        jLabel4.setText("BATCH NUMBER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 9, 7);
        jPanel19.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtShift, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtProduct, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtBatch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        jPanel17.add(jPanel19, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        List<Entry> list=this.entrySearchService.Search(txtShift.getText(), txtProduct.getText(),txtBatch.getText());
        DefaultTableModel model = (DefaultTableModel) this.tblSearch.getModel();
        for(Entry entry:list){
            model.addRow(new Object[]{entry.getId(),entry.getShift(),entry.getProductId().getCode(),(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(entry.getCreateDate())});
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtBatch;
    private javax.swing.JTextField txtProduct;
    private javax.swing.JTextField txtShift;
    // End of variables declaration//GEN-END:variables

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && tblSearch.getSelectedRow() != -1) {
            TableModel model = tblSearch.getModel();
            int checkId = (int) model.getValueAt(tblSearch.getSelectedRow(), 0);
            MainJFrame mf = AppContext.getApplicationContext().getBean("MainJFrame", MainJFrame.class);
            mf.init(checkId);
            mf.setVisible(true);
        }
    }
}
