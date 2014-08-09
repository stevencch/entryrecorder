/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

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
 * @author Administrator
 */
@Entity
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findById", query = "SELECT r FROM Record r WHERE r.id = :id"),
    @NamedQuery(name = "Record.findByKey", query = "SELECT r FROM Record r WHERE r.key = :key"),
    @NamedQuery(name = "Record.findByNumberValue", query = "SELECT r FROM Record r WHERE r.numberValue = :numberValue"),
    @NamedQuery(name = "Record.findByStringValue", query = "SELECT r FROM Record r WHERE r.stringValue = :stringValue"),
    @NamedQuery(name = "Record.findByCreatedTime", query = "SELECT r FROM Record r WHERE r.createdTime = :createdTime"),
    @NamedQuery(name = "Record.findByStaffId", query = "SELECT r FROM Record r WHERE r.staffId = :staffId"),
    @NamedQuery(name = "Record.findByEntryId", query = "SELECT r FROM Record r WHERE r.entryId = :entryId")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Key")
    private String key;
    @Basic(optional = false)
    @Column(name = "NumberValue")
    private float numberValue;
    @Basic(optional = false)
    @Column(name = "StringValue")
    private String stringValue;
    @Basic(optional = false)
    @Column(name = "CreatedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Basic(optional = false)
    @Column(name = "StaffId")
    private int staffId;
    @Basic(optional = false)
    @Column(name = "EntryId")
    private int entryId;

    public Record() {
    }

    public Record(Integer id) {
        this.id = id;
    }

    public Record(Integer id, String key, float numberValue, String stringValue, Date createdTime, int staffId, int entryId) {
        this.id = id;
        this.key = key;
        this.numberValue = numberValue;
        this.stringValue = stringValue;
        this.createdTime = createdTime;
        this.staffId = staffId;
        this.entryId = entryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(float numberValue) {
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

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
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
    
}
