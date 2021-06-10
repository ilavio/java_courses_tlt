package com.potemkin.i.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Product;

@Profile("!local")
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
