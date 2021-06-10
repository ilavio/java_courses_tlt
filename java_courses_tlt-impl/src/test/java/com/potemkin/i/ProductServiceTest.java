package com.potemkin.i;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

import com.potemkin.i.repository.stub.ProductRepositoryStub;
import com.potemkin.i.repository.stub.SupplierRepositoryStub;
import com.potemkin.i.service.impl.ProductService;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования ProductService
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class ProductServiceTest {
    private ProductRepositoryStub repositoryStub;
    private SupplierRepositoryStub repositorySupStub;
    private SupplierService supplierService;
    private ProductService productService;

    @BeforeEach
    public void maskingObjects() {
        repositorySupStub = new SupplierRepositoryStub();
        repositoryStub = new ProductRepositoryStub();
        supplierService = new SupplierService(repositorySupStub);
        productService = new ProductService(repositoryStub, supplierService);
        var json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        supplierService.addSupplier(json);
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        productService.addProduct(null);
    }

    @Test
    public void getProductJsonTets() {
        var json = productService.getProductJson(0);
        log.info("getProductJsonTets() - {}", json);
        assertEquals(json.toString(), productService.getProductJson(0).toString());
    }
    
    @Test
    public void getProductTest() {
        var prod = productService.getProduct(0);
        log.info("getProductTest() - {}", prod);
        assertEquals(prod, productService.getProduct(0));
    }
    
    @Test
    public void getProductsTest() {
        var prods = productService.getProducts();
        log.info("getProductsTest() - {}", prods);
        assertEquals(prods.toString(), productService.getProducts().toString());
    }
    
    @Test
    public void changeEntityTest() {
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        var json = productService.changeEntity(jsonProd, 0);
        assertNotNull(json);
    }
    
    @Test
    public void parseForProductTest() {
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        var prod = productService.parseForProduct(jsonProd);
        assertNotNull(prod);
    }
    
    @Test
    public void deleteByIdTest() {
        var json = productService.deleteById(0);
        log.info("deleteByIdTest() - {}", json);
        assertTrue(json.toString().contains("false"));
    }
    
    @Test
    public void minorTest() {
        repositoryStub.count();
        var supOp = Optional.of(productService.getProduct(0));
        repositoryStub.count(Example.of(productService.getProduct(0)));
        repositoryStub.delete(productService.getProduct(0));
        repositoryStub.deleteAll();
        repositoryStub.deleteAll(null);
        repositoryStub.deleteAllById(null);
        repositoryStub.deleteAllInBatch();
        repositoryStub.deleteAllByIdInBatch(null);
        repositoryStub.findAll(Example.of(productService.getProduct(0)));
        repositoryStub.findAllById(null);
        repositoryStub.findOne(null);
        repositoryStub.flush();
        repositoryStub.save(null);
        repositoryStub.saveAll(null);
        repositoryStub.saveAllAndFlush(null);
        assertFalse(repositoryStub.exists(null));
    }
}
