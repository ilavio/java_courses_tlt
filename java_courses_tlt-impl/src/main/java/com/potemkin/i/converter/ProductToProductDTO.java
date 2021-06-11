package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.ProductDTO;

/**
 * Класс ProductToProductDTO конвертер из Product в ProductDTO
 * 
 * @author Илья Пот
 *
 */
public class ProductToProductDTO implements Converter<Product, ProductDTO> {

    /**
     * Метод преоброзования из Product в ProductDTO
     * 
     * @param Product
     * @return ProductDTO
     */
    @Override
    public ProductDTO convert(Product source) {
        var prodDTO = new ProductDTO();
        prodDTO.setDiscountinued(source.isDiscontinued());
        prodDTO.setProductId(source.getProductId());
        prodDTO.setSupplierId(source.getSupplier().getSupplierId());
        prodDTO.setUnitPrice(source.getUnitPrice());
        prodDTO.setProductName(source.getProductName());
        return prodDTO;
    }
}
