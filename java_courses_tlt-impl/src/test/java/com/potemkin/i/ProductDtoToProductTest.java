package com.potemkin.i;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.ProductDtoToProduct;
import com.potemkin.i.converter.ProductToProductDTO;
import com.potemkin.i.dto.ProductDTO;

public class ProductDtoToProductTest {
    
    private ProductDtoToProduct productDtoToProduct;
    private ProductToProductDTO productToProductDTO;
    
    @BeforeEach
    public void masking() {
        productDtoToProduct = new ProductDtoToProduct();
        productToProductDTO = new ProductToProductDTO();
    }
    
    @Test
    public void test() {
        var dto = new ProductDTO();
        dto.setDiscountinued(false);
        dto.setProductId(1);
        dto.setProductName("Y");
        dto.setSupplierId(1);
        dto.setUnitPrice(1.1);
        var prod = productDtoToProduct.convert(dto);
        var dtoTest = productToProductDTO.convert(prod);
        assertEquals(prod.getProductId(), dtoTest.getProductId());
    }
}
