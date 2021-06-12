package com.potemkin.i.resource.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potemkin.i.converter.CustomerConverter;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.resource.CustomerResources;
import com.potemkin.i.service.impl.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ControllerCustomerResources обработки запросов и взаимодествия с
 * сущьностью Customer
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class ControllerCustomerResources implements CustomerResources {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    /**
     * Метод получения сущности Customer
     * 
     * @param id
     * @return
     */
    @ResponseBody
    public String getCustomer(@PathVariable("id") int id) {
        CustomerDTO dto = customerConverter.customerToDto(customerService.getCustomer(id));
        var json = new JSONObject(dto);
        return json.toString();
    }

    /**
     * Метод получения списка Customer
     * 
     * @return
     */
    @ResponseBody
    public String getCustomers() {
        List<Customer> custs = customerService.getCustomers();
        var dtos = customerConverter.customerToDto(custs);
        var jsonArray = new JSONArray(dtos);
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Customer в базу данных
     * 
     * @param strCust
     * @return
     */
    @ResponseBody
    public String addCustomer(@RequestBody String strCust) {
        var json = new JSONObject(strCust);
        var cust = customerService.addCustomer(customerConverter.parseForCustomer(json));
        var jsonCust = new JSONObject(customerConverter.customerToDto(cust));
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
        var cust = customerConverter.parseForCustomer(json);
        var dto = customerConverter.customerToDto(customerService.changeEntity(cust, id));
        var jsonResponse = new JSONObject(dto);
        return jsonResponse.toString();
    }

    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return
     */
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = customerService.deleteById(id);
        String str = "{" + "\"Delete Customer\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
