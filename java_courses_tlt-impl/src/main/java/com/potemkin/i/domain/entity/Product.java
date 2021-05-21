package com.potemkin.i.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * POJO класс Product для создание сущности
 * 
 * @author Илья Пот
 */
@Entity
@Table(name = "Product", schema = "potemkin")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    @Column(nullable = false)
    private String productName;
    
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
    
    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private double unitPrice;
    
    @Column(nullable = false)
    private boolean isDiscontinued;
    
    @ManyToMany(mappedBy = "product")
    private List<Order> order = new ArrayList<Order>();
}
