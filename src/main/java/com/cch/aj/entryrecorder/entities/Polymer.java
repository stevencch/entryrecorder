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
import javax.persistence.CascadeType;
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
@Table(name = "polymer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polymer.findAll", query = "SELECT p FROM Polymer p"),
    @NamedQuery(name = "Polymer.findById", query = "SELECT p FROM Polymer p WHERE p.id = :id"),
    @NamedQuery(name = "Polymer.findByCompany", query = "SELECT p FROM Polymer p WHERE p.company = :company"),
    @NamedQuery(name = "Polymer.findByGrade", query = "SELECT p FROM Polymer p WHERE p.grade = :grade"),
    @NamedQuery(name = "Polymer.findByDescription", query = "SELECT p FROM Polymer p WHERE p.description = :description")})
public class Polymer implements Serializable,SettingEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polymerId")
    private Collection<Entry> entryCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Company")
    private String company;
    @Column(name = "Grade")
    private String grade;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "polymerId")
    private Collection<Product> productCollection;

    public Polymer() {
    }

    public Polymer(Integer id) {
        this.id = id;
    }

    public Polymer(Integer id, String company) {
        this.id = id;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
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
        if (!(object instanceof Polymer)) {
            return false;
        }
        Polymer other = (Polymer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Polymer[ id=" + id + " ]";
    }

    @Override
    public void setDefaultValue() {
        this.company="Company Name";
        this.grade="Grade";
        this.description="Colour";
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(Collection<Entry> entryCollection) {
        this.entryCollection = entryCollection;
    }
    
}
