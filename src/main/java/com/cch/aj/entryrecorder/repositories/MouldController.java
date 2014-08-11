/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface MouldController extends Serializable {

    void create(Mould mould);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Mould mould) throws NonexistentEntityException, Exception;

    Mould findMould(Integer id);

    List<Mould> findMouldEntities();

    List<Mould> findMouldEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getMouldCount();
    
}