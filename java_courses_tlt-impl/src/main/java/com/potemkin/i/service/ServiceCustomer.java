package com.potemkin.i.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;

/**
 * Интерфейс взаимодествия с базой данных
 * 
 * @author Илья Пот
 *
 */
@Component
public interface ServiceCustomer {

    /**
     * Метод изменения сущности в базе данных
     * 
     * @param json
     * @param customerId
     * @return JSONObject
     */
    public Customer changeEntity(Customer cust, int customerId);

    /**
     * Метод удаления из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public boolean deleteById(int customerId);

    /**
     * Метод добавления сущности Customer
     * 
     * @param json
     * @return
     */
    public Customer addCustomer(Customer cust);

    /**
     * Метод получения списка Customer
     * 
     * @return
     */
    public List<Customer> getCustomers();
}
