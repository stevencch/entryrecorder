/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.common;

import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.frame.MainJFrame;
import com.cch.aj.entryrecorder.services.RecordSettingService;
import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.RecordSettingServiceImpl;
import com.cch.aj.entryrecorder.services.impl.RecordValidationServiceImpl;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author chacao
 */
@Configuration
@ComponentScan({"com.cch.aj.entryrecorder"})
public class AppConfig {

    
    
    @Bean(name = "recordSettingService")
    public RecordSettingService getRecordSettingService() {
        return new RecordSettingServiceImpl(Record.class);
    }
    
    @Bean(name = "recordValidationService")
    public RecordValidationService getRecordValidationService() {
        return new RecordValidationServiceImpl();
    }
    

    @Bean(name = "staffService")
    public SettingService<Staff> getStaffService() {
        return new SettingServiceImpl<Staff>(Staff.class);
    }
    
    @Bean
    public SettingService<Entry> getEntryService() {
        return new SettingServiceImpl<Entry>(Entry.class);
    }
    
    @Bean(name = "additiveService")
    public SettingService<Additive> getAdditiveService() {
        return new SettingServiceImpl<Additive>(Additive.class);
    }
    
    @Bean(name = "polymerService")
    public SettingService<Polymer> getPolymerService() {
        return new SettingServiceImpl<Polymer>(Polymer.class);
    }
    
    @Bean(name = "recordService")
    public SettingService<Record> getRecordService() {
        return new SettingServiceImpl<Record>(Record.class);
    }
    
    @Bean(name = "MainJFrame")
    public MainJFrame getMainJFrame() {
        MainJFrame mf = new MainJFrame();
        return mf;
    }
}
