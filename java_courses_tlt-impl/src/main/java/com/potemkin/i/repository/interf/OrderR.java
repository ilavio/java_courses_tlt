package com.potemkin.i.repository.interf;

import org.json.JSONObject;

import com.potemkin.i.domain.entity.Order;

public interface OrderR {
    
    public void addOrder(Order order, int customerId);
    
    public Order getOredr(int orderId);
    
    public void deleteOrder(int orderId);
    
    public Order parseForOrder(JSONObject json);
}
