/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cch.aj.entryrecorder.common;

/**
 *
 * @author chacao
 */
public class ComboBoxItem<T> {

    
    public ComboBoxItem(T t,String Name, int Id){
        this.Id=Id;
        this.Name=Name;
        this.item=t;
    }
    
    private int Id;
    
    private String Name;
    
    private T item;

    public T getItem() {
        return item;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
