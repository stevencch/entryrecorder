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
    @Basic(optional = false)
    @Column(name = "WeightNonDgMin")
    private float weightNonDgMin;
    @Basic(optional = false)
    @Column(name = "WeightNonDgMax")
    private float weightNonDgMax;
    @Basic(optional = false)
    @Column(name = "WeightDgMin")
    private float weightDgMin;
    @Basic(optional = false)
    @Column(name = "WeightDgMax")
    private float weightDgMax;
    @Basic(optional = false)
    @Column(name = "TapPositionMin")
    private float tapPositionMin;
    @Basic(optional = false)
    @Column(name = "TapPositionMax")
    private float tapPositionMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgUnderHandleMin")
    private float wallNonDgUnderHandleMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgUnderHandleMax")
    private float wallNonDgUnderHandleMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgBaseMin")
    private float wallNonDgBaseMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgBaseMax")
    private float wallNonDgBaseMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgClosureMin")
    private float wallNonDgClosureMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgClosureMax")
    private float wallNonDgClosureMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleBungMin")
    private float wallNonDgHandleBungMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleBungMax")
    private float wallNonDgHandleBungMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleLeftMin")
    private float wallNonDgHandleLeftMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleLeftMax")
    private float wallNonDgHandleLeftMax;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleRightMin")
    private float wallNonDgHandleRightMin;
    @Basic(optional = false)
    @Column(name = "WallNonDgHandleRightMax")
    private float wallNonDgHandleRightMax;
    @Basic(optional = false)
    @Column(name = "WallDgUnderHandleMin")
    private float wallDgUnderHandleMin;
    @Basic(optional = false)
    @Column(name = "WallDgUnderHandleMax")
    private float wallDgUnderHandleMax;
    @Basic(optional = false)
    @Column(name = "WallDgBaseMin")
    private float wallDgBaseMin;
    @Basic(optional = false)
    @Column(name = "WallDgBaseMax")
    private float wallDgBaseMax;
    @Basic(optional = false)
    @Column(name = "WallDgClosureMin")
    private float wallDgClosureMin;
    @Basic(optional = false)
    @Column(name = "WallDgClosureMax")
    private float wallDgClosureMax;
    @Basic(optional = false)
    @Column(name = "WallDgHandleBungMin")
    private float wallDgHandleBungMin;
    @Basic(optional = false)
    @Column(name = "WallDgHandleBungMax")
    private float wallDgHandleBungMax;
    @Basic(optional = false)
    @Column(name = "WallDgHandleLeftMin")
    private float wallDgHandleLeftMin;
    @Basic(optional = false)
    @Column(name = "WallDgHandleLeftMax")
    private float wallDgHandleLeftMax;
    @Basic(optional = false)
    @Column(name = "WallDgHandRightMin")
    private float wallDgHandRightMin;
    @Basic(optional = false)
    @Column(name = "WallDgHandleRightMax")
    private float wallDgHandleRightMax;
    @Basic(optional = false)
    @Column(name = "ThreadBoreASize1")
    private String threadBoreASize1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreASize2")
    private String threadBoreASize2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreASize3")
    private String threadBoreASize3;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBSize1")
    private String threadBoreBSize1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBSize2")
    private String threadBoreBSize2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBSize3")
    private String threadBoreBSize3;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMin1")
    private float threadBoreAMin1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMin2")
    private float threadBoreAMin2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMin3")
    private float threadBoreAMin3;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMax1")
    private float threadBoreAMax1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMax2")
    private float threadBoreAMax2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreAMax3")
    private float threadBoreAMax3;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMin1")
    private float threadBoreBMin1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMin2")
    private float threadBoreBMin2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMin3")
    private float threadBoreBMin3;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMax1")
    private float threadBoreBMax1;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMax2")
    private float threadBoreBMax2;
    @Basic(optional = false)
    @Column(name = "ThreadBoreBMax3")
    private float threadBoreBMax3;
    @Basic(optional = false)
    @Column(name = "ThreadNeckSize1")
    private String threadNeckSize1;
    @Basic(optional = false)
    @Column(name = "ThreadNeckSize2")
    private String threadNeckSize2;
    @Basic(optional = false)
    @Column(name = "ThreadNeckSize3")
    private String threadNeckSize3;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMin1")
    private float threadNeckMin1;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMin2")
    private float threadNeckMin2;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMin3")
    private float threadNeckMin3;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMax1")
    private float threadNeckMax1;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMax2")
    private float threadNeckMax2;
    @Basic(optional = false)
    @Column(name = "ThreadNeckMax3")
    private float threadNeckMax3;

    public Mould() {
    }

    public Mould(Integer id) {
        this.id = id;
    }

    public Mould(Integer id, String name, String code, String volumn, String manufacturer, String year, String imageDrawing, String imageNonDg, String imageDg, String imageBoreA, String imageBoreB, String imageNeck, String imageTap, float weightNonDgMin, float weightNonDgMax, float weightDgMin, float weightDgMax, float tapPositionMin, float tapPositionMax, float wallNonDgUnderHandleMin, float wallNonDgUnderHandleMax, float wallNonDgBaseMin, float wallNonDgBaseMax, float wallNonDgClosureMin, float wallNonDgClosureMax, float wallNonDgHandleBungMin, float wallNonDgHandleBungMax, float wallNonDgHandleLeftMin, float wallNonDgHandleLeftMax, float wallNonDgHandleRightMin, float wallNonDgHandleRightMax, float wallDgUnderHandleMin, float wallDgUnderHandleMax, float wallDgBaseMin, float wallDgBaseMax, float wallDgClosureMin, float wallDgClosureMax, float wallDgHandleBungMin, float wallDgHandleBungMax, float wallDgHandleLeftMin, float wallDgHandleLeftMax, float wallDgHandRightMin, float wallDgHandleRightMax, String threadBoreASize1, String threadBoreASize2, String threadBoreASize3, String threadBoreBSize1, String threadBoreBSize2, String threadBoreBSize3, float threadBoreAMin1, float threadBoreAMin2, float threadBoreAMin3, float threadBoreAMax1, float threadBoreAMax2, float threadBoreAMax3, float threadBoreBMin1, float threadBoreBMin2, float threadBoreBMin3, float threadBoreBMax1, float threadBoreBMax2, float threadBoreBMax3, String threadNeckSize1, String threadNeckSize2, String threadNeckSize3, float threadNeckMin1, float threadNeckMin2, float threadNeckMin3, float threadNeckMax1, float threadNeckMax2, float threadNeckMax3) {
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
        this.weightNonDgMin = weightNonDgMin;
        this.weightNonDgMax = weightNonDgMax;
        this.weightDgMin = weightDgMin;
        this.weightDgMax = weightDgMax;
        this.tapPositionMin = tapPositionMin;
        this.tapPositionMax = tapPositionMax;
        this.wallNonDgUnderHandleMin = wallNonDgUnderHandleMin;
        this.wallNonDgUnderHandleMax = wallNonDgUnderHandleMax;
        this.wallNonDgBaseMin = wallNonDgBaseMin;
        this.wallNonDgBaseMax = wallNonDgBaseMax;
        this.wallNonDgClosureMin = wallNonDgClosureMin;
        this.wallNonDgClosureMax = wallNonDgClosureMax;
        this.wallNonDgHandleBungMin = wallNonDgHandleBungMin;
        this.wallNonDgHandleBungMax = wallNonDgHandleBungMax;
        this.wallNonDgHandleLeftMin = wallNonDgHandleLeftMin;
        this.wallNonDgHandleLeftMax = wallNonDgHandleLeftMax;
        this.wallNonDgHandleRightMin = wallNonDgHandleRightMin;
        this.wallNonDgHandleRightMax = wallNonDgHandleRightMax;
        this.wallDgUnderHandleMin = wallDgUnderHandleMin;
        this.wallDgUnderHandleMax = wallDgUnderHandleMax;
        this.wallDgBaseMin = wallDgBaseMin;
        this.wallDgBaseMax = wallDgBaseMax;
        this.wallDgClosureMin = wallDgClosureMin;
        this.wallDgClosureMax = wallDgClosureMax;
        this.wallDgHandleBungMin = wallDgHandleBungMin;
        this.wallDgHandleBungMax = wallDgHandleBungMax;
        this.wallDgHandleLeftMin = wallDgHandleLeftMin;
        this.wallDgHandleLeftMax = wallDgHandleLeftMax;
        this.wallDgHandRightMin = wallDgHandRightMin;
        this.wallDgHandleRightMax = wallDgHandleRightMax;
        this.threadBoreASize1 = threadBoreASize1;
        this.threadBoreASize2 = threadBoreASize2;
        this.threadBoreASize3 = threadBoreASize3;
        this.threadBoreBSize1 = threadBoreBSize1;
        this.threadBoreBSize2 = threadBoreBSize2;
        this.threadBoreBSize3 = threadBoreBSize3;
        this.threadBoreAMin1 = threadBoreAMin1;
        this.threadBoreAMin2 = threadBoreAMin2;
        this.threadBoreAMin3 = threadBoreAMin3;
        this.threadBoreAMax1 = threadBoreAMax1;
        this.threadBoreAMax2 = threadBoreAMax2;
        this.threadBoreAMax3 = threadBoreAMax3;
        this.threadBoreBMin1 = threadBoreBMin1;
        this.threadBoreBMin2 = threadBoreBMin2;
        this.threadBoreBMin3 = threadBoreBMin3;
        this.threadBoreBMax1 = threadBoreBMax1;
        this.threadBoreBMax2 = threadBoreBMax2;
        this.threadBoreBMax3 = threadBoreBMax3;
        this.threadNeckSize1 = threadNeckSize1;
        this.threadNeckSize2 = threadNeckSize2;
        this.threadNeckSize3 = threadNeckSize3;
        this.threadNeckMin1 = threadNeckMin1;
        this.threadNeckMin2 = threadNeckMin2;
        this.threadNeckMin3 = threadNeckMin3;
        this.threadNeckMax1 = threadNeckMax1;
        this.threadNeckMax2 = threadNeckMax2;
        this.threadNeckMax3 = threadNeckMax3;
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

    public float getWeightNonDgMin() {
        return weightNonDgMin;
    }

    public void setWeightNonDgMin(float weightNonDgMin) {
        this.weightNonDgMin = weightNonDgMin;
    }

    public float getWeightNonDgMax() {
        return weightNonDgMax;
    }

    public void setWeightNonDgMax(float weightNonDgMax) {
        this.weightNonDgMax = weightNonDgMax;
    }

    public float getWeightDgMin() {
        return weightDgMin;
    }

    public void setWeightDgMin(float weightDgMin) {
        this.weightDgMin = weightDgMin;
    }

    public float getWeightDgMax() {
        return weightDgMax;
    }

    public void setWeightDgMax(float weightDgMax) {
        this.weightDgMax = weightDgMax;
    }

    public float getTapPositionMin() {
        return tapPositionMin;
    }

    public void setTapPositionMin(float tapPositionMin) {
        this.tapPositionMin = tapPositionMin;
    }

    public float getTapPositionMax() {
        return tapPositionMax;
    }

    public void setTapPositionMax(float tapPositionMax) {
        this.tapPositionMax = tapPositionMax;
    }

    public float getWallNonDgUnderHandleMin() {
        return wallNonDgUnderHandleMin;
    }

    public void setWallNonDgUnderHandleMin(float wallNonDgUnderHandleMin) {
        this.wallNonDgUnderHandleMin = wallNonDgUnderHandleMin;
    }

    public float getWallNonDgUnderHandleMax() {
        return wallNonDgUnderHandleMax;
    }

    public void setWallNonDgUnderHandleMax(float wallNonDgUnderHandleMax) {
        this.wallNonDgUnderHandleMax = wallNonDgUnderHandleMax;
    }

    public float getWallNonDgBaseMin() {
        return wallNonDgBaseMin;
    }

    public void setWallNonDgBaseMin(float wallNonDgBaseMin) {
        this.wallNonDgBaseMin = wallNonDgBaseMin;
    }

    public float getWallNonDgBaseMax() {
        return wallNonDgBaseMax;
    }

    public void setWallNonDgBaseMax(float wallNonDgBaseMax) {
        this.wallNonDgBaseMax = wallNonDgBaseMax;
    }

    public float getWallNonDgClosureMin() {
        return wallNonDgClosureMin;
    }

    public void setWallNonDgClosureMin(float wallNonDgClosureMin) {
        this.wallNonDgClosureMin = wallNonDgClosureMin;
    }

    public float getWallNonDgClosureMax() {
        return wallNonDgClosureMax;
    }

    public void setWallNonDgClosureMax(float wallNonDgClosureMax) {
        this.wallNonDgClosureMax = wallNonDgClosureMax;
    }

    public float getWallNonDgHandleBungMin() {
        return wallNonDgHandleBungMin;
    }

    public void setWallNonDgHandleBungMin(float wallNonDgHandleBungMin) {
        this.wallNonDgHandleBungMin = wallNonDgHandleBungMin;
    }

    public float getWallNonDgHandleBungMax() {
        return wallNonDgHandleBungMax;
    }

    public void setWallNonDgHandleBungMax(float wallNonDgHandleBungMax) {
        this.wallNonDgHandleBungMax = wallNonDgHandleBungMax;
    }

    public float getWallNonDgHandleLeftMin() {
        return wallNonDgHandleLeftMin;
    }

    public void setWallNonDgHandleLeftMin(float wallNonDgHandleLeftMin) {
        this.wallNonDgHandleLeftMin = wallNonDgHandleLeftMin;
    }

    public float getWallNonDgHandleLeftMax() {
        return wallNonDgHandleLeftMax;
    }

    public void setWallNonDgHandleLeftMax(float wallNonDgHandleLeftMax) {
        this.wallNonDgHandleLeftMax = wallNonDgHandleLeftMax;
    }

    public float getWallNonDgHandleRightMin() {
        return wallNonDgHandleRightMin;
    }

    public void setWallNonDgHandleRightMin(float wallNonDgHandleRightMin) {
        this.wallNonDgHandleRightMin = wallNonDgHandleRightMin;
    }

    public float getWallNonDgHandleRightMax() {
        return wallNonDgHandleRightMax;
    }

    public void setWallNonDgHandleRightMax(float wallNonDgHandleRightMax) {
        this.wallNonDgHandleRightMax = wallNonDgHandleRightMax;
    }

    public float getWallDgUnderHandleMin() {
        return wallDgUnderHandleMin;
    }

    public void setWallDgUnderHandleMin(float wallDgUnderHandleMin) {
        this.wallDgUnderHandleMin = wallDgUnderHandleMin;
    }

    public float getWallDgUnderHandleMax() {
        return wallDgUnderHandleMax;
    }

    public void setWallDgUnderHandleMax(float wallDgUnderHandleMax) {
        this.wallDgUnderHandleMax = wallDgUnderHandleMax;
    }

    public float getWallDgBaseMin() {
        return wallDgBaseMin;
    }

    public void setWallDgBaseMin(float wallDgBaseMin) {
        this.wallDgBaseMin = wallDgBaseMin;
    }

    public float getWallDgBaseMax() {
        return wallDgBaseMax;
    }

    public void setWallDgBaseMax(float wallDgBaseMax) {
        this.wallDgBaseMax = wallDgBaseMax;
    }

    public float getWallDgClosureMin() {
        return wallDgClosureMin;
    }

    public void setWallDgClosureMin(float wallDgClosureMin) {
        this.wallDgClosureMin = wallDgClosureMin;
    }

    public float getWallDgClosureMax() {
        return wallDgClosureMax;
    }

    public void setWallDgClosureMax(float wallDgClosureMax) {
        this.wallDgClosureMax = wallDgClosureMax;
    }

    public float getWallDgHandleBungMin() {
        return wallDgHandleBungMin;
    }

    public void setWallDgHandleBungMin(float wallDgHandleBungMin) {
        this.wallDgHandleBungMin = wallDgHandleBungMin;
    }

    public float getWallDgHandleBungMax() {
        return wallDgHandleBungMax;
    }

    public void setWallDgHandleBungMax(float wallDgHandleBungMax) {
        this.wallDgHandleBungMax = wallDgHandleBungMax;
    }

    public float getWallDgHandleLeftMin() {
        return wallDgHandleLeftMin;
    }

    public void setWallDgHandleLeftMin(float wallDgHandleLeftMin) {
        this.wallDgHandleLeftMin = wallDgHandleLeftMin;
    }

    public float getWallDgHandleLeftMax() {
        return wallDgHandleLeftMax;
    }

    public void setWallDgHandleLeftMax(float wallDgHandleLeftMax) {
        this.wallDgHandleLeftMax = wallDgHandleLeftMax;
    }

    public float getWallDgHandRightMin() {
        return wallDgHandRightMin;
    }

    public void setWallDgHandRightMin(float wallDgHandRightMin) {
        this.wallDgHandRightMin = wallDgHandRightMin;
    }

    public float getWallDgHandleRightMax() {
        return wallDgHandleRightMax;
    }

    public void setWallDgHandleRightMax(float wallDgHandleRightMax) {
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

    public float getThreadBoreAMin1() {
        return threadBoreAMin1;
    }

    public void setThreadBoreAMin1(float threadBoreAMin1) {
        this.threadBoreAMin1 = threadBoreAMin1;
    }

    public float getThreadBoreAMin2() {
        return threadBoreAMin2;
    }

    public void setThreadBoreAMin2(float threadBoreAMin2) {
        this.threadBoreAMin2 = threadBoreAMin2;
    }

    public float getThreadBoreAMin3() {
        return threadBoreAMin3;
    }

    public void setThreadBoreAMin3(float threadBoreAMin3) {
        this.threadBoreAMin3 = threadBoreAMin3;
    }

    public float getThreadBoreAMax1() {
        return threadBoreAMax1;
    }

    public void setThreadBoreAMax1(float threadBoreAMax1) {
        this.threadBoreAMax1 = threadBoreAMax1;
    }

    public float getThreadBoreAMax2() {
        return threadBoreAMax2;
    }

    public void setThreadBoreAMax2(float threadBoreAMax2) {
        this.threadBoreAMax2 = threadBoreAMax2;
    }

    public float getThreadBoreAMax3() {
        return threadBoreAMax3;
    }

    public void setThreadBoreAMax3(float threadBoreAMax3) {
        this.threadBoreAMax3 = threadBoreAMax3;
    }

    public float getThreadBoreBMin1() {
        return threadBoreBMin1;
    }

    public void setThreadBoreBMin1(float threadBoreBMin1) {
        this.threadBoreBMin1 = threadBoreBMin1;
    }

    public float getThreadBoreBMin2() {
        return threadBoreBMin2;
    }

    public void setThreadBoreBMin2(float threadBoreBMin2) {
        this.threadBoreBMin2 = threadBoreBMin2;
    }

    public float getThreadBoreBMin3() {
        return threadBoreBMin3;
    }

    public void setThreadBoreBMin3(float threadBoreBMin3) {
        this.threadBoreBMin3 = threadBoreBMin3;
    }

    public float getThreadBoreBMax1() {
        return threadBoreBMax1;
    }

    public void setThreadBoreBMax1(float threadBoreBMax1) {
        this.threadBoreBMax1 = threadBoreBMax1;
    }

    public float getThreadBoreBMax2() {
        return threadBoreBMax2;
    }

    public void setThreadBoreBMax2(float threadBoreBMax2) {
        this.threadBoreBMax2 = threadBoreBMax2;
    }

    public float getThreadBoreBMax3() {
        return threadBoreBMax3;
    }

    public void setThreadBoreBMax3(float threadBoreBMax3) {
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

    public float getThreadNeckMin1() {
        return threadNeckMin1;
    }

    public void setThreadNeckMin1(float threadNeckMin1) {
        this.threadNeckMin1 = threadNeckMin1;
    }

    public float getThreadNeckMin2() {
        return threadNeckMin2;
    }

    public void setThreadNeckMin2(float threadNeckMin2) {
        this.threadNeckMin2 = threadNeckMin2;
    }

    public float getThreadNeckMin3() {
        return threadNeckMin3;
    }

    public void setThreadNeckMin3(float threadNeckMin3) {
        this.threadNeckMin3 = threadNeckMin3;
    }

    public float getThreadNeckMax1() {
        return threadNeckMax1;
    }

    public void setThreadNeckMax1(float threadNeckMax1) {
        this.threadNeckMax1 = threadNeckMax1;
    }

    public float getThreadNeckMax2() {
        return threadNeckMax2;
    }

    public void setThreadNeckMax2(float threadNeckMax2) {
        this.threadNeckMax2 = threadNeckMax2;
    }

    public float getThreadNeckMax3() {
        return threadNeckMax3;
    }

    public void setThreadNeckMax3(float threadNeckMax3) {
        this.threadNeckMax3 = threadNeckMax3;
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
