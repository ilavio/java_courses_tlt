package com.potemkin.i.service;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("servicelocal")
@Profile("local")
public class ServiceLocal implements ServiceInt {

    @Override
    public void start(AnnotationConfigApplicationContext context) {
        var cust = context.getBean("customer", Customer.class);
        log.info("Customer: customerId - {}, customerName - {}, phone - {};", cust.getCustomerId(),
                cust.getCustomerName(), cust.getPhone());
        
        var order = context.getBean("order", Order.class);
        log.info("Order: orderId - {}, orderNumber - {}, totalAmount - {}, orderDate - {};", order.getOrderId(),
                order.getOrderNumber(), order.getTotalAmount(), order.getOrderDate());
        
        var sup = context.getBean("supplier", Supplier.class);
        log.info("Supplier: supplierId - {}, companyName - {}, Phone - {}", sup.getSupplierId(), sup.getCompanyName(),
                sup.getPhone());
        
        var prod = context.getBean("product", Product.class);
        log.info("Product: productId - {}, ProductName - {}, UnitPrice - {}", prod.getProductId(),
                prod.getProductName(), prod.getUnitPrice());
    }
}
