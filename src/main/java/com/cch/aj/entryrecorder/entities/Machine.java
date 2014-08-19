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
@Table(name = "machine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machine.findAll", query = "SELECT m FROM Machine m"),
    @NamedQuery(name = "Machine.findById", query = "SELECT m FROM Machine m WHERE m.id = :id"),
    @NamedQuery(name = "Machine.findByMachineNo", query = "SELECT m FROM Machine m WHERE m.machineNo = :machineNo"),
    @NamedQuery(name = "Machine.findByDescription", query = "SELECT m FROM Machine m WHERE m.description = :description"),
    @NamedQuery(name = "Machine.findByYear", query = "SELECT m FROM Machine m WHERE m.year = :year"),
    @NamedQuery(name = "Machine.findBySerialNo", query = "SELECT m FROM Machine m WHERE m.serialNo = :serialNo"),
    @NamedQuery(name = "Machine.findByCapacity", query = "SELECT m FROM Machine m WHERE m.capacity = :capacity"),
    @NamedQuery(name = "Machine.findByManufacturer", query = "SELECT m FROM Machine m WHERE m.manufacturer = :manufacturer")})
public class Machine implements Serializable,SettingEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "MachineNo")
    private String machineNo;
    @Column(name = "Description")
    private String description;
    @Column(name = "Year")
    private String year;
    @Column(name = "SerialNo")
    private String serialNo;
    @Column(name = "Capacity")
    private String capacity;
    @Column(name = "Manufacturer")
    private String manufacturer;
    @OneToMany(mappedBy = "machineId")
    private Collection<Entry> entryCollection;

    public Machine() {
    }

    public Machine(Integer id) {
        this.id = id;
    }

    public Machine(Integer id, String machineNo) {
        this.id = id;
        this.machineNo = machineNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(Collection<Entry> entryCollection) {
        this.entryCollection = entryCollection;
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
        if (!(object instanceof Machine)) {
            return false;
        }
        Machine other = (Machine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Machine[ id=" + id + " ]";
    }

    @Override
    public void setDefaultValue() {
        this.machineNo="Machine No";
    }
    
}
