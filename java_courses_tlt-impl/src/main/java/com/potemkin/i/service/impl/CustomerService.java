package com.potemkin.i.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс CustomerService обслуживания сущностей Customer
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Метод получения сущности Customer
     * 
     * @param id
     * @return Customer
     */
    public Customer getCustomer(Integer id) {
        var cust = customerRepository.findById(id).get();
        return cust;
    }

    /**
     * Метод получения списка Customer
     * 
     * @return List<Customer>
     */
    public List<Customer> getCustomers() {
        List<Customer> custs = customerRepository.findAll();
        log.info("CustomerService getCustomers() {}", custs);
        return custs;
    }

    /**
     * Метод добавления в базу данных Customer
     * 
     * @param Customer
     * @return Customer
     */
    public Customer addCustomer(Customer cust) {
        customerRepository.saveAndFlush(cust);
        return cust;
    }

    /**
     * Метод изменения сущности Customer
     * 
     * @param Customer
     * @param customerId
     * @return Customer
     */
    public Customer changeEntity(Customer cust, int customerId) {
        Customer entity = customerRepository.findById(customerId).get();
        entity.setCustomerName(cust.getCustomerName());
        entity.setPhone(cust.getPhone());
        customerRepository.saveAndFlush(entity);
        log.info("CustomerService changeEntity() {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Customer
     * 
     * @param customerId
     * @return
     */

    public boolean deleteById(int customerId) {
        customerRepository.deleteById(customerId);
        var ex = customerRepository.existsById(customerId);
        if (ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
}
