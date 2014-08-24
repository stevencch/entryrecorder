/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chacao
 */
@Entity
@Table(name = "productcheck")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productcheck.findAll", query = "SELECT p FROM Productcheck p"),
    @NamedQuery(name = "Productcheck.findByProduct", query = "SELECT p FROM Productcheck p WHERE p.productcheckPK.product = :product"),
    @NamedQuery(name = "Productcheck.findByCheck", query = "SELECT p FROM Productcheck p WHERE p.productcheckPK.check = :check"),
    @NamedQuery(name = "Productcheck.findByOrder", query = "SELECT p FROM Productcheck p WHERE p.order = :order")})
public class Productcheck implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductcheckPK productcheckPK;
    @Basic(optional = false)
    @Column(name = "Order")
    private int order;
    @JoinColumn(name = "Check", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Check check1;
    @JoinColumn(name = "Product", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product1;

    public Productcheck() {
    }

    public Productcheck(ProductcheckPK productcheckPK) {
        this.productcheckPK = productcheckPK;
    }

    public Productcheck(ProductcheckPK productcheckPK, int order) {
        this.productcheckPK = productcheckPK;
        this.order = order;
    }

    public Productcheck(int product, int check) {
        this.productcheckPK = new ProductcheckPK(product, check);
    }

    public ProductcheckPK getProductcheckPK() {
        return productcheckPK;
    }

    public void setProductcheckPK(ProductcheckPK productcheckPK) {
        this.productcheckPK = productcheckPK;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Check getCheck1() {
        return check1;
    }

    public void setCheck1(Check check1) {
        this.check1 = check1;
    }

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcheckPK != null ? productcheckPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productcheck)) {
            return false;
        }
        Productcheck other = (Productcheck) object;
        if ((this.productcheckPK == null && other.productcheckPK != null) || (this.productcheckPK != null && !this.productcheckPK.equals(other.productcheckPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Productcheck[ productcheckPK=" + productcheckPK + " ]";
    }
    
}
