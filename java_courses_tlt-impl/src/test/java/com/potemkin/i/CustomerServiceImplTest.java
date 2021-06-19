package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;
import com.potemkin.i.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void addCustomerTest() {
        var cust = new Customer();
        cust.setCustomerId(1);
        cust.setCustomerName("L");
        customerServiceImpl.addCustomer(cust);
        var custTest = customerServiceImpl.getCustomer(1);
        assertNotNull(custTest);
    }

    @Test
    public void getAndDeleteTest() {
        var cust = new Customer();
        cust.setCustomerId(2);
        cust.setCustomerName("L");
        customerServiceImpl.addCustomer(cust);
        var custs = customerServiceImpl.getCustomers();
        var ex = customerServiceImpl.deleteById(2);
        log.info("getAndDeleteTest() {}", ex);
        assertNotNull(ex);
    }

    @TestConfiguration
    static class MyTestConfig {
        @Bean
        public CustomerServiceImpl customerServiceImpl(CustomerRepository customerRepository) {
            return new CustomerServiceImpl(customerRepository);
        }
    }
}
