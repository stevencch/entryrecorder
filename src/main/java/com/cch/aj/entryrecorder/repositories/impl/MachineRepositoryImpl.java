/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class MachineRepositoryImpl implements SettingRepository<Machine>{

    MachineJpaController controller;
    
    public MachineRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new MachineJpaController(emf);
    }

    @Override
    public void create(Machine item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        controller.destroy(id);
    }

    @Override
    public void edit(Machine item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Machine findEntity(Integer id) {
       return  this.controller.findMachine(id);
    }

    @Override
    public List<Machine> findEntities() {
        return this.controller.findMachineEntities();
    }

    @Override
    public List<Machine> findEntities(int maxResults, int firstResult) {
        return this.controller.findMachineEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getMachineCount();
    }

    
}
