/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.EntryRepositoryImpl;
import com.cch.aj.entryrecorder.services.EntrySearchService;
import com.cch.aj.entryrecorder.services.SettingService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chacao
 */
public class EntrySearchServiceImpl extends SettingServiceImpl<Entry> implements EntrySearchService {

    public EntrySearchServiceImpl(Class<Entry> type) {
        super(type);
    }
    
    @Override
    public List<Entry> Search(String shift, String product, String batch) {
        shift=shift.toLowerCase();
        product=product.toLowerCase();
        batch=batch.toLowerCase();
        List<Entry> list=this.repository.findEntities();
        if(!shift.equals("")){
            List<String> shifts=Arrays.asList(shift.split(","));
            list=list.stream().filter(x->shifts.contains(x.getShift().toLowerCase())).collect(Collectors.toList());
        }
        if(!product.equals("")){
            List<String> products=Arrays.asList(product.split(","));
            list=list.stream().filter(x->products.contains(x.getProductId().getCode().toLowerCase())).collect(Collectors.toList());
        }
        if(!batch.equals("")){
            List<String> batchs=Arrays.asList(batch.split(","));
            list=list.stream().filter(x->
                    batchs.contains(x.getAdditiveABatchA().toLowerCase())
                    ||batchs.contains(x.getAdditiveABatchB().toLowerCase())
                    ||batchs.contains(x.getAdditiveBBatchA().toLowerCase())
                    ||batchs.contains(x.getAdditiveBBatchB().toLowerCase())
                    ||batchs.contains(x.getAdditiveCBatchA().toLowerCase())
                    ||batchs.contains(x.getAdditiveCBatchB().toLowerCase())
                    ||batchs.contains(x.getPolymerBatchA().toLowerCase())
                    ||batchs.contains(x.getPolymerBatchB().toLowerCase())).collect(Collectors.toList());
        }
        return list;
    }
    
}
