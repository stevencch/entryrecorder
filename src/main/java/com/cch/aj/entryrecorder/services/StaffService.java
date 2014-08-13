/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services;

import com.cch.aj.entryrecorder.entities.Staff;
import java.util.List;

/**
 *
 * @author chacao
 */
public interface StaffService {

    List<Staff> GetAllStaffs();
    
    int CreateNewStaff();

    void UpdateStaff(Staff currentStaff);

    public void DeleteStaff(Integer id);
}
