package com.potemkin.i.repository.hard;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.interf.CustomerR;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component()
public class CustRepHard implements CustomerR {
    private Customer cust;

    public void addCustomer(Customer customer) {
        cust = new Customer();
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        log.info("Добавление Customer: {}", cust);
    }

    public Customer getCustomer(int customerId) {
        log.info("Получение Customer: ", cust);
        return cust;
    }

    public void deleteCust(int customerId) {
        cust.setCustomerId(0);
        cust.setCustomerName("");
        cust.setPhone("");
        log.info("Удаление Customer: {}", cust);
    }
}
