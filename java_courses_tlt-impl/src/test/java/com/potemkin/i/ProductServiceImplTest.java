package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.ProductRepository;
import com.potemkin.i.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    
    @Test
    public void addProductTest() {
        var prod = new Product();
        prod.setProductId(1);
        prod.setProductName("L");
        productServiceImpl.addProduct(prod);
        var prodTest = productServiceImpl.getProduct(1);
        log.info("addProductTest() {}", prodTest);
        assertEquals(prod, prodTest);
    }
    
    @Test
    public void getAndDeleteTest() {
        var prod = new Product();
        prod.setProductId(2);
        prod.setProductName("L");
        productServiceImpl.addProduct(prod);
        var prods = productServiceImpl.getProducts();
        var ex = productServiceImpl.deleteById(2);
        log.info("getAndDeleteTest() {}", ex);
        assertNotNull(ex);
    }
    
    @TestConfiguration
    static class MyTestConfig {
        @Bean
        public ProductServiceImpl productServiceImpl(ProductRepository productRepository) {
            return new ProductServiceImpl(productRepository);
        }
    }
}
