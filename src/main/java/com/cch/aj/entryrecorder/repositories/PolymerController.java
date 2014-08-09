/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface PolymerController extends Serializable {

    void create(Polymer polymer);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Polymer polymer) throws NonexistentEntityException, Exception;

    Polymer findPolymer(Integer id);

    List<Polymer> findPolymerEntities();

    List<Polymer> findPolymerEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPolymerCount();
    
}
