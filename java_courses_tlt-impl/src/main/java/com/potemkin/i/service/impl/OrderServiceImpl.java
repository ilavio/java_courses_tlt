package com.potemkin.i.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс OrderServiceImpl обслуживания сущностей Order
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    
    /**
     * Метод добавления в базу данных Order
     * 
     * @param Order
     * @return Order
     */
    public Order addOrder(Order ord) {
        log.info("OrderService addOrder {}", ord);
        orderRepository.save(ord);
        log.info("OrderService addOrder {}", ord);
        return ord;
    }
    
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
     * Метод изменения сущности Order
     * 
     * @param Order
     * @param orderId
     * @return Order
     */
    public Order changeOrder(Order ord, int orderId) {
        var entity = orderRepository.findById(orderId).get();
        entity.setOrderNumber(ord.getOrderNumber());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(ord.getOrderDate());
        } catch (ParseException e) {
            log.error("Ошибка в OrderService changeOrder() {}", e);
        }
        entity.setOrderDate(date);
        entity.setTotalAmount(ord.getTotalAmount());
        orderRepository.save(entity);
        log.info("OrderService changeOrder {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Order
     * 
     * @param orderId
     * @return boolean
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
