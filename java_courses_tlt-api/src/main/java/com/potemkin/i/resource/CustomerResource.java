package com.potemkin.i.resource;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.potemkin.i.dto.CustomerDTO;

/**
 * Интрефейс CustomerResources для операций Rest с сущностью Customer
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Customers")
public interface CustomerResource {

    /**
     * Метод запроса Customer по id
     * 
     * @param id
     * @return String
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomer(int id);

    /**
     * Метод запроса списка Customer
     * 
     * @return String
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getCustomers();

    /**
     * Метод удаления Customer
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(int id);

    /**
     * Метод добавления Customer
     * 
     * @param strCust
     * @return String
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO addCustomer(CustomerDTO custDTO);

    /**
     * Метод изменения Customer
     * 
     * @param custDTO
     * @return CustomerDTO
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO chengeCustomer(CustomerDTO custDTO, int id);
}
