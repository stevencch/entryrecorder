/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services;

import com.cch.aj.entryrecorder.entities.Machine;
import java.util.List;

/**
 *
 * @author chacao
 */
public interface MachineService {
    List<Machine> GetAllMachines();
    
    int CreateMachine();

    void UpdateMachine(Machine item);

    public void DeleteMachine(Integer id);
}
