package com.potemkin.i.resource.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.dto.OrderDTO;
import com.potemkin.i.resource.OrderResource;
import com.potemkin.i.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс OrderResourceImpl обработки запросов и взаимодествия с сущьностью Order
 * 
 * @author Илья Пот
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {
    private final OrderServiceImpl orderServiceImpl;
    private final ConversionService conversionService;

    /**
     * Метод добавление сущности Order в базу данных
     * 
     * @param strOrd
     * @return OrderDTO
     */
    public OrderDTO addOrder(@RequestBody OrderDTO dto) {
        var ord = conversionService.convert(dto, Order.class);
        log.info("OrderResourcesImpl addOrder {}", ord);
        var ordResp = orderServiceImpl.addOrder(ord);
        var ordDTO = conversionService.convert(ordResp, OrderDTO.class);
        return ordDTO;
    }

    /**
     * Метод получения сущности Order
     * 
     * @param id
     * @return OrderDTO
     */
    public OrderDTO getOrder(@PathVariable("id") int id) {
        var dto = conversionService.convert(orderServiceImpl.getOrder(id), OrderDTO.class);
        log.info("OrderResourceImpl getOrder() {}", dto);
        return dto;
    }

    /**
     * Метод получения списка Order с одинаковым Customer
     * 
     * @param customerId
     * @return List<OrderDTO>
     */
    public List<OrderDTO> getOrders(@RequestParam(name = "orderId") int orderId) {
        var ords = orderServiceImpl.getOrders(orderId);
        var dtos = ords.stream().map(ord -> conversionService.convert(ord, OrderDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
    
    /**
     * Метод изменения сущности Order
     * 
     * @param strOrd
     * @param id
     * @return OrderDTO
     */
    public OrderDTO changeOrder(@RequestBody OrderDTO dto, @RequestParam(name = "id") int id) {
        var ord = conversionService.convert(dto, Order.class);
        var ordResp = orderServiceImpl.changeOrder(ord, id);
        var ordDTO = conversionService.convert(ordResp, OrderDTO.class);
        log.info("OrderResourceImpl changeOrder() {}", ordDTO);
        return ordDTO;
    }
    
    /**
     * Метод удаления по id сущности из базы данных
     * 
     * @param id
     * @return String
     */
    public String deleteById(@RequestParam(name = "id") int id) {
        log.info("deleteById() id - {}", id);
        var ex = orderServiceImpl.deleteById(id);
        log.info("OrderResourcesImpl deleteById() {}", ex);
        String str = "{" + "\"Delete Order\" : " + Boolean.toString(ex) + "}";
        var json = new JSONObject(str);
        return json.toString();
    }
}
