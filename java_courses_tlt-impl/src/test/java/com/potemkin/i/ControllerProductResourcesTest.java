package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.potemkin.i.converter.ProductConverter;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.resource.impl.ControllerProductResources;
import com.potemkin.i.service.impl.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования ControllerProductResources
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class ControllerProductResourcesTest {

    @Mock
    private ProductService productService;
    @Mock
    private ProductConverter productConverter;
    @InjectMocks
    private ControllerProductResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        when(productService.getProduct(0)).thenReturn(prod);
        when(productConverter.productToDto(prod)).thenReturn(prodDTO);
        resources.getProduct(0);
        verify(productService).getProduct(eq(0));
    }

    @Test
    public void getProductsTest() {
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        List<ProductDTO> listDTO = new ArrayList<>();
        listDTO.add(new ProductDTO());
        when(productService.getProducts()).thenReturn(list);
        when(productConverter.productToDto(list)).thenReturn(listDTO);
        var prods = resources.getProducts();
        log.info("getProductsTest() - {}", prods);
        verify(productService).getProducts();
    }

    @Test
    public void addProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
//        JSONObject json = new JSONObject(
//                "{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
//                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        when(productService.addProduct(any())).thenReturn(prod);
        when(productConverter.productToDto(prod)).thenReturn(prodDTO);
        var str = resources
                .addProduct("{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                        + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        log.info("addProductTest() - {}", str);
        verify(productService).addProduct(any());
    }

    @Test
    public void changeProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        var json = new JSONObject("{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}");
        when(productService.changeEntity(any(), eq(0))).thenReturn(prod);
        when(productConverter.productToDto(prod)).thenReturn(prodDTO);
        resources.changeProduct("{\r\n" + "    \"productName\" : \"TRUSARDI\",\r\n" + "    \"supplierId\" : 1,\r\n"
                + "    \"unitPrice\" : 1000.12,\r\n" + "    \"isDiscontinued\" : true\r\n" + "}", 0);
        verify(productService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        var json = new JSONObject("{\"Delete Product\":\" true \" }");
        when(productService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(productService).deleteById(eq(0));
    }
}
