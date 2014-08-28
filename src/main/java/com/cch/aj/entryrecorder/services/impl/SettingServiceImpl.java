/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.common.SettingEntity;
import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Checkitem;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.SettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.AdditiveRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.CheckitemRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.EntryRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.MachineRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.MouldRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.PolymerRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.ProductRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.RecordRepositoryImpl;
import com.cch.aj.entryrecorder.repositories.impl.StaffRepositoryImpl;
import com.cch.aj.entryrecorder.services.SettingService;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chacao
 * @param <T>
 */
public class SettingServiceImpl<T extends SettingEntity> implements SettingService<T> {

    protected String _connectionString = "com.cch.aj_EntryRecorder_jar_1.0-SNAPSHOTPU";
    protected SettingRepository repository;
    private T instance;

    public SettingServiceImpl(Class<T> type) {
        try {
            instance = type.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(SettingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SettingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (type == Machine.class) {
            this.repository = new MachineRepositoryImpl(this._connectionString);
        }
        if (type == Staff.class) {
            this.repository = new StaffRepositoryImpl(this._connectionString);
        }
        if (type == Polymer.class) {
            this.repository = new PolymerRepositoryImpl(this._connectionString);
        }
        if (type == Additive.class) {
            this.repository = new AdditiveRepositoryImpl(this._connectionString);
        }
        if (type == Mould.class) {
            this.repository = new MouldRepositoryImpl(this._connectionString);
        }
        if (type == Product.class) {
            this.repository = new ProductRepositoryImpl(this._connectionString);
        }
        if (type == Entry.class) {
            this.repository = new EntryRepositoryImpl(this._connectionString);
        }
        if (type == Record.class) {
            this.repository = new RecordRepositoryImpl(this._connectionString);
        }
        if (type == Checkitem.class) {
            this.repository = new CheckitemRepositoryImpl(this._connectionString);
        }
    }

    @Override
    public List<T> GetAllEntities() {
        return this.repository.findEntities();
    }

    @Override
    public Integer CreateEntity() {
        SettingEntity newItem = (SettingEntity) this.instance;
        Integer result = 0;
        if (newItem != null) {
            newItem.setDefaultValue();
            this.repository.create(newItem);
            result = newItem.getId();
        }
        return result;
    }

    @Override
    public void UpdateEntity(T item) {
        try {
            this.repository.edit(item);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void DeleteEntity(Integer id) {
        try {
            this.repository.destroy(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public T FindEntity(Integer id) {
        return (T) this.repository.findEntity(id);
    }

}
