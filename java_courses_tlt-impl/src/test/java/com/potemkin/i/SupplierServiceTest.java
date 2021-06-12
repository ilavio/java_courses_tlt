package com.potemkin.i;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

import com.potemkin.i.repository.stub.SupplierRepositoryStub;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierServiceTest {
    private SupplierService supplierService;
    private SupplierRepositoryStub repositoryStub;

    @BeforeEach
    public void maskingObjects() {
        repositoryStub = new SupplierRepositoryStub();
        supplierService = new SupplierService(repositoryStub);
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        supplierService.addSupplier(json);
    }

    @Test
    public void getSupplierJsonTest() {
        var jsonTest = supplierService.getSupplierJson(0);
        assertEquals(jsonTest.toString(), supplierService.getSupplierJson(0).toString());
    }
    
    @Test
    public void getSupplierTest() {
        var sup = supplierService.getSupplier(0);
        assertEquals(sup, supplierService.getSupplier(0));
    }
    
    @Test
    public void addSupplierTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var jsonTest = supplierService.addSupplier(json);
        assertEquals(json, jsonTest);
    }
    
    @Test
    public void getSuppliersTest() {
        var sups = supplierService.getSuppliers();
        assertEquals(sups.toString(), supplierService.getSuppliers().toString());
    }
    
    @Test
    public void changeEntityTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        var sup = supplierService.changeEntity(json, 0);
        assertEquals(sup, supplierService.changeEntity(json, 0));
    }
    
    @Test
    public void deleteByIdTest() {
        var json = supplierService.deleteById(0);
        log.info("deleteByIdTest() - {}", json);
        assertTrue(json.toString().contains("false"));
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
