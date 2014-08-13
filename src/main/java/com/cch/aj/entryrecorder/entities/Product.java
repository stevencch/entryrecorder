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
    @NamedQuery(name = "Product.findByAdditiveCPercentage", query = "SELECT p FROM Product p WHERE p.additiveCPercentage = :additiveCPercentage"),
    @NamedQuery(name = "Product.findByImage1", query = "SELECT p FROM Product p WHERE p.image1 = :image1"),
    @NamedQuery(name = "Product.findByImage2", query = "SELECT p FROM Product p WHERE p.image2 = :image2"),
    @NamedQuery(name = "Product.findByImage3", query = "SELECT p FROM Product p WHERE p.image3 = :image3")})
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
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "MouldId")
    private int mouldId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WeightMin")
    private Float weightMin;
    @Column(name = "WeightMax")
    private Float weightMax;
    @Column(name = "ThreadId")
    private Integer threadId;
    @Column(name = "Bung")
    private Boolean bung;
    @Column(name = "Pierced")
    private Boolean pierced;
    @Column(name = "PolymerId")
    private Integer polymerId;
    @Column(name = "AdditiveAId")
    private Integer additiveAId;
    @Column(name = "AdditiveBId")
    private Integer additiveBId;
    @Column(name = "AdditiveCId")
    private Integer additiveCId;
    @Column(name = "AdditiveAPercentage")
    private String additiveAPercentage;
    @Column(name = "AdditiveBPercentage")
    private String additiveBPercentage;
    @Column(name = "AdditiveCPercentage")
    private String additiveCPercentage;
    @Column(name = "Image1")
    private String image1;
    @Column(name = "Image2")
    private String image2;
    @Column(name = "Image3")
    private String image3;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String code, int mouldId) {
        this.id = id;
        this.code = code;
        this.mouldId = mouldId;
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

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Boolean getBung() {
        return bung;
    }

    public void setBung(Boolean bung) {
        this.bung = bung;
    }

    public Boolean getPierced() {
        return pierced;
    }

    public void setPierced(Boolean pierced) {
        this.pierced = pierced;
    }

    public Integer getPolymerId() {
        return polymerId;
    }

    public void setPolymerId(Integer polymerId) {
        this.polymerId = polymerId;
    }

    public Integer getAdditiveAId() {
        return additiveAId;
    }

    public void setAdditiveAId(Integer additiveAId) {
        this.additiveAId = additiveAId;
    }

    public Integer getAdditiveBId() {
        return additiveBId;
    }

    public void setAdditiveBId(Integer additiveBId) {
        this.additiveBId = additiveBId;
    }

    public Integer getAdditiveCId() {
        return additiveCId;
    }

    public void setAdditiveCId(Integer additiveCId) {
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
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
