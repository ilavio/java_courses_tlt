package com.potemkin.i.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ProductService обслуживания сущностей Product
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierService supplierService;

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return Customer
     */
    public Product getProduct(Integer id) {
        var prod = productRepository.findById(id).get();
        return prod;
    }

    /**
     * Метод получения списка Product
     * 
     * @return List<Product>
     */
    public List<Product> getProducts() {
        List<Product> prods = productRepository.findAll();
        log.info("ProductService getProducts() {}", prods);
        return prods;
    }

    /**
     * Метод добавления в базу данных Product
     * 
     * @param json
     * @return Product
     */
    public Product addProduct(Product prod) {
        productRepository.saveAndFlush(prod);
        log.info("ProductService addProduct {}", prod);
        return prod;
    }

    /**
     * Метод изменения сущности Product
     * 
     * @param json
     * @param productId
     * @return JSONObject
     */
    public Product changeEntity(Product prod, int productId) {
        var entity = productRepository.findById(productId).get();
        entity.setDiscontinued(prod.isDiscontinued());
        entity.setProductName(prod.getProductName());
        entity.setUnitPrice(prod.getUnitPrice());
        productRepository.saveAndFlush(entity);
        log.info("ProductService changeEntity {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Product
     * 
     * @param productId
     * @return
     */
    public boolean deleteById(int productId) {
        productRepository.deleteById(productId);
        var ex = productRepository.existsById(productId);
        if(ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
}
