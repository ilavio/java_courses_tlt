package com.potemkin.i;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестирование CrudMyFirst класс
 * 
 * @author Илья Пот
 *
 */
public class CrudMyFirstTest {
    List<Product> products = new ArrayList<Product>();
    List<Order> orders = new ArrayList<Order>();
    CrudMyFirst crud;
    Date date = new Date();

    @BeforeEach
    public void addList() {
        var prod = new Product();
        var sup = new Supplier();
        sup.setCompanyName("ABIBAS");
        sup.setPhone("232323");
        sup.setSupplierId(1);
        prod.setProductId(1);
        prod.setProductName("sneakers");
        prod.setUnitPrice(12.02);
        prod.setDiscontinued(false);
        prod.setSupplier(sup);
        this.products.add(prod);
        var cust = new Customer();
        var order = new Order();
        cust.setCustomerId(1);
        cust.setCustomerName("MMM");
        cust.setPhone("121212");
        order.setCustomer(cust);
        order.setOrderId(1);
        order.setOrderDate(date);
        order.setOrderNumber("111");
        order.setTotalAmount(12.02);
        orders.add(order);
        crud = Mockito.mock(CrudMyFirst.class);
    }

    @Test
    public void addCustomerTest() {
        crud.addCustomer("MMM", "121212");
        when(crud.getCustomer("MMM")).thenReturn(orders.get(0).getCustomer());
        var cust = crud.getCustomer("MMM");
        assertEquals(cust, orders.get(0).getCustomer());
    }

    @Test
    public void addSupplierTest() {
        crud.addSupplier("ABIBAS", "232323");
        when(crud.getSupplier("ABIBAS")).thenReturn(products.get(0).getSupplier());
        var sup = crud.getSupplier("ABIBAS");
        assertEquals(sup, products.get(0).getSupplier());
    }

    @Test
    public void addProductTest() {
        crud.addProduct("ABIBAS", "sneakers", 12.02, false);
        when(crud.getProduct("sneakers")).thenReturn(products.get(0));
        var prod = crud.getProduct("sneakers");
        assertEquals(prod, products.get(0));
    }

    @Test
    public void addOrderTest() {
        crud.addOrder("MMM", "111", date, 12.02);
        when(crud.getOrder("111")).thenReturn(orders.get(0));
        var order = crud.getOrder("111");
        assertEquals(order, orders.get(0));
    }
}
