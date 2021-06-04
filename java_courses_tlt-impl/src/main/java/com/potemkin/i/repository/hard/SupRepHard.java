package com.potemkin.i.repository.hard;

import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Supplier;
import com.potemkin.i.repository.interf.SupplierR;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component()
public class SupRepHard implements SupplierR {
    private Supplier sup = new Supplier();
    
 
    public void addSupplier(Supplier supplier) {
        sup.setCompanyName("LOCAL");
        sup.setPhone("888");
        sup.setSupplierId(0);
        log.info("Добавление Supplier: {}", sup);
    }


    public Supplier getSupplier(int supplierId) {
        log.info("Получение Supplier: ", sup);
        return sup;
    }

    public void deleteSupplier(int supplierId) {
        sup.setCompanyName("");
        sup.setPhone("");
        sup.setSupplierId(0);
        log.info("Удаление Supplier: {}", sup);
    }
    
    public Supplier parseForSupplier(JSONObject json) {
        return sup;
    }

}
