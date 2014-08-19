/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

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
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findById", query = "SELECT r FROM Record r WHERE r.id = :id"),
    @NamedQuery(name = "Record.findByRecordKey", query = "SELECT r FROM Record r WHERE r.recordKey = :recordKey"),
    @NamedQuery(name = "Record.findByNumberValue", query = "SELECT r FROM Record r WHERE r.numberValue = :numberValue"),
    @NamedQuery(name = "Record.findByStringValue", query = "SELECT r FROM Record r WHERE r.stringValue = :stringValue"),
    @NamedQuery(name = "Record.findByCreatedTime", query = "SELECT r FROM Record r WHERE r.createdTime = :createdTime")})
public class Record implements Serializable,SettingEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "RecordKey")
    private String recordKey;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NumberValue")
    private Float numberValue;
    @Column(name = "StringValue")
    private String stringValue;
    @Basic(optional = false)
    @Column(name = "CreatedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @JoinColumn(name = "EntryId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Entry entryId;
    @JoinColumn(name = "StaffId", referencedColumnName = "Id")
    @ManyToOne
    private Staff staffId;

    public Record() {
    }

    public Record(Integer id) {
        this.id = id;
    }

    public Record(Integer id, String recordKey, Date createdTime) {
        this.id = id;
        this.recordKey = recordKey;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public Float getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Float numberValue) {
        this.numberValue = numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Entry getEntryId() {
        return entryId;
    }

    public void setEntryId(Entry entryId) {
        this.entryId = entryId;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
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
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Record[ id=" + id + " ]";
    }

    @Override
    public void setDefaultValue() {
    }
    
}
