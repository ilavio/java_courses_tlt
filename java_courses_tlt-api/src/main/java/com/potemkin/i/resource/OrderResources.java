package com.potemkin.i.resource;

public interface OrderResources {
    
    public String getOrder(int id);
    
    public String getOrders(int customerId);
    
    public String addOrder(String strOrd);
    
    public String deleteById(int id);
}
