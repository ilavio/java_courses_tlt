package com.potemkin.i.converter;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderToOrderDTO implements Converter<Order, OrderDTO>  {

    @Override
    public OrderDTO convert(Order source) {
        var ordDTO = new OrderDTO();
        log.info("OrderToOrderDTO convert() {}", source);
        ordDTO.setCustomerId(source.getCustomer().getCustomerId());
        ordDTO.setOrderDate(source.getOrderDate());
        var prodIdDTOs = new ArrayList<Integer>();
        for (Product prod : source.getProduct()) {
            prodIdDTOs.add(prod.getProductId());
        }
        ordDTO.setProducts(prodIdDTOs);
        ordDTO.setOrderNumber(source.getOrderNumber());
        ordDTO.setTotalAmount(source.getTotalAmount());
        ordDTO.setOrderId(source.getOrderId());
        return ordDTO;
    }
}
