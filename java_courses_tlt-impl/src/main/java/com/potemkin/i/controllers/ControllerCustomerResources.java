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

import com.potemkin.i.service.CustomerService;

@Controller()
@RequestMapping("/Customers")
public class ControllerCustomerResources {
    
    @Autowired
    private CustomerService customerService;
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCustomer(@PathVariable("id") int id) {
       var json = customerService.getCustomerJson(id);
       return json.toString();
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getCustomers() {
        var jsonArray = customerService.getCustomers();
        return jsonArray.toString();
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addCustomer(@RequestBody String strCust) {
        var json = new JSONObject(strCust);
        return customerService.addCustomer(json).toString();
    }
    
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeCustomer(@RequestBody String strCust, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strCust);
        var jsonResponse = customerService.changeEntity(json, id);
        return jsonResponse.toString();
    }
    
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = customerService.deleteById(id);
        return ex.toString();
    }
}
