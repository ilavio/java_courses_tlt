package com.potemkin.i.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.ProductRepository;

/**
 * Класс ProductService обслуживания сущностей Product
 * 
 * @author Илья Пот
 *
 */
@Service
@Transactional
public class ProductService implements ServiceInt { //

    @Autowired
    private ProductRepository repository;
    @Autowired
    private SupplierService supplierService;

    /**
     * Метод получения сущности Product из базы данных
     * 
     * @param id
     * @return JSONObject
     */
    public JSONObject getProductJson(Integer id) {
        JSONObject json = new JSONObject(repository.findById(id).get());
        return json;
    }

    /**
     * Метод получения сущности Product
     * 
     * @param id
     * @return Customer
     */
    public Product getProduct(Integer id) {
        var prod = repository.findById(id).get();
        return prod;
    }

    /**
     * Метод получения списка Product
     * 
     * @return JSONArray
     */
    public JSONArray getProducts() {
        var jsonArray = new JSONArray(repository.findAll());
        return jsonArray;
    }

    /**
     * Метод добавления в базу данных Product
     * 
     * @param json
     * @return JSONObject
     */
    public JSONObject addProduct(JSONObject json) {
        var prod = parseForProduct(json);
        repository.saveAndFlush(prod);
        return json;
    }

    /**
     * Метод изменения сущности Product
     * 
     * @param json
     * @param productId
     * @return JSONObject
     */
    public JSONObject changeEntity(JSONObject json, int productId) {
        var entity = repository.findById(productId).get();
        entity.setDiscontinued(json.getBoolean("isDiscontinued"));
        entity.setProductName(json.getString("productName"));
        entity.setUnitPrice(json.getDouble("unitPrice"));
        repository.saveAndFlush(entity);
        json.append("id", productId);
        return json;
    }

    /**
     * Метод удаления сущности Product
     * 
     * @param productId
     * @return
     */
    public JSONObject deleteById(int productId) {
        repository.deleteById(productId);
        var ex = repository.existsById(productId);
        String str = "{" + "\"Found Product\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json;
    }

    /**
     * Метод разбора JSONObject для создания Product
     * 
     * @param json
     * @return Product
     */
    public Product parseForProduct(JSONObject json) {
        var prod = new Product();
        if (json != null) {
            prod.setProductName(json.getString("productName"));
            prod.setDiscontinued(json.getBoolean("isDiscontinued"));
            prod.setUnitPrice(json.getDouble("unitPrice"));
            var sup = supplierService.getSupplier(json.getInt("supplierId"));
            prod.setSupplier(sup);
        }
        return prod;
    }
}
