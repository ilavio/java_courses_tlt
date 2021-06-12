package com.potemkin.i.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Интрефейс CustomerResources для операций Rest с сущностью Customer
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Product")
public interface ProductResources {

    /**
     * Метод получения Product по id
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProduct(int id);

    /**
     * Метод получения списка Product
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProducts();

    /**
     * Метод добавления Product
     * 
     * @param strProd
     * @return String
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addProduct(String strProd);

    /**
     * Метод удаления Product по id
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);
}
