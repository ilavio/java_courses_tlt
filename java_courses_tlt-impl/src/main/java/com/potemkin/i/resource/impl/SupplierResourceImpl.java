package com.potemkin.i.resource.impl;

import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;
import com.potemkin.i.service.impl.CustomerServiceImpl;
import com.potemkin.i.service.impl.SupplierServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SupplierResourceImpl {
    
    private final SupplierServiceImpl supplierServiceImpl;
    private final ConversionService conversionService;

    public SupplierDTO addSupplier(SupplierDTO dto) {
        var sup = conversionService.convert(dto, Supplier.class);
        var supResp = supplierServiceImpl.addSupplier(sup);
        var supDTO = conversionService.convert(supResp, SupplierDTO.class);
        return supDTO;
    }
}
