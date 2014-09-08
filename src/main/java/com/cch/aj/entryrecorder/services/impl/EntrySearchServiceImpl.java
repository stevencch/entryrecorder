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
        shift = shift.toLowerCase();
        product = product.toLowerCase();
        batch = batch.toLowerCase();
        List<Entry> list = this.getRepository().findEntities();
        if (!shift.equals("")) {
            List<String> shifts = Arrays.asList(shift.split(","));
            list = list.stream().filter(x -> shifts.contains(x.getShift().toLowerCase())).collect(Collectors.toList());
        }
        if (!product.equals("")) {
            List<String> products = Arrays.asList(product.split(","));
            list = list.stream().filter(x -> products.contains(x.getProductId().getCode().toLowerCase())).collect(Collectors.toList());
        }
        if (!batch.equals("")) {
            List<String> batchs = Arrays.asList(batch.split(","));
            list = list.stream().filter(x -> batchs.stream().anyMatch(y -> (x.getMaterial()!=null && x.getMaterial().contains(y)))).collect(Collectors.toList());
        }
        return list;
    }

}
