package com.potemkin.i.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDtoToOrder implements Converter<OrderDTO, Order> {

    @Override
    public Order convert(OrderDTO source) {
        var ord = new Order();
        var cust = new Customer();
        cust.setCustomerId(source.getCustomerId());
        ord.setCustomer(cust);
//        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        date = source.getOrderDate();
        ord.setOrderDate(date);
        ord.setOrderId(source.getOrderId());
        ord.setOrderNumber(source.getOrderNumber());
        ord.setTotalAmount(source.getTotalAmount());
        List<Product> prods = source.getProducts().stream().map(x -> {
            Product prod = new Product();
            prod.setProductId(x);
            return prod;
        }).collect(Collectors.toList());
        ord.setProduct(prods);
        return ord;
    }

}
