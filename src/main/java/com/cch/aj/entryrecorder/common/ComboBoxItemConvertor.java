/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.common;

import com.cch.aj.entryrecorder.entities.Staff;

/**
 *
 * @author chacao
 */
public class ComboBoxItemConvertor {
    public static <T> ComboBoxItem<T> ConvertToComboBoxItem(T t,String name, int id){
        return new ComboBoxItem<T>(t,name,id);
    }
}
