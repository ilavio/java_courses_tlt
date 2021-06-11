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

import org.json.JSONPropertyIgnore;

import lombok.Data;
import lombok.ToString;

/**
 * Сущность Supplier
 * 
 * @author Илья Пот
 *
 */
@Table(name = "Supplier", schema = "potemkin")
@Data
@Entity
@ToString(exclude = "products")
public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(nullable = false, name = "company_name")
    private String companyName;

    @Column
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
}
