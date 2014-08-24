/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author chacao
 */
@Embeddable
public class ProductcheckPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Product")
    private int product;
    @Basic(optional = false)
    @Column(name = "Check")
    private int check;

    public ProductcheckPK() {
    }

    public ProductcheckPK(int product, int check) {
        this.product = product;
        this.check = check;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) product;
        hash += (int) check;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductcheckPK)) {
            return false;
        }
        ProductcheckPK other = (ProductcheckPK) object;
        if (this.product != other.product) {
            return false;
        }
        if (this.check != other.check) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.ProductcheckPK[ product=" + product + ", check=" + check + " ]";
    }
    
}
