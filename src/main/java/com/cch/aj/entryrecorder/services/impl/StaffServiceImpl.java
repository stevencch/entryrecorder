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

    @Override
    public int CreateNewStaff() {
        Staff staff=new Staff();
        staff.setName("Staff Name");
        staff.setJobType("PROCESS WORKER");
        this.staffRepository.create(staff);
        return staff.getId();
    }

    @Override
    public void UpdateStaff(Staff currentStaff) {
        try{
        this.staffRepository.edit(currentStaff);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void DeleteStaff(Integer id) {
        try{
        this.staffRepository.destroy(id);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
