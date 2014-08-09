/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.repositories.MouldsettingController;
import com.cch.aj.entryrecorder.entities.Mouldsetting;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Administrator
 */
public class MouldsettingJpaController implements MouldsettingController {

    public MouldsettingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Mouldsetting mouldsetting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mouldsetting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Mouldsetting mouldsetting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mouldsetting = em.merge(mouldsetting);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mouldsetting.getId();
                if (findMouldsetting(id) == null) {
                    throw new NonexistentEntityException("The mouldsetting with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mouldsetting mouldsetting;
            try {
                mouldsetting = em.getReference(Mouldsetting.class, id);
                mouldsetting.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mouldsetting with id " + id + " no longer exists.", enfe);
            }
            em.remove(mouldsetting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Mouldsetting> findMouldsettingEntities() {
        return findMouldsettingEntities(true, -1, -1);
    }

    @Override
    public List<Mouldsetting> findMouldsettingEntities(int maxResults, int firstResult) {
        return findMouldsettingEntities(false, maxResults, firstResult);
    }

    private List<Mouldsetting> findMouldsettingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mouldsetting.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Mouldsetting findMouldsetting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mouldsetting.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getMouldsettingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mouldsetting> rt = cq.from(Mouldsetting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
