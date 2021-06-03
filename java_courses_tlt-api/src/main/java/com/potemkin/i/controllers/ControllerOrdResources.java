package com.potemkin.i.controllers;

public interface ControllerOrdResources {
    
    public String getOrder(int id);
    
    public String getOrders(int customerId);
    
    public String addOrder(String strOrd);
    
    public String deleteById(int id);
}
