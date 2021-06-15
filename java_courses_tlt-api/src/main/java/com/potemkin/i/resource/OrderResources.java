package com.potemkin.i.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Интрефейс OrderResources для операций Rest с сущностью Order
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Orders")
public interface OrderResources {

    /**
     * Метод запроса Order по id
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrder(int id);

    /**
     * Метод получения списка Order по customerId
     * 
     * @param customerId
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrders(int customerId);

    /**
     * Метод добавления Order
     * 
     * @param strOrd
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addOrder(String strOrd);

    /**
     * Метод удаления Order по id
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);
}
