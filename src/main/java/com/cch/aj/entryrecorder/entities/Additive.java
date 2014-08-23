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
@Table(name = "additive")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Additive.findAll", query = "SELECT a FROM Additive a"),
    @NamedQuery(name = "Additive.findById", query = "SELECT a FROM Additive a WHERE a.id = :id"),
    @NamedQuery(name = "Additive.findByCompany", query = "SELECT a FROM Additive a WHERE a.company = :company"),
    @NamedQuery(name = "Additive.findByGrade", query = "SELECT a FROM Additive a WHERE a.grade = :grade"),
    @NamedQuery(name = "Additive.findByDescription", query = "SELECT a FROM Additive a WHERE a.description = :description")})
public class Additive implements Serializable,SettingEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "additiveCId")
    private Collection<Entry> entryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "additiveAId")
    private Collection<Entry> entryCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "additiveBId")
    private Collection<Entry> entryCollection2;
    @OneToMany(mappedBy = "additiveAId")
    private Collection<Product> productCollection;
    @OneToMany(mappedBy = "additiveBId")
    private Collection<Product> productCollection1;
    @OneToMany(mappedBy = "additiveCId")
    private Collection<Product> productCollection2;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Company")
    private String company;
    @Column(name = "Grade")
    private String grade;
    @Column(name = "Description")
    private String description;

    public Additive() {
    }

    public Additive(Integer id) {
        this.id = id;
    }

    public Additive(Integer id, String company) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Additive)) {
            return false;
        }
        Additive other = (Additive) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Additive[ id=" + id + " ]";
    }

    @Override
    public void setDefaultValue() {
        this.company="Company Name";
        this.grade="Grade";
        this.description="Colour";
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<Product> getProductCollection1() {
        return productCollection1;
    }

    public void setProductCollection1(Collection<Product> productCollection1) {
        this.productCollection1 = productCollection1;
    }

    @XmlTransient
    public Collection<Product> getProductCollection2() {
        return productCollection2;
    }

    public void setProductCollection2(Collection<Product> productCollection2) {
        this.productCollection2 = productCollection2;
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
    
}
