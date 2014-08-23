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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component("MainJFrame")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MainJFrame extends javax.swing.JFrame {

    //private RecordValidationService recordValidationService = new RecordValidationServiceImpl();
    @Autowired
    private RecordValidationService recordValidationService;
    private RecordSettingService recordService = new RecordSettingServiceImpl(Record.class);
    private SettingService<Staff> staffService = new SettingServiceImpl<Staff>(Staff.class);
    private SettingService<Entry> entryService = new SettingServiceImpl<Entry>(Entry.class);
    private Entry currentEntry = null;

    DefaultCategoryDataset datasetWeight = new DefaultCategoryDataset();
    DefaultCategoryDataset datasetTap = new DefaultCategoryDataset();

    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //load entry
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        this.cbEntry.setRenderer(new ComboBoxRender());
        FillEntryComboBox(this.cbEntry, 0);

        if (this.currentEntry != null) {
            UpdateEntryForm();
        }
    }

    private void UpdateEntryForm() {
        List<Record> records = this.recordService.GetAllEntitiesByKeyAndRecord(RecordKey.ALL, this.currentEntry.getId());
        //info
        UpdateProductInfo(this.currentEntry);
        //weight
        this.labWeightStaff.setVisible(false);
        this.txtWeightStaff.setVisible(false);
        datasetWeight = new DefaultCategoryDataset();

        List<Record> recordsWeight = records.stream().filter(x -> x.getRecordKey().equals("PRODUCT_WEIGHT")).collect(Collectors.toList());
        DefaultTableModel modelWeight = (DefaultTableModel) this.tblWeight.getModel();
        modelWeight.setRowCount(0);
        for (Record record : recordsWeight) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            datasetWeight.addValue(record.getNumberValue(), "Weight", time);
            modelWeight.addRow(new Object[]{time, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblWeight.getModel()).fireTableDataChanged();
        JFreeChart chartWeight = ChartFactory.createLineChart(
                "Product Weight (kg)", "", "",
                datasetWeight, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cpWeight = new ChartPanel(chartWeight);
        this.pnlChartWeight.removeAll();
        this.pnlChartWeight.add(cpWeight, gridBagConstraints);
        //wall
        this.labWallStaff.setVisible(false);
        this.txtWallStaff.setVisible(false);

        List<Record> recordsWall = records.stream().filter(x -> x.getRecordKey().startsWith("WALL_")).collect(Collectors.toList());
        DefaultTableModel modelWall = (DefaultTableModel) this.tblWall.getModel();
        modelWall.setRowCount(0);
        for (Record record : recordsWall) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            String name = record.getRecordKey();
            modelWall.addRow(new Object[]{time, name, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblWall.getModel()).fireTableDataChanged();
        //Tap
        this.labTapStaff.setVisible(false);
        this.txtTapStaff.setVisible(false);
        datasetTap = new DefaultCategoryDataset();

        List<Record> recordsTap = records.stream().filter(x -> x.getRecordKey().equals("TAP_POSITION")).collect(Collectors.toList());
        DefaultTableModel modelTap = (DefaultTableModel) this.tblTap.getModel();
        modelTap.setRowCount(0);
        for (Record record : recordsTap) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            datasetTap.addValue(record.getNumberValue(), "Tap", time);
            modelTap.addRow(new Object[]{time, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblTap.getModel()).fireTableDataChanged();
        JFreeChart chartTap = ChartFactory.createLineChart(
                "Product Tap (kg)", "", "",
                datasetTap, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cpTap = new ChartPanel(chartTap);
        this.pnlChartTap.removeAll();
        this.pnlChartTap.add(cpTap, gridBagConstraints);
        //Bore
        this.labBoreStaff.setVisible(false);
        this.txtBoreStaff.setVisible(false);

        List<Record> recordsBore = records.stream().filter(x -> x.getRecordKey().startsWith("THREAD_")).collect(Collectors.toList());
        DefaultTableModel modelBore = (DefaultTableModel) this.tblBore.getModel();
        modelBore.setRowCount(0);
        for (Record record : recordsBore) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            String name = record.getRecordKey();
            modelBore.addRow(new Object[]{time, name, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblBore.getModel()).fireTableDataChanged();
        //Check
        this.labCheckStaff.setVisible(false);
        this.txtCheckStaff.setVisible(false);

        List<Record> recordsCheck = records.stream().filter(x -> x.getRecordKey().startsWith("CHECK_")).collect(Collectors.toList());
        DefaultTableModel modelCheck = (DefaultTableModel) this.tblCheck.getModel();
        modelCheck.setRowCount(0);
        for (Record record : recordsCheck) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            String name = record.getRecordKey();
            modelCheck.addRow(new Object[]{time, name, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblCheck.getModel()).fireTableDataChanged();
        //Drop
        this.labDropStaff.setVisible(false);
        this.txtDropStaff.setVisible(false);

        List<Record> recordsDrop = records.stream().filter(x -> x.getRecordKey().startsWith("DROP_")).collect(Collectors.toList());
        DefaultTableModel modelDrop = (DefaultTableModel) this.tblDrop.getModel();
        modelDrop.setRowCount(0);
        for (Record record : recordsDrop) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            String name = record.getRecordKey();
            modelDrop.addRow(new Object[]{time, name, record.getNumberValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblDrop.getModel()).fireTableDataChanged();
    }

    private int FillEntryComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Entry> allEntrys = this.entryService.GetAllEntities();
        if (allEntrys.size() > 0) {
            List<Entry> entrys = allEntrys.stream().filter(x -> x.getInUse().equals("YES")).collect(Collectors.toList());
            if (entrys.size() > 0) {
                List<ComboBoxItem<Entry>> entryNames = entrys.stream().sorted(comparing(x -> x.getCreateDate())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getShift() + "-" + (x.getMachineId() != null ? x.getMachineId().getMachineNo() : ""), x.getId())).collect(Collectors.toList());
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
        jLabel38 = new javax.swing.JLabel();
        txtProductGrade = new javax.swing.JLabel();
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
        txtWeightStaff = new javax.swing.JTextField();
        labWeightStaff = new javax.swing.JLabel();
        btnWeight = new javax.swing.JButton();
        txtWeight = new javax.swing.JTextField();
        pnlChartWeight = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblWall = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtWallStaff = new javax.swing.JTextField();
        labWallStaff = new javax.swing.JLabel();
        btnWall = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtWallUnderHandle = new javax.swing.JTextField();
        txtWallBase = new javax.swing.JTextField();
        txtWallClosure = new javax.swing.JTextField();
        txtWallHandleBung = new javax.swing.JTextField();
        txtWallHandleLeft = new javax.swing.JTextField();
        txtWallHandleRight = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTap = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        labTapStaff = new javax.swing.JLabel();
        btnTap = new javax.swing.JButton();
        cbTap = new javax.swing.JComboBox();
        txtTapStaff = new javax.swing.JTextField();
        pnlChartTap = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBore = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        labBoreStaff = new javax.swing.JLabel();
        btnBore = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtBore1 = new javax.swing.JTextField();
        txtBore2 = new javax.swing.JTextField();
        txtBoreStaff = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtNeck = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCheck = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        labCheckStaff = new javax.swing.JLabel();
        btnCheck = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbNeckRound = new javax.swing.JComboBox();
        cbNeckComplete = new javax.swing.JComboBox();
        cbUnderTheHandle = new javax.swing.JComboBox();
        cbBungIfDrilled = new javax.swing.JComboBox();
        cbBase = new javax.swing.JComboBox();
        cbStrengthOfDrum = new javax.swing.JComboBox();
        cbWeightWithinRange = new javax.swing.JComboBox();
        cbColourTexture = new javax.swing.JComboBox();
        txtCheckStaff = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDrop = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        labDropStaff = new javax.swing.JLabel();
        btnDrop = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbDrop1 = new javax.swing.JComboBox();
        cbDrop2 = new javax.swing.JComboBox();
        cbDrop3 = new javax.swing.JComboBox();
        cbDrop4 = new javax.swing.JComboBox();
        cbDrop5 = new javax.swing.JComboBox();
        cbDrop6 = new javax.swing.JComboBox();
        cbDrop7 = new javax.swing.JComboBox();
        cbDrop8 = new javax.swing.JComboBox();
        txtDropStaff = new javax.swing.JTextField();
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
        gridBagConstraints.gridy = 9;
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
        gridBagConstraints.gridy = 13;
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
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductBung, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("COLOUR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
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
        gridBagConstraints.gridy = 10;
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
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel15, gridBagConstraints);

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setText("GRADE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel38, gridBagConstraints);

        txtProductGrade.setText("jLabel12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(txtProductGrade, gridBagConstraints);

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
                "Time", "Value", "Pass", "Staff"
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
        gridBagConstraints.weightx = 0.7;
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

        txtWeightStaff.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel14.add(txtWeightStaff, gridBagConstraints);

        labWeightStaff.setText("Check By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(4, 28, 4, 28);
        jPanel14.add(labWeightStaff, gridBagConstraints);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
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

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jPanel18.setLayout(new java.awt.GridBagLayout());

        tblWall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Name", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane2.setViewportView(tblWall);

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
        gridBagConstraints.weightx = 0.7;
        jPanel17.add(jPanel18, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jLabel22.setText("END OF HANDLE SIDE-RIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel22, gridBagConstraints);

        txtWallStaff.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallStaff, gridBagConstraints);

        labWallStaff.setText("Record By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(labWallStaff, gridBagConstraints);

        btnWall.setText("Add");
        btnWall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWallActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(btnWall, gridBagConstraints);

        jLabel2.setText("UNDER THE HANDLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel2, gridBagConstraints);

        jLabel3.setText("BASE (CENTRE)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel3, gridBagConstraints);

        jLabel4.setText("CLOSURE SIDE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel4, gridBagConstraints);

        jLabel6.setText("END OF HANDLE SIDE-BUNG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel6, gridBagConstraints);

        jLabel8.setText("END OF HANDLE SIDE-LEFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallUnderHandle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallBase, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallClosure, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleBung, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleLeft, gridBagConstraints);

        txtWallHandleRight.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel17.add(jPanel19, gridBagConstraints);

        jTabbedPane1.addTab("Wall Thickness", jPanel17);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel20.setLayout(new java.awt.GridBagLayout());

        tblTap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane3.setViewportView(tblTap);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel20.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel6.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jLabel23.setText("Tap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(jLabel23, gridBagConstraints);

        labTapStaff.setText("Check By");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(labTapStaff, gridBagConstraints);

        btnTap.setText("Add");
        btnTap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(btnTap, gridBagConstraints);

        cbTap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9;00", "10:00", "11:00", "12:00" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(cbTap, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(txtTapStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel6.add(jPanel21, gridBagConstraints);

        pnlChartTap.setPreferredSize(new java.awt.Dimension(400, 400));
        pnlChartTap.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(pnlChartTap, gridBagConstraints);

        jTabbedPane1.addTab("Tap Position / Tightness", jPanel6);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jPanel23.setLayout(new java.awt.GridBagLayout());

        tblBore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Name", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane4.setViewportView(tblBore);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel23.add(jScrollPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel22.add(jPanel23, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        labBoreStaff.setText("Staff Check");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(13, 27, 3, 27);
        jPanel24.add(labBoreStaff, gridBagConstraints);

        btnBore.setText("Add");
        btnBore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(btnBore, gridBagConstraints);

        jLabel10.setText("BORE DIAMETRE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(jLabel10, gridBagConstraints);

        jLabel12.setText("BORE DIAMETRE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(jLabel12, gridBagConstraints);

        jLabel14.setText("NECK HEIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(jLabel14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(txtBore1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(txtBore2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 27, 3, 27);
        jPanel24.add(txtBoreStaff, gridBagConstraints);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel24.add(jLabel35, gridBagConstraints);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b2.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel24.add(jLabel36, gridBagConstraints);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b3.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel24.add(jLabel37, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(txtNeck, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel22.add(jPanel24, gridBagConstraints);

        jTabbedPane1.addTab("Bore / Neck", jPanel22);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jPanel26.setLayout(new java.awt.GridBagLayout());

        tblCheck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Name", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane5.setViewportView(tblCheck);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel26.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel25.add(jPanel26, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        jLabel24.setText("STRENGTH OF DRUM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel24, gridBagConstraints);

        labCheckStaff.setText("Staff Check");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(labCheckStaff, gridBagConstraints);

        btnCheck.setText("Add");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(btnCheck, gridBagConstraints);

        jLabel16.setText("NECK ROUND");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel16, gridBagConstraints);

        jLabel17.setText("NECK COMPLETE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel17, gridBagConstraints);

        jLabel18.setText("UNDER THE HANDLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel18, gridBagConstraints);

        jLabel19.setText("BUNG, IF DRILLED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel19, gridBagConstraints);

        jLabel20.setText("BASE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel20, gridBagConstraints);

        jLabel25.setText("WEIGHT WITHIN RANGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel25, gridBagConstraints);

        jLabel26.setText("COLOUR / TEXTURE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel26, gridBagConstraints);

        cbNeckRound.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbNeckRound, gridBagConstraints);

        cbNeckComplete.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbNeckComplete, gridBagConstraints);

        cbUnderTheHandle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbUnderTheHandle, gridBagConstraints);

        cbBungIfDrilled.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbBungIfDrilled, gridBagConstraints);

        cbBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbBase, gridBagConstraints);

        cbStrengthOfDrum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbStrengthOfDrum, gridBagConstraints);

        cbWeightWithinRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbWeightWithinRange, gridBagConstraints);

        cbColourTexture.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Checked", "NA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(cbColourTexture, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(txtCheckStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel25.add(jPanel27, gridBagConstraints);

        jTabbedPane1.addTab("Quanlity Check", jPanel25);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        jPanel29.setLayout(new java.awt.GridBagLayout());

        tblDrop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Name", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane6.setViewportView(tblDrop);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel29.add(jScrollPane6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel28.add(jPanel29, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p6.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel27, gridBagConstraints);

        labDropStaff.setText("Staff Check");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(labDropStaff, gridBagConstraints);

        btnDrop.setText("Add");
        btnDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(btnDrop, gridBagConstraints);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p1.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel28, gridBagConstraints);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p2.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel29, gridBagConstraints);

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p3.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel30, gridBagConstraints);

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p4.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel31, gridBagConstraints);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p5.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel32, gridBagConstraints);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p7.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel33, gridBagConstraints);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p8.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(jLabel34, gridBagConstraints);

        cbDrop1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop1, gridBagConstraints);

        cbDrop2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop2, gridBagConstraints);

        cbDrop3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop3, gridBagConstraints);

        cbDrop4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop4, gridBagConstraints);

        cbDrop5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop5, gridBagConstraints);

        cbDrop6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop6, gridBagConstraints);

        cbDrop7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop7, gridBagConstraints);

        cbDrop8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Pass", "Fail" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(cbDrop8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel30.add(txtDropStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(jPanel30, gridBagConstraints);

        jTabbedPane1.addTab("Drop Test", jPanel28);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
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
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (NumberUtils.isNumber(this.txtWeight.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.PRODUCT_WEIGHT, Float.parseFloat(this.txtWeight.getText()))) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtWeightStaff.getText().equals("")) {
                        isSave = true;

                        staff = this.txtWeightStaff.getText();
                        this.txtWeightStaff.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "the value is over the limit, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labWeightStaff.setVisible(true);
                        this.txtWeightStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblWeight.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = Float.parseFloat(this.txtWeight.getText());
                model.addRow(new Object[]{time, value,pass, staff});
                ((AbstractTableModel) this.tblWeight.getModel()).fireTableDataChanged();
                datasetWeight.addValue(value, "Weight", time);
                this.labWeightStaff.setVisible(false);
                this.txtWeightStaff.setVisible(false);
                this.txtWeightStaff.setText("");
                UpdateEntryData(now, value, RecordKey.PRODUCT_WEIGHT, staff, pass);
            }
        }
    }//GEN-LAST:event_btnWeightActionPerformed

    private void cbEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryActionPerformed
        this.currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
        this.UpdateEntryForm();
    }//GEN-LAST:event_cbEntryActionPerformed

    private void btnWallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWallActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (NumberUtils.isNumber(this.txtWallBase.getText()) && NumberUtils.isNumber(this.txtWallHandleBung.getText())
                    && NumberUtils.isNumber(this.txtWallClosure.getText()) && NumberUtils.isNumber(this.txtWallHandleLeft.getText())
                    && NumberUtils.isNumber(this.txtWallHandleRight.getText()) && NumberUtils.isNumber(this.txtWallUnderHandle.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_BASE, Float.parseFloat(this.txtWallBase.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_CLOSURE, Float.parseFloat(this.txtWallClosure.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_BUNG, Float.parseFloat(this.txtWallHandleBung.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_LEFT, Float.parseFloat(this.txtWallHandleLeft.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_RIGHT, Float.parseFloat(this.txtWallStaff.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_UNDER_HANDLE, Float.parseFloat(this.txtWallUnderHandle.getText()))) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtWallStaff.getText().equals("")) {
                        isSave = true;
                        staff = this.txtWallStaff.getText();
                        this.txtWallStaff.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "the value is over the limit, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labWallStaff.setVisible(true);
                        this.txtWallStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblWall.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float valueUnderHandle = Float.parseFloat(this.txtWallUnderHandle.getText());
                Float valueBase = Float.parseFloat(this.txtWallBase.getText());
                Float valueClosure = Float.parseFloat(this.txtWallClosure.getText());
                Float valueHandleBung = Float.parseFloat(this.txtWallHandleBung.getText());
                Float valueHandleLeft = Float.parseFloat(this.txtWallHandleLeft.getText());
                Float valueHandleRight = Float.parseFloat(this.txtWallStaff.getText());
                model.addRow(new Object[]{time, RecordKey.WALL_UNDER_HANDLE, valueUnderHandle, pass, staff});
                model.addRow(new Object[]{time, RecordKey.WALL_BASE, valueBase, pass, staff});
                model.addRow(new Object[]{time, RecordKey.WALL_CLOSURE, valueClosure, pass, staff});
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_BUNG, valueHandleBung, pass, staff});
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_LEFT, valueHandleLeft, pass, staff});
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_RIGHT, valueHandleRight, pass, staff});

                ((AbstractTableModel) this.tblWall.getModel()).fireTableDataChanged();
                this.labWallStaff.setVisible(false);
                this.txtWallStaff.setVisible(false);
                this.txtWallUnderHandle.setText("");
                this.txtWallBase.setText("");
                this.txtWallClosure.setText("");
                this.txtWallHandleBung.setText("");
                this.txtWallHandleLeft.setText("");
                this.txtWallStaff.setText("");
                //
                UpdateEntryData(now, valueUnderHandle, RecordKey.WALL_UNDER_HANDLE, staff, pass);
                UpdateEntryData(now, valueBase, RecordKey.WALL_BASE, staff, pass);
                UpdateEntryData(now, valueClosure, RecordKey.WALL_CLOSURE, staff, pass);
                UpdateEntryData(now, valueHandleBung, RecordKey.WALL_HANDLE_BUNG, staff, pass);
                UpdateEntryData(now, valueHandleLeft, RecordKey.WALL_HANDLE_LEFT, staff, pass);
                UpdateEntryData(now, valueHandleRight, RecordKey.WALL_HANDLE_RIGHT, staff, pass);
            }
        }
    }//GEN-LAST:event_btnWallActionPerformed

    private void btnTapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTapActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (this.cbTap.getSelectedIndex() != 0) {
                if (recordValidationService.Validate(currentEntry, RecordKey.PRODUCT_WEIGHT, (float) this.cbTap.getSelectedIndex())) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtTapStaff.getText().equals("")) {
                        isSave = true;
                        staff = txtTapStaff.getText();
                        this.txtTapStaff.setText("");

                    } else {
                        JOptionPane.showMessageDialog(this, "the value is over the limit, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labTapStaff.setVisible(true);
                        this.txtTapStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select the tap position.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblTap.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = (float) this.cbTap.getSelectedIndex();
                model.addRow(new Object[]{time, value, pass, staff});
                ((AbstractTableModel) this.tblTap.getModel()).fireTableDataChanged();
                datasetTap.addValue(value, "Tap", time);
                this.labTapStaff.setVisible(false);
                this.txtTapStaff.setVisible(false);
                this.cbTap.setSelectedIndex(0);
                UpdateEntryData(now, value, RecordKey.TAP_POSITION, staff, pass);
            }
        }
    }//GEN-LAST:event_btnTapActionPerformed

    private void btnBoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoreActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (NumberUtils.isNumber(this.txtBore1.getText()) && NumberUtils.isNumber(this.txtBore2.getText())
                    && NumberUtils.isNumber(this.txtNeck.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE, Float.parseFloat(this.txtBore1.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE, Float.parseFloat(this.txtBore2.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.THREAD_NECK, Float.parseFloat(this.txtBoreStaff.getText()))) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtBoreStaff.getText().equals("")) {
                        isSave = true;
                        staff = this.txtBoreStaff.getText();
                        this.txtBoreStaff.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "the value is over the limit, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labBoreStaff.setVisible(true);
                        this.txtBoreStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblBore.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float valueBore1 = Float.parseFloat(this.txtBore1.getText());
                Float valueBore2 = Float.parseFloat(this.txtBore2.getText());
                Float valueNeck = Float.parseFloat(this.txtBoreStaff.getText());
                model.addRow(new Object[]{time, RecordKey.THREAD_BORE1, valueBore1, pass, staff});
                model.addRow(new Object[]{time, RecordKey.THREAD_BORE2, valueBore2, pass, staff});
                model.addRow(new Object[]{time, RecordKey.THREAD_NECK, valueNeck, pass, staff});

                ((AbstractTableModel) this.tblBore.getModel()).fireTableDataChanged();
                this.labBoreStaff.setVisible(false);
                this.txtBoreStaff.setVisible(false);
                this.txtBore1.setText("");
                this.txtBore2.setText("");
                this.txtBoreStaff.setText("");
                //
                UpdateEntryData(now, valueBore1, RecordKey.THREAD_BORE1, staff, pass);
                UpdateEntryData(now, valueBore2, RecordKey.THREAD_BORE2, staff, pass);
                UpdateEntryData(now, valueNeck, RecordKey.THREAD_NECK, staff, pass);
            }
        }
    }//GEN-LAST:event_btnBoreActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (this.cbNeckRound.getSelectedIndex() != 0 && this.cbNeckComplete.getSelectedIndex() != 0 && this.cbUnderTheHandle.getSelectedIndex() != 0
                    && this.cbBungIfDrilled.getSelectedIndex() != 0 && this.cbBase.getSelectedIndex() != 0 && this.cbStrengthOfDrum.getSelectedIndex() != 0
                    && this.cbWeightWithinRange.getSelectedIndex() != 0 && this.cbColourTexture.getSelectedIndex() != 0) {
                if (this.cbNeckRound.getSelectedIndex() != 2 && this.cbNeckComplete.getSelectedIndex() != 2 && this.cbUnderTheHandle.getSelectedIndex() != 2
                        && this.cbBungIfDrilled.getSelectedIndex() != 2 && this.cbBase.getSelectedIndex() != 2 && this.cbStrengthOfDrum.getSelectedIndex() != 2
                        && this.cbWeightWithinRange.getSelectedIndex() != 2 && this.cbColourTexture.getSelectedIndex() != 2) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtCheckStaff.getText().equals("")) {
                        isSave = true;
                        staff = this.txtCheckStaff.getText();
                        this.txtCheckStaff.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Fail to pass all the checks, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labCheckStaff.setVisible(true);
                        this.txtCheckStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please complete all the checks.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblCheck.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                model.addRow(new Object[]{time, RecordKey.CHECK_NECK_ROUND, this.cbNeckRound.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_NECK_COMPLETE, this.cbNeckComplete.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_UNDER_THE_HANDLE, this.cbUnderTheHandle.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_BUNG_IF_DRILLED, this.cbBungIfDrilled.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_BASE, this.cbBase.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_STRENGTH_OF_DRUM, this.cbStrengthOfDrum.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_WEIGHT_WITHIN_RANGE, this.cbWeightWithinRange.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.CHECK_COLOUR_TEXTURE, this.cbColourTexture.getSelectedItem(), pass, staff});

                ((AbstractTableModel) this.tblCheck.getModel()).fireTableDataChanged();
                this.labCheckStaff.setVisible(false);
                this.txtCheckStaff.setVisible(false);
                this.cbNeckRound.setSelectedIndex(0);
                this.cbNeckComplete.setSelectedIndex(0);
                this.cbUnderTheHandle.setSelectedIndex(0);
                this.cbBungIfDrilled.setSelectedIndex(0);
                this.cbBase.setSelectedIndex(0);
                this.cbStrengthOfDrum.setSelectedIndex(0);
                this.cbWeightWithinRange.setSelectedIndex(0);
                this.cbColourTexture.setSelectedIndex(0);
                //
                UpdateEntryData(now, (float) this.cbNeckRound.getSelectedIndex(), RecordKey.CHECK_NECK_ROUND, staff, pass);
                UpdateEntryData(now, (float) this.cbNeckComplete.getSelectedIndex(), RecordKey.CHECK_NECK_COMPLETE, staff, pass);
                UpdateEntryData(now, (float) this.cbUnderTheHandle.getSelectedIndex(), RecordKey.CHECK_UNDER_THE_HANDLE, staff, pass);
                UpdateEntryData(now, (float) this.cbBungIfDrilled.getSelectedIndex(), RecordKey.CHECK_BUNG_IF_DRILLED, staff, pass);
                UpdateEntryData(now, (float) this.cbBase.getSelectedIndex(), RecordKey.CHECK_BASE, staff, pass);
                UpdateEntryData(now, (float) this.cbStrengthOfDrum.getSelectedIndex(), RecordKey.CHECK_STRENGTH_OF_DRUM, staff, pass);
                UpdateEntryData(now, (float) this.cbWeightWithinRange.getSelectedIndex(), RecordKey.CHECK_WEIGHT_WITHIN_RANGE, staff, pass);
                UpdateEntryData(now, (float) this.cbColourTexture.getSelectedIndex(), RecordKey.CHECK_COLOUR_TEXTURE, staff, pass);
            }
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (this.cbDrop1.getSelectedIndex() != 0 && this.cbDrop2.getSelectedIndex() != 0 && this.cbDrop3.getSelectedIndex() != 0
                    && this.cbDrop4.getSelectedIndex() != 0 && this.cbDrop5.getSelectedIndex() != 0 && this.cbDrop6.getSelectedIndex() != 0
                    && this.cbDrop7.getSelectedIndex() != 0 && this.cbDrop8.getSelectedIndex() != 0) {
                if (this.cbDrop1.getSelectedIndex() != 2 && this.cbDrop2.getSelectedIndex() != 2 && this.cbDrop3.getSelectedIndex() != 2
                        && this.cbDrop4.getSelectedIndex() != 2 && this.cbDrop5.getSelectedIndex() != 2 && this.cbDrop6.getSelectedIndex() != 2
                        && this.cbDrop7.getSelectedIndex() != 2 && this.cbDrop8.getSelectedIndex() != 2) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtDropStaff.getText().equals("")) {
                        isSave = true;
                        staff = this.txtDropStaff.getText();
                        this.txtDropStaff.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Fail to pass all the tests, please entry supervisor name.", "Warning", JOptionPane.OK_OPTION);
                        this.labDropStaff.setVisible(true);
                        this.txtDropStaff.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please complete all the tests.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblDrop.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_1, this.cbDrop1.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_2, this.cbDrop2.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_3, this.cbDrop3.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_4, this.cbDrop4.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_5, this.cbDrop5.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_6, this.cbDrop6.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_7, this.cbDrop7.getSelectedItem(), pass, staff});
                model.addRow(new Object[]{time, RecordKey.DROP_TEST_8, this.cbDrop8.getSelectedItem(), pass, staff});

                ((AbstractTableModel) this.tblDrop.getModel()).fireTableDataChanged();
                this.labDropStaff.setVisible(false);
                this.txtDropStaff.setVisible(false);
                this.cbDrop1.setSelectedIndex(0);
                this.cbDrop2.setSelectedIndex(0);
                this.cbDrop3.setSelectedIndex(0);
                this.cbDrop4.setSelectedIndex(0);
                this.cbDrop5.setSelectedIndex(0);
                this.cbDrop6.setSelectedIndex(0);
                this.cbDrop7.setSelectedIndex(0);
                this.cbDrop8.setSelectedIndex(0);
                //
                UpdateEntryData(now, (float) this.cbDrop1.getSelectedIndex(), RecordKey.DROP_TEST_1, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop2.getSelectedIndex(), RecordKey.DROP_TEST_2, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop3.getSelectedIndex(), RecordKey.DROP_TEST_3, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop4.getSelectedIndex(), RecordKey.DROP_TEST_4, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop5.getSelectedIndex(), RecordKey.DROP_TEST_5, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop6.getSelectedIndex(), RecordKey.DROP_TEST_6, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop7.getSelectedIndex(), RecordKey.DROP_TEST_7, pass, staff);
                UpdateEntryData(now, (float) this.cbDrop8.getSelectedIndex(), RecordKey.DROP_TEST_8, pass, staff);
            }
        }
    }//GEN-LAST:event_btnDropActionPerformed

    private void UpdateEntryData(Date now, Float valueUnderHandle, RecordKey key, String staff, String pass) {
        int recordId = this.recordService.CreateEntity();
        Record record = this.recordService.FindEntity(recordId);
        record.setEntryId(this.currentEntry);
        record.setCreatedTime(now);
        record.setNumberValue(valueUnderHandle);
        record.setIsPass(pass);
        record.setRecordKey(key.toString());
        record.setStaff(staff);
        this.recordService.UpdateEntity(record);
    }

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
    private javax.swing.JButton btnBore;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnDrop;
    private javax.swing.JButton btnTap;
    private javax.swing.JButton btnWall;
    private javax.swing.JButton btnWeight;
    private javax.swing.JComboBox cbBase;
    private javax.swing.JComboBox cbBungIfDrilled;
    private javax.swing.JComboBox cbColourTexture;
    private javax.swing.JComboBox cbDrop1;
    private javax.swing.JComboBox cbDrop2;
    private javax.swing.JComboBox cbDrop3;
    private javax.swing.JComboBox cbDrop4;
    private javax.swing.JComboBox cbDrop5;
    private javax.swing.JComboBox cbDrop6;
    private javax.swing.JComboBox cbDrop7;
    private javax.swing.JComboBox cbDrop8;
    private javax.swing.JComboBox cbEntry;
    private javax.swing.JComboBox cbNeckComplete;
    private javax.swing.JComboBox cbNeckRound;
    private javax.swing.JComboBox cbStrengthOfDrum;
    private javax.swing.JComboBox cbTap;
    private javax.swing.JComboBox cbUnderTheHandle;
    private javax.swing.JComboBox cbWeightWithinRange;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labBoreStaff;
    private javax.swing.JLabel labCheckStaff;
    private javax.swing.JLabel labDropStaff;
    private javax.swing.JLabel labProductImage;
    private javax.swing.JLabel labTapStaff;
    private javax.swing.JLabel labWallStaff;
    private javax.swing.JLabel labWeightStaff;
    private javax.swing.JPanel pnlChartTap;
    private javax.swing.JPanel pnlChartWeight;
    private javax.swing.JPanel pnlMouldImage;
    private javax.swing.JTable tblBore;
    private javax.swing.JTable tblCheck;
    private javax.swing.JTable tblDrop;
    private javax.swing.JTable tblTap;
    private javax.swing.JTable tblWall;
    private javax.swing.JTable tblWeight;
    private javax.swing.JTextField txtBore1;
    private javax.swing.JTextField txtBore2;
    private javax.swing.JTextField txtBoreStaff;
    private javax.swing.JTextField txtCheckStaff;
    private javax.swing.JTextField txtDropStaff;
    private javax.swing.JTextField txtNeck;
    private javax.swing.JLabel txtProductBung;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProductColor;
    private javax.swing.JLabel txtProductDesc;
    private javax.swing.JLabel txtProductGrade;
    private javax.swing.JLabel txtProductPierced;
    private javax.swing.JLabel txtProductWeight;
    private javax.swing.JTextField txtTapStaff;
    private javax.swing.JTextField txtWallBase;
    private javax.swing.JTextField txtWallClosure;
    private javax.swing.JTextField txtWallHandleBung;
    private javax.swing.JTextField txtWallHandleLeft;
    private javax.swing.JTextField txtWallHandleRight;
    private javax.swing.JTextField txtWallStaff;
    private javax.swing.JTextField txtWallUnderHandle;
    private javax.swing.JTextField txtWeight;
    private javax.swing.JTextField txtWeightStaff;
    // End of variables declaration//GEN-END:variables

    private void UpdateProductInfo(Entry currentEntry) {
        if (currentEntry.getMouldId().getImageDrawing() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageDrawing(), this.pnlMouldImage, 150);
        } else {
            AppHelper.DisplayImage("/images/no_photo.png", this.pnlMouldImage, 150);
        }
        this.txtProductBung.setText(currentEntry.getProductId().getBung());
        this.txtProductCode.setText(currentEntry.getProductId().getCode());
        this.txtProductColor.setText(currentEntry.getAdditiveAId() != null ? currentEntry.getAdditiveAId().getDescription() : "");
        this.txtProductGrade.setText(currentEntry.getAdditiveAId() != null ? currentEntry.getAdditiveAId().getGrade() : "");
        this.txtProductDesc.setText(currentEntry.getProductId().getDescription());
        this.txtProductPierced.setText(currentEntry.getProductId().getPierced());
        this.txtProductWeight.setText(currentEntry.getProductId().getWeightMin() + " - " + currentEntry.getProductId().getWeightMax());
    }
}
