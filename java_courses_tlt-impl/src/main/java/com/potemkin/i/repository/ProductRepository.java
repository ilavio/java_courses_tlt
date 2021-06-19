package com.potemkin.i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
