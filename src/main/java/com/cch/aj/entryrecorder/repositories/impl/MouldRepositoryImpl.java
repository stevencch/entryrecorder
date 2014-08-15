/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class MouldRepositoryImpl implements SettingRepository<Mould>{

    MouldJpaController controller;
    
    public MouldRepositoryImpl(String dbConnectionString) {
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString);
        this.controller=new MouldJpaController(emf);
    }

    @Override
    public void create(Mould item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        controller.destroy(id);
    }

    @Override
    public void edit(Mould item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Mould findEntity(Integer id) {
       return  this.controller.findMould(id);
    }

    @Override
    public List<Mould> findEntities() {
        return this.controller.findMouldEntities();
    }

    @Override
    public List<Mould> findEntities(int maxResults, int firstResult) {
        return this.controller.findMouldEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getMouldCount();
    }

    
}
