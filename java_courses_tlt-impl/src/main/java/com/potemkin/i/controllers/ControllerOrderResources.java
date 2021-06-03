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

import com.potemkin.i.service.OrderService;
import com.potemkin.i.controllers.ControllerOrdResources;

@Controller()
@RequestMapping("/Orders")
public class ControllerOrderResources implements ControllerOrdResources { //
    
    @Autowired
    private OrderService service;
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOrder(@PathVariable("id") int id) {
       var json = service.getOrderJson(id);
       return json.toString();
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOrders(@RequestParam(name = "customerId") int customerId) {
        var jsonArray = service.getOrders(customerId);
        return jsonArray.toString();
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addOrder(@RequestBody String strOrd) {
        var json = new JSONObject(strOrd);
        return service.addOrder(json).toString();
    }
    
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeOrder(@RequestBody String strOrd, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strOrd);
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
