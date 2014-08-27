/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Checkitem;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class CheckitemJpaController implements Serializable {

    public CheckitemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Checkitem checkitem) {
        if (checkitem.getProductCollection() == null) {
            checkitem.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : checkitem.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            checkitem.setProductCollection(attachedProductCollection);
            em.persist(checkitem);
            for (Product productCollectionProduct : checkitem.getProductCollection()) {
                productCollectionProduct.getCheckitemCollection().add(checkitem);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Checkitem checkitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Checkitem persistentCheckitem = em.find(Checkitem.class, checkitem.getId());
            Collection<Product> productCollectionOld = persistentCheckitem.getProductCollection();
            Collection<Product> productCollectionNew = checkitem.getProductCollection();
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            checkitem.setProductCollection(productCollectionNew);
            checkitem = em.merge(checkitem);
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.getCheckitemCollection().remove(checkitem);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    productCollectionNewProduct.getCheckitemCollection().add(checkitem);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = checkitem.getId();
                if (findCheckitem(id) == null) {
                    throw new NonexistentEntityException("The checkitem with id " + id + " no longer exists.");
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
            Checkitem checkitem;
            try {
                checkitem = em.getReference(Checkitem.class, id);
                checkitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The checkitem with id " + id + " no longer exists.", enfe);
            }
            Collection<Product> productCollection = checkitem.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.getCheckitemCollection().remove(checkitem);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(checkitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Checkitem> findCheckitemEntities() {
        return findCheckitemEntities(true, -1, -1);
    }

    public List<Checkitem> findCheckitemEntities(int maxResults, int firstResult) {
        return findCheckitemEntities(false, maxResults, firstResult);
    }

    private List<Checkitem> findCheckitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Checkitem.class));
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

    public Checkitem findCheckitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Checkitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getCheckitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Checkitem> rt = cq.from(Checkitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
