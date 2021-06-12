package com.potemkin.i.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.potemkin.i.domain.entity.Order;

@Profile("!local")
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * FROM potemkin.orders where customer_id = ?1", nativeQuery = true)
    public List<Order> findByCustomerCustomerId(int customerId);
}
