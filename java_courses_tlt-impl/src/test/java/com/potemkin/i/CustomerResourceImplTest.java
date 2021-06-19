package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potemkin.i.converter.CustomerDtoToCustomer;
import com.potemkin.i.converter.CustomerToCustomerDTO;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.resource.impl.CustomerResourceImpl;
import com.potemkin.i.service.impl.CustomerServiceImpl;

/**
 * Тестирование CustomerResourceImpl
 * 
 * @author Илья Пот
 *
 */
@WebMvcTest(CustomerResourceImpl.class)
@ContextConfiguration(classes = { CustomerServiceImpl.class, CustomerResourceImpl.class, CustomerDtoToCustomer.class,
        CustomerToCustomerDTO.class })
public class CustomerResourceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addCustomer() throws Exception {
        var cust = createCust();
        var dto = createDTO();
        when(this.customerService.addCustomer(any())).thenReturn(cust);
        mockMvc.perform(MockMvcRequestBuilders.post("/Customers").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("customerName").value(cust.getCustomerName()));
    }

    @Test
    public void getCustomerTest() throws Exception {
        var cust = createCust();
        when(customerService.getCustomer(1)).thenReturn(cust);
        mockMvc.perform(MockMvcRequestBuilders.get("/Customers/1")).andExpect(status().isOk());
    }

    @Test
    public void getCustomersTest() throws Exception {
        var cust = createCust();
        List<Customer> list = new ArrayList<>();
        list.add(cust);
        when(customerService.getCustomers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/Customers")).andExpect(status().isOk());
    }

    @Test()
    public void chengeCustomerTest() throws Exception {
        var cust = createCust();
        var dto = createDTO();
        when(customerService.chengeEntity(cust, 1)).thenReturn(cust);
        mockMvc.perform(MockMvcRequestBuilders.put("/Customers").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)).param("id", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test()
    public void deleteByIdTest() throws Exception {
        when(customerService.deleteById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/Customers").param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public Customer createCust() {
        var cust = new Customer();
        cust.setCustomerId(1);
        cust.setCustomerName("L");
        cust.setPhone("8");
        return cust;
    }

    public CustomerDTO createDTO() {
        var dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setCustomerName("L");
        dto.setPhone("8");
        return dto;
    }
}
