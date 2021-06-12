package com.potemkin.i.dto;

import lombok.Data;

/**
 * Класс DTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
public class CustomerDTO {

    private Integer customerId;

    private String customerName;

    private String phone;
}
