package com.potemkin.i.resource.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potemkin.i.repository.CustomerRepository;
import com.potemkin.i.resource.OrderResources;
import com.potemkin.i.service.impl.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ControllerOrderResources обработки запросов и взаимодествия с
 * сущьностью Order
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Controller()
@RequestMapping("/Orders")
@RequiredArgsConstructor
public class ControllerOrderResources implements OrderResources {

    private final OrderService orderService;

    /**
     * Метод получения сущности Order
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOrder(@PathVariable("id") int id) {
        var json = orderService.getOrderJson(id);
        return json.toString();
    }

    /**
     * Метод получения списка Order с одинаковым Customer
     * 
     * @param customerId
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOrders(@RequestParam(name = "customerId") int customerId) {
        var jsonArray = orderService.getOrders(customerId);
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Order в базу данных
     * 
     * @param strOrd
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addOrder(@RequestBody String strOrd) {
        var json = new JSONObject(strOrd);
        return orderService.addOrder(json).toString();
    }

    /**
     * Метод изменения сущности Order
     * 
     * @param strOrd
     * @param id
     * @return String
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeOrder(@RequestBody String strOrd, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strOrd);
        var jsonResponse = orderService.changeEntity(json, id);
        return jsonResponse.toString();
    }

    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = orderService.deleteById(id);
        log.info("ControllerOrderResources deleteById() {}", ex);
        return ex.toString();
    }
}
