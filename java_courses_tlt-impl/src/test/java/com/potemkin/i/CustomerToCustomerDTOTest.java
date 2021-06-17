package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.CustomerToCustomerDTO;
import com.potemkin.i.domain.entity.Customer;

public class CustomerToCustomerDTOTest {
    
    private CustomerToCustomerDTO customerToCustomerDTO;
    
    @BeforeEach
    private void masking() {
        customerToCustomerDTO = new CustomerToCustomerDTO();
    }
    
    @Test
    public void test() {
        var cust = new Customer();
        cust.setCustomerId(1);
        cust.setCustomerName("Y");
        cust.setPhone("8");
        var dto = customerToCustomerDTO.convert(cust);
        assertNotNull(dto);
    }

}
