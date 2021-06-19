package com.potemkin.i;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potemkin.i.converter.SupplierDtoToSupplier;
import com.potemkin.i.converter.SupplierToSupplierDTO;
import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.dto.SupplierDTO;
import com.potemkin.i.resource.impl.CustomerResourceImpl;
import com.potemkin.i.resource.impl.SupplierResourceImpl;
import com.potemkin.i.service.impl.SupplierServiceImpl;

/**
 * Класс тестирования SupplierResourceImpl
 * 
 * @author Илья Пот
 *
 */
@WebMvcTest(CustomerResourceImpl.class)
@ContextConfiguration(classes = { SupplierServiceImpl.class, SupplierResourceImpl.class, SupplierDtoToSupplier.class,
        SupplierToSupplierDTO.class })
public class SupplierResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierServiceImpl supplierService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addSupplierTest() throws Exception {
        var sup = createSup();
        var dto = createDTO();
        when(supplierService.addSupplier(any())).thenReturn(sup);
        mockMvc.perform(MockMvcRequestBuilders.post("/Supplier").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getSupplierTest() throws Exception {
        var sup = createSup();
        when(supplierService.getSupplier(1)).thenReturn(sup);
        mockMvc.perform(MockMvcRequestBuilders.get("/Supplier/1")).andExpect(status().isOk());
    }

    @Test
    public void getSuppliersTets() throws Exception {
        var sup = createSup();
        List<Supplier> list = new ArrayList<>();
        list.add(sup);
        when(supplierService.getSuppliers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/Supplier")).andExpect(status().isOk());
    }

    @Test
    public void changeSupplierTest() throws Exception {
        var sup = createSup();
        var dto = createDTO();
        when(supplierService.changeEntity(sup, 1)).thenReturn(sup);
        mockMvc.perform(MockMvcRequestBuilders.put("/Supplier").accept(MediaType.APPLICATION_JSON).param("id", "1")
                .content(objectMapper.writeValueAsString(dto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        when(supplierService.deleteById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/Supplier").param("id", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public Supplier createSup() {
        var sup = new Supplier();
        sup.setCompanyName("Y");
        sup.setPhone("8");
        sup.setSupplierId(1);
        return sup;
    }

    public SupplierDTO createDTO() {
        var dto = new SupplierDTO();
        dto.setCompanyName("Y");
        dto.setPhone("8");
        dto.setSupplierId(1);
        return dto;
    }
}
