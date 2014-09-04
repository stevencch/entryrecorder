/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.common.SettingEntity;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.repositories.RecordSettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.RecordRepositoryImpl;
import com.cch.aj.entryrecorder.services.RecordSettingService;
import com.cch.aj.entryrecorder.services.SettingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chacao
 */
public class RecordSettingServiceImpl extends SettingServiceImpl<Record> implements RecordSettingService  {
    
    
    @Autowired
    RecordSettingRepository recordSettingRepository;
    
    public RecordSettingServiceImpl() {
        super(Record.class);
    }
    
    public RecordSettingServiceImpl(Class<Record> type) {
        super(type);
    }

    @Override
    public List<Record> GetAllEntitiesByKeyAndRecord(RecordKey recordkey,int recordId) {
        return this.recordSettingRepository.FindEntitiesByKeyAndRecord(recordkey,recordId);
    }

    
    
}
