/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface EntryController extends Serializable {

    void create(Entry entry);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Entry entry) throws NonexistentEntityException, Exception;

    Entry findEntry(Integer id);

    List<Entry> findEntryEntities();

    List<Entry> findEntryEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getEntryCount();
    
}
