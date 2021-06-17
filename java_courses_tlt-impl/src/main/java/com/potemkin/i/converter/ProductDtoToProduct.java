package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.ProductDTO;

public class ProductDtoToProduct implements Converter<ProductDTO, Product> {

    @Override
    public Product convert(ProductDTO source) {
        var prod = new Product();
        prod.setDiscontinued(source.isDiscountinued());
        prod.setProductName(source.getProductName());
        if (source.getSupplierId() != null && source.getSupplierId() != 0) {
            var sup = new Supplier();
            sup.setSupplierId(source.getSupplierId());
            prod.setSupplier(sup);
        }
        prod.setUnitPrice(source.getUnitPrice());
        return prod;
    }

}
