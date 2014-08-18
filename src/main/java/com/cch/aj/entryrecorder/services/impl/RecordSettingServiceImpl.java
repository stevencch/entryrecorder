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

/**
 *
 * @author chacao
 */
public class RecordSettingServiceImpl extends SettingServiceImpl<Record> implements RecordSettingService  {
    
    RecordSettingRepository recordSettingRepository=new RecordRepositoryImpl(this._connectionString);
    
    public RecordSettingServiceImpl(Class<Record> type) {
        super(type);
    }

    @Override
    public List<Record> GetAllEntitiesByKey(RecordKey recordkey) {
        return this.recordSettingRepository.FindEntitiesByKey(recordkey);
    }

    
    
}
