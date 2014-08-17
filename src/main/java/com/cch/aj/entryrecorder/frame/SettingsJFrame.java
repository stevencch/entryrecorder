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
public class SettingsJFrame extends javax.swing.JFrame {

    private int settingMouldId = 0;
    private int settingMouldPreviousId = 0;
    private Mould settingMould = new Mould();

    private SettingService staffService = new SettingServiceImpl<Staff>(Staff.class);
    private SettingService machineService = new SettingServiceImpl<Machine>(Machine.class);
    private SettingService polymerService = new SettingServiceImpl<Polymer>(Polymer.class);
    private SettingService additiveService = new SettingServiceImpl<Additive>(Additive.class);
    private SettingService mouldService = new SettingServiceImpl<Mould>(Mould.class);
    private SettingService productService = new SettingServiceImpl<Product>(Product.class);
    private SettingService entryService = new SettingServiceImpl<Entry>(Entry.class);

    /**
     * Creates new form SettingsJFrame
     */
    public SettingsJFrame() {

        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //load staff
        this.cbStaff.setRenderer(new ComboBoxRender());
        UpdateTabStaff(0);
        //load Machine
        this.cbMachine.setRenderer(new ComboBoxRender());
        UpdateTabMachine(0);
        //load Polymer
        this.cbPolymer.setRenderer(new ComboBoxRender());
        UpdateTabPolymer(0);
        //load Additive
        this.cbAdditive.setRenderer(new ComboBoxRender());
        UpdateTabAdditive(0);
        //load Mould
        this.cbMould.setRenderer(new ComboBoxRender());
        UpdateTabMould(0);
        //load Product
        this.cbProduct.setRenderer(new ComboBoxRender());
        this.cbProductMould.setRenderer(new ComboBoxRender());
        this.cbProductPolymer.setRenderer(new ComboBoxRender());
        this.cbProductAdditive1.setRenderer(new ComboBoxRender());
        this.cbProductAdditive2.setRenderer(new ComboBoxRender());
        this.cbProductAdditive3.setRenderer(new ComboBoxRender());
        UpdateTabProduct(0);
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

    private void UpdateTabMachine(int id) {
        int selectedIndex = FillMachineComboBox(this.cbMachine, id);
        if (selectedIndex >= 0) {
            Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
            //
            this.txtMachineCapacity.setText(currentMachine.getCapacity());
            this.txtMachineDesc.setText(currentMachine.getDescription());
            this.txtMachineManufa.setText(currentMachine.getManufacturer());
            this.txtMachineNo.setText(currentMachine.getMachineNo());
            this.txtMachineSerial.setText(currentMachine.getSerialNo());
            this.txtMachineYear.setText(currentMachine.getYear());
        } else {
            this.cbMachine.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtMachineCapacity.setText("");
            this.txtMachineDesc.setText("");
            this.txtMachineManufa.setText("");
            this.txtMachineNo.setText("");
            this.txtMachineSerial.setText("");
            this.txtMachineYear.setText("");
        }
    }

    private void UpdateTabPolymer(int id) {
        int selectedIndex = FillPolymerComboBox(this.cbPolymer, id);
        if (selectedIndex >= 0) {
            Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
            //
            this.txtPolymerCompany.setText(currentPolymer.getCompany() == null || currentPolymer.getCompany() == "- Select -" ? "" : currentPolymer.getCompany().toString());
            this.txtPolymerDesc.setText(currentPolymer.getDescription() == null ? "" : currentPolymer.getDescription().toString());
            this.txtPolymerGrade.setText(currentPolymer.getGrade() == null ? "" : currentPolymer.getGrade().toString());
        } else {
            this.cbPolymer.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtPolymerCompany.setText("");
            this.txtPolymerDesc.setText("");
            this.txtPolymerGrade.setText("");
        }
    }

    private void UpdateTabAdditive(int id) {
        int selectedIndex = FillAdditiveComboBox(this.cbAdditive, id);
        if (selectedIndex >= 0) {
            Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
            //
            this.txtAdditiveCompany.setText(currentAdditive.getCompany());
            this.txtAdditiveDesc.setText(currentAdditive.getDescription());
            this.txtAdditiveGrade.setText(currentAdditive.getGrade());
        } else {
            this.cbAdditive.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtAdditiveCompany.setText("");
            this.txtAdditiveDesc.setText("");
            this.txtAdditiveGrade.setText("");
        }
    }

    private void UpdateTabMould(int id) {
        int selectedIndex = FillMouldComboBox(this.cbMould, id);
        if (selectedIndex >= 0) {
            Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
            this.settingMouldId = currentMould.getId();
            this.settingMould = currentMould;
            //
            this.UpdateMouldUI(currentMould);

        } else {
            this.cbMould.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.UpdateMouldUI(new Mould());
        }
    }

    private void UpdateTabProduct(int id) {
        //product
        Product currentProduct = new Product();

        int selectedIndex = FillProductComboBox(this.cbProduct, id, this.settingMouldId);
        if (selectedIndex >= 0) {
            currentProduct = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
            this.UpdateProductUI(currentProduct);

        } else {
            this.cbProduct.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.UpdateProductUI(new Product());
        }
//        //combobox
//        this.FillMouldComboBox(this.cbProductMould, mouldId);
//        this.FillPolymerComboBox(this.cbProductPolymer, polymerId);
//        this.FillAdditiveComboBox(this.cbProductAdditive1, additiveId1);
//        this.FillAdditiveComboBox(this.cbProductAdditive2, additiveId2);
//        this.FillAdditiveComboBox(this.cbProductAdditive3, additiveId3);
//        List<String> threadBores = new ArrayList<String>();
//        threadBores.add("- Select -");
//        if (settingMould.getThreadBoreASize1() != null && !settingMould.getThreadBoreASize1().equals("")) {
//            threadBores.add(settingMould.getThreadBoreASize1());
//        }
//        if (settingMould.getThreadBoreASize2() != null && !settingMould.getThreadBoreASize2().equals("")) {
//            threadBores.add(settingMould.getThreadBoreASize2());
//        }
//        if (settingMould.getThreadBoreASize3() != null && !settingMould.getThreadBoreASize3().equals("")) {
//            threadBores.add(settingMould.getThreadBoreASize3());
//        }
//        if (settingMould.getThreadBoreBSize1() != null && !settingMould.getThreadBoreBSize1().equals("")) {
//            threadBores.add(settingMould.getThreadBoreBSize1());
//        }
//        if (settingMould.getThreadBoreBSize2() != null && !settingMould.getThreadBoreBSize2().equals("")) {
//            threadBores.add(settingMould.getThreadBoreBSize2());
//        }
//        if (settingMould.getThreadBoreBSize3() != null && !settingMould.getThreadBoreBSize3().equals("")) {
//            threadBores.add(settingMould.getThreadBoreBSize3());
//        }
//        this.cbProductBore.setModel(new DefaultComboBoxModel(threadBores.toArray()));
//        List<String> threadNecks = new ArrayList<String>();
//        threadNecks.add("- Select -");
//        if (settingMould.getThreadNeckSize1() != null && !settingMould.getThreadNeckSize1().equals("")) {
//            threadNecks.add(settingMould.getThreadNeckSize1());
//        }
//        if (settingMould.getThreadNeckSize2() != null && !settingMould.getThreadNeckSize2().equals("")) {
//            threadNecks.add(settingMould.getThreadNeckSize2());
//        }
//        if (settingMould.getThreadNeckSize3() != null && !settingMould.getThreadNeckSize3().equals("")) {
//            threadNecks.add(settingMould.getThreadNeckSize3());
//        }
//        this.cbProductNeck.setModel(new DefaultComboBoxModel(threadNecks.toArray()));
//        if (currentProduct.getBung() != null && !currentProduct.getBung().equals("")) {
//            this.cbProductBung.setSelectedItem(currentProduct.getBung().toString());
//        }
//        if (currentProduct.getPierced() != null && !currentProduct.getPierced().equals("")) {
//            this.cbProductPierced.setSelectedItem(currentProduct.getPierced().toString());
//        }
//        if (currentProduct.getDgnondg() != null) {
//            this.cbProductDg.setSelectedIndex(currentProduct.getDgnondg());
//        }
//        if (currentProduct.getThreadBore() != null) {
//            this.cbProductBore.setSelectedIndex(currentProduct.getThreadBore());
//        }
//        if (currentProduct.getThreadNeck() != null) {
//            this.cbProductNeck.setSelectedIndex(currentProduct.getThreadNeck());
//        }

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

    private int FillPolymerComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Polymer> polymers = this.polymerService.GetAllEntities();
        if (polymers.size() > 0) {
            List<ComboBoxItem<Polymer>> polymerNames = polymers.stream().sorted(comparing(x -> x.getCompany())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCompany() + " " + x.getGrade(), x.getId())).collect(Collectors.toList());
            Polymer polymer = new Polymer();
            polymer.setId(0);
            polymer.setCompany("- Select -");
            polymerNames.add(0, new ComboBoxItem<Polymer>(polymer, polymer.getCompany(), polymer.getId()));
            ComboBoxItem[] polymerNamesArray = polymerNames.toArray(new ComboBoxItem[polymerNames.size()]);
            comboBox.setModel(new DefaultComboBoxModel(polymerNamesArray));
            if (id != 0) {
                ComboBoxItem<Polymer> currentPolymerName = polymerNames.stream().filter(x -> x.getId() == id).findFirst().get();
                result = polymerNames.indexOf(currentPolymerName);
            } else {
                result = 0;
            }
            comboBox.setSelectedIndex(result);
        }
        return result;
    }

