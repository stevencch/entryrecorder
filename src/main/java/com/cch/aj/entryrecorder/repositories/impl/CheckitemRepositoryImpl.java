/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Checkitem;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.IllegalOrphanException;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class CheckitemRepositoryImpl implements SettingRepository<Checkitem>{

    CheckitemJpaController controller;
    
    public CheckitemRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new CheckitemJpaController(emf);
    }

    @Override
    public void create(Checkitem item) {
        try {
            this.controller.create(item);
        } catch (Exception ex) {
            Logger.getLogger(CheckitemRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
            controller.destroy(id);
    }

    @Override
    public void edit(Checkitem item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Checkitem findEntity(Integer id) {
       return  this.controller.findCheckitem(id);
    }

    @Override
    public List<Checkitem> findEntities() {
        return this.controller.findCheckitemEntities();
    }

    @Override
    public List<Checkitem> findEntities(int maxResults, int firstResult) {
        return this.controller.findCheckitemEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getCheckitemCount();
    }

    
}
