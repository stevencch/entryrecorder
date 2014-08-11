/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.services.StaffService;
import com.cch.aj.entryrecorder.entities.Staff;
import com.cch.aj.entryrecorder.repositories.StaffRepository;
import com.cch.aj.entryrecorder.repositories.impl.StaffRepositoryImpl;
import java.util.List;

/**
 *
 * @author chacao
 */
public class StaffServiceImpl implements StaffService {
    
    StaffRepository staffRepository=new StaffRepositoryImpl("com.cch.aj_EntryRecorder_jar_1.0-SNAPSHOTPU");
    
    @Override
    public List<Staff> GetAllStaffs(){
        return this.staffRepository.findStaffEntities();
    }
}
