/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.repositories.MachineController;
import com.cch.aj.entryrecorder.common.exceptions.NonexistentEntityException;
import com.cch.aj.entryrecorder.entities.Machine;
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
 * @author chacao
 */
public class MachineJpaController implements MachineController {

    public MachineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Machine machine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(machine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Machine machine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            machine = em.merge(machine);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = machine.getId();
                if (findMachine(id) == null) {
                    throw new NonexistentEntityException("The machine with id " + id + " no longer exists.");
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
            Machine machine;
            try {
                machine = em.getReference(Machine.class, id);
                machine.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The machine with id " + id + " no longer exists.", enfe);
            }
            em.remove(machine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Machine> findMachineEntities() {
        return findMachineEntities(true, -1, -1);
    }

    @Override
    public List<Machine> findMachineEntities(int maxResults, int firstResult) {
        return findMachineEntities(false, maxResults, firstResult);
    }

    private List<Machine> findMachineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Machine.class));
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
    public Machine findMachine(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Machine.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getMachineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Machine> rt = cq.from(Machine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
