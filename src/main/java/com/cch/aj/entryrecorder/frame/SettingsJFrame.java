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
import com.cch.aj.entryrecorder.entities.Checkitem;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.stream;
import java.util.Collection;
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
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparing;
import java.util.Date;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Administrator
 */
public class SettingsJFrame extends javax.swing.JFrame implements ListSelectionListener {

    private int settingMouldId = 0;
    private int settingMouldPreviousId = 0;
    private Mould settingMould = new Mould();
    private Product settingProduct = new Product();
    private int settingCheckId = 0;
    private int templateProdcutId = 0;

    private SettingService<Machine> machineService = new SettingServiceImpl<Machine>(Machine.class);
    private SettingService<Polymer> polymerService = new SettingServiceImpl<Polymer>(Polymer.class);
    private SettingService<Additive> additiveService = new SettingServiceImpl<Additive>(Additive.class);
    private SettingService<Mould> mouldService = new SettingServiceImpl<Mould>(Mould.class);
    private SettingService<Product> productService = new SettingServiceImpl<Product>(Product.class);
    private SettingService<Checkitem> checkitemService = new SettingServiceImpl<Checkitem>(Checkitem.class);
    private SettingService<Staff> staffService = new SettingServiceImpl<Staff>(Staff.class);

    /**
     * Creates new form SettingsJFrame
     */
    public SettingsJFrame() {

        initComponents();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        ListSelectionModel model = tblCheck.getSelectionModel();
        model.addListSelectionListener(this);

        this.tblCheck.getColumnModel().getColumn(0).setMaxWidth(40);
        //load staff
        this.cbStaff.setRenderer(new ComboBoxRender());
        UpdateTabStaff(0);
    }

