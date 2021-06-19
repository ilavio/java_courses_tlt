package com.potemkin.i.dto;

import java.util.List;

import lombok.Data;

/**
 * Класс DTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
public class SupplierDTO {

    private Integer supplierId;

    private String companyName;

    private String phone;

    private List<Integer> products;
}
