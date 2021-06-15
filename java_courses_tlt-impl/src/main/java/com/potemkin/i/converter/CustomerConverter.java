package com.potemkin.i.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;

/**
 * Конвертер Customer в CustomerDTO и обратно
 * 
 * @author Илья Пот
 *
 */
@Component
public class CustomerConverter {

    /**
     * Метод преобразования в Customer
     * 
     * @param dto
     * @return Customer
     */
    public Customer dtoToCustomer(CustomerDTO dto) {
        var cust = new Customer();
        cust.setCustomerName(dto.getCustomerName());
        cust.setPhone(dto.getPhone());
        cust.setCustomerId(dto.getCustomerId());
        return cust;
    }

    /**
     * Метод преоброзования в DTO
     * 
     * @param cust
     * @return CustomerDTO
     */
    public CustomerDTO customerToDto(Customer cust) {
        var dto = new CustomerDTO();
        dto.setCustomerId(cust.getCustomerId());
        dto.setCustomerName(cust.getCustomerName());
        dto.setPhone(cust.getPhone());
        return dto;
    }

    /**
     * Метод преоброзования листа Customer в CustomerDTO
     * 
     * @param custs
     * @return
     */
    public List<CustomerDTO> customerToDto(List<Customer> custs) {
        var custDTO = custs.stream().map(cust -> customerToDto(cust)).collect(Collectors.toList());
        return custDTO;
    }

    /**
     * Метод преобразования лист CustomerDTO в Customer
     * 
     * @param custsDTO
     * @return
     */
    public List<Customer> dtoToCustomer(List<CustomerDTO> custsDTO) {
        var custs = custsDTO.stream().map(custDTO -> dtoToCustomer(custDTO)).collect(Collectors.toList());
        return custs;
    }

    /**
     * Метод разбора JSONObject для создания Customer
     * 
     * @param json
     * @return Customer
     */
    public Customer parseForCustomer(JSONObject json) {
        Customer cust = new Customer();
        if (json != null) {
            cust.setCustomerName(json.getString("customerName"));
            cust.setPhone(json.getString("phone"));
        }
        return cust;
    }
}
