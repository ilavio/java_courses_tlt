package com.potemkin.i.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Сущность Product
 * 
 * @author Илья Пот
 *
 */
@Table(name = "Product", schema = "potemkin")
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)", name = "unit_price")
    private double unitPrice;

    @Column(nullable = false, name = "is_discontinued")
    private boolean isDiscontinued;

//    @Override
//    public String toString() {
//        return "Product [productId=" + productId + ", productName=" + productName + ", unitPrice=" + unitPrice
//                + ", isDiscontinued=" + isDiscontinued + ", = supplier" + supplier + "]";
//    }
}
