/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author chacao
 */
public class ApplicationContextProvider implements ApplicationContextAware{
    
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        AppContext.setApplicationContext(ctx);
    }
}
