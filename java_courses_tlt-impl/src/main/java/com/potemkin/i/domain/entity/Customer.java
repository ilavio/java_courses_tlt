package com.potemkin.i.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * POJO класс Customer для создание сущности
 * 
 * @author Илья Пот
 */
@Entity
@Table(name = "Customers", schema = "potemkin")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(nullable = false)
    private String customerName;

    @Column
    private String phone;
    
    @JoinColumn(name = "orderId")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Order order;
}
