package com.potemkin.i.resource;

public interface SupplierResources {
    
    public String getSupplier(int id);
    
    public String getSuppliers();
    
    public String addSupplier(String strSup);
    
    public String deleteById(int id);
}
