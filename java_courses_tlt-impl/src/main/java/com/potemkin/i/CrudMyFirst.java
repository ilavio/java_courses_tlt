package com.potemkin.i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import com.potemkin.i.domain.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrudMyFirst {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-First");
    
    public void closed() {
        entityManagerFactory.close();
    }
    
    public void addCustomer(String customerName, String phone) {
        var entity = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try{
           transaction = entity.getTransaction();
           transaction.begin();
           var cust = new Customer();
           cust.setCustomerName(customerName);
           cust.setPhone(phone);
           entity.persist(cust);
           transaction.commit();
        }catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addCustomer(): {}", e);
        }finally {
            entity.close();
        }
    }
    
    public void getCustomer(int customerId) {
        var entity = entityManagerFactory.createEntityManager();
        String query = "SELECT cust FROM Customer cust WHERE cust.customerId = ?1";
        TypedQuery<Customer> custQuery = entity.createQuery(query, Customer.class);
        Customer cust = null;
        try{
            cust = custQuery.setParameter(1, customerId).getSingleResult();
            log.trace("Покупатель: {}", cust);
        }catch(Exception e) {
            log.error("Ошибка CrudMyFirst getCustomer() {}", e);
        }finally {
            entity.close();
        }
    }
    
    public void addCustomer(String customerName) {
        
    }
}
