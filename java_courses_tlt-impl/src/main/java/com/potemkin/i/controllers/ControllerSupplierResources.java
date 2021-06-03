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

import com.potemkin.i.service.SupplierService;

@Controller()
@RequestMapping("/Supplier")
public class ControllerSupplierResources { //implements ControllerSupResources
    
    @Autowired
    private SupplierService service;
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSupplier(@PathVariable("id") int id) {
       var json = service.getSupplierJson(id);
       return json.toString();
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSuppliers() {
        var jsonArray = service.getSuppliers();
        return jsonArray.toString();
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addSupplier(@RequestBody String strSup) {
        var json = new JSONObject(strSup);
        return service.addSupplier(json).toString();
    }
    
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeSupplier(@RequestBody String strSup, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strSup);
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
