package com.potemkin.i.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;

/**
 * Конвертер Supplier в SupplierDTO и обратно
 * 
 * @author Илья Пот
 * 
 */
@Component
public class SupplierConverter {

    /**
     * Метод преоброзования Supplier в SupplierDTO
     * 
     * @param sup
     * @return
     */
    public SupplierDTO supplierToDTO(Supplier sup) {
        var supDTO = new SupplierDTO();
        supDTO.setCompanyName(sup.getCompanyName());
        supDTO.setPhone(sup.getPhone());
        supDTO.setSupplierId(sup.getSupplierId());
        return supDTO;
    }

    /**
     * Метод преоброзования SupplierDTO в Supplier
     * 
     * @param dto
     * @return
     */
    public Supplier dtoToSupplier(SupplierDTO dto) {
        var sup = new Supplier();
        sup.setCompanyName(dto.getCompanyName());
        sup.setPhone(dto.getPhone());
        sup.setSupplierId(dto.getSupplierId());
        return sup;
    }

    /**
     * Метод преоброзования листа List<SupplierDTO> в List<Supplier>
     * 
     * @param dtos
     * @return List<Supplier>
     */
    public List<Supplier> dtoTOSupplier(List<SupplierDTO> dtos) {
        var sups = dtos.stream().map(dto -> dtoToSupplier(dto)).collect(Collectors.toList());
        return sups;
    }

    /**
     * Метод преоброзования листа Supplier в SupplierDTO
     * 
     * @param sups
     * @return
     */
    public List<SupplierDTO> supplierToDTO(List<Supplier> sups) {
        var dtos = sups.stream().map(sup -> supplierToDTO(sup)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * Метод разбора JSONObject для создания Supplier
     * 
     * @param json
     * @return Supplier
     */
    public Supplier parseForSupplier(JSONObject json) {
        Supplier sup = new Supplier();
        if (json != null) {
            sup.setCompanyName(json.getString("companyName"));
            sup.setPhone(json.getString("phone"));
        }
        return sup;
    }
}
