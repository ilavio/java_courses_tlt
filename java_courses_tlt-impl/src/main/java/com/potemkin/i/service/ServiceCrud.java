package com.potemkin.i.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.interf.CustomerR;
import com.potemkin.i.repository.interf.OrderR;
import com.potemkin.i.repository.interf.ProductR;
import com.potemkin.i.repository.interf.SupplierR;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component()
public class ServiceCrud {
    @Autowired
    CustomerR repoCust;
    @Autowired
    OrderR repoOrd;
    @Autowired
    SupplierR repoSup;
    @Autowired
    ProductR repoProd;
    
    public void start(AnnotationConfigApplicationContext context) {
        log.info("MainImpl");

        log.info("Добавление Customer");
        var cust = new Customer();
        cust.setCustomerName("Maks-1");
        cust.setPhone("78451256");
        repoCust.addCustomer(cust);

        log.info("Добавление Order");
        try {
            var reader = Files.newBufferedReader(Paths.get("src/main/resources/order.json"));
            var strbuf = new StringBuffer();
            while(reader.ready()) {
                strbuf.append(reader.readLine());
                strbuf.append("\n");
            }
            log.info(strbuf.toString());
            JSONObject json = new JSONObject(strbuf.toString());
            int id = json.getInt("customerId");
            repoOrd.addOrder(repoOrd.parseForOrder(json), id);
            reader.close();
        } catch (IOException e) {
            log.error("MainImpl main() {}", e);
        }

        log.info("Добавление Supplier");
        try {
            var reader = Files.newBufferedReader(Paths.get("src/main/resources/supplier.json"));
            var strbuf = new StringBuffer();
            while(reader.ready()) {
                strbuf.append(reader.readLine());
                strbuf.append("\n");
            }
            log.info(strbuf.toString());
            JSONObject json = new JSONObject(strbuf.toString());
            repoSup.addSupplier(repoSup.parseForSupplier(json));
            reader.close();
        } catch (IOException e) {
            log.error("MainImpl main() {}", e);
        }
        
        log.info("Добавление Product");
        try {
            var reader = Files.newBufferedReader(Paths.get("src/main/resources/product.json"));
            var strbuf = new StringBuffer();
            while(reader.ready()) {
                strbuf.append(reader.readLine());
                strbuf.append("\n");
            }
            log.info(strbuf.toString());
            JSONObject json = new JSONObject(strbuf.toString());
            int id = json.getInt("supplierId");
            repoProd.addProduct(repoProd.parseForProduct(json), id);
            reader.close();
        } catch (IOException e) {
            log.error("MainImpl main() {}", e);
        }
    }
}
