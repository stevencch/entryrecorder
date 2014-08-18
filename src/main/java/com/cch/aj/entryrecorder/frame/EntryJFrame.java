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
import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.stream;
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
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class EntryJFrame extends javax.swing.JFrame {

    private int settingMouldId = 0;
    private int settingMouldPreviousId = 0;
    private Mould settingMould = new Mould();

    private SettingService staffService = new SettingServiceImpl<Staff>(Staff.class);
    private SettingService machineService = new SettingServiceImpl<Machine>(Machine.class);
    private SettingService mouldService = new SettingServiceImpl<Mould>(Mould.class);
    private SettingService productService = new SettingServiceImpl<Product>(Product.class);
    private SettingService entryService = new SettingServiceImpl<Entry>(Entry.class);

    /**
     * Creates new form SettingsJFrame
     */
    public EntryJFrame() {

        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //load staff
        this.cbStaff.setRenderer(new ComboBoxRender());
        UpdateTabStaff(0);
        //load entry
        this.cbEntry.setRenderer(new ComboBoxRender());
        this.cbEntryMachine.setRenderer(new ComboBoxRender());
        this.cbEntryMould.setRenderer(new ComboBoxRender());
        this.cbEntryProduct.setRenderer(new ComboBoxRender());

    }

    private void UpdateTabStaff(int id) {
        int selectedIndex = FillStaffComboBox(this.cbStaff, id);
        if (selectedIndex >= 0) {
            Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
            //
            this.cbStaffJob.setSelectedItem(currentStaff.getJobType());
            this.txtStaffName.setText(currentStaff.getName());
        } else {
            this.cbStaff.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtStaffName.setText("");
        }
    }

    private void UpdateTabEntry(int id) {

        //entry
        Entry currentEntry = new Entry();

        int selectedIndex = FillEntryComboBox(this.cbEntry, id);
        if (selectedIndex >= 0) {
            currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
            this.UpdateEntryUI(currentEntry);

        } else {
            this.cbEntry.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.UpdateEntryUI(new Entry());
        }

    }

    private int FillMouldComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Mould> moulds = this.mouldService.GetAllEntities();
        if (moulds.size() > 0) {
            List<ComboBoxItem<Mould>> mouldNames = moulds.stream().sorted(comparing(x -> x.getCode())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCode(), x.getId())).collect(Collectors.toList());
            Mould mould = new Mould();
            mould.setId(0);
            mould.setCode("- Select -");
            mouldNames.add(0, new ComboBoxItem<Mould>(mould, mould.getCode(), mould.getId()));
            ComboBoxItem[] mouldNamesArray = mouldNames.toArray(new ComboBoxItem[mouldNames.size()]);
            comboBox.setModel(new DefaultComboBoxModel(mouldNamesArray));
            if (id != 0) {
                ComboBoxItem<Mould> currentMouldName = mouldNames.stream().filter(x -> x.getId() == id).findFirst().get();
                result = mouldNames.indexOf(currentMouldName);
            } else {
                result = 0;
            }
            comboBox.setSelectedIndex(result);
        }
        return result;
    }

    private int FillProductComboBox(JComboBox comboBox, int id, int mouldId) {
        int result = -1;
        List<Product> allProducts = this.productService.GetAllEntities();
        if (allProducts.size() > 0) {
            List<Product> products = allProducts.stream().filter(x -> x.getMouldId() != null && x.getMouldId() == mouldId).collect(Collectors.toList());
            if (products.size() > 0) {
                List<ComboBoxItem<Product>> productNames = products.stream().sorted(comparing(x -> x.getCode())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCode(), x.getId())).collect(Collectors.toList());
                Product product = new Product();
                product.setId(0);
                product.setCode("- Select -");
                productNames.add(0, new ComboBoxItem<Product>(product, product.getCode(), product.getId()));
                ComboBoxItem[] productNamesArray = productNames.toArray(new ComboBoxItem[productNames.size()]);
                comboBox.setModel(new DefaultComboBoxModel(productNamesArray));
                if (id != 0) {
                    ComboBoxItem<Product> currentProductName = productNames.stream().filter(x -> x.getId() == id).findFirst().get();
                    result = productNames.indexOf(currentProductName);
                } else {
                    result = 0;
                }
                comboBox.setSelectedIndex(result);
            }
        }
        return result;
    }

    private int FillStaffComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Staff> staffs = this.staffService.GetAllEntities();
        if (staffs.size() > 0) {
            List<ComboBoxItem<Staff>> staffNames = staffs.stream().sorted(comparing(x -> x.getJobType() + " " + x.getName())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getJobType() + " " + x.getName(), x.getId())).collect(Collectors.toList());
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

    private int FillMachineComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Machine> machines = this.machineService.GetAllEntities();
        if (machines.size() > 0) {
            List<ComboBoxItem<Machine>> machineNames = machines.stream().sorted(comparing(x -> x.getMachineNo())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getMachineNo(), x.getId())).collect(Collectors.toList());
            Machine machine = new Machine();
            machine.setId(0);
            machine.setMachineNo("- Select -");
            machineNames.add(0, new ComboBoxItem<Machine>(machine, machine.getMachineNo(), machine.getId()));
            ComboBoxItem[] machineNamesArray = machineNames.toArray(new ComboBoxItem[machineNames.size()]);
            comboBox.setModel(new DefaultComboBoxModel(machineNamesArray));
            if (id != 0) {
                ComboBoxItem<Machine> currentMachineName = machineNames.stream().filter(x -> x.getId() == id).findFirst().get();
                result = machineNames.indexOf(currentMachineName);
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
            List<Entry> entrys = allEntrys.stream().filter(x -> x.getShift().equals(this.txtEntrySearch.getText())).collect(Collectors.toList());
            if (entrys.size() > 0) {
                List<ComboBoxItem<Entry>> entryNames = entrys.stream().sorted(comparing(x -> x.getCreateDate())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getMachineId() != null ? ((Machine) this.machineService.FindEntity(x.getMachineId())).getMachineNo() : "New", x.getId())).collect(Collectors.toList());
                Entry entry = new Entry();
                entry.setId(0);
                entry.setShift("- Select -");
                entryNames.add(0, new ComboBoxItem<Entry>(entry, entry.getShift(), entry.getId()));
                ComboBoxItem[] entryNamesArray = entryNames.toArray(new ComboBoxItem[entryNames.size()]);
                comboBox.setModel(new DefaultComboBoxModel(entryNamesArray));
                if (id != 0) {
                    ComboBoxItem<Entry> currentEntryName = entryNames.stream().filter(x -> x.getId() == id).findFirst().get();
                    result = entryNames.indexOf(currentEntryName);
                } else {
                    result = 0;
                }
                comboBox.setSelectedIndex(result);
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

        tabSettings = new javax.swing.JTabbedPane();
        jPanel38 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtEntryShift = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        cbEntryMachine = new javax.swing.JComboBox();
        cbEntryMould = new javax.swing.JComboBox();
        cbEntryProduct = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        cbEntryInUse = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        labEntryCreatedDate = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        txtEntryWeightMin = new javax.swing.JTextField();
        txtEntryWeightMax = new javax.swing.JTextField();
        txtEntryWallMin = new javax.swing.JTextField();
        txtEntryWallMax = new javax.swing.JTextField();
        txtEntryThreadBoreMin = new javax.swing.JTextField();
        txtEntryThreadBoreMax = new javax.swing.JTextField();
        txtEntryThreadNeckMin = new javax.swing.JTextField();
        txtEntryThreadNeckMax = new javax.swing.JTextField();
        txtEntryTapPositionMin = new javax.swing.JTextField();
        txtEntryTapPositionMax = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        btnEntryNew = new javax.swing.JButton();
        cbEntry = new javax.swing.JComboBox();
        btnEntryDelete = new javax.swing.JButton();
        jLabel126 = new javax.swing.JLabel();
        txtEntrySearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        btnEntryUndo = new javax.swing.JButton();
        btnEntrySave = new javax.swing.JButton();
        jLabel163 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        btnStaffNew = new javax.swing.JButton();
        cbStaff = new javax.swing.JComboBox();
        btnStaffDelete = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        btnStaffUndo = new javax.swing.JButton();
        btnStaffSave = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        txtStaffName = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        cbStaffJob = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tabSettings.setName(""); // NOI18N
        tabSettings.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabSettingsAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabSettings.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabSettingsStateChanged(evt);
            }
        });

        jPanel38.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel47.setText("SHIFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel47, gridBagConstraints);

        txtEntryShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntryShiftActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(txtEntryShift, gridBagConstraints);

        jLabel74.setText("MACHINE No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel74, gridBagConstraints);

        jLabel75.setText("MOULD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel75, gridBagConstraints);

        jLabel76.setText("PRODUCT CODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel76, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(cbEntryMachine, gridBagConstraints);

        cbEntryMould.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntryMouldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(cbEntryMould, gridBagConstraints);

        cbEntryProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntryProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(cbEntryProduct, gridBagConstraints);

        jLabel73.setText("IN USE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel73, gridBagConstraints);

        cbEntryInUse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(cbEntryInUse, gridBagConstraints);

        jLabel1.setText("Created Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(jLabel1, gridBagConstraints);

        labEntryCreatedDate.setText("Created Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 45);
        jPanel9.add(labEntryCreatedDate, gridBagConstraints);

        jTabbedPane4.addTab("General", jPanel9);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel118.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(jLabel118, gridBagConstraints);

        jLabel119.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(jLabel119, gridBagConstraints);

        jLabel120.setText("PRODUCT WEIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel13.add(jLabel120, gridBagConstraints);

        jLabel121.setText("WALL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel13.add(jLabel121, gridBagConstraints);

        jLabel122.setText("THREAD BORE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel13.add(jLabel122, gridBagConstraints);

        jLabel123.setText("THREAD NECK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel13.add(jLabel123, gridBagConstraints);

        jLabel124.setText("TAP POSITION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel13.add(jLabel124, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(txtEntryWeightMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(txtEntryWeightMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(txtEntryWallMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(txtEntryWallMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(txtEntryThreadBoreMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(txtEntryThreadBoreMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(txtEntryThreadNeckMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(txtEntryThreadNeckMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel13.add(txtEntryTapPositionMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel13.add(txtEntryTapPositionMax, gridBagConstraints);

        jTabbedPane4.addTab("Settings", jPanel13);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel38.add(jTabbedPane4, gridBagConstraints);

        jPanel47.setLayout(new java.awt.GridBagLayout());

        btnEntryNew.setText("New");
        btnEntryNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntryNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(btnEntryNew, gridBagConstraints);

        cbEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEntryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(cbEntry, gridBagConstraints);

        btnEntryDelete.setText("Delete");
        btnEntryDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntryDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(btnEntryDelete, gridBagConstraints);

        jLabel126.setText("SHIFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(jLabel126, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(txtEntrySearch, gridBagConstraints);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel38.add(jPanel47, gridBagConstraints);

        jPanel48.setLayout(new java.awt.GridBagLayout());

        btnEntryUndo.setText("Undo");
        btnEntryUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntryUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel48.add(btnEntryUndo, gridBagConstraints);

        btnEntrySave.setText("Save");
        btnEntrySave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrySaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel48.add(btnEntrySave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel48.add(jLabel163, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel38.add(jPanel48, gridBagConstraints);

        tabSettings.addTab("Entry", jPanel38);

        jPanel29.setLayout(new java.awt.GridBagLayout());

        jPanel30.setLayout(new java.awt.GridBagLayout());

        btnStaffNew.setText("New");
        btnStaffNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel30.add(btnStaffNew, gridBagConstraints);

        cbStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStaffActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel30.add(cbStaff, gridBagConstraints);

        btnStaffDelete.setText("Delete");
        btnStaffDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel30.add(btnStaffDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel29.add(jPanel30, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());

        btnStaffUndo.setText("Undo");
        btnStaffUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel31.add(btnStaffUndo, gridBagConstraints);

        btnStaffSave.setText("Save");
        btnStaffSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel31.add(btnStaffSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel31.add(jLabel101, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel29.add(jPanel31, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel78.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 20);
        jPanel12.add(jLabel78, gridBagConstraints);

        txtStaffName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStaffNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 55);
        jPanel12.add(txtStaffName, gridBagConstraints);

        jLabel90.setText("JOB TITLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 20);
        jPanel12.add(jLabel90, gridBagConstraints);

        cbStaffJob.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PROCESS WORKER", "TECHNICIAN", "SUPERVISOR" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 55);
        jPanel12.add(cbStaffJob, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel29.add(jPanel12, gridBagConstraints);

        tabSettings.addTab("Staff", jPanel29);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 52;
        gridBagConstraints.ipady = 174;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 20);
        getContentPane().add(tabSettings, gridBagConstraints);
        tabSettings.getAccessibleContext().setAccessibleName("Tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStaffNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStaffNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStaffNameActionPerformed

    private void btnStaffNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffNewActionPerformed
        // TODO add your handling code here:
        int newId = this.staffService.CreateEntity();
        UpdateTabStaff(newId);
    }//GEN-LAST:event_btnStaffNewActionPerformed

    private void cbStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStaffActionPerformed

        Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
        //
        this.cbStaffJob.setSelectedItem(currentStaff.getJobType());
        this.txtStaffName.setText(currentStaff.getName());
    }//GEN-LAST:event_cbStaffActionPerformed

    private void btnStaffSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSaveActionPerformed
        // TODO add your handling code here:
        Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
        if ("- Select -".equals(currentStaff.getName())) {
            return;
        }
        currentStaff.setJobType((String) this.cbStaffJob.getSelectedItem());
        currentStaff.setName(this.txtStaffName.getText());
        this.staffService.UpdateEntity(currentStaff);
        this.UpdateTabStaff(currentStaff.getId());
    }//GEN-LAST:event_btnStaffSaveActionPerformed

    private void btnStaffDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffDeleteActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
            if ("- Select -".equals(currentStaff.getName())) {
                return;
            }
            this.staffService.DeleteEntity(currentStaff.getId());
            this.UpdateTabStaff(0);
        }
    }//GEN-LAST:event_btnStaffDeleteActionPerformed

    private void btnStaffUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffUndoActionPerformed
        // TODO add your handling code here:
        Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
        this.UpdateTabStaff(currentStaff.getId());
    }//GEN-LAST:event_btnStaffUndoActionPerformed


    private void tabSettingsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabSettingsStateChanged
    }//GEN-LAST:event_tabSettingsStateChanged

    private void txtEntryShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntryShiftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntryShiftActionPerformed

    private void btnEntryNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntryNewActionPerformed
        if (CheckEntryShift()) {
            int newId = this.entryService.CreateEntity();
            if (this.settingMouldId != 0) {
                Entry newEntry = (Entry) this.entryService.FindEntity(newId);
                newEntry.setMouldId(this.settingMouldId);
                this.entryService.UpdateEntity(newEntry);
            }
            UpdateTabEntry(newId);
        }
    }//GEN-LAST:event_btnEntryNewActionPerformed

    private void cbEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryActionPerformed
        Entry currentItem = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
        UpdateEntryUI(currentItem);
    }//GEN-LAST:event_cbEntryActionPerformed

    private void btnEntryDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntryDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Entry currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
            if ("- Select -".equals(currentEntry.getShift())) {
                return;
            }
            this.entryService.DeleteEntity(currentEntry.getId());
            this.UpdateTabEntry(0);
        }
    }//GEN-LAST:event_btnEntryDeleteActionPerformed

    private void btnEntryUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntryUndoActionPerformed
        Entry currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
        this.UpdateTabEntry(currentEntry.getId());
    }//GEN-LAST:event_btnEntryUndoActionPerformed

    private void btnEntrySaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrySaveActionPerformed
        Entry currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
        if ("- Select -".equals(currentEntry.getShift())) {
            return;
        }
        if (!this.txtEntryShift.getText().equals("")) {
            currentEntry.setShift(this.txtEntryShift.getText());
        }
        if (!this.txtEntryWeightMax.getText().equals("")) {
            currentEntry.setWeightMax(Float.parseFloat(this.txtEntryWeightMax.getText()));
        }
        if (!this.txtEntryWeightMin.getText().equals("")) {
            currentEntry.setWeightMin(Float.parseFloat(this.txtEntryWeightMin.getText()));
        }
        if (!this.txtEntryWallMax.getText().equals("")) {
            currentEntry.setWallMax(Float.parseFloat(this.txtEntryWallMax.getText()));
        }
        if (!this.txtEntryWallMin.getText().equals("")) {
            currentEntry.setWallMin(Float.parseFloat(this.txtEntryWallMin.getText()));
        }
        if (!this.txtEntryThreadBoreMax.getText().equals("")) {
            currentEntry.setThreadBoreMax(Float.parseFloat(this.txtEntryThreadBoreMax.getText()));
        }
        if (!this.txtEntryThreadBoreMin.getText().equals("")) {
            currentEntry.setThreadBoreMin(Float.parseFloat(this.txtEntryThreadBoreMin.getText()));
        }
        if (!this.txtEntryThreadNeckMax.getText().equals("")) {
            currentEntry.setThreadNeckMax(Float.parseFloat(this.txtEntryThreadNeckMax.getText()));
        }
        if (!this.txtEntryThreadNeckMin.getText().equals("")) {
            currentEntry.setThreadNeckMin(Float.parseFloat(this.txtEntryThreadNeckMin.getText()));
        }
        if (!this.txtEntryTapPositionMax.getText().equals("")) {
            currentEntry.setTapPositionMax(Float.parseFloat(this.txtEntryTapPositionMax.getText()));
        }
        if (!this.txtEntryTapPositionMin.getText().equals("")) {
            currentEntry.setTapPositionMin(Float.parseFloat(this.txtEntryTapPositionMin.getText()));
        }
        currentEntry.setMouldId(((ComboBoxItem<Mould>) this.cbEntryMould.getSelectedItem()).getId());
        currentEntry.setMachineId(((ComboBoxItem<Machine>) this.cbEntryMachine.getSelectedItem()).getId());
        currentEntry.setProductId(((ComboBoxItem<Product>) this.cbEntryProduct.getSelectedItem()).getId());
        if (this.cbEntryInUse.getSelectedItem() != null) {
            currentEntry.setInUse(this.cbEntryInUse.getSelectedItem().toString());
        }
        this.entryService.UpdateEntity(currentEntry);
        this.UpdateTabEntry(currentEntry.getId());
    }//GEN-LAST:event_btnEntrySaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (CheckEntryShift()) {
            UpdateTabEntry(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private Boolean CheckEntryShift() throws HeadlessException {
        Boolean result = true;
        if (!this.txtEntrySearch.getText().isEmpty()) {
            AppHelper.defaultShift = this.txtEntrySearch.getText();
        } else {
            JOptionPane.showMessageDialog(this, "Please entry the value of shift", "Warning", JOptionPane.OK_OPTION);
            result = false;
        }
        return result;
    }

    private void cbEntryMouldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryMouldActionPerformed
        this.FillProductComboBox(this.cbEntryProduct, 0, ((ComboBoxItem<Mould>) this.cbEntryMould.getSelectedItem()).getItem().getId());
    }//GEN-LAST:event_cbEntryMouldActionPerformed

    private void tabSettingsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabSettingsAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabSettingsAncestorAdded

    private void cbEntryProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryProductActionPerformed
        Product currentProduct = ((ComboBoxItem<Product>) this.cbEntryProduct.getSelectedItem()).getItem();
        if (currentProduct.getCode() != "- Select -") {
            Mould currentMould = ((ComboBoxItem<Mould>) this.cbEntryMould.getSelectedItem()).getItem();
            txtEntryWeightMin.setText(currentProduct.getWeightMin() == null ? "" : currentProduct.getWeightMin().toString());;
            txtEntryWeightMax.setText(currentProduct.getWeightMax() == null ? "" : currentProduct.getWeightMax().toString());;
            txtEntryTapPositionMin.setText(currentMould.getTapPositionMin() == null ? "" : currentMould.getTapPositionMin().toString());
            txtEntryTapPositionMax.setText(currentMould.getTapPositionMax() == null ? "" : currentMould.getTapPositionMax().toString());
            if (currentProduct.getDgnondg() != null) {
                switch (currentProduct.getDgnondg()) {
                    case 0:
                        this.txtEntryWallMin.setText(currentMould.getWallDgUnderHandleMin() == null ? "" : currentMould.getWallDgUnderHandleMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgUnderHandleMax() == null ? "" : currentMould.getWallDgUnderHandleMax().toString());
                        break;
                    case 1:
                        this.txtEntryWallMin.setText(currentMould.getWallDgBaseMin() == null ? "" : currentMould.getWallDgBaseMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgBaseMax() == null ? "" : currentMould.getWallDgBaseMax().toString());
                        break;
                    case 2:
                        this.txtEntryWallMin.setText(currentMould.getWallDgClosureMin() == null ? "" : currentMould.getWallDgClosureMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgClosureMax() == null ? "" : currentMould.getWallDgClosureMax().toString());
                        break;
                    case 3:
                        this.txtEntryWallMin.setText(currentMould.getWallDgHandleBungMin() == null ? "" : currentMould.getWallDgHandleBungMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgHandleBungMax() == null ? "" : currentMould.getWallDgHandleBungMax().toString());
                        break;
                    case 4:
                        this.txtEntryWallMin.setText(currentMould.getWallDgHandleLeftMin() == null ? "" : currentMould.getWallDgHandleLeftMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgHandleLeftMax() == null ? "" : currentMould.getWallDgHandleLeftMax().toString());
                        break;
                    case 5:
                        this.txtEntryWallMin.setText(currentMould.getWallDgHandRightMin() == null ? "" : currentMould.getWallDgHandRightMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallDgHandleRightMax() == null ? "" : currentMould.getWallDgHandleRightMax().toString());
                        break;
                    case 6:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgUnderHandleMin() == null ? "" : currentMould.getWallNonDgUnderHandleMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgUnderHandleMax() == null ? "" : currentMould.getWallNonDgUnderHandleMax().toString());
                        break;
                    case 7:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgBaseMin() == null ? "" : currentMould.getWallNonDgBaseMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgBaseMax() == null ? "" : currentMould.getWallNonDgBaseMax().toString());
                        break;
                    case 8:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgClosureMin() == null ? "" : currentMould.getWallNonDgClosureMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgClosureMax() == null ? "" : currentMould.getWallNonDgClosureMax().toString());
                        break;
                    case 9:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgHandleBungMin() == null ? "" : currentMould.getWallNonDgHandleBungMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgHandleBungMax() == null ? "" : currentMould.getWallNonDgHandleBungMax().toString());
                        break;
                    case 10:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgHandleLeftMin() == null ? "" : currentMould.getWallNonDgHandleLeftMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgHandleLeftMax() == null ? "" : currentMould.getWallNonDgHandleLeftMax().toString());
                        break;
                    case 11:
                        this.txtEntryWallMin.setText(currentMould.getWallNonDgHandleRightMin() == null ? "" : currentMould.getWallNonDgHandleRightMin().toString());
                        this.txtEntryWallMax.setText(currentMould.getWallNonDgHandleRightMax() == null ? "" : currentMould.getWallNonDgHandleRightMax().toString());
                        break;
                }
                if (currentProduct.getThreadBore() != null) {
                    switch (currentProduct.getThreadBore()) {
                        case 1:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreAMin1() == null ? "" : currentMould.getThreadBoreAMin1().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreAMax1() == null ? "" : currentMould.getThreadBoreAMax1().toString());
                            break;
                        case 2:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreAMin2() == null ? "" : currentMould.getThreadBoreAMin2().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreAMax2() == null ? "" : currentMould.getThreadBoreAMax2().toString());
                            break;
                        case 3:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreAMin3() == null ? "" : currentMould.getThreadBoreAMin3().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreAMax3() == null ? "" : currentMould.getThreadBoreAMax3().toString());
                            break;
                        case 4:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreBMin1() == null ? "" : currentMould.getThreadBoreBMin1().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreBMax1() == null ? "" : currentMould.getThreadBoreBMax1().toString());
                            break;
                        case 5:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreBMin2() == null ? "" : currentMould.getThreadBoreBMin2().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreBMax2() == null ? "" : currentMould.getThreadBoreBMax2().toString());
                            break;
                        case 6:
                            this.txtEntryThreadBoreMin.setText(currentMould.getThreadBoreBMin3() == null ? "" : currentMould.getThreadBoreBMin3().toString());
                            this.txtEntryThreadBoreMax.setText(currentMould.getThreadBoreBMax3() == null ? "" : currentMould.getThreadBoreBMax3().toString());
                            break;
                    }
                }
                if (currentProduct.getThreadNeck() != null) {
                    switch (currentProduct.getThreadNeck()) {
                        case 1:
                            this.txtEntryThreadNeckMin.setText(currentMould.getThreadNeckMin1() == null ? "" : currentMould.getThreadNeckMin1().toString());
                            this.txtEntryThreadNeckMax.setText(currentMould.getThreadNeckMax1() == null ? "" : currentMould.getThreadNeckMax1().toString());
                            break;
                        case 2:
                            this.txtEntryThreadNeckMin.setText(currentMould.getThreadNeckMin2() == null ? "" : currentMould.getThreadNeckMin2().toString());
                            this.txtEntryThreadNeckMax.setText(currentMould.getThreadNeckMax2() == null ? "" : currentMould.getThreadNeckMax2().toString());
                            break;
                        case 3:
                            this.txtEntryThreadNeckMin.setText(currentMould.getThreadNeckMin3() == null ? "" : currentMould.getThreadNeckMin3().toString());
                            this.txtEntryThreadNeckMax.setText(currentMould.getThreadNeckMax3() == null ? "" : currentMould.getThreadNeckMax3().toString());
                            break;
                    }
                }
            }
        }
    }//GEN-LAST:event_cbEntryProductActionPerformed


    private void UpdateEntryUI(Entry currentEntry) {
        this.labEntryCreatedDate.setText(currentEntry.getCreateDate()!=null?currentEntry.getCreateDate().toString():"");
        cbEntryInUse.setSelectedItem(currentEntry.getInUse());
        txtEntryShift.setText(currentEntry.getShift() == null || currentEntry.getShift() == "- Select -" ? "" : currentEntry.getShift().toString());;
        txtEntryWeightMin.setText(currentEntry.getWeightMin() == null ? "" : currentEntry.getWeightMin().toString());;
        txtEntryWeightMax.setText(currentEntry.getWeightMax() == null ? "" : currentEntry.getWeightMax().toString());;
        txtEntryWallMin.setText(currentEntry.getWallMin() == null ? "" : currentEntry.getWallMin().toString());;
        txtEntryWallMax.setText(currentEntry.getWallMax() == null ? "" : currentEntry.getWallMax().toString());
        txtEntryThreadBoreMin.setText(currentEntry.getThreadBoreMin() == null ? "" : currentEntry.getThreadBoreMin().toString());
        txtEntryThreadBoreMax.setText(currentEntry.getThreadBoreMax() == null ? "" : currentEntry.getThreadBoreMax().toString());
        txtEntryThreadNeckMin.setText(currentEntry.getThreadNeckMin() == null ? "" : currentEntry.getThreadNeckMin().toString());
        txtEntryThreadNeckMax.setText(currentEntry.getThreadNeckMax() == null ? "" : currentEntry.getThreadNeckMax().toString());
        txtEntryTapPositionMin.setText(currentEntry.getTapPositionMin() == null ? "" : currentEntry.getTapPositionMin().toString());
        txtEntryTapPositionMax.setText(currentEntry.getTapPositionMax() == null ? "" : currentEntry.getTapPositionMax().toString());
        //combobox
        this.FillMouldComboBox(this.cbEntryMould, currentEntry.getMouldId() != null ? currentEntry.getMouldId() : 0);
        if (currentEntry.getMouldId() != null) {
            this.FillProductComboBox(this.cbEntryProduct, currentEntry.getProductId() != null ? currentEntry.getProductId() : 0, currentEntry.getMouldId());
        }
        this.FillMachineComboBox(this.cbEntryMachine, currentEntry.getMachineId() != null ? currentEntry.getMachineId() : 0);
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
            java.util.logging.Logger.getLogger(EntryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntryDelete;
    private javax.swing.JButton btnEntryNew;
    private javax.swing.JButton btnEntrySave;
    private javax.swing.JButton btnEntryUndo;
    private javax.swing.JButton btnStaffDelete;
    private javax.swing.JButton btnStaffNew;
    private javax.swing.JButton btnStaffSave;
    private javax.swing.JButton btnStaffUndo;
    private javax.swing.JComboBox cbEntry;
    private javax.swing.JComboBox cbEntryInUse;
    private javax.swing.JComboBox cbEntryMachine;
    private javax.swing.JComboBox cbEntryMould;
    private javax.swing.JComboBox cbEntryProduct;
    private javax.swing.JComboBox cbStaff;
    private javax.swing.JComboBox cbStaffJob;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel labEntryCreatedDate;
    private javax.swing.JTabbedPane tabSettings;
    private javax.swing.JTextField txtEntrySearch;
    private javax.swing.JTextField txtEntryShift;
    private javax.swing.JTextField txtEntryTapPositionMax;
    private javax.swing.JTextField txtEntryTapPositionMin;
    private javax.swing.JTextField txtEntryThreadBoreMax;
    private javax.swing.JTextField txtEntryThreadBoreMin;
    private javax.swing.JTextField txtEntryThreadNeckMax;
    private javax.swing.JTextField txtEntryThreadNeckMin;
    private javax.swing.JTextField txtEntryWallMax;
    private javax.swing.JTextField txtEntryWallMin;
    private javax.swing.JTextField txtEntryWeightMax;
    private javax.swing.JTextField txtEntryWeightMin;
    private javax.swing.JTextField txtStaffName;
    // End of variables declaration//GEN-END:variables

}