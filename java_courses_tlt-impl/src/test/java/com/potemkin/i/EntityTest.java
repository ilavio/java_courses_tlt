package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.config.SpringConfigLocal;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.domain.entity.Supplier;

public class EntityTest {
    private SpringConfigLocal local = new SpringConfigLocal();
    private Customer cust;
    private Product prod;
    private Order ord;
    private Supplier sup;

    @BeforeEach
    public void entityCreate() {
        cust = local.customer();
        prod = local.product();
        ord = local.order();
        sup = local.supplier();
    }

    @Test
    public void customerTestToString() {
        var custTest = local.customer();
        String str = cust.toString();

        assertNotNull(str);
        assertEquals(cust.hashCode(), custTest.hashCode());
    }

    @Test
    public void productTestToString() {
        var prodTest = local.product();
        String str = prod.toString();

        assertNotNull(str);
        assertEquals(prod.hashCode(), prodTest.hashCode());
        assertTrue(prod.equals(prodTest));
    }

    @Test
    public void supplierTestToString() {
        var supTest = local.supplier();
        String str = sup.toString();

        assertNotNull(str);
        assertEquals(sup.hashCode(), supTest.hashCode());
    }
    
    @Test
    public void orderTestToString() {
        var ordTest = local.order();
        String str = ord.toString();

        assertNotNull(str);
        assertEquals(ord.hashCode(), ordTest.hashCode());
    }
}
