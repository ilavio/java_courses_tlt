package com.potemkin.i.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.CrudHandler;
import com.potemkin.i.CrudHandlerSupAndProduct;
import com.potemkin.i.domain.entity.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("servicecrud")
@Profile("!local")
public class ServiceCrud implements ServiceInt {
    
    @Override
    public void start(AnnotationConfigApplicationContext context) {
        var crudCustAndOrd = context.getBean("crudhandler", CrudHandler.class);
        var crudSupAndProd = context.getBean("crudHandlerSupAndProduct", CrudHandlerSupAndProduct.class);
        log.info("MainImpl");

        log.info("Добавление Customer");
        var cust = context.getBean("customer", Customer.class);
        crudCustAndOrd.addEntity(cust);

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
            crudCustAndOrd.addEntity(crudCustAndOrd.parseForOrder(json), id);
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
            crudSupAndProd.addEntity(crudSupAndProd.parseForSupplier(json));
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
            crudSupAndProd.addEntity(crudSupAndProd.parseForProduct(json), id);
            reader.close();
        } catch (IOException e) {
            log.error("MainImpl main() {}", e);
        }
    }
}
