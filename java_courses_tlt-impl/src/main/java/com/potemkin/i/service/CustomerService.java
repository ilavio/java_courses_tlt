package com.potemkin.i.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;

/**
 * Класс CustomerService обслуживания сущностей Customer
 * 
 * @author Илья Пот
 *
 */
@Service
@Transactional
public class CustomerService implements ServiceInt { //

    @Autowired
    private CustomerRepository repository;

    /**
     * Метод получения сущности Customer из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getCustomerJson(Integer id) {
        JSONObject json = new JSONObject(repository.findById(id).get());
        return json;
    }

    /**
     * Метод получения сущности Customer
     * 
     * @param id
     * @return Customer
     */
    public Customer getCustomer(Integer id) {
        var cust = repository.findById(id).get();
        return cust;
    }

    /**
     * Метод получения списка Customer
     * 
     * @return JSONArray
     */
    public JSONArray getCustomers() {
        var jsonArray = new JSONArray(repository.findAll());
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
        repository.saveAndFlush(cust);
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
        Customer entity = repository.findById(customerId).get();
        entity.setCustomerName(json.getString("customerName"));
        entity.setPhone(json.getString("phone"));
        repository.saveAndFlush(entity);
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
        repository.deleteById(customerId);
        var ex = repository.existsById(customerId);
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
