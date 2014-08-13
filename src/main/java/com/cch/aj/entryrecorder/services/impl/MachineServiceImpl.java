/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cch.aj.entryrecorder.services.impl;

import com.cch.aj.entryrecorder.entities.Machine;
import com.cch.aj.entryrecorder.repositories.MachineRepository;
import com.cch.aj.entryrecorder.repositories.impl.MachineRepositoryImpl;
import com.cch.aj.entryrecorder.services.MachineService;
import java.util.List;

/**
 *
 * @author chacao
 */
public class MachineServiceImpl implements MachineService {
    
    MachineRepository repository=new MachineRepositoryImpl("com.cch.aj_EntryRecorder_jar_1.0-SNAPSHOTPU");
    
    @Override
    public List<Machine> GetAllMachines(){
        return this.repository.findMachineEntities();
    }

    @Override
    public int CreateMachine() {
        Machine item=new Machine();
        item.setMachineNo("New No");
        this.repository.create(item);
        return item.getId();
    }

    @Override
    public void UpdateMachine(Machine item) {
        try{
        this.repository.edit(item);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void DeleteMachine(Integer id) {
        try{
        this.repository.destroy(id);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
