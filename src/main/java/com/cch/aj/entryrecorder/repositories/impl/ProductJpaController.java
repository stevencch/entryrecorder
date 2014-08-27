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
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Checkitem;
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
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getCheckitemCollection() == null) {
            product.setCheckitemCollection(new ArrayList<Checkitem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mould mouldId = product.getMouldId();
            if (mouldId != null) {
                mouldId = em.getReference(mouldId.getClass(), mouldId.getId());
                product.setMouldId(mouldId);
            }
            Polymer polymerId = product.getPolymerId();
            if (polymerId != null) {
                polymerId = em.getReference(polymerId.getClass(), polymerId.getId());
                product.setPolymerId(polymerId);
            }
            Additive additiveAId = product.getAdditiveAId();
            if (additiveAId != null) {
                additiveAId = em.getReference(additiveAId.getClass(), additiveAId.getId());
                product.setAdditiveAId(additiveAId);
            }
            Additive additiveBId = product.getAdditiveBId();
            if (additiveBId != null) {
                additiveBId = em.getReference(additiveBId.getClass(), additiveBId.getId());
                product.setAdditiveBId(additiveBId);
            }
            Additive additiveCId = product.getAdditiveCId();
            if (additiveCId != null) {
                additiveCId = em.getReference(additiveCId.getClass(), additiveCId.getId());
                product.setAdditiveCId(additiveCId);
            }
            Collection<Checkitem> attachedCheckitemCollection = new ArrayList<Checkitem>();
            for (Checkitem checkitemCollectionCheckitemToAttach : product.getCheckitemCollection()) {
                checkitemCollectionCheckitemToAttach = em.getReference(checkitemCollectionCheckitemToAttach.getClass(), checkitemCollectionCheckitemToAttach.getId());
                attachedCheckitemCollection.add(checkitemCollectionCheckitemToAttach);
            }
            product.setCheckitemCollection(attachedCheckitemCollection);
            em.persist(product);
            if (mouldId != null) {
                mouldId.getProductCollection().add(product);
                mouldId = em.merge(mouldId);
            }
            if (polymerId != null) {
                polymerId.getProductCollection().add(product);
                polymerId = em.merge(polymerId);
            }
            if (additiveAId != null) {
                additiveAId.getProductCollection().add(product);
                additiveAId = em.merge(additiveAId);
            }
            if (additiveBId != null) {
                additiveBId.getProductCollection().add(product);
                additiveBId = em.merge(additiveBId);
            }
            if (additiveCId != null) {
                additiveCId.getProductCollection().add(product);
                additiveCId = em.merge(additiveCId);
            }
            for (Checkitem checkitemCollectionCheckitem : product.getCheckitemCollection()) {
                checkitemCollectionCheckitem.getProductCollection().add(product);
                checkitemCollectionCheckitem = em.merge(checkitemCollectionCheckitem);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Mould mouldIdOld = persistentProduct.getMouldId();
            Mould mouldIdNew = product.getMouldId();
            Polymer polymerIdOld = persistentProduct.getPolymerId();
            Polymer polymerIdNew = product.getPolymerId();
            Additive additiveAIdOld = persistentProduct.getAdditiveAId();
            Additive additiveAIdNew = product.getAdditiveAId();
            Additive additiveBIdOld = persistentProduct.getAdditiveBId();
            Additive additiveBIdNew = product.getAdditiveBId();
            Additive additiveCIdOld = persistentProduct.getAdditiveCId();
            Additive additiveCIdNew = product.getAdditiveCId();
            Collection<Checkitem> checkitemCollectionOld = persistentProduct.getCheckitemCollection();
            Collection<Checkitem> checkitemCollectionNew = product.getCheckitemCollection();
            if (mouldIdNew != null) {
                mouldIdNew = em.getReference(mouldIdNew.getClass(), mouldIdNew.getId());
                product.setMouldId(mouldIdNew);
            }
            if (polymerIdNew != null) {
                polymerIdNew = em.getReference(polymerIdNew.getClass(), polymerIdNew.getId());
                product.setPolymerId(polymerIdNew);
            }
            if (additiveAIdNew != null) {
                additiveAIdNew = em.getReference(additiveAIdNew.getClass(), additiveAIdNew.getId());
                product.setAdditiveAId(additiveAIdNew);
            }
            if (additiveBIdNew != null) {
                additiveBIdNew = em.getReference(additiveBIdNew.getClass(), additiveBIdNew.getId());
                product.setAdditiveBId(additiveBIdNew);
            }
            if (additiveCIdNew != null) {
                additiveCIdNew = em.getReference(additiveCIdNew.getClass(), additiveCIdNew.getId());
                product.setAdditiveCId(additiveCIdNew);
            }
            Collection<Checkitem> attachedCheckitemCollectionNew = new ArrayList<Checkitem>();
            for (Checkitem checkitemCollectionNewCheckitemToAttach : checkitemCollectionNew) {
                checkitemCollectionNewCheckitemToAttach = em.getReference(checkitemCollectionNewCheckitemToAttach.getClass(), checkitemCollectionNewCheckitemToAttach.getId());
                attachedCheckitemCollectionNew.add(checkitemCollectionNewCheckitemToAttach);
            }
            checkitemCollectionNew = attachedCheckitemCollectionNew;
            product.setCheckitemCollection(checkitemCollectionNew);
            product = em.merge(product);
            if (mouldIdOld != null && !mouldIdOld.equals(mouldIdNew)) {
                mouldIdOld.getProductCollection().remove(product);
                mouldIdOld = em.merge(mouldIdOld);
            }
            if (mouldIdNew != null && !mouldIdNew.equals(mouldIdOld)) {
                mouldIdNew.getProductCollection().add(product);
                mouldIdNew = em.merge(mouldIdNew);
            }
            if (polymerIdOld != null && !polymerIdOld.equals(polymerIdNew)) {
                polymerIdOld.getProductCollection().remove(product);
                polymerIdOld = em.merge(polymerIdOld);
            }
            if (polymerIdNew != null && !polymerIdNew.equals(polymerIdOld)) {
                polymerIdNew.getProductCollection().add(product);
                polymerIdNew = em.merge(polymerIdNew);
            }
            if (additiveAIdOld != null && !additiveAIdOld.equals(additiveAIdNew)) {
                additiveAIdOld.getProductCollection().remove(product);
                additiveAIdOld = em.merge(additiveAIdOld);
            }
            if (additiveAIdNew != null && !additiveAIdNew.equals(additiveAIdOld)) {
                additiveAIdNew.getProductCollection().add(product);
                additiveAIdNew = em.merge(additiveAIdNew);
            }
            if (additiveBIdOld != null && !additiveBIdOld.equals(additiveBIdNew)) {
                additiveBIdOld.getProductCollection().remove(product);
                additiveBIdOld = em.merge(additiveBIdOld);
            }
            if (additiveBIdNew != null && !additiveBIdNew.equals(additiveBIdOld)) {
                additiveBIdNew.getProductCollection().add(product);
                additiveBIdNew = em.merge(additiveBIdNew);
            }
            if (additiveCIdOld != null && !additiveCIdOld.equals(additiveCIdNew)) {
                additiveCIdOld.getProductCollection().remove(product);
                additiveCIdOld = em.merge(additiveCIdOld);
            }
            if (additiveCIdNew != null && !additiveCIdNew.equals(additiveCIdOld)) {
                additiveCIdNew.getProductCollection().add(product);
                additiveCIdNew = em.merge(additiveCIdNew);
            }
            for (Checkitem checkitemCollectionOldCheckitem : checkitemCollectionOld) {
                if (!checkitemCollectionNew.contains(checkitemCollectionOldCheckitem)) {
                    checkitemCollectionOldCheckitem.getProductCollection().remove(product);
                    checkitemCollectionOldCheckitem = em.merge(checkitemCollectionOldCheckitem);
                }
            }
            for (Checkitem checkitemCollectionNewCheckitem : checkitemCollectionNew) {
                if (!checkitemCollectionOld.contains(checkitemCollectionNewCheckitem)) {
                    checkitemCollectionNewCheckitem.getProductCollection().add(product);
                    checkitemCollectionNewCheckitem = em.merge(checkitemCollectionNewCheckitem);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            Mould mouldId = product.getMouldId();
            if (mouldId != null) {
                mouldId.getProductCollection().remove(product);
                mouldId = em.merge(mouldId);
            }
            Polymer polymerId = product.getPolymerId();
            if (polymerId != null) {
                polymerId.getProductCollection().remove(product);
                polymerId = em.merge(polymerId);
            }
            Additive additiveAId = product.getAdditiveAId();
            if (additiveAId != null) {
                additiveAId.getProductCollection().remove(product);
                additiveAId = em.merge(additiveAId);
            }
            Additive additiveBId = product.getAdditiveBId();
            if (additiveBId != null) {
                additiveBId.getProductCollection().remove(product);
                additiveBId = em.merge(additiveBId);
            }
            Additive additiveCId = product.getAdditiveCId();
            if (additiveCId != null) {
                additiveCId.getProductCollection().remove(product);
                additiveCId = em.merge(additiveCId);
            }
            Collection<Checkitem> checkitemCollection = product.getCheckitemCollection();
            for (Checkitem checkitemCollectionCheckitem : checkitemCollection) {
                checkitemCollectionCheckitem.getProductCollection().remove(product);
                checkitemCollectionCheckitem = em.merge(checkitemCollectionCheckitem);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
