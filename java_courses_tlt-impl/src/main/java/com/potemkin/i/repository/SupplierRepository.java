package com.potemkin.i.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}
