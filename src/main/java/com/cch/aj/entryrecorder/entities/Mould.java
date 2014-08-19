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
    @NamedQuery(name = "Mould.findByImageTap", query = "SELECT m FROM Mould m WHERE m.imageTap = :imageTap"),
    @NamedQuery(name = "Mould.findByWeightNonDgMin", query = "SELECT m FROM Mould m WHERE m.weightNonDgMin = :weightNonDgMin"),
    @NamedQuery(name = "Mould.findByWeightNonDgMax", query = "SELECT m FROM Mould m WHERE m.weightNonDgMax = :weightNonDgMax"),
    @NamedQuery(name = "Mould.findByWeightDgMin", query = "SELECT m FROM Mould m WHERE m.weightDgMin = :weightDgMin"),
    @NamedQuery(name = "Mould.findByWeightDgMax", query = "SELECT m FROM Mould m WHERE m.weightDgMax = :weightDgMax"),
    @NamedQuery(name = "Mould.findByTapPositionMin", query = "SELECT m FROM Mould m WHERE m.tapPositionMin = :tapPositionMin"),
    @NamedQuery(name = "Mould.findByTapPositionMax", query = "SELECT m FROM Mould m WHERE m.tapPositionMax = :tapPositionMax"),
    @NamedQuery(name = "Mould.findByWallNonDgUnderHandleMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgUnderHandleMin = :wallNonDgUnderHandleMin"),
    @NamedQuery(name = "Mould.findByWallNonDgUnderHandleMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgUnderHandleMax = :wallNonDgUnderHandleMax"),
    @NamedQuery(name = "Mould.findByWallNonDgBaseMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgBaseMin = :wallNonDgBaseMin"),
    @NamedQuery(name = "Mould.findByWallNonDgBaseMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgBaseMax = :wallNonDgBaseMax"),
    @NamedQuery(name = "Mould.findByWallNonDgClosureMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgClosureMin = :wallNonDgClosureMin"),
    @NamedQuery(name = "Mould.findByWallNonDgClosureMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgClosureMax = :wallNonDgClosureMax"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleBungMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleBungMin = :wallNonDgHandleBungMin"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleBungMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleBungMax = :wallNonDgHandleBungMax"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleLeftMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleLeftMin = :wallNonDgHandleLeftMin"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleLeftMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleLeftMax = :wallNonDgHandleLeftMax"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleRightMin", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleRightMin = :wallNonDgHandleRightMin"),
    @NamedQuery(name = "Mould.findByWallNonDgHandleRightMax", query = "SELECT m FROM Mould m WHERE m.wallNonDgHandleRightMax = :wallNonDgHandleRightMax"),
    @NamedQuery(name = "Mould.findByWallDgUnderHandleMin", query = "SELECT m FROM Mould m WHERE m.wallDgUnderHandleMin = :wallDgUnderHandleMin"),
    @NamedQuery(name = "Mould.findByWallDgUnderHandleMax", query = "SELECT m FROM Mould m WHERE m.wallDgUnderHandleMax = :wallDgUnderHandleMax"),
    @NamedQuery(name = "Mould.findByWallDgBaseMin", query = "SELECT m FROM Mould m WHERE m.wallDgBaseMin = :wallDgBaseMin"),
    @NamedQuery(name = "Mould.findByWallDgBaseMax", query = "SELECT m FROM Mould m WHERE m.wallDgBaseMax = :wallDgBaseMax"),
    @NamedQuery(name = "Mould.findByWallDgClosureMin", query = "SELECT m FROM Mould m WHERE m.wallDgClosureMin = :wallDgClosureMin"),
    @NamedQuery(name = "Mould.findByWallDgClosureMax", query = "SELECT m FROM Mould m WHERE m.wallDgClosureMax = :wallDgClosureMax"),
    @NamedQuery(name = "Mould.findByWallDgHandleBungMin", query = "SELECT m FROM Mould m WHERE m.wallDgHandleBungMin = :wallDgHandleBungMin"),
    @NamedQuery(name = "Mould.findByWallDgHandleBungMax", query = "SELECT m FROM Mould m WHERE m.wallDgHandleBungMax = :wallDgHandleBungMax"),
    @NamedQuery(name = "Mould.findByWallDgHandleLeftMin", query = "SELECT m FROM Mould m WHERE m.wallDgHandleLeftMin = :wallDgHandleLeftMin"),
    @NamedQuery(name = "Mould.findByWallDgHandleLeftMax", query = "SELECT m FROM Mould m WHERE m.wallDgHandleLeftMax = :wallDgHandleLeftMax"),
    @NamedQuery(name = "Mould.findByWallDgHandRightMin", query = "SELECT m FROM Mould m WHERE m.wallDgHandRightMin = :wallDgHandRightMin"),
    @NamedQuery(name = "Mould.findByWallDgHandleRightMax", query = "SELECT m FROM Mould m WHERE m.wallDgHandleRightMax = :wallDgHandleRightMax"),
    @NamedQuery(name = "Mould.findByThreadBoreASize1", query = "SELECT m FROM Mould m WHERE m.threadBoreASize1 = :threadBoreASize1"),
    @NamedQuery(name = "Mould.findByThreadBoreASize2", query = "SELECT m FROM Mould m WHERE m.threadBoreASize2 = :threadBoreASize2"),
    @NamedQuery(name = "Mould.findByThreadBoreASize3", query = "SELECT m FROM Mould m WHERE m.threadBoreASize3 = :threadBoreASize3"),
    @NamedQuery(name = "Mould.findByThreadBoreBSize1", query = "SELECT m FROM Mould m WHERE m.threadBoreBSize1 = :threadBoreBSize1"),
    @NamedQuery(name = "Mould.findByThreadBoreBSize2", query = "SELECT m FROM Mould m WHERE m.threadBoreBSize2 = :threadBoreBSize2"),
    @NamedQuery(name = "Mould.findByThreadBoreBSize3", query = "SELECT m FROM Mould m WHERE m.threadBoreBSize3 = :threadBoreBSize3"),
    @NamedQuery(name = "Mould.findByThreadBoreAMin1", query = "SELECT m FROM Mould m WHERE m.threadBoreAMin1 = :threadBoreAMin1"),
    @NamedQuery(name = "Mould.findByThreadBoreAMin2", query = "SELECT m FROM Mould m WHERE m.threadBoreAMin2 = :threadBoreAMin2"),
    @NamedQuery(name = "Mould.findByThreadBoreAMin3", query = "SELECT m FROM Mould m WHERE m.threadBoreAMin3 = :threadBoreAMin3"),
    @NamedQuery(name = "Mould.findByThreadBoreAMax1", query = "SELECT m FROM Mould m WHERE m.threadBoreAMax1 = :threadBoreAMax1"),
    @NamedQuery(name = "Mould.findByThreadBoreAMax2", query = "SELECT m FROM Mould m WHERE m.threadBoreAMax2 = :threadBoreAMax2"),
    @NamedQuery(name = "Mould.findByThreadBoreAMax3", query = "SELECT m FROM Mould m WHERE m.threadBoreAMax3 = :threadBoreAMax3"),
    @NamedQuery(name = "Mould.findByThreadBoreBMin1", query = "SELECT m FROM Mould m WHERE m.threadBoreBMin1 = :threadBoreBMin1"),
    @NamedQuery(name = "Mould.findByThreadBoreBMin2", query = "SELECT m FROM Mould m WHERE m.threadBoreBMin2 = :threadBoreBMin2"),
    @NamedQuery(name = "Mould.findByThreadBoreBMin3", query = "SELECT m FROM Mould m WHERE m.threadBoreBMin3 = :threadBoreBMin3"),
    @NamedQuery(name = "Mould.findByThreadBoreBMax1", query = "SELECT m FROM Mould m WHERE m.threadBoreBMax1 = :threadBoreBMax1"),
    @NamedQuery(name = "Mould.findByThreadBoreBMax2", query = "SELECT m FROM Mould m WHERE m.threadBoreBMax2 = :threadBoreBMax2"),
    @NamedQuery(name = "Mould.findByThreadBoreBMax3", query = "SELECT m FROM Mould m WHERE m.threadBoreBMax3 = :threadBoreBMax3"),
    @NamedQuery(name = "Mould.findByThreadNeckSize1", query = "SELECT m FROM Mould m WHERE m.threadNeckSize1 = :threadNeckSize1"),
    @NamedQuery(name = "Mould.findByThreadNeckSize2", query = "SELECT m FROM Mould m WHERE m.threadNeckSize2 = :threadNeckSize2"),
    @NamedQuery(name = "Mould.findByThreadNeckSize3", query = "SELECT m FROM Mould m WHERE m.threadNeckSize3 = :threadNeckSize3"),
    @NamedQuery(name = "Mould.findByThreadNeckMin1", query = "SELECT m FROM Mould m WHERE m.threadNeckMin1 = :threadNeckMin1"),
    @NamedQuery(name = "Mould.findByThreadNeckMin2", query = "SELECT m FROM Mould m WHERE m.threadNeckMin2 = :threadNeckMin2"),
    @NamedQuery(name = "Mould.findByThreadNeckMin3", query = "SELECT m FROM Mould m WHERE m.threadNeckMin3 = :threadNeckMin3"),
    @NamedQuery(name = "Mould.findByThreadNeckMax1", query = "SELECT m FROM Mould m WHERE m.threadNeckMax1 = :threadNeckMax1"),
    @NamedQuery(name = "Mould.findByThreadNeckMax2", query = "SELECT m FROM Mould m WHERE m.threadNeckMax2 = :threadNeckMax2"),
    @NamedQuery(name = "Mould.findByThreadNeckMax3", query = "SELECT m FROM Mould m WHERE m.threadNeckMax3 = :threadNeckMax3")})
