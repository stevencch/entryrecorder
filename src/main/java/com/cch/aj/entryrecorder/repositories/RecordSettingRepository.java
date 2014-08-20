/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.repositories;

import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Record;
import java.util.List;

/**
 *
 * @author chacao
 */
public interface RecordSettingRepository extends SettingRepository<Record> {
    List<Record> FindEntitiesByKeyAndRecord(RecordKey key,int recordId);
}
