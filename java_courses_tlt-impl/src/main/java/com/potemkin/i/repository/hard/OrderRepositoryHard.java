package com.potemkin.i.repository.hard;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.interf.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component
public class OrderRepositoryHard implements OrderRepository {
    Order orderH = new Order();
    Date date = new Date();
    Customer cust = new Customer();
   
    public Order addOrder(Order order, int customerId) {
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        orderH.setOrderDate(date);
        orderH.setCustomer(cust);
        orderH.setOrderId(0);
        orderH.setOrderNumber("888");
        orderH.setTotalAmount(10.01);
        log.info("Добавление Order: {}", orderH);
        return orderH;
    }

    public Order getOredr(int orderId) {
        log.info("Получение Order: {}", orderH);
        return orderH;
    }

    public boolean deleteOrder(int orderId) {
        orderH.setOrderDate(date);
        orderH.setCustomer(cust);
        orderH.setOrderId(0);
        orderH.setOrderNumber("");
        orderH.setTotalAmount(0);
        log.info("Удаление Order: {}", orderH);
        return true;
    }

    public Order parseForOrder(JSONObject json) {
        return orderH;
    }
}
