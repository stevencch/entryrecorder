/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface AdditiveController extends Serializable {

    void create(Additive additive);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Additive additive) throws NonexistentEntityException, Exception;

    Additive findAdditive(Integer id);

    List<Additive> findAdditiveEntities();

    List<Additive> findAdditiveEntities(int maxResults, int firstResult);

    int getAdditiveCount();

    EntityManager getEntityManager();
    
}
