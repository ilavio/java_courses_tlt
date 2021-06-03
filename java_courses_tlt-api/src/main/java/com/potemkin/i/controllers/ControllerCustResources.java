package com.potemkin.i.controllers;

public interface ControllerCustResources {
    
    public String getCustomer(int id);
    
    public String getCustomers();
    
    public String deleteById(int id);
}
