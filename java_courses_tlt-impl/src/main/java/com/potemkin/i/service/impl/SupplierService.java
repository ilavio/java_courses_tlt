package com.potemkin.i.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;
import com.potemkin.i.service.ServiceSupplier;

import lombok.RequiredArgsConstructor;

/**
 * Класс SupplierService обслуживания сущностей Supplier
 * 
 * @author Илья Пот
 *
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService implements ServiceSupplier  {

    private final SupplierRepository supplierRepository;

    /**
     * Метод получения сущности Supplier из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getSupplierJson(Integer id) {
        JSONObject json = new JSONObject(supplierRepository.findById(id).get());
        return json;
    }

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
     * @return JSONArray
     */
    public JSONArray getSuppliers() {
        var jsonArray = new JSONArray(supplierRepository.findAll());
        return jsonArray;
    }

    /**
     * Метод добавления в базу данных Supplier
     * 
     * @param json
     * @return JSONObject
     */
    public JSONObject addSupplier(JSONObject json) {
        var sup = parseForSupplier(json);
        supplierRepository.saveAndFlush(sup);
        return json;
    }

    /**
     * Метод изменения сущности Supplier
     * 
     * @param json
     * @param supplierId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int supplierId) {
        var entity = supplierRepository.findById(supplierId).get();
        entity.setCompanyName(json.getString("companyName"));
        entity.setPhone(json.getString("phone"));
        supplierRepository.saveAndFlush(entity);
        json.append("id", supplierId);
        return json;
    }

    /**
     * Метод удаления сущности Supplier
     * 
     * @param supplierId
     * @return JSONObject
     */
    public JSONObject deleteById(int supplierId) {
        supplierRepository.deleteById(supplierId);
        var ex = supplierRepository.existsById(supplierId);
        String str = "{" + "\"Found Supplier\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json;
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
