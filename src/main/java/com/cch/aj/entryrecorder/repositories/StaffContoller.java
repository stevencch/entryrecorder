/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface StaffContoller extends Serializable {

    void create(Staff staff);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Staff staff) throws NonexistentEntityException, Exception;

    Staff findStaff(Integer id);

    List<Staff> findStaffEntities();

    List<Staff> findStaffEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getStaffCount();
    
}
