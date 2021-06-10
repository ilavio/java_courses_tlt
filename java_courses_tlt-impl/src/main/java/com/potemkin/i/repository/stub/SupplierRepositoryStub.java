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

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс SupplierRepositoryStub с зафиксированными значениями
 * 
 * @author Илья Пот
 *
 */
@Profile("local")
@Slf4j
@Component("supplierRepository")
public class SupplierRepositoryStub implements SupplierRepository {
    private Supplier sup = new Supplier();

    @Override
    public List<Supplier> findAll() {
        List<Supplier> sups = new ArrayList<>();
        sups.add(sup);
        return sups;
    }

    @Override
    public List<Supplier> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Supplier> findAllById(Iterable<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Supplier> S saveAndFlush(S entity) {
        sup.setCompanyName("LOCAL");
        sup.setPhone("888");
        sup.setSupplierId(0);
        log.info("Добавление Supplier: {}", sup);
        entity = (S) sup;
        return entity;
    }

    @Override
    public <S extends Supplier> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Supplier> entities) {
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
    public Supplier getOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Supplier getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return Optional.of(sup);
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
        sup.setCompanyName("");
        sup.setPhone("");
        sup.setSupplierId(0);
        log.info("Удаление Supplier: {}", sup);
    }

    @Override
    public void delete(Supplier entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Supplier> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Supplier> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Supplier> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Supplier> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }
}
