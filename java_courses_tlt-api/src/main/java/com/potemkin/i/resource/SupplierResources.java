package com.potemkin.i.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Интрефейс SupplierResources для операций Rest с сущностью Supplier
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Supplier")
public interface SupplierResources {

    /**
     * Метод получения Supplier по id
     * 
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSupplier(int id);

    /**
     * Метод получения списка Supplier
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSuppliers();

    /**
     * Метод добавления Supplier
     * 
     * @param strSup
     * @return Supplier
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addSupplier(String strSup);

    /**
     * Метод удаления Supplier по id
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);
}
