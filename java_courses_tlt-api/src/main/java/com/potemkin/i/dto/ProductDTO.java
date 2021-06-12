package com.potemkin.i.dto;

import lombok.Data;

/**
 * Класс DTO для обмена REST
 * 
 * @author Илья Пот
 *
 */
@Data
public class ProductDTO {
    private Integer productId;

    private String productName;

    private double unitPrice;

    private boolean isDiscountinued;

    private Integer supplierId;

}
