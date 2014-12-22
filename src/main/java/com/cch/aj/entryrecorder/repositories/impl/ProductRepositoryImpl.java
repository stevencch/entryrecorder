/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories.impl;

import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.IllegalOrphanException;
import com.cch.aj.entryrecorder.repositories.impl.exceptions.NonexistentEntityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chacao
 */
public class ProductRepositoryImpl implements SettingRepository<Product>{

    ProductJpaController controller;
    
    public ProductRepositoryImpl(String dbConnectionString,String server) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://"+server+":3306/ajrecorder?zeroDateTimeBehavior=convertToNull");
        EntityManagerFactory emf=javax.persistence.Persistence.createEntityManagerFactory(dbConnectionString,properties);
        this.controller=new ProductJpaController(emf);
    }

    @Override
    public void create(Product item) {
        this.controller.create(item);
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException
    {
            controller.destroy(id);
    }

    @Override
    public void edit(Product item) throws NonexistentEntityException, Exception {
        this.controller.edit(item);
    }

    @Override
    public Product findEntity(Integer id) {
       return  this.controller.findProduct(id);
    }

    @Override
    public List<Product> findEntities() {
        return this.controller.findProductEntities();
    }

    @Override
    public List<Product> findEntities(int maxResults, int firstResult) {
        return this.controller.findProductEntities(maxResults, firstResult);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    @Override
    public int getEntityCount() {
        return this.controller.getProductCount();
    }

    
}
