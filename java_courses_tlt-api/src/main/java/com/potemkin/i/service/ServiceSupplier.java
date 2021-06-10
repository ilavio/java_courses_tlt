package com.potemkin.i.service;

import org.json.JSONObject;

/**
 * Интерфейс взаимодествия с базой данных
 * 
 * @author Илья Пот
 *
 */
public interface ServiceSupplier {

    /**
     * Метод получения сущности из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getSupplierJson(Integer id);

    /**
     * Метод добавления сущности в базу данных
     * 
     * @param json
     * @return JSONObject
     */
    public JSONObject addSupplier(JSONObject json);

    /**
     * Метод изменения сущности в базе данных
     * 
     * @param json
     * @param supplierId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int supplierId);

    /**
     * Метод удаления сущности по id
     * 
     * @param supplierId
     * @return JSONObject
     */
    public JSONObject deleteById(int supplierId);
}
