package com.potemkin.i.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.dto.OrderDTO;
import com.potemkin.i.service.impl.CustomerService;
import com.potemkin.i.service.impl.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Конвертер OrderDTO в Order и обратно
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class OrderConverter {

    private final ProductService productService;
    private final CustomerService customerService;

    /**
     * Метод преоброзования OrderDTO в Order
     * 
     * @param dto
     * @return Order
     */
    public Order dtoToOrder(OrderDTO dto) {
        var ord = new Order();
        var cust = new Customer();
        cust.setCustomerId(dto.getCustomerId());
        ord.setCustomer(cust);
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateformate.parse(dto.getorderDate());
        } catch (ParseException e) {
            log.error("OrderConverter dtoToOrder() - {}", e);
        }
        ord.setOrderDate(date);
        ord.setOrderId(dto.getOrderId());
        ord.setOrderNumber(dto.getOrderNumber());
        ord.setTotalAmount(dto.getTotalAmount());
        List<Product> prods = dto.getProducts().stream().map(x -> {
            Product prod = new Product();
            prod.setProductId(x);
            return prod;
        }).collect(Collectors.toList());
        ord.setProduct(prods);
        return ord;
    }

    /**
     * Метод преоброзования Order в OrderDTO
     * 
     * @param ord
     * @return
     */
    public OrderDTO orderToDto(Order ord) {
        var dto = new OrderDTO();
        dto.setCustomerId(ord.getCustomer().getCustomerId());
        Date date = null;
        try {
            SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
            date = dateformate.parse(ord.getOrderDate());
        } catch (ParseException e) {
            log.error("OrderConverter dtoToOrder() - {}", e);
        }
        dto.setOrderDate(date);
        dto.setCustomerId(ord.getCustomer().getCustomerId());
        dto.setOrderId(ord.getOrderId());
        dto.setOrderNumber(ord.getOrderNumber());
        var prods = ord.getProduct().stream().map(prod -> prod.getProductId()).collect(Collectors.toList());
        dto.setProducts(prods);
        dto.setTotalAmount(ord.getTotalAmount());
        return dto;
    }

    /**
     * Метод преобразования лист OrderDTO в <Order
     * 
     * @param dtos
     * @return
     */
    public List<Order> dtoToProduct(List<OrderDTO> dtos) {
        var ords = dtos.stream().map(dto -> dtoToOrder(dto)).collect(Collectors.toList());
        return ords;
    }

    /**
     * Метод преоброзования листа Order в OrderDTO
     * 
     * @param ords
     * @return
     */
    public List<OrderDTO> orderToDto(List<Order> ords) {
        var dtos = ords.stream().map(ord -> orderToDto(ord)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * Метод разбора JSONObject для создания Order
     * 
     * @param json
     * @return Order
     */
    public Order parseForOrder(JSONObject json) {
        Order order = new Order();
        if (json != null) {
            order.setOrderNumber(json.getString("orderNumber"));
            order.setTotalAmount(json.getDouble("totalAmount"));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(json.getString("orderDate"));
            } catch (JSONException | ParseException e) {
                log.error("Ошибка в OrderConverter parseForOrder() {}", e);
            }
            order.setOrderDate(date);
            if (json.has("productId")) {
                var prod = productService.getProduct(json.getInt("productId"));
                order.addProduct().add(prod);
                prod.addOrder().add(order);
            }
            var cust = customerService.getCustomer(json.getInt("customerId"));
            order.setCustomer(cust);
        }
        log.info("2---->>> ControllerOrderResources parseForOrder {}", order.toString());
        return order;
    }
}
