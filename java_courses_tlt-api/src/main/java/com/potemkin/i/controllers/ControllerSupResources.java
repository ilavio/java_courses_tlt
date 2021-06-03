package com.potemkin.i.controllers;

public interface ControllerSupResources {
    
    public String getSupplier(int id);
    
    public String getSuppliers();
    
    public String addSupplier(String strSup);
    
    public String deleteById(int id);
}
