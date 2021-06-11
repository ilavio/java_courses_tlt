package com.potemkin.i.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
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
        ordDTO.setCustomerId(source.getCustomer().getCustomerId());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(source.getOrderDate());
        } catch (JSONException | ParseException e) {
            log.error("Ошибка в OrderToOrderDTO convert() {}", e);
        }
        ordDTO.setOrderDate(date);
        var prodIdDTOs = new ArrayList<Integer>();
        for (Product prod : source.getProduct()) {
            prodIdDTOs.add(prod.getProductId());
        }
        ordDTO.setProducts(prodIdDTOs);
        ordDTO.setOrderNumber(source.getOrderNumber());
        ordDTO.setTotalAmount(source.getTotalAmount());
        return ordDTO;
    }
}
