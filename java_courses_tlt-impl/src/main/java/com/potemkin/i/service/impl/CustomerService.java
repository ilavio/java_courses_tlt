package com.potemkin.i.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;
import com.potemkin.i.service.ServiceCustomer;

import lombok.RequiredArgsConstructor;

/**
 * Класс CustomerService обслуживания сущностей Customer
 * 
 * @author Илья Пот
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements ServiceCustomer {

    private final CustomerRepository customerRepository;

    /**
     * Метод получения сущности Customer из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getCustomerJson(Integer id) {
        JSONObject json = new JSONObject(customerRepository.findById(id).get());
        return json;
    }

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
     * @return JSONArray
     */
    public JSONArray getCustomers() {
        var jsonArray = new JSONArray(customerRepository.findAll());
        return jsonArray;
    }

    /**
     * Метод добавления в базу данных Customer
     * 
     * @param json
     * @return JSONObject
     */
    public JSONObject addCustomer(JSONObject json) {
        var cust = parseForCustomer(json);
        customerRepository.saveAndFlush(cust);
        return json;
    }

    /**
     * Метод изменения сущности Customer
     * 
     * @param json
     * @param customerId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int customerId) {
        Customer entity = customerRepository.findById(customerId).get();
        entity.setCustomerName(json.getString("customerName"));
        entity.setPhone(json.getString("phone"));
        customerRepository.saveAndFlush(entity);
        json.append("id", customerId);
        return json;
    }

    /**
     * Метод удаления сущности Customer
     * 
     * @param customerId
     * @return
     */
    public JSONObject deleteById(int customerId) {
        customerRepository.deleteById(customerId);
        var ex = customerRepository.existsById(customerId);
        String str = "{" + "\"Found Customer\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json;
    }

    /**
     * Метод разбора JSONObject для создания Customer
     * 
     * @param json
     * @return Customer
     */
    public Customer parseForCustomer(JSONObject json) {
        Customer cust = new Customer();
        if (json != null) {
            cust.setCustomerName(json.getString("customerName"));
            cust.setPhone(json.getString("phone"));
        }
        return cust;
    }
}
