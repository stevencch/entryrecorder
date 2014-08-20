/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.frame;

import com.cch.aj.entryrecorder.common.AppHelper;
import com.cch.aj.entryrecorder.common.ComboBoxItem;
import com.cch.aj.entryrecorder.common.ComboBoxItemConvertor;
import com.cch.aj.entryrecorder.common.ComboBoxRender;
import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.services.RecordSettingService;
import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.RecordSettingServiceImpl;
import com.cch.aj.entryrecorder.services.impl.RecordValidationServiceImpl;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Administrator
 */
public class MainJFrame extends javax.swing.JFrame {

    private RecordValidationService recordValidationService = new RecordValidationServiceImpl();
    private RecordSettingService recordService = new RecordSettingServiceImpl(Record.class);
    private SettingService<Staff> staffService = new SettingServiceImpl<Staff>(Staff.class);
    private SettingService<Entry> entryService = new SettingServiceImpl<Entry>(Entry.class);
    private SettingService<Machine> machineService = new SettingServiceImpl<Machine>(Machine.class);
    private Entry currentEntry = null;

    DefaultCategoryDataset datasetWeight = new DefaultCategoryDataset();

    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        new SettingsJFrame().setVisible(true);

        //load entry
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        this.cbEntry.setRenderer(new ComboBoxRender());
        this.cbWeightStaff.setRenderer(new ComboBoxRender());
        FillEntryComboBox(this.cbEntry, 0);

