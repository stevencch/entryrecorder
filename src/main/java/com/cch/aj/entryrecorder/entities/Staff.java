/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import com.cch.aj.entryrecorder.common.SettingEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chacao
 */
@Entity
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id"),
    @NamedQuery(name = "Staff.findByName", query = "SELECT s FROM Staff s WHERE s.name = :name"),
    @NamedQuery(name = "Staff.findByJobType", query = "SELECT s FROM Staff s WHERE s.jobType = :jobType")})
public class Staff implements Serializable,SettingEntity {
    @OneToMany(mappedBy = "worker3")
    private Collection<Entry> entryCollection;
    @OneToMany(mappedBy = "worker1")
    private Collection<Entry> entryCollection1;
    @OneToMany(mappedBy = "worker2")
    private Collection<Entry> entryCollection2;
    @OneToMany(mappedBy = "technician3")
    private Collection<Entry> entryCollection3;
    @OneToMany(mappedBy = "supervisor3")
    private Collection<Entry> entryCollection4;
    @OneToMany(mappedBy = "technician2")
    private Collection<Entry> entryCollection5;
    @OneToMany(mappedBy = "supervisor2")
    private Collection<Entry> entryCollection6;
    @OneToMany(mappedBy = "technician1")
    private Collection<Entry> entryCollection7;
    @OneToMany(mappedBy = "supervisor1")
    private Collection<Entry> entryCollection8;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "JobType")
    private String jobType;

    public Staff() {
    }

    public Staff(Integer id) {
        this.id = id;
    }

    public Staff(Integer id, String name, String jobType) {
        this.id = id;
        this.name = name;
        this.jobType = jobType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Staff[ id=" + id + " ]";
    }

    @Override
    public void setDefaultValue() {
        this.jobType="PROCESS WORKER";
        this.name="Name";
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(Collection<Entry> entryCollection) {
        this.entryCollection = entryCollection;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection1() {
        return entryCollection1;
    }

    public void setEntryCollection1(Collection<Entry> entryCollection1) {
        this.entryCollection1 = entryCollection1;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection2() {
        return entryCollection2;
    }

    public void setEntryCollection2(Collection<Entry> entryCollection2) {
        this.entryCollection2 = entryCollection2;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection3() {
        return entryCollection3;
    }

    public void setEntryCollection3(Collection<Entry> entryCollection3) {
        this.entryCollection3 = entryCollection3;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection4() {
        return entryCollection4;
    }

    public void setEntryCollection4(Collection<Entry> entryCollection4) {
        this.entryCollection4 = entryCollection4;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection5() {
        return entryCollection5;
    }

    public void setEntryCollection5(Collection<Entry> entryCollection5) {
        this.entryCollection5 = entryCollection5;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection6() {
        return entryCollection6;
    }

    public void setEntryCollection6(Collection<Entry> entryCollection6) {
        this.entryCollection6 = entryCollection6;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection7() {
        return entryCollection7;
    }

    public void setEntryCollection7(Collection<Entry> entryCollection7) {
        this.entryCollection7 = entryCollection7;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection8() {
        return entryCollection8;
    }

    public void setEntryCollection8(Collection<Entry> entryCollection8) {
        this.entryCollection8 = entryCollection8;
    }
    
}
