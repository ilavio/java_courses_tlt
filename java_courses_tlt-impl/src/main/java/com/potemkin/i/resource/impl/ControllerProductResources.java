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
import com.potemkin.i.resource.ProductResources;
import com.potemkin.i.service.impl.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ControllerProductResources обработки запросов и взаимодествия с
 * сущьностью Product
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Controller()
@RequestMapping("/Product")
@RequiredArgsConstructor
public class ControllerProductResources implements ProductResources {

    private final ProductService productService;

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProduct(@PathVariable("id") int id) {
        var json = productService.getProductJson(id);
        return json.toString();
    }

    /**
     * Метод получения списка Product
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProducts() {
        var jsonArray = productService.getProducts();
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Product в базу данных
     * 
     * @param strProd
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addProduct(@RequestBody String strProd) {
        var json = new JSONObject(strProd);
        return productService.addProduct(json).toString();
    }

    /**
     * Метод изменения сущности Product
     * 
     * @param strProd
     * @param id
     * @return String
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeProduct(@RequestBody String strProd, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strProd);
        var jsonResponse = productService.changeEntity(json, id);
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
        var ex = productService.deleteById(id);
        log.info("ControllerProductResources deleteById() - {}", ex);
        return ex.toString();
    }
}
