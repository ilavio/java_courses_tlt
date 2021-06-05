package com.potemkin.i.repository.interf;

import org.json.JSONObject;

import com.potemkin.i.domain.entity.Supplier;

/**
 * Интерфейс SupplierRepository
 * 
 * @author Илья Пот
 *
 */
public interface SupplierRepository {

    public Supplier addSupplier(Supplier supplier);
    
    public Supplier getSupplier(int supplierId);
    
    public boolean deleteSupplier(int supplierId);
    
    public Supplier parseForSupplier(JSONObject json);
}
