package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;
import com.potemkin.i.resource.impl.SupplierResourceImpl;
import com.potemkin.i.service.impl.SupplierServiceImpl;

/**
 *  Класс тестирования SupplierResourceImpl
 * 
 * @author Илья Пот
 *
 */
public class SupplierResourceImplTest {

    @Mock
    private SupplierServiceImpl supplierService;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private SupplierResourceImpl resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSupplier() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        when(conversionService.convert(any(), eq(Supplier.class))).thenReturn(sup);
        when(conversionService.convert(sup, SupplierDTO.class)).thenReturn(supDTO);
        when(supplierService.getSupplier(0)).thenReturn(sup);
        resources.getSupplier(0);
        verify(supplierService).getSupplier(eq(0));
    }

    @Test
    public void getSuppliersTest() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        List<Supplier> list = new ArrayList<>();
        list.add(new Supplier());
        List<SupplierDTO> listDTO = new ArrayList<>();
        listDTO.add(new SupplierDTO());
        when(conversionService.convert(sup, SupplierDTO.class)).thenReturn(supDTO);
        when(supplierService.getSuppliers()).thenReturn(list);
        resources.getSuppliers();
        verify(supplierService).getSuppliers();
    }

    @Test
    public void addSupplierTest() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        when(conversionService.convert(any(), eq(Supplier.class))).thenReturn(sup);
        when(conversionService.convert(sup, SupplierDTO.class)).thenReturn(supDTO);
        when(supplierService.addSupplier(sup)).thenReturn(sup);
        resources.addSupplier(supDTO);
        verify(supplierService).addSupplier(sup);
    }

    @Test
    public void changeSupplierTest() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        when(conversionService.convert(any(), eq(Supplier.class))).thenReturn(sup);
        when(conversionService.convert(sup, SupplierDTO.class)).thenReturn(supDTO);
        when(supplierService.changeEntity(any(), eq(0))).thenReturn(sup);
        resources.changeSupplier(supDTO, 0);
        verify(supplierService).changeEntity(any(), eq(0));
    }
    
    @Test
    public void deleteByIdTest() {
        when(supplierService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(supplierService).deleteById(eq(0));
    }
}
