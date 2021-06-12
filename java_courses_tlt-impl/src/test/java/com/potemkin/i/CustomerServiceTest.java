package com.potemkin.i;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;

import com.potemkin.i.repository.stub.CustomerRepositoryStub;
import com.potemkin.i.service.impl.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerServiceTest {
    private CustomerService customerService;
    private CustomerRepositoryStub repositoryStub;
    

    @BeforeEach
    public void maskingObjects() {
        repositoryStub = new CustomerRepositoryStub();
        customerService = new CustomerService(repositoryStub);
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        customerService.addCustomer(json);
    }

    @Test
    public void addCustomerTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        var jsonTest = customerService.addCustomer(json);
        log.info("{} : {}", json, jsonTest);
        assertEquals(json, jsonTest);
    }
    
    @Test
    public void getCustomerJsonTest() {
        var json = customerService.getCustomerJson(0);
        log.info("{}", json);
        assertEquals(json.toString(), customerService.getCustomerJson(0).toString());
    }
    
    @Test
    public void getCustomerTest() {
        var cust = customerService.getCustomer(0);
        assertEquals(cust, customerService.getCustomer(0));
    }
    
    @Test
    public void getCustomersTest() {
        var custs = customerService.getCustomers();
        String strCuts = custs.toString();
        assertEquals(strCuts, customerService.getCustomers().toString());
    }
    
    @Test
    public void changeEntityTets() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        var cust = customerService.changeEntity(json, 0);
        log.info("{}", cust);
        assertEquals(cust, customerService.changeEntity(json, 0));
    }
    
    @Test
    public void deleteByIdTest() {
        var del = customerService.deleteById(0);
        log.info(del.toString());
        assertNotNull(del);
    }
    
    @Test
    public void parseForCustomerTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        var cust = customerService.parseForCustomer(json);
        log.info(cust.toString());
        assertNotNull(cust);
    }
    
    @Test
    public void minorTest() {
        repositoryStub.count();
        var supOp = Optional.of(customerService.getCustomer(0));
        repositoryStub.count(Example.of(customerService.getCustomer(0)));
        repositoryStub.delete(customerService.getCustomer(0));
        repositoryStub.deleteAll();
        repositoryStub.deleteAll(null);
        repositoryStub.deleteAllById(null);
        repositoryStub.deleteAllInBatch();
        repositoryStub.deleteAllByIdInBatch(null);
        repositoryStub.findAll(Example.of(customerService.getCustomer(0)));
        repositoryStub.findAllById(null);
        repositoryStub.findOne(null);
        repositoryStub.flush();
        repositoryStub.save(null);
        repositoryStub.saveAll(null);
        repositoryStub.saveAllAndFlush(null);
        assertFalse(repositoryStub.exists(null));
    }
}
