/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Mouldsetting;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface MouldsettingController extends Serializable {

    void create(Mouldsetting mouldsetting);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Mouldsetting mouldsetting) throws NonexistentEntityException, Exception;

    Mouldsetting findMouldsetting(Integer id);

    List<Mouldsetting> findMouldsettingEntities();

    List<Mouldsetting> findMouldsettingEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getMouldsettingCount();
    
}
