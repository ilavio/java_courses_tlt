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

import com.potemkin.i.domain.entity.Supplier;
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
    @InjectMocks
    private ControllerSupplierResources resources;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSupplier() {
        var json = new JSONObject();
        when(supplierService.getSupplierJson(0)).thenReturn(json);
        resources.getSupplier(0);
        verify(supplierService).getSupplierJson(eq(0));
    }

    @Test
    public void getSuppliersTest() {
        List<Supplier> list = new ArrayList<>();
        list.add(new Supplier());
        var jsonArraySup = new JSONArray(list);
        when(supplierService.getSuppliers()).thenReturn(jsonArraySup);
        var jsonArray = resources.getSuppliers();
        verify(supplierService).getSuppliers();
    }

    @Test
    public void addSupplierTest() {
        JSONObject json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        when(supplierService.addSupplier(any())).thenReturn(json);
        var str = resources
                .addSupplier("{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        log.info("addSupplierTest() - {}", str);
        verify(supplierService).addSupplier(any());
    }

    @Test
    public void changeSupplierTest() {
        var json = new JSONObject(
                "{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}");
        when(supplierService.changeEntity(any(), eq(0))).thenReturn(json);
        resources.changeSupplier("{\r\n" + "    \"companyName\" : \"LOCAL\",\r\n" + "    \"phone\" : \"888\"\r\n" + "}",
                0);
        verify(supplierService).changeEntity(any(), eq(0));
    }

    @Test
    public void deleteByIdTest() {
        var json = new JSONObject("{\"Found Supplier\":\" false \" }");
        when(supplierService.deleteById(0)).thenReturn(json);
        resources.deleteById(0);
        verify(supplierService).deleteById(eq(0));
    }
}
