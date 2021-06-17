package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;
import com.potemkin.i.service.impl.SupplierServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class SupplierServiceImplTest {
    
    @Autowired
    private SupplierServiceImpl supplierServiceImpl;
    
    @Test
    public void addSupplierTest() {
        var sup = new Supplier();
        sup.setSupplierId(1);
        sup.setCompanyName("L");
        supplierServiceImpl.addSupplier(sup);
        var supTest = supplierServiceImpl.getSupplier(1);
        assertEquals(sup, supTest);
    }
    
    @Test
    public void getAndDeleteTest() {
        var sup = new Supplier();
        sup.setSupplierId(2);
        sup.setCompanyName("L");
        supplierServiceImpl.addSupplier(sup);
        var sups = supplierServiceImpl.getSuppliers();
        var ex = supplierServiceImpl.deleteById(2);
        log.info("getAndDeleteTest() {}", ex);
        assertNotNull(sups);
    }
    
    @TestConfiguration
    static class MyTestConfig {
        @Bean
        public SupplierServiceImpl supplierServiceImpl(SupplierRepository supplierRepository) {
            return new SupplierServiceImpl(supplierRepository);
        }
    }
}
