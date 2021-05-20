package com.potemkin.i.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * POJO класс Order для создание сущности
 * 
 * @author Илья Пот
 */
@Entity
@Table(name = "Orders", schema = "potemkin")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column
    private String orderNumber;

    @Column
    private int customerId;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private double totalAmount;
    
    @OneToOne(optional = false, mappedBy="order")
    private Customer customer;
}
