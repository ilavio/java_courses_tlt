package com.potemkin.i;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс обработчик сущностей Supplier и Product
 * 
 * @author Илья Пот
 *
 */
@Profile("!local")
@Slf4j
@Component("crudHandlerSupAndProduct")
@Scope("singleton")
public class CrudHandlerSupAndProduct {
    private EntityManagerFactory managerFactory;
    
    @Autowired
    public CrudHandlerSupAndProduct(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    /**
     * Метод добавления сущности типа Supplier
     * 
     * @param supplier
     */
    public void addEntity(Supplier supplier) {
        var entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(supplier);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudHandlerSupAndProduct addEntity(): {}", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Метод добавления Product в базу данных
     * 
     * @param order
     * @param customerId
     */
    public void addEntity(Product prod, int supplierId) {
        var entityManager = managerFactory.createEntityManager();
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

    /**
     * Метод получения Supplier по id
     * 
     * @param supplierId - int
     * @return Supplier
     */
    public Supplier getSupplier(int supplierId) {
        var entityManager = managerFactory.createEntityManager();
        String query = "SELECT sup FROM Supplier sup WHERE sup.supplierId = ?1";
        TypedQuery<Supplier> custQuery = entityManager.createQuery(query, Supplier.class);
        Supplier sup = null;
        try {
            sup = custQuery.setParameter(1, supplierId).getSingleResult();
            log.trace("Поставщик: {}", sup);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct getSupplier() {}", e);
        } finally {
            entityManager.close();
        }
        return sup;
    }

    /**
     * Метод получения Product из базы данных
     * 
     * @param productId
     * @return Product
     */
    public Product getProduct(int productId) {
        var entityManager = managerFactory.createEntityManager();
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
        var entityManager = managerFactory.createEntityManager();
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
     * Метод получения всего списка Supplier з баззы данных
     * 
     * @return List<Supplier>
     */
    public List<Supplier> getSupplierAll() {
        var entityManager = managerFactory.createEntityManager();
        String query = "SELECT sup FROM Supplier sup";
        TypedQuery<Supplier> custQuery = entityManager.createQuery(query, Supplier.class);
        List<Supplier> sups = null;
        try {
            sups = custQuery.getResultList();
            log.trace("Поставщик: {}", sups);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct getSupplierAll() {}", e);
        } finally {
            entityManager.close();
        }
        return sups;
    }

    /**
     * Метод изменения Supplier по id
     * 
     * @param json
     * @param supplierId
     * @return Supplier
     */
    public Supplier changeEntity(JSONObject json, int supplierId) {
        var entityManager = managerFactory.createEntityManager();
        Supplier entity = entityManager.find(Supplier.class, supplierId);
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity.setCompanyName(json.getString("companyName"));
            entity.setPhone(json.getString("phone"));
            log.trace("Поставщик: {}", entity);
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct changeEntity() {}", e);
        } finally {
            entityManager.close();
        }
        return entity;
    }
    
    /**
     * Метод изменения Product  базе данных
     * 
     * @param json
     * @param productId
     * @return Product
     */
    public Product changeProduct(JSONObject json, int productId) {
        var entityManager = managerFactory.createEntityManager();
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
     * Метод удаления Supplier по id
     * 
     * @param supplierId
     */
    public void deleteSupplier(int supplierId) {
        var entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Supplier entity = entityManager.find(Supplier.class, supplierId);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
            log.trace("Поставщик: {}", entity);
            entityManager.persist(entity);
        } catch (Exception e) {
            log.error("Ошибка CrudHandlerSupAndProduct deleteSupplier() {}", e);
        } finally {
            entityManager.close();
        }
    }
    
    /**
     * Метод удаления Product по id
     * 
     * @param orderId
     */
    public void deleteProduct(int productId) {
        var entityManager = managerFactory.createEntityManager();
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
