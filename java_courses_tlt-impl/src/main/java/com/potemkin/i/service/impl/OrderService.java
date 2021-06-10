package com.potemkin.i.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductService productService;
    private final CustomerService customerService;

    /**
     * Метод получения сущности Order из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getOrderJson(Integer id) {
        Order ord = orderRepository.findById(id).get();
        JSONObject json = new JSONObject(ord);
        return json;
    }

    /**
     * Метод получения сущности Order
     * 
     * @param id
     * @return Customer
     */
    public Order getOrder(Integer id) {
        var ord = orderRepository.findById(id).get();
        return ord;
    }

    /**
     * Метод получения списка Order
     * 
     * @return JSONArray
     */
    public JSONArray getOrders(int id) {
        var jsonArray = new JSONArray(orderRepository.findByCustomerCustomerId(id));
        return jsonArray;
    }

    /**
     * Метод добавления в базу данных Order
     * 
     * @param json
     * @return JSONObject
     */
    public JSONObject addOrder(JSONObject json) {
        var ord = parseForOrder(json);
        orderRepository.saveAndFlush(ord);
        return json;
    }

    /**
     * Метод изменения сущности Order
     * 
     * @param json
     * @param orderId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int orderId) {
        var entity = orderRepository.findById(orderId).get();
        entity.setOrderNumber(json.getString("orderNumber"));
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(json.getString("orderDate"));
        } catch (JSONException | ParseException e) {
            log.error("Ошибка в OrderService changeOrder() {}", e);
        }
        entity.setOrderDate(date);
        entity.setTotalAmount(json.getDouble("totalAmount"));
        orderRepository.saveAndFlush(entity);
        json.append("id", orderId);
        return json;
    }

    /**
     * Метод удаления сущности Order
     * 
     * @param customerId
     * @return JSONObject
     */
    public JSONObject deleteById(int orderId) {
        orderRepository.deleteById(orderId);
        var ex = orderRepository.existsById(orderId);
        String str = "{" + "\"Found Order\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json;
    }

    /**
     * Метод разбора JSONObject для создания Order
     * 
     * @param json
     * @return Order
     */
    public Order parseForOrder(JSONObject json) {
        Order order = new Order();
        if (json != null) {
            order.setOrderNumber(json.getString("orderNumber"));
            order.setTotalAmount(json.getDouble("totalAmount"));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(json.getString("orderDate"));
            } catch (JSONException | ParseException e) {
                log.error("Ошибка в OrderService parseForOrder() {}", e);
            }
            order.setOrderDate(date);
            if (json.has("productId")) {
                var prod = productService.getProduct(json.getInt("productId"));
                order.addProduct().add(prod);
                prod.addOrder().add(order);
            }
            var cust = customerService.getCustomer(json.getInt("customerId"));
            order.setCustomer(cust);
        }
        return order;
    }
}
