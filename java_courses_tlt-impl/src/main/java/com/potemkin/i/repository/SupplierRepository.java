package com.potemkin.i.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Supplier;

@Profile("!local")
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
