package com.potemkin.i.repository.interf;

import org.json.JSONObject;

import com.potemkin.i.domain.entity.Product;

/**
 * Интерфейс ProductRepository
 * 
 * @author Илья Пот
 *
 */
public interface ProductRepository {

    public void addProduct(Product prod, int supplierId);

    public Product getProduct(int productId);

    public void deleteProduct(int productId);
    
    public Product parseForProduct(JSONObject json);
}
