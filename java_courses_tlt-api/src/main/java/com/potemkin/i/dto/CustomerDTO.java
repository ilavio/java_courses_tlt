package com.potemkin.i.dto;

import java.util.List;

import lombok.Data;

/**
 * Класс CustomerDTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
public class CustomerDTO {

    private Integer customerId;

    private String customerName;

    private String phone;

    private List<Integer> orders;
}
