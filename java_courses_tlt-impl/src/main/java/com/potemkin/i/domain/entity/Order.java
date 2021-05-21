package com.potemkin.i.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders", schema = "potemkin")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column
    private String orderNumber;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
