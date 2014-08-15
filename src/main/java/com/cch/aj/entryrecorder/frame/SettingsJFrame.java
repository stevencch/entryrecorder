/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.frame;

import com.cch.aj.entryrecorder.common.ComboBoxItem;
import com.cch.aj.entryrecorder.common.ComboBoxItemConvertor;
import com.cch.aj.entryrecorder.common.ComboBoxRender;
import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import static java.util.Arrays.stream;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class SettingsJFrame extends javax.swing.JFrame {

    private SettingService staffService = new SettingServiceImpl<Staff>(Staff.class);
    private SettingService machineService = new SettingServiceImpl<Machine>(Machine.class);
    private SettingService polymerService = new SettingServiceImpl<Polymer>(Polymer.class);
    private SettingService additiveService = new SettingServiceImpl<Additive>(Additive.class);
    private SettingService mouldService = new SettingServiceImpl<Mould>(Mould.class);

    /**
     * Creates new form SettingsJFrame
     */
    public SettingsJFrame() {

        initComponents();
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

    }

    private void UpdateTabStaff(int id) {
        List<Staff> staffs = this.staffService.GetAllEntities();
        if (staffs.size() > 0) {
            List<ComboBoxItem<Staff>> staffNames = staffs.stream().map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getName(), x.getId())).collect(Collectors.toList());
            ComboBoxItem[] staffNamesArray = staffNames.toArray(new ComboBoxItem[staffNames.size()]);
            this.cbStaff.setModel(new DefaultComboBoxModel(staffNamesArray));
            int selectedIndex = id;
            if (id != 0) {
                ComboBoxItem<Staff> currentStaffName = staffNames.stream().filter(x -> x.getId() == id).findFirst().get();
                selectedIndex = staffNames.indexOf(currentStaffName);
            }
            this.cbStaff.setSelectedIndex(selectedIndex);
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
        List<Machine> machines = this.machineService.GetAllEntities();
        if (machines.size() > 0) {
            List<ComboBoxItem<Machine>> machineNames = machines.stream().map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, "Machine " + x.getMachineNo(), x.getId())).collect(Collectors.toList());
            ComboBoxItem[] machineNamesArray = machineNames.toArray(new ComboBoxItem[machineNames.size()]);
            this.cbMachine.setModel(new DefaultComboBoxModel(machineNamesArray));
            int selectedIndex = id;
            if (id != 0) {
                ComboBoxItem<Machine> currentMachineName = machineNames.stream().filter(x -> x.getId() == id).findFirst().get();
                selectedIndex = machineNames.indexOf(currentMachineName);
            }
            this.cbMachine.setSelectedIndex(selectedIndex);
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
        List<Polymer> polymers = this.polymerService.GetAllEntities();
        if (polymers.size() > 0) {
            List<ComboBoxItem<Polymer>> polymerNames = polymers.stream().map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCompany() + " " + x.getGrade(), x.getId())).collect(Collectors.toList());
            ComboBoxItem[] polymerNamesArray = polymerNames.toArray(new ComboBoxItem[polymerNames.size()]);
            this.cbPolymer.setModel(new DefaultComboBoxModel(polymerNamesArray));
            int selectedIndex = id;
            if (id != 0) {
                ComboBoxItem<Polymer> currentPolymerName = polymerNames.stream().filter(x -> x.getId() == id).findFirst().get();
                selectedIndex = polymerNames.indexOf(currentPolymerName);
            }
            this.cbPolymer.setSelectedIndex(selectedIndex);
            Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
            //
            this.txtPolymerCompany.setText(currentPolymer.getCompany());
            this.txtPolymerDesc.setText(currentPolymer.getDescription());
            this.txtPolymerGrade.setText(currentPolymer.getGrade());
        } else {
            this.cbPolymer.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtPolymerCompany.setText("");
            this.txtPolymerDesc.setText("");
            this.txtPolymerGrade.setText("");
        }
    }

    private void UpdateTabAdditive(int id) {
        List<Additive> additives = this.additiveService.GetAllEntities();
        if (additives.size() > 0) {
            List<ComboBoxItem<Additive>> additiveNames = additives.stream().map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCompany() + " " + x.getGrade(), x.getId())).collect(Collectors.toList());
            ComboBoxItem[] additiveNamesArray = additiveNames.toArray(new ComboBoxItem[additiveNames.size()]);
            this.cbAdditive.setModel(new DefaultComboBoxModel(additiveNamesArray));
            int selectedIndex = id;
            if (id != 0) {
                ComboBoxItem<Additive> currentAdditiveName = additiveNames.stream().filter(x -> x.getId() == id).findFirst().get();
                selectedIndex = additiveNames.indexOf(currentAdditiveName);
            }
            this.cbAdditive.setSelectedIndex(selectedIndex);
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
        List<Mould> moulds = this.mouldService.GetAllEntities();
        if (moulds.size() > 0) {
            List<ComboBoxItem<Mould>> mouldNames = moulds.stream().map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getCode(), x.getId())).collect(Collectors.toList());
            ComboBoxItem[] mouldNamesArray = mouldNames.toArray(new ComboBoxItem[mouldNames.size()]);
            this.cbMould.setModel(new DefaultComboBoxModel(mouldNamesArray));
            int selectedIndex = id;
            if (id != 0) {
                ComboBoxItem<Mould> currentMouldName = mouldNames.stream().filter(x -> x.getId() == id).findFirst().get();
                selectedIndex = mouldNames.indexOf(currentMouldName);
            }
            this.cbMould.setSelectedIndex(selectedIndex);
            Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
            //
            this.txtMouldBaseMax.setText(currentMould.getWallNonDgBaseMax().toString());
            this.txtMouldBaseMin.setText(currentMould.getWallNonDgBaseMin().toString());
            this.txtMouldClosureMax.setText(currentMould.getWallNonDgClosureMax().toString());
            this.txtMouldClosureMin.setText(currentMould.getWallNonDgClosureMin().toString());
            this.txtMouldCode.setText(currentMould.getCode());
            this.txtMouldDgBaseMax.setText(currentMould.getWallDgBaseMax().toString());
            this.txtMouldDgBaseMin.setText(currentMould.getWallDgBaseMin().toString());
            this.txtMouldDgClosureMax.setText(currentMould.getWallDgClosureMax().toString());
            this.txtMouldDgClosureMin.setText(currentMould.getWallDgClosureMin().toString());
            this.txtMouldDgHandleBungMax.setText(currentMould.getWallDgHandleBungMax().toString());
            this.txtMouldDgHandleBungMin.setText(currentMould.getWallDgHandleBungMin().toString());
            this.txtMouldDgHandleLeftMax.setText(currentMould.getWallDgHandleLeftMax().toString());
            this.txtMouldDgHandleLeftMin.setText(currentMould.getWallDgHandleLeftMin().toString());
            this.txtMouldDgHandleRightMax.setText(currentMould.getWallDgHandleRightMax().toString());
            this.txtMouldDgHandleRightMin.setText(currentMould.getWallDgHandRightMin().toString());
            this.txtMouldDgUnderHandleMax.setText(currentMould.getWallDgUnderHandleMax().toString());
            this.txtMouldDgUnderHandleMin.setText(currentMould.getWallDgUnderHandleMin().toString());
            this.txtMouldHandleBungMax.setText(currentMould.getWallNonDgHandleBungMax().toString());
            this.txtMouldHandleBungMin.setText(currentMould.getWallNonDgHandleBungMin().toString());
            this.txtMouldHandleLeftMax.setText(currentMould.getWallNonDgHandleLeftMax().toString());
            this.txtMouldHandleLeftMin.setText(currentMould.getWallNonDgHandleLeftMin().toString());
            this.txtMouldHandleRightMax.setText(currentMould.getWallNonDgHandleRightMax().toString());
            this.txtMouldHandleRightMin.setText(currentMould.getWallNonDgHandleRightMin().toString());
            this.txtMouldManufacturer.setText(currentMould.getManufacturer());
            this.txtMouldName.setText(currentMould.getName());
            this.txtMouldNonDgMax.setText(currentMould.getWeightNonDgMax().toString());
            this.txtMouldNonDgMin.setText(currentMould.getWeightNonDgMin().toString());
            this.txtMouldSize1.setText(currentMould.getThreadNeckSize1());
            this.txtMouldSize1Max.setText(currentMould.getThreadNeckMax1().toString());
            this.txtMouldSize1Min.setText(currentMould.getThreadNeckMin1().toString());
            this.txtMouldSize2.setText(currentMould.getThreadNeckSize2());
            this.txtMouldSize2Max.setText(currentMould.getThreadNeckMax2().toString());
            this.txtMouldSize2Min.setText(currentMould.getThreadNeckMin2().toString());
            this.txtMouldSize3.setText(currentMould.getThreadNeckSize3());
            this.txtMouldSize3Max.setText(currentMould.getThreadNeckMax3().toString());
            this.txtMouldSize3Min.setText(currentMould.getThreadNeckMin3().toString());
            this.txtMouldSizeA1.setText(currentMould.getThreadBoreASize1());
            this.txtMouldSizeA1Max.setText(currentMould.getThreadBoreAMax1().toString());
            this.txtMouldSizeA1Min.setText(currentMould.getThreadBoreAMin1().toString());
            this.txtMouldSizeA2.setText(currentMould.getThreadBoreASize2());
            this.txtMouldSizeA2Max.setText(currentMould.getThreadBoreAMax2().toString());
            this.txtMouldSizeA2Min.setText(currentMould.getThreadBoreAMin2().toString());
            this.txtMouldSizeA3.setText(currentMould.getThreadBoreASize3());
            this.txtMouldSizeA3Max.setText(currentMould.getThreadBoreAMax3().toString());
            this.txtMouldSizeA3Min.setText(currentMould.getThreadBoreAMin3().toString());
            this.txtMouldSizeB1.setText(currentMould.getThreadBoreBSize1());
            this.txtMouldSizeB1Max.setText(currentMould.getThreadBoreBMax1().toString());
            this.txtMouldSizeB1Min.setText(currentMould.getThreadBoreBMin1().toString());
            this.txtMouldSizeB2.setText(currentMould.getThreadBoreBSize2());
            this.txtMouldSizeB2Max.setText(currentMould.getThreadBoreBMax2().toString());
            this.txtMouldSizeB2Min.setText(currentMould.getThreadBoreBMin2().toString());
            this.txtMouldSizeB3.setText(currentMould.getThreadBoreBSize3());
            this.txtMouldSizeB3Max.setText(currentMould.getThreadBoreBMax3().toString());
            this.txtMouldSizeB3Min.setText(currentMould.getThreadBoreBMin3().toString());
            this.txtMouldTapMax.setText(currentMould.getTapPositionMax().toString());
            this.txtMouldTapMin.setText(currentMould.getTapPositionMin().toString());
            this.txtMouldUnderHandleMax.setText(currentMould.getWallNonDgUnderHandleMax().toString());
            this.txtMouldUnderHandleMin.setText(currentMould.getWallNonDgUnderHandleMin().toString());
            this.txtMouldVolume.setText(currentMould.getVolumn());
            this.txtMouldWeightMax.setText(currentMould.getWeightDgMax().toString());
            this.txtMouldWeightMin.setText(currentMould.getWeightDgMin().toString());
            this.txtMouldYear.setText(currentMould.getYear());

        } else {
            this.cbMould.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtMouldBaseMin.setText("");
            this.txtMouldClosureMax.setText("");
            this.txtMouldClosureMin.setText("");
            this.txtMouldCode.setText("");
            this.txtMouldDgBaseMax.setText("");
            this.txtMouldDgBaseMin.setText("");
            this.txtMouldDgClosureMax.setText("");
            this.txtMouldDgClosureMin.setText("");
            this.txtMouldDgHandleBungMax.setText("");
            this.txtMouldDgHandleBungMin.setText("");
            this.txtMouldDgHandleLeftMax.setText("");
            this.txtMouldDgHandleLeftMin.setText("");
            this.txtMouldDgHandleRightMax.setText("");
            this.txtMouldDgHandleRightMin.setText("");
            this.txtMouldDgUnderHandleMax.setText("");
            this.txtMouldDgUnderHandleMin.setText("");
            this.txtMouldHandleBungMax.setText("");
            this.txtMouldHandleBungMin.setText("");
            this.txtMouldHandleLeftMax.setText("");
            this.txtMouldHandleLeftMin.setText("");
            this.txtMouldHandleRightMax.setText("");
            this.txtMouldHandleRightMin.setText("");
            this.txtMouldManufacturer.setText("");
            this.txtMouldName.setText("");
            this.txtMouldNonDgMax.setText("");
            this.txtMouldNonDgMin.setText("");
            this.txtMouldSize1.setText("");
            this.txtMouldSize1Max.setText("");
            this.txtMouldSize1Min.setText("");
            this.txtMouldSize2.setText("");
            this.txtMouldSize2Max.setText("");
            this.txtMouldSize2Min.setText("");
            this.txtMouldSize3.setText("");
            this.txtMouldSize3Max.setText("");
            this.txtMouldSize3Min.setText("");
            this.txtMouldSizeA1.setText("");
            this.txtMouldSizeA1Max.setText("");
            this.txtMouldSizeA1Min.setText("");
            this.txtMouldSizeA2.setText("");
            this.txtMouldSizeA2Max.setText("");
            this.txtMouldSizeA2Min.setText("");
            this.txtMouldSizeA3.setText("");
            this.txtMouldSizeA3Max.setText("");
            this.txtMouldSizeA3Min.setText("");
            this.txtMouldSizeB1.setText("");
            this.txtMouldSizeB1Max.setText("");
            this.txtMouldSizeB1Min.setText("");
            this.txtMouldSizeB2.setText("");
            this.txtMouldSizeB2Max.setText("");
            this.txtMouldSizeB2Min.setText("");
            this.txtMouldSizeB3.setText("");
            this.txtMouldSizeB3Max.setText("");
            this.txtMouldSizeB3Min.setText("");
            this.txtMouldTapMax.setText("");
            this.txtMouldTapMin.setText("");
            this.txtMouldUnderHandleMax.setText("");
            this.txtMouldUnderHandleMin.setText("");
            this.txtMouldVolume.setText("");
            this.txtMouldWeightMax.setText("");
            this.txtMouldWeightMin.setText("");
            this.txtMouldYear.setText("");

        }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jPanel9 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        txtMouldDrawingImage = new javax.swing.JTextField();
        txtMouldNonDgImage = new javax.swing.JTextField();
        txtMouldDgImage = new javax.swing.JTextField();
        txtMouldBoreAImage = new javax.swing.JTextField();
        txtMouldBoreBImage = new javax.swing.JTextField();
        txtMouldNeckImage = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtMouldTapImage = new javax.swing.JTextField();
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
        jLabel64 = new javax.swing.JLabel();
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

        jTabbedPane1.setName(""); // NOI18N

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

        jTabbedPane1.addTab("Machine", jPanel19);

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

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel73.setText("Drawing Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel73, gridBagConstraints);

        jLabel74.setText("Non DG Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel74, gridBagConstraints);

        jLabel75.setText("DG Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel75, gridBagConstraints);

        jLabel76.setText("Bore A Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel76, gridBagConstraints);

        jLabel77.setText("Bore B Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel77, gridBagConstraints);

        jLabel89.setText("Neck Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel89, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldDrawingImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldNonDgImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldDgImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldBoreAImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldBoreBImage, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldNeckImage, gridBagConstraints);

        jLabel47.setText("Tap Image");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 10);
        jPanel9.add(jLabel47, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 18);
        jPanel9.add(txtMouldTapImage, gridBagConstraints);

        jTabbedPane3.addTab("Image", jPanel9);

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

        jTabbedPane1.addTab("Mould", jPanel16);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jPanel26.setLayout(new java.awt.GridBagLayout());

        btnProductNew.setText("New");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel26.add(btnProductNew, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel26.add(cbProduct, gridBagConstraints);

        btnProductDelete.setText("Delete");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        jPanel27.add(btnProductUndo, gridBagConstraints);

        btnProductSave.setText("Save");
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

        cbProductBung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductPierced.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductMould.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductPolymer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductDg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        jLabel64.setText("THREAD TYPE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(jLabel64, gridBagConstraints);

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

        cbProductBore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel28.add(cbProductBore, gridBagConstraints);

        cbProductNeck.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductAdditive1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductAdditive2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        cbProductAdditive3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel25.add(jPanel28, gridBagConstraints);

        jTabbedPane1.addTab("Product", jPanel25);

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

        jTabbedPane1.addTab("Raw Material", jTabbedPane5);

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

        jTabbedPane1.addTab("Staff", jPanel29);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMouldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMouldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMouldNameActionPerformed

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
            this.machineService.DeleteEntity(currentMachine.getId());
            this.UpdateTabMachine(0);
        }
    }//GEN-LAST:event_btnMachineDeleteActionPerformed

    private void btnMachineSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMachineSaveActionPerformed
        // TODO add your handling code here:
        Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
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
            this.polymerService.DeleteEntity(currentPolymer.getId());
            this.UpdateTabPolymer(0);
        }
    }//GEN-LAST:event_btnPolymerDeleteActionPerformed

    private void btnPolymerSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolymerSaveActionPerformed
        Polymer currentPolymer = ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getItem();
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
            this.additiveService.DeleteEntity(currentAdditive.getId());
            this.UpdateTabAdditive(0);
        }
    }//GEN-LAST:event_btnAdditiveDeleteActionPerformed

    private void btnAdditiveSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveSaveActionPerformed
        Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
        currentAdditive.setCompany(this.txtAdditiveCompany.getText());
        currentAdditive.setDescription(this.txtAdditiveDesc.getText());
        currentAdditive.setGrade(this.txtAdditiveGrade.getText());
        this.additiveService.UpdateEntity(currentAdditive);
        this.UpdateTabAdditive(currentAdditive.getId());
    }//GEN-LAST:event_btnAdditiveSaveActionPerformed

    private void btnAdditiveUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdditiveUndoActionPerformed
        Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbMachine.getSelectedItem()).getItem();
        this.UpdateTabMachine(currentAdditive.getId());
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
        //
        this.txtMouldBaseMax.setText(currentItem.getWallNonDgBaseMax().toString());
        this.txtMouldBaseMin.setText(currentItem.getWallNonDgBaseMin().toString());
        this.txtMouldClosureMax.setText(currentItem.getWallNonDgClosureMax().toString());
        this.txtMouldClosureMin.setText(currentItem.getWallNonDgClosureMin().toString());
        this.txtMouldCode.setText(currentItem.getCode().toString());
        this.txtMouldDgBaseMax.setText(currentItem.getWallDgBaseMax().toString());
        this.txtMouldDgBaseMin.setText(currentItem.getWallDgBaseMin().toString());
        this.txtMouldDgClosureMax.setText(currentItem.getWallDgClosureMax().toString());
        this.txtMouldDgClosureMin.setText(currentItem.getWallDgClosureMin().toString());
        this.txtMouldDgHandleBungMax.setText(currentItem.getWallDgHandleBungMax().toString());
        this.txtMouldDgHandleBungMin.setText(currentItem.getWallDgHandleBungMin().toString());
        this.txtMouldDgHandleLeftMax.setText(currentItem.getWallDgHandleLeftMax().toString());
        this.txtMouldDgHandleLeftMin.setText(currentItem.getWallDgHandleLeftMin().toString());
        this.txtMouldDgHandleRightMax.setText(currentItem.getWallDgHandleRightMax().toString());
        this.txtMouldDgHandleRightMin.setText(currentItem.getWallDgHandRightMin().toString());
        this.txtMouldDgUnderHandleMax.setText(currentItem.getWallDgUnderHandleMax().toString());
        this.txtMouldDgUnderHandleMin.setText(currentItem.getWallDgUnderHandleMin().toString());
        this.txtMouldHandleBungMax.setText(currentItem.getWallNonDgHandleBungMax().toString());
        this.txtMouldHandleBungMin.setText(currentItem.getWallNonDgHandleBungMin().toString());
        this.txtMouldHandleLeftMax.setText(currentItem.getWallNonDgHandleLeftMax().toString());
        this.txtMouldHandleLeftMin.setText(currentItem.getWallNonDgHandleLeftMin().toString());
        this.txtMouldHandleRightMax.setText(currentItem.getWallNonDgHandleRightMax().toString());
        this.txtMouldHandleRightMin.setText(currentItem.getWallNonDgHandleRightMin().toString());
        this.txtMouldManufacturer.setText(currentItem.getManufacturer().toString());
        this.txtMouldName.setText(currentItem.getName().toString());
        this.txtMouldNonDgMax.setText(currentItem.getWeightNonDgMax().toString());
        this.txtMouldNonDgMin.setText(currentItem.getWeightNonDgMin().toString());
        this.txtMouldSize1.setText(currentItem.getThreadNeckSize1().toString());
        this.txtMouldSize1Max.setText(currentItem.getThreadNeckMax1().toString());
        this.txtMouldSize1Min.setText(currentItem.getThreadNeckMin1().toString());
        this.txtMouldSize2.setText(currentItem.getThreadNeckSize2().toString());
        this.txtMouldSize2Max.setText(currentItem.getThreadNeckMax2().toString());
        this.txtMouldSize2Min.setText(currentItem.getThreadNeckMin2().toString());
        this.txtMouldSize3.setText(currentItem.getThreadNeckSize3().toString());
        this.txtMouldSize3Max.setText(currentItem.getThreadNeckMax3().toString());
        this.txtMouldSize3Min.setText(currentItem.getThreadNeckMin3().toString());
        this.txtMouldSizeA1.setText(currentItem.getThreadBoreASize1().toString());
        this.txtMouldSizeA1Max.setText(currentItem.getThreadBoreAMax1().toString());
        this.txtMouldSizeA1Min.setText(currentItem.getThreadBoreAMin1().toString());
        this.txtMouldSizeA2.setText(currentItem.getThreadBoreASize2().toString());
        this.txtMouldSizeA2Max.setText(currentItem.getThreadBoreAMax2().toString());
        this.txtMouldSizeA2Min.setText(currentItem.getThreadBoreAMin2().toString());
        this.txtMouldSizeA3.setText(currentItem.getThreadBoreASize3().toString());
        this.txtMouldSizeA3Max.setText(currentItem.getThreadBoreAMax3().toString());
        this.txtMouldSizeA3Min.setText(currentItem.getThreadBoreAMin3().toString());
        this.txtMouldSizeB1.setText(currentItem.getThreadBoreBSize1().toString());
        this.txtMouldSizeB1Max.setText(currentItem.getThreadBoreBMax1().toString());
        this.txtMouldSizeB1Min.setText(currentItem.getThreadBoreBMin1().toString());
        this.txtMouldSizeB2.setText(currentItem.getThreadBoreBSize2().toString());
        this.txtMouldSizeB2Max.setText(currentItem.getThreadBoreBMax2().toString());
        this.txtMouldSizeB2Min.setText(currentItem.getThreadBoreBMin2().toString());
        this.txtMouldSizeB3.setText(currentItem.getThreadBoreBSize3().toString());
        this.txtMouldSizeB3Max.setText(currentItem.getThreadBoreBMax3().toString());
        this.txtMouldSizeB3Min.setText(currentItem.getThreadBoreBMin3().toString());
        this.txtMouldTapMax.setText(currentItem.getTapPositionMax().toString());
        this.txtMouldTapMin.setText(currentItem.getTapPositionMin().toString());
        this.txtMouldUnderHandleMax.setText(currentItem.getWallNonDgUnderHandleMax().toString());
        this.txtMouldUnderHandleMin.setText(currentItem.getWallNonDgUnderHandleMin().toString());
        this.txtMouldVolume.setText(currentItem.getVolumn().toString());
        this.txtMouldWeightMax.setText(currentItem.getWeightDgMax().toString());
        this.txtMouldWeightMin.setText(currentItem.getWeightDgMin().toString());
        this.txtMouldYear.setText(currentItem.getYear().toString());

    }//GEN-LAST:event_cbMouldActionPerformed

    private void btnMouldNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldNewActionPerformed
        int newId = this.mouldService.CreateEntity();
        UpdateTabMould(newId);
    }//GEN-LAST:event_btnMouldNewActionPerformed

    private void btnMouldDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure to delete this item", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            Mould currentMould = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
            this.mouldService.DeleteEntity(currentMould.getId());
            this.UpdateTabMould(0);
        }
    }//GEN-LAST:event_btnMouldDeleteActionPerformed

    private void btnMouldSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldSaveActionPerformed
        Mould currentMould = ((ComboBoxItem<Mould>) this.cbAdditive.getSelectedItem()).getItem();
        currentMould.setWallNonDgBaseMax(Float.parseFloat(this.txtMouldBaseMax.getText()));
        currentMould.setWallNonDgBaseMin(Float.parseFloat(this.txtMouldBaseMin.getText()));
        currentMould.setWallNonDgClosureMax(Float.parseFloat(this.txtMouldClosureMax.getText()));
        currentMould.setWallNonDgClosureMin(Float.parseFloat(this.txtMouldClosureMin.getText()));
        currentMould.setCode(this.txtMouldCode.getText());
        currentMould.setWallDgBaseMax(Float.parseFloat(this.txtMouldDgBaseMax.getText()));
        currentMould.setWallDgBaseMin(Float.parseFloat(this.txtMouldDgBaseMin.getText()));
        currentMould.setWallDgClosureMax(Float.parseFloat(this.txtMouldDgClosureMax.getText()));
        currentMould.setWallDgClosureMin(Float.parseFloat(this.txtMouldDgClosureMin.getText()));
        currentMould.setWallDgHandleBungMax(Float.parseFloat(this.txtMouldDgHandleBungMax.getText()));
        currentMould.setWallDgHandleBungMin(Float.parseFloat(this.txtMouldDgHandleBungMin.getText()));
        currentMould.setWallDgHandleLeftMax(Float.parseFloat(this.txtMouldDgHandleLeftMax.getText()));
        currentMould.setWallDgHandleLeftMin(Float.parseFloat(this.txtMouldDgHandleLeftMin.getText()));
        currentMould.setWallDgHandleRightMax(Float.parseFloat(this.txtMouldDgHandleRightMax.getText()));
        currentMould.setWallDgHandRightMin(Float.parseFloat(this.txtMouldDgHandleRightMin.getText()));
        currentMould.setWallDgUnderHandleMax(Float.parseFloat(this.txtMouldDgUnderHandleMax.getText()));
        currentMould.setWallDgUnderHandleMin(Float.parseFloat(this.txtMouldDgUnderHandleMin.getText()));
        currentMould.setWallNonDgHandleBungMax(Float.parseFloat(this.txtMouldHandleBungMax.getText()));
        currentMould.setWallNonDgHandleBungMin(Float.parseFloat(this.txtMouldHandleBungMin.getText()));
        currentMould.setWallNonDgHandleLeftMax(Float.parseFloat(this.txtMouldHandleLeftMax.getText()));
        currentMould.setWallNonDgHandleLeftMin(Float.parseFloat(this.txtMouldHandleLeftMin.getText()));
        currentMould.setWallNonDgHandleRightMax(Float.parseFloat(this.txtMouldHandleRightMax.getText()));
        currentMould.setWallNonDgHandleRightMin(Float.parseFloat(this.txtMouldHandleRightMin.getText()));
        currentMould.setManufacturer(this.txtMouldManufacturer.getText());
        currentMould.setName(this.txtMouldName.getText());
        currentMould.setWeightNonDgMax(Float.parseFloat(this.txtMouldNonDgMax.getText()));
        currentMould.setWeightNonDgMin(Float.parseFloat(this.txtMouldNonDgMin.getText()));
        currentMould.setThreadNeckSize1(this.txtMouldSize1.getText());
        currentMould.setThreadNeckMax1(Float.parseFloat(this.txtMouldSize1Max.getText()));
        currentMould.setThreadNeckMin1(Float.parseFloat(this.txtMouldSize1Min.getText()));
        currentMould.setThreadNeckSize2(this.txtMouldSize2.getText());
        currentMould.setThreadNeckMax2(Float.parseFloat(this.txtMouldSize2Max.getText()));
        currentMould.setThreadNeckMin2(Float.parseFloat(this.txtMouldSize2Min.getText()));
        currentMould.setThreadNeckSize3(this.txtMouldSize3.getText());
        currentMould.setThreadNeckMax3(Float.parseFloat(this.txtMouldSize3Max.getText()));
        currentMould.setThreadNeckMin3(Float.parseFloat(this.txtMouldSize3Min.getText()));
        currentMould.setThreadBoreASize1(this.txtMouldSizeA1.getText());
        currentMould.setThreadBoreAMax1(Float.parseFloat(this.txtMouldSizeA1Max.getText()));
        currentMould.setThreadBoreAMin1(Float.parseFloat(this.txtMouldSizeA1Min.getText()));
        currentMould.setThreadBoreASize2(this.txtMouldSizeA2.getText());
        currentMould.setThreadBoreAMax2(Float.parseFloat(this.txtMouldSizeA2Max.getText()));
        currentMould.setThreadBoreAMin2(Float.parseFloat(this.txtMouldSizeA2Min.getText()));
        currentMould.setThreadBoreASize3(this.txtMouldSizeA3.getText());
        currentMould.setThreadBoreAMax3(Float.parseFloat(this.txtMouldSizeA3Max.getText()));
        currentMould.setThreadBoreAMin3(Float.parseFloat(this.txtMouldSizeA3Min.getText()));
        currentMould.setThreadBoreBSize1(this.txtMouldSizeB1.getText());
        currentMould.setThreadBoreBMax1(Float.parseFloat(this.txtMouldSizeB1Max.getText()));
        currentMould.setThreadBoreBMin1(Float.parseFloat(this.txtMouldSizeB1Min.getText()));
        currentMould.setThreadBoreBSize2(this.txtMouldSizeB2.getText());
        currentMould.setThreadBoreBMax2(Float.parseFloat(this.txtMouldSizeB2Max.getText()));
        currentMould.setThreadBoreBMin2(Float.parseFloat(this.txtMouldSizeB2Min.getText()));
        currentMould.setThreadBoreBSize3(this.txtMouldSizeB3.getText());
        currentMould.setThreadBoreBMax3(Float.parseFloat(this.txtMouldSizeB3Max.getText()));
        currentMould.setThreadBoreBMin3(Float.parseFloat(this.txtMouldSizeB3Min.getText()));
        currentMould.setTapPositionMax(Float.parseFloat(this.txtMouldTapMax.getText()));
        currentMould.setTapPositionMin(Float.parseFloat(this.txtMouldTapMin.getText()));
        currentMould.setWallNonDgUnderHandleMax(Float.parseFloat(this.txtMouldUnderHandleMax.getText()));
        currentMould.setWallNonDgUnderHandleMin(Float.parseFloat(this.txtMouldUnderHandleMin.getText()));
        currentMould.setVolumn(this.txtMouldVolume.getText());
        currentMould.setWeightDgMax(Float.parseFloat(this.txtMouldWeightMax.getText()));
        currentMould.setWeightDgMin(Float.parseFloat(this.txtMouldWeightMin.getText()));
        currentMould.setYear(this.txtMouldYear.getText());

        this.additiveService.UpdateEntity(currentMould);
        this.UpdateTabAdditive(currentMould.getId());
    }//GEN-LAST:event_btnMouldSaveActionPerformed

    private void btnMouldUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMouldUndoActionPerformed
        Mould currentMould = ((ComboBoxItem<Mould>) this.cbMachine.getSelectedItem()).getItem();
        this.UpdateTabMachine(currentMould.getId());
    }//GEN-LAST:event_btnMouldUndoActionPerformed

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
    private javax.swing.JButton btnMachineDelete;
    private javax.swing.JButton btnMachineNew;
    private javax.swing.JButton btnMachineSave;
    private javax.swing.JButton btnMachineUndo;
    private javax.swing.JButton btnMouldDelete;
    private javax.swing.JButton btnMouldNew;
    private javax.swing.JButton btnMouldSave;
    private javax.swing.JButton btnMouldUndo;
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
    private javax.swing.JComboBox cbAdditive;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
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
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
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
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTextField txtAdditiveCompany;
    private javax.swing.JTextField txtAdditiveDesc;
    private javax.swing.JTextField txtAdditiveGrade;
    private javax.swing.JTextField txtMachineCapacity;
    private javax.swing.JTextField txtMachineDesc;
    private javax.swing.JTextField txtMachineManufa;
    private javax.swing.JTextField txtMachineNo;
    private javax.swing.JTextField txtMachineSerial;
    private javax.swing.JTextField txtMachineYear;
    private javax.swing.JTextField txtMouldBaseMax;
    private javax.swing.JTextField txtMouldBaseMin;
    private javax.swing.JTextField txtMouldBoreAImage;
    private javax.swing.JTextField txtMouldBoreBImage;
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
    private javax.swing.JTextField txtMouldDgImage;
    private javax.swing.JTextField txtMouldDgUnderHandleMax;
    private javax.swing.JTextField txtMouldDgUnderHandleMin;
    private javax.swing.JTextField txtMouldDrawingImage;
    private javax.swing.JTextField txtMouldHandleBungMax;
    private javax.swing.JTextField txtMouldHandleBungMin;
    private javax.swing.JTextField txtMouldHandleLeftMax;
    private javax.swing.JTextField txtMouldHandleLeftMin;
    private javax.swing.JTextField txtMouldHandleRightMax;
    private javax.swing.JTextField txtMouldHandleRightMin;
    private javax.swing.JTextField txtMouldManufacturer;
    private javax.swing.JTextField txtMouldName;
    private javax.swing.JTextField txtMouldNeckImage;
    private javax.swing.JTextField txtMouldNonDgImage;
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
    private javax.swing.JTextField txtMouldTapImage;
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
