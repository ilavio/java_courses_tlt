package com.potemkin.i.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс SupplierService обслуживания сущностей Supplier
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    /**
     * Метод получения сущности Supplier
     * 
     * @param id
     * @return Supplier
     */
    public Supplier getSupplier(Integer id) {
        var sup = supplierRepository.findById(id).get();
        return sup;
    }

    /**
     * Метод получения списка Supplier
     * 
     * @return List<Supplier>
     */
    public List<Supplier> getSuppliers() {
        List<Supplier> sups = supplierRepository.findAll();
        log.info("getSuppliers() {}", sups);
        return sups;
    }

    /**
     * Метод добавления в базу данных Supplier
     * 
     * @param json
     * @return JSONObject
     */
    public Supplier addSupplier(Supplier sup) {
        supplierRepository.saveAndFlush(sup);
        log.info("SupplierService addSupplier() {}", sup);
        return sup;
    }

    /**
     * Метод изменения сущности Supplier
     * 
     * @param json
     * @param supplierId
     * @return JSONObject
     */
    public Supplier changeEntity(Supplier sup, int supplierId) {
        var entity = supplierRepository.findById(supplierId).get();
        entity.setCompanyName(sup.getCompanyName());
        entity.setPhone(sup.getPhone());
        supplierRepository.saveAndFlush(entity);
        log.info("SupplierService changeEntity {}", entity);
        return entity;
    }

    /**
     * Метод удаления сущности Supplier
     * 
     * @param supplierId
     * @return JSONObject
     */
    public boolean deleteById(int supplierId) {
        supplierRepository.deleteById(supplierId);
        var ex = supplierRepository.existsById(supplierId);
        if(ex == false) {
            ex = true;
            return ex;
        }
        return false;
    }
}
