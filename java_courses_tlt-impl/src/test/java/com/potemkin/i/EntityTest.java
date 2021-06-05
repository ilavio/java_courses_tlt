package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.config.EntityManagerConfigLocal;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.ProductRepositoryImpl;
import com.potemkin.i.repository.SupplierRepositoryImpl;
import com.potemkin.i.repository.hard.CustomerRepositoryHard;
import com.potemkin.i.repository.hard.OrderRepositoryHard;
import com.potemkin.i.repository.hard.ProductRepositoryHard;
import com.potemkin.i.repository.hard.SupplierRepositoryHard;

public class EntityTest {
    private EntityManagerConfigLocal local = new EntityManagerConfigLocal();
    private CustomerRepositoryHard custHard = new CustomerRepositoryHard();
    private OrderRepositoryHard ordHard = new OrderRepositoryHard();
    private SupplierRepositoryHard supHard = new SupplierRepositoryHard();
    private ProductRepositoryHard prodHard = new ProductRepositoryHard();
    private Customer cust;
    private Product prod;
    private Order ord;
    private Supplier sup;

    @BeforeEach
    public void entityCreate() {
        ordHard.addOrder(new Order(), 0);
        custHard.addCustomer(new Customer());
        supHard.addSupplier(new Supplier());
        prodHard.addProduct(new Product(), 0);
        cust = custHard.getCustomer(0);
        prod = prodHard.getProduct(0);
        ord = ordHard.getOredr(0);
        sup = supHard.getSupplier(0);
    }

    @Test
    public void customerTestToString() {
        var custTest = custHard.getCustomer(0);
        String str = cust.toString();

        assertNotNull(str);
        assertEquals(cust.hashCode(), custTest.hashCode());
    }

    @Test
    public void productTestToString() {
        var prodTest = prodHard.getProduct(0);
        String str = prod.toString();

        assertNotNull(str);
        assertEquals(prod.hashCode(), prodTest.hashCode());
        assertTrue(prod.equals(prodTest));
    }

    @Test
    public void supplierTestToString() {
        var supTest = supHard.getSupplier(0);
        String str = sup.toString();

        assertNotNull(str);
        assertEquals(sup.hashCode(), supTest.hashCode());
    }
    
    @Test
    public void orderTestToString() {
        var ordTest = ordHard.getOredr(0);
        String str = ord.toString();

        assertNotNull(str);
        assertEquals(ord.hashCode(), ordTest.hashCode());
    }
}
