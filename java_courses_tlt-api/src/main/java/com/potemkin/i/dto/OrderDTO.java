package com.potemkin.i.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * Класс DTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
@ToString(exclude = "orderDate")
public class OrderDTO {
    
    private Integer orderId;

    private String orderNumber;
    
    private String orderDate;

    private double totalAmount;

    private Integer customerId;
    
    private Integer productId;

    private List<Integer> products;
}
