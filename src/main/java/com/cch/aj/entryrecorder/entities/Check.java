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
import javax.persistence.Id;
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
@Table(name = "check")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Check.findAll", query = "SELECT c FROM Check c"),
    @NamedQuery(name = "Check.findById", query = "SELECT c FROM Check c WHERE c.id = :id"),
    @NamedQuery(name = "Check.findByItemOrder", query = "SELECT c FROM Check c WHERE c.itemOrder = :itemOrder"),
    @NamedQuery(name = "Check.findByDescription", query = "SELECT c FROM Check c WHERE c.description = :description")})
public class Check implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ItemOrder")
    private int itemOrder;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Product", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Product product;

    public Check() {
    }

    public Check(Integer id) {
        this.id = id;
    }

    public Check(Integer id, int itemOrder, String description) {
        this.id = id;
        this.itemOrder = itemOrder;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
