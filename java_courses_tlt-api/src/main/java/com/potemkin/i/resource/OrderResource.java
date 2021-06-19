package com.potemkin.i.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.potemkin.i.dto.OrderDTO;

/**
 * Интрефейс OrderResources для операций Rest с сущностью Order
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Orders")
public interface OrderResource {

    /**
     * Метод добавления Order
     * 
     * @param strOrd
     * @return OrderDTO
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public OrderDTO addOrder(OrderDTO dto);

    /**
     * Метод запроса Order по id
     * 
     * @param id
     * @return OrderDTO
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO getOrder(int id);

    /**
     * Метод получения списка Order по customerId
     * 
     * @param customerId
     * @return List<OrderDTO>
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrders(int orderId);

    /**
     * Метод изменения Order по id
     * 
     * @param dto
     * @param id
     * @return OrderDTO
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO changeOrder(OrderDTO dto, int id);

    /**
     * Метод удаления Order по id
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(int id);
}
