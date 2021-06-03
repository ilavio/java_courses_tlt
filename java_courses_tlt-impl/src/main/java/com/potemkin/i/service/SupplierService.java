package com.potemkin.i.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;

/**
 * Класс SupplierService обслуживания сущностей Supplier
 * 
 * @author Илья Пот
 *
 */
@Service
@Transactional
public class SupplierService  { //implements ServiceInt

    @Autowired
    private SupplierRepository repository;

    /**
     * Метод получения сущности Supplier из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getSupplierJson(Integer id) {
        JSONObject json = new JSONObject(repository.findById(id).get());
        return json;
    }

    /**
     * Метод получения сущности Supplier
     * 
     * @param id
     * @return Supplier
     */
    public Supplier getSupplier(Integer id) {
        var sup = repository.findById(id).get();
        return sup;
    }

    /**
     * Метод получения списка Supplier
     * 
     * @return JSONArray
     */
    public JSONArray getSuppliers() {
        var jsonArray = new JSONArray(repository.findAll());
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
        repository.saveAndFlush(sup);
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
        var entity = repository.findById(supplierId).get();
        entity.setCompanyName(json.getString("companyName"));
        entity.setPhone(json.getString("phone"));
        repository.saveAndFlush(entity);
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
        repository.deleteById(supplierId);
        var ex = repository.existsById(supplierId);
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
