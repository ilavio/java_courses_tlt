package com.potemkin.i.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customers", schema = "potemkin")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(nullable = false)
    private String customerName;

    @Column
    private String phone;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List <Order> orders = new ArrayList<Order>();
}
