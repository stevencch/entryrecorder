/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.repositories.RecordSettingRepository;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author chacao
 */
public class RecordRepositoryImpl implements RecordSettingRepository {

    RecordJpaController controller;

    public RecordRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller = new RecordJpaController(emf);
    }

    @Override
    public void create(Record item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        controller.destroy(id);
    }

    @Override
    public void edit(Record item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Record findEntity(Integer id) {
        return this.controller.findRecord(id);
    }

    @Override
    public List<Record> findEntities() {
        return this.controller.findRecordEntities();
    }

    @Override
    public List<Record> findEntities(int maxResults, int firstResult) {
        return this.controller.findRecordEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getRecordCount();
    }

    @Override
    public List<Record> FindEntitiesByKeyAndRecord(RecordKey key, int recordId) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        try {
            CriteriaQuery<Record> cq = em.getCriteriaBuilder().createQuery(Record.class);
            Root<Record> from = cq.from(Record.class);
            Predicate pKey = cb.equal(from.get("recordKey"), key.toString());
            Predicate pEntry = cb.equal(from.get("entryId").get("id"), recordId);
            if (key != RecordKey.ALL) {
                cq.where(cb.and(pKey, pEntry)).select(from);
            } else {
                cq.where(pEntry).select(from);
            }
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
