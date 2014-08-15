/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class PolymerRepositoryImpl implements SettingRepository<Polymer>{

    PolymerJpaController controller;
    
    public PolymerRepositoryImpl(String dbConnectionString) {
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString);
        this.controller=new PolymerJpaController(emf);
    }

    @Override
    public void create(Polymer item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        controller.destroy(id);
    }

    @Override
    public void edit(Polymer item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Polymer findEntity(Integer id) {
       return  this.controller.findPolymer(id);
    }

    @Override
    public List<Polymer> findEntities() {
        return this.controller.findPolymerEntities();
    }

    @Override
    public List<Polymer> findEntities(int maxResults, int firstResult) {
        return this.controller.findPolymerEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getPolymerCount();
    }

    
}
