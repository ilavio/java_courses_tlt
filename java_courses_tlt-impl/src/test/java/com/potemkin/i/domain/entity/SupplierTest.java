package com.potemkin.i.domain.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тестирование Supplier класс
 * 
 * @author Илья Пот
 */
public class SupplierTest {
    Supplier suppli;
    Product prod;

    @BeforeEach
    public void addCustomer() {
        prod = new Product();
        suppli = new Supplier();
        suppli.setCompanyName("ABIBAS");
        suppli.setPhone("232323");
        suppli.setSupplierId(1);
        prod.setProductId(1);
        prod.setProductName("sneakers");
        prod.setUnitPrice(12.02);
        prod.setDiscontinued(false);
        suppli.setProducts(new ArrayList<Product>());
        suppli.getProducts().add(prod);
    }

    @Test
    public void equlsTest() {
        Supplier sup = new Supplier();
        Product prod2 = new Product();
        sup.setCompanyName("ABIBAS");
        sup.setPhone("232323");
        sup.setSupplierId(1);
        prod2.setProductId(1);
        prod2.setProductName("sneakers");
        prod2.setUnitPrice(12.02);
        prod2.setDiscontinued(false);
        sup.setProducts(new ArrayList<Product>());
        sup.getProducts().add(prod2);
        assertTrue(sup.equals(suppli));
        assertTrue(suppli.equals(suppli));
        assertEquals(suppli.getSupplierId(), 1);
        assertEquals(suppli.getCompanyName(), "ABIBAS");
        assertEquals(suppli.getPhone(), "232323");
        assertEquals(suppli.getProducts().get(0), prod);
        assertEquals(suppli.getProducts().get(0).hashCode(), prod.hashCode());
        assertTrue(suppli.canEqual(suppli));
        assertEquals(suppli.hashCode(), suppli.hashCode());
        assertNotNull(suppli.toString());
        assertNotNull(suppli.getProducts().get(0).toString());
    }
}
