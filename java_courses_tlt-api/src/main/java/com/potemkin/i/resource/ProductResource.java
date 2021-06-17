package com.potemkin.i.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.potemkin.i.dto.ProductDTO;

/**
 * Интрефейс ProductResource для операций Rest с сущностью Product
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Product")
public interface ProductResource {

    /**
     * Метод добавления Product
     * @param dto
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO addProduct(ProductDTO dto);

    /**
     * Метод получения Product по id
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getProduct(int id);

    /**
     * Метод получения списка Product
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProducts();

    /**
     * Метод изменения Product по id
     * @param dto
     * @param id
     * @return
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO changeProduct(ProductDTO dto, int id);

    /**
     * Метод удаления Product по id
     * @param id
     * @return
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);
}
