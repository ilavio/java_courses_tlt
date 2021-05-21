package com.potemkin.i;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainImpl {
    public static void main(String[] args) {
        log.info("Пуск соединения с базой данных");
        CrudMyFirst crud = new CrudMyFirst();
        log.info("Добавляем Customer:");
        crud.addCustomer("MMM", "123456");
        crud.addCustomer("GGG", "123");
        crud.addCustomer("FFF", "56743");
        Date date = new Date();
        log.info("Добавляем Order:");
        crud.addOrder("MMM", "111", date , 12.02);
        crud.addOrder("MMM", "112", date , 12.03);
        log.info("Добавляем Supplier:");
        crud.addSupplier("ABIBAS", "232232");
        crud.addSupplier("NIKE", "454545");
        log.info("Добавляем Product:");
        crud.addProduct("ABIBAS", "sneakers", 2.3, false);
        log.info("Объединяем Order и Product:");
        crud.mergeOrderProduct(crud.getOrder("112"), crud.getProduct("sneakers"));
        crud.closed();
    }
}
