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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByWeightMin", query = "SELECT p FROM Product p WHERE p.weightMin = :weightMin"),
    @NamedQuery(name = "Product.findByWeightMax", query = "SELECT p FROM Product p WHERE p.weightMax = :weightMax"),
    @NamedQuery(name = "Product.findByBung", query = "SELECT p FROM Product p WHERE p.bung = :bung"),
    @NamedQuery(name = "Product.findByPierced", query = "SELECT p FROM Product p WHERE p.pierced = :pierced"),
    @NamedQuery(name = "Product.findByAdditiveAPercentage", query = "SELECT p FROM Product p WHERE p.additiveAPercentage = :additiveAPercentage"),
    @NamedQuery(name = "Product.findByAdditiveBPercentage", query = "SELECT p FROM Product p WHERE p.additiveBPercentage = :additiveBPercentage"),
    @NamedQuery(name = "Product.findByAdditiveCPercentage", query = "SELECT p FROM Product p WHERE p.additiveCPercentage = :additiveCPercentage"),
    @NamedQuery(name = "Product.findByThreadBoreA", query = "SELECT p FROM Product p WHERE p.threadBoreA = :threadBoreA"),
    @NamedQuery(name = "Product.findByThreadBoreB", query = "SELECT p FROM Product p WHERE p.threadBoreB = :threadBoreB"),
    @NamedQuery(name = "Product.findByThreadNeck", query = "SELECT p FROM Product p WHERE p.threadNeck = :threadNeck"),
    @NamedQuery(name = "Product.findByDgnondg", query = "SELECT p FROM Product p WHERE p.dgnondg = :dgnondg")})
public class Product implements Serializable,SettingEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product1")
    private Collection<Productcheck> productcheckCollection;
    @OneToMany(mappedBy = "productId")
    private Collection<Entry> entryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<Check> checkCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WeightMin")
    private Float weightMin;
    @Column(name = "WeightMax")
    private Float weightMax;
    @Column(name = "Bung")
    private String bung;
    @Column(name = "Pierced")
    private String pierced;
    @Column(name = "AdditiveAPercentage")
    private String additiveAPercentage;
    @Column(name = "AdditiveBPercentage")
    private String additiveBPercentage;
    @Column(name = "AdditiveCPercentage")
    private String additiveCPercentage;
    @Column(name = "ThreadBoreA")
    private Integer threadBoreA;
    @Column(name = "ThreadBoreB")
    private Integer threadBoreB;
    @Column(name = "ThreadNeck")
    private Integer threadNeck;
    @Column(name = "DGNONDG")
    private Integer dgnondg;
    @JoinColumn(name = "MouldId", referencedColumnName = "Id")
    @ManyToOne
    private Mould mouldId;
    @JoinColumn(name = "PolymerId", referencedColumnName = "Id")
    @ManyToOne
    private Polymer polymerId;
    @JoinColumn(name = "AdditiveAId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveAId;
    @JoinColumn(name = "AdditiveBId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveBId;
    @JoinColumn(name = "AdditiveCId", referencedColumnName = "Id")
    @ManyToOne
    private Additive additiveCId;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String code, int threadBoreB) {
        this.id = id;
        this.code = code;
        this.threadBoreB = threadBoreB;
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

    public Float getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(Float weightMin) {
        this.weightMin = weightMin;
    }

    public Float getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(Float weightMax) {
        this.weightMax = weightMax;
    }

    public String getBung() {
        return bung;
    }

    public void setBung(String bung) {
        this.bung = bung;
    }

    public String getPierced() {
        return pierced;
    }

    public void setPierced(String pierced) {
        this.pierced = pierced;
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

    public Integer getThreadBoreA() {
        return threadBoreA;
    }

    public void setThreadBoreA(Integer threadBoreA) {
        this.threadBoreA = threadBoreA;
    }

    public Integer getThreadBoreB() {
        return threadBoreB;
    }

    public void setThreadBoreB(Integer threadBoreB) {
        this.threadBoreB = threadBoreB;
    }

    public Integer getThreadNeck() {
        return threadNeck;
    }

    public void setThreadNeck(Integer threadNeck) {
        this.threadNeck = threadNeck;
    }

    public Integer getDgnondg() {
        return dgnondg;
    }

    public void setDgnondg(Integer dgnondg) {
        this.dgnondg = dgnondg;
    }

    public Mould getMouldId() {
        return mouldId;
    }

    public void setMouldId(Mould mouldId) {
        this.mouldId = mouldId;
    }

    public Polymer getPolymerId() {
        return polymerId;
    }

    public void setPolymerId(Polymer polymerId) {
        this.polymerId = polymerId;
    }

    public Additive getAdditiveAId() {
        return additiveAId;
    }

    public void setAdditiveAId(Additive additiveAId) {
        this.additiveAId = additiveAId;
    }

    public Additive getAdditiveBId() {
        return additiveBId;
    }

    public void setAdditiveBId(Additive additiveBId) {
        this.additiveBId = additiveBId;
    }

    public Additive getAdditiveCId() {
        return additiveCId;
    }

    public void setAdditiveCId(Additive additiveCId) {
        this.additiveCId = additiveCId;
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
    
    @Override
    public void setDefaultValue() {
        this.code="Product Code";
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(Collection<Entry> entryCollection) {
        this.entryCollection = entryCollection;
    }

    @XmlTransient
    public Collection<Check> getCheckCollection() {
        return checkCollection;
    }

    public void setCheckCollection(Collection<Check> checkCollection) {
        this.checkCollection = checkCollection;
    }

    @XmlTransient
    public Collection<Productcheck> getProductcheckCollection() {
        return productcheckCollection;
    }

    public void setProductcheckCollection(Collection<Productcheck> productcheckCollection) {
        this.productcheckCollection = productcheckCollection;
    }
}
