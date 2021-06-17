package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.CustomerRepository;
import com.potemkin.i.repository.OrderRepository;
import com.potemkin.i.service.impl.CustomerServiceImpl;
import com.potemkin.i.service.impl.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;
    
//    @Autowired
//    private CustomerServiceImpl customerServiceImpl;
    
    @Test
    public void addOrderTest() {
        var ord = new Order();
        ord.setOrderId(1);
        ord.setOrderNumber("111");
        var date = new Date();
        ord.setOrderDate(date);
        orderServiceImpl.addOrder(ord);
        var ordTest = orderServiceImpl.getOrder(1);
        log.info(" addOrderTest() {}", ordTest);
        orderServiceImpl.deleteById(1);
        assertNotNull(ordTest);
    }
    
    
    @TestConfiguration
    static class MyTestConfig {
        @Bean
        public OrderServiceImpl orderServiceImpl(OrderRepository orderRepository) {
            return new OrderServiceImpl(orderRepository);
        }
        
//        @Bean
//        public CustomerServiceImpl customerServiceImpl(CustomerRepository customerRepository) {
//            return new CustomerServiceImpl(customerRepository);
//        }
    }
}
