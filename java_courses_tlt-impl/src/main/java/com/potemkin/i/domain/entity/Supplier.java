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

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Profile("!local")
@Component("supplier")
@Scope("prototype")
@Table(name = "Supplier", schema = "potemkin")
@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;
    
    @Column(nullable = false, name = "company_name")
    private String companyName;
    
    @Column
    private String phone;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "supplier", fetch = FetchType.EAGER)
    private List <Product> products = new ArrayList<>();
    
    public String getProducts() {
        return products.toString();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Supplier [supplierId=" + supplierId + ", companyName=" + companyName + ", phone=" + phone + "]";
    }
}
