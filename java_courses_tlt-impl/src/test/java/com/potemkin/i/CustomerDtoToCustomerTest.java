package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.CustomerDtoToCustomer;
import com.potemkin.i.dto.CustomerDTO;

public class CustomerDtoToCustomerTest {
    
    private CustomerDtoToCustomer customerDtoToCustomer;
    
    @BeforeEach
    public void masking() {
        customerDtoToCustomer = new CustomerDtoToCustomer();
    }
    
    @Test
    public void test() {
        var custDTO = new CustomerDTO();
        custDTO.setCustomerId(1);
        custDTO.setCustomerName("Y");
        custDTO.setPhone("8");
        var cust = customerDtoToCustomer.convert(custDTO);
        assertNotNull(cust);
    }
}
