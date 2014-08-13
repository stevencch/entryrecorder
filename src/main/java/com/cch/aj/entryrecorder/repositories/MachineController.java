/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.common.exceptions.NonexistentEntityException;
import com.cch.aj.entryrecorder.entities.Machine;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author chacao
 */
public interface MachineController extends Serializable {

    void create(Machine machine);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Machine machine) throws NonexistentEntityException, Exception;

    Machine findMachine(Integer id);

    List<Machine> findMachineEntities();

    List<Machine> findMachineEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getMachineCount();
    
}
