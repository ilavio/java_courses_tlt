package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.CustomerConverter;
import com.potemkin.i.converter.OrderConverter;
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
 * Тестирование конвертера OrderConverter
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class OrderConverterTest {
    
    private OrderRepositoryStub repositoryStub;
    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;
    private CustomerRepositoryStub repositoryCustStub;
    private ProductRepositoryStub repositoryProdStub;
    private SupplierService supplierService;
    private SupplierRepositoryStub repositorySupStub;
    private OrderConverter orderConverter;
    private CustomerConverter customerConverter;
    
    @BeforeEach
    public void maskingObjects() {
        repositorySupStub = new SupplierRepositoryStub();
        repositoryProdStub = new ProductRepositoryStub();
        repositoryStub = new OrderRepositoryStub();
        repositoryCustStub = new CustomerRepositoryStub();
        supplierService = new SupplierService(repositorySupStub);
        productService = new ProductService(repositoryProdStub, supplierService);
        customerService = new CustomerService(repositoryCustStub);
        orderConverter = new OrderConverter(productService, customerService);
        orderService = new OrderService(repositoryStub);
        customerConverter = new CustomerConverter();
        JSONObject json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        JSONObject jsonCust = new JSONObject(
                "{\r\n" + "    \"customerName\": \"CHIGIK\",\r\n" + "    \"phone\": \"89377810580\"\r\n" + "}");
        var cust = customerConverter.parseForCustomer(jsonCust);
        customerService.addCustomer(cust);
        var ord = orderConverter.parseForOrder(json);
        orderService.addOrder(ord);
    }
    
    @Test
    public void dtoToOrderTest() {
        JSONObject json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        var ord = orderConverter.parseForOrder(json);
        var ordDTO = orderConverter.orderToDto(ord);
        var ordTest = orderConverter.dtoToOrder(ordDTO);
        log.info("OrderConverterTest dtoToOrderTest() - {}; {}", ord, ordTest);
        assertNotNull(ordTest);
    }
    
}
