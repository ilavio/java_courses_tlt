package com.potemkin.i.repository.hard;

import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.interf.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component
public class ProductRepositoryHard implements ProductRepository {
    private Product prod = new Product();

    
    public void addProduct(Product prod, int supplierId) {
        prod.setDiscontinued(true);
        prod.setProductId(0);
        prod.setProductName("LOCAL");
        prod.setUnitPrice(10.01);
        log.info("Добавление Product: {}", prod);
    }

    
    public Product getProduct(int productId) {
        log.info("Получение Product: {}", prod);
        return prod;
    }

   
    public void deleteProduct(int productId) {
        prod.setDiscontinued(true);
        prod.setProductId(0);
        prod.setProductName("");
        prod.setUnitPrice(0);
        log.info("Удаление Product: {}", prod);
    }

    
    public Product parseForProduct(JSONObject json) {
        return prod;
    }
    
    
}
