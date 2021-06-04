package com.potemkin.i.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.interf.ProductR;

import lombok.extern.slf4j.Slf4j;

@Profile("!local")
@Slf4j
@Component()
public class ProductRepository implements ProductR {
    
private final EntityManagerFactory MANAGER_FACTORY;
    
    @Autowired
    public ProductRepository(EntityManagerFactory managerFactory) {
        this.MANAGER_FACTORY = managerFactory;
    }

    /**
     * Метод добавления Product в базу данных
     * 
     * @param order
     * @param customerId
     */
    public void addProduct(Product prod, int supplierId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Supplier sup = entityManager.find(Supplier.class, supplierId);
            prod.setSupplier(sup);
            entityManager.persist(prod);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addCustomer(): {}", e);
        } finally {
            entityManager.close();
        }
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
        }
        return prod;
    }
    
    /**
     * Метод получения Product из базы данных
     * 
     * @param productId
     * @return Product
     */
    public Product getProduct(int productId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "SELECT prod FROM Product prod WHERE prod.productId = ?1";
        TypedQuery<Product> custQuery = entityManager.createQuery(query, Product.class);
        Product prod = null;
        try {
            prod = custQuery.setParameter(1, productId).getSingleResult();
            log.trace("Покупатель: {}", prod);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct getProduct() {}", e);
        } finally {
            entityManager.close();
        }
        return prod;
    }
    
    /**
     * Метод выборки всего списка Product
     * 
     * @return List<Product>
     */
    public List<Product> getProductAll() {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "SELECT prod FROM Product prod";
        TypedQuery<Product> custQuery = entityManager.createQuery(query, Product.class);
        List<Product> prods = null;
        try {
            prods = new ArrayList<>(custQuery.getResultList());
            log.trace("Товары: {}", prods);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct getProductAll() {}", e);
        } finally {
            entityManager.close();
        }
        return prods;
    }
    
    /**
     * Метод изменения Product  базе данных
     * 
     * @param json
     * @param productId
     * @return Product
     */
    public Product changeProduct(JSONObject json, int productId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        Product entity = entityManager.find(Product.class, productId);
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity.setDiscontinued(json.getBoolean("isDiscontinued"));
            entity.setProductName(json.getString("productName"));
            entity.setUnitPrice(json.getDouble("unitPrice"));
            log.trace("Товар: {}", entity);
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct changeProduct() {}", e);
        } finally {
            entityManager.close();
        }
        return entity;
    }
    
    /**
     * Метод удаления Product по id
     * 
     * @param orderId
     */
    public void deleteProduct(int productId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Product entity = entityManager.find(Product.class, productId);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
            log.trace("Товар: {}", entity);
            entityManager.persist(entity);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct deleteProduct() {}", e);
        } finally {
            entityManager.close();
        }
    }
}
