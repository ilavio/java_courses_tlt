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

import com.potemkin.i.converter.SupplierConverter;
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
@Controller
@RequiredArgsConstructor
public class ControllerSupplierResources implements SupplierResources {

    private final SupplierService supplierService;
    private final SupplierConverter supplierConverter;

    /**
     * Метод получения сущности Supplier
     * 
     * @param id
     * @return String
     */
    @ResponseBody
    public String getSupplier(@PathVariable("id") int id) {
        var dto = supplierConverter.supplierToDTO(supplierService.getSupplier(id));
        var json = new JSONObject(dto);
        log.info("ControllerSupplierResources getSupplier() {}", dto);
        return json.toString();
    }

    /**
     * Метод получения списка Supplier
     * 
     * @return String
     */
    @ResponseBody
    public String getSuppliers() {
        var dtos = supplierConverter.supplierToDTO(supplierService.getSuppliers());
        var jsonArray = new JSONArray(dtos);
        return jsonArray.toString();
    }

    /**
     * Метод добавление сущности Supplier в базу данных
     * 
     * @param strSup
     * @return String
     */
    @ResponseBody
    public String addSupplier(@RequestBody String strSup) {
        var json = new JSONObject(strSup);
        var sup = supplierService.addSupplier(supplierConverter.parseForSupplier(json));
        var supDTO = supplierConverter.supplierToDTO(sup);
        var jsonDTO = new JSONObject(supDTO);
        log.info("ControllerSupplierResources addSupplier() {}", jsonDTO);
        return jsonDTO.toString();
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
        var sup = supplierConverter.parseForSupplier(json);
        var dto = supplierConverter.supplierToDTO(supplierService.changeEntity(sup, id));
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
        var ex = supplierService.deleteById(id);
        String str = "{" + "\"Delete Supplier\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
