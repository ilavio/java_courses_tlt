package com.potemkin.i.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;

/**
 * Интерфейс взаимодествия с базой данных
 * 
 * @author Илья Пот
 *
 */
@Component
public interface ServiceSupplier {

    /**
     * Метод получения Supplier
     * 
     * @param id
     * @return Supplier
     */
    public Supplier getSupplier(Integer id);

    /**
     * Метод добавления сущности в базу данных
     * 
     * @param json
     * @return JSONObject
     */
    public Supplier addSupplier(Supplier sup);

    /**
     * Метод изменения сущности в базе данных
     * 
     * @param json
     * @param supplierId
     * @return JSONObject
     */
    public Supplier changeEntity(Supplier sup, int supplierId);

    /**
     * Метод удаления сущности по id
     * 
     * @param supplierId
     * @return JSONObject
     */
    public boolean deleteById(int supplierId);

    /**
     * Метод получения списка Supplier
     * 
     * @return
     */
    public List<Supplier> getSuppliers();
}
