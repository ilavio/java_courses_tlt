package com.potemkin.i.repository.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Product;
import com.potemkin.i.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс ProductRepositoryStub с зафиксированными значениями
 * 
 * @author Илья Пот
 *
 */
@Profile("local")
@Slf4j
@Component("productRepository")
public class ProductRepositoryStub implements ProductRepository {
    private Product prod = new Product();

    @Override
    public List<Product> findAll() {
        List<Product> prods = new ArrayList<>();
        prods.add(prod);
        return prods;
    }

    @Override
    public List<Product> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {//
        prod.setDiscontinued(true);
        prod.setProductId(0);
        prod.setProductName("LOCAL");
        prod.setUnitPrice(10.01);
        log.info("Добавление Product: {}", prod);
        entity = (S) prod;
        return entity;
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
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
    public Product getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.of(prod);
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        prod.setDiscontinued(true);
        prod.setProductId(0);
        prod.setProductName("");
        prod.setUnitPrice(0);
        log.info("Удаление Product: {}", prod);
    }

    @Override
    public void delete(Product entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }
}
