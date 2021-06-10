package com.potemkin.i;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;

import com.potemkin.i.repository.stub.CustomerRepositoryStub;
import com.potemkin.i.repository.stub.OrderRepositoryStub;
import com.potemkin.i.repository.stub.ProductRepositoryStub;
import com.potemkin.i.repository.stub.SupplierRepositoryStub;
import com.potemkin.i.service.impl.CustomerService;
import com.potemkin.i.service.impl.OrderService;
import com.potemkin.i.service.impl.ProductService;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс теста OrderService
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class OrderServiceTest {
    private OrderRepositoryStub repositoryStub;
    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;
    private CustomerRepositoryStub repositoryCustStub;
    private ProductRepositoryStub repositoryProdStub;
    private SupplierService supplierService;
    private SupplierRepositoryStub repositorySupStub;

    @BeforeEach
    public void maskingObjects() {
        repositorySupStub = new SupplierRepositoryStub();
        repositoryProdStub = new ProductRepositoryStub();
        repositoryStub = new OrderRepositoryStub();
        repositoryCustStub = new CustomerRepositoryStub();
        supplierService = new SupplierService(repositorySupStub);
        productService = new ProductService(repositoryProdStub, supplierService);
        customerService = new CustomerService(repositoryCustStub);
        orderService = new OrderService(repositoryStub, productService, customerService);
        JSONObject json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        JSONObject jsonCust = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        customerService.addCustomer(jsonCust);
        orderService.addOrder(json);
    }

    @Test
    public void addOrderTets() {
        JSONObject json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        orderService.addOrder(json);
        var jsonTest = orderService.getOrderJson(0);
        assertEquals(orderService.getOrderJson(0).toString(), jsonTest.toString());
    }

    @Test
    public void getOrderTest() {
        var ord = orderService.getOrder(0);
        log.info("{}", ord);
        assertEquals(ord, orderService.getOrder(0));
    }

    @Test
    public void getOrdersTets() {
        var ords = orderService.getOrders(0);
        log.info("getOrdersTets() - {}", ords);
        assertEquals(ords.toString(), orderService.getOrders(0).toString());
    }

    @Test
    public void changeEntityTest() {
        var json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        var ord = orderService.changeEntity(json, 0);
        assertNotNull(ord);
    }

    @Test
    public void deleteById() {
        var json = orderService.deleteById(0);
        log.info("deleteById() - {}", json);
        assertTrue(json.toString().contains("false"));
    }

    @Test
    public void minorTest() {
        repositoryStub.count();
        var supOp = Optional.of(orderService.getOrder(0));
        repositoryStub.count(Example.of(orderService.getOrder(0)));
        repositoryStub.delete(orderService.getOrder(0));
        repositoryStub.deleteAll();
        repositoryStub.deleteAll(null);
        repositoryStub.deleteAllById(null);
        repositoryStub.deleteAllInBatch();
        repositoryStub.deleteAllByIdInBatch(null);
        repositoryStub.findAll(Example.of(orderService.getOrder(0)));
        repositoryStub.findAllById(null);
        repositoryStub.findOne(null);
        repositoryStub.flush();
        repositoryStub.save(null);
        repositoryStub.saveAll(null);
        repositoryStub.saveAllAndFlush(null);
        assertFalse(repositoryStub.exists(null));
    }
}