        if (this.currentEntry != null) {
            UpdateEntryForm();
        }
    }

    private void UpdateEntryForm() {
        //info
        UpdateProductInfo(this.currentEntry);
        //weight
        this.labWeightStaff.setVisible(false);
        this.cbWeightStaff.setVisible(false);
        FillStaffComboBox(this.cbWeightStaff, 0);
        List<Record> records = this.recordService.GetAllEntitiesByKey(RecordKey.PRODUCT_WEIGHT);
        DefaultTableModel model = (DefaultTableModel) this.tblWeight.getModel();
        for (Record record : records) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaffId() == null ? "" : record.getStaffId().getName();
            datasetWeight.addValue(record.getNumberValue(), "Weight", time);
            model.addRow(new Object[]{time, record.getNumberValue(), staff});
        }
        ((AbstractTableModel) this.tblWeight.getModel()).fireTableDataChanged();
        JFreeChart chartWeight = ChartFactory.createLineChart(
                "Product Weight (kg)", "", "",
                datasetWeight, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cp = new ChartPanel(chartWeight);
        this.pnlChartWeight.add(cp, gridBagConstraints);
    }

    private int FillStaffComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Staff> staffs = this.staffService.GetAllEntities();
        if (staffs.size() > 0) {
            List<ComboBoxItem<Staff>> staffNames = staffs.stream().filter(x->x.getJobType().equals("SUPERVISOR")).sorted(comparing(x -> x.getName())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getName(), x.getId())).collect(Collectors.toList());
            Staff staff = new Staff();
            staff.setId(0);
            staff.setName("- Select -");
            staffNames.add(0, new ComboBoxItem<Staff>(staff, staff.getName(), staff.getId()));
            ComboBoxItem[] staffNamesArray = staffNames.toArray(new ComboBoxItem[staffNames.size()]);
            comboBox.setModel(new DefaultComboBoxModel(staffNamesArray));
            if (id != 0) {
                ComboBoxItem<Staff> currentStaffName = staffNames.stream().filter(x -> x.getId() == id).findFirst().get();
                result = staffNames.indexOf(currentStaffName);
            } else {
                result = 0;
            }
            comboBox.setSelectedIndex(result);
        }
        return result;
    }

    private int FillEntryComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Entry> allEntrys = this.entryService.GetAllEntities();
        if (allEntrys.size() > 0) {
            List<Entry> entrys = allEntrys.stream().filter(x -> x.getInUse().equals("YES")).collect(Collectors.toList());
            if (entrys.size() > 0) {
                List<ComboBoxItem<Entry>> entryNames = entrys.stream().sorted(comparing(x -> x.getCreateDate())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getShift() + "-" + x.getMachineId() != null ? x.getMachineId().getMachineNo() : "", x.getId())).collect(Collectors.toList());
                ComboBoxItem[] entryNamesArray = entryNames.toArray(new ComboBoxItem[entryNames.size()]);
                comboBox.setModel(new DefaultComboBoxModel(entryNamesArray));
                if (id != 0) {
                    ComboBoxItem<Entry> currentEntryName = entryNames.stream().filter(x -> x.getId() == id).findFirst().get();
                    result = entryNames.indexOf(currentEntryName);
                } else {
                    result = 0;
                }
                comboBox.setSelectedIndex(result);
                this.currentEntry = ((ComboBoxItem<Entry>) comboBox.getSelectedItem()).getItem();
            }
        }
        return result;
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
        cbEntry = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtProductCode = new javax.swing.JLabel();
        txtProductColor = new javax.swing.JLabel();
        txtProductWeight = new javax.swing.JLabel();
        txtProductPierced = new javax.swing.JLabel();
        txtProductDesc = new javax.swing.JLabel();
        txtProductBung = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlMouldImage = new javax.swing.JPanel();
        labProductImage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWeight = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();
        labWeightStaff = new javax.swing.JLabel();
        cbWeightStaff = new javax.swing.JComboBox();
        btnWeight = new javax.swing.JButton();
        pnlChartWeight = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1150, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Machine No"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        cbEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cbEntry, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Information"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("CODE:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel5, gridBagConstraints);

        txtProductCode.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductCode, gridBagConstraints);

        txtProductColor.setText("jLabel12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductColor, gridBagConstraints);

        txtProductWeight.setText("jLabel10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductWeight, gridBagConstraints);

        txtProductPierced.setText("jLabel16");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductPierced, gridBagConstraints);

        txtProductDesc.setText("jLabel8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductDesc, gridBagConstraints);

        txtProductBung.setText("jLabel14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductBung, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("COLOUR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel11, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel7, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("BUNG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel13, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("WEIGHT RANGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel9, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("PIERCED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel15, gridBagConstraints);

        pnlMouldImage.setLayout(new java.awt.GridBagLayout());

        labProductImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/no_photo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        pnlMouldImage.add(labProductImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(pnlMouldImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Recorder"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1224, 426));

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel16.setLayout(new java.awt.GridBagLayout());

        tblWeight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value", "Staff"
            }
        ));
        jScrollPane1.setViewportView(tblWeight);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel16.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        jPanel5.add(jPanel16, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel21.setText("Product Weight (kg)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel14.add(jLabel21, gridBagConstraints);

        txtWeight.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel14.add(txtWeight, gridBagConstraints);

        labWeightStaff.setText("Staff Check");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 28, 4, 28);
        jPanel14.add(labWeightStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 28, 4, 28);
        jPanel14.add(cbWeightStaff, gridBagConstraints);

        btnWeight.setText("Add");
        btnWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWeightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 5, 0);
        jPanel14.add(btnWeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        jPanel5.add(jPanel14, gridBagConstraints);

        pnlChartWeight.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlChartWeight.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(pnlChartWeight, gridBagConstraints);

        jTabbedPane1.addTab("Weight", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Wall Thickness", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tap Poistion/Tightness", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Bore/ Neck", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quality Random Check", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Drop Test", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Staff", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Raw Material", jPanel12);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quantity Produced", jPanel13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 95;
        gridBagConstraints.ipady = 95;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setText("A & J Entry Recorder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel4.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeightActionPerformed
        Boolean isSave = false;
        Staff staff=null;
        if (this.currentEntry != null) {
            if (NumberUtils.isNumber(this.txtWeight.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.PRODUCT_WEIGHT, Float.parseFloat(this.txtWeight.getText()))) {
                    isSave = true;
                } else {
                    if (this.cbWeightStaff.getSelectedItem() != null && this.cbWeightStaff.getSelectedIndex() != 0) {
                        isSave = true;
                        staff = ((ComboBoxItem<Staff>) this.cbWeightStaff.getSelectedItem()).getItem();
                        this.cbWeightStaff.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(this, "the value is over the limit, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labWeightStaff.setVisible(true);
                        this.cbWeightStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please entry a valid number.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblWeight.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = Float.parseFloat(this.txtWeight.getText());
                model.addRow(new Object[]{time, value, staff!=null?staff.getName():""});
                ((AbstractTableModel) this.tblWeight.getModel()).fireTableDataChanged();
                datasetWeight.addValue(value, "Weight", time);
                this.labWeightStaff.setVisible(false);
                this.cbWeightStaff.setVisible(false);
                this.txtWeight.setText("");
                //
                int recordId=this.recordService.CreateEntity();
                Record record=this.recordService.FindEntity(recordId);
                record.setEntryId(this.currentEntry);
                record.setCreatedTime(now);
                record.setNumberValue(value);
                record.setRecordKey(RecordKey.PRODUCT_WEIGHT.toString());
                if(staff!=null){
                    record.setStaffId(staff);
                }
                this.recordService.UpdateEntity(record);
            }
        }
    }//GEN-LAST:event_btnWeightActionPerformed

    private void cbEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryActionPerformed
        Entry currentItem = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
        UpdateProductInfo(currentItem);
    }//GEN-LAST:event_cbEntryActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnWeight;
    private javax.swing.JComboBox cbEntry;
    private javax.swing.JComboBox cbWeightStaff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labProductImage;
    private javax.swing.JLabel labWeightStaff;
    private javax.swing.JPanel pnlChartWeight;
    private javax.swing.JPanel pnlMouldImage;
    private javax.swing.JTable tblWeight;
    private javax.swing.JLabel txtProductBung;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProductColor;
    private javax.swing.JLabel txtProductDesc;
    private javax.swing.JLabel txtProductPierced;
    private javax.swing.JLabel txtProductWeight;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables

    private void UpdateProductInfo(Entry currentEntry) {
        if (currentEntry.getMouldId().getImageDrawing() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageDrawing(), this.pnlMouldImage, 150);
        } else {
            AppHelper.DisplayImage("/images/no_photo.png", this.pnlMouldImage, 150);
        }
        this.txtProductBung.setText(currentEntry.getProductId().getBung());
        this.txtProductCode.setText(currentEntry.getProductId().getCode());
        this.txtProductColor.setText("NA");
        this.txtProductDesc.setText(currentEntry.getProductId().getDescription());
        this.txtProductPierced.setText(currentEntry.getProductId().getPierced());
        this.txtProductWeight.setText(currentEntry.getProductId().getWeightMin() + " - " + currentEntry.getProductId().getWeightMax());
    }
}
