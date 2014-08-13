/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.repositories.MachineController;
import com.cch.aj.entryrecorder.common.exceptions.NonexistentEntityException;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.MachineRepository;
import com.cch.aj.entryrecorder.repositories.StaffController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class MachineRepositoryImpl implements MachineRepository{

    MachineController controller;
    
    public MachineRepositoryImpl(String dbConnectionString) {
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString);
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
    public Machine findMachine(Integer id) {
       return  this.controller.findMachine(id);
    }

    @Override
    public List<Machine> findMachineEntities() {
        return this.controller.findMachineEntities();
    }

    @Override
    public List<Machine> findMachineEntities(int maxResults, int firstResult) {
        return this.controller.findMachineEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getMachineCount() {
        return this.controller.getMachineCount();
    }
    
}
