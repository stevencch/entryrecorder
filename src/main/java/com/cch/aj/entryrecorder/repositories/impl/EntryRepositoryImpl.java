/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class EntryRepositoryImpl implements SettingRepository<Entry>{

    EntryJpaController controller;
    
    public EntryRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new EntryJpaController(emf);
    }

    @Override
    public void create(Entry item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        controller.destroy(id);
    }

    @Override
    public void edit(Entry item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Entry findEntity(Integer id) {
       return  this.controller.findEntry(id);
    }

    @Override
    public List<Entry> findEntities() {
        return this.controller.findEntryEntities();
    }

    @Override
    public List<Entry> findEntities(int maxResults, int firstResult) {
        return this.controller.findEntryEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getEntryCount();
    }

    
}

