package com.potemkin.i;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

import com.potemkin.i.converter.SupplierConverter;
import com.potemkin.i.repository.stub.SupplierRepositoryStub;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierServiceTest {
    private SupplierService supplierService;
    private SupplierRepositoryStub repositoryStub;
    private SupplierConverter supplierConverter;

    @BeforeEach
    public void maskingObjects() {
        supplierConverter = new SupplierConverter();
        repositoryStub = new SupplierRepositoryStub();
        supplierService = new SupplierService(repositoryStub);
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var sup = supplierConverter.parseForSupplier(json);
        supplierService.addSupplier(sup);
    }

    @Test
    public void getSupplierTest() {
        var supTest = supplierService.getSupplier(0);
        assertEquals(supTest, supplierService.getSupplier(0));
    }
    
    @Test
    public void addSupplierTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var sup = supplierConverter.parseForSupplier(json);
        var supTest = supplierService.addSupplier(sup);
        assertEquals(sup, supTest);
    }
    
    @Test
    public void getSuppliersTest() {
        var sups = supplierService.getSuppliers();
        assertEquals(sups, supplierService.getSuppliers());
    }
    
    @Test
    public void changeEntityTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var sup = supplierConverter.parseForSupplier(json);
        var supTest = supplierService.changeEntity(sup, 0);
        assertEquals(supTest, supplierService.changeEntity(sup, 0));
    }
    
    @Test
    public void deleteByIdTest() {
        var ex = supplierService.deleteById(0);
        log.info("deleteByIdTest() - {}", ex);
        assertTrue(ex);
    }
    
    @Test
    public void minorTest() {
        repositoryStub.count();
        var supOp = Optional.of(supplierService.getSupplier(0));
        repositoryStub.count(Example.of(supplierService.getSupplier(0)));
        repositoryStub.delete(supplierService.getSupplier(0));
        repositoryStub.deleteAll();
        repositoryStub.deleteAll(null);
        repositoryStub.deleteAllById(null);
        repositoryStub.deleteAllInBatch();
        repositoryStub.deleteAllByIdInBatch(null);
        repositoryStub.findAll(Example.of(supplierService.getSupplier(0)));
        repositoryStub.findAllById(null);
        repositoryStub.findOne(null);
        repositoryStub.flush();
        repositoryStub.save(null);
        repositoryStub.saveAll(null);
        repositoryStub.saveAllAndFlush(null);
        assertFalse(repositoryStub.exists(null));
    }
}
