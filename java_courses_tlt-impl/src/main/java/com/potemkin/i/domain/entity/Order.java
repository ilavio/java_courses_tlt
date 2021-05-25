package com.potemkin.i.domain.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "Orders", schema = "potemkin")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(nullable = false, name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(nullable = false, columnDefinition = "NUMERIC(12,2)", name = "total_amount")
    private double totalAmount;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    public String toString() {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateformate.format(orderDate);
        return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderDate=" + strDate
                + ", totalAmount=" + totalAmount + ", customer=" + customer + "]";
    }

    public String getOrderDate() {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateformate.format(orderDate);
        return strDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
