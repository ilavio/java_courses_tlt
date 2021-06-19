package com.potemkin.i.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Сущность Customer
 * 
 * @author Илья Пот
 *
 */
@Entity
@Table(name = "Customers")
@Data
@ToString(exclude = "orders")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(nullable = false, name = "customer_name")
    private String customerName;

    @Column
    private String phone;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
