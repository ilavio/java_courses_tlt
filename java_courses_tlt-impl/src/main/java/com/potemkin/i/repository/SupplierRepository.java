package com.potemkin.i.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.interf.SupplierR;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс обработчик сущности Supplier
 * 
 * @author Илья Пот
 *
 */
@Profile("!local")
@Slf4j
@Component()
public class SupplierRepository implements SupplierR {
    
private final EntityManagerFactory MANAGER_FACTORY;
    
    @Autowired
    public SupplierRepository(EntityManagerFactory managerFactory) {
        this.MANAGER_FACTORY = managerFactory;
    }

    /**
     * Метод добавления сущности типа Supplier
     * 
     * @param supplier
     */
    public void addSupplier(Supplier supplier) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
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
        var entityManager = MANAGER_FACTORY.createEntityManager();
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
     * Метод получения всего списка Supplier з баззы данных
     * 
     * @return List<Supplier>
     */
    public List<Supplier> getSupplierAll() {
        var entityManager = MANAGER_FACTORY.createEntityManager();
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
        var entityManager = MANAGER_FACTORY.createEntityManager();
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
     * Метод удаления Supplier по id
     * 
     * @param supplierId
     */
    public void deleteSupplier(int supplierId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
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
}
