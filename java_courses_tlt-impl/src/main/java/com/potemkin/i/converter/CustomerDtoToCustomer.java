package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;

public class CustomerDtoToCustomer implements Converter<CustomerDTO, Customer> {

    @Override
    public Customer convert(CustomerDTO source) {
        var cust = new Customer();
        cust.setCustomerName(source.getCustomerName());
        cust.setPhone(source.getPhone());
        return cust;
    }

}
