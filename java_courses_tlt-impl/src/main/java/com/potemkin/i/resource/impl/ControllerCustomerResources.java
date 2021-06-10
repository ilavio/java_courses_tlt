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

import com.potemkin.i.resource.CustomerResources;
import com.potemkin.i.service.impl.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс ControllerCustomerResources обработки запросов и взаимодествия с
 * сущьностью Customer
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Controller()
@RequestMapping("/Customers")
public class ControllerCustomerResources implements CustomerResources {

    private final CustomerService customerService;

    @Autowired
    public ControllerCustomerResources(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Метод получения сущности Customer
     * 
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCustomer(@PathVariable("id") int id) {
        var json = customerService.getCustomerJson(id);
        return json.toString();
    }

    /**
     * Метод получения списка Customer
     * 
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCustomers() {
        var jsonArray = customerService.getCustomers();
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Customer в базу данных
     * 
     * @param strCust
     * @return
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addCustomer(@RequestBody String strCust) {
        var json = new JSONObject(strCust);
        var jsonCust = customerService.addCustomer(json);
        log.info("ControllerCustomerResources addCustomer() - {}", jsonCust);
        return jsonCust.toString();
    }

    /**
     * Метод изменения сущности Customer
     * 
     * @param strCust
     * @param id
     * @return
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeCustomer(@RequestBody String strCust, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strCust);
        var jsonResponse = customerService.changeEntity(json, id);
        return jsonResponse.toString();
    }

    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = customerService.deleteById(id);
        return ex.toString();
    }
}
