/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.common.RecordKey;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.services.SettingService;
import static java.util.Arrays.stream;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 *
 * @author chacao
 */
public class RecordValidationServiceImpl implements RecordValidationService {

    
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
            case WALL_BASE:
                if (value < entry.getWallBaseMin() || value > entry.getWallBaseMax()) {
                    result = false;
                }
                break;
            case WALL_CLOSURE:
                if (value < entry.getWallClosureMin() || value > entry.getWallClosureMax()) {
                    result = false;
                }
                break;
            case WALL_HANDLE_BUNG:
                if (value < entry.getWallHandleBungMin() || value > entry.getWallHandleBungMax()) {
                    result = false;
                }
                break;
            case WALL_HANDLE_LEFT:
                if (value < entry.getWallHandleLeftMin() || value > entry.getWallHandleLeftMax()) {
                    result = false;
                }
                break;
            case WALL_HANDLE_RIGHT:
                if (value < entry.getWallHandleRightMin() || value > entry.getWallHandleRightMax()) {
                    result = false;
                }
                break;
            case WALL_UNDER_HANDLE:
                if (value < entry.getWallUnderHandleMin() || value > entry.getWallUnderHandleMax()) {
                    result = false;
                }
                break;
            case THREAD_BORE1:
                if (value < entry.getThreadBoreAMin() || value > entry.getThreadBoreAMax()) {
                    result = false;
                }
            break;
                case THREAD_BORE2:
                if (value < entry.getThreadBoreAMin() || value > entry.getThreadBoreAMax()) {
                    result = false;
                }
                break;
            case THREAD_NECK:
                if (value < entry.getThreadNeckMin() || value > entry.getThreadNeckMax()) {
                    result = false;
                }
                break;
            case THREAD_BORE1_2:
                if (value < entry.getThreadBoreAMin1() || value > entry.getThreadBoreAMax1()) {
                    result = false;
                }
            break;
                case THREAD_BORE2_2:
                if (value < entry.getThreadBoreAMin1() || value > entry.getThreadBoreAMax1()) {
                    result = false;
                }
                break;
            case THREAD_NECK_2:
                if (value < entry.getThreadNeckMin1() || value > entry.getThreadNeckMax1()) {
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
    
    @Override
    public void UpdateEntryStatus(String message){
        System.out.println(message);
    }
}
