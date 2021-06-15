package com.potemkin.i.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Класс DTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
public class OrderDTO {

    private Integer orderId;

    private String orderNumber;

    private Date orderDate;

    private double totalAmount;

    private Integer customerId;

    private List<Integer> products;

    public String getorderDate() {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateformate.format(orderDate);
        return strDate;
    }
}
