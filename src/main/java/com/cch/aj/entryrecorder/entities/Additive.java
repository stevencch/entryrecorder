/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import com.cch.aj.entryrecorder.common.SettingEntity;
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
public class Additive implements Serializable, SettingEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
        this.company="Company A";
        this.grade="AAA";
    }
}
