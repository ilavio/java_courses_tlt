package com.potemkin.i.repository.stub;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс CustomerRepositoryStub с зафиксированными значениями
 * 
 * @author Илья Пот
 *
 */
@Profile("local")
@Slf4j
@Component("customerRepository")
public class CustomerRepositoryStub implements CustomerRepository {

    private Customer cust;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return Optional.of(cust);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        cust.setCustomerId(0);
        cust.setCustomerName("");
        cust.setPhone("");
        log.info("Удаление Customer: {}", cust);
    }

    @Override
    public void delete(Customer entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(cust);
        return customers;
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Customer> findAllById(Iterable<Integer> ids) {

        return null;
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {

        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Customer> S saveAndFlush(S entity) {
        cust = new Customer();
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        log.info("Добавление Customer: {}", cust);
        entity = (S) cust;
        return entity;
    }

    @Override
    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {

        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Customer> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Customer getOne(Integer id) {

        return null;
    }

    @Override
    public Customer getById(Integer id) {

        return null;
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {

        return null;
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {

        return null;
    }
}
