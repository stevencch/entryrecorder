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
    @NamedQuery(name = "Entry.findByCreateDate", query = "SELECT e FROM Entry e WHERE e.createDate = :createDate")})
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Shift")
    private String shift;
    @Basic(optional = false)
    @Column(name = "MachineId")
    private int machineId;
    @Basic(optional = false)
    @Column(name = "MouldId")
    private int mouldId;
    @Basic(optional = false)
    @Column(name = "ProductId")
    private int productId;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Entry() {
    }

    public Entry(Integer id) {
        this.id = id;
    }

    public Entry(Integer id, String shift, int machineId, int mouldId, int productId, Date createDate) {
        this.id = id;
        this.shift = shift;
        this.machineId = machineId;
        this.mouldId = mouldId;
        this.productId = productId;
        this.createDate = createDate;
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

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMouldId() {
        return mouldId;
    }

    public void setMouldId(int mouldId) {
        this.mouldId = mouldId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    
}
