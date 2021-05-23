package com.potemkin.i;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс CrudMyFirst - CRUD взаимодествия с базой данных
 * 
 * @author Илья Пот
 */
@Slf4j
public class CrudMyFirst {
    private EntityManagerFactory entityManagerFactory;
    
public CrudMyFirst(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void closed() {
        entityManagerFactory.close();
    }

    /**
     * Метод добавления Customer объектов
     * 
     * @param customerName - типа String
     * @param phone        - типа String
     */
    public void addCustomer(String customerName, String phone) {
        var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var cust = new Customer();
            cust.setCustomerName(customerName);
            cust.setPhone(phone);
            entityManager.persist(cust);
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
     * Метод изъятия из баззы данных объектов типа Customer
     * 
     * @param customerName - тип String
     * @return - объектов Customer
     */
    public Customer getCustomer(String customerName) {
        var entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT cust FROM Customer cust WHERE cust.customerName = ?1";
        TypedQuery<Customer> custQuery = entityManager.createQuery(query, Customer.class);
        Customer cust = null;
        try {
            cust = custQuery.setParameter(1, customerName).getSingleResult();
            log.trace("Покупатель: {}", cust);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getCustomer() {}", e);
        } finally {
            entityManager.close();
        }
        return cust;
    }

    /**
     * Метод добавления Order объектов
     * 
     * @param customerName - типа String
     * @param orderNumber  - типа String
     * @param orderDate    - типа Date
     * @param totalAmount  - типа double
     */
    public void addOrder(String customerName, String orderNumber, Date orderDate, double totalAmount) {
        var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Customer cust = getCustomer(customerName);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var order = new Order();
            order.setOrderNumber(orderNumber);
            order.setOrderDate(orderDate);
            order.setTotalAmount(totalAmount);
            if (cust != null) {
                order.setCustomer(cust);
            }
            entityManager.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addOrder(): {}", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Метод добавления Supplier объектов
     * 
     * @param companyName - типа String
     * @param phone       - типа String
     */
    public void addSupplier(String companyName, String phone) {
        var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var sup = new Supplier();
            sup.setCompanyName(companyName);
            sup.setPhone(phone);
            entityManager.persist(sup);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addSupplier(): {}", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Метод изъятия из баззы данных объектов типа Supplier
     * 
     * @param companyName - типа String
     * @return - объект типа Supplier
     */
    public Supplier getSupplier(String companyName) {
        var entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT sup FROM Supplier sup WHERE sup.companyName = ?1";
        TypedQuery<Supplier> supQuery = entityManager.createQuery(query, Supplier.class);
        Supplier sup = null;
        try {
            sup = supQuery.setParameter(1, companyName).getSingleResult();
            log.trace("Поставщик: {}", sup);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getSupplier() {}", e);
        } finally {
            entityManager.close();
        }
        return sup;
    }

    /**
     * Метод добавления Product объектов в базу данных
     * 
     * @param companyName    - типа String
     * @param productName    - типа String
     * @param unitPrice      - типа double
     * @param isDiscontinued - типа boolean
     */
    public void addProduct(String companyName, String productName, double unitPrice, boolean isDiscontinued) {
        var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Supplier sup = getSupplier(companyName);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            var product = new Product();
            product.setProductName(productName);
            product.setUnitPrice(unitPrice);
            product.setDiscontinued(isDiscontinued);
            if (product != null) {
                product.setSupplier(sup);
            }
            entityManager.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst addProduct(): {}", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Метод изъятия из баззы данных объектов типа Order
     * 
     * @param orderNumber - типа String
     * @return - типа Order
     */
    public Order getOrder(String orderNumber) {
        var entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT order FROM Order order WHERE order.orderNumber = ?1";
        TypedQuery<Order> oQuery = entityManager.createQuery(query, Order.class);
        Order order = null;
        try {
            order = oQuery.setParameter(1, orderNumber).getSingleResult();
            log.trace("Заказ: {}", order);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getOrder() {}", e);
        } finally {
            entityManager.close();
        }
        return order;
    }

    /**
     * Метод изъятия из баззы данных объектов типа Product
     * 
     * @param productName - типа String
     * @return - типа Product
     */
    public Product getProduct(String productName) {
        var entityManager = entityManagerFactory.createEntityManager();
        String query = "SELECT prod FROM Product prod WHERE prod.productName = ?1";
        TypedQuery<Product> oQuery = entityManager.createQuery(query, Product.class);
        Product prod = null;
        try {
            prod = oQuery.setParameter(1, productName).getSingleResult();
            log.trace("Продукт: {}", prod);
        } catch (Exception e) {
            log.error("Ошибка CrudMyFirst getProduct() {}", e);
        } finally {
            entityManager.close();
        }
        return prod;
    }

    /**
     * Метод объединения Order с Product
     * 
     * @param order   - типа Order
     * @param product - типа Product
     */
    public void mergeOrderProduct(Order order, Product product) {
        var entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Order or = null;
        Product prod = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            or = entityManager.find(Order.class, order.getOrderId());
            prod = entityManager.find(Product.class, product.getProductId());
            or.getProduct().add(prod);
            prod.getOrder().add(or);
            entityManager.persist(or);
            entityManager.refresh(prod);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка CrudMyFirst mergeOrderProduct(): {}", e);
        } finally {
            entityManager.close();
        }
    }
}
