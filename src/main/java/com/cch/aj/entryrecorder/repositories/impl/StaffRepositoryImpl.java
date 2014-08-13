/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.StaffController;
import com.cch.aj.entryrecorder.repositories.StaffRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class StaffRepositoryImpl implements StaffRepository{

    StaffController staffController;
    
    public StaffRepositoryImpl(String dbConnectionString) {
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString);
        this.staffController=new StaffJpaController(emf);
    }

    @Override
    public void create(Staff staff) {
        this.staffController.create(staff);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        staffController.destroy(id);
    }

    @Override
    public void edit(Staff staff) throws NonexistentEntityException, Exception {
        this.staffController.edit(staff);
    }

    @Override
    public Staff findStaff(Integer id) {
       return  this.staffController.findStaff(id);
    }

    @Override
    public List<Staff> findStaffEntities() {
        return this.staffController.findStaffEntities();
    }

    @Override
    public List<Staff> findStaffEntities(int maxResults, int firstResult) {
        return this.staffController.findStaffEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.staffController.getEntityManager();
    }

    @Override
    public int getStaffCount() {
        return this.staffController.getStaffCount();
    }
    
}
