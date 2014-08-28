/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services;

import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Record;
import java.util.List;

/**
 *
 * @author chacao
 */
public interface EntrySearchService extends SettingService<Entry> {
    List<Entry> Search(String shift, String product, String batch);
}
