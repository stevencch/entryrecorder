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
import com.cch.aj.entryrecorder.common.ProcessBuilderWrapper;
import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.services.RecordSettingService;
import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.RecordSettingServiceImpl;
import com.cch.aj.entryrecorder.services.impl.RecordValidationServiceImpl;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    private SettingService<Polymer> polymerService = new SettingServiceImpl<Polymer>(Polymer.class);
    private SettingService<Additive> additiveService = new SettingServiceImpl<Additive>(Additive.class);
    private Entry currentEntry = null;

    DefaultCategoryDataset datasetWeight = new DefaultCategoryDataset();
    DefaultCategoryDataset datasetTap = new DefaultCategoryDataset();

    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {

        LoadMainForm();

        FillEntryComboBox(this.cbEntry, 0);
    }

    private void LoadMainForm() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //load entry
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        this.cbEntry.setRenderer(new ComboBoxRender());
        this.cbSupervisor1.setRenderer(new ComboBoxRender());
        this.cbSupervisor2.setRenderer(new ComboBoxRender());
        this.cbSupervisor3.setRenderer(new ComboBoxRender());
        this.cbTechnician1.setRenderer(new ComboBoxRender());
        this.cbTechnician2.setRenderer(new ComboBoxRender());
        this.cbTechnician3.setRenderer(new ComboBoxRender());
        this.cbWorker1.setRenderer(new ComboBoxRender());
        this.cbWorker2.setRenderer(new ComboBoxRender());
        this.cbWorker3.setRenderer(new ComboBoxRender());
        this.cbProductAdditive1.setRenderer(new ComboBoxRender());
        this.cbProductAdditive2.setRenderer(new ComboBoxRender());
        this.cbProductAdditive3.setRenderer(new ComboBoxRender());
        this.cbProductPolymer.setRenderer(new ComboBoxRender());
    }

    MainJFrame(int checkId) {
        LoadMainForm();
        this.currentEntry = this.entryService.FindEntity(checkId);
        AppHelper.entryProduct = currentEntry.getProductId();
        AppHelper.currentEntry = currentEntry;
        this.UpdateEntryForm();
        this.btnDone.setVisible(false);
        this.labShift.setText(this.currentEntry.getShift() + "/" + this.currentEntry.getMachineId().getMachineNo()
                + "/" + this.currentEntry.getProductId().getCode());
    }

    private void UpdateEntryForm() {
        List<Record> records = this.recordService.GetAllEntitiesByKeyAndRecord(RecordKey.ALL, this.currentEntry.getId());
        //load images
        if (currentEntry.getMouldId().getImageBoreA() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageBoreA(), this.pnlBoreImage1, 75);
        } else {
            AppHelper.DisplayImageFromResource("/b1.png", this.pnlBoreImage1, 75);
        }
        if (currentEntry.getMouldId().getImageBoreB() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageBoreB(), this.pnlBoreImage2, 75);
        } else {
            AppHelper.DisplayImageFromResource("/b2.png", this.pnlBoreImage2, 75);
        }
        if (currentEntry.getMouldId().getImageNeck() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageNeck(), this.pnlNeckImage, 75);
        } else {
            AppHelper.DisplayImageFromResource("/b3.png", this.pnlNeckImage, 75);
        }
        if (currentEntry.getMouldId().getImageTap() != null) {
            AppHelper.DisplayImage(currentEntry.getMouldId().getImageTap(), this.pnlTapImage, 75);
        } else {
            AppHelper.DisplayImageFromResource("/no_photo_small.png", this.pnlTapImage, 75);
        }
        if (currentEntry.getProductId().getDgnondg() != null && currentEntry.getProductId().getDgnondg() == 0) {
            if (currentEntry.getMouldId().getImageDg() != null) {
                AppHelper.DisplayImage(currentEntry.getMouldId().getImageDg(), this.pnlWallImage, 75);
            } else {
                AppHelper.DisplayImageFromResource("/no_photo_small.png", this.pnlWallImage, 75);
            }
        } else {
            if (currentEntry.getMouldId().getImageNonDg() != null) {
                AppHelper.DisplayImage(currentEntry.getMouldId().getImageNonDg(), this.pnlWallImage, 75);
            } else {
                AppHelper.DisplayImageFromResource("/no_photo_small.png", this.pnlWallImage, 75);
            }
        }
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
                "Product Weight (grams)", "", "",
                datasetWeight, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cpWeight = new ChartPanel(chartWeight);
        this.pnlChartWeight.removeAll();
        this.pnlChartWeight.add(cpWeight, gridBagConstraints);
        //wall
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
        datasetTap = new DefaultCategoryDataset();

        List<Record> recordsTap = records.stream().filter(x -> x.getRecordKey().equals("TAP_POSITION")).collect(Collectors.toList());
        DefaultTableModel modelTap = (DefaultTableModel) this.tblTap.getModel();
        modelTap.setRowCount(0);
        for (Record record : recordsTap) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            datasetTap.addValue(record.getNumberValue(), "Tap", time);
            modelTap.addRow(new Object[]{time, record.getStringValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblTap.getModel()).fireTableDataChanged();
        JFreeChart chartTap = ChartFactory.createLineChart(
                "Product Tap (kg)", "", "",
                datasetTap, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel cpTap = new ChartPanel(chartTap);
        this.pnlChartTap.removeAll();
        this.pnlChartTap.add(cpTap, gridBagConstraints);
        //Bore
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
        List<Record> recordsCheck = records.stream().filter(x -> x.getRecordKey().startsWith("CHECK_")).collect(Collectors.toList());
        DefaultTableModel modelCheck = (DefaultTableModel) this.tblCheck.getModel();
        modelCheck.setRowCount(0);
        for (Record record : recordsCheck) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            String name = record.getRecordKey();
            modelCheck.addRow(new Object[]{time, name, record.getStringValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblCheck.getModel()).fireTableDataChanged();
        //Drop
        List<Record> recordsDrop = records.stream().filter(x -> x.getRecordKey().startsWith("DROP_")).collect(Collectors.toList());
        DefaultTableModel modelDrop = (DefaultTableModel) this.tblDrop.getModel();
        modelDrop.setRowCount(0);
        for (Record record : recordsDrop) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String name = record.getRecordKey();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            modelDrop.addRow(new Object[]{time, name, record.getStringValue(), pass, staff});
        }
        ((AbstractTableModel) this.tblDrop.getModel()).fireTableDataChanged();
        //Bung
        List<Record> recordsBung = records.stream().filter(x -> x.getRecordKey().equals("BUNG")).collect(Collectors.toList());
        DefaultTableModel modelBung = (DefaultTableModel) this.tblBung.getModel();
        modelBung.setRowCount(0);
        for (Record record : recordsBung) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            String pass = record.getIsPass() == null ? "" : record.getIsPass();
            modelBung.addRow(new Object[]{time, record.getStringValue(), pass, staff});
        }
        //Cycle
        List<Record> recordsCycle = records.stream().filter(x -> x.getRecordKey().equals("CYCLE")).collect(Collectors.toList());
        DefaultTableModel modelCycle = (DefaultTableModel) this.tblCycle.getModel();
        modelCycle.setRowCount(0);
        for (Record record : recordsCycle) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String staff = record.getStaff() == null ? "" : record.getStaff();
            modelCycle.addRow(new Object[]{time, record.getStringValue(), staff});
        }
        //Seconds
        float totalSeconds = 0f;
        List<Record> recordsSeconds = records.stream().filter(x -> x.getRecordKey().equals("SECONDS")).collect(Collectors.toList());
        DefaultTableModel modelSeconds = (DefaultTableModel) this.tblSeconds.getModel();
        modelSeconds.setRowCount(0);
        for (Record record : recordsSeconds) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            modelSeconds.addRow(new Object[]{time, record.getNumberValue()});
            totalSeconds += record.getNumberValue();
        }
        this.labSecondsTotal.setText(Float.toString(totalSeconds));
        //Rejects
        float totalRejects = 0f;
        List<Record> recordsRejects = records.stream().filter(x -> x.getRecordKey().equals("REJECTS")).collect(Collectors.toList());
        DefaultTableModel modelRejects = (DefaultTableModel) this.tblRejects.getModel();
        modelRejects.setRowCount(0);
        for (Record record : recordsRejects) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            modelRejects.addRow(new Object[]{time, record.getNumberValue()});
            totalRejects += record.getNumberValue();
        }
        this.labRejectsTotal.setText(Float.toString(totalRejects));
        //material
        this.FillPolymerComboBox(this.cbProductPolymer, currentEntry.getPolymerId() != null ? currentEntry.getPolymerId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive1, currentEntry.getAdditiveAId() != null ? currentEntry.getAdditiveAId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive2, currentEntry.getAdditiveBId() != null ? currentEntry.getAdditiveAId().getId() : 0);
        this.FillAdditiveComboBox(this.cbProductAdditive3, currentEntry.getAdditiveCId() != null ? currentEntry.getAdditiveCId().getId() : 0);
        txtAdditiveABatchA.setText(currentEntry.getAdditiveABatchA() == null ? "" : currentEntry.getAdditiveABatchA().toString());
        txtAdditiveBBatchA.setText(currentEntry.getAdditiveBBatchA() == null ? "" : currentEntry.getAdditiveBBatchA().toString());
        txtAdditiveCBatchA.setText(currentEntry.getAdditiveCBatchA() == null ? "" : currentEntry.getAdditiveCBatchA().toString());
        txtAdditiveABatchB.setText(currentEntry.getAdditiveABatchB() == null ? "" : currentEntry.getAdditiveABatchB().toString());
        txtAdditiveBBatchB.setText(currentEntry.getAdditiveBBatchB() == null ? "" : currentEntry.getAdditiveBBatchB().toString());
        txtAdditiveCBatchB.setText(currentEntry.getAdditiveCBatchB() == null ? "" : currentEntry.getAdditiveCBatchB().toString());
        //staff
        this.FillStaffComboBox(this.cbSupervisor1, currentEntry.getSupervisor1() != null ? currentEntry.getSupervisor1().getId() : 0, "SUPERVISOR");
        this.FillStaffComboBox(this.cbSupervisor2, currentEntry.getSupervisor2() != null ? currentEntry.getSupervisor2().getId() : 0, "SUPERVISOR");
        this.FillStaffComboBox(this.cbSupervisor3, currentEntry.getSupervisor3() != null ? currentEntry.getSupervisor3().getId() : 0, "SUPERVISOR");
        this.FillStaffComboBox(this.cbTechnician1, currentEntry.getTechnician1() != null ? currentEntry.getTechnician1().getId() : 0, "TECHNICIAN");
        this.FillStaffComboBox(this.cbTechnician2, currentEntry.getTechnician2() != null ? currentEntry.getTechnician2().getId() : 0, "TECHNICIAN");
        this.FillStaffComboBox(this.cbTechnician3, currentEntry.getTechnician3() != null ? currentEntry.getTechnician3().getId() : 0, "TECHNICIAN");
        this.FillStaffComboBox(this.cbWorker1, currentEntry.getWorker1() != null ? currentEntry.getWorker1().getId() : 0, "PROCESS WORKER");
        this.FillStaffComboBox(this.cbWorker2, currentEntry.getWorker2() != null ? currentEntry.getWorker2().getId() : 0, "PROCESS WORKER");
        this.FillStaffComboBox(this.cbWorker3, currentEntry.getWorker3() != null ? currentEntry.getWorker3().getId() : 0, "PROCESS WORKER");
        //Leak
        List<Record> recordsLeak = records.stream().filter(x -> x.getRecordKey().startsWith("DROP_") || x.getRecordKey().equals("ANY_LEAK")).collect(Collectors.toList());
        DefaultTableModel modelLeak = (DefaultTableModel) this.tblLeak.getModel();
        modelLeak.setRowCount(0);
        for (Record record : recordsLeak) {
            String time = new SimpleDateFormat("HH:mm").format(record.getCreatedTime());
            String name = record.getRecordKey();
            modelLeak.addRow(new Object[]{time, name, record.getStringValue()});
        }
        ((AbstractTableModel) this.tblLeak.getModel()).fireTableDataChanged();
        this.txtLeakNotes.setVisible(false);
        //quantity
        this.txtPalletQuantity.setText(currentEntry.getPalletQuantity() == null ? "0" : currentEntry.getPalletQuantity().toString());
        this.txtOtherQuantity.setText(currentEntry.getOtherQuantity() == null ? "0" : currentEntry.getOtherQuantity().toString());
        this.txtPalletProducedA.setText(currentEntry.getPalletProducedA() == null ? "0" : currentEntry.getPalletProducedA().toString());
        this.txtPalletProducedB.setText(currentEntry.getPalletProducedB() == null ? "0" : currentEntry.getPalletProducedB().toString());
        int a1 = 0;
        int a2 = 0;
        int b1 = 0;
        int b2 = 0;
        a1 = Integer.parseInt(this.txtPalletProducedA.getText());
        b1 = Integer.parseInt(this.txtPalletProducedB.getText());
        a2 = Integer.parseInt(this.txtPalletQuantity.getText());
        b2 = Integer.parseInt(this.txtOtherQuantity.getText());
        this.labQuantityTotal.setText(Integer.toString((a1 * a2) + (b1 * b2)));
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

    private int FillEntryComboBox(JComboBox comboBox, int id) {
        int result = -1;
        List<Entry> allEntrys = this.entryService.GetAllEntities();
        if (allEntrys.size() > 0) {
            List<Entry> entrys = allEntrys.stream().filter(x -> x.getInUse().equals("YES")).collect(Collectors.toList());
            if (entrys.size() > 0) {
                List<ComboBoxItem<Entry>> entryNames = entrys.stream().sorted(comparing(x -> x.getCreateDate())).map(x -> ComboBoxItemConvertor
                        .ConvertToComboBoxItem(x, (x.getMachineId() != null ? x.getMachineId().getMachineNo() : "")
                                + "/" + (x.getProductId() != null ? x.getProductId().getCode() : ""), x.getId())).collect(Collectors.toList());
                Entry entry = new Entry();
                entry.setId(0);
                entry.setShift("- Select -");
                entryNames.add(0, new ComboBoxItem<Entry>(entry, entry.getShift(), entry.getId()));
                ComboBoxItem[] entryNamesArray = entryNames.toArray(new ComboBoxItem[entryNames.size()]);
                comboBox.setModel(new DefaultComboBoxModel(entryNamesArray));
                if (id != 0) {
                    ComboBoxItem<Entry> currentEntryName = entryNames.stream().filter(x -> x.getId() == id).findFirst().get();
                    result = entryNames.indexOf(currentEntryName);
                    this.currentEntry = ((ComboBoxItem<Entry>) comboBox.getSelectedItem()).getItem();
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

        jPanel1 = new javax.swing.JPanel();
        cbEntry = new javax.swing.JComboBox();
        btnDone = new javax.swing.JButton();
        labShift = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
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
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblCycle = new javax.swing.JTable();
        jPanel39 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        labBungStaff1 = new javax.swing.JLabel();
        btnCycle = new javax.swing.JButton();
        txtCycle = new javax.swing.JTextField();
        txtCycleStaff = new javax.swing.JTextField();
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
        pnlWallImage = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
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
        pnlTapImage = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        pnlChartTap = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblBung = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        labBungStaff = new javax.swing.JLabel();
        btnBung = new javax.swing.JButton();
        cbBung = new javax.swing.JComboBox();
        txtBungStaff = new javax.swing.JTextField();
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
        txtNeck = new javax.swing.JTextField();
        pnlBoreImage1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        pnlBoreImage2 = new javax.swing.JPanel();
        labBoreImage2 = new javax.swing.JLabel();
        pnlNeckImage = new javax.swing.JPanel();
        labNeckImage = new javax.swing.JLabel();
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
        jLabel49 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        txtPalletQuantity = new javax.swing.JTextField();
        txtPalletProducedA = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtOtherQuantity = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txtPalletProducedB = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        labQuantityTotal = new javax.swing.JLabel();
        btnQuantity = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRejects = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        btnRejects = new javax.swing.JButton();
        txtRejects = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        labRejectsTotal = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSeconds = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        btnSeconds = new javax.swing.JButton();
        txtSeconds = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        labSecondsTotal = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblLeak = new javax.swing.JTable();
        jPanel42 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtLeakFill = new javax.swing.JTextField();
        btnLeakFill = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        labLeakTime = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtLeakCheck = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        cbLeak = new javax.swing.JComboBox();
        btnLeakCheck = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtLeakNotes = new javax.swing.JTextArea();
        pnlProductTab = new javax.swing.JPanel();
        pnlEditProduct = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        cbProductPolymer = new javax.swing.JComboBox();
        jLabel63 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        cbProductAdditive1 = new javax.swing.JComboBox();
        txtAdditiveABatchA = new javax.swing.JTextField();
        cbProductAdditive2 = new javax.swing.JComboBox();
        txtAdditiveBBatchA = new javax.swing.JTextField();
        cbProductAdditive3 = new javax.swing.JComboBox();
        txtAdditiveCBatchA = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtPolymerBatchA = new javax.swing.JTextField();
        txtPolymerBatchB = new javax.swing.JTextField();
        txtAdditiveABatchB = new javax.swing.JTextField();
        txtAdditiveBBatchB = new javax.swing.JTextField();
        txtAdditiveCBatchB = new javax.swing.JTextField();
        btnMaterialSave = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        cbSupervisor1 = new javax.swing.JComboBox();
        cbSupervisor2 = new javax.swing.JComboBox();
        cbSupervisor3 = new javax.swing.JComboBox();
        cbTechnician1 = new javax.swing.JComboBox();
        cbTechnician2 = new javax.swing.JComboBox();
        cbTechnician3 = new javax.swing.JComboBox();
        cbWorker1 = new javax.swing.JComboBox();
        cbWorker2 = new javax.swing.JComboBox();
        cbWorker3 = new javax.swing.JComboBox();
        btnStaffSave = new javax.swing.JButton();
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cbEntry, gridBagConstraints);

        btnDone.setText("Complete完成");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 6);
        jPanel1.add(btnDone, gridBagConstraints);

        labShift.setText("shift");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(9, 3, 10, 3);
        jPanel1.add(labShift, gridBagConstraints);

        btnRefresh.setText("Refresh刷新");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 2, 4, 2);
        jPanel1.add(btnRefresh, gridBagConstraints);

        btnReport.setText("Report报表");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(btnReport, gridBagConstraints);

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
        jLabel11.setText("COLOUR颜色");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel11, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("DESCRIPTION描述");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel7, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("BUNG塞子");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel13, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("WEIGHT RANGE重量");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel9, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("PIERCED钻穿");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 2, 1);
        jPanel15.add(jLabel15, gridBagConstraints);

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setText("GRADE等级");
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

        jLabel21.setText("Product Weight (grams)重量");
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

        labWeightStaff.setText("Check By检查人");
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

        jTabbedPane1.addTab("Weight重量", jPanel5);

        jPanel37.setLayout(new java.awt.GridBagLayout());

        jPanel38.setLayout(new java.awt.GridBagLayout());

        tblCycle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value", "Staff"
            }
        ));
        jScrollPane10.setViewportView(tblCycle);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel38.add(jScrollPane10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel37.add(jPanel38, gridBagConstraints);

        jPanel39.setLayout(new java.awt.GridBagLayout());

        jLabel44.setText("Cycle Time循环时间");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel39.add(jLabel44, gridBagConstraints);

        labBungStaff1.setText("Check By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel39.add(labBungStaff1, gridBagConstraints);

        btnCycle.setText("Add");
        btnCycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCycleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel39.add(btnCycle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel39.add(txtCycle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel39.add(txtCycleStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel37.add(jPanel39, gridBagConstraints);

        jTabbedPane1.addTab("Cycle Time循环", jPanel37);

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

        jLabel22.setText("END OF HANDLE SIDE-RIGHT把手右");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel22, gridBagConstraints);

        txtWallStaff.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallStaff, gridBagConstraints);

        labWallStaff.setText("Record By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(btnWall, gridBagConstraints);

        jLabel2.setText("UNDER THE HANDLE把手下");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel2, gridBagConstraints);

        jLabel3.setText("BASE (CENTRE)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel3, gridBagConstraints);

        jLabel4.setText("CLOSURE SIDE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel4, gridBagConstraints);

        jLabel6.setText("END OF HANDLE SIDE-BUNG把手塞子");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel6, gridBagConstraints);

        jLabel8.setText("END OF HANDLE SIDE-LEFT把手左");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallUnderHandle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallBase, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallClosure, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleBung, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleLeft, gridBagConstraints);

        txtWallHandleRight.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 33, 9, 33);
        jPanel19.add(txtWallHandleRight, gridBagConstraints);

        pnlWallImage.setLayout(new java.awt.GridBagLayout());

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/no_photo_small.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlWallImage.add(jLabel37, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel19.add(pnlWallImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel17.add(jPanel19, gridBagConstraints);

        jTabbedPane1.addTab("Wall Thickness壁厚", jPanel17);

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

        jLabel23.setText("Tap龙头");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(jLabel23, gridBagConstraints);

        labTapStaff.setText("Check By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridy = 3;
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel21.add(txtTapStaff, gridBagConstraints);

        pnlTapImage.setLayout(new java.awt.GridBagLayout());

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/no_photo_small.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlTapImage.add(jLabel36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        jPanel21.add(pnlTapImage, gridBagConstraints);

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

        jTabbedPane1.addTab("Tap Position龙头", jPanel6);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel31.setLayout(new java.awt.GridBagLayout());

        tblBung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value", "Pass", "Staff"
            }
        ));
        jScrollPane7.setViewportView(tblBung);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel31.add(jScrollPane7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel7.add(jPanel31, gridBagConstraints);

        jPanel32.setLayout(new java.awt.GridBagLayout());

        jLabel39.setText("Bung塞子");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel32.add(jLabel39, gridBagConstraints);

        labBungStaff.setText("Check By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel32.add(labBungStaff, gridBagConstraints);

        btnBung.setText("Add");
        btnBung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBungActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel32.add(btnBung, gridBagConstraints);

        cbBung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select -", "Weak", "Good", "Strong" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel32.add(cbBung, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(9, 27, 9, 27);
        jPanel32.add(txtBungStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel7.add(jPanel32, gridBagConstraints);

        jTabbedPane1.addTab("Bung塞子", jPanel7);

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

        labBoreStaff.setText("Check By检查人");
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

        jLabel10.setText("BORE DIAMETRE 1钻孔");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(jLabel10, gridBagConstraints);

        jLabel12.setText("BORE DIAMETRE 2钻孔");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(jLabel12, gridBagConstraints);

        jLabel14.setText("NECK HEIGHT颈长");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 27, 3, 27);
        jPanel24.add(txtNeck, gridBagConstraints);

        pnlBoreImage1.setLayout(new java.awt.GridBagLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlBoreImage1.add(jLabel35, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(pnlBoreImage1, gridBagConstraints);

        pnlBoreImage2.setLayout(new java.awt.GridBagLayout());

        labBoreImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b2.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlBoreImage2.add(labBoreImage2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(pnlBoreImage2, gridBagConstraints);

        pnlNeckImage.setLayout(new java.awt.GridBagLayout());

        labNeckImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b3.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        pnlNeckImage.add(labNeckImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(pnlNeckImage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel22.add(jPanel24, gridBagConstraints);

        jTabbedPane1.addTab("Bore / Neck钻孔颈长", jPanel22);

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

        jLabel24.setText("STRENGTH OF DRUM桶强度");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel24, gridBagConstraints);

        labCheckStaff.setText("Check By检查人");
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

        jLabel16.setText("NECK ROUND颈圆");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel16, gridBagConstraints);

        jLabel17.setText("NECK COMPLETE颈完成");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel17, gridBagConstraints);

        jLabel18.setText("UNDER THE HANDLE把手下");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel18, gridBagConstraints);

        jLabel19.setText("BUNG, IF DRILLED塞子");
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

        jLabel25.setText("WEIGHT WITHIN RANGE重量符合");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 29, 3, 29);
        jPanel27.add(jLabel25, gridBagConstraints);

        jLabel26.setText("COLOUR / TEXTURE颜色材质");
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

        jTabbedPane1.addTab("Quality Check质检", jPanel25);

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

        labDropStaff.setText("Test By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(10, 29, 5, 29);
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
        gridBagConstraints.insets = new java.awt.Insets(10, 29, 5, 29);
        jPanel30.add(txtDropStaff, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(jPanel30, gridBagConstraints);

        jTabbedPane1.addTab("Drop Test摔落", jPanel28);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel49.setText("Pallet Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel49, gridBagConstraints);

        jLabel76.setText("Pallets Produced");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel76, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(txtPalletQuantity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(txtPalletProducedA, gridBagConstraints);

        jLabel77.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel77, gridBagConstraints);

        jLabel78.setText("Other Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel78, gridBagConstraints);

        jLabel79.setText("Pallets Produced");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel79, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(txtOtherQuantity, gridBagConstraints);

        jLabel80.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel80, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(txtPalletProducedB, gridBagConstraints);

        jLabel81.setText("Total Produced");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(jLabel81, gridBagConstraints);

        labQuantityTotal.setText("total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(labQuantityTotal, gridBagConstraints);

        btnQuantity.setText("Save");
        btnQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuantityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 7, 22);
        jPanel13.add(btnQuantity, gridBagConstraints);

        jTabbedPane1.addTab("Quantity Produced数量", jPanel13);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel33.setLayout(new java.awt.GridBagLayout());

        tblRejects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value"
            }
        ));
        jScrollPane8.setViewportView(tblRejects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel33.add(jScrollPane8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel9.add(jPanel33, gridBagConstraints);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        jLabel40.setText("Rejects次品");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel34.add(jLabel40, gridBagConstraints);

        btnRejects.setText("Add");
        btnRejects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel34.add(btnRejects, gridBagConstraints);

        txtRejects.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel34.add(txtRejects, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel9.add(jPanel34, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel42.setText("Rejects Total : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel10.add(jLabel42, gridBagConstraints);

        labRejectsTotal.setText("NA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(labRejectsTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel8.add(jPanel9, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel35.setLayout(new java.awt.GridBagLayout());

        tblSeconds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Value"
            }
        ));
        jScrollPane9.setViewportView(tblSeconds);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel35.add(jScrollPane9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.7;
        jPanel11.add(jPanel35, gridBagConstraints);

        jPanel36.setLayout(new java.awt.GridBagLayout());

        jLabel41.setText("Seconds二手");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel36.add(jLabel41, gridBagConstraints);

        btnSeconds.setText("Add");
        btnSeconds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecondsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel36.add(btnSeconds, gridBagConstraints);

        txtSeconds.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(21, 28, 4, 28);
        jPanel36.add(txtSeconds, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        jPanel11.add(jPanel36, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel43.setText("Seconds Total : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanel12.add(jLabel43, gridBagConstraints);

        labSecondsTotal.setText("NA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(labSecondsTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel8.add(jPanel11, gridBagConstraints);

        jTabbedPane1.addTab("Seconds / Rejects二手次品", jPanel8);

        jPanel40.setLayout(new java.awt.GridBagLayout());

        jPanel41.setLayout(new java.awt.GridBagLayout());

        tblLeak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Name", "Value"
            }
        ));
        jScrollPane11.setViewportView(tblLeak);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel41.add(jScrollPane11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(26, 3, 26, 3);
        jPanel40.add(jPanel41, gridBagConstraints);

        jPanel42.setLayout(new java.awt.GridBagLayout());

        jLabel45.setText("Stage 1 Testing测试");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel45, gridBagConstraints);

        jLabel46.setText("Water filled by注水");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel46, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(txtLeakFill, gridBagConstraints);

        btnLeakFill.setText("Add");
        btnLeakFill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeakFillActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(btnLeakFill, gridBagConstraints);

        jLabel47.setText("Stage 2 Examination检查");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 3, 13);
        jPanel42.add(jLabel47, gridBagConstraints);

        jLabel48.setText("Time filled时间");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel48, gridBagConstraints);

        labLeakTime.setText("time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(labLeakTime, gridBagConstraints);

        jLabel50.setText("Checked By检查人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel50, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(txtLeakCheck, gridBagConstraints);

        jLabel51.setText("Any Leaks漏水");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel51, gridBagConstraints);

        cbLeak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "YES", "NO" }));
        cbLeak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLeakActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(cbLeak, gridBagConstraints);

        btnLeakCheck.setText("Add");
        btnLeakCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeakCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(btnLeakCheck, gridBagConstraints);

        jLabel52.setText("Notes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jLabel52, gridBagConstraints);

        txtLeakNotes.setColumns(20);
        txtLeakNotes.setRows(5);
        txtLeakNotes.setMinimumSize(new java.awt.Dimension(150, 100));
        txtLeakNotes.setPreferredSize(new java.awt.Dimension(160, 100));
        jScrollPane12.setViewportView(txtLeakNotes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 13, 3, 13);
        jPanel42.add(jScrollPane12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(26, 3, 26, 3);
        jPanel40.add(jPanel42, gridBagConstraints);

        jTabbedPane1.addTab("Leak Test漏水", jPanel40);

        pnlProductTab.setLayout(new java.awt.GridBagLayout());

        pnlEditProduct.setLayout(new java.awt.GridBagLayout());

        jLabel62.setText("POLYMER聚合物");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel62, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(cbProductPolymer, gridBagConstraints);

        jLabel63.setText("ADDITIVE添加物");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel63, gridBagConstraints);

        jLabel67.setText("TYPE 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel67, gridBagConstraints);

        jLabel68.setText("Batch Number 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel68, gridBagConstraints);

        jLabel69.setText("TYPE 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel69, gridBagConstraints);

        jLabel70.setText("Batch Number 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel70, gridBagConstraints);

        jLabel71.setText("TYPE 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel71, gridBagConstraints);

        jLabel72.setText("Batch Number 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 33, 2, 29);
        pnlEditProduct.add(jLabel72, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(cbProductAdditive1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveABatchA, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(cbProductAdditive2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveBBatchA, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(cbProductAdditive3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveCBatchA, gridBagConstraints);

        jLabel64.setText("Batch Number 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel64, gridBagConstraints);

        jLabel65.setText("Polymer Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel65, gridBagConstraints);

        jLabel66.setText("Batch Number 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel66, gridBagConstraints);

        jLabel73.setText("Batch Number 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel73, gridBagConstraints);

        jLabel74.setText("Batch Number 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel74, gridBagConstraints);

        jLabel75.setText("Batch Number 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(jLabel75, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtPolymerBatchA, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtPolymerBatchB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveABatchB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveBBatchB, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 2, 29);
        pnlEditProduct.add(txtAdditiveCBatchB, gridBagConstraints);

        btnMaterialSave.setText("Save");
        btnMaterialSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        pnlEditProduct.add(btnMaterialSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlProductTab.add(pnlEditProduct, gridBagConstraints);

        jTabbedPane1.addTab("Raw Material原料", pnlProductTab);

        jPanel43.setLayout(new java.awt.GridBagLayout());

        jLabel53.setText("SUPERVISOR 1监管人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel53, gridBagConstraints);

        jLabel54.setText("SUPERVISOR 2监管人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel54, gridBagConstraints);

        jLabel55.setText("SUPERVISOR 3监管人");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel55, gridBagConstraints);

        jLabel56.setText("TECHNICIAN 1技术员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel56, gridBagConstraints);

        jLabel57.setText("TECHNICIAN 2技术员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel57, gridBagConstraints);

        jLabel58.setText("TECHNICIAN 3技术员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel58, gridBagConstraints);

        jLabel59.setText("MACHINE OPERATOR 1操作员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel59, gridBagConstraints);

        jLabel60.setText("MACHINE OPERATOR 2操作员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel60, gridBagConstraints);

        jLabel61.setText("MACHINE OPERATOR 3操作员");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(jLabel61, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbSupervisor1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbSupervisor2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbSupervisor3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbTechnician1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbTechnician2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbTechnician3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbWorker1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbWorker2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 2, 20);
        jPanel43.add(cbWorker3, gridBagConstraints);

        btnStaffSave.setText("Save");
        btnStaffSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel43.add(btnStaffSave, gridBagConstraints);

        jTabbedPane1.addTab("Staff员工", jPanel43);

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
            if (AppHelper.CheckTwoDigit(this.txtWeight.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.PRODUCT_WEIGHT, Float.parseFloat(this.txtWeight.getText()))) {
                    isSave = true;
                    pass = "YES";
                } else {
                    if (!this.txtWeightStaff.getText().equals("")) {
                        isSave = true;

                    } else {
                        JOptionPane.showMessageDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                        this.labWeightStaff.setVisible(true);
                        this.txtWeightStaff.setVisible(true);
                    }
                }
                staff = this.txtWeightStaff.getText();
                this.txtWeightStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number like (123.45).", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblWeight.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = Float.parseFloat(this.txtWeight.getText());
                model.addRow(new Object[]{time, value, pass, staff});
                ((AbstractTableModel) this.tblWeight.getModel()).fireTableDataChanged();
                datasetWeight.addValue(value, "Weight", time);
                this.labWeightStaff.setVisible(false);
                this.txtWeightStaff.setVisible(false);
                this.txtWeight.setText("");
                UpdateEntryData(now, value, RecordKey.PRODUCT_WEIGHT, staff, pass, "");
            }
        }
    }//GEN-LAST:event_btnWeightActionPerformed

    private void cbEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEntryActionPerformed
        if (this.cbEntry.getSelectedIndex() != 0) {
            this.currentEntry = ((ComboBoxItem<Entry>) this.cbEntry.getSelectedItem()).getItem();
            this.labShift.setText(this.currentEntry.getShift());
            AppHelper.entryProduct = currentEntry.getProductId();
            AppHelper.currentEntry = currentEntry;
            if (currentEntry.getIsChecked() == null || !currentEntry.getIsChecked()) {
                (new CheckJFrame()).setVisible(true);
            }
            this.btnDone.setVisible(true);
            this.UpdateEntryForm();
        } else {
            this.btnDone.setVisible(false);
        }
    }//GEN-LAST:event_cbEntryActionPerformed

    private void btnWallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWallActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        String checker = "";
        if (this.currentEntry != null) {
            if (AppHelper.CheckTwoDigit(this.txtWallBase.getText()) && AppHelper.CheckTwoDigit(this.txtWallHandleBung.getText())
                    && AppHelper.CheckTwoDigit(this.txtWallClosure.getText()) && AppHelper.CheckTwoDigit(this.txtWallHandleLeft.getText())
                    && AppHelper.CheckTwoDigit(this.txtWallHandleRight.getText()) && AppHelper.CheckTwoDigit(this.txtWallUnderHandle.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_BASE, Float.parseFloat(this.txtWallBase.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_CLOSURE, Float.parseFloat(this.txtWallClosure.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_BUNG, Float.parseFloat(this.txtWallHandleBung.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_LEFT, Float.parseFloat(this.txtWallHandleLeft.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_RIGHT, Float.parseFloat(this.txtWallHandleRight.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.WALL_UNDER_HANDLE, Float.parseFloat(this.txtWallUnderHandle.getText()))) {
                    isSave = true;
                } else {
                    checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = this.txtWallStaff.getText();
                this.txtWallStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number like (123.45).", "Warning", JOptionPane.OK_OPTION);
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
                Float valueHandleRight = Float.parseFloat(this.txtWallHandleRight.getText());
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_UNDER_HANDLE, Float.parseFloat(this.txtWallUnderHandle.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_UNDER_HANDLE, valueUnderHandle, pass, staff});
                UpdateEntryData(now, valueUnderHandle, RecordKey.WALL_UNDER_HANDLE, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_BASE, Float.parseFloat(this.txtWallBase.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_BASE, valueBase, "YES", staff});
                UpdateEntryData(now, valueBase, RecordKey.WALL_BASE, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_CLOSURE, Float.parseFloat(this.txtWallClosure.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_CLOSURE, valueClosure, pass, staff});
                UpdateEntryData(now, valueClosure, RecordKey.WALL_CLOSURE, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_BUNG, Float.parseFloat(this.txtWallHandleBung.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_BUNG, valueHandleBung, pass, staff});
                UpdateEntryData(now, valueHandleBung, RecordKey.WALL_HANDLE_BUNG, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_LEFT, Float.parseFloat(this.txtWallHandleLeft.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_LEFT, valueHandleLeft, pass, staff});
                UpdateEntryData(now, valueHandleLeft, RecordKey.WALL_HANDLE_LEFT, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.WALL_HANDLE_RIGHT, Float.parseFloat(this.txtWallHandleRight.getText()))) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.WALL_HANDLE_RIGHT, valueHandleRight, pass, staff});
                UpdateEntryData(now, valueHandleRight, RecordKey.WALL_HANDLE_RIGHT, staff, pass, "");
                
                ((AbstractTableModel) this.tblWall.getModel()).fireTableDataChanged();
                this.txtWallUnderHandle.setText("");
                this.txtWallBase.setText("");
                this.txtWallClosure.setText("");
                this.txtWallHandleBung.setText("");
                this.txtWallHandleLeft.setText("");
                this.txtWallHandleRight.setText("");
            }
        }
    }//GEN-LAST:event_btnWallActionPerformed

    private void btnTapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTapActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (this.cbTap.getSelectedIndex() != 0) {
                if (recordValidationService.Validate(currentEntry, RecordKey.TAP_POSITION, (float) this.cbTap.getSelectedIndex())) {
                    isSave = true;
                    pass = "YES";
                } else {
                    String checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                    pass = "NO(" + checker + ")";
                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = txtTapStaff.getText();
                this.txtTapStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please select the tap position.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblTap.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = (float) this.cbTap.getSelectedIndex();
                String stringValue = this.cbTap.getSelectedItem().toString();
                model.addRow(new Object[]{time, stringValue, pass, staff});
                ((AbstractTableModel) this.tblTap.getModel()).fireTableDataChanged();
                datasetTap.addValue(value, "Tap", time);
                this.cbTap.setSelectedIndex(0);
                UpdateEntryData(now, value, RecordKey.TAP_POSITION, staff, pass, stringValue);
            }
        }
    }//GEN-LAST:event_btnTapActionPerformed

    private void btnBoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoreActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        String checker = "";
        if (this.currentEntry != null) {
            if (AppHelper.CheckTwoDigit(this.txtBore1.getText()) && AppHelper.CheckTwoDigit(this.txtBore2.getText())
                    && AppHelper.CheckTwoDigit(this.txtNeck.getText())) {
                if (recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE, Float.parseFloat(this.txtBore1.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE, Float.parseFloat(this.txtBore2.getText()))
                        && recordValidationService.Validate(currentEntry, RecordKey.THREAD_NECK, Float.parseFloat(this.txtNeck.getText()))) {
                    isSave = true;

                } else {
                    checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);

                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = this.txtBoreStaff.getText();
                this.txtBoreStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the valid number like (123.45).", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblBore.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float valueBore1 = Float.parseFloat(this.txtBore1.getText());
                Float valueBore2 = Float.parseFloat(this.txtBore2.getText());
                Float valueNeck = Float.parseFloat(this.txtNeck.getText());
                if (recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE1, valueBore1)) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.THREAD_BORE1, valueBore1, pass, staff});
                UpdateEntryData(now, valueBore1, RecordKey.THREAD_BORE1, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.THREAD_BORE2, valueBore1)) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.THREAD_BORE2, valueBore1, pass, staff});
                UpdateEntryData(now, valueBore1, RecordKey.THREAD_BORE2, staff, pass, "");
                if (recordValidationService.Validate(currentEntry, RecordKey.THREAD_NECK, valueNeck)) {
                    pass = "YES";
                } else {
                    pass = "NO(" + checker + ")";
                }
                model.addRow(new Object[]{time, RecordKey.THREAD_NECK, valueBore1, pass, staff});
                UpdateEntryData(now, valueBore1, RecordKey.THREAD_NECK, staff, pass, "");

                ((AbstractTableModel) this.tblBore.getModel()).fireTableDataChanged();
                this.txtBore1.setText("");
                this.txtBore2.setText("");
                this.txtNeck.setText("");
                //

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
                    String checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                    pass = "NO(" + checker + ")";
                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = this.txtCheckStaff.getText();
                this.txtCheckStaff.setText("");
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

                //
                UpdateEntryData(now, (float) this.cbNeckRound.getSelectedIndex(), RecordKey.CHECK_NECK_ROUND, staff, pass, this.cbNeckRound.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbNeckComplete.getSelectedIndex(), RecordKey.CHECK_NECK_COMPLETE, staff, pass, this.cbNeckComplete.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbUnderTheHandle.getSelectedIndex(), RecordKey.CHECK_UNDER_THE_HANDLE, staff, pass, this.cbUnderTheHandle.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbBungIfDrilled.getSelectedIndex(), RecordKey.CHECK_BUNG_IF_DRILLED, staff, pass, this.cbBungIfDrilled.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbBase.getSelectedIndex(), RecordKey.CHECK_BASE, staff, pass, this.cbBase.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbStrengthOfDrum.getSelectedIndex(), RecordKey.CHECK_STRENGTH_OF_DRUM, staff, pass, this.cbStrengthOfDrum.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbWeightWithinRange.getSelectedIndex(), RecordKey.CHECK_WEIGHT_WITHIN_RANGE, staff, pass, this.cbWeightWithinRange.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbColourTexture.getSelectedIndex(), RecordKey.CHECK_COLOUR_TEXTURE, staff, pass, this.cbColourTexture.getSelectedItem().toString());

                this.cbNeckRound.setSelectedIndex(0);
                this.cbNeckComplete.setSelectedIndex(0);
                this.cbUnderTheHandle.setSelectedIndex(0);
                this.cbBungIfDrilled.setSelectedIndex(0);
                this.cbBase.setSelectedIndex(0);
                this.cbStrengthOfDrum.setSelectedIndex(0);
                this.cbWeightWithinRange.setSelectedIndex(0);
                this.cbColourTexture.setSelectedIndex(0);
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
                    String checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                    pass = "NO(" + checker + ")";
                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = this.txtDropStaff.getText();
                this.txtDropStaff.setText("");
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

                //
                UpdateEntryData(now, (float) this.cbDrop1.getSelectedIndex(), RecordKey.DROP_TEST_1, pass, staff, this.cbDrop1.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop2.getSelectedIndex(), RecordKey.DROP_TEST_2, pass, staff, this.cbDrop2.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop3.getSelectedIndex(), RecordKey.DROP_TEST_3, pass, staff, this.cbDrop3.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop4.getSelectedIndex(), RecordKey.DROP_TEST_4, pass, staff, this.cbDrop4.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop5.getSelectedIndex(), RecordKey.DROP_TEST_5, pass, staff, this.cbDrop5.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop6.getSelectedIndex(), RecordKey.DROP_TEST_6, pass, staff, this.cbDrop6.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop7.getSelectedIndex(), RecordKey.DROP_TEST_7, pass, staff, this.cbDrop7.getSelectedItem().toString());
                UpdateEntryData(now, (float) this.cbDrop8.getSelectedIndex(), RecordKey.DROP_TEST_8, pass, staff, this.cbDrop8.getSelectedItem().toString());
                this.cbDrop1.setSelectedIndex(0);
                this.cbDrop2.setSelectedIndex(0);
                this.cbDrop3.setSelectedIndex(0);
                this.cbDrop4.setSelectedIndex(0);
                this.cbDrop5.setSelectedIndex(0);
                this.cbDrop6.setSelectedIndex(0);
                this.cbDrop7.setSelectedIndex(0);
                this.cbDrop8.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_btnDropActionPerformed

    private void btnBungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBungActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (this.cbBung.getSelectedIndex() != 0) {
                if (this.cbBung.getSelectedIndex() != 1) {
                    isSave = true;
                    pass = "YES";
                } else {
                    String checker = JOptionPane.showInputDialog(this, "the value is not within the range, please entry technician name.", "Warning", JOptionPane.OK_OPTION);
                    pass = "NO(" + checker + ")";
                    if (!checker.equals("")) {
                        isSave = true;
                    }
                }
                staff = txtBungStaff.getText();
                this.txtBungStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please select the tap position.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblBung.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = (float) this.cbBung.getSelectedIndex();
                String stringValue = this.cbBung.getSelectedItem().toString();
                model.addRow(new Object[]{time, stringValue, pass, staff});
                ((AbstractTableModel) this.tblBung.getModel()).fireTableDataChanged();
                this.cbBung.setSelectedIndex(0);
                UpdateEntryData(now, value, RecordKey.BUNG, staff, pass, stringValue);
            }
        }
    }//GEN-LAST:event_btnBungActionPerformed

    private void btnRejectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectsActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (!this.txtRejects.getText().equals("")) {
                isSave = true;
                pass = "YES";
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the mount of the rejects.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblRejects.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = Float.parseFloat(this.txtRejects.getText());
                model.addRow(new Object[]{time, value});
                ((AbstractTableModel) this.tblRejects.getModel()).fireTableDataChanged();
                this.txtRejects.setText("");
                UpdateEntryData(now, value, RecordKey.BUNG, staff, pass, "");
            }
        }
    }//GEN-LAST:event_btnRejectsActionPerformed

    private void btnSecondsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecondsActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (!this.txtSeconds.getText().equals("")) {
                isSave = true;
                pass = "YES";
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the mount of the seconds.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblSeconds.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = Float.parseFloat(this.txtSeconds.getText());
                model.addRow(new Object[]{time, value});
                ((AbstractTableModel) this.tblSeconds.getModel()).fireTableDataChanged();
                this.txtSeconds.setText("");
                UpdateEntryData(now, value, RecordKey.BUNG, staff, pass, "");
            }
        }
    }//GEN-LAST:event_btnSecondsActionPerformed

    private void btnCycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCycleActionPerformed
        Boolean isSave = false;
        String staff = "";
        String pass = "NO";
        if (this.currentEntry != null) {
            if (!this.txtCycle.getText().equals("")) {
                isSave = true;
                pass = "YES";
                staff = txtCycleStaff.getText();
                this.txtCycleStaff.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the value of cycle time.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblCycle.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                Float value = (float) 0;
                String stringValue = this.txtCycle.getText();
                model.addRow(new Object[]{time, stringValue, staff});
                ((AbstractTableModel) this.tblCycle.getModel()).fireTableDataChanged();
                this.txtCycle.setText("");
                UpdateEntryData(now, value, RecordKey.CYCLE, staff, pass, stringValue);
            }
        }
    }//GEN-LAST:event_btnCycleActionPerformed

    private void btnMaterialSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialSaveActionPerformed
        //material
        if (this.cbProductPolymer.getSelectedIndex() != 0) {
            currentEntry.setPolymerId(((ComboBoxItem<Polymer>) this.cbProductPolymer.getSelectedItem()).getItem());
        }
        if (this.cbProductAdditive1.getSelectedIndex() != 0) {
            currentEntry.setAdditiveAId(((ComboBoxItem<Additive>) this.cbProductAdditive1.getSelectedItem()).getItem());
        }
        if (this.cbProductAdditive2.getSelectedIndex() != 0) {
            currentEntry.setAdditiveBId(((ComboBoxItem<Additive>) this.cbProductAdditive2.getSelectedItem()).getItem());
        }
        if (this.cbProductAdditive3.getSelectedIndex() != 0) {
            currentEntry.setAdditiveCId(((ComboBoxItem<Additive>) this.cbProductAdditive3.getSelectedItem()).getItem());
        }
        if (!this.txtAdditiveABatchA.getText().equals("")) {
            currentEntry.setAdditiveABatchA(this.txtAdditiveABatchA.getText());
        }
        if (!this.txtAdditiveBBatchA.getText().equals("")) {
            currentEntry.setAdditiveBBatchA(this.txtAdditiveBBatchA.getText());
        }
        if (!this.txtAdditiveCBatchA.getText().equals("")) {
            currentEntry.setAdditiveCBatchA(this.txtAdditiveCBatchA.getText());
        }
        if (!this.txtAdditiveABatchB.getText().equals("")) {
            currentEntry.setAdditiveABatchB(this.txtAdditiveABatchB.getText());
        }
        if (!this.txtAdditiveBBatchB.getText().equals("")) {
            currentEntry.setAdditiveBBatchB(this.txtAdditiveBBatchB.getText());
        }
        if (!this.txtAdditiveCBatchB.getText().equals("")) {
            currentEntry.setAdditiveCBatchB(this.txtAdditiveCBatchB.getText());
        }
        if (!this.txtPolymerBatchA.getText().equals("")) {
            currentEntry.setPolymerBatchA(this.txtPolymerBatchA.getText());
        }
        if (!this.txtPolymerBatchB.getText().equals("")) {
            currentEntry.setPolymerBatchB(this.txtPolymerBatchB.getText());
        }
        this.entryService.UpdateEntity(currentEntry);
    }//GEN-LAST:event_btnMaterialSaveActionPerformed

    private void btnStaffSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffSaveActionPerformed
        //staff
        if (this.cbSupervisor1.getSelectedItem() != null && this.cbSupervisor1.getSelectedIndex() != 0) {
            currentEntry.setSupervisor1(((ComboBoxItem<Staff>) this.cbSupervisor1.getSelectedItem()).getItem());
        }
        if (this.cbSupervisor2.getSelectedItem() != null && this.cbSupervisor2.getSelectedIndex() != 0) {
            currentEntry.setSupervisor2(((ComboBoxItem<Staff>) this.cbSupervisor2.getSelectedItem()).getItem());
        }
        if (this.cbSupervisor3.getSelectedItem() != null && this.cbSupervisor3.getSelectedIndex() != 0) {
            currentEntry.setSupervisor3(((ComboBoxItem<Staff>) this.cbSupervisor3.getSelectedItem()).getItem());
        }
        if (this.cbTechnician1.getSelectedItem() != null && this.cbTechnician1.getSelectedIndex() != 0) {
            currentEntry.setTechnician1(((ComboBoxItem<Staff>) this.cbTechnician1.getSelectedItem()).getItem());
        }
        if (this.cbTechnician2.getSelectedItem() != null && this.cbTechnician2.getSelectedIndex() != 0) {
            currentEntry.setTechnician2(((ComboBoxItem<Staff>) this.cbTechnician2.getSelectedItem()).getItem());
        }
        if (this.cbTechnician3.getSelectedItem() != null && this.cbTechnician3.getSelectedIndex() != 0) {
            currentEntry.setTechnician3(((ComboBoxItem<Staff>) this.cbTechnician3.getSelectedItem()).getItem());
        }
        if (this.cbWorker1.getSelectedItem() != null && this.cbWorker1.getSelectedIndex() != 0) {
            currentEntry.setWorker1(((ComboBoxItem<Staff>) this.cbWorker1.getSelectedItem()).getItem());
        }
        if (this.cbWorker2.getSelectedItem() != null && this.cbWorker2.getSelectedIndex() != 0) {
            currentEntry.setWorker2(((ComboBoxItem<Staff>) this.cbWorker2.getSelectedItem()).getItem());
        }
        if (this.cbWorker3.getSelectedItem() != null && this.cbWorker3.getSelectedIndex() != 0) {
            currentEntry.setWorker3(((ComboBoxItem<Staff>) this.cbWorker3.getSelectedItem()).getItem());
        }
        this.entryService.UpdateEntity(currentEntry);
    }//GEN-LAST:event_btnStaffSaveActionPerformed

    private void btnLeakFillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeakFillActionPerformed
        Boolean isSave = false;
        if (this.currentEntry != null) {
            if (!this.txtLeakFill.getText().equals("")) {
                isSave = true;
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the data.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblLeak.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                String stringValue = this.txtLeakFill.getText();
                model.addRow(new Object[]{time, RecordKey.LEAK_FILL, stringValue});
                ((AbstractTableModel) this.tblLeak.getModel()).fireTableDataChanged();
                this.txtLeakFill.setText("");
                UpdateEntryData(now, 0f, RecordKey.LEAK_FILL, "", "", stringValue);
                this.labLeakTime.setText(time);
            }
        }
    }//GEN-LAST:event_btnLeakFillActionPerformed

    private void btnLeakCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeakCheckActionPerformed
        Boolean isSave = false;
        if (this.currentEntry != null) {
            if (!this.txtLeakCheck.getText().equals("")) {
                isSave = true;
            } else {
                JOptionPane.showMessageDialog(this, "Please entry the data.", "Warning", JOptionPane.OK_OPTION);
            }

            if (isSave) {
                DefaultTableModel model = (DefaultTableModel) this.tblLeak.getModel();
                Date now = new Date();
                String time = new SimpleDateFormat("HH:mm").format(now);
                String stringValue = this.txtLeakCheck.getText();
                model.addRow(new Object[]{time, RecordKey.LEAK_CHECK, stringValue});
                this.txtLeakCheck.setText("");
                UpdateEntryData(now, 0f, RecordKey.LEAK_CHECK, "", "", stringValue);
                //
                stringValue = this.cbLeak.getSelectedItem().toString();
                model.addRow(new Object[]{time, RecordKey.ANY_LEAK, stringValue});
                this.cbLeak.setSelectedIndex(0);
                UpdateEntryData(now, 0f, RecordKey.ANY_LEAK, "", "", stringValue);
                //
                if (!this.txtLeakNotes.getText().equals("")) {
                    stringValue = this.txtLeakNotes.getText();
                    model.addRow(new Object[]{time, RecordKey.LEAK_NOTES, stringValue});
                    this.txtLeakNotes.setText("");
                    UpdateEntryData(now, 0f, RecordKey.LEAK_NOTES, "", "", stringValue);
                }
                //
                ((AbstractTableModel) this.tblLeak.getModel()).fireTableDataChanged();
            }
        }
    }//GEN-LAST:event_btnLeakCheckActionPerformed

    private void cbLeakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLeakActionPerformed
        if (this.cbLeak.getSelectedIndex() == 1) {
            this.txtLeakNotes.setVisible(true);
        } else {
            this.txtLeakNotes.setVisible(false);
        }
    }//GEN-LAST:event_cbLeakActionPerformed

    private void btnQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuantityActionPerformed
        int a1 = 0;
        int a2 = 0;
        int b1 = 0;
        int b2 = 0;
        if (StringUtils.isNumeric(this.txtPalletProducedA.getText())) {
            currentEntry.setPalletProducedA(Integer.parseInt(this.txtPalletProducedA.getText()));
            a1 = Integer.parseInt(this.txtPalletProducedA.getText());
        }
        if (StringUtils.isNumeric(this.txtPalletProducedB.getText())) {
            currentEntry.setPalletProducedB(Integer.parseInt(this.txtPalletProducedB.getText()));
            b1 = Integer.parseInt(this.txtPalletProducedB.getText());
        }
        if (StringUtils.isNumeric(this.txtPalletQuantity.getText())) {
            currentEntry.setPalletQuantity(Integer.parseInt(this.txtPalletQuantity.getText()));
            a2 = Integer.parseInt(this.txtPalletQuantity.getText());
        }
        if (StringUtils.isNumeric(this.txtOtherQuantity.getText())) {
            currentEntry.setOtherQuantity(Integer.parseInt(this.txtOtherQuantity.getText()));
            b2 = Integer.parseInt(this.txtOtherQuantity.getText());
        }
        this.entryService.UpdateEntity(currentEntry);
        this.labQuantityTotal.setText(Integer.toString((a1 * a2) + (b1 * b2)));
    }//GEN-LAST:event_btnQuantityActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        this.currentEntry.setInUse("NO");
        this.entryService.UpdateEntity(currentEntry);
        this.FillEntryComboBox(this.cbEntry, 0);
    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        this.FillEntryComboBox(this.cbEntry, 0);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        try {
            final File batchFile = new File(AppHelper.currentDir + "\\pdfs\\genReport.bat");
            List cmd = new ArrayList();
            cmd.add(batchFile.getAbsolutePath());
            cmd.add("-f");
            cmd.add("PDF");
            cmd.add("-p");
            cmd.add("\"entryId="
                    + AppHelper.currentEntry.getId() + "\"");
            cmd.add("-o");
            final String pdfFileName = AppHelper.currentDir + "\\pdfs\\report_" + AppHelper.currentEntry.getShift().replace(' ', '-') + "_"
                    + AppHelper.currentEntry.getMachineId().getMachineNo().replace(' ', '-') + "_"
                    + AppHelper.currentEntry.getProductId().getCode().replace(' ', '-') + "_" + (new SimpleDateFormat("yyyyMMdd")).format(new Date())
                    + ".pdf";
            cmd.add("\"" + pdfFileName + "\"");
            cmd.add("-F");
            cmd.add("\"" + AppHelper.currentDir + "\\pdfs\\entry.rptdesign\"");

            ProcessBuilderWrapper pbd = new ProcessBuilderWrapper(new File(AppHelper.currentDir + "\\pdfs\\"), cmd);
            System.out.println("Command has terminated with status: " + pbd.getStatus());
            System.out.println("Output:\n" + pbd.getInfos());
            System.out.println("Error: " + pbd.getErrors());

            //open
            File pdfFile = new File(pdfFileName);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File is not exists!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnReportActionPerformed

    private void UpdateEntryData(Date now, Float valueUnderHandle, RecordKey key, String staff, String pass, String stringValue) {
        int recordId = this.recordService.CreateEntity();
        Record record = this.recordService.FindEntity(recordId);
        record.setEntryId(this.currentEntry);
        record.setCreatedTime(now);
        record.setNumberValue(valueUnderHandle);
        record.setIsPass(pass);
        record.setRecordKey(key.toString());
        record.setStaff(staff);
        record.setStringValue(stringValue);
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
    private javax.swing.JButton btnBung;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnCycle;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnDrop;
    private javax.swing.JButton btnLeakCheck;
    private javax.swing.JButton btnLeakFill;
    private javax.swing.JButton btnMaterialSave;
    private javax.swing.JButton btnQuantity;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRejects;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSeconds;
    private javax.swing.JButton btnStaffSave;
    private javax.swing.JButton btnTap;
    private javax.swing.JButton btnWall;
    private javax.swing.JButton btnWeight;
    private javax.swing.JComboBox cbBase;
    private javax.swing.JComboBox cbBung;
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
    private javax.swing.JComboBox cbLeak;
    private javax.swing.JComboBox cbNeckComplete;
    private javax.swing.JComboBox cbNeckRound;
    private javax.swing.JComboBox cbProductAdditive1;
    private javax.swing.JComboBox cbProductAdditive2;
    private javax.swing.JComboBox cbProductAdditive3;
    private javax.swing.JComboBox cbProductPolymer;
    private javax.swing.JComboBox cbStrengthOfDrum;
    private javax.swing.JComboBox cbSupervisor1;
    private javax.swing.JComboBox cbSupervisor2;
    private javax.swing.JComboBox cbSupervisor3;
    private javax.swing.JComboBox cbTap;
    private javax.swing.JComboBox cbTechnician1;
    private javax.swing.JComboBox cbTechnician2;
    private javax.swing.JComboBox cbTechnician3;
    private javax.swing.JComboBox cbUnderTheHandle;
    private javax.swing.JComboBox cbWeightWithinRange;
    private javax.swing.JComboBox cbWorker1;
    private javax.swing.JComboBox cbWorker2;
    private javax.swing.JComboBox cbWorker3;
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
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labBoreImage2;
    private javax.swing.JLabel labBoreStaff;
    private javax.swing.JLabel labBungStaff;
    private javax.swing.JLabel labBungStaff1;
    private javax.swing.JLabel labCheckStaff;
    private javax.swing.JLabel labDropStaff;
    private javax.swing.JLabel labLeakTime;
    private javax.swing.JLabel labNeckImage;
    private javax.swing.JLabel labProductImage;
    private javax.swing.JLabel labQuantityTotal;
    private javax.swing.JLabel labRejectsTotal;
    private javax.swing.JLabel labSecondsTotal;
    private javax.swing.JLabel labShift;
    private javax.swing.JLabel labTapStaff;
    private javax.swing.JLabel labWallStaff;
    private javax.swing.JLabel labWeightStaff;
    private javax.swing.JPanel pnlBoreImage1;
    private javax.swing.JPanel pnlBoreImage2;
    private javax.swing.JPanel pnlChartTap;
    private javax.swing.JPanel pnlChartWeight;
    private javax.swing.JPanel pnlEditProduct;
    private javax.swing.JPanel pnlMouldImage;
    private javax.swing.JPanel pnlNeckImage;
    private javax.swing.JPanel pnlProductTab;
    private javax.swing.JPanel pnlTapImage;
    private javax.swing.JPanel pnlWallImage;
    private javax.swing.JTable tblBore;
    private javax.swing.JTable tblBung;
    private javax.swing.JTable tblCheck;
    private javax.swing.JTable tblCycle;
    private javax.swing.JTable tblDrop;
    private javax.swing.JTable tblLeak;
    private javax.swing.JTable tblRejects;
    private javax.swing.JTable tblSeconds;
    private javax.swing.JTable tblTap;
    private javax.swing.JTable tblWall;
    private javax.swing.JTable tblWeight;
    private javax.swing.JTextField txtAdditiveABatchA;
    private javax.swing.JTextField txtAdditiveABatchB;
    private javax.swing.JTextField txtAdditiveBBatchA;
    private javax.swing.JTextField txtAdditiveBBatchB;
    private javax.swing.JTextField txtAdditiveCBatchA;
    private javax.swing.JTextField txtAdditiveCBatchB;
    private javax.swing.JTextField txtBore1;
    private javax.swing.JTextField txtBore2;
    private javax.swing.JTextField txtBoreStaff;
    private javax.swing.JTextField txtBungStaff;
    private javax.swing.JTextField txtCheckStaff;
    private javax.swing.JTextField txtCycle;
    private javax.swing.JTextField txtCycleStaff;
    private javax.swing.JTextField txtDropStaff;
    private javax.swing.JTextField txtLeakCheck;
    private javax.swing.JTextField txtLeakFill;
    private javax.swing.JTextArea txtLeakNotes;
    private javax.swing.JTextField txtNeck;
    private javax.swing.JTextField txtOtherQuantity;
    private javax.swing.JTextField txtPalletProducedA;
    private javax.swing.JTextField txtPalletProducedB;
    private javax.swing.JTextField txtPalletQuantity;
    private javax.swing.JTextField txtPolymerBatchA;
    private javax.swing.JTextField txtPolymerBatchB;
    private javax.swing.JLabel txtProductBung;
    private javax.swing.JLabel txtProductCode;
    private javax.swing.JLabel txtProductColor;
    private javax.swing.JLabel txtProductDesc;
    private javax.swing.JLabel txtProductGrade;
    private javax.swing.JLabel txtProductPierced;
    private javax.swing.JLabel txtProductWeight;
    private javax.swing.JTextField txtRejects;
    private javax.swing.JTextField txtSeconds;
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
            AppHelper.DisplayImageFromResource("/no_photo.png", this.pnlMouldImage, 150);
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
