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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByMouldId", query = "SELECT p FROM Product p WHERE p.mouldId = :mouldId"),
    @NamedQuery(name = "Product.findByWeightMin", query = "SELECT p FROM Product p WHERE p.weightMin = :weightMin"),
    @NamedQuery(name = "Product.findByWeightMax", query = "SELECT p FROM Product p WHERE p.weightMax = :weightMax"),
    @NamedQuery(name = "Product.findByThreadId", query = "SELECT p FROM Product p WHERE p.threadId = :threadId"),
    @NamedQuery(name = "Product.findByBung", query = "SELECT p FROM Product p WHERE p.bung = :bung"),
    @NamedQuery(name = "Product.findByPierced", query = "SELECT p FROM Product p WHERE p.pierced = :pierced"),
    @NamedQuery(name = "Product.findByPolymerId", query = "SELECT p FROM Product p WHERE p.polymerId = :polymerId"),
    @NamedQuery(name = "Product.findByAdditiveAId", query = "SELECT p FROM Product p WHERE p.additiveAId = :additiveAId"),
    @NamedQuery(name = "Product.findByAdditiveBId", query = "SELECT p FROM Product p WHERE p.additiveBId = :additiveBId"),
    @NamedQuery(name = "Product.findByAdditiveCId", query = "SELECT p FROM Product p WHERE p.additiveCId = :additiveCId"),
    @NamedQuery(name = "Product.findByAdditiveAPercentage", query = "SELECT p FROM Product p WHERE p.additiveAPercentage = :additiveAPercentage"),
    @NamedQuery(name = "Product.findByAdditiveBPercentage", query = "SELECT p FROM Product p WHERE p.additiveBPercentage = :additiveBPercentage"),
    @NamedQuery(name = "Product.findByAdditiveCPercentage", query = "SELECT p FROM Product p WHERE p.additiveCPercentage = :additiveCPercentage")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "MouldId")
    private int mouldId;
    @Basic(optional = false)
    @Column(name = "WeightMin")
    private float weightMin;
    @Basic(optional = false)
    @Column(name = "WeightMax")
    private float weightMax;
    @Basic(optional = false)
    @Column(name = "ThreadId")
    private int threadId;
    @Basic(optional = false)
    @Column(name = "Bung")
    private boolean bung;
    @Basic(optional = false)
    @Column(name = "Pierced")
    private boolean pierced;
    @Basic(optional = false)
    @Column(name = "PolymerId")
    private int polymerId;
    @Basic(optional = false)
    @Column(name = "AdditiveAId")
    private int additiveAId;
    @Basic(optional = false)
    @Column(name = "AdditiveBId")
    private int additiveBId;
    @Basic(optional = false)
    @Column(name = "AdditiveCId")
    private int additiveCId;
    @Basic(optional = false)
    @Column(name = "AdditiveAPercentage")
    private String additiveAPercentage;
    @Basic(optional = false)
    @Column(name = "AdditiveBPercentage")
    private String additiveBPercentage;
    @Basic(optional = false)
    @Column(name = "AdditiveCPercentage")
    private String additiveCPercentage;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String code, String description, int mouldId, float weightMin, float weightMax, int threadId, boolean bung, boolean pierced, int polymerId, int additiveAId, int additiveBId, int additiveCId, String additiveAPercentage, String additiveBPercentage, String additiveCPercentage) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.mouldId = mouldId;
        this.weightMin = weightMin;
        this.weightMax = weightMax;
        this.threadId = threadId;
        this.bung = bung;
        this.pierced = pierced;
        this.polymerId = polymerId;
        this.additiveAId = additiveAId;
        this.additiveBId = additiveBId;
        this.additiveCId = additiveCId;
        this.additiveAPercentage = additiveAPercentage;
        this.additiveBPercentage = additiveBPercentage;
        this.additiveCPercentage = additiveCPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMouldId() {
        return mouldId;
    }

    public void setMouldId(int mouldId) {
        this.mouldId = mouldId;
    }

    public float getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(float weightMin) {
        this.weightMin = weightMin;
    }

    public float getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(float weightMax) {
        this.weightMax = weightMax;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public boolean getBung() {
        return bung;
    }

    public void setBung(boolean bung) {
        this.bung = bung;
    }

    public boolean getPierced() {
        return pierced;
    }

    public void setPierced(boolean pierced) {
        this.pierced = pierced;
    }

    public int getPolymerId() {
        return polymerId;
    }

    public void setPolymerId(int polymerId) {
        this.polymerId = polymerId;
    }

    public int getAdditiveAId() {
        return additiveAId;
    }

    public void setAdditiveAId(int additiveAId) {
        this.additiveAId = additiveAId;
    }

    public int getAdditiveBId() {
        return additiveBId;
    }

    public void setAdditiveBId(int additiveBId) {
        this.additiveBId = additiveBId;
    }

    public int getAdditiveCId() {
        return additiveCId;
    }

    public void setAdditiveCId(int additiveCId) {
        this.additiveCId = additiveCId;
    }

    public String getAdditiveAPercentage() {
        return additiveAPercentage;
    }

    public void setAdditiveAPercentage(String additiveAPercentage) {
        this.additiveAPercentage = additiveAPercentage;
    }

    public String getAdditiveBPercentage() {
        return additiveBPercentage;
    }

    public void setAdditiveBPercentage(String additiveBPercentage) {
        this.additiveBPercentage = additiveBPercentage;
    }

    public String getAdditiveCPercentage() {
        return additiveCPercentage;
    }

    public void setAdditiveCPercentage(String additiveCPercentage) {
        this.additiveCPercentage = additiveCPercentage;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Product[ id=" + id + " ]";
    }
    
}
