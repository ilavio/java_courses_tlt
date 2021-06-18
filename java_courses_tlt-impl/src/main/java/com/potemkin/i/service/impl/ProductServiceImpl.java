package com.potemkin.i.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс ProductServiceImpl обслуживания сущностей Supplier
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;

    /**
     * Метод добавления в базу данных Product
     * 
     * @param Product
     * @return Product
     */
    public Product addProduct(Product prod) {
        productRepository.save(prod);
        log.info("ProductService addProduct {}", prod);
        return prod;
    }

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return Product
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
        List<Product> prods = new ArrayList<>();
        var iter = productRepository.findAll();
        iter.forEach(x -> prods.add(x));
        log.info("ProductService getProducts() {}", prods);
        return prods;
    }

    /**
     * Метод изменения сущности Product
     * 
     * @param Product
     * @param productId
     * @return Product
     */
    public Product changeEntity(Product prod, int productId) {
        var entity = productRepository.findById(productId).get();
        entity.setDiscontinued(prod.getIsDiscontinued());
        entity.setProductName(prod.getProductName());
        entity.setUnitPrice(prod.getUnitPrice());
        productRepository.save(entity);
        log.info("ProductService changeEntity {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Product
     * 
     * @param productId
     * @return boolean
     */
    public boolean deleteById(int productId) {
        productRepository.deleteById(productId);
        var ex = productRepository.existsById(productId);
        if (ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
}
