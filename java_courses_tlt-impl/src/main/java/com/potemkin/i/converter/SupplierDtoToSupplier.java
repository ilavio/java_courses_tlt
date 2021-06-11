package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;

public class SupplierDtoToSupplier implements Converter<SupplierDTO, Supplier> {

    @Override
    public Supplier convert(SupplierDTO source) {
        var sup = new Supplier();
        sup.setCompanyName(source.getCompanyName());
        sup.setPhone(source.getPhone());
        sup.setSupplierId(source.getSupplierId());
        return null;
    }
}
