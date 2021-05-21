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

import lombok.Data;

/**
 * POJO класс Supplier для создание сущности
 * 
 * @author Илья Пот
 */
@Entity
@Table(name = "Supplier", schema = "potemkin")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;
    
    @Column(nullable = false)
    private String companyName;
    
    @Column
    private String phone;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List <Product> products = new ArrayList<Product>();
}
