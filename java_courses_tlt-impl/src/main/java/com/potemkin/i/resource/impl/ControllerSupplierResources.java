package com.potemkin.i.resource.impl;

import org.json.JSONObject;
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

import com.potemkin.i.resource.SupplierResources;
import com.potemkin.i.service.impl.SupplierService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ControllerSupplierResources обработки запросов и взаимодествия с
 * сущьностью Supplier
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Controller()
@RequestMapping("/Supplier")
@RequiredArgsConstructor
public class ControllerSupplierResources  implements SupplierResources {

    private final SupplierService supplierService;

    /**
     * Метод получения сущности Supplier
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSupplier(@PathVariable("id") int id) {
        var json = supplierService.getSupplierJson(id);
        log.info("ControllerSupplierResources getSupplier() {}", json);
        return json.toString();
    }

    /**
     * Метод получения списка Supplier
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSuppliers() {
        var jsonArray = supplierService.getSuppliers();
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Supplier в базу данных
     * 
     * @param strSup
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addSupplier(@RequestBody String strSup) {
        var json = new JSONObject(strSup);
        log.info("ControllerSupplierResources addSupplier() {}", json);
        return supplierService.addSupplier(json).toString();
    }

    /**
     * Метод изменения сущности Supplier
     * 
     * @param strSup
     * @param id
     * @return String
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeSupplier(@RequestBody String strSup, @RequestParam(name = "id") int id) {
        var json = new JSONObject(strSup);
        var jsonResponse = supplierService.changeEntity(json, id);

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
        var ex = supplierService.deleteById(id);
        return ex.toString();
    }
}
