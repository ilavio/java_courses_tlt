package com.potemkin.i.resource.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.resource.CustomerResource;
import com.potemkin.i.service.impl.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс CustomerResourceImpl обработки запросов и взаимодествия с сущьностью
 * Customer
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {

    private final CustomerServiceImpl customerServiceImpl;
    private final ConversionService conversionService;

    @Override
    public CustomerDTO getCustomer(@PathVariable("id") int id) {
        var cust = customerServiceImpl.getCustomer(id);
        var dto = conversionService.convert(cust, CustomerDTO.class);
        return dto;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        var custs = customerServiceImpl.getCustomers();
        var custDTOs = custs.stream().map(cust -> conversionService.convert(cust, CustomerDTO.class))
                .collect(Collectors.toList());
        return custDTOs;
    }

    @Override
    public ResponseEntity<?> deleteById(@RequestParam(name = "id") int id) {
        var ex = customerServiceImpl.deleteById(id);
        if (ex) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @Override
    public CustomerDTO addCustomer(@RequestBody CustomerDTO custDTO) {
        var cust = conversionService.convert(custDTO, Customer.class);
        var custDTOresp = conversionService.convert(customerServiceImpl.addCustomer(cust), CustomerDTO.class);
        return custDTOresp;
    }

    @Override
    public CustomerDTO chengeCustomer(@RequestBody CustomerDTO custDTO, @RequestParam(name = "id") int id) {
        var cust = conversionService.convert(custDTO, Customer.class);
        var custDTOresp = conversionService.convert(customerServiceImpl.chengeEntity(cust, id), CustomerDTO.class);
        log.info("CustomerResourceImpl chengeCustomer() {}", custDTOresp);
        return custDTOresp;
    }

}
