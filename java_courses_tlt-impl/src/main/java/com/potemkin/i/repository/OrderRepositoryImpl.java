package com.potemkin.i.repository;

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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.interf.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс OrderRepository обработчик сущности Order
 * 
 * @author Илья Пот
 *
 */
@Profile("!local")
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final EntityManagerFactory managerFactory;

    /**
     * Метод добавления Order
     * 
     * @param order
     * @param customerId
     */
    public Order addOrder(Order order, int customerId) {
        var entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer cust = entityManager.find(Customer.class, customerId);
            order.setCustomer(cust);
            entityManager.persist(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addCustomer(): {}", e);
        } finally {
            entityManager.close();
        }
        return order;
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
     * Удаление Order из базы по id
     * 
     * @param orderId
     */
    public boolean deleteOrder(int orderId) {
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
            return true;
        } catch (Exception e) {
            log.error("Ошибка CrudHandler deleteCust() {}", e);
        } finally {
            entityManager.close();
        }
        return false;
    }
}
