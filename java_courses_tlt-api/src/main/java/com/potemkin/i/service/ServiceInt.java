package com.potemkin.i.service;

import org.json.JSONObject;

public interface ServiceInt {
    
    public JSONObject changeEntity(JSONObject json, int customerId);
    public JSONObject deleteById(int id);
}
