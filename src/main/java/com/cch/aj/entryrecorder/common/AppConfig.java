/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.common;

import com.cch.aj.entryrecorder.entities.Additive;
import com.cch.aj.entryrecorder.entities.Checkitem;
import com.cch.aj.entryrecorder.entities.Embossing;
import com.cch.aj.entryrecorder.entities.Entry;
import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.entities.Mould;
import com.cch.aj.entryrecorder.entities.Polymer;
import com.cch.aj.entryrecorder.entities.Product;
import com.cch.aj.entryrecorder.entities.Record;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.frame.CheckJFrame;
import com.cch.aj.entryrecorder.frame.EntryJFrame;
import com.cch.aj.entryrecorder.frame.MainJFrame;
import com.cch.aj.entryrecorder.frame.SearchJFrame;
import com.cch.aj.entryrecorder.frame.SettingsJFrame;
import com.cch.aj.entryrecorder.repositories.RecordSettingRepository;
import com.cch.aj.entryrecorder.repositories.impl.RecordRepositoryImpl;
import com.cch.aj.entryrecorder.services.EntrySearchService;
import com.cch.aj.entryrecorder.services.RecordSettingService;
import com.cch.aj.entryrecorder.services.RecordValidationService;
import com.cch.aj.entryrecorder.services.SettingService;
import com.cch.aj.entryrecorder.services.impl.EntrySearchServiceImpl;
import com.cch.aj.entryrecorder.services.impl.RecordSettingServiceImpl;
import com.cch.aj.entryrecorder.services.impl.RecordValidationServiceImpl;
import com.cch.aj.entryrecorder.services.impl.SettingServiceImpl;
import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 *
 * @author chacao
 */
@Configuration
@ComponentScan({"com.cch.aj.entryrecorder"})
@PropertySource("file:./app.properties")
@EnableAspectJAutoProxy
public class AppConfig {

    @Inject
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "recordSettingRepository")
    public RecordSettingRepository getRecordSettingRepository() {
        return new RecordRepositoryImpl(this.environment.getProperty("connectionString"),this.environment.getProperty("server"));
    }

    @Bean(name = "entrySearchService")
    public EntrySearchService getEntrySearchService() {
        return new EntrySearchServiceImpl(Entry.class);
    }

    @Bean(name = "recordSettingService")
    public RecordSettingService getRecordSettingService() {
        return new RecordSettingServiceImpl(Record.class);
    }

    @Bean(name = "recordValidationService")
    public RecordValidationService getRecordValidationService() {
        return new RecordValidationServiceImpl();
    }

    @Bean(name = "machineService")
    public SettingService<Machine> getMachineService() {
        return new SettingServiceImpl<Machine>(Machine.class);
    }

    @Bean(name = "mouldService")
    public SettingService<Mould> getMouldService() {
        return new SettingServiceImpl<Mould>(Mould.class);
    }

    @Bean(name = "productService")
    public SettingService<Product> getProductService() {
        return new SettingServiceImpl<Product>(Product.class);
    }

    @Bean(name = "checkitemService")
    public SettingService<Checkitem> getCheckitemService() {
        return new SettingServiceImpl<Checkitem>(Checkitem.class);
    }

    @Bean(name = "staffService")
    public SettingService<Staff> getStaffService() {
        return new SettingServiceImpl<Staff>(Staff.class);
    }
    
    @Bean(name = "embossingService")
    public SettingService<Embossing> getEmbossingService() {
        return new SettingServiceImpl<Embossing>(Embossing.class);
    }

    @Bean(name = "entryService")
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
    @Scope("prototype")
    public MainJFrame getMainJFrame() {
        MainJFrame mf = new MainJFrame();
        return mf;
    }

    @Bean(name = "EntryJFrame", initMethod = "init")
    @Scope("prototype")
    public EntryJFrame getEntryJFrame() {
        EntryJFrame mf = new EntryJFrame();
        return mf;
    }

    @Bean(name = "SearchJFrame", initMethod = "init")
    @Scope("prototype")
    public SearchJFrame getSearchJFrame() {
        SearchJFrame mf = new SearchJFrame();
        return mf;
    }

    @Bean(name = "SettingsJFrame", initMethod = "init")
    @Scope("prototype")
    public SettingsJFrame getSettingsJFrame() {
        SettingsJFrame mf = new SettingsJFrame();
        return mf;
    }

    @Bean(name = "CheckJFrame", initMethod = "init")
    @Scope("prototype")
    public CheckJFrame getCheckJFrame() {
        CheckJFrame mf = new CheckJFrame();
        return mf;
    }
}
