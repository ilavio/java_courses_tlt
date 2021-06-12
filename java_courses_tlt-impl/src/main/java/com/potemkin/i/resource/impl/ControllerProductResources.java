package com.potemkin.i.resource.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potemkin.i.converter.ProductConverter;
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
@Controller
@RequiredArgsConstructor
public class ControllerProductResources implements ProductResources {

    private final ProductService productService;
    private final ProductConverter productConverter;

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return String
     */
    @ResponseBody
    public String getProduct(@PathVariable("id") int id) {
        var dto = productConverter.productToDto(productService.getProduct(id));
        var json = new JSONObject(dto);
        return json.toString();
    }

    /**
     * Метод получения списка Product
     * 
     * @return String
     */
    @ResponseBody
    public String getProducts() {
        var prods = productService.getProducts();
        var dtos = productConverter.productToDto(prods);
        var jsonArray = new JSONArray(dtos);
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Product в базу данных
     * 
     * @param strProd
     * @return String
     */
    @ResponseBody
    public String addProduct(@RequestBody String strProd) {
        var json = new JSONObject(strProd);
        var prod = productConverter.parseForProduct(json);
        var jsonCust = new JSONObject(productConverter.productToDto(productService.addProduct(prod)));
        log.info("ControllerProductResources addProduct() - {}", jsonCust);
        return jsonCust.toString();
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
        var prod = productConverter.parseForProduct(json);
        var dto =  productConverter.productToDto(productService.changeEntity(prod, id));
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
        var ex = productService.deleteById(id);
        log.info("ControllerProductResources deleteById() - {}", ex);
        String str = "{" + "\"Delete Product\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
