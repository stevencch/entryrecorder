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
@Table(name = "mould")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mould.findAll", query = "SELECT m FROM Mould m"),
    @NamedQuery(name = "Mould.findById", query = "SELECT m FROM Mould m WHERE m.id = :id"),
    @NamedQuery(name = "Mould.findByName", query = "SELECT m FROM Mould m WHERE m.name = :name"),
    @NamedQuery(name = "Mould.findByCode", query = "SELECT m FROM Mould m WHERE m.code = :code"),
    @NamedQuery(name = "Mould.findByVolumn", query = "SELECT m FROM Mould m WHERE m.volumn = :volumn"),
    @NamedQuery(name = "Mould.findByManufacturer", query = "SELECT m FROM Mould m WHERE m.manufacturer = :manufacturer"),
    @NamedQuery(name = "Mould.findByYear", query = "SELECT m FROM Mould m WHERE m.year = :year"),
    @NamedQuery(name = "Mould.findByImageDrawing", query = "SELECT m FROM Mould m WHERE m.imageDrawing = :imageDrawing"),
    @NamedQuery(name = "Mould.findByImageNonDg", query = "SELECT m FROM Mould m WHERE m.imageNonDg = :imageNonDg"),
    @NamedQuery(name = "Mould.findByImageDg", query = "SELECT m FROM Mould m WHERE m.imageDg = :imageDg"),
    @NamedQuery(name = "Mould.findByImageBoreA", query = "SELECT m FROM Mould m WHERE m.imageBoreA = :imageBoreA"),
    @NamedQuery(name = "Mould.findByImageBoreB", query = "SELECT m FROM Mould m WHERE m.imageBoreB = :imageBoreB"),
    @NamedQuery(name = "Mould.findByImageNeck", query = "SELECT m FROM Mould m WHERE m.imageNeck = :imageNeck"),
    @NamedQuery(name = "Mould.findByImageTap", query = "SELECT m FROM Mould m WHERE m.imageTap = :imageTap")})
public class Mould implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "Volumn")
    private String volumn;
    @Basic(optional = false)
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Basic(optional = false)
    @Column(name = "Year")
    private String year;
    @Basic(optional = false)
    @Column(name = "ImageDrawing")
    private String imageDrawing;
    @Basic(optional = false)
    @Column(name = "ImageNonDg")
    private String imageNonDg;
    @Basic(optional = false)
    @Column(name = "ImageDg")
    private String imageDg;
    @Basic(optional = false)
    @Column(name = "ImageBoreA")
    private String imageBoreA;
    @Basic(optional = false)
    @Column(name = "ImageBoreB")
    private String imageBoreB;
    @Basic(optional = false)
    @Column(name = "ImageNeck")
    private String imageNeck;
    @Basic(optional = false)
    @Column(name = "ImageTap")
    private String imageTap;

    public Mould() {
    }

    public Mould(Integer id) {
        this.id = id;
    }

    public Mould(Integer id, String name, String code, String volumn, String manufacturer, String year, String imageDrawing, String imageNonDg, String imageDg, String imageBoreA, String imageBoreB, String imageNeck, String imageTap) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.volumn = volumn;
        this.manufacturer = manufacturer;
        this.year = year;
        this.imageDrawing = imageDrawing;
        this.imageNonDg = imageNonDg;
        this.imageDg = imageDg;
        this.imageBoreA = imageBoreA;
        this.imageBoreB = imageBoreB;
        this.imageNeck = imageNeck;
        this.imageTap = imageTap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVolumn() {
        return volumn;
    }

    public void setVolumn(String volumn) {
        this.volumn = volumn;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageDrawing() {
        return imageDrawing;
    }

    public void setImageDrawing(String imageDrawing) {
        this.imageDrawing = imageDrawing;
    }

    public String getImageNonDg() {
        return imageNonDg;
    }

    public void setImageNonDg(String imageNonDg) {
        this.imageNonDg = imageNonDg;
    }

    public String getImageDg() {
        return imageDg;
    }

    public void setImageDg(String imageDg) {
        this.imageDg = imageDg;
    }

    public String getImageBoreA() {
        return imageBoreA;
    }

    public void setImageBoreA(String imageBoreA) {
        this.imageBoreA = imageBoreA;
    }

    public String getImageBoreB() {
        return imageBoreB;
    }

    public void setImageBoreB(String imageBoreB) {
        this.imageBoreB = imageBoreB;
    }

    public String getImageNeck() {
        return imageNeck;
    }

    public void setImageNeck(String imageNeck) {
        this.imageNeck = imageNeck;
    }

    public String getImageTap() {
        return imageTap;
    }

    public void setImageTap(String imageTap) {
        this.imageTap = imageTap;
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
        if (!(object instanceof Mould)) {
            return false;
        }
        Mould other = (Mould) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cch.aj.entryrecorder.entities.Mould[ id=" + id + " ]";
    }
    
}
