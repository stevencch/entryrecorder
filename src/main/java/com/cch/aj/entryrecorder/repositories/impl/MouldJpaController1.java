/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Mould;
import java.util.ArrayList;
import java.util.Collection;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class MouldJpaController1 implements Serializable {

    public MouldJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mould mould) {
        if (mould.getEntryCollection() == null) {
            mould.setEntryCollection(new ArrayList<Entry>());
        }
        if (mould.getProductCollection() == null) {
            mould.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Entry> attachedEntryCollection = new ArrayList<Entry>();
            for (Entry entryCollectionEntryToAttach : mould.getEntryCollection()) {
                entryCollectionEntryToAttach = em.getReference(entryCollectionEntryToAttach.getClass(), entryCollectionEntryToAttach.getId());
                attachedEntryCollection.add(entryCollectionEntryToAttach);
            }
            mould.setEntryCollection(attachedEntryCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : mould.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            mould.setProductCollection(attachedProductCollection);
            em.persist(mould);
            for (Entry entryCollectionEntry : mould.getEntryCollection()) {
                Mould oldMouldIdOfEntryCollectionEntry = entryCollectionEntry.getMouldId();
                entryCollectionEntry.setMouldId(mould);
                entryCollectionEntry = em.merge(entryCollectionEntry);
                if (oldMouldIdOfEntryCollectionEntry != null) {
                    oldMouldIdOfEntryCollectionEntry.getEntryCollection().remove(entryCollectionEntry);
                    oldMouldIdOfEntryCollectionEntry = em.merge(oldMouldIdOfEntryCollectionEntry);
                }
            }
            for (Product productCollectionProduct : mould.getProductCollection()) {
                Mould oldMouldIdOfProductCollectionProduct = productCollectionProduct.getMouldId();
                productCollectionProduct.setMouldId(mould);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldMouldIdOfProductCollectionProduct != null) {
                    oldMouldIdOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldMouldIdOfProductCollectionProduct = em.merge(oldMouldIdOfProductCollectionProduct);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mould mould) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mould persistentMould = em.find(Mould.class, mould.getId());
            Collection<Entry> entryCollectionOld = persistentMould.getEntryCollection();
            Collection<Entry> entryCollectionNew = mould.getEntryCollection();
            Collection<Product> productCollectionOld = persistentMould.getProductCollection();
            Collection<Product> productCollectionNew = mould.getProductCollection();
            Collection<Entry> attachedEntryCollectionNew = new ArrayList<Entry>();
            for (Entry entryCollectionNewEntryToAttach : entryCollectionNew) {
                entryCollectionNewEntryToAttach = em.getReference(entryCollectionNewEntryToAttach.getClass(), entryCollectionNewEntryToAttach.getId());
                attachedEntryCollectionNew.add(entryCollectionNewEntryToAttach);
            }
            entryCollectionNew = attachedEntryCollectionNew;
            mould.setEntryCollection(entryCollectionNew);
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            mould.setProductCollection(productCollectionNew);
            mould = em.merge(mould);
            for (Entry entryCollectionOldEntry : entryCollectionOld) {
                if (!entryCollectionNew.contains(entryCollectionOldEntry)) {
                    entryCollectionOldEntry.setMouldId(null);
                    entryCollectionOldEntry = em.merge(entryCollectionOldEntry);
                }
            }
            for (Entry entryCollectionNewEntry : entryCollectionNew) {
                if (!entryCollectionOld.contains(entryCollectionNewEntry)) {
                    Mould oldMouldIdOfEntryCollectionNewEntry = entryCollectionNewEntry.getMouldId();
                    entryCollectionNewEntry.setMouldId(mould);
                    entryCollectionNewEntry = em.merge(entryCollectionNewEntry);
                    if (oldMouldIdOfEntryCollectionNewEntry != null && !oldMouldIdOfEntryCollectionNewEntry.equals(mould)) {
                        oldMouldIdOfEntryCollectionNewEntry.getEntryCollection().remove(entryCollectionNewEntry);
                        oldMouldIdOfEntryCollectionNewEntry = em.merge(oldMouldIdOfEntryCollectionNewEntry);
                    }
                }
            }
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setMouldId(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Mould oldMouldIdOfProductCollectionNewProduct = productCollectionNewProduct.getMouldId();
                    productCollectionNewProduct.setMouldId(mould);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldMouldIdOfProductCollectionNewProduct != null && !oldMouldIdOfProductCollectionNewProduct.equals(mould)) {
                        oldMouldIdOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldMouldIdOfProductCollectionNewProduct = em.merge(oldMouldIdOfProductCollectionNewProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mould.getId();
                if (findMould(id) == null) {
                    throw new NonexistentEntityException("The mould with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mould mould;
            try {
                mould = em.getReference(Mould.class, id);
                mould.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mould with id " + id + " no longer exists.", enfe);
            }
            Collection<Entry> entryCollection = mould.getEntryCollection();
            for (Entry entryCollectionEntry : entryCollection) {
                entryCollectionEntry.setMouldId(null);
                entryCollectionEntry = em.merge(entryCollectionEntry);
            }
            Collection<Product> productCollection = mould.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setMouldId(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(mould);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mould> findMouldEntities() {
        return findMouldEntities(true, -1, -1);
    }

    public List<Mould> findMouldEntities(int maxResults, int firstResult) {
        return findMouldEntities(false, maxResults, firstResult);
    }

    private List<Mould> findMouldEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mould.class));
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

    public Mould findMould(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mould.class, id);
        } finally {
            em.close();
        }
    }

    public int getMouldCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mould> rt = cq.from(Mould.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
