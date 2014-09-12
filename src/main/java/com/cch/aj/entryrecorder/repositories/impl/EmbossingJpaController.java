/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Embossing;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.IllegalOrphanException;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class EmbossingJpaController implements Serializable {

    public EmbossingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Embossing embossing) throws PreexistingEntityException, Exception {
        if (embossing.getProductCollection() == null) {
            embossing.setProductCollection(new ArrayList<Product>());
        }
        if (embossing.getProductCollection1() == null) {
            embossing.setProductCollection1(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : embossing.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            embossing.setProductCollection(attachedProductCollection);
            Collection<Product> attachedProductCollection1 = new ArrayList<Product>();
            for (Product productCollection1ProductToAttach : embossing.getProductCollection1()) {
                productCollection1ProductToAttach = em.getReference(productCollection1ProductToAttach.getClass(), productCollection1ProductToAttach.getId());
                attachedProductCollection1.add(productCollection1ProductToAttach);
            }
            embossing.setProductCollection1(attachedProductCollection1);
            em.persist(embossing);
            for (Product productCollectionProduct : embossing.getProductCollection()) {
                Embossing oldEmbossingIdOfProductCollectionProduct = productCollectionProduct.getEmbossingId();
                productCollectionProduct.setEmbossingId(embossing);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldEmbossingIdOfProductCollectionProduct != null) {
                    oldEmbossingIdOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldEmbossingIdOfProductCollectionProduct = em.merge(oldEmbossingIdOfProductCollectionProduct);
                }
            }
            for (Product productCollection1Product : embossing.getProductCollection1()) {
                Embossing oldInsertIdOfProductCollection1Product = productCollection1Product.getInsertId();
                productCollection1Product.setInsertId(embossing);
                productCollection1Product = em.merge(productCollection1Product);
                if (oldInsertIdOfProductCollection1Product != null) {
                    oldInsertIdOfProductCollection1Product.getProductCollection1().remove(productCollection1Product);
                    oldInsertIdOfProductCollection1Product = em.merge(oldInsertIdOfProductCollection1Product);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmbossing(embossing.getId()) != null) {
                throw new PreexistingEntityException("Embossing " + embossing + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Embossing embossing) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Embossing persistentEmbossing = em.find(Embossing.class, embossing.getId());
            Collection<Product> productCollectionOld = persistentEmbossing.getProductCollection();
            Collection<Product> productCollectionNew = embossing.getProductCollection();
            Collection<Product> productCollection1Old = persistentEmbossing.getProductCollection1();
            Collection<Product> productCollection1New = embossing.getProductCollection1();
            List<String> illegalOrphanMessages = null;
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productCollectionOldProduct + " since its embossingId field is not nullable.");
                }
            }
            for (Product productCollection1OldProduct : productCollection1Old) {
                if (!productCollection1New.contains(productCollection1OldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productCollection1OldProduct + " since its insertId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            embossing.setProductCollection(productCollectionNew);
            Collection<Product> attachedProductCollection1New = new ArrayList<Product>();
            for (Product productCollection1NewProductToAttach : productCollection1New) {
                productCollection1NewProductToAttach = em.getReference(productCollection1NewProductToAttach.getClass(), productCollection1NewProductToAttach.getId());
                attachedProductCollection1New.add(productCollection1NewProductToAttach);
            }
            productCollection1New = attachedProductCollection1New;
            embossing.setProductCollection1(productCollection1New);
            embossing = em.merge(embossing);
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Embossing oldEmbossingIdOfProductCollectionNewProduct = productCollectionNewProduct.getEmbossingId();
                    productCollectionNewProduct.setEmbossingId(embossing);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldEmbossingIdOfProductCollectionNewProduct != null && !oldEmbossingIdOfProductCollectionNewProduct.equals(embossing)) {
                        oldEmbossingIdOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldEmbossingIdOfProductCollectionNewProduct = em.merge(oldEmbossingIdOfProductCollectionNewProduct);
                    }
                }
            }
            for (Product productCollection1NewProduct : productCollection1New) {
                if (!productCollection1Old.contains(productCollection1NewProduct)) {
                    Embossing oldInsertIdOfProductCollection1NewProduct = productCollection1NewProduct.getInsertId();
                    productCollection1NewProduct.setInsertId(embossing);
                    productCollection1NewProduct = em.merge(productCollection1NewProduct);
                    if (oldInsertIdOfProductCollection1NewProduct != null && !oldInsertIdOfProductCollection1NewProduct.equals(embossing)) {
                        oldInsertIdOfProductCollection1NewProduct.getProductCollection1().remove(productCollection1NewProduct);
                        oldInsertIdOfProductCollection1NewProduct = em.merge(oldInsertIdOfProductCollection1NewProduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = embossing.getId();
                if (findEmbossing(id) == null) {
                    throw new NonexistentEntityException("The embossing with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Embossing embossing;
            try {
                embossing = em.getReference(Embossing.class, id);
                embossing.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The embossing with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Product> productCollectionOrphanCheck = embossing.getProductCollection();
            for (Product productCollectionOrphanCheckProduct : productCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Embossing (" + embossing + ") cannot be destroyed since the Product " + productCollectionOrphanCheckProduct + " in its productCollection field has a non-nullable embossingId field.");
            }
            Collection<Product> productCollection1OrphanCheck = embossing.getProductCollection1();
            for (Product productCollection1OrphanCheckProduct : productCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Embossing (" + embossing + ") cannot be destroyed since the Product " + productCollection1OrphanCheckProduct + " in its productCollection1 field has a non-nullable insertId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(embossing);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Embossing> findEmbossingEntities() {
        return findEmbossingEntities(true, -1, -1);
    }

    public List<Embossing> findEmbossingEntities(int maxResults, int firstResult) {
        return findEmbossingEntities(false, maxResults, firstResult);
    }

    private List<Embossing> findEmbossingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Embossing.class));
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

    public Embossing findEmbossing(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Embossing.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmbossingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Embossing> rt = cq.from(Embossing.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
