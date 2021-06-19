package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.potemkin.i.converter.OrderDtoToOrder;
import com.potemkin.i.converter.OrderToOrderDTO;
import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.dto.OrderDTO;
import com.potemkin.i.resource.impl.OrderResourceImpl;
import com.potemkin.i.service.impl.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирование OrderResourceImpl
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@WebMvcTest(OrderResourceImpl.class)
@ContextConfiguration(classes = { OrderServiceImpl.class, OrderResourceImpl.class, OrderDtoToOrder.class,
        OrderToOrderDTO.class })
public class OrderResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getOrderTets() throws Exception {
        var ord = createOrder();
        when(orderService.getOrder(1)).thenReturn(ord);
        mockMvc.perform(MockMvcRequestBuilders.get("/Orders/1")).andExpect(status().isOk());
    }

    @Test
    public void changeOrderTest() throws Exception {
        var ordDTO = createDTO();
        var ord = createOrder();
        when(orderService.changeOrder(ord, 1)).thenReturn(ord);
        mockMvc.perform(MockMvcRequestBuilders.put("/Orders").accept(MediaType.APPLICATION_JSON).param("id", "1")
                .content(objectMapper.writeValueAsString(ordDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
    
    @Test
    public void getOrders() throws Exception {
        var ord = createOrder();
        List<Order> list = new ArrayList<>();
        list.add(ord);
        when(orderService.getOrders(1)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/Orders").param("orderId", "1")).andExpect(status().isOk());
    }
    
    @Test
    public void deleteTest() throws Exception {
        when(orderService.deleteById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/Orders").param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void addTest() throws Exception {
        var ordDTO = createDTO();
        var ord = createOrder();
        when(orderService.addOrder(any())).thenReturn(ord);
        mockMvc.perform(MockMvcRequestBuilders.post("/Orders").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ordDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
    
    public Order createOrder() {
        var ord = new Order();
        var cust = new Customer();
        cust.setCustomerId(1);
        ord.setOrderId(1);
        ord.setOrderNumber("111");
        ord.setCustomer(cust);
        var date = new Date();
        ord.setOrderDate(date);
        return ord;
    }
    
    public OrderDTO createDTO() {
        var dto = new OrderDTO();
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        String str = "22-05-2021";
        try {
            date = dateformate.parse(str);
        } catch (ParseException e) {
            log.info("Ошибка OrderDtoToOrder test() {}", e);
        }
        dto.setOrderDate(str);
        dto.setCustomerId(1);
        dto.setOrderId(1);
        dto.setOrderNumber("111");
        dto.setTotalAmount(1.1);
        dto.setProductId(1);
        return dto;
    }
}