    private void UpdateTabMachine(int id) {
        int selectedIndex = FillMachineComboBox(this.cbMachine, id);
        if (selectedIndex >= 0) {
            Machine currentMachine = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
            this.txtMachineCapacity.setText(currentMachine.getCapacity());
            this.txtMachineDesc.setText(currentMachine.getDescription());
            this.txtMachineManufa.setText(currentMachine.getManufacturer());
            this.txtMachineNo.setText(currentMachine.getMachineNo() != "- Select -" ? currentMachine.getMachineNo() : "");
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

        if (this.cbMachine.getSelectedItem() == null || ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getId() == 0) {
            this.pnlEditMachine.setVisible(false);
            this.btnMachineDelete.setVisible(false);
            this.btnMachineSave.setVisible(false);
            this.btnMachineUndo.setVisible(false);
        } else {
            this.pnlEditMachine.setVisible(true);
            this.btnMachineDelete.setVisible(true);
            this.btnMachineSave.setVisible(true);
            this.btnMachineUndo.setVisible(true);
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

        if (this.cbPolymer.getSelectedItem() == null || ((ComboBoxItem<Polymer>) this.cbPolymer.getSelectedItem()).getId() == 0) {
            this.pnlEditPolymer.setVisible(false);
            this.btnPolymerDelete.setVisible(false);
            this.btnPolymerSave.setVisible(false);
            this.btnPolymerUndo.setVisible(false);
        } else {
            this.pnlEditPolymer.setVisible(true);
            this.btnPolymerDelete.setVisible(true);
            this.btnPolymerSave.setVisible(true);
            this.btnPolymerUndo.setVisible(true);
        }
    }

    private void UpdateTabAdditive(int id) {
        int selectedIndex = FillAdditiveComboBox(this.cbAdditive, id);
        if (selectedIndex >= 0) {
            Additive currentAdditive = ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getItem();
            //
            this.txtAdditiveCompany.setText(currentAdditive.getCompany() == "- Select -" ? "" : currentAdditive.getCompany());
            this.txtAdditiveDesc.setText(currentAdditive.getDescription() != null ? currentAdditive.getDescription() : "");
            this.txtAdditiveGrade.setText(currentAdditive.getGrade() != null ? currentAdditive.getGrade() : "");
        } else {
            this.cbAdditive.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtAdditiveCompany.setText("");
            this.txtAdditiveDesc.setText("");
            this.txtAdditiveGrade.setText("");
        }

        if (this.cbAdditive.getSelectedItem() == null || ((ComboBoxItem<Additive>) this.cbAdditive.getSelectedItem()).getId() == 0) {
            this.pnlEditAdditive.setVisible(false);
            this.btnAdditiveDelete.setVisible(false);
            this.btnAdditiveSave.setVisible(false);
            this.btnAdditiveUndo.setVisible(false);
        } else {
            this.pnlEditAdditive.setVisible(true);
            this.btnAdditiveDelete.setVisible(true);
            this.btnAdditiveSave.setVisible(true);
            this.btnAdditiveUndo.setVisible(true);
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

        if (this.cbMould.getSelectedItem() == null || ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getId() == 0) {
            this.pnlEditMould.setVisible(false);
            this.btnMouldDelete.setVisible(false);
            this.btnMouldSave.setVisible(false);
            this.btnMouldUndo.setVisible(false);
        } else {
            this.pnlEditMould.setVisible(true);
            this.btnMouldDelete.setVisible(true);
            this.btnMouldSave.setVisible(true);
            this.btnMouldUndo.setVisible(true);
        }
    }

    private void UpdateTabStaff(int id) {
        int selectedIndex = FillStaffComboBox(this.cbStaff, id, "");
        if (selectedIndex >= 0) {
            Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
            //
            this.cbStaffJob.setSelectedItem(currentStaff.getJobType());
            this.txtStaffName.setText(currentStaff.getName());
        } else {
            this.cbStaff.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.txtStaffName.setText("");
        }

        if (this.cbStaff.getSelectedItem() == null || ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getId() == 0) {
            this.pnlEditStaff.setVisible(false);
            this.btnStaffDelete.setVisible(false);
            this.btnStaffSave.setVisible(false);
            this.btnStaffUndo.setVisible(false);
        } else {
            this.pnlEditStaff.setVisible(true);
            this.btnStaffDelete.setVisible(true);
            this.btnStaffSave.setVisible(true);
            this.btnStaffUndo.setVisible(true);
        }
    }

    private void UpdateTabProduct(int id) {
        //product
        Product currentProduct = new Product();

        int selectedIndex = FillProductComboBox(this.cbProduct, id, this.settingMouldId);
        if (selectedIndex >= 0) {
            currentProduct = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
            this.settingProduct = currentProduct;
            this.UpdateProductUI(currentProduct);

        } else {
            this.cbProduct.setModel(new DefaultComboBoxModel(new ComboBoxItem[]{}));
            this.UpdateProductUI(new Product());
        }

        if (this.cbProduct.getSelectedItem() == null || ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getId() == 0) {
            this.pnlProductEdit.setVisible(false);
            this.btnProductDelete.setVisible(false);
            this.btnProductSave.setVisible(false);
            this.btnProductUndo.setVisible(false);
        } else {
            this.pnlProductEdit.setVisible(true);
            this.btnProductDelete.setVisible(true);
            this.btnProductSave.setVisible(true);
            this.btnProductUndo.setVisible(true);
        }

    }

    private int FillStaffComboBox(JComboBox comboBox, int id, String jobType) {
        int result = -1;
        List<Staff> staffs = this.staffService.GetAllEntities();
        if (!jobType.equals("")) {
            staffs = staffs.stream().filter(x -> x.getJobType().equals(jobType)).collect(Collectors.toList());
        }
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
            List<ComboBoxItem<Polymer>> polymerNames = polymers.stream().sorted(comparing(x -> x.getGrade())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getGrade() + " / " + x.getCompany(), x.getId())).collect(Collectors.toList());
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
            List<ComboBoxItem<Additive>> additiveNames = additives.stream().sorted(comparing(x -> x.getGrade())).map(x -> ComboBoxItemConvertor.ConvertToComboBoxItem(x, x.getGrade() + " / " + x.getCompany(), x.getId())).collect(Collectors.toList());
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
        comboBox.removeAll();
        List<Product> allProducts = this.productService.GetAllEntities();
        if (allProducts.size() > 0) {
            List<Product> products = allProducts.stream().filter(x -> x.getMouldId() != null && x.getMouldId().getId() == mouldId).collect(Collectors.toList());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlEditSetting = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        btnMachineNew = new javax.swing.JButton();
        cbMachine = new javax.swing.JComboBox();
        btnMachineDelete = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        btnMachineUndo = new javax.swing.JButton();
        btnMachineSave = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        pnlEditMachine = new javax.swing.JPanel();
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
        pnlEditMould = new javax.swing.JTabbedPane();
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
        pnlProductTab = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        btnProductNew = new javax.swing.JButton();
        cbProduct = new javax.swing.JComboBox();
        btnProductDelete = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        btnProductUndo = new javax.swing.JButton();
        btnProductSave = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        pnlEditProduct = new javax.swing.JPanel();
        pnlProductEdit = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        txtProductCode = new javax.swing.JTextField();
        cbProductPolymer = new javax.swing.JComboBox();
        txtProductPerc2 = new javax.swing.JTextField();
        txtProductPerc1 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        cbProductAdditive3 = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        cbProductAdditive1 = new javax.swing.JComboBox();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtProductPerc3 = new javax.swing.JTextField();
        cbProductAdditive2 = new javax.swing.JComboBox();
        cbProductMould = new javax.swing.JComboBox();
        jLabel63 = new javax.swing.JLabel();
        cbProductBoreB = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtProductWeightMax = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        cbProductBung = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtProductWeightMin = new javax.swing.JTextField();
        txtProductDesc = new javax.swing.JTextField();
        cbProductNeck = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cbProductDg = new javax.swing.JComboBox();
        cbProductPierced = new javax.swing.JComboBox();
        jLabel61 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        cbProductBoreA = new javax.swing.JComboBox();
        cbProductViewLine = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCheck = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        btnCheckUpdate = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        btnCheckDelete = new javax.swing.JButton();
        txtCheckDesc = new javax.swing.JTextField();
        btnCheckInsert = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        btnCheckPaste = new javax.swing.JButton();
        btnCheckCopy = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        pnlEditPolymer = new javax.swing.JPanel();
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
        pnlEditAdditive = new javax.swing.JPanel();
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
        pnlEditStaff = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        txtStaffName = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        cbStaffJob = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(908, 668));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlEditSetting.setName(""); // NOI18N
        pnlEditSetting.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                pnlEditSettingAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        pnlEditSetting.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pnlEditSettingStateChanged(evt);
            }
        });

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

        pnlEditMachine.setLayout(new java.awt.GridBagLayout());

