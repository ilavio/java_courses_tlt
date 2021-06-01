package com.potemkin.i;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс CrudHandler обработчик сущностей Customer и Order
 * 
 */
@Profile("!local")
@Slf4j
@Scope("singleton")
@Component("crudhandler")
public class CrudHandler {
    private EntityManagerFactory managerFactory;

    @Autowired
    public CrudHandler(@Qualifier("entityManagerFactory") EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    public CrudHandler() {
    }

    /**
     * Метод добавления сущности Customer
     * 
     * @param customer
     */
    public void addEntity(Customer customer) {
        var entityManager = managerFactory.createEntityManager();
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
     * 
     * @param order
     * @param customerId
     */
    public void addEntity(Order order, int customerId) {
        var entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer cust = entityManager.find(Customer.class, customerId);
            order.setCustomer(cust);
            entityManager.persist(order);
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
        var entityManager = managerFactory.createEntityManager();
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
     * Метод изъятия из баззы данных объектов типа Order по customerId
     * 
     * @param customerId - тип int
     * @return - объектов List<Order>
     */
    public List<Order> getOrders(int customerId) {
        var entityManager = managerFactory.createEntityManager();
        String query = "SELECT order FROM Order order WHERE order.customer.customerId = ?1";
        TypedQuery<Order> custQuery = entityManager.createQuery(query, Order.class);
        List<Order> orders = null;
        try {
            orders = new ArrayList<>(custQuery.setParameter(1, customerId).getResultList());
            log.trace("Заказы: {}", orders);
        } catch (Exception e) {
            log.error("Ошибка CrudHandler getCustomer() {}", e);
        } finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Метод изъятия из баззы данных объектa типа Order по Id
     * 
     * @param orderId - тип int
     * @return - объект Order
     */
    public Order getOredr(int orderId) {
        var entityManager = managerFactory.createEntityManager();
        String query = "SELECT order FROM Order order WHERE order.orderId = ?1";
        TypedQuery<Order> custQuery = entityManager.createQuery(query, Order.class);
        Order order = null;
        try {
            order = custQuery.setParameter(1, orderId).getSingleResult();
            log.trace("Покупатель: {}", order);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getOrder() {}", e);
        } finally {
            entityManager.close();
        }
        return order;
    }

    /**
     * Метод изъятия из баззы данных всех объектов типа Customer
     * 
     * @return - список Customer
     */
    public List<Customer> getCustomerAll() {
        var entityManager = managerFactory.createEntityManager();
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
     * Метод разбора JSONObject для создания Order
     * 
     * @param json
     * @return Order
     */
    public Order parseForOrder(JSONObject json) {
        Order order = new Order();
        if (json != null) {
            order.setOrderNumber(json.getString("orderNumber"));
            order.setTotalAmount(json.getDouble("totalAmount"));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(json.getString("orderDate"));
            } catch (JSONException | ParseException e) {
                log.error("Ошибка в CrudHandler parseForOrder() {}", e);
            }
            order.setOrderDate(date);
        }
        return order;
    }

    /**
     * Метод замена значений в Customer
     * 
     * @param json
     * @param customerId
     * @return Customer
     */
    public Customer changeEntity(JSONObject json, int customerId) {
        var entityManager = managerFactory.createEntityManager();
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
     * Метод замены значений в Order
     * 
     * @param json
     * @param customerId
     * @return Customer
     */
    public Order changeOrder(JSONObject json, int orderId) {
        var entityManager = managerFactory.createEntityManager();
        Order entity = entityManager.find(Order.class, orderId);
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity.setOrderNumber(json.getString("orderNumber"));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(json.getString("orderDate"));
            } catch (JSONException | ParseException e) {
                log.error("Ошибка в CrudHandler changeOrder() {}", e);
            }
            entity.setOrderDate(date);
            entity.setTotalAmount(json.getDouble("totalAmount"));
            log.trace("Заказ: {}", entity);
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка CrudHandler changeOrder() {}", e);
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
        var entityManager = managerFactory.createEntityManager();
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

    /**
     * Удаление Order из базы по id
     * 
     * @param orderId
     */
    public void deleteOrder(int orderId) {
        var entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Order entity = entityManager.find(Order.class, orderId);
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
