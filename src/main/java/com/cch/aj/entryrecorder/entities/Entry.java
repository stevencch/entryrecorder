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
    @NamedQuery(name = "Entry.findByMachineId", query = "SELECT e FROM Entry e WHERE e.machineId = :machineId"),
    @NamedQuery(name = "Entry.findByMouldId", query = "SELECT e FROM Entry e WHERE e.mouldId = :mouldId"),
    @NamedQuery(name = "Entry.findByProductId", query = "SELECT e FROM Entry e WHERE e.productId = :productId"),
    @NamedQuery(name = "Entry.findByCreateDate", query = "SELECT e FROM Entry e WHERE e.createDate = :createDate"),
    @NamedQuery(name = "Entry.findByWallMin", query = "SELECT e FROM Entry e WHERE e.wallMin = :wallMin"),
    @NamedQuery(name = "Entry.findByWallMax", query = "SELECT e FROM Entry e WHERE e.wallMax = :wallMax"),
    @NamedQuery(name = "Entry.findByWeightMin", query = "SELECT e FROM Entry e WHERE e.weightMin = :weightMin"),
    @NamedQuery(name = "Entry.findByWeightMax", query = "SELECT e FROM Entry e WHERE e.weightMax = :weightMax"),
    @NamedQuery(name = "Entry.findByTapPositionMin", query = "SELECT e FROM Entry e WHERE e.tapPositionMin = :tapPositionMin"),
    @NamedQuery(name = "Entry.findByTapPositionMax", query = "SELECT e FROM Entry e WHERE e.tapPositionMax = :tapPositionMax"),
    @NamedQuery(name = "Entry.findByThreadBoreMin", query = "SELECT e FROM Entry e WHERE e.threadBoreMin = :threadBoreMin"),
    @NamedQuery(name = "Entry.findByThreadBoreMax", query = "SELECT e FROM Entry e WHERE e.threadBoreMax = :threadBoreMax"),
    @NamedQuery(name = "Entry.findByThreadNeckMin", query = "SELECT e FROM Entry e WHERE e.threadNeckMin = :threadNeckMin"),
    @NamedQuery(name = "Entry.findByThreadNeckMax", query = "SELECT e FROM Entry e WHERE e.threadNeckMax = :threadNeckMax"),
    @NamedQuery(name = "Entry.findByInUse", query = "SELECT e FROM Entry e WHERE e.inUse = :inUse")})
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
    @Column(name = "MachineId")
    private Integer machineId;
    @Column(name = "MouldId")
    private Integer mouldId;
    @Column(name = "ProductId")
    private Integer productId;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WallMin")
    private Float wallMin;
    @Column(name = "WallMax")
    private Float wallMax;
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

    public Entry() {
    }

    public Entry(Integer id) {
        this.id = id;
    }

    public Entry(Integer id, String shift, String inUse) {
        this.id = id;
        this.shift = shift;
        this.inUse = inUse;
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

    public Integer getMachineId() {
        return machineId;
    }

    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getMouldId() {
        return mouldId;
    }

    public void setMouldId(Integer mouldId) {
        this.mouldId = mouldId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Float getWallMin() {
        return wallMin;
    }

    public void setWallMin(Float wallMin) {
        this.wallMin = wallMin;
    }

    public Float getWallMax() {
        return wallMax;
    }

    public void setWallMax(Float wallMax) {
        this.wallMax = wallMax;
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
        this.shift=AppHelper.defaultShift;
        this.createDate=new Date();
    }
    
    
    
}
