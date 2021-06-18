package com.potemkin.i.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.potemkin.i.dto.SupplierDTO;

/**
 * Интрефейс SupplierResource для операций Rest с сущностью Supplier
 * 
 * @author Илья Пот
 *
 */
@RequestMapping("/Supplier")
public interface SupplierResource {

    /**
     * Метод добавления Supplier
     * 
     * @param dto
     * @return SupplierDTO
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplierDTO addSupplier(SupplierDTO dto);

    /**
     * Метод получения Supplier по id
     * 
     * @param id
     * @return SupplierDTO
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplierDTO getSupplier(int id);

    /**
     * Метод получения списка Supplier
     * 
     * @return SupplierDTO
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplierDTO> getSuppliers();

    /**
     * Метод изменения по id Supplier
     * 
     * @param dto
     * @param id
     * @return SupplierDTO
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplierDTO changeSupplier(SupplierDTO dto, int id);

    /**
     * Метод удаления Supplier по id
     * 
     * @param id
     * @return String
     */
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(int id);
}
