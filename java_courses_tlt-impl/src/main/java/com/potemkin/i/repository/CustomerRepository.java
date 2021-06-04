package com.potemkin.i.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.interf.CustomerR;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс CustomerRepository обработчик сущности Customer
 * 
 * @author Илья Пот
 *
 */
@Profile("!local")
@Slf4j
@Component()
public class CustomerRepository implements CustomerR {
    private final EntityManagerFactory MANAGER_FACTORY;
    
    @Autowired
    public CustomerRepository(@Qualifier("entityManagerFactory") EntityManagerFactory managerFactory) {
        this.MANAGER_FACTORY = managerFactory;
    }
    
    /**
     * Метод добавления сущности Customer
     * 
     * @param customer
     */
    public void addCustomer(Customer customer) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(customer);
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
     * Метод изъятия из баззы данных объектa типа Customer по Id
     * 
     * @param customerName - тип String
     * @return - объектов Customer
     */
    public Customer getCustomer(int customerId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "SELECT cust FROM Customer cust WHERE cust.customerId = ?1";
        TypedQuery<Customer> custQuery = entityManager.createQuery(query, Customer.class);
        Customer cust = null;
        try {
            cust = custQuery.setParameter(1, customerId).getSingleResult();
            log.trace("Покупатель: {}", cust);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getCustomer() {}", e);
        } finally {
            entityManager.close();
        }
        return cust;
    }
    
    /**
     * Метод изъятия из баззы данных всех объектов типа Customer
     * 
     * @return - список Customer
     */
    public List<Customer> getCustomerAll() {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        String query = "SELECT cust FROM Customer cust";
        TypedQuery<Customer> custQuery = entityManager.createQuery(query, Customer.class);
        List<Customer> custs = null;
        try {
            custs = custQuery.getResultList();
            log.trace("Покупатель: {}", custs);
        } catch (Exception e) {
            log.error("Ошибка CrudHandler getCustomerAll() {}", e);
        } finally {
            entityManager.close();
        }
        return custs;
    }
    
    /**
     * Метод разбора JSONObject для создания Customer
     * 
     * @param json
     * @return
     */
    public Customer parseForCustomer(JSONObject json) {
        Customer cust = new Customer();
        if (json != null) {
            cust.setCustomerName(json.getString("customerName"));
            cust.setPhone(json.getString("phone"));
        }
        return cust;
    }
    
    /**
     * Метод замена значений в Customer
     * 
     * @param json
     * @param customerId
     * @return Customer
     */
    public Customer changeEntity(JSONObject json, int customerId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        Customer entity = entityManager.find(Customer.class, customerId);
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity.setCustomerName(json.getString("customerName"));
            entity.setPhone(json.getString("phone"));
            log.trace("Покупатель: {}", entity);
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка CrudHandler changeEntity() {}", e);
        } finally {
            entityManager.close();
        }
        return entity;
    }
    
    /**
     * Удаление Customer из базы по id
     * 
     * @param customerId
     */
    public void deleteCust(int customerId) {
        var entityManager = MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Customer entity = entityManager.find(Customer.class, customerId);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
            log.trace("Покупатель: {}", entity);
            entityManager.persist(entity);
        } catch (Exception e) {
            log.error("Ошибка CrudHandler deleteCust() {}", e);
        } finally {
            entityManager.close();
        }
    }
}
