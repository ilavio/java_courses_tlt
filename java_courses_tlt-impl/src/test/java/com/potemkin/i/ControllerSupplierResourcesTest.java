package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.potemkin.i.converter.SupplierConverter;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;
import com.potemkin.i.resource.impl.ControllerSupplierResources;
import com.potemkin.i.service.impl.SupplierService;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс тестирования ControllerSupplierResources
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class ControllerSupplierResourcesTest {
    
    @Mock
    private SupplierService supplierService;
    @Mock
    private SupplierConverter supplierConverter;
    @InjectMocks
    private ControllerSupplierResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSupplier() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        when(supplierService.getSupplier(0)).thenReturn(sup);
        when(supplierConverter.supplierToDTO(sup)).thenReturn(supDTO);
        resources.getSupplier(0);
        verify(supplierService).getSupplier(eq(0));
    }

    @Test
    public void getSuppliersTest() {
        List<Supplier> list = new ArrayList<>();
        list.add(new Supplier());
        List<SupplierDTO> listDTO = new ArrayList<>();
        listDTO.add(new SupplierDTO());
        when(supplierService.getSuppliers()).thenReturn(list);
        when(supplierConverter.supplierToDTO(list)).thenReturn(listDTO);
        resources.getSuppliers();
        verify(supplierService).getSuppliers();
    }

    @Test
    public void addSupplierTest() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        when(supplierService.addSupplier(any())).thenReturn(sup);
        when(supplierConverter.supplierToDTO(sup)).thenReturn(supDTO);
        var str = resources
                .addSupplier("{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        log.info("addSupplierTest() - {}", str);
        verify(supplierService).addSupplier(any());
    }

    @Test
    public void changeSupplierTest() {
        var sup = new Supplier();
        var supDTO = new SupplierDTO();
        when(supplierService.changeEntity(any(), eq(0))).thenReturn(sup);
        when(supplierConverter.supplierToDTO(sup)).thenReturn(supDTO);
        resources.changeSupplier("{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}",
                0);
        verify(supplierService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        var json = new JSONObject("{\"Delete Supplier\":\" true \" }");
        when(supplierService.deleteById(0)).thenReturn(true);
        resources.deleteById(0);
        verify(supplierService).deleteById(eq(0));
    }
}
