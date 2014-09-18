/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.repositories.NativeRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author chacao
 */
public class NativeRepositoryImpl implements NativeRepository {

    EntityManager em;
    public NativeRepositoryImpl(String dbConnectionString){
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString);
        em=emf.createEntityManager();
    }
    @Override
    public void executeUpdate(String query) {
        em.getTransaction().begin();
        Query q=em.createNativeQuery(query);
        q.executeUpdate();
        em.getTransaction().commit();
    }
    
}
