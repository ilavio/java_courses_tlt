package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainImpl {
    public static void main(String[] args) {
        log.info("MainImpl");
        CrudMyFirst crud = new CrudMyFirst();
        crud.addCustomer("MMM", "123456");
        crud.addCustomer("GGG", "123");
        crud.addCustomer("FFF", "56743");
        crud.closed();
    }
}
