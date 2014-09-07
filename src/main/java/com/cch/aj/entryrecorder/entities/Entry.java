/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import com.cch.aj.entryrecorder.common.AppHelper;
import com.cch.aj.entryrecorder.common.SettingEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chacao
 */
@Entity
@Table(name = "entry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entry.findAll", query = "SELECT e FROM Entry e"),
    @NamedQuery(name = "Entry.findById", query = "SELECT e FROM Entry e WHERE e.id = :id"),
    @NamedQuery(name = "Entry.findByShift", query = "SELECT e FROM Entry e WHERE e.shift = :shift"),
    @NamedQuery(name = "Entry.findByCreateDate", query = "SELECT e FROM Entry e WHERE e.createDate = :createDate"),
    @NamedQuery(name = "Entry.findByWeightMin", query = "SELECT e FROM Entry e WHERE e.weightMin = :weightMin"),
    @NamedQuery(name = "Entry.findByWeightMax", query = "SELECT e FROM Entry e WHERE e.weightMax = :weightMax"),
    @NamedQuery(name = "Entry.findByTapPositionMin", query = "SELECT e FROM Entry e WHERE e.tapPositionMin = :tapPositionMin"),
    @NamedQuery(name = "Entry.findByTapPositionMax", query = "SELECT e FROM Entry e WHERE e.tapPositionMax = :tapPositionMax"),
    @NamedQuery(name = "Entry.findByThreadBoreMin", query = "SELECT e FROM Entry e WHERE e.threadBoreMin = :threadBoreMin"),
    @NamedQuery(name = "Entry.findByThreadBoreMax", query = "SELECT e FROM Entry e WHERE e.threadBoreMax = :threadBoreMax"),
    @NamedQuery(name = "Entry.findByThreadNeckMin", query = "SELECT e FROM Entry e WHERE e.threadNeckMin = :threadNeckMin"),
    @NamedQuery(name = "Entry.findByThreadNeckMax", query = "SELECT e FROM Entry e WHERE e.threadNeckMax = :threadNeckMax"),
    @NamedQuery(name = "Entry.findByInUse", query = "SELECT e FROM Entry e WHERE e.inUse = :inUse"),
    @NamedQuery(name = "Entry.findByWallUnderHandleMin", query = "SELECT e FROM Entry e WHERE e.wallUnderHandleMin = :wallUnderHandleMin"),
    @NamedQuery(name = "Entry.findByWallUnderHandleMax", query = "SELECT e FROM Entry e WHERE e.wallUnderHandleMax = :wallUnderHandleMax"),
    @NamedQuery(name = "Entry.findByWallBaseMin", query = "SELECT e FROM Entry e WHERE e.wallBaseMin = :wallBaseMin"),
    @NamedQuery(name = "Entry.findByWallBaseMax", query = "SELECT e FROM Entry e WHERE e.wallBaseMax = :wallBaseMax"),
    @NamedQuery(name = "Entry.findByWallClosureMin", query = "SELECT e FROM Entry e WHERE e.wallClosureMin = :wallClosureMin"),
    @NamedQuery(name = "Entry.findByWallClosureMax", query = "SELECT e FROM Entry e WHERE e.wallClosureMax = :wallClosureMax"),
    @NamedQuery(name = "Entry.findByWallHandleBungMin", query = "SELECT e FROM Entry e WHERE e.wallHandleBungMin = :wallHandleBungMin"),
    @NamedQuery(name = "Entry.findByWallHandleBungMax", query = "SELECT e FROM Entry e WHERE e.wallHandleBungMax = :wallHandleBungMax"),
    @NamedQuery(name = "Entry.findByWallHandleLeftMin", query = "SELECT e FROM Entry e WHERE e.wallHandleLeftMin = :wallHandleLeftMin"),
    @NamedQuery(name = "Entry.findByWallHandleLeftMax", query = "SELECT e FROM Entry e WHERE e.wallHandleLeftMax = :wallHandleLeftMax"),
    @NamedQuery(name = "Entry.findByWallHandleRightMin", query = "SELECT e FROM Entry e WHERE e.wallHandleRightMin = :wallHandleRightMin"),
    @NamedQuery(name = "Entry.findByWallHandleRightMax", query = "SELECT e FROM Entry e WHERE e.wallHandleRightMax = :wallHandleRightMax"),
    @NamedQuery(name = "Entry.findByAdditiveABatchA", query = "SELECT e FROM Entry e WHERE e.additiveABatchA = :additiveABatchA"),
    @NamedQuery(name = "Entry.findByAdditiveBBatchA", query = "SELECT e FROM Entry e WHERE e.additiveBBatchA = :additiveBBatchA"),
    @NamedQuery(name = "Entry.findByAdditiveCBatchA", query = "SELECT e FROM Entry e WHERE e.additiveCBatchA = :additiveCBatchA"),
    @NamedQuery(name = "Entry.findByAdditiveABatchB", query = "SELECT e FROM Entry e WHERE e.additiveABatchB = :additiveABatchB"),
    @NamedQuery(name = "Entry.findByAdditiveBBatchB", query = "SELECT e FROM Entry e WHERE e.additiveBBatchB = :additiveBBatchB"),
    @NamedQuery(name = "Entry.findByAdditiveCBatchB", query = "SELECT e FROM Entry e WHERE e.additiveCBatchB = :additiveCBatchB"),
    @NamedQuery(name = "Entry.findByPolymerBatchA", query = "SELECT e FROM Entry e WHERE e.polymerBatchA = :polymerBatchA"),
    @NamedQuery(name = "Entry.findByPolymerBatchB", query = "SELECT e FROM Entry e WHERE e.polymerBatchB = :polymerBatchB"),
    @NamedQuery(name = "Entry.findByPalletQuantity", query = "SELECT e FROM Entry e WHERE e.palletQuantity = :palletQuantity"),
    @NamedQuery(name = "Entry.findByPalletProducedA", query = "SELECT e FROM Entry e WHERE e.palletProducedA = :palletProducedA"),
    @NamedQuery(name = "Entry.findByPalletProducedB", query = "SELECT e FROM Entry e WHERE e.palletProducedB = :palletProducedB"),
    @NamedQuery(name = "Entry.findByOtherQuantity", query = "SELECT e FROM Entry e WHERE e.otherQuantity = :otherQuantity"),
    @NamedQuery(name = "Entry.findByIsChecked", query = "SELECT e FROM Entry e WHERE e.isChecked = :isChecked"),
    @NamedQuery(name = "Entry.findByMaterial", query = "SELECT e FROM Entry e WHERE e.material = :material")})
