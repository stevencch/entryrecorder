/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Embossing;
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
public class EmbossingRepositoryImpl implements SettingRepository<Embossing>{

    EmbossingJpaController controller;
    
    public EmbossingRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new EmbossingJpaController(emf);
    }

    @Override
    public void create(Embossing item) {
        try {
            this.controller.create(item);
        } catch (Exception ex) {
            Logger.getLogger(EmbossingRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        try {
            controller.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(EmbossingRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(Embossing item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Embossing findEntity(Integer id) {
       return  this.controller.findEmbossing(id);
    }

    @Override
    public List<Embossing> findEntities() {
        return this.controller.findEmbossingEntities();
    }

    @Override
    public List<Embossing> findEntities(int maxResults, int firstResult) {
        return this.controller.findEmbossingEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getEmbossingCount();
    }

    
}
