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
import com.cch.aj.entryrecorder.entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class PolymerJpaController1 implements Serializable {

    public PolymerJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Polymer polymer) {
        if (polymer.getProductCollection() == null) {
            polymer.setProductCollection(new ArrayList<Product>());
        }
        if (polymer.getEntryCollection() == null) {
            polymer.setEntryCollection(new ArrayList<Entry>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : polymer.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            polymer.setProductCollection(attachedProductCollection);
            Collection<Entry> attachedEntryCollection = new ArrayList<Entry>();
            for (Entry entryCollectionEntryToAttach : polymer.getEntryCollection()) {
                entryCollectionEntryToAttach = em.getReference(entryCollectionEntryToAttach.getClass(), entryCollectionEntryToAttach.getId());
                attachedEntryCollection.add(entryCollectionEntryToAttach);
            }
            polymer.setEntryCollection(attachedEntryCollection);
            em.persist(polymer);
            for (Product productCollectionProduct : polymer.getProductCollection()) {
                Polymer oldPolymerIdOfProductCollectionProduct = productCollectionProduct.getPolymerId();
                productCollectionProduct.setPolymerId(polymer);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldPolymerIdOfProductCollectionProduct != null) {
                    oldPolymerIdOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldPolymerIdOfProductCollectionProduct = em.merge(oldPolymerIdOfProductCollectionProduct);
                }
            }
            for (Entry entryCollectionEntry : polymer.getEntryCollection()) {
                Polymer oldPolymerIdOfEntryCollectionEntry = entryCollectionEntry.getPolymerId();
                entryCollectionEntry.setPolymerId(polymer);
                entryCollectionEntry = em.merge(entryCollectionEntry);
                if (oldPolymerIdOfEntryCollectionEntry != null) {
                    oldPolymerIdOfEntryCollectionEntry.getEntryCollection().remove(entryCollectionEntry);
                    oldPolymerIdOfEntryCollectionEntry = em.merge(oldPolymerIdOfEntryCollectionEntry);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Polymer polymer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Polymer persistentPolymer = em.find(Polymer.class, polymer.getId());
            Collection<Product> productCollectionOld = persistentPolymer.getProductCollection();
            Collection<Product> productCollectionNew = polymer.getProductCollection();
            Collection<Entry> entryCollectionOld = persistentPolymer.getEntryCollection();
            Collection<Entry> entryCollectionNew = polymer.getEntryCollection();
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            polymer.setProductCollection(productCollectionNew);
            Collection<Entry> attachedEntryCollectionNew = new ArrayList<Entry>();
            for (Entry entryCollectionNewEntryToAttach : entryCollectionNew) {
                entryCollectionNewEntryToAttach = em.getReference(entryCollectionNewEntryToAttach.getClass(), entryCollectionNewEntryToAttach.getId());
                attachedEntryCollectionNew.add(entryCollectionNewEntryToAttach);
            }
            entryCollectionNew = attachedEntryCollectionNew;
            polymer.setEntryCollection(entryCollectionNew);
            polymer = em.merge(polymer);
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setPolymerId(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Polymer oldPolymerIdOfProductCollectionNewProduct = productCollectionNewProduct.getPolymerId();
                    productCollectionNewProduct.setPolymerId(polymer);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldPolymerIdOfProductCollectionNewProduct != null && !oldPolymerIdOfProductCollectionNewProduct.equals(polymer)) {
                        oldPolymerIdOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldPolymerIdOfProductCollectionNewProduct = em.merge(oldPolymerIdOfProductCollectionNewProduct);
                    }
                }
            }
            for (Entry entryCollectionOldEntry : entryCollectionOld) {
                if (!entryCollectionNew.contains(entryCollectionOldEntry)) {
                    entryCollectionOldEntry.setPolymerId(null);
                    entryCollectionOldEntry = em.merge(entryCollectionOldEntry);
                }
            }
            for (Entry entryCollectionNewEntry : entryCollectionNew) {
                if (!entryCollectionOld.contains(entryCollectionNewEntry)) {
                    Polymer oldPolymerIdOfEntryCollectionNewEntry = entryCollectionNewEntry.getPolymerId();
                    entryCollectionNewEntry.setPolymerId(polymer);
                    entryCollectionNewEntry = em.merge(entryCollectionNewEntry);
                    if (oldPolymerIdOfEntryCollectionNewEntry != null && !oldPolymerIdOfEntryCollectionNewEntry.equals(polymer)) {
                        oldPolymerIdOfEntryCollectionNewEntry.getEntryCollection().remove(entryCollectionNewEntry);
                        oldPolymerIdOfEntryCollectionNewEntry = em.merge(oldPolymerIdOfEntryCollectionNewEntry);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = polymer.getId();
                if (findPolymer(id) == null) {
                    throw new NonexistentEntityException("The polymer with id " + id + " no longer exists.");
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
            Polymer polymer;
            try {
                polymer = em.getReference(Polymer.class, id);
                polymer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The polymer with id " + id + " no longer exists.", enfe);
            }
            Collection<Product> productCollection = polymer.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setPolymerId(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            Collection<Entry> entryCollection = polymer.getEntryCollection();
            for (Entry entryCollectionEntry : entryCollection) {
                entryCollectionEntry.setPolymerId(null);
                entryCollectionEntry = em.merge(entryCollectionEntry);
            }
            em.remove(polymer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Polymer> findPolymerEntities() {
        return findPolymerEntities(true, -1, -1);
    }

    public List<Polymer> findPolymerEntities(int maxResults, int firstResult) {
        return findPolymerEntities(false, maxResults, firstResult);
    }

    private List<Polymer> findPolymerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Polymer.class));
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

    public Polymer findPolymer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Polymer.class, id);
        } finally {
            em.close();
        }
    }

    public int getPolymerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Polymer> rt = cq.from(Polymer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
