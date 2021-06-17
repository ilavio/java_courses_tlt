package com.potemkin.i.resource.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;
import com.potemkin.i.resource.SupplierResource;
import com.potemkin.i.service.impl.SupplierServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс контроллер SupplierResourceImpl
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SupplierResourceImpl implements SupplierResource {

    private final SupplierServiceImpl supplierServiceImpl;
    private final ConversionService conversionService;

    public SupplierDTO addSupplier(@RequestBody SupplierDTO dto) {
        var sup = conversionService.convert(dto, Supplier.class);
        log.info("SupplierResourceImpl addSupplier() {}", sup);
        var supResp = supplierServiceImpl.addSupplier(sup);
        var supDTO = conversionService.convert(supResp, SupplierDTO.class);
        log.info("SupplierResourceImpl addSupplier() {}", supDTO);
        return supDTO;
    }

    public SupplierDTO getSupplier(@PathVariable("id") int id) {
        var sup = supplierServiceImpl.getSupplier(id);
        var supDTO = conversionService.convert(sup, SupplierDTO.class);
        log.info("SupplierResourceImpl getSupplier() {}", supDTO);
        return supDTO;
    }

    public List<SupplierDTO> getSuppliers() {
        var sups = supplierServiceImpl.getSuppliers();
        var dtos = sups.stream().map(sup -> conversionService.convert(sup, SupplierDTO.class))
                .collect(Collectors.toList());
        log.info("SupplierResourceImpl getSuppliers() {}", dtos);
        return dtos;
    }

    public SupplierDTO changeSupplier(@RequestBody SupplierDTO dto, @RequestParam(name = "id") int id) {
        var sup = conversionService.convert(dto, Supplier.class);
        var supResp = supplierServiceImpl.changeEntity(sup, id);
        var supDTO = conversionService.convert(supResp, SupplierDTO.class);
        log.info("SupplierResourceImpl changeSupplier() {}", supDTO);
        return supDTO;
    }

    public String deleteById(@RequestParam(name = "id") int id) {
        var ex = supplierServiceImpl.deleteById(id);
        String str = "{" + "\"Delete Supplier\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
