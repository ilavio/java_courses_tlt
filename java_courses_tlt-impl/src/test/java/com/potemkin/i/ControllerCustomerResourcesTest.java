package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.potemkin.i.resource.impl.ControllerCustomerResources;
import com.potemkin.i.service.impl.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования ControllerCustomerResources
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class ControllerCustomerResourcesTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private ControllerCustomerResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomerTest() {
        var json = new JSONObject();
        when(customerService.getCustomerJson(0)).thenReturn(json);
        resources.getCustomer(0);
        verify(customerService).getCustomerJson(eq(0));
    }

    @Test
    public void addCustomerTest() {
        var json = new JSONObject("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}");
        log.info("{}", json);
        Mockito.doReturn(json).when(customerService).addCustomer(any());
        resources.addCustomer("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}");
        verify(customerService).addCustomer(any());
    }

    @Test
    public void changeCustomerTetst() {
        var json = new JSONObject("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}");
        when(customerService.changeEntity(any(), eq(0))).thenReturn(json);
        resources.changeCustomer("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}",
                0);
        verify(customerService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        var json = new JSONObject("{\"Found Customer\":\" false \" }");
        when(customerService.deleteById(0)).thenReturn(json);
        resources.deleteById(0);
        verify(customerService).deleteById(eq(0));
    }
}
