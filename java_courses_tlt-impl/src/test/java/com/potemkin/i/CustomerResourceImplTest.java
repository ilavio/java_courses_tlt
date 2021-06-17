package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.resource.impl.CustomerResourceImpl;
import com.potemkin.i.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования CustomerResourceImpl
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class CustomerResourceImplTest {

    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CustomerResourceImpl resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomerTest() {
        var cust = new Customer();
        var dto = new CustomerDTO();
        when(conversionService.convert(any(), eq(Customer.class))).thenReturn(cust);
        when(conversionService.convert(cust, CustomerDTO.class)).thenReturn(dto);
        when(customerService.getCustomer(0)).thenReturn(cust);
        resources.getCustomer(0);
        verify(customerService).getCustomer(eq(0));
    }

    @Test
    public void addCustomer() {
        var cust = new Customer();
        var dto = new CustomerDTO();
        when(conversionService.convert(any(), eq(Customer.class))).thenReturn(cust);
        when(conversionService.convert(cust, CustomerDTO.class)).thenReturn(dto);
        Mockito.doReturn(cust).when(customerService).addCustomer(any());
        resources.addCustomer(dto);
        verify(customerService).addCustomer(any());
    }

    @Test
    public void changeCustomerTetst() {
        var dto = new CustomerDTO();
        var cust = new Customer();
        when(conversionService.convert(any(), eq(Customer.class))).thenReturn(cust);
        when(conversionService.convert(cust, CustomerDTO.class)).thenReturn(dto);
        when(customerService.chengeEntity(any(), eq(0))).thenReturn(cust);
        resources.chengeCustomer(dto, 0);
        verify(customerService).chengeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        when(customerService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(customerService).deleteById(eq(0));
    }

    @Test
    public void getCustomersTest() {
        var dto = new CustomerDTO();
        var cust = new Customer();
        List<Customer> list = new ArrayList<>();
        list.add(cust);
        List<CustomerDTO> listDTO = new ArrayList<>();
        listDTO.add(dto);
        when(conversionService.convert(cust, CustomerDTO.class)).thenReturn(dto);
        when(customerService.getCustomers()).thenReturn(list);
        resources.getCustomers();
        verify(customerService).getCustomers();
    }
}
