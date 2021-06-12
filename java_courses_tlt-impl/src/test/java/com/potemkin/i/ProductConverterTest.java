package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.ProductConverter;
import com.potemkin.i.converter.SupplierConverter;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.stub.ProductRepositoryStub;
import com.potemkin.i.repository.stub.SupplierRepositoryStub;
import com.potemkin.i.service.impl.ProductService;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductConverterTest {
    
    private ProductRepositoryStub repositoryStub;
    private SupplierRepositoryStub repositorySupStub;
    private SupplierService supplierService;
    private ProductService productService;
    private ProductConverter productConverter;
    private SupplierConverter supplierConverter;

    @BeforeEach
    public void maskingObjects() {
        repositorySupStub = new SupplierRepositoryStub();
        repositoryStub = new ProductRepositoryStub();
        supplierService = new SupplierService(repositorySupStub);
        productService = new ProductService(repositoryStub, supplierService);
        productConverter = new ProductConverter(supplierService);
        supplierConverter = new SupplierConverter();
        var json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var sup = supplierConverter.parseForSupplier(json);
        supplierService.addSupplier(sup);
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        var prod = productConverter.parseForProduct(jsonProd);
        productService.addProduct(prod);
    }
    
    @Test
    public void productToDto() {
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        var prod = productConverter.parseForProduct(jsonProd);
        var dto = productConverter.productToDto(prod);
        var prodTest = productConverter.dtoToProduct(dto);
        log.info("ProductConverterTest productToDto() {}; {}", prod, prodTest);
        assertNotNull(prodTest);
    }
    
    @Test
    public void dtoToProductTest() {
        var jsonProd = new JSONObject(
                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        var prod = productConverter.parseForProduct(jsonProd);
        List<Product> list = new ArrayList<>();
        list.add(prod);
        var prodsDTO = productConverter.productToDto(list);
        var listTest = productConverter.dtoToProduct(prodsDTO);
        log.info("ProductConverterTest dtoToProductTest() {}; {}", list, listTest);
        assertNotNull(listTest);
    }
}
