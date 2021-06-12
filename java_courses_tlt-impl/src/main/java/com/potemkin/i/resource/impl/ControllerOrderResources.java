package com.potemkin.i.resource.impl;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potemkin.i.converter.OrderConverter;

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
@Controller
@RequiredArgsConstructor
public class ControllerOrderResources implements OrderResources {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    /**
     * Метод получения сущности Order
     * 
     * @param id
     * @return String
     */
    @ResponseBody
    public String getOrder(@PathVariable("id") int id) {
        var dto = orderConverter.orderToDto(orderService.getOrder(id));
        var json = new JSONObject(dto);
        return json.toString();
    }

    /**
     * Метод получения списка Order с одинаковым Customer
     * 
     * @param customerId
     * @return String
     */
    @ResponseBody
    public String getOrders(@RequestParam(name = "customerId") int customerId) {
        var ords = orderService.getOrders(customerId);
        var dtos = orderConverter.orderToDto(ords);
        var jsonArray = new JSONArray(dtos);
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Order в базу данных
     * 
     * @param strOrd
     * @return String
     */
    @ResponseBody
    public String addOrder(@RequestBody String strOrd) {
        var json = new JSONObject(strOrd);
        log.info("ControllerOrderResources addOrder {}", json);
        var ord = orderConverter.parseForOrder(json);
        var ordResponse = orderService.addOrder(ord);
        var jsonOrd = new JSONObject(orderConverter.orderToDto(ordResponse));
        return jsonOrd.toString();
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
        var ord = orderConverter.parseForOrder(json);
        var dto = orderConverter.orderToDto(orderService.changeOrder(ord, id));
        var jsonResponse = new JSONObject(dto);
        return jsonResponse.toString();
    }

    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return String
     */
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = orderService.deleteById(id);
        log.info("ControllerOrderResources deleteById() {}", ex);
        String str = "{" + "\"Delete Order\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