    private int FillAdditiveComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Additive> additives = this.additiveService.GetAllEntities();
        if (additives.size() > 0) {
            List<ComboBoxItem<Additive>> additiveNames = additives.stream().sorted(comparing(x -> x.getCompany())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCompany() + " " + x.getGrade(), x.getId())).collect(Collectors.toList());
            Additive additive = new Additive();
            additive.setId(0);
            additive.setCompany("- Select -");
            additiveNames.add(0, new ComboBoxItem<Additive>(additive, additive.getCompany(), additive.getId()));
            ComboBoxItem[] additiveNamesArray = additiveNames.toArray(new ComboBoxItem[additiveNames.size()]);
            comboBox.setModel(new DefaultComboBoxModel(additiveNamesArray));
            if (id != 0) {
                ComboBoxItem<Additive> currentAdditiveName = additiveNames.stream().filter(x -> x.getId() == id).findFirst().get();
                result = additiveNames.indexOf(currentAdditiveName);
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
                List<ComboBoxItem<Entry>> entryNames = entrys.stream().sorted(comparing(x -> x.getCreateDate())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getMachineId() != null ? ((Machine) this.machineService.FindEntity(x.getMachineId())).getMachineNo() : "new" + " " + x.getCreateDate(), x.getId())).collect(Collectors.toList());
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
        jButton2 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        btnEntryUndo = new javax.swing.JButton();
        btnEntrySave = new javax.swing.JButton();
        jLabel163 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        btnMachineNew = new javax.swing.JButton();
        cbMachine = new javax.swing.JComboBox();
        btnMachineDelete = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        btnMachineUndo = new javax.swing.JButton();
        btnMachineSave = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txtMachineNo = new javax.swing.JTextField();
        txtMachineDesc = new javax.swing.JTextField();
        txtMachineManufa = new javax.swing.JTextField();
        txtMachineYear = new javax.swing.JTextField();
        txtMachineSerial = new javax.swing.JTextField();
        txtMachineCapacity = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMouldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMouldCode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMouldVolume = new javax.swing.JTextField();
        txtMouldManufacturer = new javax.swing.JTextField();
        txtMouldYear = new javax.swing.JTextField();
        txtMouldNonDgMin = new javax.swing.JTextField();
        txtMouldNonDgMax = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMouldWeightMin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMouldWeightMax = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMouldTapMin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMouldTapMax = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtMouldUnderHandleMin = new javax.swing.JTextField();
        txtMouldUnderHandleMax = new javax.swing.JTextField();
        txtMouldBaseMin = new javax.swing.JTextField();
        txtMouldBaseMax = new javax.swing.JTextField();
        txtMouldClosureMin = new javax.swing.JTextField();
        txtMouldClosureMax = new javax.swing.JTextField();
        txtMouldHandleBungMin = new javax.swing.JTextField();
        txtMouldHandleBungMax = new javax.swing.JTextField();
        txtMouldHandleLeftMin = new javax.swing.JTextField();
        txtMouldHandleLeftMax = new javax.swing.JTextField();
        txtMouldHandleRightMin = new javax.swing.JTextField();
        txtMouldHandleRightMax = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtMouldDgUnderHandleMin = new javax.swing.JTextField();
        txtMouldDgUnderHandleMax = new javax.swing.JTextField();
        txtMouldDgBaseMin = new javax.swing.JTextField();
        txtMouldDgBaseMax = new javax.swing.JTextField();
        txtMouldDgClosureMin = new javax.swing.JTextField();
        txtMouldDgClosureMax = new javax.swing.JTextField();
        txtMouldDgHandleBungMin = new javax.swing.JTextField();
        txtMouldDgHandleBungMax = new javax.swing.JTextField();
        txtMouldDgHandleLeftMin = new javax.swing.JTextField();
        txtMouldDgHandleLeftMax = new javax.swing.JTextField();
        txtMouldDgHandleRightMin = new javax.swing.JTextField();
        txtMouldDgHandleRightMax = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtMouldSizeA1 = new javax.swing.JTextField();
        txtMouldSizeA1Min = new javax.swing.JTextField();
        txtMouldSizeA1Max = new javax.swing.JTextField();
        txtMouldSizeA2 = new javax.swing.JTextField();
        txtMouldSizeA2Min = new javax.swing.JTextField();
        txtMouldSizeA2Max = new javax.swing.JTextField();
        txtMouldSizeA3 = new javax.swing.JTextField();
        txtMouldSizeA3Min = new javax.swing.JTextField();
        txtMouldSizeA3Max = new javax.swing.JTextField();
        txtMouldSizeB1 = new javax.swing.JTextField();
        txtMouldSizeB1Min = new javax.swing.JTextField();
        txtMouldSizeB1Max = new javax.swing.JTextField();
        txtMouldSizeB2 = new javax.swing.JTextField();
        txtMouldSizeB2Min = new javax.swing.JTextField();
        txtMouldSizeB2Max = new javax.swing.JTextField();
        txtMouldSizeB3 = new javax.swing.JTextField();
        txtMouldSizeB3Min = new javax.swing.JTextField();
        txtMouldSizeB3Max = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        txtMouldSize1 = new javax.swing.JTextField();
        txtMouldSize1Min = new javax.swing.JTextField();
        txtMouldSize1Max = new javax.swing.JTextField();
        txtMouldSize2 = new javax.swing.JTextField();
        txtMouldSize2Min = new javax.swing.JTextField();
        txtMouldSize2Max = new javax.swing.JTextField();
        txtMouldSize3 = new javax.swing.JTextField();
        txtMouldSize3Min = new javax.swing.JTextField();
        txtMouldSize3Max = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        labDrawingImage = new javax.swing.JLabel();
        btnDrawingImage = new javax.swing.JButton();
        pnlDrawingImage = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        labDgImage = new javax.swing.JLabel();
        btnDgImage = new javax.swing.JButton();
        pnlDgImage = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        labNonDgImage = new javax.swing.JLabel();
        btnNonDgImage = new javax.swing.JButton();
        pnlNonDgImage = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        labBoreAImage = new javax.swing.JLabel();
        btnBoreAImage = new javax.swing.JButton();
        pnlBoreAImage = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        labBoreBImage = new javax.swing.JLabel();
        btnBoreBImage = new javax.swing.JButton();
        pnlBoreBImage = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        labNeckImage = new javax.swing.JLabel();
        btnNeckImage = new javax.swing.JButton();
        pnlNeckImage = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        labTapImage = new javax.swing.JLabel();
        btnTapImage = new javax.swing.JButton();
        pnlTapImage = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnMouldNew = new javax.swing.JButton();
        cbMould = new javax.swing.JComboBox();
        btnMouldDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnMouldUndo = new javax.swing.JButton();
        btnMouldSave = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        btnProductNew = new javax.swing.JButton();
        cbProduct = new javax.swing.JComboBox();
        btnProductDelete = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        btnProductUndo = new javax.swing.JButton();
        btnProductSave = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        txtProductCode = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        cbProductBung = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        txtProductDesc = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        cbProductPierced = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        cbProductMould = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        cbProductPolymer = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        cbProductDg = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtProductWeightMin = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtProductWeightMax = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cbProductBore = new javax.swing.JComboBox();
        cbProductNeck = new javax.swing.JComboBox();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        cbProductAdditive1 = new javax.swing.JComboBox();
        txtProductPerc1 = new javax.swing.JTextField();
        cbProductAdditive2 = new javax.swing.JComboBox();
        txtProductPerc2 = new javax.swing.JTextField();
        cbProductAdditive3 = new javax.swing.JComboBox();
        txtProductPerc3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtPolymerGrade = new javax.swing.JTextField();
        txtPolymerCompany = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtPolymerDesc = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        btnPolymerNew = new javax.swing.JButton();
        cbPolymer = new javax.swing.JComboBox();
        btnPolymerDelete = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        btnPolymerUndo = new javax.swing.JButton();
        btnPolymerSave = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtAdditiveGrade = new javax.swing.JTextField();
        txtAdditiveCompany = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        txtAdditiveDesc = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        btnAdditiveNew = new javax.swing.JButton();
        cbAdditive = new javax.swing.JComboBox();
        btnAdditiveDelete = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        btnAdditiveUndo = new javax.swing.JButton();
        btnAdditiveSave = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
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
        setPreferredSize(new java.awt.Dimension(908, 668));

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

        jButton2.setText("Go to Entry Form");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel47.add(jButton2, gridBagConstraints);

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

        tabSettings.addTab("Shift", jPanel38);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jPanel20.setLayout(new java.awt.GridBagLayout());

        btnMachineNew.setText("New");
        btnMachineNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMachineNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel20.add(btnMachineNew, gridBagConstraints);

        cbMachine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMachineActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel20.add(cbMachine, gridBagConstraints);

