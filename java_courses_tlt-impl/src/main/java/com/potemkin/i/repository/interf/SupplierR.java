package com.potemkin.i.repository.interf;

import org.json.JSONObject;

import com.potemkin.i.domain.entity.Supplier;

public interface SupplierR {

    public void addSupplier(Supplier supplier);
    
    public Supplier getSupplier(int supplierId);
    
    public void deleteSupplier(int supplierId);
    
    public Supplier parseForSupplier(JSONObject json);
}
