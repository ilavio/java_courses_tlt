package com.potemkin.i.resource;

public interface CustomerResources {
    
    public String getCustomer(int id);
    
    public String getCustomers();
    
    public String deleteById(int id);
}