public class Entry implements Serializable,SettingEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Shift")
    private String shift;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WeightMin")
    private Float weightMin;
    @Column(name = "WeightMax")
    private Float weightMax;
    @Column(name = "TapPositionMin")
    private Float tapPositionMin;
    @Column(name = "TapPositionMax")
    private Float tapPositionMax;
    @Column(name = "ThreadBoreMin")
    private Float threadBoreMin;
    @Column(name = "ThreadBoreMax")
    private Float threadBoreMax;
    @Column(name = "ThreadNeckMin")
    private Float threadNeckMin;
    @Column(name = "ThreadNeckMax")
    private Float threadNeckMax;
    @Column(name = "InUse")
    private String inUse;
    @Column(name = "WallUnderHandleMin")
    private Float wallUnderHandleMin;
    @Column(name = "WallUnderHandleMax")
    private Float wallUnderHandleMax;
    @Column(name = "WallBaseMin")
    private Float wallBaseMin;
    @Column(name = "WallBaseMax")
    private Float wallBaseMax;
    @Column(name = "WallClosureMin")
    private Float wallClosureMin;
    @Column(name = "WallClosureMax")
    private Float wallClosureMax;
    @Column(name = "WallHandleBungMin")
    private Float wallHandleBungMin;
    @Column(name = "WallHandleBungMax")
    private Float wallHandleBungMax;
    @Column(name = "WallHandleLeftMin")
    private Float wallHandleLeftMin;
    @Column(name = "WallHandleLeftMax")
    private Float wallHandleLeftMax;
    @Column(name = "WallHandleRightMin")
    private Float wallHandleRightMin;
    @Column(name = "WallHandleRightMax")
    private Float wallHandleRightMax;
    @Column(name = "AdditiveABatchA")
    private String additiveABatchA;
    @Column(name = "AdditiveBBatchA")
    private String additiveBBatchA;
    @Column(name = "AdditiveCBatchA")
    private String additiveCBatchA;
    @Column(name = "AdditiveABatchB")
    private String additiveABatchB;
    @Column(name = "AdditiveBBatchB")
    private String additiveBBatchB;
    @Column(name = "AdditiveCBatchB")
    private String additiveCBatchB;
    @Column(name = "PolymerBatchA")
    private String polymerBatchA;
    @Column(name = "PolymerBatchB")
    private String polymerBatchB;
    @Column(name = "PalletQuantity")
    private Integer palletQuantity;
    @Column(name = "PalletProducedA")
    private Integer palletProducedA;
    @Column(name = "PalletProducedB")
    private Integer palletProducedB;
    @Column(name = "OtherQuantity")
    private Integer otherQuantity;
    @Column(name = "IsChecked")
    private Boolean isChecked;
    @Column(name = "Material")
    private String material;
    @JoinColumn(name = "MachineId", referencedColumnName = "Id")
    @ManyToOne
    private Machine machineId;
    @JoinColumn(name = "Worker1", referencedColumnName = "Id")
    @ManyToOne
    private Staff worker1;
    @JoinColumn(name = "Worker2", referencedColumnName = "Id")
    @ManyToOne
    private Staff worker2;
    @JoinColumn(name = "Supervisor3", referencedColumnName = "Id")
    @ManyToOne
    private Staff supervisor3;
    @JoinColumn(name = "Technician2", referencedColumnName = "Id")
    @ManyToOne
    private Staff technician2;
    @JoinColumn(name = "Worker3", referencedColumnName = "Id")
    @ManyToOne
    private Staff worker3;
    @JoinColumn(name = "PolymerId", referencedColumnName = "Id")
    @ManyToOne
    private Polymer polymerId;
    @JoinColumn(name = "Supervisor2", referencedColumnName = "Id")
    @ManyToOne
    private Staff supervisor2;
    @JoinColumn(name = "AdditiveAId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveAId;
    @JoinColumn(name = "AdditiveBId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveBId;
    @JoinColumn(name = "Technician3", referencedColumnName = "Id")
    @ManyToOne
    private Staff technician3;
    @JoinColumn(name = "AdditiveCId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveCId;
    @JoinColumn(name = "MouldId", referencedColumnName = "Id")
    @ManyToOne
    private Mould mouldId;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne
    private Product productId;
    @JoinColumn(name = "Supervisor1", referencedColumnName = "Id")
    @ManyToOne
    private Staff supervisor1;
    @JoinColumn(name = "Technician1", referencedColumnName = "Id")
    @ManyToOne
    private Staff technician1;

    public Entry() {
    }

    public Entry(Integer id) {
        this.id = id;
    }

    public Entry(Integer id, String shift) {
        this.id = id;
        this.shift = shift;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Float getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(Float weightMin) {
        this.weightMin = weightMin;
    }

    public Float getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(Float weightMax) {
        this.weightMax = weightMax;
    }

    public Float getTapPositionMin() {
        return tapPositionMin;
    }

    public void setTapPositionMin(Float tapPositionMin) {
        this.tapPositionMin = tapPositionMin;
    }

    public Float getTapPositionMax() {
        return tapPositionMax;
    }

    public void setTapPositionMax(Float tapPositionMax) {
        this.tapPositionMax = tapPositionMax;
    }

    public Float getThreadBoreMin() {
        return threadBoreMin;
    }

    public void setThreadBoreMin(Float threadBoreMin) {
        this.threadBoreMin = threadBoreMin;
    }

    public Float getThreadBoreMax() {
        return threadBoreMax;
    }

    public void setThreadBoreMax(Float threadBoreMax) {
        this.threadBoreMax = threadBoreMax;
    }

    public Float getThreadNeckMin() {
        return threadNeckMin;
    }

    public void setThreadNeckMin(Float threadNeckMin) {
        this.threadNeckMin = threadNeckMin;
    }

    public Float getThreadNeckMax() {
        return threadNeckMax;
    }

    public void setThreadNeckMax(Float threadNeckMax) {
        this.threadNeckMax = threadNeckMax;
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    public Float getWallUnderHandleMin() {
        return wallUnderHandleMin;
    }

    public void setWallUnderHandleMin(Float wallUnderHandleMin) {
        this.wallUnderHandleMin = wallUnderHandleMin;
    }

    public Float getWallUnderHandleMax() {
        return wallUnderHandleMax;
    }

    public void setWallUnderHandleMax(Float wallUnderHandleMax) {
        this.wallUnderHandleMax = wallUnderHandleMax;
    }

    public Float getWallBaseMin() {
        return wallBaseMin;
    }

    public void setWallBaseMin(Float wallBaseMin) {
        this.wallBaseMin = wallBaseMin;
    }

    public Float getWallBaseMax() {
        return wallBaseMax;
    }

    public void setWallBaseMax(Float wallBaseMax) {
        this.wallBaseMax = wallBaseMax;
    }

    public Float getWallClosureMin() {
        return wallClosureMin;
    }

    public void setWallClosureMin(Float wallClosureMin) {
        this.wallClosureMin = wallClosureMin;
    }

    public Float getWallClosureMax() {
        return wallClosureMax;
    }

    public void setWallClosureMax(Float wallClosureMax) {
        this.wallClosureMax = wallClosureMax;
    }

    public Float getWallHandleBungMin() {
        return wallHandleBungMin;
    }

    public void setWallHandleBungMin(Float wallHandleBungMin) {
        this.wallHandleBungMin = wallHandleBungMin;
    }

    public Float getWallHandleBungMax() {
        return wallHandleBungMax;
    }

    public void setWallHandleBungMax(Float wallHandleBungMax) {
        this.wallHandleBungMax = wallHandleBungMax;
    }

    public Float getWallHandleLeftMin() {
        return wallHandleLeftMin;
    }

    public void setWallHandleLeftMin(Float wallHandleLeftMin) {
        this.wallHandleLeftMin = wallHandleLeftMin;
    }

    public Float getWallHandleLeftMax() {
        return wallHandleLeftMax;
    }

    public void setWallHandleLeftMax(Float wallHandleLeftMax) {
        this.wallHandleLeftMax = wallHandleLeftMax;
    }

    public Float getWallHandleRightMin() {
        return wallHandleRightMin;
    }

    public void setWallHandleRightMin(Float wallHandleRightMin) {
        this.wallHandleRightMin = wallHandleRightMin;
    }

    public Float getWallHandleRightMax() {
        return wallHandleRightMax;
    }

    public void setWallHandleRightMax(Float wallHandleRightMax) {
        this.wallHandleRightMax = wallHandleRightMax;
    }

    public String getAdditiveABatchA() {
        return additiveABatchA;
    }

    public void setAdditiveABatchA(String additiveABatchA) {
        this.additiveABatchA = additiveABatchA;
    }

    public String getAdditiveBBatchA() {
        return additiveBBatchA;
    }

    public void setAdditiveBBatchA(String additiveBBatchA) {
        this.additiveBBatchA = additiveBBatchA;
    }

    public String getAdditiveCBatchA() {
        return additiveCBatchA;
    }

    public void setAdditiveCBatchA(String additiveCBatchA) {
        this.additiveCBatchA = additiveCBatchA;
    }

    public String getAdditiveABatchB() {
        return additiveABatchB;
    }

    public void setAdditiveABatchB(String additiveABatchB) {
        this.additiveABatchB = additiveABatchB;
    }

    public String getAdditiveBBatchB() {
        return additiveBBatchB;
    }

    public void setAdditiveBBatchB(String additiveBBatchB) {
        this.additiveBBatchB = additiveBBatchB;
    }

    public String getAdditiveCBatchB() {
        return additiveCBatchB;
    }

    public void setAdditiveCBatchB(String additiveCBatchB) {
        this.additiveCBatchB = additiveCBatchB;
    }

    public String getPolymerBatchA() {
        return polymerBatchA;
    }

    public void setPolymerBatchA(String polymerBatchA) {
        this.polymerBatchA = polymerBatchA;
    }

    public String getPolymerBatchB() {
        return polymerBatchB;
    }

    public void setPolymerBatchB(String polymerBatchB) {
        this.polymerBatchB = polymerBatchB;
    }

    public Integer getPalletQuantity() {
        return palletQuantity;
    }

    public void setPalletQuantity(Integer palletQuantity) {
        this.palletQuantity = palletQuantity;
    }

    public Integer getPalletProducedA() {
        return palletProducedA;
    }

    public void setPalletProducedA(Integer palletProducedA) {
        this.palletProducedA = palletProducedA;
    }

    public Integer getPalletProducedB() {
        return palletProducedB;
    }

    public void setPalletProducedB(Integer palletProducedB) {
        this.palletProducedB = palletProducedB;
    }

    public Integer getOtherQuantity() {
        return otherQuantity;
    }

    public void setOtherQuantity(Integer otherQuantity) {
        this.otherQuantity = otherQuantity;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Machine getMachineId() {
        return machineId;
    }

    public void setMachineId(Machine machineId) {
        this.machineId = machineId;
    }

    public Staff getWorker1() {
        return worker1;
    }

    public void setWorker1(Staff worker1) {
        this.worker1 = worker1;
    }

    public Staff getWorker2() {
        return worker2;
    }

    public void setWorker2(Staff worker2) {
        this.worker2 = worker2;
    }

    public Staff getSupervisor3() {
        return supervisor3;
    }

    public void setSupervisor3(Staff supervisor3) {
        this.supervisor3 = supervisor3;
    }

    public Staff getTechnician2() {
        return technician2;
    }

    public void setTechnician2(Staff technician2) {
        this.technician2 = technician2;
    }

    public Staff getWorker3() {
        return worker3;
    }

    public void setWorker3(Staff worker3) {
        this.worker3 = worker3;
    }

    public Polymer getPolymerId() {
        return polymerId;
    }

    public void setPolymerId(Polymer polymerId) {
        this.polymerId = polymerId;
    }

    public Staff getSupervisor2() {
        return supervisor2;
    }

    public void setSupervisor2(Staff supervisor2) {
        this.supervisor2 = supervisor2;
    }

    public Additive getAdditiveAId() {
        return additiveAId;
    }

    public void setAdditiveAId(Additive additiveAId) {
        this.additiveAId = additiveAId;
    }

    public Additive getAdditiveBId() {
        return additiveBId;
    }

    public void setAdditiveBId(Additive additiveBId) {
        this.additiveBId = additiveBId;
    }

    public Staff getTechnician3() {
        return technician3;
    }

    public void setTechnician3(Staff technician3) {
        this.technician3 = technician3;
    }

    public Additive getAdditiveCId() {
        return additiveCId;
    }

    public void setAdditiveCId(Additive additiveCId) {
        this.additiveCId = additiveCId;
    }

    public Mould getMouldId() {
        return mouldId;
    }

    public void setMouldId(Mould mouldId) {
        this.mouldId = mouldId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Staff getSupervisor1() {
        return supervisor1;
    }

    public void setSupervisor1(Staff supervisor1) {
        this.supervisor1 = supervisor1;
    }

    public Staff getTechnician1() {
        return technician1;
    }

    public void setTechnician1(Staff technician1) {
        this.technician1 = technician1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Entry[ id=" + id + " ]";
    }
    
    @Override
    public void setDefaultValue() {
        this.inUse="YES";
        this.createDate=new Date();
        this.shift=AppHelper.defaultShift;
    }
    
}
