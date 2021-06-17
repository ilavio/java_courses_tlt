package com.potemkin.i.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RequiredArgsConstructor
@Transactional
public class OrderDtoToOrder implements Converter<OrderDTO, Order> {
//    private final ProductServiceImpl productServiceImpl;

    @Override
    public Order convert(OrderDTO source) {
        var ord = new Order();
        var cust = new Customer();
        if (source.getCustomerId() != null) {
            cust.setCustomerId(source.getCustomerId());
            ord.setCustomer(cust);
        }
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateformate.parse(source.getOrderDate());
        } catch (ParseException e) {
            log.info("Ошибка OrderDtoToOrder convert() {}", e);
        }
        ord.setOrderDate(date);
        ord.setOrderNumber(source.getOrderNumber());
        ord.setTotalAmount(source.getTotalAmount());

        if (source.getProductId() != null && source.getProductId() != 0) {
            var prod = new Product();
            prod.setProductId(source.getProductId());
//             var prod = productServiceImpl.getProduct(source.getProductId());
            ord.getProduct().add(prod);
            prod.getOrder().add(ord);
        }
        return ord;
    }

}
