/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.common;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author chacao
 */
@Component
@Aspect
public class SearchAspect {
    @Before("DoSearch()")
    public void LogTime(JoinPoint joinPoint){
        AppHelper.Logger.warn("##Search##"+Arrays.toString(joinPoint.getArgs()));
    }
    
    @Pointcut("execution(public * Search(..))")
    public void DoSearch(){
    }
    
    
    @Pointcut("execution(void UpdateEntryStatus(String))")
    public void UpdateEntryStatus(){
    }
    
    @Before("UpdateEntryStatus()")
    public void LogEntryTime(JoinPoint joinPoint){
        AppHelper.Logger.warn("##entry "+AppHelper.currentEntry.getId()+" ## "+Arrays.toString(joinPoint.getArgs()));
    }
}
