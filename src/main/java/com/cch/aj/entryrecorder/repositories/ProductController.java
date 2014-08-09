/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public interface ProductController extends Serializable {

    void create(Product product);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Product product) throws NonexistentEntityException, Exception;

    Product findProduct(Integer id);

    List<Product> findProductEntities();

    List<Product> findProductEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getProductCount();
    
}
