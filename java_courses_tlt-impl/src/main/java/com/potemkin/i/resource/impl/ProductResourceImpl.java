package com.potemkin.i.resource.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.resource.ProductResource;
import com.potemkin.i.service.impl.ProductServiceImpl;

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
@RestController
@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {
    private final ProductServiceImpl productServiceImpl;
    private final ConversionService conversionService;

    /**
     * Метод добавление сущности Product в базу данных
     * 
     * @param dto
     * @return ProductDTO
     */
    public ProductDTO addProduct(@RequestBody ProductDTO dto) {
        log.info("ProductResourceImpl addProduct() - {}", dto);
        var prod = conversionService.convert(dto, Product.class);
        log.info("ProductResourceImpl addProduct() - {}", prod);
        var prodDTOresp = conversionService.convert(productServiceImpl.addProduct(prod), ProductDTO.class);
        return prodDTOresp;
    }

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return ProductDTO
     */
    public ProductDTO getProduct(@PathVariable("id") int id) {
        var dto = conversionService.convert(productServiceImpl.getProduct(id), ProductDTO.class);
        log.info("ProductResourceImpl getProduct() {}", dto);
        return dto;
    }

    /**
     * Метод получения списка Product
     * 
     * @return List<ProductDTO>
     */
    public List<ProductDTO> getProducts() {
        var prods = productServiceImpl.getProducts();
        var prodDTOs = prods.stream().map(prod -> conversionService.convert(prod, ProductDTO.class))
                .collect(Collectors.toList());
        return prodDTOs;
    }

    /**
     * Метод изменения сущности Product
     * 
     * @param dto
     * @param id
     * @return ProductDTO
     */
    public ProductDTO changeProduct(@RequestBody ProductDTO dto, @RequestParam(name = "id") int id) {
        var prod = conversionService.convert(dto, Product.class);
        var prodDTO = conversionService.convert(productServiceImpl.changeEntity(prod, id), ProductDTO.class);
        log.info("ProductResourceImpl changeProduct() {}", prodDTO);
        return prodDTO;
    }

    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return String
     */
    public ResponseEntity<?> deleteById(@RequestParam(name = "id") int id) {
        var ex = productServiceImpl.deleteById(id);
        log.info("ProductResourcesImpl deleteById() - {}", ex);
        if (ex) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
