/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services;

import com.cch.aj.entryrecorder.common.SettingEntity;
import com.cch.aj.entryrecorder.entities.Machine;
import java.util.List;

/**
 *
 * @author chacao
 * @param <T>
 */
public interface SettingService<T extends SettingEntity> {
    List<T> GetAllEntities();
    
    Integer CreateEntity();

    void UpdateEntity(T item);

    public void DeleteEntity(Integer id);
    
    T FindEntity(Integer id);
}
