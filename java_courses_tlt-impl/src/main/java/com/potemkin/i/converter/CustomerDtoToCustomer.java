package com.potemkin.i.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;

/**
 * Класс CustomerToCustomerDTO конвертер из CustomerDTO в Customer
 * 
 * @author Илья Пот
 *
 */
@Component
public class CustomerDtoToCustomer implements Converter<CustomerDTO, Customer> {

    /**
     * Метод преоброзования
     * 
     * @return Customer
     */
    @Override
    public Customer convert(CustomerDTO source) {
        var cust = new Customer();
        cust.setCustomerName(source.getCustomerName());
        cust.setPhone(source.getPhone());
        return cust;
    }

}
