/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "check")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Check.findAll", query = "SELECT c FROM Check c"),
    @NamedQuery(name = "Check.findById", query = "SELECT c FROM Check c WHERE c.id = :id"),
    @NamedQuery(name = "Check.findByDescription", query = "SELECT c FROM Check c WHERE c.description = :description"),
    @NamedQuery(name = "Check.findByIsDefault", query = "SELECT c FROM Check c WHERE c.isDefault = :isDefault")})
public class Check implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "IsDefault")
    private boolean isDefault;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "check1")
    private Collection<Productcheck> productcheckCollection;

    public Check() {
    }

    public Check(Integer id) {
        this.id = id;
    }

    public Check(Integer id, String description, boolean isDefault) {
        this.id = id;
        this.description = description;
        this.isDefault = isDefault;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @XmlTransient
    public Collection<Productcheck> getProductcheckCollection() {
        return productcheckCollection;
    }

    public void setProductcheckCollection(Collection<Productcheck> productcheckCollection) {
        this.productcheckCollection = productcheckCollection;
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
        if (!(object instanceof Check)) {
            return false;
        }
        Check other = (Check) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Check[ id=" + id + " ]";
    }
    
}
