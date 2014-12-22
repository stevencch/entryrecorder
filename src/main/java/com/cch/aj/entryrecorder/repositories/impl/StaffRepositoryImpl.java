/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
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
public class StaffRepositoryImpl implements SettingRepository<Staff>{

    StaffJpaController controller;
    
    public StaffRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new StaffJpaController(emf);
    }

    @Override
    public void create(Staff item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
        controller.destroy(id);
    }

    @Override
    public void edit(Staff item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Staff findEntity(Integer id) {
       return  this.controller.findStaff(id);
    }

    @Override
    public List<Staff> findEntities() {
        return this.controller.findStaffEntities();
    }

    @Override
    public List<Staff> findEntities(int maxResults, int firstResult) {
        return this.controller.findStaffEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getStaffCount();
    }

    
}
