package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;

/**
 * Класс SupplierDtoToSupplier преоброзования SupplierDTO в Supplier
 * 
 * @author Илья Пот
 *
 */
@Component
public class SupplierDtoToSupplier implements Converter<SupplierDTO, Supplier> {

    /**
     * Метод преоброзования SupplierDTO в Supplier
     * 
     * @param SupplierDTO
     * @return Supplier
     */
    @Override
    public Supplier convert(SupplierDTO source) {
        var sup = new Supplier();
        sup.setCompanyName(source.getCompanyName());
        sup.setPhone(source.getPhone());
        return sup;
    }
}
