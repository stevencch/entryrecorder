/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.services.SettingService;
import static java.util.Arrays.stream;
import java.util.stream.Stream;

/**
 *
 * @author chacao
 */
public class RecordValidationServiceImpl implements RecordValidationService {

    private SettingService<Entry> entryService = new SettingServiceImpl<Entry>(Entry.class);

    @Override
    public Boolean Validate(Entry entry, RecordKey key, Float value) {
        Boolean result = true;
        switch (key) {
            case PRODUCT_WEIGHT:
                if (value < entry.getWeightMin() || value > entry.getWeightMax()) {
                    result = false;
                }
                break;
            case TAP_POSITION:
                if (value < entry.getTapPositionMin() || value > entry.getTapPositionMax()) {
                    result = false;
                }
                break;
            case WALL_THICKNESS:
                if (value < entry.getWallMin() || value > entry.getWallMax()) {
                    result = false;
                }
                break;
            case THREAD_BORE:
                if (value < entry.getThreadBoreMin() || value > entry.getThreadBoreMax()) {
                    result = false;
                }
                break;
            case THREAD_NECK:
                if (value < entry.getThreadNeckMin() || value > entry.getThreadNeckMax()) {
                    result = false;
                }
                break;
        }
        return result;
    }

    @Override
    public Boolean ValidateValues(String... values) {
        Boolean result = true;
        String[] checkList = new String[]{};
        for (String value : values) {
            if (Stream.of(checkList).filter(x -> x.equals(value)).count() > 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
