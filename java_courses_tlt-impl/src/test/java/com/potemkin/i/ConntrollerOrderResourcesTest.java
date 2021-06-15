package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.potemkin.i.converter.OrderConverter;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.OrderDTO;
import com.potemkin.i.dto.ProductDTO;
import com.potemkin.i.resource.impl.ControllerOrderResources;
import com.potemkin.i.service.impl.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирование ConntrollerOrderResources
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class ConntrollerOrderResourcesTest {

    @Mock
    private OrderService orderService;
    @Mock
    private OrderConverter orderConverter;
    @InjectMocks
    private ControllerOrderResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOrderTets() {
        var ord = new Order();
        var ordDTO = new OrderDTO();
        when(orderService.getOrder(0)).thenReturn(ord);
        when(orderConverter.orderToDto(ord)).thenReturn(ordDTO);
        resources.getOrder(0);
        verify(orderService).getOrder(eq(0));
    }

    @Test
    public void changeOrderTest() {
        var ordDTO = new OrderDTO();
        var ord = new Order();
        when(orderService.changeOrder(any(), eq(0))).thenReturn(ord);
        when(orderConverter.orderToDto(ord)).thenReturn(ordDTO);
        var str = resources.changeOrder("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}", 0);
        log.info("changeOrderTest() - {}", str);
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
        when(orderService.getOrders(0)).thenReturn(list);
        when(orderConverter.orderToDto(list)).thenReturn(listDTO);
        resources.getOrders(0);
        verify(orderService).getOrders(eq(0));
        
    }
}
