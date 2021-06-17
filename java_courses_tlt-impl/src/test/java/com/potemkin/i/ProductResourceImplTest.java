package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.resource.impl.ProductResourceImpl;
import com.potemkin.i.service.impl.ProductServiceImpl;

/**
 * Класс тестирования ProductResourceImpl
 * 
 * @author Илья Пот
 *
 */
public class ProductResourceImplTest {

    @Mock
    private ProductServiceImpl productService;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private ProductResourceImpl resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        when(conversionService.convert(any(), eq(Product.class))).thenReturn(prod);
        when(conversionService.convert(prod, ProductDTO.class)).thenReturn(prodDTO);
        when(productService.getProduct(0)).thenReturn(prod);
        resources.getProduct(0);
        verify(productService).getProduct(eq(0));
    }

    @Test
    public void getProductsTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        List<Product> list = new ArrayList<>();
        list.add(new Product());
        List<ProductDTO> listDTO = new ArrayList<>();
        listDTO.add(new ProductDTO());
        when(conversionService.convert(prod, ProductDTO.class)).thenReturn(prodDTO);
        when(productService.getProducts()).thenReturn(list);
        resources.getProducts();
        verify(productService).getProducts();
    }

    @Test
    public void addProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        when(conversionService.convert(any(), eq(Product.class))).thenReturn(prod);
        when(conversionService.convert(prod, ProductDTO.class)).thenReturn(prodDTO);
        when(productService.addProduct(prod)).thenReturn(prod);
        resources.addProduct(prodDTO);
        verify(productService).addProduct(prod);
    }

    @Test
    public void changeProductTest() {
        var prod = new Product();
        var prodDTO = new ProductDTO();
        when(conversionService.convert(any(), eq(Product.class))).thenReturn(prod);
        when(conversionService.convert(prod, ProductDTO.class)).thenReturn(prodDTO);
        when(productService.changeEntity(any(), eq(0))).thenReturn(prod);
        resources.changeProduct(prodDTO, 0);
        verify(productService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        when(productService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(productService).deleteById(eq(0));
    }
}
