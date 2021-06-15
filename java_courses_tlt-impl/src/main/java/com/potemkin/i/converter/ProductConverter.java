package com.potemkin.i.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.service.impl.SupplierService;

import lombok.RequiredArgsConstructor;

/**
 * Конвертер ProductDTO в Product и обратно
 * 
 * @author Илья Пот
 *
 */
@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final SupplierService supplierService;

    /**
     * Метод преоброзования Product в ProductDTO
     * 
     * @param prod
     * @return ProductDTO
     */
    public ProductDTO productToDto(Product prod) {
        var dto = new ProductDTO();
        dto.setDiscountinued(prod.isDiscontinued());
        dto.setProductId(prod.getProductId());
        dto.setProductName(prod.getProductName());
        dto.setSupplierId(prod.getSupplier().getSupplierId());
        dto.setUnitPrice(prod.getUnitPrice());
        return dto;
    }

    /**
     * Метод преоброзования ProductDTO в Product
     * 
     * @param dto
     * @return Product
     */
    public Product dtoToProduct(ProductDTO dto) {
        var prod = new Product();
        prod.setProductId(dto.getProductId());
        prod.setDiscontinued(dto.isDiscountinued());
        prod.setProductName(dto.getProductName());
        var sup = new Supplier();
        sup.setSupplierId(dto.getSupplierId());
        prod.setSupplier(sup);
        prod.setUnitPrice(dto.getUnitPrice());
        return prod;
    }

    /**
     * Метод преоброзования List<ProductDTO> в List<Product>
     * 
     * @param dtos
     * @return List<Product>
     */
    public List<Product> dtoToProduct(List<ProductDTO> dtos) {
        var prods = dtos.stream().map(dto -> dtoToProduct(dto)).collect(Collectors.toList());
        return prods;
    }

    /**
     * Метод преоброзования List<ProductDTO> в List<Product>
     * 
     * @param prods
     * @return
     */
    public List<ProductDTO> productToDto(List<Product> prods) {
        var dtos = prods.stream().map(prod -> productToDto(prod)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * Метод разбора JSONObject для создания Product
     * 
     * @param json
     * @return Product
     */
    public Product parseForProduct(JSONObject json) {
        var prod = new Product();
        if (json != null) {
            prod.setProductName(json.getString("productName"));
            prod.setDiscontinued(json.getBoolean("isDiscontinued"));
            prod.setUnitPrice(json.getDouble("unitPrice"));
            var sup = supplierService.getSupplier(json.getInt("supplierId"));
            prod.setSupplier(sup);
        }
        return prod;
    }
}
