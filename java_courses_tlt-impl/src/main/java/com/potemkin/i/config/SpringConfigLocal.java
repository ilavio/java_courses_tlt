package com.potemkin.i.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

@Profile("local")
@Configuration
@ComponentScan("com.potemkin.i")
@PropertySource("classpath:entity.properties")
public class SpringConfigLocal {
    @Autowired
    private Customer cust;
    
    @Bean
    public Customer customer() {
        var cust = new Customer();
        cust.setCustomerId(0);
        cust.setCustomerName("LOCAL");
        cust.setPhone("888");
        return cust;
    }
    
    @Bean
    public Order order() {
        var order = new Order();
        Date date = new Date();
        order.setOrderDate(date);
        order.setCustomer(cust);
        order.setOrderId(0);
        order.setOrderNumber("888");
        order.setTotalAmount(10.01);
        return order;
    }
    
    @Bean
    public Supplier supplier() {
        var sup = new Supplier();
        sup.setCompanyName("LOCAL");
        sup.setPhone("888");
        sup.setSupplierId(0);
        return sup;
    }
    
    @Bean
    public Product product() {
        var prod = new Product();
        prod.setDiscontinued(true);
        prod.setProductId(0);
        prod.setProductName("LOCAL");
        prod.setUnitPrice(10.01);
        return prod;
    }
}
