package com.potemkin.i;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.SupplierDtoToSupplier;
import com.potemkin.i.converter.SupplierToSupplierDTO;
import com.potemkin.i.dto.SupplierDTO;

public class SupplierDtoToSupplierTest {

    private SupplierDtoToSupplier supplierDtoToSupplier;
    private SupplierToSupplierDTO supplierToSupplierDTO;
    
    @BeforeEach
    public void masking() {
        supplierDtoToSupplier = new SupplierDtoToSupplier();
        supplierToSupplierDTO = new SupplierToSupplierDTO();
    }
    
    @Test
    public void test() {
        var dto = new SupplierDTO();
        dto.setCompanyName("Y");
        dto.setPhone("8");
        dto.setSupplierId(1);
        var sup = supplierDtoToSupplier.convert(dto);
        var dtoTest = supplierToSupplierDTO.convert(sup);
        assertEquals(sup.getSupplierId(), dtoTest.getSupplierId());
    }
}
