package com.potemkin.i;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potemkin.i.converter.ProductDtoToProduct;
import com.potemkin.i.converter.ProductToProductDTO;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.resource.impl.ProductResourceImpl;
import com.potemkin.i.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования ProductResourceImpl
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@WebMvcTest(ProductResourceImpl.class)
@ContextConfiguration(classes = { ProductServiceImpl.class, ProductResourceImpl.class, ProductDtoToProduct.class,
        ProductToProductDTO.class })
public class ProductResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getProductTest() throws Exception {
        var prod = createProduct();
        when(productService.getProduct(1)).thenReturn(prod);
        mockMvc.perform(MockMvcRequestBuilders.get("/Product/1")).andExpect(status().isOk());
    }

    @Test
    public void getProductsTest() throws Exception {
        var prod = createProduct();
        List<Product> list = new ArrayList<>();
        list.add(prod);
        when(productService.getProducts()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/Product")).andExpect(status().isOk());
    }

    @Test
    public void addProductTest() throws Exception {
        var prod = createProduct();
        var dto = createDTO();
        when(productService.addProduct(any())).thenReturn(prod);
        mockMvc.perform(MockMvcRequestBuilders.post("/Product").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void changeProductTest() throws Exception {
        var prod = createProduct();
        var dto = createDTO();
        when(productService.changeEntity(prod, 1)).thenReturn(prod);
        mockMvc.perform(MockMvcRequestBuilders.put("/Product").accept(MediaType.APPLICATION_JSON).param("id", "1")
                .content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void deleteByIdTest() throws Exception {
        when(productService.deleteById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/Product").param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    public Product createProduct() {
        var prod = new Product();
        var sup = new Supplier();
        sup.setSupplierId(1);
        prod.setDiscontinued(false);
        prod.setProductId(1);
        prod.setProductName("Y");
        prod.setUnitPrice(1.1);
        prod.setSupplier(sup);
        return prod;
    }
    
    public ProductDTO createDTO() {
        var dto = new ProductDTO();
        dto.setDiscountinued(false);
        dto.setProductId(1);
        dto.setProductName("Y");
        dto.setSupplierId(1);
        dto.setUnitPrice(1.1);
        return dto;
    }
}