public class Mould implements Serializable,SettingEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Column(name = "Volumn")
    private String volumn;
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Column(name = "Year")
    private String year;
    @Column(name = "ImageDrawing")
    private String imageDrawing;
    @Column(name = "ImageNonDg")
    private String imageNonDg;
    @Column(name = "ImageDg")
    private String imageDg;
    @Column(name = "ImageBoreA")
    private String imageBoreA;
    @Column(name = "ImageBoreB")
    private String imageBoreB;
    @Column(name = "ImageNeck")
    private String imageNeck;
    @Column(name = "ImageTap")
    private String imageTap;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WeightNonDgMin")
    private Float weightNonDgMin;
    @Column(name = "WeightNonDgMax")
    private Float weightNonDgMax;
    @Column(name = "WeightDgMin")
    private Float weightDgMin;
    @Column(name = "WeightDgMax")
    private Float weightDgMax;
    @Column(name = "TapPositionMin")
    private Float tapPositionMin;
    @Column(name = "TapPositionMax")
    private Float tapPositionMax;
    @Column(name = "WallNonDgUnderHandleMin")
    private Float wallNonDgUnderHandleMin;
    @Column(name = "WallNonDgUnderHandleMax")
    private Float wallNonDgUnderHandleMax;
    @Column(name = "WallNonDgBaseMin")
    private Float wallNonDgBaseMin;
    @Column(name = "WallNonDgBaseMax")
    private Float wallNonDgBaseMax;
    @Column(name = "WallNonDgClosureMin")
    private Float wallNonDgClosureMin;
    @Column(name = "WallNonDgClosureMax")
    private Float wallNonDgClosureMax;
    @Column(name = "WallNonDgHandleBungMin")
    private Float wallNonDgHandleBungMin;
    @Column(name = "WallNonDgHandleBungMax")
    private Float wallNonDgHandleBungMax;
    @Column(name = "WallNonDgHandleLeftMin")
    private Float wallNonDgHandleLeftMin;
    @Column(name = "WallNonDgHandleLeftMax")
    private Float wallNonDgHandleLeftMax;
    @Column(name = "WallNonDgHandleRightMin")
    private Float wallNonDgHandleRightMin;
    @Column(name = "WallNonDgHandleRightMax")
    private Float wallNonDgHandleRightMax;
    @Column(name = "WallDgUnderHandleMin")
    private Float wallDgUnderHandleMin;
    @Column(name = "WallDgUnderHandleMax")
    private Float wallDgUnderHandleMax;
    @Column(name = "WallDgBaseMin")
    private Float wallDgBaseMin;
    @Column(name = "WallDgBaseMax")
    private Float wallDgBaseMax;
    @Column(name = "WallDgClosureMin")
    private Float wallDgClosureMin;
    @Column(name = "WallDgClosureMax")
    private Float wallDgClosureMax;
    @Column(name = "WallDgHandleBungMin")
    private Float wallDgHandleBungMin;
    @Column(name = "WallDgHandleBungMax")
    private Float wallDgHandleBungMax;
    @Column(name = "WallDgHandleLeftMin")
    private Float wallDgHandleLeftMin;
    @Column(name = "WallDgHandleLeftMax")
    private Float wallDgHandleLeftMax;
    @Column(name = "WallDgHandRightMin")
    private Float wallDgHandRightMin;
    @Column(name = "WallDgHandleRightMax")
    private Float wallDgHandleRightMax;
    @Column(name = "ThreadBoreASize1")
    private String threadBoreASize1;
    @Column(name = "ThreadBoreASize2")
    private String threadBoreASize2;
    @Column(name = "ThreadBoreASize3")
    private String threadBoreASize3;
    @Column(name = "ThreadBoreBSize1")
    private String threadBoreBSize1;
    @Column(name = "ThreadBoreBSize2")
    private String threadBoreBSize2;
    @Column(name = "ThreadBoreBSize3")
    private String threadBoreBSize3;
    @Column(name = "ThreadBoreAMin1")
    private Float threadBoreAMin1;
    @Column(name = "ThreadBoreAMin2")
    private Float threadBoreAMin2;
    @Column(name = "ThreadBoreAMin3")
    private Float threadBoreAMin3;
    @Column(name = "ThreadBoreAMax1")
    private Float threadBoreAMax1;
    @Column(name = "ThreadBoreAMax2")
    private Float threadBoreAMax2;
    @Column(name = "ThreadBoreAMax3")
    private Float threadBoreAMax3;
    @Column(name = "ThreadBoreBMin1")
    private Float threadBoreBMin1;
    @Column(name = "ThreadBoreBMin2")
    private Float threadBoreBMin2;
    @Column(name = "ThreadBoreBMin3")
    private Float threadBoreBMin3;
    @Column(name = "ThreadBoreBMax1")
    private Float threadBoreBMax1;
    @Column(name = "ThreadBoreBMax2")
    private Float threadBoreBMax2;
    @Column(name = "ThreadBoreBMax3")
    private Float threadBoreBMax3;
    @Column(name = "ThreadNeckSize1")
    private String threadNeckSize1;
    @Column(name = "ThreadNeckSize2")
    private String threadNeckSize2;
    @Column(name = "ThreadNeckSize3")
    private String threadNeckSize3;
    @Column(name = "ThreadNeckMin1")
    private Float threadNeckMin1;
    @Column(name = "ThreadNeckMin2")
    private Float threadNeckMin2;
    @Column(name = "ThreadNeckMin3")
    private Float threadNeckMin3;
    @Column(name = "ThreadNeckMax1")
    private Float threadNeckMax1;
    @Column(name = "ThreadNeckMax2")
    private Float threadNeckMax2;
    @Column(name = "ThreadNeckMax3")
    private Float threadNeckMax3;
    @OneToMany(mappedBy = "mouldId")
    private Collection<Entry> entryCollection;
    @OneToMany(mappedBy = "mouldId")
    private Collection<Product> productCollection;

    public Mould() {
    }

    public Mould(Integer id) {
        this.id = id;
    }

    public Mould(Integer id, String code) {
        this.id = id;
        this.code = code;
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

    public Float getWeightNonDgMin() {
        return weightNonDgMin;
    }

    public void setWeightNonDgMin(Float weightNonDgMin) {
        this.weightNonDgMin = weightNonDgMin;
    }

    public Float getWeightNonDgMax() {
        return weightNonDgMax;
    }

    public void setWeightNonDgMax(Float weightNonDgMax) {
        this.weightNonDgMax = weightNonDgMax;
    }

    public Float getWeightDgMin() {
        return weightDgMin;
    }

    public void setWeightDgMin(Float weightDgMin) {
        this.weightDgMin = weightDgMin;
    }

    public Float getWeightDgMax() {
        return weightDgMax;
    }

    public void setWeightDgMax(Float weightDgMax) {
        this.weightDgMax = weightDgMax;
    }

    public Float getTapPositionMin() {
        return tapPositionMin;
    }

    public void setTapPositionMin(Float tapPositionMin) {
        this.tapPositionMin = tapPositionMin;
    }

    public Float getTapPositionMax() {
        return tapPositionMax;
    }

    public void setTapPositionMax(Float tapPositionMax) {
        this.tapPositionMax = tapPositionMax;
    }

    public Float getWallNonDgUnderHandleMin() {
        return wallNonDgUnderHandleMin;
    }

    public void setWallNonDgUnderHandleMin(Float wallNonDgUnderHandleMin) {
        this.wallNonDgUnderHandleMin = wallNonDgUnderHandleMin;
    }

    public Float getWallNonDgUnderHandleMax() {
        return wallNonDgUnderHandleMax;
    }

    public void setWallNonDgUnderHandleMax(Float wallNonDgUnderHandleMax) {
        this.wallNonDgUnderHandleMax = wallNonDgUnderHandleMax;
    }

    public Float getWallNonDgBaseMin() {
        return wallNonDgBaseMin;
    }

    public void setWallNonDgBaseMin(Float wallNonDgBaseMin) {
        this.wallNonDgBaseMin = wallNonDgBaseMin;
    }

    public Float getWallNonDgBaseMax() {
        return wallNonDgBaseMax;
    }

    public void setWallNonDgBaseMax(Float wallNonDgBaseMax) {
        this.wallNonDgBaseMax = wallNonDgBaseMax;
    }

    public Float getWallNonDgClosureMin() {
        return wallNonDgClosureMin;
    }

    public void setWallNonDgClosureMin(Float wallNonDgClosureMin) {
        this.wallNonDgClosureMin = wallNonDgClosureMin;
    }

    public Float getWallNonDgClosureMax() {
        return wallNonDgClosureMax;
    }

    public void setWallNonDgClosureMax(Float wallNonDgClosureMax) {
        this.wallNonDgClosureMax = wallNonDgClosureMax;
    }

    public Float getWallNonDgHandleBungMin() {
        return wallNonDgHandleBungMin;
    }

    public void setWallNonDgHandleBungMin(Float wallNonDgHandleBungMin) {
        this.wallNonDgHandleBungMin = wallNonDgHandleBungMin;
    }

    public Float getWallNonDgHandleBungMax() {
        return wallNonDgHandleBungMax;
    }

    public void setWallNonDgHandleBungMax(Float wallNonDgHandleBungMax) {
        this.wallNonDgHandleBungMax = wallNonDgHandleBungMax;
    }

    public Float getWallNonDgHandleLeftMin() {
        return wallNonDgHandleLeftMin;
    }

    public void setWallNonDgHandleLeftMin(Float wallNonDgHandleLeftMin) {
        this.wallNonDgHandleLeftMin = wallNonDgHandleLeftMin;
    }

    public Float getWallNonDgHandleLeftMax() {
        return wallNonDgHandleLeftMax;
    }

    public void setWallNonDgHandleLeftMax(Float wallNonDgHandleLeftMax) {
        this.wallNonDgHandleLeftMax = wallNonDgHandleLeftMax;
    }

    public Float getWallNonDgHandleRightMin() {
        return wallNonDgHandleRightMin;
    }

    public void setWallNonDgHandleRightMin(Float wallNonDgHandleRightMin) {
        this.wallNonDgHandleRightMin = wallNonDgHandleRightMin;
    }

    public Float getWallNonDgHandleRightMax() {
        return wallNonDgHandleRightMax;
    }

    public void setWallNonDgHandleRightMax(Float wallNonDgHandleRightMax) {
        this.wallNonDgHandleRightMax = wallNonDgHandleRightMax;
    }

    public Float getWallDgUnderHandleMin() {
        return wallDgUnderHandleMin;
    }

    public void setWallDgUnderHandleMin(Float wallDgUnderHandleMin) {
        this.wallDgUnderHandleMin = wallDgUnderHandleMin;
    }

    public Float getWallDgUnderHandleMax() {
        return wallDgUnderHandleMax;
    }

    public void setWallDgUnderHandleMax(Float wallDgUnderHandleMax) {
        this.wallDgUnderHandleMax = wallDgUnderHandleMax;
    }

    public Float getWallDgBaseMin() {
        return wallDgBaseMin;
    }

    public void setWallDgBaseMin(Float wallDgBaseMin) {
        this.wallDgBaseMin = wallDgBaseMin;
    }

    public Float getWallDgBaseMax() {
        return wallDgBaseMax;
    }

    public void setWallDgBaseMax(Float wallDgBaseMax) {
        this.wallDgBaseMax = wallDgBaseMax;
    }

    public Float getWallDgClosureMin() {
        return wallDgClosureMin;
    }

    public void setWallDgClosureMin(Float wallDgClosureMin) {
        this.wallDgClosureMin = wallDgClosureMin;
    }

    public Float getWallDgClosureMax() {
        return wallDgClosureMax;
    }

    public void setWallDgClosureMax(Float wallDgClosureMax) {
        this.wallDgClosureMax = wallDgClosureMax;
    }

    public Float getWallDgHandleBungMin() {
        return wallDgHandleBungMin;
    }

    public void setWallDgHandleBungMin(Float wallDgHandleBungMin) {
        this.wallDgHandleBungMin = wallDgHandleBungMin;
    }

    public Float getWallDgHandleBungMax() {
        return wallDgHandleBungMax;
    }

    public void setWallDgHandleBungMax(Float wallDgHandleBungMax) {
        this.wallDgHandleBungMax = wallDgHandleBungMax;
    }

    public Float getWallDgHandleLeftMin() {
        return wallDgHandleLeftMin;
    }

    public void setWallDgHandleLeftMin(Float wallDgHandleLeftMin) {
        this.wallDgHandleLeftMin = wallDgHandleLeftMin;
    }

    public Float getWallDgHandleLeftMax() {
        return wallDgHandleLeftMax;
    }

    public void setWallDgHandleLeftMax(Float wallDgHandleLeftMax) {
        this.wallDgHandleLeftMax = wallDgHandleLeftMax;
    }

    public Float getWallDgHandRightMin() {
        return wallDgHandRightMin;
    }

    public void setWallDgHandRightMin(Float wallDgHandRightMin) {
        this.wallDgHandRightMin = wallDgHandRightMin;
    }

    public Float getWallDgHandleRightMax() {
        return wallDgHandleRightMax;
    }

    public void setWallDgHandleRightMax(Float wallDgHandleRightMax) {
        this.wallDgHandleRightMax = wallDgHandleRightMax;
    }

    public String getThreadBoreASize1() {
        return threadBoreASize1;
    }

    public void setThreadBoreASize1(String threadBoreASize1) {
        this.threadBoreASize1 = threadBoreASize1;
    }

    public String getThreadBoreASize2() {
        return threadBoreASize2;
    }

    public void setThreadBoreASize2(String threadBoreASize2) {
        this.threadBoreASize2 = threadBoreASize2;
    }

    public String getThreadBoreASize3() {
        return threadBoreASize3;
    }

    public void setThreadBoreASize3(String threadBoreASize3) {
        this.threadBoreASize3 = threadBoreASize3;
    }

    public String getThreadBoreBSize1() {
        return threadBoreBSize1;
    }

    public void setThreadBoreBSize1(String threadBoreBSize1) {
        this.threadBoreBSize1 = threadBoreBSize1;
    }

    public String getThreadBoreBSize2() {
        return threadBoreBSize2;
    }

    public void setThreadBoreBSize2(String threadBoreBSize2) {
        this.threadBoreBSize2 = threadBoreBSize2;
    }

    public String getThreadBoreBSize3() {
        return threadBoreBSize3;
    }

    public void setThreadBoreBSize3(String threadBoreBSize3) {
        this.threadBoreBSize3 = threadBoreBSize3;
    }

    public Float getThreadBoreAMin1() {
        return threadBoreAMin1;
    }

    public void setThreadBoreAMin1(Float threadBoreAMin1) {
        this.threadBoreAMin1 = threadBoreAMin1;
    }

    public Float getThreadBoreAMin2() {
        return threadBoreAMin2;
    }

    public void setThreadBoreAMin2(Float threadBoreAMin2) {
        this.threadBoreAMin2 = threadBoreAMin2;
    }

    public Float getThreadBoreAMin3() {
        return threadBoreAMin3;
    }

    public void setThreadBoreAMin3(Float threadBoreAMin3) {
        this.threadBoreAMin3 = threadBoreAMin3;
    }

    public Float getThreadBoreAMax1() {
        return threadBoreAMax1;
    }

    public void setThreadBoreAMax1(Float threadBoreAMax1) {
        this.threadBoreAMax1 = threadBoreAMax1;
    }

    public Float getThreadBoreAMax2() {
        return threadBoreAMax2;
    }

    public void setThreadBoreAMax2(Float threadBoreAMax2) {
        this.threadBoreAMax2 = threadBoreAMax2;
    }

    public Float getThreadBoreAMax3() {
        return threadBoreAMax3;
    }

    public void setThreadBoreAMax3(Float threadBoreAMax3) {
        this.threadBoreAMax3 = threadBoreAMax3;
    }

    public Float getThreadBoreBMin1() {
        return threadBoreBMin1;
    }

    public void setThreadBoreBMin1(Float threadBoreBMin1) {
        this.threadBoreBMin1 = threadBoreBMin1;
    }

    public Float getThreadBoreBMin2() {
        return threadBoreBMin2;
    }

    public void setThreadBoreBMin2(Float threadBoreBMin2) {
        this.threadBoreBMin2 = threadBoreBMin2;
    }

    public Float getThreadBoreBMin3() {
        return threadBoreBMin3;
    }

    public void setThreadBoreBMin3(Float threadBoreBMin3) {
        this.threadBoreBMin3 = threadBoreBMin3;
    }

    public Float getThreadBoreBMax1() {
        return threadBoreBMax1;
    }

    public void setThreadBoreBMax1(Float threadBoreBMax1) {
        this.threadBoreBMax1 = threadBoreBMax1;
    }

    public Float getThreadBoreBMax2() {
        return threadBoreBMax2;
    }

    public void setThreadBoreBMax2(Float threadBoreBMax2) {
        this.threadBoreBMax2 = threadBoreBMax2;
    }

    public Float getThreadBoreBMax3() {
        return threadBoreBMax3;
    }

    public void setThreadBoreBMax3(Float threadBoreBMax3) {
        this.threadBoreBMax3 = threadBoreBMax3;
    }

    public String getThreadNeckSize1() {
        return threadNeckSize1;
    }

    public void setThreadNeckSize1(String threadNeckSize1) {
        this.threadNeckSize1 = threadNeckSize1;
    }

    public String getThreadNeckSize2() {
        return threadNeckSize2;
    }

    public void setThreadNeckSize2(String threadNeckSize2) {
        this.threadNeckSize2 = threadNeckSize2;
    }

    public String getThreadNeckSize3() {
        return threadNeckSize3;
    }

    public void setThreadNeckSize3(String threadNeckSize3) {
        this.threadNeckSize3 = threadNeckSize3;
    }

    public Float getThreadNeckMin1() {
        return threadNeckMin1;
    }

    public void setThreadNeckMin1(Float threadNeckMin1) {
        this.threadNeckMin1 = threadNeckMin1;
    }

    public Float getThreadNeckMin2() {
        return threadNeckMin2;
    }

    public void setThreadNeckMin2(Float threadNeckMin2) {
        this.threadNeckMin2 = threadNeckMin2;
    }

    public Float getThreadNeckMin3() {
        return threadNeckMin3;
    }

    public void setThreadNeckMin3(Float threadNeckMin3) {
        this.threadNeckMin3 = threadNeckMin3;
    }

    public Float getThreadNeckMax1() {
        return threadNeckMax1;
    }

    public void setThreadNeckMax1(Float threadNeckMax1) {
        this.threadNeckMax1 = threadNeckMax1;
    }

    public Float getThreadNeckMax2() {
        return threadNeckMax2;
    }

    public void setThreadNeckMax2(Float threadNeckMax2) {
        this.threadNeckMax2 = threadNeckMax2;
    }

    public Float getThreadNeckMax3() {
        return threadNeckMax3;
    }

    public void setThreadNeckMax3(Float threadNeckMax3) {
        this.threadNeckMax3 = threadNeckMax3;
    }

    @XmlTransient
    public Collection<Entry> getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(Collection<Entry> entryCollection) {
        this.entryCollection = entryCollection;
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

    @Override
    public void setDefaultValue() {
        this.code="Mould Code";
    }
    
}
