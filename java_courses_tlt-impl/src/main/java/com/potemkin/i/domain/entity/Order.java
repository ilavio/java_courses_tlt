package com.potemkin.i.domain.entity;

import java.text.SimpleDateFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONPropertyIgnore;

import lombok.Data;

/**
 * Сущность Order
 * 
 * @author Илья Пот
 *
 */
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


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"), 
    inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    private List<Product> product = new ArrayList<>();

    @Override
    public String toString() {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateformate.format(orderDate);
        return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderDate=" + strDate
                + ", totalAmount=" + totalAmount + "]";
    }

    public String getOrderDate() {
        SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateformate.format(orderDate);
        return strDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProduct() {
        return product;
    }
  
    public List<Product> addProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @JSONPropertyIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
