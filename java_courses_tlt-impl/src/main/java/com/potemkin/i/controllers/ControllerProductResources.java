package com.potemkin.i.controllers;

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

import com.potemkin.i.service.ProductService;
import com.potemkin.i.controllers.ControllerProdResources;

@Controller()
@RequestMapping("/Product")
public class ControllerProductResources implements ControllerProdResources { //

    @Autowired
    private ProductService service;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProduct(@PathVariable("id") int id) {
        var json = service.getProductJson(id);
        return json.toString();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getProducts() {
        var jsonArray = service.getProducts();
        return jsonArray.toString();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addProduct(@RequestBody String strProd) {
        var json = new JSONObject(strProd);
        return service.addProduct(json).toString();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeProduct(@RequestBody String strProd, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strProd);
        var jsonResponse = service.changeEntity(json, id);
        return jsonResponse.toString();
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = service.deleteById(id);
        return ex.toString();
    }
}
