/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Additive;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.cch.aj.entryrecorder.entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class AdditiveJpaController1 implements Serializable {

    public AdditiveJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Additive additive) {
        if (additive.getProductCollection() == null) {
            additive.setProductCollection(new ArrayList<Product>());
        }
        if (additive.getProductCollection1() == null) {
            additive.setProductCollection1(new ArrayList<Product>());
        }
        if (additive.getProductCollection2() == null) {
            additive.setProductCollection2(new ArrayList<Product>());
        }
        if (additive.getEntryCollection() == null) {
            additive.setEntryCollection(new ArrayList<Entry>());
        }
        if (additive.getEntryCollection1() == null) {
            additive.setEntryCollection1(new ArrayList<Entry>());
        }
        if (additive.getEntryCollection2() == null) {
            additive.setEntryCollection2(new ArrayList<Entry>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : additive.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            additive.setProductCollection(attachedProductCollection);
            Collection<Product> attachedProductCollection1 = new ArrayList<Product>();
            for (Product productCollection1ProductToAttach : additive.getProductCollection1()) {
                productCollection1ProductToAttach = em.getReference(productCollection1ProductToAttach.getClass(), productCollection1ProductToAttach.getId());
                attachedProductCollection1.add(productCollection1ProductToAttach);
            }
            additive.setProductCollection1(attachedProductCollection1);
            Collection<Product> attachedProductCollection2 = new ArrayList<Product>();
            for (Product productCollection2ProductToAttach : additive.getProductCollection2()) {
                productCollection2ProductToAttach = em.getReference(productCollection2ProductToAttach.getClass(), productCollection2ProductToAttach.getId());
                attachedProductCollection2.add(productCollection2ProductToAttach);
            }
            additive.setProductCollection2(attachedProductCollection2);
            Collection<Entry> attachedEntryCollection = new ArrayList<Entry>();
            for (Entry entryCollectionEntryToAttach : additive.getEntryCollection()) {
                entryCollectionEntryToAttach = em.getReference(entryCollectionEntryToAttach.getClass(), entryCollectionEntryToAttach.getId());
                attachedEntryCollection.add(entryCollectionEntryToAttach);
            }
            additive.setEntryCollection(attachedEntryCollection);
            Collection<Entry> attachedEntryCollection1 = new ArrayList<Entry>();
            for (Entry entryCollection1EntryToAttach : additive.getEntryCollection1()) {
                entryCollection1EntryToAttach = em.getReference(entryCollection1EntryToAttach.getClass(), entryCollection1EntryToAttach.getId());
                attachedEntryCollection1.add(entryCollection1EntryToAttach);
            }
            additive.setEntryCollection1(attachedEntryCollection1);
            Collection<Entry> attachedEntryCollection2 = new ArrayList<Entry>();
            for (Entry entryCollection2EntryToAttach : additive.getEntryCollection2()) {
                entryCollection2EntryToAttach = em.getReference(entryCollection2EntryToAttach.getClass(), entryCollection2EntryToAttach.getId());
                attachedEntryCollection2.add(entryCollection2EntryToAttach);
            }
            additive.setEntryCollection2(attachedEntryCollection2);
            em.persist(additive);
            for (Product productCollectionProduct : additive.getProductCollection()) {
                Additive oldAdditiveAIdOfProductCollectionProduct = productCollectionProduct.getAdditiveAId();
                productCollectionProduct.setAdditiveAId(additive);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldAdditiveAIdOfProductCollectionProduct != null) {
                    oldAdditiveAIdOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldAdditiveAIdOfProductCollectionProduct = em.merge(oldAdditiveAIdOfProductCollectionProduct);
                }
            }
            for (Product productCollection1Product : additive.getProductCollection1()) {
                Additive oldAdditiveBIdOfProductCollection1Product = productCollection1Product.getAdditiveBId();
                productCollection1Product.setAdditiveBId(additive);
                productCollection1Product = em.merge(productCollection1Product);
                if (oldAdditiveBIdOfProductCollection1Product != null) {
                    oldAdditiveBIdOfProductCollection1Product.getProductCollection1().remove(productCollection1Product);
                    oldAdditiveBIdOfProductCollection1Product = em.merge(oldAdditiveBIdOfProductCollection1Product);
                }
            }
            for (Product productCollection2Product : additive.getProductCollection2()) {
                Additive oldAdditiveCIdOfProductCollection2Product = productCollection2Product.getAdditiveCId();
                productCollection2Product.setAdditiveCId(additive);
                productCollection2Product = em.merge(productCollection2Product);
                if (oldAdditiveCIdOfProductCollection2Product != null) {
                    oldAdditiveCIdOfProductCollection2Product.getProductCollection2().remove(productCollection2Product);
                    oldAdditiveCIdOfProductCollection2Product = em.merge(oldAdditiveCIdOfProductCollection2Product);
                }
            }
            for (Entry entryCollectionEntry : additive.getEntryCollection()) {
                Additive oldAdditiveCIdOfEntryCollectionEntry = entryCollectionEntry.getAdditiveCId();
                entryCollectionEntry.setAdditiveCId(additive);
                entryCollectionEntry = em.merge(entryCollectionEntry);
                if (oldAdditiveCIdOfEntryCollectionEntry != null) {
                    oldAdditiveCIdOfEntryCollectionEntry.getEntryCollection().remove(entryCollectionEntry);
                    oldAdditiveCIdOfEntryCollectionEntry = em.merge(oldAdditiveCIdOfEntryCollectionEntry);
                }
            }
            for (Entry entryCollection1Entry : additive.getEntryCollection1()) {
                Additive oldAdditiveAIdOfEntryCollection1Entry = entryCollection1Entry.getAdditiveAId();
                entryCollection1Entry.setAdditiveAId(additive);
                entryCollection1Entry = em.merge(entryCollection1Entry);
                if (oldAdditiveAIdOfEntryCollection1Entry != null) {
                    oldAdditiveAIdOfEntryCollection1Entry.getEntryCollection1().remove(entryCollection1Entry);
                    oldAdditiveAIdOfEntryCollection1Entry = em.merge(oldAdditiveAIdOfEntryCollection1Entry);
                }
            }
            for (Entry entryCollection2Entry : additive.getEntryCollection2()) {
                Additive oldAdditiveBIdOfEntryCollection2Entry = entryCollection2Entry.getAdditiveBId();
                entryCollection2Entry.setAdditiveBId(additive);
                entryCollection2Entry = em.merge(entryCollection2Entry);
                if (oldAdditiveBIdOfEntryCollection2Entry != null) {
                    oldAdditiveBIdOfEntryCollection2Entry.getEntryCollection2().remove(entryCollection2Entry);
                    oldAdditiveBIdOfEntryCollection2Entry = em.merge(oldAdditiveBIdOfEntryCollection2Entry);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Additive additive) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Additive persistentAdditive = em.find(Additive.class, additive.getId());
            Collection<Product> productCollectionOld = persistentAdditive.getProductCollection();
            Collection<Product> productCollectionNew = additive.getProductCollection();
            Collection<Product> productCollection1Old = persistentAdditive.getProductCollection1();
            Collection<Product> productCollection1New = additive.getProductCollection1();
            Collection<Product> productCollection2Old = persistentAdditive.getProductCollection2();
            Collection<Product> productCollection2New = additive.getProductCollection2();
            Collection<Entry> entryCollectionOld = persistentAdditive.getEntryCollection();
            Collection<Entry> entryCollectionNew = additive.getEntryCollection();
            Collection<Entry> entryCollection1Old = persistentAdditive.getEntryCollection1();
            Collection<Entry> entryCollection1New = additive.getEntryCollection1();
            Collection<Entry> entryCollection2Old = persistentAdditive.getEntryCollection2();
            Collection<Entry> entryCollection2New = additive.getEntryCollection2();
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            additive.setProductCollection(productCollectionNew);
            Collection<Product> attachedProductCollection1New = new ArrayList<Product>();
            for (Product productCollection1NewProductToAttach : productCollection1New) {
                productCollection1NewProductToAttach = em.getReference(productCollection1NewProductToAttach.getClass(), productCollection1NewProductToAttach.getId());
                attachedProductCollection1New.add(productCollection1NewProductToAttach);
            }
            productCollection1New = attachedProductCollection1New;
            additive.setProductCollection1(productCollection1New);
            Collection<Product> attachedProductCollection2New = new ArrayList<Product>();
            for (Product productCollection2NewProductToAttach : productCollection2New) {
                productCollection2NewProductToAttach = em.getReference(productCollection2NewProductToAttach.getClass(), productCollection2NewProductToAttach.getId());
                attachedProductCollection2New.add(productCollection2NewProductToAttach);
            }
            productCollection2New = attachedProductCollection2New;
            additive.setProductCollection2(productCollection2New);
            Collection<Entry> attachedEntryCollectionNew = new ArrayList<Entry>();
            for (Entry entryCollectionNewEntryToAttach : entryCollectionNew) {
                entryCollectionNewEntryToAttach = em.getReference(entryCollectionNewEntryToAttach.getClass(), entryCollectionNewEntryToAttach.getId());
                attachedEntryCollectionNew.add(entryCollectionNewEntryToAttach);
            }
            entryCollectionNew = attachedEntryCollectionNew;
            additive.setEntryCollection(entryCollectionNew);
            Collection<Entry> attachedEntryCollection1New = new ArrayList<Entry>();
            for (Entry entryCollection1NewEntryToAttach : entryCollection1New) {
                entryCollection1NewEntryToAttach = em.getReference(entryCollection1NewEntryToAttach.getClass(), entryCollection1NewEntryToAttach.getId());
                attachedEntryCollection1New.add(entryCollection1NewEntryToAttach);
            }
            entryCollection1New = attachedEntryCollection1New;
            additive.setEntryCollection1(entryCollection1New);
            Collection<Entry> attachedEntryCollection2New = new ArrayList<Entry>();
            for (Entry entryCollection2NewEntryToAttach : entryCollection2New) {
                entryCollection2NewEntryToAttach = em.getReference(entryCollection2NewEntryToAttach.getClass(), entryCollection2NewEntryToAttach.getId());
                attachedEntryCollection2New.add(entryCollection2NewEntryToAttach);
            }
            entryCollection2New = attachedEntryCollection2New;
            additive.setEntryCollection2(entryCollection2New);
            additive = em.merge(additive);
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.setAdditiveAId(null);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Additive oldAdditiveAIdOfProductCollectionNewProduct = productCollectionNewProduct.getAdditiveAId();
                    productCollectionNewProduct.setAdditiveAId(additive);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldAdditiveAIdOfProductCollectionNewProduct != null && !oldAdditiveAIdOfProductCollectionNewProduct.equals(additive)) {
                        oldAdditiveAIdOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldAdditiveAIdOfProductCollectionNewProduct = em.merge(oldAdditiveAIdOfProductCollectionNewProduct);
                    }
                }
            }
            for (Product productCollection1OldProduct : productCollection1Old) {
                if (!productCollection1New.contains(productCollection1OldProduct)) {
                    productCollection1OldProduct.setAdditiveBId(null);
                    productCollection1OldProduct = em.merge(productCollection1OldProduct);
                }
            }
            for (Product productCollection1NewProduct : productCollection1New) {
                if (!productCollection1Old.contains(productCollection1NewProduct)) {
                    Additive oldAdditiveBIdOfProductCollection1NewProduct = productCollection1NewProduct.getAdditiveBId();
                    productCollection1NewProduct.setAdditiveBId(additive);
                    productCollection1NewProduct = em.merge(productCollection1NewProduct);
                    if (oldAdditiveBIdOfProductCollection1NewProduct != null && !oldAdditiveBIdOfProductCollection1NewProduct.equals(additive)) {
                        oldAdditiveBIdOfProductCollection1NewProduct.getProductCollection1().remove(productCollection1NewProduct);
                        oldAdditiveBIdOfProductCollection1NewProduct = em.merge(oldAdditiveBIdOfProductCollection1NewProduct);
                    }
                }
            }
            for (Product productCollection2OldProduct : productCollection2Old) {
                if (!productCollection2New.contains(productCollection2OldProduct)) {
                    productCollection2OldProduct.setAdditiveCId(null);
                    productCollection2OldProduct = em.merge(productCollection2OldProduct);
                }
            }
            for (Product productCollection2NewProduct : productCollection2New) {
                if (!productCollection2Old.contains(productCollection2NewProduct)) {
                    Additive oldAdditiveCIdOfProductCollection2NewProduct = productCollection2NewProduct.getAdditiveCId();
                    productCollection2NewProduct.setAdditiveCId(additive);
                    productCollection2NewProduct = em.merge(productCollection2NewProduct);
                    if (oldAdditiveCIdOfProductCollection2NewProduct != null && !oldAdditiveCIdOfProductCollection2NewProduct.equals(additive)) {
                        oldAdditiveCIdOfProductCollection2NewProduct.getProductCollection2().remove(productCollection2NewProduct);
                        oldAdditiveCIdOfProductCollection2NewProduct = em.merge(oldAdditiveCIdOfProductCollection2NewProduct);
                    }
                }
            }
            for (Entry entryCollectionOldEntry : entryCollectionOld) {
                if (!entryCollectionNew.contains(entryCollectionOldEntry)) {
                    entryCollectionOldEntry.setAdditiveCId(null);
                    entryCollectionOldEntry = em.merge(entryCollectionOldEntry);
                }
            }
            for (Entry entryCollectionNewEntry : entryCollectionNew) {
                if (!entryCollectionOld.contains(entryCollectionNewEntry)) {
                    Additive oldAdditiveCIdOfEntryCollectionNewEntry = entryCollectionNewEntry.getAdditiveCId();
                    entryCollectionNewEntry.setAdditiveCId(additive);
                    entryCollectionNewEntry = em.merge(entryCollectionNewEntry);
                    if (oldAdditiveCIdOfEntryCollectionNewEntry != null && !oldAdditiveCIdOfEntryCollectionNewEntry.equals(additive)) {
                        oldAdditiveCIdOfEntryCollectionNewEntry.getEntryCollection().remove(entryCollectionNewEntry);
                        oldAdditiveCIdOfEntryCollectionNewEntry = em.merge(oldAdditiveCIdOfEntryCollectionNewEntry);
                    }
                }
            }
            for (Entry entryCollection1OldEntry : entryCollection1Old) {
                if (!entryCollection1New.contains(entryCollection1OldEntry)) {
                    entryCollection1OldEntry.setAdditiveAId(null);
                    entryCollection1OldEntry = em.merge(entryCollection1OldEntry);
                }
            }
            for (Entry entryCollection1NewEntry : entryCollection1New) {
                if (!entryCollection1Old.contains(entryCollection1NewEntry)) {
                    Additive oldAdditiveAIdOfEntryCollection1NewEntry = entryCollection1NewEntry.getAdditiveAId();
                    entryCollection1NewEntry.setAdditiveAId(additive);
                    entryCollection1NewEntry = em.merge(entryCollection1NewEntry);
                    if (oldAdditiveAIdOfEntryCollection1NewEntry != null && !oldAdditiveAIdOfEntryCollection1NewEntry.equals(additive)) {
                        oldAdditiveAIdOfEntryCollection1NewEntry.getEntryCollection1().remove(entryCollection1NewEntry);
                        oldAdditiveAIdOfEntryCollection1NewEntry = em.merge(oldAdditiveAIdOfEntryCollection1NewEntry);
                    }
                }
            }
            for (Entry entryCollection2OldEntry : entryCollection2Old) {
                if (!entryCollection2New.contains(entryCollection2OldEntry)) {
                    entryCollection2OldEntry.setAdditiveBId(null);
                    entryCollection2OldEntry = em.merge(entryCollection2OldEntry);
                }
            }
            for (Entry entryCollection2NewEntry : entryCollection2New) {
                if (!entryCollection2Old.contains(entryCollection2NewEntry)) {
                    Additive oldAdditiveBIdOfEntryCollection2NewEntry = entryCollection2NewEntry.getAdditiveBId();
                    entryCollection2NewEntry.setAdditiveBId(additive);
                    entryCollection2NewEntry = em.merge(entryCollection2NewEntry);
                    if (oldAdditiveBIdOfEntryCollection2NewEntry != null && !oldAdditiveBIdOfEntryCollection2NewEntry.equals(additive)) {
                        oldAdditiveBIdOfEntryCollection2NewEntry.getEntryCollection2().remove(entryCollection2NewEntry);
                        oldAdditiveBIdOfEntryCollection2NewEntry = em.merge(oldAdditiveBIdOfEntryCollection2NewEntry);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = additive.getId();
                if (findAdditive(id) == null) {
                    throw new NonexistentEntityException("The additive with id " + id + " no longer exists.");
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
            Additive additive;
            try {
                additive = em.getReference(Additive.class, id);
                additive.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The additive with id " + id + " no longer exists.", enfe);
            }
            Collection<Product> productCollection = additive.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.setAdditiveAId(null);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            Collection<Product> productCollection1 = additive.getProductCollection1();
            for (Product productCollection1Product : productCollection1) {
                productCollection1Product.setAdditiveBId(null);
                productCollection1Product = em.merge(productCollection1Product);
            }
            Collection<Product> productCollection2 = additive.getProductCollection2();
            for (Product productCollection2Product : productCollection2) {
                productCollection2Product.setAdditiveCId(null);
                productCollection2Product = em.merge(productCollection2Product);
            }
            Collection<Entry> entryCollection = additive.getEntryCollection();
            for (Entry entryCollectionEntry : entryCollection) {
                entryCollectionEntry.setAdditiveCId(null);
                entryCollectionEntry = em.merge(entryCollectionEntry);
            }
            Collection<Entry> entryCollection1 = additive.getEntryCollection1();
            for (Entry entryCollection1Entry : entryCollection1) {
                entryCollection1Entry.setAdditiveAId(null);
                entryCollection1Entry = em.merge(entryCollection1Entry);
            }
            Collection<Entry> entryCollection2 = additive.getEntryCollection2();
            for (Entry entryCollection2Entry : entryCollection2) {
                entryCollection2Entry.setAdditiveBId(null);
                entryCollection2Entry = em.merge(entryCollection2Entry);
            }
            em.remove(additive);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Additive> findAdditiveEntities() {
        return findAdditiveEntities(true, -1, -1);
    }

    public List<Additive> findAdditiveEntities(int maxResults, int firstResult) {
        return findAdditiveEntities(false, maxResults, firstResult);
    }

    private List<Additive> findAdditiveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Additive.class));
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

    public Additive findAdditive(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Additive.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdditiveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Additive> rt = cq.from(Additive.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
