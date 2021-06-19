package com.potemkin.i.converter;

import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;

/**
 * Класс SupplierToSupplierDTO конвертер из Supplier в SupplierDTO
 * 
 * @author Илья Пот 
 * 
 */
@Component
public class SupplierToSupplierDTO implements Converter<Supplier, SupplierDTO> {

    /**
     * Метод конвертации из Supplier в SupplierDTO
     * 
     * @param Supplier
     * @return SupplierDTO
     */
    @Override
    public SupplierDTO convert(Supplier source) {
        var supDTO = new SupplierDTO();
        supDTO.setCompanyName(source.getCompanyName());
        supDTO.setPhone(source.getPhone());
        supDTO.setSupplierId(source.getSupplierId());
        var prodIdDTOs = source.getProducts().stream().map(prod -> prod.getProductId()).collect(Collectors.toList());
        supDTO.setProducts(prodIdDTOs);
        return supDTO;
    }
}
