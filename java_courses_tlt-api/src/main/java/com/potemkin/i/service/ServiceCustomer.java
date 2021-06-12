package com.potemkin.i.service;

import org.json.JSONObject;

/**
 * Интерфейс взаимодествия с базой данных
 * 
 * @author Илья Пот
 *
 */
public interface ServiceCustomer {

    /**
     * Метод получения сущности из базы данных
     * 
     * @param id
     * @return Customer
     */
    public JSONObject getCustomerJson(Integer id);

    /**
     * Метод изменения сущности в базе данных
     * 
     * @param json
     * @param customerId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int customerId);

    /**
     * Метод удаления из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject deleteById(int id);

    /**
     * Метод добавления сущности Customer
     * 
     * @param json
     * @return
     */
    public JSONObject addCustomer(JSONObject json);
}
