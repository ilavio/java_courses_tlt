package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.potemkin.i.converter.CustomerConverter;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.CustomerDTO;
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
    @Mock
    private CustomerConverter customerConverter;
    
    @InjectMocks
    private ControllerCustomerResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomerTest() {
        var cust = new Customer();
        var dto = new CustomerDTO();
        when(customerConverter.parseForCustomer(any())).thenReturn(cust);
        when(customerConverter.customerToDto(cust)).thenReturn(dto);
        when(customerService.getCustomer(0)).thenReturn(cust);
        resources.getCustomer(0);
        verify(customerService).getCustomer(eq(0));
    }

    @Test
    public void addCustomersTest() {
        var cust = new Customer();
        var dto = new CustomerDTO();
        when(customerConverter.parseForCustomer(any())).thenReturn(cust);
        when(customerConverter.customerToDto(cust)).thenReturn(dto);
        var json = new JSONObject("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}");
        log.info("{}", json);
        Mockito.doReturn(cust).when(customerService).addCustomer(any());
        resources.addCustomer("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}");
        verify(customerService).addCustomer(any());
    }

    @Test
    public void changeCustomerTetst() {
        var dto = new CustomerDTO();
        var cust = new Customer();
        when(customerConverter.customerToDto(cust)).thenReturn(dto);
        when(customerService.changeEntity(any(), eq(0))).thenReturn(cust);
        resources.changeCustomer("{\r\n" + "\"customerName\":\"CHIGIK\",\r\n" + "\"phone\":\"89377810580\"\r\n" + "}",
                0);
        verify(customerService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        when(customerService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(customerService).deleteById(eq(0));
    }
    
    @Test
    public void getCustomersTest() {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer());
        List<CustomerDTO> listDTO = new ArrayList<>();
        listDTO.add(new CustomerDTO());
        when(customerService.getCustomers()).thenReturn(list);
        when(customerConverter.customerToDto(list)).thenReturn(listDTO);
        var custs = resources.getCustomers();
        verify(customerService).getCustomers();
    }
}
