package com.potemkin.i.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс CustomerServiceImpl обслуживания сущностей Customer
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    /**
     * Метод добавления Customer в базу данных
     * 
     * @param Customer
     * @return Customer
     */
    public Customer addCustomer(Customer cust) {
        log.info("CustomerServiceImpl addCustomer() - {}", cust);
        customerRepository.saveAndFlush(cust);
        return cust;
    }

    /**
     * Метод получения сущности Customer
     * 
     * @param id
     * @return Customer
     */
    public Customer getCustomer(Integer id) {
        var cust = customerRepository.findById(id).get();
        log.info("CustomerServiceImpl getCustomer() - {}", cust);
        return cust;
    }

    /**
     * Метод получения листа Customer
     * 
     * @return List<Customer>
     */
    public List<Customer> getCustomers() {
        var custs = customerRepository.findAll();
        log.info("CustomerServiceImpl getCustomers() {}", custs);
        return custs;
    }
    
    public boolean deleteById(int customerId) {
        customerRepository.deleteById(customerId);
        var ex = customerRepository.existsById(customerId);
        if (ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
    
    public Customer chengeEntity(Customer cust, int customerId) {
        Customer entity = customerRepository.findById(customerId).get();
        entity.setCustomerName(cust.getCustomerName());
        entity.setPhone(cust.getPhone());
        customerRepository.saveAndFlush(entity);
        log.info("CustomerService changeEntity() {}", entity);
        return entity;
    }
}
