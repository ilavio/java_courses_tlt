package com.potemkin.i.repository.interf;

import org.json.JSONObject;

import com.potemkin.i.domain.entity.Order;

/**
 * Интерфейс OrderRepository
 * 
 * @author Илья Пот
 *
 */
public interface OrderRepository {
    
    public Order addOrder(Order order, int customerId);
    
    public Order getOredr(int orderId);
    
    public boolean deleteOrder(int orderId);
    
    public Order parseForOrder(JSONObject json);
}
