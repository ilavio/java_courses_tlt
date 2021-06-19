package com.potemkin.i.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    
    @Query(value = "select * FROM potemkin.orders where customer_id = ?1", nativeQuery = true)
    public List<Order> findByCustomerCustomerId(int customerId);
}
