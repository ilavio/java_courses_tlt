package com.potemkin.i.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Интрефейс CustomerResources для операций Rest с сущностью Customer
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Customers")
public interface CustomerResources {

    /**
     * Метод запроса Customer по id
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomer(int id);

    /**
     * Метод запроса списка Customer
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomers();

    /**
     * Метод удаления Customer
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);

    /**
     * Метод добавления Customer
     * 
     * @param strCust
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addCustomer(String strCust);
}
