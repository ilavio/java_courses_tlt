package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    @InjectMocks
    private ControllerOrderResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOrderTets() {
        var json = new JSONObject();
        when(orderService.getOrderJson(0)).thenReturn(json);
        resources.getOrder(0);
        verify(orderService).getOrderJson(eq(0));
    }

    @Test
    public void changeOrderTest() {
        var json = new JSONObject("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}");
        when(orderService.changeEntity(any(), eq(0))).thenReturn(json);
        var str = resources.changeOrder("{\r\n" + "    \"orderNumber\" : \"121\",\r\n"
                + "    \"orderDate\" : \"23-05-2021\",\r\n" + "    \"totalAmount\" : 12.02,\r\n"
                + "    \"customerId\" : 2,\r\n" + "    \"productId\" : 1\r\n" + "}", 0);
        log.info("changeOrderTest() - {}", str);
        verify(orderService).changeEntity(any(), eq(0));
    }
}
