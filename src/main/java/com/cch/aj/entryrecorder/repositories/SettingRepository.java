/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author chacao
 */
public interface  SettingRepository<T> {
    void create(T machine);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(T machine) throws NonexistentEntityException, Exception;

    T findEntity(Integer id);

    List<T> findEntities();

    List<T> findEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getEntityCount();
}
