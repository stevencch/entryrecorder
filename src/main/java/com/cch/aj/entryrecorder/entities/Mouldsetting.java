/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "mouldsetting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouldsetting.findAll", query = "SELECT m FROM Mouldsetting m"),
    @NamedQuery(name = "Mouldsetting.findById", query = "SELECT m FROM Mouldsetting m WHERE m.id = :id"),
    @NamedQuery(name = "Mouldsetting.findByKey", query = "SELECT m FROM Mouldsetting m WHERE m.key = :key"),
    @NamedQuery(name = "Mouldsetting.findByDescription", query = "SELECT m FROM Mouldsetting m WHERE m.description = :description"),
    @NamedQuery(name = "Mouldsetting.findByMin", query = "SELECT m FROM Mouldsetting m WHERE m.min = :min"),
    @NamedQuery(name = "Mouldsetting.findByMax", query = "SELECT m FROM Mouldsetting m WHERE m.max = :max"),
    @NamedQuery(name = "Mouldsetting.findByMouldId", query = "SELECT m FROM Mouldsetting m WHERE m.mouldId = :mouldId")})
public class Mouldsetting implements Serializable {
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
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Min")
    private float min;
    @Basic(optional = false)
    @Column(name = "Max")
    private float max;
    @Basic(optional = false)
    @Column(name = "MouldId")
    private int mouldId;

    public Mouldsetting() {
    }

    public Mouldsetting(Integer id) {
        this.id = id;
    }

    public Mouldsetting(Integer id, String key, String description, float min, float max, int mouldId) {
        this.id = id;
        this.key = key;
        this.description = description;
        this.min = min;
        this.max = max;
        this.mouldId = mouldId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public int getMouldId() {
        return mouldId;
    }

    public void setMouldId(int mouldId) {
        this.mouldId = mouldId;
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
        if (!(object instanceof Mouldsetting)) {
            return false;
        }
        Mouldsetting other = (Mouldsetting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Mouldsetting[ id=" + id + " ]";
    }
    
}
