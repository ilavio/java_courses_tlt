package com.potemkin.i.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
