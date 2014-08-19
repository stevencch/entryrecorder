/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services;

import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Entry;

/**
 *
 * @author chacao
 */
public interface RecordValidationService {

    Boolean Validate(Entry entry, RecordKey key, Float value);

    Boolean ValidateValues(String... values);
    
}
