package com.potemkin.i.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "orderproduct", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderId"), inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"))
    private List<Product> product = new ArrayList<Product>();
}
