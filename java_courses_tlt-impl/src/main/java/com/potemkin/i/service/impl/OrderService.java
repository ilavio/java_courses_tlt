package com.potemkin.i.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс OrderService обслуживания сущностей Order
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService  {
    
    private final OrderRepository orderRepository;

    /**
     * Метод получения сущности Order
     * 
     * @param id
     * @return Order
     */
    public Order getOrder(Integer id) {
        var ord = orderRepository.findById(id).get();
        return ord;
    }

    /**
     * Метод получения списка Order
     * 
     * @return List<Order>
     */
    public List<Order> getOrders(int id) {
        List<Order> ords  = orderRepository.findByCustomerCustomerId(id);
        log.info("OrderService getOrders() {}", ords);
        return ords;
    }

    /**
     * Метод добавления в базу данных Order
     * 
     * @param json
     * @return Order
     */
    public Order addOrder(Order ord) {
        log.info("3---->>> OrderService addOrder {}", ord.toString());
        orderRepository.saveAndFlush(ord);
        log.info("OrderService addOrder {}", ord);
        return ord;
    }

    /**
     * Метод изменения сущности Order
     * 
     * @param json
     * @param orderId
     * @return Order
     */
    public Order changeOrder(Order ord, int orderId) {
        var entity = orderRepository.findById(orderId).get();
        entity.setOrderNumber(ord.getOrderNumber());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(ord.getOrderDate());
        } catch (JSONException | ParseException e) {
            log.error("Ошибка в OrderService changeOrder() {}", e);
        }
        entity.setOrderDate(date);
        entity.setTotalAmount(ord.getTotalAmount());
        orderRepository.saveAndFlush(entity);
        log.info("OrderService changeOrder {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Order
     * 
     * @param customerId
     * @return JSONObject
     */

    public boolean deleteById(int orderId) {
        orderRepository.deleteById(orderId);
        var ex = orderRepository.existsById(orderId);
        if(ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
}
