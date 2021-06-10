package com.potemkin.i.repository.stub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.domain.entity.Order;
import com.potemkin.i.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс OrderRepositoryStub с зафиксированными значениями
 * 
 * @author Илья Пот
 *
 */
@Profile("local")
@Slf4j
@Component("orderRepository")
public class OrderRepositoryStub implements OrderRepository {
    private Order orderH = new Order();
    private Date date = new Date();
    private Customer cust = new Customer();

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Order> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Order> S saveAndFlush(S entity) {
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        orderH.setOrderDate(date);
        orderH.setCustomer(cust);
        orderH.setOrderId(0);
        orderH.setOrderNumber("888");
        orderH.setTotalAmount(10.01);
        log.info("Добавление Order: {}", orderH);
        entity = (S) orderH;
        return entity;
    }

    @Override
    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Order> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public Order getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return Optional.of(orderH);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        orderH.setOrderDate(date);
        orderH.setCustomer(cust);
        orderH.setOrderId(0);
        orderH.setOrderNumber("");
        orderH.setTotalAmount(0);
        log.info("Удаление Order: {}", orderH);
    }

    @Override
    public void delete(Order entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Order> findByCustomerCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        orders.add(orderH);
        return orders;
    }
}
