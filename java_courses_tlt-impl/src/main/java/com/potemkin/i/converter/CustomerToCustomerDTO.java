package com.potemkin.i.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.dto.CustomerDTO;

/**
 * Класс CustomerToCustomerDTO конвертер из Customer в CustomerDTO
 * 
 * @author Илья Пот
 *
 */
public class CustomerToCustomerDTO implements Converter<Customer, CustomerDTO> {

    /**
     * Метод конвертации из Customer в CustomerDTO
     * 
     * @param Customer
     * @return CustomerDTO
     * 
     */
    @Override
    public CustomerDTO convert(Customer source) {
        var custDTO = new CustomerDTO();
        custDTO.setCustomerName(source.getCustomerName());
        custDTO.setCustomerId(source.getCustomerId());
        custDTO.setPhone(source.getPhone());
        List<Integer> ordsDTO = new ArrayList<>();
        for (Order ord : source.getOrders()) {
            ordsDTO.add(ord.getOrderId());
        }
        custDTO.setOrders(ordsDTO);
        return custDTO;
    }
}
