package com.potemkin.i;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.converter.OrderDtoToOrder;
import com.potemkin.i.converter.OrderToOrderDTO;
import com.potemkin.i.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDtoToOrderTest {

    private OrderDtoToOrder orderDtoToOrder;
    private OrderToOrderDTO orderToOrderDTO;
    
    @BeforeEach
    public void masking() {
        orderDtoToOrder = new OrderDtoToOrder();
        orderToOrderDTO = new OrderToOrderDTO();
    }
    
    @Test
    public void test() {
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
        var ord = orderDtoToOrder.convert(dto);
        var dtoTest = orderToOrderDTO.convert(ord);
        assertEquals(ord.getOrderId(), dtoTest.getOrderId());
    }
}
