package com.potemkin.i.domain.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тестированеи Customer класс
 * 
 * @author Илья Пот
 */
public class CustomerTest {
    Customer customer;
    Date date = new Date();
    Order order;

    @BeforeEach
    public void addCustomer() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("MMM");
        customer.setPhone("121212");
        order = new Order();
        order.setOrderId(1);
        order.setOrderDate(date);
        order.setOrderNumber("111");
        order.setTotalAmount(12.02);
        customer.setOrders(new ArrayList<Order>());
        customer.getOrders().add(order);
    }

    @Test
    public void equlsTest() {
        Customer customer2 = new Customer();
        Order order2 = new Order();
        customer2.setCustomerId(1);
        customer2.setCustomerName("MMM");
        customer2.setPhone("121212");
        order2.setOrderId(1);
        order2.setOrderDate(date);
        order2.setOrderNumber("111");
        order2.setTotalAmount(12.02);
        customer2.setOrders(new ArrayList<Order>());
        customer2.getOrders().add(order);
        assertTrue(order2.equals(order));
        assertTrue(customer2.equals(customer));
        assertTrue(customer.equals(customer));
        assertEquals(customer.getCustomerId(), 1);
        assertEquals(customer.getCustomerName(), "MMM");
        assertEquals(customer.getPhone(), "121212");
        assertEquals(customer.getOrders().get(0), order);
        assertEquals(customer.getOrders().get(0).hashCode(), order.hashCode());
        assertEquals(customer.hashCode(), customer.hashCode());
        assertNotNull(customer.toString());
        assertNotNull(customer.getOrders().get(0).toString());
        assertTrue(customer.canEqual(customer));
    }
}