        btnMachineDelete.setText("Delete");
        btnMachineDelete.setToolTipText("");
        btnMachineDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMachineDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel20.add(btnMachineDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel19.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        btnMachineUndo.setText("Undo");
        btnMachineUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMachineUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel21.add(btnMachineUndo, gridBagConstraints);

        btnMachineSave.setText("Save");
        btnMachineSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMachineSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel21.add(btnMachineSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel21.add(jLabel92, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel19.add(jPanel21, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel93.setText("MACHINE No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel93, gridBagConstraints);

        jLabel94.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel94, gridBagConstraints);

        jLabel95.setText("MANUFACTURER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel95, gridBagConstraints);

        jLabel96.setText("YEAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel96, gridBagConstraints);

        jLabel97.setText("SERIAL No");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel97, gridBagConstraints);

        jLabel98.setText("CAPACITY / VOL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanel22.add(jLabel98, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineNo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineDesc, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineManufa, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineYear, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineSerial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        jPanel22.add(txtMachineCapacity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel19.add(jPanel22, gridBagConstraints);

        tabSettings.addTab("Machine", jPanel19);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 4, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        txtMouldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMouldNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 4, 0);
        jPanel2.add(txtMouldName, gridBagConstraints);

        jLabel2.setText("PRODUCT WEIGHT DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 24, 4, 22);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("CODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldCode, gridBagConstraints);

        jLabel4.setText("VOLUME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("MANUFACTURER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("YEAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel8.setText("PRODUCT WEIGHT FOR NON-DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 16, 0);
        jPanel2.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldVolume, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldManufacturer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldYear, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldNonDgMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 16, 0);
        jPanel2.add(txtMouldNonDgMax, gridBagConstraints);

        jLabel11.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldWeightMin, gridBagConstraints);

        jLabel12.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldWeightMax, gridBagConstraints);

        jLabel13.setText("TAP POSITION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 22);
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel14.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldTapMin, gridBagConstraints);

        jLabel15.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldTapMax, gridBagConstraints);

        jTabbedPane3.addTab("General", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel17.setText("WALL THICKNESS NON-DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel17, gridBagConstraints);

        jLabel18.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(jLabel18, gridBagConstraints);

        jLabel19.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(jLabel19, gridBagConstraints);

        jLabel20.setText("UNDER THE HANDLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel20, gridBagConstraints);

        jLabel21.setText("BASE (CENTRE)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel21, gridBagConstraints);

        jLabel22.setText("CLOSURE SIDE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel22, gridBagConstraints);

        jLabel23.setText("END OF HANDLE SIDE - BUNG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel23, gridBagConstraints);

        jLabel24.setText("END OF HANDLE SIDE - LEFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel24, gridBagConstraints);

        jLabel25.setText("END OF HANDLE SIDE - RIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldUnderHandleMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldUnderHandleMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldBaseMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldBaseMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldClosureMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldClosureMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldHandleBungMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldHandleBungMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldHandleLeftMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldHandleLeftMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel3.add(txtMouldHandleRightMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel3.add(txtMouldHandleRightMax, gridBagConstraints);

        jTabbedPane3.addTab("Wall Non-DG", jPanel3);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel26.setText("WALL THICKNESS - DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel26, gridBagConstraints);

        jLabel27.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(jLabel27, gridBagConstraints);

        jLabel28.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(jLabel28, gridBagConstraints);

        jLabel29.setText("UNDER THE HANDLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel29, gridBagConstraints);

        jLabel30.setText("BASE (CENTRE)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel30, gridBagConstraints);

        jLabel31.setText("CLOSURE SIDE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel31, gridBagConstraints);

        jLabel32.setText("END OF HANDLE SIDE - BUNG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel32, gridBagConstraints);

        jLabel33.setText("END OF HANDLE SIDE - LEFT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel33, gridBagConstraints);

        jLabel34.setText("END OF HANDLE SIDE - RIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel34, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgUnderHandleMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgUnderHandleMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgBaseMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgBaseMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgClosureMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgClosureMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgHandleBungMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgHandleBungMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgHandleLeftMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgHandleLeftMax, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 44);
        jPanel8.add(txtMouldDgHandleRightMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 28);
        jPanel8.add(txtMouldDgHandleRightMax, gridBagConstraints);

        jTabbedPane3.addTab("Wall DG", jPanel8);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel35.setText("BORE DIAMETER A");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel35, gridBagConstraints);

        jLabel36.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel36, gridBagConstraints);

        jLabel37.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(jLabel37, gridBagConstraints);

        jLabel38.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel38, gridBagConstraints);

        jLabel39.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel39, gridBagConstraints);

        jLabel40.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel40, gridBagConstraints);

        jLabel41.setText("BORE DIAMETER B");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel41, gridBagConstraints);

        jLabel42.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel42, gridBagConstraints);

        jLabel43.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel43, gridBagConstraints);

        jLabel44.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel44, gridBagConstraints);

        jLabel45.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel45, gridBagConstraints);

        jLabel46.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(jLabel46, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA1Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeA1Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA2Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeA2Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeA3Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeA3Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB1Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeB1Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB2Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeB2Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSizeB3Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSizeB3Max, gridBagConstraints);

        jLabel48.setText("NECK HEIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel48, gridBagConstraints);

        jLabel51.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel51, gridBagConstraints);

        jLabel52.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(jLabel52, gridBagConstraints);

        jLabel80.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel80, gridBagConstraints);

        jLabel83.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel83, gridBagConstraints);

        jLabel84.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(jLabel84, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize1Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSize1Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize2Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSize2Max, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel4.add(txtMouldSize3Min, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 20);
        jPanel4.add(txtMouldSize3Max, gridBagConstraints);

        jLabel85.setText("THREAD SIZE A");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jLabel85, gridBagConstraints);

        jLabel86.setText("THREAD SIZE B");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel4.add(jLabel86, gridBagConstraints);

        jLabel87.setText("THREAD SIZE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        jPanel4.add(jLabel87, gridBagConstraints);

        jTabbedPane3.addTab("Thread", jPanel4);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        labDrawingImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel11.add(labDrawingImage, gridBagConstraints);

        btnDrawingImage.setText("Browse");
        btnDrawingImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawingImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel11.add(btnDrawingImage, gridBagConstraints);

        pnlDrawingImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel11.add(pnlDrawingImage, gridBagConstraints);

        jLabel49.setText("Drawing Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel11.add(jLabel49, gridBagConstraints);

        jTabbedPane1.addTab("Drawing", jPanel11);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        labDgImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel17.add(labDgImage, gridBagConstraints);

        btnDgImage.setText("Browse");
        btnDgImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDgImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel17.add(btnDgImage, gridBagConstraints);

        pnlDgImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel17.add(pnlDgImage, gridBagConstraints);

        jLabel50.setText("DG Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel17.add(jLabel50, gridBagConstraints);

        jTabbedPane1.addTab("DG", jPanel17);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        labNonDgImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel23.add(labNonDgImage, gridBagConstraints);

        btnNonDgImage.setText("Browse");
        btnNonDgImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNonDgImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel23.add(btnNonDgImage, gridBagConstraints);

        pnlNonDgImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel23.add(pnlNonDgImage, gridBagConstraints);

        jLabel64.setText("Non-DG Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel23.add(jLabel64, gridBagConstraints);

        jTabbedPane1.addTab("Non-DG", jPanel23);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        labBoreAImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel24.add(labBoreAImage, gridBagConstraints);

        btnBoreAImage.setText("Browse");
        btnBoreAImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoreAImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel24.add(btnBoreAImage, gridBagConstraints);

        pnlBoreAImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel24.add(pnlBoreAImage, gridBagConstraints);

        jLabel91.setText("Bore A Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel24.add(jLabel91, gridBagConstraints);

        jTabbedPane1.addTab("Bore A", jPanel24);

        jPanel32.setLayout(new java.awt.GridBagLayout());

        labBoreBImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel32.add(labBoreBImage, gridBagConstraints);

        btnBoreBImage.setText("Browse");
        btnBoreBImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoreBImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel32.add(btnBoreBImage, gridBagConstraints);

        pnlBoreBImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel32.add(pnlBoreBImage, gridBagConstraints);

        jLabel99.setText("Bore B Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel32.add(jLabel99, gridBagConstraints);

        jTabbedPane1.addTab("Bore B", jPanel32);

        jPanel33.setLayout(new java.awt.GridBagLayout());

        labNeckImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel33.add(labNeckImage, gridBagConstraints);

        btnNeckImage.setText("Browse");
        btnNeckImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNeckImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel33.add(btnNeckImage, gridBagConstraints);

        pnlNeckImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel33.add(pnlNeckImage, gridBagConstraints);

        jLabel102.setText("Neck Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel33.add(jLabel102, gridBagConstraints);

        jTabbedPane1.addTab("Neck", jPanel33);

        jPanel37.setLayout(new java.awt.GridBagLayout());

        labTapImage.setText("Image File Path");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel37.add(labTapImage, gridBagConstraints);

        btnTapImage.setText("Browse");
        btnTapImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTapImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel37.add(btnTapImage, gridBagConstraints);

        pnlTapImage.setPreferredSize(new java.awt.Dimension(640, 640));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 18, 7, 10);
        jPanel37.add(pnlTapImage, gridBagConstraints);

        jLabel103.setText("Tap Image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(14, 25, 14, 14);
        jPanel37.add(jLabel103, gridBagConstraints);

        jTabbedPane1.addTab("Tap", jPanel37);

        jTabbedPane3.addTab("Images", jTabbedPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel16.add(jTabbedPane3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnMouldNew.setText("New");
        btnMouldNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMouldNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel5.add(btnMouldNew, gridBagConstraints);

        cbMould.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMouldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel5.add(cbMould, gridBagConstraints);

        btnMouldDelete.setText("Delete");
        btnMouldDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMouldDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel5.add(btnMouldDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel16.add(jPanel5, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnMouldUndo.setText("Undo");
        btnMouldUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMouldUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel1.add(btnMouldUndo, gridBagConstraints);

        btnMouldSave.setText("Save");
        btnMouldSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMouldSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel1.add(btnMouldSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(jLabel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel16.add(jPanel1, gridBagConstraints);

        tabSettings.addTab("Mould", jPanel16);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jPanel26.setLayout(new java.awt.GridBagLayout());

        btnProductNew.setText("New");
        btnProductNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel26.add(btnProductNew, gridBagConstraints);

        cbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel26.add(cbProduct, gridBagConstraints);

        btnProductDelete.setText("Delete");
        btnProductDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel26.add(btnProductDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel25.add(jPanel26, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        btnProductUndo.setText("Undo");
        btnProductUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel27.add(btnProductUndo, gridBagConstraints);

        btnProductSave.setText("Save");
        btnProductSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel27.add(btnProductSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel27.add(jLabel100, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel25.add(jPanel27, gridBagConstraints);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        jLabel53.setText("CODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel53, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(txtProductCode, gridBagConstraints);

        jLabel54.setText("BUNG REQUIRED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel54, gridBagConstraints);

        cbProductBung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductBung, gridBagConstraints);

        jLabel55.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel55, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(txtProductDesc, gridBagConstraints);

        jLabel56.setText("PIERCED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel56, gridBagConstraints);

        cbProductPierced.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductPierced, gridBagConstraints);

        jLabel57.setText("MOULD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel57, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(cbProductMould, gridBagConstraints);

        jLabel58.setText("POLYMER TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel58, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductPolymer, gridBagConstraints);

        jLabel59.setText("DG OR NON-DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel59, gridBagConstraints);

        cbProductDg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DG-UNDER THE HANDLE", "DG-BASE(CENTRE)", "DG-CLOSURE SIDE", "DG-END OF HANDLE SIDE-BUNG", "DG-END OF HANDLE SIDE-LEFT", "DG-END OF HANDLE SIDE-RIGHT", "NONDG-UNDER THE HANDLE", "NONDG-BASE(CENTRE)", "NONDG-CLOSURE SIDE", "NONDG-END OF HANDLE SIDE-BUNG", "NONDG-END OF HANDLE SIDE-LEFT", "NONDG-END OF HANDLE SIDE-RIGHT" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(cbProductDg, gridBagConstraints);

        jLabel60.setText("ADDITIVE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel60, gridBagConstraints);

        jLabel61.setText("WEIGHT RANGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel61, gridBagConstraints);

        jLabel62.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel62, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(txtProductWeightMin, gridBagConstraints);

        jLabel63.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel63, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(txtProductWeightMax, gridBagConstraints);

        jLabel65.setText("BORE DIAMETRE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel65, gridBagConstraints);

        jLabel66.setText("NECK HEIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel66, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(cbProductBore, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(cbProductNeck, gridBagConstraints);

        jLabel67.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel67, gridBagConstraints);

        jLabel68.setText("PERCENTAGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel68, gridBagConstraints);

        jLabel69.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel69, gridBagConstraints);

        jLabel70.setText("PERCENTAGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel70, gridBagConstraints);

        jLabel71.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel71, gridBagConstraints);

        jLabel72.setText("PERCENTAGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel28.add(jLabel72, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductAdditive1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(txtProductPerc1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductAdditive2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(txtProductPerc2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(cbProductAdditive3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel28.add(txtProductPerc3, gridBagConstraints);

        jLabel7.setText("THREAD TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel25.add(jPanel28, gridBagConstraints);

        tabSettings.addTab("Product", jPanel25);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 89);
        jPanel6.add(txtPolymerGrade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 89);
        jPanel6.add(txtPolymerCompany, gridBagConstraints);

        jLabel81.setText("GRADE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel6.add(jLabel81, gridBagConstraints);

        jLabel79.setText("COMPANY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 0);
        jPanel6.add(jLabel79, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 89);
        jPanel6.add(txtPolymerDesc, gridBagConstraints);

        jLabel82.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 0);
        jPanel6.add(jLabel82, gridBagConstraints);

        jLabel88.setText("POLYMER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(12, 8, 8, 8);
        jPanel6.add(jLabel88, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(jPanel6, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        btnPolymerNew.setText("New");
        btnPolymerNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPolymerNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel18.add(btnPolymerNew, gridBagConstraints);

        cbPolymer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPolymerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel18.add(cbPolymer, gridBagConstraints);

        btnPolymerDelete.setText("Delete");
        btnPolymerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPolymerDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel18.add(btnPolymerDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel10.add(jPanel18, gridBagConstraints);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        btnPolymerUndo.setText("Undo");
        btnPolymerUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPolymerUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel34.add(btnPolymerUndo, gridBagConstraints);

        btnPolymerSave.setText("Save");
        btnPolymerSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPolymerSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel34.add(btnPolymerSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel34.add(jLabel105, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel10.add(jPanel34, gridBagConstraints);

        jTabbedPane5.addTab("Polymer", jPanel10);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 89);
        jPanel7.add(txtAdditiveGrade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 89);
        jPanel7.add(txtAdditiveCompany, gridBagConstraints);

        jLabel106.setText("GRADE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel7.add(jLabel106, gridBagConstraints);

        jLabel107.setText("COMPANY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 0);
        jPanel7.add(jLabel107, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 89);
        jPanel7.add(txtAdditiveDesc, gridBagConstraints);

        jLabel108.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 0);
        jPanel7.add(jLabel108, gridBagConstraints);

        jLabel109.setText("ADDITIVE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(12, 8, 8, 8);
        jPanel7.add(jLabel109, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel14.add(jPanel7, gridBagConstraints);

        jPanel35.setLayout(new java.awt.GridBagLayout());

        btnAdditiveNew.setText("New");
        btnAdditiveNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdditiveNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel35.add(btnAdditiveNew, gridBagConstraints);

        cbAdditive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdditiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel35.add(cbAdditive, gridBagConstraints);

        btnAdditiveDelete.setText("Delete");
        btnAdditiveDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdditiveDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel35.add(btnAdditiveDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel14.add(jPanel35, gridBagConstraints);

        jPanel36.setLayout(new java.awt.GridBagLayout());

        btnAdditiveUndo.setText("Undo");
        btnAdditiveUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdditiveUndoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel36.add(btnAdditiveUndo, gridBagConstraints);

        btnAdditiveSave.setText("Save");
        btnAdditiveSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdditiveSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel36.add(btnAdditiveSave, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel36.add(jLabel110, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel14.add(jPanel36, gridBagConstraints);

        jTabbedPane5.addTab("Additive", jPanel14);

        tabSettings.addTab("Raw Material", jTabbedPane5);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSettings)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        currentStaff.setDefaultValue();
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

    private void cbMachineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMachineActionPerformed
        // TODO add your handling code here:
        Machine currentItem = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
        //
        this.txtMachineCapacity.setText(currentItem.getCapacity());
        this.txtMachineDesc.setText(currentItem.getDescription());
        this.txtMachineManufa.setText(currentItem.getManufacturer());
        this.txtMachineNo.setText(currentItem.getMachineNo());
        this.txtMachineSerial.setText(currentItem.getSerialNo());
        this.txtMachineYear.setText(currentItem.getYear());
    }//GEN-LAST:event_cbMachineActionPerformed

    private void btnMachineNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMachineNewActionPerformed
        // TODO add your handling code here:
        int newId = this.machineService.CreateEntity();
        UpdateTabMachine(newId);
    }//GEN-LAST:event_btnMachineNewActionPerformed

    private void btnMachineDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMachineDeleteActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
            if ("- Select -".equals(currentMachine.getMachineNo())) {
                return;
            }
            this.machineService.DeleteEntity(currentMachine.getId());
            this.UpdateTabMachine(0);
        }
    }//GEN-LAST:event_btnMachineDeleteActionPerformed

    private void btnMachineSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMachineSaveActionPerformed
        // TODO add your handling code here:
        Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
        if ("- Select -".equals(currentMachine.getMachineNo())) {
            return;
        }
        currentMachine.setCapacity(this.txtMachineCapacity.getText());
        currentMachine.setDescription(this.txtMachineDesc.getText());
        currentMachine.setMachineNo(this.txtMachineNo.getText());
        currentMachine.setManufacturer(this.txtMachineManufa.getText());
        currentMachine.setSerialNo(this.txtMachineSerial.getText());
        currentMachine.setYear(this.txtMachineYear.getText());
        this.machineService.UpdateEntity(currentMachine);
        this.UpdateTabMachine(currentMachine.getId());
    }//GEN-LAST:event_btnMachineSaveActionPerformed

    private void btnMachineUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMachineUndoActionPerformed
        // TODO add your handling code here:
        Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
        this.UpdateTabMachine(currentMachine.getId());
    }//GEN-LAST:event_btnMachineUndoActionPerformed

    private void cbPolymerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPolymerActionPerformed
        Polymer currentItem = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
        //
        this.txtPolymerCompany.setText(currentItem.getCompany());
        this.txtPolymerDesc.setText(currentItem.getDescription());
        this.txtPolymerGrade.setText(currentItem.getGrade());
    }//GEN-LAST:event_cbPolymerActionPerformed

    private void btnPolymerNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolymerNewActionPerformed
        int newId = this.polymerService.CreateEntity();
        UpdateTabPolymer(newId);
    }//GEN-LAST:event_btnPolymerNewActionPerformed

    private void btnPolymerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolymerDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
            if ("- Select -".equals(currentPolymer.getCompany())) {
                return;
            }
            this.polymerService.DeleteEntity(currentPolymer.getId());
            this.UpdateTabPolymer(0);
        }
    }//GEN-LAST:event_btnPolymerDeleteActionPerformed

    private void btnPolymerSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolymerSaveActionPerformed
        Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
        if ("- Select -".equals(currentPolymer.getCompany())) {
            return;
        }
        currentPolymer.setCompany(this.txtPolymerCompany.getText());
        currentPolymer.setDescription(this.txtPolymerDesc.getText());
        currentPolymer.setGrade(this.txtPolymerGrade.getText());
        this.polymerService.UpdateEntity(currentPolymer);
        this.UpdateTabPolymer(currentPolymer.getId());
    }//GEN-LAST:event_btnPolymerSaveActionPerformed

    private void btnPolymerUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolymerUndoActionPerformed
        Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
        this.UpdateTabPolymer(currentPolymer.getId());
    }//GEN-LAST:event_btnPolymerUndoActionPerformed

    private void btnAdditiveNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveNewActionPerformed
        int newId = this.additiveService.CreateEntity();
        UpdateTabAdditive(newId);
    }//GEN-LAST:event_btnAdditiveNewActionPerformed

    private void btnAdditiveDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
            if ("- Select -".equals(currentAdditive.getCompany())) {
                return;
            }
            this.additiveService.DeleteEntity(currentAdditive.getId());
            this.UpdateTabAdditive(0);
        }
    }//GEN-LAST:event_btnAdditiveDeleteActionPerformed

    private void btnAdditiveSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveSaveActionPerformed
        Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
        if ("- Select -".equals(currentAdditive.getCompany())) {
            return;
        }
        currentAdditive.setCompany(this.txtAdditiveCompany.getText());
        currentAdditive.setDescription(this.txtAdditiveDesc.getText());
        currentAdditive.setGrade(this.txtAdditiveGrade.getText());
        this.additiveService.UpdateEntity(currentAdditive);
        this.UpdateTabAdditive(currentAdditive.getId());
    }//GEN-LAST:event_btnAdditiveSaveActionPerformed

    private void btnAdditiveUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveUndoActionPerformed
        Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
        this.UpdateTabAdditive(currentAdditive.getId());
    }//GEN-LAST:event_btnAdditiveUndoActionPerformed

    private void cbAdditiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdditiveActionPerformed
        Additive currentItem = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
        //
        this.txtAdditiveCompany.setText(currentItem.getCompany());
        this.txtAdditiveDesc.setText(currentItem.getDescription());
        this.txtAdditiveGrade.setText(currentItem.getGrade());
    }//GEN-LAST:event_cbAdditiveActionPerformed

    private void cbMouldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMouldActionPerformed
        Mould currentItem = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
        this.settingMouldId = currentItem.getId();
        UpdateMouldUI(currentItem);


    }//GEN-LAST:event_cbMouldActionPerformed

    private void UpdateMouldUI(Mould currentMould) {
        //
        this.txtMouldBaseMax.setText(currentMould.getWallNonDgBaseMax() == null ? "" : currentMould.getWallNonDgBaseMax().toString());
        this.txtMouldBaseMin.setText(currentMould.getWallNonDgBaseMin() == null ? "" : currentMould.getWallNonDgBaseMin().toString());
        this.txtMouldClosureMax.setText(currentMould.getWallNonDgClosureMax() == null ? "" : currentMould.getWallNonDgClosureMax().toString());
        this.txtMouldClosureMin.setText(currentMould.getWallNonDgClosureMin() == null ? "" : currentMould.getWallNonDgClosureMin().toString());
        this.txtMouldCode.setText(currentMould.getCode() == null || currentMould.getCode() == "- Select -" ? "" : currentMould.getCode().toString());
        this.txtMouldDgBaseMax.setText(currentMould.getWallDgBaseMax() == null ? "" : currentMould.getWallDgBaseMax().toString());
        this.txtMouldDgBaseMin.setText(currentMould.getWallDgBaseMin() == null ? "" : currentMould.getWallDgBaseMin().toString());
        this.txtMouldDgClosureMax.setText(currentMould.getWallDgClosureMax() == null ? "" : currentMould.getWallDgClosureMax().toString());
        this.txtMouldDgClosureMin.setText(currentMould.getWallDgClosureMin() == null ? "" : currentMould.getWallDgClosureMin().toString());
        this.txtMouldDgHandleBungMax.setText(currentMould.getWallDgHandleBungMax() == null ? "" : currentMould.getWallDgHandleBungMax().toString());
        this.txtMouldDgHandleBungMin.setText(currentMould.getWallDgHandleBungMin() == null ? "" : currentMould.getWallDgHandleBungMin().toString());
        this.txtMouldDgHandleLeftMax.setText(currentMould.getWallDgHandleLeftMax() == null ? "" : currentMould.getWallDgHandleLeftMax().toString());
        this.txtMouldDgHandleLeftMin.setText(currentMould.getWallDgHandleLeftMin() == null ? "" : currentMould.getWallDgHandleLeftMin().toString());
        this.txtMouldDgHandleRightMax.setText(currentMould.getWallDgHandleRightMax() == null ? "" : currentMould.getWallDgHandleRightMax().toString());
        this.txtMouldDgHandleRightMin.setText(currentMould.getWallDgHandRightMin() == null ? "" : currentMould.getWallDgHandRightMin().toString());
        this.txtMouldDgUnderHandleMax.setText(currentMould.getWallDgUnderHandleMax() == null ? "" : currentMould.getWallDgUnderHandleMax().toString());
        this.txtMouldDgUnderHandleMin.setText(currentMould.getWallDgUnderHandleMin() == null ? "" : currentMould.getWallDgUnderHandleMin().toString());
        this.txtMouldHandleBungMax.setText(currentMould.getWallNonDgHandleBungMax() == null ? "" : currentMould.getWallNonDgHandleBungMax().toString());
        this.txtMouldHandleBungMin.setText(currentMould.getWallNonDgHandleBungMin() == null ? "" : currentMould.getWallNonDgHandleBungMin().toString());
        this.txtMouldHandleLeftMax.setText(currentMould.getWallNonDgHandleLeftMax() == null ? "" : currentMould.getWallNonDgHandleLeftMax().toString());
        this.txtMouldHandleLeftMin.setText(currentMould.getWallNonDgHandleLeftMin() == null ? "" : currentMould.getWallNonDgHandleLeftMin().toString());
        this.txtMouldHandleRightMax.setText(currentMould.getWallNonDgHandleRightMax() == null ? "" : currentMould.getWallNonDgHandleRightMax().toString());
        this.txtMouldHandleRightMin.setText(currentMould.getWallNonDgHandleRightMin() == null ? "" : currentMould.getWallNonDgHandleRightMin().toString());
        this.txtMouldManufacturer.setText(currentMould.getManufacturer() == null ? "" : currentMould.getManufacturer().toString());
        this.txtMouldName.setText(currentMould.getName() == null ? "" : currentMould.getName().toString());
        this.txtMouldNonDgMax.setText(currentMould.getWeightNonDgMax() == null ? "" : currentMould.getWeightNonDgMax().toString());
        this.txtMouldNonDgMin.setText(currentMould.getWeightNonDgMin() == null ? "" : currentMould.getWeightNonDgMin().toString());
        this.txtMouldSize1.setText(currentMould.getThreadNeckSize1() == null ? "" : currentMould.getThreadNeckSize1().toString());
        this.txtMouldSize1Max.setText(currentMould.getThreadNeckMax1() == null ? "" : currentMould.getThreadNeckMax1().toString());
        this.txtMouldSize1Min.setText(currentMould.getThreadNeckMin1() == null ? "" : currentMould.getThreadNeckMin1().toString());
        this.txtMouldSize2.setText(currentMould.getThreadNeckSize2() == null ? "" : currentMould.getThreadNeckSize2().toString());
        this.txtMouldSize2Max.setText(currentMould.getThreadNeckMax2() == null ? "" : currentMould.getThreadNeckMax2().toString());
        this.txtMouldSize2Min.setText(currentMould.getThreadNeckMin2() == null ? "" : currentMould.getThreadNeckMin2().toString());
        this.txtMouldSize3.setText(currentMould.getThreadNeckSize3() == null ? "" : currentMould.getThreadNeckSize3().toString());
        this.txtMouldSize3Max.setText(currentMould.getThreadNeckMax3() == null ? "" : currentMould.getThreadNeckMax3().toString());
        this.txtMouldSize3Min.setText(currentMould.getThreadNeckMin3() == null ? "" : currentMould.getThreadNeckMin3().toString());
        this.txtMouldSizeA1.setText(currentMould.getThreadBoreASize1() == null ? "" : currentMould.getThreadBoreASize1().toString());
        this.txtMouldSizeA1Max.setText(currentMould.getThreadBoreAMax1() == null ? "" : currentMould.getThreadBoreAMax1().toString());
        this.txtMouldSizeA1Min.setText(currentMould.getThreadBoreAMin1() == null ? "" : currentMould.getThreadBoreAMin1().toString());
        this.txtMouldSizeA2.setText(currentMould.getThreadBoreASize2() == null ? "" : currentMould.getThreadBoreASize2().toString());
        this.txtMouldSizeA2Max.setText(currentMould.getThreadBoreAMax2() == null ? "" : currentMould.getThreadBoreAMax2().toString());
        this.txtMouldSizeA2Min.setText(currentMould.getThreadBoreAMin2() == null ? "" : currentMould.getThreadBoreAMin2().toString());
        this.txtMouldSizeA3.setText(currentMould.getThreadBoreASize3() == null ? "" : currentMould.getThreadBoreASize3().toString());
        this.txtMouldSizeA3Max.setText(currentMould.getThreadBoreAMax3() == null ? "" : currentMould.getThreadBoreAMax3().toString());
        this.txtMouldSizeA3Min.setText(currentMould.getThreadBoreAMin3() == null ? "" : currentMould.getThreadBoreAMin3().toString());
        this.txtMouldSizeB1.setText(currentMould.getThreadBoreBSize1() == null ? "" : currentMould.getThreadBoreBSize1().toString());
        this.txtMouldSizeB1Max.setText(currentMould.getThreadBoreBMax1() == null ? "" : currentMould.getThreadBoreBMax1().toString());
        this.txtMouldSizeB1Min.setText(currentMould.getThreadBoreBMin1() == null ? "" : currentMould.getThreadBoreBMin1().toString());
        this.txtMouldSizeB2.setText(currentMould.getThreadBoreBSize2() == null ? "" : currentMould.getThreadBoreBSize2().toString());
        this.txtMouldSizeB2Max.setText(currentMould.getThreadBoreBMax2() == null ? "" : currentMould.getThreadBoreBMax2().toString());
        this.txtMouldSizeB2Min.setText(currentMould.getThreadBoreBMin2() == null ? "" : currentMould.getThreadBoreBMin2().toString());
        this.txtMouldSizeB3.setText(currentMould.getThreadBoreBSize3() == null ? "" : currentMould.getThreadBoreBSize3().toString());
        this.txtMouldSizeB3Max.setText(currentMould.getThreadBoreBMax3() == null ? "" : currentMould.getThreadBoreBMax3().toString());
        this.txtMouldSizeB3Min.setText(currentMould.getThreadBoreBMin3() == null ? "" : currentMould.getThreadBoreBMin3().toString());
        this.txtMouldTapMax.setText(currentMould.getTapPositionMax() == null ? "" : currentMould.getTapPositionMax().toString());
        this.txtMouldTapMin.setText(currentMould.getTapPositionMin() == null ? "" : currentMould.getTapPositionMin().toString());
        this.txtMouldUnderHandleMax.setText(currentMould.getWallNonDgUnderHandleMax() == null ? "" : currentMould.getWallNonDgUnderHandleMax().toString());
        this.txtMouldUnderHandleMin.setText(currentMould.getWallNonDgUnderHandleMin() == null ? "" : currentMould.getWallNonDgUnderHandleMin().toString());
        this.txtMouldVolume.setText(currentMould.getVolumn() == null ? "" : currentMould.getVolumn().toString());
        this.txtMouldWeightMax.setText(currentMould.getWeightDgMax() == null ? "" : currentMould.getWeightDgMax().toString());
        this.txtMouldWeightMin.setText(currentMould.getWeightDgMin() == null ? "" : currentMould.getWeightDgMin().toString());
        this.txtMouldYear.setText(currentMould.getYear() == null ? "" : currentMould.getYear().toString());
        if (currentMould.getImageDrawing() != null) {
            AppHelper.displayImage(currentMould.getImageDrawing().toString(), this.pnlDrawingImage, this.labDrawingImage);
        } else {
            this.labDrawingImage.setText("Image File Path");
            this.pnlDrawingImage.removeAll();
        }
        if (currentMould.getImageDg() != null) {
            AppHelper.displayImage(currentMould.getImageDg().toString(), this.pnlDgImage, this.labDgImage);
        } else {
            this.labDgImage.setText("Image File Path");
            this.pnlDgImage.removeAll();
        }
        if (currentMould.getImageNonDg() != null) {
            AppHelper.displayImage(currentMould.getImageNonDg().toString(), this.pnlNonDgImage, this.labNonDgImage);
        } else {
            this.labNonDgImage.setText("Image File Path");
            this.pnlNonDgImage.removeAll();
        }
        if (currentMould.getImageTap() != null) {
            AppHelper.displayImage(currentMould.getImageTap().toString(), this.pnlTapImage, this.labTapImage);
        } else {
            this.labTapImage.setText("Image File Path");
            this.pnlTapImage.removeAll();
        }
        if (currentMould.getImageNeck() != null) {
            AppHelper.displayImage(currentMould.getImageNeck().toString(), this.pnlNeckImage, this.labNeckImage);
        } else {
            this.labNeckImage.setText("Image File Path");
            this.pnlNeckImage.removeAll();
        }
        if (currentMould.getImageBoreA() != null) {
            AppHelper.displayImage(currentMould.getImageBoreA().toString(), this.pnlBoreAImage, this.labBoreAImage);
        } else {
            this.labBoreAImage.setText("Image File Path");
            this.pnlBoreAImage.removeAll();
        }
        if (currentMould.getImageBoreB() != null) {
            AppHelper.displayImage(currentMould.getImageBoreB().toString(), this.pnlBoreBImage, this.labBoreBImage);
        } else {
            this.labBoreBImage.setText("Image File Path");
            this.pnlBoreBImage.removeAll();
        }
    }

    private void btnMouldNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldNewActionPerformed
        int newId = this.mouldService.CreateEntity();
        UpdateTabMould(newId);
    }//GEN-LAST:event_btnMouldNewActionPerformed

    private void btnMouldDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
            if ("- Select -".equals(currentMould.getCode())) {
                return;
            }
            this.mouldService.DeleteEntity(currentMould.getId());
            this.UpdateTabMould(0);
        }
    }//GEN-LAST:event_btnMouldDeleteActionPerformed

    private void btnMouldSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldSaveActionPerformed
        Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
        if ("- Select -".equals(currentMould.getCode())) {
            return;
        }
        if (!this.txtMouldBaseMax.getText().equals("")) {
            currentMould.setWallNonDgBaseMax(Float.parseFloat(this.txtMouldBaseMax.getText()));
        }
        if (!this.txtMouldBaseMin.getText().equals("")) {
            currentMould.setWallNonDgBaseMin(Float.parseFloat(this.txtMouldBaseMin.getText()));
        }
        if (!this.txtMouldClosureMax.getText().equals("")) {
            currentMould.setWallNonDgClosureMax(Float.parseFloat(this.txtMouldClosureMax.getText()));
        }
        if (!this.txtMouldClosureMin.getText().equals("")) {
            currentMould.setWallNonDgClosureMin(Float.parseFloat(this.txtMouldClosureMin.getText()));
        }
        if (!this.txtMouldCode.getText().equals("")) {
            currentMould.setCode(this.txtMouldCode.getText());
        }
        if (!this.txtMouldDgBaseMax.getText().equals("")) {
            currentMould.setWallDgBaseMax(Float.parseFloat(this.txtMouldDgBaseMax.getText()));
        }
        if (!this.txtMouldDgBaseMin.getText().equals("")) {
            currentMould.setWallDgBaseMin(Float.parseFloat(this.txtMouldDgBaseMin.getText()));
        }
        if (!this.txtMouldDgClosureMax.getText().equals("")) {
            currentMould.setWallDgClosureMax(Float.parseFloat(this.txtMouldDgClosureMax.getText()));
        }
        if (!this.txtMouldDgClosureMin.getText().equals("")) {
            currentMould.setWallDgClosureMin(Float.parseFloat(this.txtMouldDgClosureMin.getText()));
        }
        if (!this.txtMouldDgHandleBungMax.getText().equals("")) {
            currentMould.setWallDgHandleBungMax(Float.parseFloat(this.txtMouldDgHandleBungMax.getText()));
        }
        if (!this.txtMouldDgHandleBungMin.getText().equals("")) {
            currentMould.setWallDgHandleBungMin(Float.parseFloat(this.txtMouldDgHandleBungMin.getText()));
        }
        if (!this.txtMouldDgHandleLeftMax.getText().equals("")) {
            currentMould.setWallDgHandleLeftMax(Float.parseFloat(this.txtMouldDgHandleLeftMax.getText()));
        }
        if (!this.txtMouldDgHandleLeftMin.getText().equals("")) {
            currentMould.setWallDgHandleLeftMin(Float.parseFloat(this.txtMouldDgHandleLeftMin.getText()));
        }
        if (!this.txtMouldDgHandleRightMax.getText().equals("")) {
            currentMould.setWallDgHandleRightMax(Float.parseFloat(this.txtMouldDgHandleRightMax.getText()));
        }
        if (!this.txtMouldDgHandleRightMin.getText().equals("")) {
            currentMould.setWallDgHandRightMin(Float.parseFloat(this.txtMouldDgHandleRightMin.getText()));
        }
        if (!this.txtMouldDgUnderHandleMax.getText().equals("")) {
            currentMould.setWallDgUnderHandleMax(Float.parseFloat(this.txtMouldDgUnderHandleMax.getText()));
        }
        if (!this.txtMouldDgUnderHandleMin.getText().equals("")) {
            currentMould.setWallDgUnderHandleMin(Float.parseFloat(this.txtMouldDgUnderHandleMin.getText()));
        }
        if (!this.txtMouldHandleBungMax.getText().equals("")) {
            currentMould.setWallNonDgHandleBungMax(Float.parseFloat(this.txtMouldHandleBungMax.getText()));
        }
        if (!this.txtMouldHandleBungMin.getText().equals("")) {
            currentMould.setWallNonDgHandleBungMin(Float.parseFloat(this.txtMouldHandleBungMin.getText()));
        }
        if (!this.txtMouldHandleLeftMax.getText().equals("")) {
            currentMould.setWallNonDgHandleLeftMax(Float.parseFloat(this.txtMouldHandleLeftMax.getText()));
        }
        if (!this.txtMouldHandleLeftMin.getText().equals("")) {
            currentMould.setWallNonDgHandleLeftMin(Float.parseFloat(this.txtMouldHandleLeftMin.getText()));
        }
        if (!this.txtMouldHandleRightMax.getText().equals("")) {
            currentMould.setWallNonDgHandleRightMax(Float.parseFloat(this.txtMouldHandleRightMax.getText()));
        }
        if (!this.txtMouldHandleRightMin.getText().equals("")) {
            currentMould.setWallNonDgHandleRightMin(Float.parseFloat(this.txtMouldHandleRightMin.getText()));
        }
        if (!this.txtMouldManufacturer.getText().equals("")) {
            currentMould.setManufacturer(this.txtMouldManufacturer.getText());
        }
        if (!this.txtMouldName.getText().equals("")) {
            currentMould.setName(this.txtMouldName.getText());
        }
        if (!this.txtMouldNonDgMax.getText().equals("")) {
            currentMould.setWeightNonDgMax(Float.parseFloat(this.txtMouldNonDgMax.getText()));
        }
        if (!this.txtMouldNonDgMin.getText().equals("")) {
            currentMould.setWeightNonDgMin(Float.parseFloat(this.txtMouldNonDgMin.getText()));
        }
        if (!this.txtMouldSize1.getText().equals("")) {
            currentMould.setThreadNeckSize1(this.txtMouldSize1.getText());
        }
        if (!this.txtMouldSize1Max.getText().equals("")) {
            currentMould.setThreadNeckMax1(Float.parseFloat(this.txtMouldSize1Max.getText()));
        }
        if (!this.txtMouldSize1Min.getText().equals("")) {
            currentMould.setThreadNeckMin1(Float.parseFloat(this.txtMouldSize1Min.getText()));
        }
        if (!this.txtMouldSize2.getText().equals("")) {
            currentMould.setThreadNeckSize2(this.txtMouldSize2.getText());
        }
        if (!this.txtMouldSize2Max.getText().equals("")) {
            currentMould.setThreadNeckMax2(Float.parseFloat(this.txtMouldSize2Max.getText()));
        }
        if (!this.txtMouldSize2Min.getText().equals("")) {
            currentMould.setThreadNeckMin2(Float.parseFloat(this.txtMouldSize2Min.getText()));
        }
        if (!this.txtMouldSize3.getText().equals("")) {
            currentMould.setThreadNeckSize3(this.txtMouldSize3.getText());
        }
        if (!this.txtMouldSize3Max.getText().equals("")) {
            currentMould.setThreadNeckMax3(Float.parseFloat(this.txtMouldSize3Max.getText()));
        }
        if (!this.txtMouldSize3Min.getText().equals("")) {
            currentMould.setThreadNeckMin3(Float.parseFloat(this.txtMouldSize3Min.getText()));
        }
        if (!this.txtMouldSizeA1.getText().equals("")) {
            currentMould.setThreadBoreASize1(this.txtMouldSizeA1.getText());
        }
        if (!this.txtMouldSizeA1Max.getText().equals("")) {
            currentMould.setThreadBoreAMax1(Float.parseFloat(this.txtMouldSizeA1Max.getText()));
        }
        if (!this.txtMouldSizeA1Min.getText().equals("")) {
            currentMould.setThreadBoreAMin1(Float.parseFloat(this.txtMouldSizeA1Min.getText()));
        }
        if (!this.txtMouldSizeA2.getText().equals("")) {
            currentMould.setThreadBoreASize2(this.txtMouldSizeA2.getText());
        }
        if (!this.txtMouldSizeA2Max.getText().equals("")) {
            currentMould.setThreadBoreAMax2(Float.parseFloat(this.txtMouldSizeA2Max.getText()));
        }
        if (!this.txtMouldSizeA2Min.getText().equals("")) {
            currentMould.setThreadBoreAMin2(Float.parseFloat(this.txtMouldSizeA2Min.getText()));
        }
        if (!this.txtMouldSizeA3.getText().equals("")) {
            currentMould.setThreadBoreASize3(this.txtMouldSizeA3.getText());
        }
        if (!this.txtMouldSizeA3Max.getText().equals("")) {
            currentMould.setThreadBoreAMax3(Float.parseFloat(this.txtMouldSizeA3Max.getText()));
        }
        if (!this.txtMouldSizeA3Min.getText().equals("")) {
            currentMould.setThreadBoreAMin3(Float.parseFloat(this.txtMouldSizeA3Min.getText()));
        }
        if (!this.txtMouldSizeB1.getText().equals("")) {
            currentMould.setThreadBoreBSize1(this.txtMouldSizeB1.getText());
        }
        if (!this.txtMouldSizeB1Max.getText().equals("")) {
            currentMould.setThreadBoreBMax1(Float.parseFloat(this.txtMouldSizeB1Max.getText()));
        }
        if (!this.txtMouldSizeB1Min.getText().equals("")) {
            currentMould.setThreadBoreBMin1(Float.parseFloat(this.txtMouldSizeB1Min.getText()));
        }
        if (!this.txtMouldSizeB2.getText().equals("")) {
            currentMould.setThreadBoreBSize2(this.txtMouldSizeB2.getText());
        }
        if (!this.txtMouldSizeB2Max.getText().equals("")) {
            currentMould.setThreadBoreBMax2(Float.parseFloat(this.txtMouldSizeB2Max.getText()));
        }
        if (!this.txtMouldSizeB2Min.getText().equals("")) {
            currentMould.setThreadBoreBMin2(Float.parseFloat(this.txtMouldSizeB2Min.getText()));
        }
        if (!this.txtMouldSizeB3.getText().equals("")) {
            currentMould.setThreadBoreBSize3(this.txtMouldSizeB3.getText());
        }
        if (!this.txtMouldSizeB3Max.getText().equals("")) {
            currentMould.setThreadBoreBMax3(Float.parseFloat(this.txtMouldSizeB3Max.getText()));
        }
        if (!this.txtMouldSizeB3Min.getText().equals("")) {
            currentMould.setThreadBoreBMin3(Float.parseFloat(this.txtMouldSizeB3Min.getText()));
        }
        if (!this.txtMouldTapMax.getText().equals("")) {
            currentMould.setTapPositionMax(Float.parseFloat(this.txtMouldTapMax.getText()));
        }
        if (!this.txtMouldTapMin.getText().equals("")) {
            currentMould.setTapPositionMin(Float.parseFloat(this.txtMouldTapMin.getText()));
        }
        if (!this.txtMouldUnderHandleMax.getText().equals("")) {
            currentMould.setWallNonDgUnderHandleMax(Float.parseFloat(this.txtMouldUnderHandleMax.getText()));
        }
        if (!this.txtMouldUnderHandleMin.getText().equals("")) {
            currentMould.setWallNonDgUnderHandleMin(Float.parseFloat(this.txtMouldUnderHandleMin.getText()));
        }
        if (!this.txtMouldVolume.getText().equals("")) {
            currentMould.setVolumn(this.txtMouldVolume.getText());
        }
        if (!this.txtMouldWeightMax.getText().equals("")) {
            currentMould.setWeightDgMax(Float.parseFloat(this.txtMouldWeightMax.getText()));
        }
        if (!this.txtMouldWeightMin.getText().equals("")) {
            currentMould.setWeightDgMin(Float.parseFloat(this.txtMouldWeightMin.getText()));
        }
        if (!this.txtMouldYear.getText().equals("")) {
            currentMould.setYear(this.txtMouldYear.getText());
        }
        if (!this.labBoreAImage.getText().equals("") && !this.labBoreAImage.getText().equals("Image File Path")) {
            currentMould.setImageBoreA(this.labBoreAImage.getText());
        }
        if (!this.labBoreBImage.getText().equals("") && !this.labBoreBImage.getText().equals("Image File Path")) {
            currentMould.setImageBoreB(this.labBoreAImage.getText());
        }
        if (!this.labDgImage.getText().equals("") && !this.labDgImage.getText().equals("Image File Path")) {
            currentMould.setImageDg(this.labDgImage.getText());
        }
        if (!this.labDrawingImage.getText().equals("") && !this.labDrawingImage.getText().equals("Image File Path")) {
            currentMould.setImageDrawing(this.labDrawingImage.getText());
        }
        if (!this.labNonDgImage.getText().equals("") && !this.labNonDgImage.getText().equals("Image File Path")) {
            currentMould.setImageNonDg(this.labNonDgImage.getText());
        }
        if (!this.labNeckImage.getText().equals("") && !this.labNeckImage.getText().equals("Image File Path")) {
            currentMould.setImageBoreA(this.labNeckImage.getText());
        }
        if (!this.labTapImage.getText().equals("") && !this.labTapImage.getText().equals("Image File Path")) {
            currentMould.setImageTap(this.labTapImage.getText());
        }

        this.mouldService.UpdateEntity(currentMould);
        this.UpdateTabMould(currentMould.getId());
    }//GEN-LAST:event_btnMouldSaveActionPerformed

    private void btnMouldUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldUndoActionPerformed
        Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
        this.UpdateTabMould(currentMould.getId());
    }//GEN-LAST:event_btnMouldUndoActionPerformed

    private void tabSettingsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabSettingsStateChanged
        int index = tabSettings.getSelectedIndex();
        if (index == 3) {
            if (settingMouldPreviousId != settingMouldId) {
                this.UpdateTabProduct(0);
            }
        }
    }//GEN-LAST:event_tabSettingsStateChanged

    private void cbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductActionPerformed
        Product currentItem = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
        UpdateProductUI(currentItem);
    }//GEN-LAST:event_cbProductActionPerformed

    private void btnProductNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductNewActionPerformed
        int newId = this.productService.CreateEntity();
        if (this.settingMouldId != 0) {
            Product newProduct = (Product) this.productService.FindEntity(newId);
            newProduct.setMouldId(this.settingMouldId);
            this.productService.UpdateEntity(newProduct);
        }
        UpdateTabProduct(newId);
    }//GEN-LAST:event_btnProductNewActionPerformed

    private void btnProductDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Product currentProduct = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
            if ("- Select -".equals(currentProduct.getCode())) {
                return;
            }
            this.productService.DeleteEntity(currentProduct.getId());
            this.UpdateTabProduct(0);
        }
    }//GEN-LAST:event_btnProductDeleteActionPerformed

    private void btnProductSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductSaveActionPerformed
        Product currentProduct = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
        if ("- Select -".equals(currentProduct.getCode())) {
            return;
        }
        if (!this.txtProductCode.getText().equals("")) {
            currentProduct.setCode(this.txtProductCode.getText());
        }
        if (!this.txtProductDesc.getText().equals("")) {
            currentProduct.setDescription(this.txtProductDesc.getText());
        }
        if (!this.txtProductPerc1.getText().equals("")) {
            currentProduct.setAdditiveAPercentage(this.txtProductPerc1.getText());
        }
        if (!this.txtProductPerc2.getText().equals("")) {
            currentProduct.setAdditiveBPercentage(this.txtProductPerc2.getText());
        }
        if (!this.txtProductPerc3.getText().equals("")) {
            currentProduct.setAdditiveCPercentage(this.txtProductPerc3.getText());
        }
        if (!this.txtProductWeightMax.getText().equals("")) {
            currentProduct.setWeightMax(Float.parseFloat(this.txtProductWeightMax.getText()));
        }
        if (!this.txtProductWeightMin.getText().equals("")) {
            currentProduct.setWeightMin(Float.parseFloat(this.txtProductWeightMin.getText()));
        }
        currentProduct.setBung(this.cbProductBung.getSelectedItem().toString());
        currentProduct.setPierced(this.cbProductPierced.getSelectedItem().toString());
        currentProduct.setDgnondg(this.cbProductDg.getSelectedIndex());
        currentProduct.setMouldId(((ComboBoxItem<Mould>) this.cbProductMould.getSelectedItem()).getId());
        currentProduct.setPolymerId(((ComboBoxItem<Polymer>) this.cbProductPolymer.getSelectedItem()).getId());
        currentProduct.setAdditiveAId(((ComboBoxItem<Additive>) this.cbProductAdditive1.getSelectedItem()).getId());
        currentProduct.setAdditiveBId(((ComboBoxItem<Additive>) this.cbProductAdditive2.getSelectedItem()).getId());
        currentProduct.setAdditiveCId(((ComboBoxItem<Additive>) this.cbProductAdditive3.getSelectedItem()).getId());
        currentProduct.setThreadBore(this.cbProductBore.getSelectedIndex());
        currentProduct.setThreadNeck(this.cbProductNeck.getSelectedIndex());

        this.productService.UpdateEntity(currentProduct);
        this.UpdateTabProduct(currentProduct.getId());
    }//GEN-LAST:event_btnProductSaveActionPerformed

    private void btnProductUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductUndoActionPerformed
        Product currentProduct = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
        this.UpdateTabProduct(currentProduct.getId());
    }//GEN-LAST:event_btnProductUndoActionPerformed

    private void btnTapImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTapImageActionPerformed
        AppHelper.selectImage(this.pnlTapImage, this.labTapImage);
    }//GEN-LAST:event_btnTapImageActionPerformed

    private void btnNeckImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNeckImageActionPerformed
        AppHelper.selectImage(this.pnlNeckImage, this.labNeckImage);
    }//GEN-LAST:event_btnNeckImageActionPerformed

    private void btnBoreBImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoreBImageActionPerformed
        AppHelper.selectImage(this.pnlBoreBImage, this.labBoreBImage);
    }//GEN-LAST:event_btnBoreBImageActionPerformed

    private void btnBoreAImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoreAImageActionPerformed
        AppHelper.selectImage(this.pnlBoreAImage, this.labBoreAImage);
    }//GEN-LAST:event_btnBoreAImageActionPerformed

    private void btnNonDgImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNonDgImageActionPerformed
        AppHelper.selectImage(this.pnlNonDgImage, this.labNonDgImage);
    }//GEN-LAST:event_btnNonDgImageActionPerformed

    private void btnDgImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDgImageActionPerformed
        AppHelper.selectImage(this.pnlDgImage, this.labDgImage);
    }//GEN-LAST:event_btnDgImageActionPerformed

    private void btnDrawingImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawingImageActionPerformed
        AppHelper.selectImage(this.pnlDrawingImage, this.labDrawingImage);
    }//GEN-LAST:event_btnDrawingImageActionPerformed

    private void txtMouldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMouldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMouldNameActionPerformed

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
        currentEntry.setInUse(this.cbEntryInUse.getSelectedItem().toString());
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

    private void UpdateProductUI(Product currentProduct) {
        txtProductCode.setText(currentProduct.getCode() == null ? "" : currentProduct.getCode().toString());;
        txtProductDesc.setText(currentProduct.getDescription() == null ? "" : currentProduct.getDescription().toString());;
        txtProductPerc1.setText(currentProduct.getAdditiveAPercentage() == null ? "" : currentProduct.getAdditiveAPercentage().toString());;
        txtProductPerc2.setText(currentProduct.getAdditiveBPercentage() == null ? "" : currentProduct.getAdditiveBPercentage().toString());;
        txtProductPerc3.setText(currentProduct.getAdditiveCPercentage() == null ? "" : currentProduct.getAdditiveCPercentage().toString());
        txtProductWeightMax.setText(currentProduct.getWeightMax() == null ? "" : currentProduct.getWeightMax().toString());
        txtProductWeightMin.setText(currentProduct.getWeightMin() == null ? "" : currentProduct.getWeightMin().toString());
        //combobox
        if(currentProduct.getMouldId()!=null){
        this.FillMouldComboBox(this.cbProductMould, currentProduct.getMouldId());
        }
        if(currentProduct.getPolymerId()!=null){
        this.FillPolymerComboBox(this.cbProductPolymer, currentProduct.getPolymerId());
        }
        if(currentProduct.getAdditiveAId()!=null){
        this.FillAdditiveComboBox(this.cbProductAdditive1, currentProduct.getAdditiveAId());
        }
        if(currentProduct.getAdditiveBId()!=null){
        this.FillAdditiveComboBox(this.cbProductAdditive2, currentProduct.getAdditiveBId());
        }
        if(currentProduct.getAdditiveCId()!=null){
        this.FillAdditiveComboBox(this.cbProductAdditive3, currentProduct.getAdditiveCId());
        }
        List<String> threadBores = new ArrayList<String>();
        threadBores.add("- Select -");
        if (settingMould.getThreadBoreASize1() != null && !settingMould.getThreadBoreASize1().equals("")) {
            threadBores.add(settingMould.getThreadBoreASize1());
        }
        if (settingMould.getThreadBoreASize2() != null && !settingMould.getThreadBoreASize2().equals("")) {
            threadBores.add(settingMould.getThreadBoreASize2());
        }
        if (settingMould.getThreadBoreASize3() != null && !settingMould.getThreadBoreASize3().equals("")) {
            threadBores.add(settingMould.getThreadBoreASize3());
        }
        if (settingMould.getThreadBoreBSize1() != null && !settingMould.getThreadBoreBSize1().equals("")) {
            threadBores.add(settingMould.getThreadBoreBSize1());
        }
        if (settingMould.getThreadBoreBSize2() != null && !settingMould.getThreadBoreBSize2().equals("")) {
            threadBores.add(settingMould.getThreadBoreBSize2());
        }
        if (settingMould.getThreadBoreBSize3() != null && !settingMould.getThreadBoreBSize3().equals("")) {
            threadBores.add(settingMould.getThreadBoreBSize3());
        }
        this.cbProductBore.setModel(new DefaultComboBoxModel(threadBores.toArray()));
        List<String> threadNecks = new ArrayList<String>();
        threadNecks.add("- Select -");
        if (settingMould.getThreadNeckSize1() != null && !settingMould.getThreadNeckSize1().equals("")) {
            threadNecks.add(settingMould.getThreadNeckSize1());
        }
        if (settingMould.getThreadNeckSize2() != null && !settingMould.getThreadNeckSize2().equals("")) {
            threadNecks.add(settingMould.getThreadNeckSize2());
        }
        if (settingMould.getThreadNeckSize3() != null && !settingMould.getThreadNeckSize3().equals("")) {
            threadNecks.add(settingMould.getThreadNeckSize3());
        }
        this.cbProductNeck.setModel(new DefaultComboBoxModel(threadNecks.toArray()));
        if (currentProduct.getBung() != null && !currentProduct.getBung().equals("")) {
            this.cbProductBung.setSelectedItem(currentProduct.getBung().toString());
        }
        if (currentProduct.getPierced() != null && !currentProduct.getPierced().equals("")) {
            this.cbProductPierced.setSelectedItem(currentProduct.getPierced().toString());
        }
        if (currentProduct.getDgnondg() != null) {
            this.cbProductDg.setSelectedIndex(currentProduct.getDgnondg());
        }
        if (currentProduct.getThreadBore() != null) {
            this.cbProductBore.setSelectedIndex(currentProduct.getThreadBore());
        }
        if (currentProduct.getThreadNeck() != null) {
            this.cbProductNeck.setSelectedIndex(currentProduct.getThreadNeck());
        }

    }

    private void UpdateEntryUI(Entry currentEntry) {
        cbEntryInUse.setSelectedItem(currentEntry.getInUse());
        txtEntryShift.setText(currentEntry.getShift() == null ? "" : currentEntry.getShift().toString());;
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
        if (currentEntry.getMouldId() != null) {
            this.FillMouldComboBox(this.cbEntryMould, currentEntry.getMouldId());
        }
        if (currentEntry.getProductId() != null) {
            this.FillProductComboBox(this.cbEntryProduct, currentEntry.getProductId(), currentEntry.getMouldId());
        }
        if (currentEntry.getMachineId() != null) {
            this.FillMachineComboBox(this.cbEntryMachine, currentEntry.getMachineId());
        }
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
            java.util.logging.Logger.getLogger(SettingsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingsJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdditiveDelete;
    private javax.swing.JButton btnAdditiveNew;
    private javax.swing.JButton btnAdditiveSave;
    private javax.swing.JButton btnAdditiveUndo;
    private javax.swing.JButton btnBoreAImage;
    private javax.swing.JButton btnBoreBImage;
    private javax.swing.JButton btnDgImage;
    private javax.swing.JButton btnDrawingImage;
    private javax.swing.JButton btnEntryDelete;
    private javax.swing.JButton btnEntryNew;
    private javax.swing.JButton btnEntrySave;
    private javax.swing.JButton btnEntryUndo;
    private javax.swing.JButton btnMachineDelete;
    private javax.swing.JButton btnMachineNew;
    private javax.swing.JButton btnMachineSave;
    private javax.swing.JButton btnMachineUndo;
    private javax.swing.JButton btnMouldDelete;
    private javax.swing.JButton btnMouldNew;
    private javax.swing.JButton btnMouldSave;
    private javax.swing.JButton btnMouldUndo;
    private javax.swing.JButton btnNeckImage;
    private javax.swing.JButton btnNonDgImage;
    private javax.swing.JButton btnPolymerDelete;
    private javax.swing.JButton btnPolymerNew;
    private javax.swing.JButton btnPolymerSave;
    private javax.swing.JButton btnPolymerUndo;
    private javax.swing.JButton btnProductDelete;
    private javax.swing.JButton btnProductNew;
    private javax.swing.JButton btnProductSave;
    private javax.swing.JButton btnProductUndo;
    private javax.swing.JButton btnStaffDelete;
    private javax.swing.JButton btnStaffNew;
    private javax.swing.JButton btnStaffSave;
    private javax.swing.JButton btnStaffUndo;
    private javax.swing.JButton btnTapImage;
    private javax.swing.JComboBox cbAdditive;
    private javax.swing.JComboBox cbEntry;
    private javax.swing.JComboBox cbEntryInUse;
    private javax.swing.JComboBox cbEntryMachine;
    private javax.swing.JComboBox cbEntryMould;
    private javax.swing.JComboBox cbEntryProduct;
    private javax.swing.JComboBox cbMachine;
    private javax.swing.JComboBox cbMould;
    private javax.swing.JComboBox cbPolymer;
    private javax.swing.JComboBox cbProduct;
    private javax.swing.JComboBox cbProductAdditive1;
    private javax.swing.JComboBox cbProductAdditive2;
    private javax.swing.JComboBox cbProductAdditive3;
    private javax.swing.JComboBox cbProductBore;
    private javax.swing.JComboBox cbProductBung;
    private javax.swing.JComboBox cbProductDg;
    private javax.swing.JComboBox cbProductMould;
    private javax.swing.JComboBox cbProductNeck;
    private javax.swing.JComboBox cbProductPierced;
    private javax.swing.JComboBox cbProductPolymer;
    private javax.swing.JComboBox cbStaff;
    private javax.swing.JComboBox cbStaffJob;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel163;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel labBoreAImage;
    private javax.swing.JLabel labBoreBImage;
    private javax.swing.JLabel labDgImage;
    private javax.swing.JLabel labDrawingImage;
    private javax.swing.JLabel labNeckImage;
    private javax.swing.JLabel labNonDgImage;
    private javax.swing.JLabel labTapImage;
    private javax.swing.JPanel pnlBoreAImage;
    private javax.swing.JPanel pnlBoreBImage;
    private javax.swing.JPanel pnlDgImage;
    private javax.swing.JPanel pnlDrawingImage;
    private javax.swing.JPanel pnlNeckImage;
    private javax.swing.JPanel pnlNonDgImage;
    private javax.swing.JPanel pnlTapImage;
    private javax.swing.JTabbedPane tabSettings;
    private javax.swing.JTextField txtAdditiveCompany;
    private javax.swing.JTextField txtAdditiveDesc;
    private javax.swing.JTextField txtAdditiveGrade;
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
    private javax.swing.JTextField txtMachineCapacity;
    private javax.swing.JTextField txtMachineDesc;
    private javax.swing.JTextField txtMachineManufa;
    private javax.swing.JTextField txtMachineNo;
    private javax.swing.JTextField txtMachineSerial;
    private javax.swing.JTextField txtMachineYear;
    private javax.swing.JTextField txtMouldBaseMax;
    private javax.swing.JTextField txtMouldBaseMin;
    private javax.swing.JTextField txtMouldClosureMax;
    private javax.swing.JTextField txtMouldClosureMin;
    private javax.swing.JTextField txtMouldCode;
    private javax.swing.JTextField txtMouldDgBaseMax;
    private javax.swing.JTextField txtMouldDgBaseMin;
    private javax.swing.JTextField txtMouldDgClosureMax;
    private javax.swing.JTextField txtMouldDgClosureMin;
    private javax.swing.JTextField txtMouldDgHandleBungMax;
    private javax.swing.JTextField txtMouldDgHandleBungMin;
    private javax.swing.JTextField txtMouldDgHandleLeftMax;
    private javax.swing.JTextField txtMouldDgHandleLeftMin;
    private javax.swing.JTextField txtMouldDgHandleRightMax;
    private javax.swing.JTextField txtMouldDgHandleRightMin;
    private javax.swing.JTextField txtMouldDgUnderHandleMax;
    private javax.swing.JTextField txtMouldDgUnderHandleMin;
    private javax.swing.JTextField txtMouldHandleBungMax;
    private javax.swing.JTextField txtMouldHandleBungMin;
    private javax.swing.JTextField txtMouldHandleLeftMax;
    private javax.swing.JTextField txtMouldHandleLeftMin;
    private javax.swing.JTextField txtMouldHandleRightMax;
    private javax.swing.JTextField txtMouldHandleRightMin;
    private javax.swing.JTextField txtMouldManufacturer;
    private javax.swing.JTextField txtMouldName;
    private javax.swing.JTextField txtMouldNonDgMax;
    private javax.swing.JTextField txtMouldNonDgMin;
    private javax.swing.JTextField txtMouldSize1;
    private javax.swing.JTextField txtMouldSize1Max;
    private javax.swing.JTextField txtMouldSize1Min;
    private javax.swing.JTextField txtMouldSize2;
    private javax.swing.JTextField txtMouldSize2Max;
    private javax.swing.JTextField txtMouldSize2Min;
    private javax.swing.JTextField txtMouldSize3;
    private javax.swing.JTextField txtMouldSize3Max;
    private javax.swing.JTextField txtMouldSize3Min;
    private javax.swing.JTextField txtMouldSizeA1;
    private javax.swing.JTextField txtMouldSizeA1Max;
    private javax.swing.JTextField txtMouldSizeA1Min;
    private javax.swing.JTextField txtMouldSizeA2;
    private javax.swing.JTextField txtMouldSizeA2Max;
    private javax.swing.JTextField txtMouldSizeA2Min;
    private javax.swing.JTextField txtMouldSizeA3;
    private javax.swing.JTextField txtMouldSizeA3Max;
    private javax.swing.JTextField txtMouldSizeA3Min;
    private javax.swing.JTextField txtMouldSizeB1;
    private javax.swing.JTextField txtMouldSizeB1Max;
    private javax.swing.JTextField txtMouldSizeB1Min;
    private javax.swing.JTextField txtMouldSizeB2;
    private javax.swing.JTextField txtMouldSizeB2Max;
    private javax.swing.JTextField txtMouldSizeB2Min;
    private javax.swing.JTextField txtMouldSizeB3;
    private javax.swing.JTextField txtMouldSizeB3Max;
    private javax.swing.JTextField txtMouldSizeB3Min;
    private javax.swing.JTextField txtMouldTapMax;
    private javax.swing.JTextField txtMouldTapMin;
    private javax.swing.JTextField txtMouldUnderHandleMax;
    private javax.swing.JTextField txtMouldUnderHandleMin;
    private javax.swing.JTextField txtMouldVolume;
    private javax.swing.JTextField txtMouldWeightMax;
    private javax.swing.JTextField txtMouldWeightMin;
    private javax.swing.JTextField txtMouldYear;
    private javax.swing.JTextField txtPolymerCompany;
    private javax.swing.JTextField txtPolymerDesc;
    private javax.swing.JTextField txtPolymerGrade;
    private javax.swing.JTextField txtProductCode;
    private javax.swing.JTextField txtProductDesc;
    private javax.swing.JTextField txtProductPerc1;
    private javax.swing.JTextField txtProductPerc2;
    private javax.swing.JTextField txtProductPerc3;
    private javax.swing.JTextField txtProductWeightMax;
    private javax.swing.JTextField txtProductWeightMin;
    private javax.swing.JTextField txtStaffName;
    // End of variables declaration//GEN-END:variables

}
