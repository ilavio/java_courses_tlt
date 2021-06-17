package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.dto.CustomerDTO;
import com.potemkin.i.dto.OrderDTO;
import com.potemkin.i.resource.impl.OrderResourceImpl;
import com.potemkin.i.service.impl.OrderServiceImpl;

/**
 * Класс тестирование OrderResourceImpl
 * 
 * @author Илья Пот
 *
 */
public class OrderResourceImplTest {

    @Mock
    private OrderServiceImpl orderService;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private OrderResourceImpl resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOrderTets() {
        var ord = new Order();
        var ordDTO = new OrderDTO();
        when(conversionService.convert(any(), eq(Order.class))).thenReturn(ord);
        when(conversionService.convert(ord, OrderDTO.class)).thenReturn(ordDTO);
        when(orderService.getOrder(0)).thenReturn(ord);
        resources.getOrder(0);
        verify(orderService).getOrder(eq(0));
    }

    @Test
    public void changeOrderTest() {
        var ordDTO = new OrderDTO();
        var ord = new Order();
        when(conversionService.convert(any(), eq(Order.class))).thenReturn(ord);
        when(conversionService.convert(ord, OrderDTO.class)).thenReturn(ordDTO);
        when(orderService.changeOrder(any(), eq(0))).thenReturn(ord);
        resources.changeOrder(ordDTO, 0);
        verify(orderService).changeOrder(any(), eq(0));
    }
    
    @Test
    public void getOrders() {
        var ord = new Order();
        var ordDTO = new OrderDTO();
        List<Order> list = new ArrayList<>();
        list.add(ord);
        List<OrderDTO> listDTO = new ArrayList<>();
        listDTO.add(ordDTO);
        when(conversionService.convert(any(), eq(Order.class))).thenReturn(ord);
        when(conversionService.convert(ord, OrderDTO.class)).thenReturn(ordDTO);
        when(orderService.getOrders(eq(0))).thenReturn(list);
        resources.getOrders(0);
        verify(orderService).getOrders(eq(0));
    }
    
    @Test
    public void deleteTest() {
        when(orderService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(orderService).deleteById(eq(0));
    }
    
    @Test
    public void addTest() {
        var ord = new Order();
        ord.setOrderDate(new Date());
        var ordDTO = new OrderDTO();
        when(conversionService.convert(any(), eq(Order.class))).thenReturn(ord);
        when(conversionService.convert(ord, OrderDTO.class)).thenReturn(ordDTO);
        when(orderService.addOrder(ord)).thenReturn(ord);
        resources.addOrder(ordDTO);
        verify(orderService).addOrder(ord);
    }
}
