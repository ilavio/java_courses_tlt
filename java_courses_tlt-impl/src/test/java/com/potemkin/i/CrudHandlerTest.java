package com.potemkin.i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.config.EntityManagerConfigLocal;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.CustomerRepository;
import com.potemkin.i.repository.OrderRepository;
import com.potemkin.i.repository.hard.CustRepHard;
import com.potemkin.i.repository.hard.OrdRepHard;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrudHandlerTest {
    private EntityManagerFactory entityManagerFactoryMock;
    private EntityManager entityManagerMock;
    private EntityTransaction entityTransactionMock;
    //private EntityManagerConfigLocal springConfigLocal = new EntityManagerConfigLocal();
    private CustRepHard custHard = new CustRepHard();
    private OrdRepHard ordHard = new OrdRepHard();
    private CustomerRepository repoCust;
    private OrderRepository repoOrd;
    
    @BeforeEach
    public void maskingObjects() {
        entityManagerFactoryMock = mock(EntityManagerFactory.class);
        entityManagerMock = mock(EntityManager.class);
        entityTransactionMock = mock(EntityTransaction.class);

        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
        repoCust = new CustomerRepository(entityManagerFactoryMock);
        repoOrd = new OrderRepository(entityManagerFactoryMock);
        ordHard.addOrder(new Order(), 0);
        var cust = new Customer();
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        custHard.addCustomer(cust);
    }

    @Test
    public void addTest() {
        var cus = custHard.getCustomer(0);
        var ord = ordHard.getOredr(0);
        repoCust.addCustomer(custHard.getCustomer(0));
        repoOrd.addOrder(ordHard.getOredr(0), 1);

        verify(entityManagerMock).persist(eq(cus));
    }

    @Test
    public void getCustomerTest() {
        TypedQuery<Customer> queryMock = (TypedQuery<Customer>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT cust FROM Customer cust WHERE cust.customerId = ?1", Customer.class))
                .thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        var cust = custHard.getCustomer(0);
        when(queryMock.getSingleResult()).thenReturn(custHard.getCustomer(0));

        var custTest = repoCust.getCustomer(1);

        assertEquals(cust, custTest);
    }

    @Test
    public void getOrdersTest() {
        TypedQuery<Order> queryMock = (TypedQuery<Order>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT order FROM Order order WHERE order.customer.customerId = ?1",
                Order.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        List<Order> ords = new ArrayList<Order>();
        when(queryMock.getResultList()).thenReturn(ords);

        var ordsTest = repoOrd.getOrders(1);

        assertEquals(ordsTest, ords);
    }

    @Test
    public void getOredrTest() {
        TypedQuery<Order> queryMock = (TypedQuery<Order>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT order FROM Order order WHERE order.orderId = ?1", Order.class))
                .thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        var ord = ordHard.getOredr(0);
        when(queryMock.getSingleResult()).thenReturn(ordHard.getOredr(0));

        var ordTest = repoOrd.getOredr(1);

        assertEquals(ord, ordTest);
    }

    @Test
    public void getCustomerAllTest() {
        TypedQuery<Customer> queryMock = (TypedQuery<Customer>) mock(TypedQuery.class);
        when(entityManagerMock.createQuery("SELECT cust FROM Customer cust", Customer.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, 1)).thenReturn(queryMock);
        List<Customer> custs = new ArrayList<Customer>();
        when(queryMock.getResultList()).thenReturn(custs);

        var custsTest = repoCust.getCustomerAll();

        assertEquals(custsTest, custs);
    }

    @Test
    public void parseForCustomerTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"LUKA\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        var cust = repoCust.parseForCustomer(json);
        assertNotNull(cust);
    }

    @Test
    public void parseForOrderTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"orderNumber\" : \"113\",\r\n" + "    \"orderDate\" : \"23-05-2021\",\r\n"
                        + "    \"totalAmount\" : 12.02,\r\n" + "    \"customerId\" : 12\r\n" + "}");
        var ord = repoOrd.parseForOrder(json);
    }

    @Test
    public void changeEntityTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"LUKA\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        when(entityManagerMock.find(Customer.class, 1)).thenReturn(new Customer());

        var cust = repoCust.changeEntity(json, 1);

        assertEquals(cust.getCustomerName(), "LUKA");
        assertEquals(cust.getPhone(), "89377810580");
    }

    @Test
    public void changeOrderTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"orderNumber\" : \"113\",\r\n" + "    \"orderDate\" : \"23-05-2021\",\r\n"
                        + "    \"totalAmount\" : 12.02,\r\n" + "    \"customerId\" : 12\r\n" + "}");
        when(entityManagerMock.find(Order.class, 1)).thenReturn(new Order());

        var ord = repoOrd.changeOrder(json, 1);

        assertEquals(ord.getOrderNumber(), "113");
        assertEquals(ord.getTotalAmount(), 12.02);
    }

    @Test
    public void deleteCustTest() {
        when(entityManagerMock.find(Customer.class, 1)).thenReturn(new Customer());
        repoCust.deleteCust(1);
        verify(entityManagerMock).persist(eq(new Customer()));
    }

    @Test
    public void deleteOrderTest() {
        var date = new Date();
        var order = new Order();
        order.setOrderDate(date);
        when(entityManagerMock.find(Order.class, 1)).thenReturn(order);
        repoOrd.deleteOrder(1);
        verify(entityManagerMock).persist(eq(order));
    }
}