        jLabel93.setText("MACHINE No编号");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel93, gridBagConstraints);

        jLabel94.setText("DESCRIPTION描述");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel94, gridBagConstraints);

        jLabel95.setText("MANUFACTURER产商");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel95, gridBagConstraints);

        jLabel96.setText("YEAR年份");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel96, gridBagConstraints);

        jLabel97.setText("SERIAL No编号");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel97, gridBagConstraints);

        jLabel98.setText("CAPACITY / VOL容积");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        pnlEditMachine.add(jLabel98, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineNo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineDesc, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineManufa, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineYear, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineSerial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 29);
        pnlEditMachine.add(txtMachineCapacity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel19.add(pnlEditMachine, gridBagConstraints);

        pnlEditSetting.addTab("Machine机器", jPanel19);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
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
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 4, 0);
        jPanel2.add(txtMouldName, gridBagConstraints);

        jLabel2.setText("PRODUCT WEIGHT DG重量");
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
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldCode, gridBagConstraints);

        jLabel4.setText("VOLUME容积");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("MANUFACTURER厂商");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("YEAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel8.setText("PRODUCT WEIGHT FOR NON-DG重量");
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
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 16, 0);
        jPanel2.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldVolume, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldManufacturer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldYear, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        jPanel2.add(txtMouldNonDgMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 16, 0);
        jPanel2.add(txtMouldNonDgMax, gridBagConstraints);

        jLabel11.setText("MIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldWeightMin, gridBagConstraints);

        jLabel12.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldWeightMax, gridBagConstraints);

        jLabel13.setText("TAP POSITION龙头");
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
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldTapMin, gridBagConstraints);

        jLabel15.setText("MAX");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 24, 4, 0);
        jPanel2.add(jLabel15, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 22);
        jPanel2.add(txtMouldTapMax, gridBagConstraints);

        pnlEditMould.addTab("General基本", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel17.setText("WALL THICKNESS NON-DG壁厚");
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

        jLabel20.setText("UNDER THE HANDLE把手下");
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

        jLabel24.setText("END OF HANDLE SIDE - LEFT把手左");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel3.add(jLabel24, gridBagConstraints);

        jLabel25.setText("END OF HANDLE SIDE - RIGHT把手右");
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

        pnlEditMould.addTab("Wall Non-DG壁厚", jPanel3);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel26.setText("WALL THICKNESS - DG壁厚");
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

        jLabel29.setText("UNDER THE HANDLE把手下");
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

        jLabel33.setText("END OF HANDLE SIDE - LEFT把手左");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 8, 0);
        jPanel8.add(jLabel33, gridBagConstraints);

        jLabel34.setText("END OF HANDLE SIDE - RIGHT把手右");
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

        pnlEditMould.addTab("Wall DG壁厚", jPanel8);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel35.setText("BORE DIAMETER A钻孔");
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

        jLabel41.setText("BORE DIAMETER B钻孔");
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

        jLabel48.setText("NECK HEIGHT颈长");
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

        pnlEditMould.addTab("Thread", jPanel4);

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

        pnlEditMould.addTab("Images图片", jTabbedPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        jPanel16.add(pnlEditMould, gridBagConstraints);

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

        pnlEditSetting.addTab("Mould型号", jPanel16);

        pnlProductTab.setLayout(new java.awt.GridBagLayout());

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
        pnlProductTab.add(jPanel26, gridBagConstraints);

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
        pnlProductTab.add(jPanel27, gridBagConstraints);

        pnlEditProduct.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pnlProductTab.add(pnlEditProduct, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(txtProductCode, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductPolymer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(txtProductPerc2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(txtProductPerc1, gridBagConstraints);

        jLabel68.setText("PERCENTAGE百分比");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel68, gridBagConstraints);

        jLabel67.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel67, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductAdditive3, gridBagConstraints);

        jLabel54.setText("BUNG REQUIRED塞子");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel54, gridBagConstraints);

        jLabel71.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel71, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductAdditive1, gridBagConstraints);

        jLabel55.setText("DESCRIPTION描述");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel55, gridBagConstraints);

        jLabel57.setText("MOULD型号");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel57, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(txtProductPerc3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductAdditive2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductMould, gridBagConstraints);

        jLabel63.setText("WEIGHT MAX重量");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel63, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductBoreB, gridBagConstraints);

        jLabel60.setText("ADDITIVE添加物");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel60, gridBagConstraints);

        jLabel70.setText("PERCENTAGE百分比");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel70, gridBagConstraints);

        jLabel58.setText("POLYMER TYPE聚合物");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel58, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(txtProductWeightMax, gridBagConstraints);

        jLabel72.setText("PERCENTAGE百分比");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel72, gridBagConstraints);

        jLabel53.setText("CODE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel53, gridBagConstraints);

        jLabel65.setText("BORE B DIAMETRE钻孔");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel65, gridBagConstraints);

        cbProductBung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductBung, gridBagConstraints);

        jLabel62.setText("WEIGHT MIN重量");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel62, gridBagConstraints);

        jLabel56.setText("PIERCED钻穿");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel56, gridBagConstraints);

        jLabel59.setText("DG OR NON-DG");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel59, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(txtProductWeightMin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(txtProductDesc, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductNeck, gridBagConstraints);

        jLabel7.setText("BORE A DIAMETRE钻孔");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel7, gridBagConstraints);

        jLabel66.setText("NECK HEIGHT颈长");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel66, gridBagConstraints);

        cbProductDg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DG", "NON-DG" }));
        cbProductDg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductDgActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductDg, gridBagConstraints);

        cbProductPierced.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 25);
        jPanel6.add(cbProductPierced, gridBagConstraints);

        jLabel61.setText("VIEW LINE线");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(jLabel61, gridBagConstraints);

        jLabel69.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 46, 2, 9);
        jPanel6.add(jLabel69, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductBoreA, gridBagConstraints);

        cbProductViewLine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 9);
        jPanel6.add(cbProductViewLine, gridBagConstraints);

        pnlProductEdit.addTab("General基本", jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        tblCheck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCheck);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 7, 15, 7);
        jPanel7.add(jScrollPane1, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Detail"));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        btnCheckUpdate.setText("Update");
        btnCheckUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        jPanel12.add(btnCheckUpdate, gridBagConstraints);

        jLabel47.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        jPanel12.add(jLabel47, gridBagConstraints);

        btnCheckDelete.setText("Delete");
        btnCheckDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        jPanel12.add(btnCheckDelete, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanel12.add(txtCheckDesc, gridBagConstraints);

        btnCheckInsert.setText("Insert");
        btnCheckInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckInsertActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        jPanel12.add(btnCheckInsert, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 5, 7);
        jPanel7.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        btnCheckPaste.setText("Paste Template");
        btnCheckPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckPasteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel13.add(btnCheckPaste, gridBagConstraints);

        btnCheckCopy.setText("Copy Template");
        btnCheckCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckCopyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        jPanel13.add(btnCheckCopy, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 7, 12, 7);
        jPanel7.add(jPanel13, gridBagConstraints);

        pnlProductEdit.addTab("Check List检查表", jPanel7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlProductTab.add(pnlProductEdit, gridBagConstraints);

        pnlEditSetting.addTab("Product产品", pnlProductTab);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        pnlEditPolymer.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 89);
        pnlEditPolymer.add(txtPolymerGrade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 89);
        pnlEditPolymer.add(txtPolymerCompany, gridBagConstraints);

        jLabel81.setText("GRADE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        pnlEditPolymer.add(jLabel81, gridBagConstraints);

        jLabel79.setText("COMPANY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 0);
        pnlEditPolymer.add(jLabel79, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 89);
        pnlEditPolymer.add(txtPolymerDesc, gridBagConstraints);

        jLabel82.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 0);
        pnlEditPolymer.add(jLabel82, gridBagConstraints);

        jLabel88.setText("POLYMER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(12, 8, 8, 8);
        pnlEditPolymer.add(jLabel88, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel10.add(pnlEditPolymer, gridBagConstraints);

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

        jTabbedPane5.addTab("Polymer聚合物", jPanel10);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        pnlEditAdditive.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 89);
        pnlEditAdditive.add(txtAdditiveGrade, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 89);
        pnlEditAdditive.add(txtAdditiveCompany, gridBagConstraints);

        jLabel106.setText("GRADE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 4, 0);
        pnlEditAdditive.add(jLabel106, gridBagConstraints);

        jLabel107.setText("COMPANY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 5, 0);
        pnlEditAdditive.add(jLabel107, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 89);
        pnlEditAdditive.add(txtAdditiveDesc, gridBagConstraints);

        jLabel108.setText("DESCRIPTION");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 19, 0);
        pnlEditAdditive.add(jLabel108, gridBagConstraints);

        jLabel109.setText("ADDITIVE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(12, 8, 8, 8);
        pnlEditAdditive.add(jLabel109, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel14.add(pnlEditAdditive, gridBagConstraints);

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

        jTabbedPane5.addTab("Additive添加物", jPanel14);

        pnlEditSetting.addTab("Raw Material原料", jTabbedPane5);

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

        pnlEditStaff.setLayout(new java.awt.GridBagLayout());

        jLabel78.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 20);
        pnlEditStaff.add(jLabel78, gridBagConstraints);

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
        pnlEditStaff.add(txtStaffName, gridBagConstraints);

        jLabel90.setText("JOB TITLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 20);
        pnlEditStaff.add(jLabel90, gridBagConstraints);

        cbStaffJob.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PROCESS WORKER", "TECHNICIAN", "SUPERVISOR" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 6, 55);
        pnlEditStaff.add(cbStaffJob, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel29.add(pnlEditStaff, gridBagConstraints);

        pnlEditSetting.addTab("Staff员工", jPanel29);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 52;
        gridBagConstraints.ipady = 174;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 20);
        getContentPane().add(pnlEditSetting, gridBagConstraints);
        pnlEditSetting.getAccessibleContext().setAccessibleName("Tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbMachineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMachineActionPerformed
        // TODO add your handling code here:
        Machine currentItem = ((ComboBoxItem<Machine>) this.cbMachine.getSelectedItem()).getItem();
        this.UpdateTabMachine(currentItem.getId());
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
        UpdateTabPolymer(currentItem.getId());
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
        UpdateTabAdditive(currentItem.getId());
    }//GEN-LAST:event_cbAdditiveActionPerformed

    private void cbMouldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMouldActionPerformed
        Mould currentItem = ((ComboBoxItem<Mould>) this.cbMould.getSelectedItem()).getItem();
        this.UpdateTabMould(currentItem.getId());


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
        this.txtMouldDgHandleRightMin.setText(currentMould.getWallDgHandleRightMin() == null ? "" : currentMould.getWallDgHandleRightMin().toString());
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
            this.labDrawingImage.setText(currentMould.getImageDrawing().toString());
            AppHelper.DisplayImage(currentMould.getImageDrawing().toString(), this.pnlDrawingImage);
        } else {
            this.labDrawingImage.setText("Image File Path");
            this.pnlDrawingImage.removeAll();
        }
        if (currentMould.getImageDg() != null) {
            this.labDgImage.setText(currentMould.getImageDg().toString());
            AppHelper.DisplayImage(currentMould.getImageDg().toString(), this.pnlDgImage);
        } else {
            this.labDgImage.setText("Image File Path");
            this.pnlDgImage.removeAll();
        }
        if (currentMould.getImageNonDg() != null) {
            this.labNonDgImage.setText(currentMould.getImageNonDg().toString());
            AppHelper.DisplayImage(currentMould.getImageNonDg().toString(), this.pnlNonDgImage);
        } else {
            this.labNonDgImage.setText("Image File Path");
            this.pnlNonDgImage.removeAll();
        }
        if (currentMould.getImageTap() != null) {
            this.labTapImage.setText(currentMould.getImageTap().toString());
            AppHelper.DisplayImage(currentMould.getImageTap().toString(), this.pnlTapImage);
        } else {
            this.labTapImage.setText("Image File Path");
            this.pnlTapImage.removeAll();
        }
        if (currentMould.getImageNeck() != null) {
            this.labNeckImage.setText(currentMould.getImageNeck().toString());
            AppHelper.DisplayImage(currentMould.getImageNeck().toString(), this.pnlNeckImage);
        } else {
            this.labNeckImage.setText("Image File Path");
            this.pnlNeckImage.removeAll();
        }
        if (currentMould.getImageBoreA() != null) {
            this.labBoreAImage.setText(currentMould.getImageBoreA().toString());
            AppHelper.DisplayImage(currentMould.getImageBoreA().toString(), this.pnlBoreAImage);
        } else {
            this.labBoreAImage.setText("Image File Path");
            this.pnlBoreAImage.removeAll();
        }
        if (currentMould.getImageBoreB() != null) {
            this.labBoreBImage.setText(currentMould.getImageBoreB().toString());
            AppHelper.DisplayImage(currentMould.getImageBoreB().toString(), this.pnlBoreBImage);
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
            currentMould.setWallDgHandleRightMin(Float.parseFloat(this.txtMouldDgHandleRightMin.getText()));
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
        } else {
            currentMould.setThreadNeckSize1(null);
        }
        if (!this.txtMouldSize1Max.getText().equals("")) {
            currentMould.setThreadNeckMax1(Float.parseFloat(this.txtMouldSize1Max.getText()));
        } else {
            currentMould.setThreadNeckMax1(null);
        }
        if (!this.txtMouldSize1Min.getText().equals("")) {
            currentMould.setThreadNeckMin1(Float.parseFloat(this.txtMouldSize1Min.getText()));
        } else {
            currentMould.setThreadNeckMin1(null);
        }
        if (!this.txtMouldSize2.getText().equals("")) {
            currentMould.setThreadNeckSize2(this.txtMouldSize2.getText());
        } else {
            currentMould.setThreadNeckSize2(null);
        }
        if (!this.txtMouldSize2Max.getText().equals("")) {
            currentMould.setThreadNeckMax2(Float.parseFloat(this.txtMouldSize2Max.getText()));
        } else {
            currentMould.setThreadNeckMax2(null);
        }
        if (!this.txtMouldSize2Min.getText().equals("")) {
            currentMould.setThreadNeckMin2(Float.parseFloat(this.txtMouldSize2Min.getText()));
        } else {
            currentMould.setThreadNeckMin2(null);
        }
        if (!this.txtMouldSize3.getText().equals("")) {
            currentMould.setThreadNeckSize3(this.txtMouldSize3.getText());
        } else {
            currentMould.setThreadNeckSize3(null);
        }
        if (!this.txtMouldSize3Max.getText().equals("")) {
            currentMould.setThreadNeckMax3(Float.parseFloat(this.txtMouldSize3Max.getText()));
        } else {
            currentMould.setThreadNeckMax3(null);
        }
        if (!this.txtMouldSize3Min.getText().equals("")) {
            currentMould.setThreadNeckMin3(Float.parseFloat(this.txtMouldSize3Min.getText()));
        } else {
            currentMould.setThreadNeckMin3(null);
        }
        if (!this.txtMouldSizeA1.getText().equals("")) {
            currentMould.setThreadBoreASize1(this.txtMouldSizeA1.getText());
        } else {
            currentMould.setThreadBoreASize1(null);
        }
        if (!this.txtMouldSizeA1Max.getText().equals("")) {
            currentMould.setThreadBoreAMax1(Float.parseFloat(this.txtMouldSizeA1Max.getText()));
        } else {
            currentMould.setThreadBoreAMax1(null);
        }
        if (!this.txtMouldSizeA1Min.getText().equals("")) {
            currentMould.setThreadBoreAMin1(Float.parseFloat(this.txtMouldSizeA1Min.getText()));
        } else {
            currentMould.setThreadBoreAMin1(null);
        }
        if (!this.txtMouldSizeA2.getText().equals("")) {
            currentMould.setThreadBoreASize2(this.txtMouldSizeA2.getText());
        } else {
            currentMould.setThreadBoreASize2(null);
        }
        if (!this.txtMouldSizeA2Max.getText().equals("")) {
            currentMould.setThreadBoreAMax2(Float.parseFloat(this.txtMouldSizeA2Max.getText()));
        } else {
            currentMould.setThreadBoreAMax2(null);
        }
        if (!this.txtMouldSizeA2Min.getText().equals("")) {
            currentMould.setThreadBoreAMin2(Float.parseFloat(this.txtMouldSizeA2Min.getText()));
        } else {
            currentMould.setThreadBoreAMin2(null);
        }
        if (!this.txtMouldSizeA3.getText().equals("")) {
            currentMould.setThreadBoreASize3(this.txtMouldSizeA3.getText());
        } else {
            currentMould.setThreadBoreASize3(null);
        }
        if (!this.txtMouldSizeA3Max.getText().equals("")) {
            currentMould.setThreadBoreAMax3(Float.parseFloat(this.txtMouldSizeA3Max.getText()));
        } else {
            currentMould.setThreadBoreAMax3(null);
        }
        if (!this.txtMouldSizeA3Min.getText().equals("")) {
            currentMould.setThreadBoreAMin3(Float.parseFloat(this.txtMouldSizeA3Min.getText()));
        } else {
            currentMould.setThreadBoreAMin3(null);
        }
        if (!this.txtMouldSizeB1.getText().equals("")) {
            currentMould.setThreadBoreBSize1(this.txtMouldSizeB1.getText());
        } else {
            currentMould.setThreadBoreBSize1(null);
        }
        if (!this.txtMouldSizeB1Max.getText().equals("")) {
            currentMould.setThreadBoreBMax1(Float.parseFloat(this.txtMouldSizeB1Max.getText()));
        } else {
            currentMould.setThreadBoreBMax1(null);
        }
        if (!this.txtMouldSizeB1Min.getText().equals("")) {
            currentMould.setThreadBoreBMin1(Float.parseFloat(this.txtMouldSizeB1Min.getText()));
        } else {
            currentMould.setThreadBoreBMin1(null);
        }
        if (!this.txtMouldSizeB2.getText().equals("")) {
            currentMould.setThreadBoreBSize2(this.txtMouldSizeB2.getText());
        } else {
            currentMould.setThreadBoreBSize2(null);
        }
        if (!this.txtMouldSizeB2Max.getText().equals("")) {
            currentMould.setThreadBoreBMax2(Float.parseFloat(this.txtMouldSizeB2Max.getText()));
        } else {
            currentMould.setThreadBoreBMax2(null);
        }
        if (!this.txtMouldSizeB2Min.getText().equals("")) {
            currentMould.setThreadBoreBMin2(Float.parseFloat(this.txtMouldSizeB2Min.getText()));
        } else {
            currentMould.setThreadBoreBMin2(null);
        }
        if (!this.txtMouldSizeB3.getText().equals("")) {
            currentMould.setThreadBoreBSize3(this.txtMouldSizeB3.getText());
        } else {
            currentMould.setThreadBoreBSize3(null);
        }
        if (!this.txtMouldSizeB3Max.getText().equals("")) {
            currentMould.setThreadBoreBMax3(Float.parseFloat(this.txtMouldSizeB3Max.getText()));
        } else {
            currentMould.setThreadBoreBMax3(null);
        }
        if (!this.txtMouldSizeB3Min.getText().equals("")) {
            currentMould.setThreadBoreBMin3(Float.parseFloat(this.txtMouldSizeB3Min.getText()));
        } else {
            currentMould.setThreadBoreBMin3(null);
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
            currentMould.setImageBoreB(this.labBoreBImage.getText());
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
            currentMould.setImageNeck(this.labNeckImage.getText());
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

    private void pnlEditSettingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pnlEditSettingStateChanged
        int index = pnlEditSetting.getSelectedIndex();
        if (index == 2) {
            if (settingMouldPreviousId != settingMouldId) {
                this.UpdateTabProduct(0);
            }
            if (this.cbProduct.getSelectedItem() == null || ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getId() == 0) {
                this.pnlProductEdit.setVisible(false);
                this.btnProductDelete.setVisible(false);
                this.btnProductSave.setVisible(false);
                this.btnProductUndo.setVisible(false);
            } else {
                this.pnlProductEdit.setVisible(true);
                this.btnProductDelete.setVisible(true);
                this.btnProductSave.setVisible(true);
                this.btnProductUndo.setVisible(true);
            }
        }
    }//GEN-LAST:event_pnlEditSettingStateChanged

    private void cbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductActionPerformed
        Product currentItem = ((ComboBoxItem<Product>) this.cbProduct.getSelectedItem()).getItem();
        UpdateTabProduct(currentItem.getId());
    }//GEN-LAST:event_cbProductActionPerformed

    private void btnProductNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductNewActionPerformed
        if (this.settingMouldId == 0) {
            JOptionPane.showMessageDialog(this, "Please select mould first.", "Warning", JOptionPane.OK_OPTION);
        } else {
            int newId = this.productService.CreateEntity();
            if (this.settingMouldId != 0) {
                Product newProduct = (Product) this.productService.FindEntity(newId);
                newProduct.setMouldId(this.mouldService.FindEntity(this.settingMouldId));
                this.productService.UpdateEntity(newProduct);
            }
            UpdateTabProduct(newId);
        }
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
        currentProduct.setViewLine(this.cbProductViewLine.getSelectedItem().toString());
        currentProduct.setMouldId(((ComboBoxItem<Mould>) this.cbProductMould.getSelectedItem()).getItem());
        if (this.cbProductPolymer.getSelectedIndex() != 0) {
            currentProduct.setPolymerId(((ComboBoxItem<Polymer>) this.cbProductPolymer.getSelectedItem()).getItem());
        } else {
            currentProduct.setPolymerId(null);
        }
        if (this.cbProductAdditive1.getSelectedIndex() != 0) {
            currentProduct.setAdditiveAId(((ComboBoxItem<Additive>) this.cbProductAdditive1.getSelectedItem()).getItem());
        } else {
            currentProduct.setAdditiveAId(null);
        }
        if (this.cbProductAdditive2.getSelectedIndex() != 0) {
            currentProduct.setAdditiveBId(((ComboBoxItem<Additive>) this.cbProductAdditive2.getSelectedItem()).getItem());
        } else {
            currentProduct.setAdditiveBId(null);
        }
        if (this.cbProductAdditive3.getSelectedIndex() != 0) {
            currentProduct.setAdditiveCId(((ComboBoxItem<Additive>) this.cbProductAdditive3.getSelectedItem()).getItem());
        } else {
            currentProduct.setAdditiveCId(null);
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
        if (this.cbProductBoreA.getSelectedIndex() != 0) {
            currentProduct.setThreadBoreA(this.cbProductBoreA.getSelectedIndex());
        } else {
            currentProduct.setThreadBoreA(null);
        }
        if (this.cbProductBoreB.getSelectedIndex() != 0) {
            currentProduct.setThreadBoreB(this.cbProductBoreB.getSelectedIndex());
        } else {
            currentProduct.setThreadBoreB(null);
        }
        if (this.cbProductNeck.getSelectedIndex() != 0) {
            currentProduct.setThreadNeck(this.cbProductNeck.getSelectedIndex());
        } else {
            currentProduct.setThreadNeck(null);
        }

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

    private void pnlEditSettingAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pnlEditSettingAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlEditSettingAncestorAdded

    private void btnCheckInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInsertActionPerformed
        if (!this.txtCheckDesc.getText().equals("")) {
            String desc = this.txtCheckDesc.getText();

            int checkId = this.checkitemService.CreateEntity();
            Checkitem check = this.checkitemService.FindEntity(checkId);
            check.setDescription(desc);
            List<Product> list = new ArrayList<Product>();
            list.add(this.settingProduct);
            check.setProductCollection(list);
            this.checkitemService.UpdateEntity(check);
            DefaultTableModel model = (DefaultTableModel) this.tblCheck.getModel();
            model.addRow(new Object[]{check.getId(), desc});
            this.txtCheckDesc.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "please enter the valid data", "Warming", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_btnCheckInsertActionPerformed

    private void btnCheckPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckPasteActionPerformed
        if (this.templateProdcutId != 0 && this.templateProdcutId != this.settingProduct.getId()) {
            Product product = this.productService.FindEntity(this.templateProdcutId);
            for (Checkitem ci : product.getCheckitemCollection()) {
                this.settingProduct.getCheckitemCollection().add(ci);
            }
        }
        this.productService.UpdateEntity(settingProduct);
        this.UpdateProductUI(settingProduct);
    }//GEN-LAST:event_btnCheckPasteActionPerformed

    private void btnCheckUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckUpdateActionPerformed
        Checkitem item = this.checkitemService.FindEntity(this.settingCheckId);
        if (item != null) {
            item.setDescription(this.txtCheckDesc.getText());
        }
        this.checkitemService.UpdateEntity(item);
        this.UpdateProductUI(settingProduct);
    }//GEN-LAST:event_btnCheckUpdateActionPerformed

    private void btnCheckDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckDeleteActionPerformed
        Checkitem item = null;
        for (Checkitem check : this.settingProduct.getCheckitemCollection()) {
            if (check.getId() == this.settingCheckId) {
                item = check;
                break;
            }
        }
        if (item != null) {
            this.settingProduct.getCheckitemCollection().remove(item);
        }
        this.productService.UpdateEntity(settingProduct);
        this.UpdateProductUI(settingProduct);
    }//GEN-LAST:event_btnCheckDeleteActionPerformed

    private void btnCheckCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckCopyActionPerformed
        templateProdcutId = this.settingProduct.getId();
    }//GEN-LAST:event_btnCheckCopyActionPerformed

    private void btnStaffNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffNewActionPerformed
        // TODO add your handling code here:
        int newId = this.staffService.CreateEntity();
        UpdateTabStaff(newId);
    }//GEN-LAST:event_btnStaffNewActionPerformed

    private void cbStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStaffActionPerformed
        Staff currentStaff = ((ComboBoxItem<Staff>) this.cbStaff.getSelectedItem()).getItem();
        UpdateTabStaff(currentStaff.getId());
    }//GEN-LAST:event_cbStaffActionPerformed

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

    private void txtStaffNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStaffNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStaffNameActionPerformed

    private void cbProductDgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductDgActionPerformed
        if(this.cbProductDg.getSelectedIndex()==0){
            this.txtProductWeightMin.setText(this.settingMould.getWeightDgMin() != null ? this.settingMould.getWeightDgMin().toString() : "");
            this.txtProductWeightMax.setText(this.settingMould.getWeightDgMax() != null ? this.settingMould.getWeightDgMax().toString() : "");
        }
        else{
            this.txtProductWeightMin.setText(this.settingMould.getWeightNonDgMin() != null ? this.settingMould.getWeightNonDgMin().toString() : "");
            this.txtProductWeightMax.setText(this.settingMould.getWeightNonDgMax() != null ? this.settingMould.getWeightNonDgMax().toString() : "");
        }
    }//GEN-LAST:event_cbProductDgActionPerformed

    private void UpdateProductUI(Product currentProduct) {
        txtProductCode.setText(currentProduct.getCode() == null || currentProduct.getCode() == "- Select -" ? "" : currentProduct.getCode().toString());;
        txtProductDesc.setText(currentProduct.getDescription() == null ? "" : currentProduct.getDescription().toString());;
        txtProductPerc1.setText(currentProduct.getAdditiveAPercentage() == null ? "" : currentProduct.getAdditiveAPercentage().toString());;
        txtProductPerc2.setText(currentProduct.getAdditiveBPercentage() == null ? "" : currentProduct.getAdditiveBPercentage().toString());;
        txtProductPerc3.setText(currentProduct.getAdditiveCPercentage() == null ? "" : currentProduct.getAdditiveCPercentage().toString());
        txtProductWeightMax.setText(currentProduct.getWeightMax() == null ? "" : currentProduct.getWeightMax().toString());
        txtProductWeightMin.setText(currentProduct.getWeightMin() == null ? "" : currentProduct.getWeightMin().toString());
        //combobox
        this.FillMouldComboBox(this.cbProductMould, currentProduct.getMouldId() != null ? currentProduct.getMouldId().getId() : 0);
        this.FillPolymerComboBox(this.cbProductPolymer, currentProduct.getPolymerId() != null ? currentProduct.getPolymerId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive1, currentProduct.getAdditiveAId() != null ? currentProduct.getAdditiveAId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive2, currentProduct.getAdditiveBId() != null ? currentProduct.getAdditiveBId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive3, currentProduct.getAdditiveCId() != null ? currentProduct.getAdditiveCId().getId() : 0);
        List<String> threadBoresA = new ArrayList<String>();
        threadBoresA.add("- Select -");
        if (settingMould.getThreadBoreASize1() != null && !settingMould.getThreadBoreASize1().equals("")) {
            threadBoresA.add(settingMould.getThreadBoreASize1());
        }
        if (settingMould.getThreadBoreASize2() != null && !settingMould.getThreadBoreASize2().equals("")) {
            threadBoresA.add(settingMould.getThreadBoreASize2());
        }
        if (settingMould.getThreadBoreASize3() != null && !settingMould.getThreadBoreASize3().equals("")) {
            threadBoresA.add(settingMould.getThreadBoreASize3());
        }
        this.cbProductBoreA.setModel(new DefaultComboBoxModel(threadBoresA.toArray()));
        List<String> threadBoresB = new ArrayList<String>();
        threadBoresB.add("- Select -");
        if (settingMould.getThreadBoreBSize1() != null && !settingMould.getThreadBoreBSize1().equals("")) {
            threadBoresB.add(settingMould.getThreadBoreBSize1());
        }
        if (settingMould.getThreadBoreBSize2() != null && !settingMould.getThreadBoreBSize2().equals("")) {
            threadBoresB.add(settingMould.getThreadBoreBSize2());
        }
        if (settingMould.getThreadBoreBSize3() != null && !settingMould.getThreadBoreBSize3().equals("")) {
            threadBoresB.add(settingMould.getThreadBoreBSize3());
        }
        this.cbProductBoreB.setModel(new DefaultComboBoxModel(threadBoresB.toArray()));
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
        if (currentProduct.getViewLine() != null && !currentProduct.getViewLine().equals("")) {
            this.cbProductViewLine.setSelectedItem(currentProduct.getViewLine().toString());
        }
        if (currentProduct.getDgnondg() != null) {
            this.cbProductDg.setSelectedIndex(currentProduct.getDgnondg());
        }
        if (currentProduct.getThreadBoreA() != null) {
            this.cbProductBoreA.setSelectedIndex(currentProduct.getThreadBoreA());
        }
        if (currentProduct.getThreadBoreB() != null) {
            this.cbProductBoreB.setSelectedIndex(currentProduct.getThreadBoreB());
        }
        if (currentProduct.getThreadNeck() != null) {
            this.cbProductNeck.setSelectedIndex(currentProduct.getThreadNeck());
        }
        //
        DefaultTableModel model = (DefaultTableModel) this.tblCheck.getModel();
        model.setRowCount(0);
        model.fireTableDataChanged();
        List<Checkitem> checks = this.checkitemService.GetAllEntities();
        List<Checkitem> list = new ArrayList<Checkitem>();
        for (Checkitem ci : checks) {
            if (ci.getProductCollection().contains(this.settingProduct)) {
                list.add(ci);
            }
        }
        if (list != null) {
            for (Checkitem item : list) {
                model.addRow(new Object[]{item.getId(), item.getDescription()});
            }
        }
        this.txtCheckDesc.setText("");
        //
        if (this.settingProduct.getWeightMin() == null && this.settingProduct.getWeightMax() == null) {
            this.txtProductWeightMin.setText(this.settingMould.getWeightDgMin() != null ? this.settingMould.getWeightDgMin().toString() : "");
            this.txtProductWeightMax.setText(this.settingMould.getWeightDgMax() != null ? this.settingMould.getWeightDgMax().toString() : "");
        } else {
            this.txtProductWeightMin.setText(this.settingProduct.getWeightMin() != null ? this.settingProduct.getWeightMin().toString() : "");
            this.txtProductWeightMax.setText(this.settingProduct.getWeightMax() != null ? this.settingProduct.getWeightMax().toString() : "");
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
            java.util.logging.Logger.getLogger(SettingsJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingsJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnCheckCopy;
    private javax.swing.JButton btnCheckDelete;
    private javax.swing.JButton btnCheckInsert;
    private javax.swing.JButton btnCheckPaste;
    private javax.swing.JButton btnCheckUpdate;
    private javax.swing.JButton btnDgImage;
    private javax.swing.JButton btnDrawingImage;
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbAdditive;
    private javax.swing.JComboBox cbMachine;
    private javax.swing.JComboBox cbMould;
    private javax.swing.JComboBox cbPolymer;
    private javax.swing.JComboBox cbProduct;
    private javax.swing.JComboBox cbProductAdditive1;
    private javax.swing.JComboBox cbProductAdditive2;
    private javax.swing.JComboBox cbProductAdditive3;
    private javax.swing.JComboBox cbProductBoreA;
    private javax.swing.JComboBox cbProductBoreB;
    private javax.swing.JComboBox cbProductBung;
    private javax.swing.JComboBox cbProductDg;
    private javax.swing.JComboBox cbProductMould;
    private javax.swing.JComboBox cbProductNeck;
    private javax.swing.JComboBox cbProductPierced;
    private javax.swing.JComboBox cbProductPolymer;
    private javax.swing.JComboBox cbProductViewLine;
    private javax.swing.JComboBox cbStaff;
    private javax.swing.JComboBox cbStaffJob;
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
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
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
    private javax.swing.JPanel pnlEditAdditive;
    private javax.swing.JPanel pnlEditMachine;
    private javax.swing.JTabbedPane pnlEditMould;
    private javax.swing.JPanel pnlEditPolymer;
    private javax.swing.JPanel pnlEditProduct;
    private javax.swing.JTabbedPane pnlEditSetting;
    private javax.swing.JPanel pnlEditStaff;
    private javax.swing.JPanel pnlNeckImage;
    private javax.swing.JPanel pnlNonDgImage;
    private javax.swing.JTabbedPane pnlProductEdit;
    private javax.swing.JPanel pnlProductTab;
    private javax.swing.JPanel pnlTapImage;
    private javax.swing.JTable tblCheck;
    private javax.swing.JTextField txtAdditiveCompany;
    private javax.swing.JTextField txtAdditiveDesc;
    private javax.swing.JTextField txtAdditiveGrade;
    private javax.swing.JTextField txtCheckDesc;
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && tblCheck.getSelectedRow() != -1) {
            TableModel model = tblCheck.getModel();
            int checkId = (int) model.getValueAt(tblCheck.getSelectedRow(), 0);
            String desc = model.getValueAt(tblCheck.getSelectedRow(), 1).toString();
            this.settingCheckId = checkId;
            this.txtCheckDesc.setText(desc);
        }
    }

}
