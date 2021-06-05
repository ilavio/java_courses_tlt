package com.potemkin.i.repository.hard;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.potemkin.i.domain.entity.Customer;
import com.potemkin.i.repository.interf.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Slf4j
@Component()
public class CustomerRepositoryHard implements CustomerRepository {
    private Customer cust;

    public Customer addCustomer(Customer customer) {
        cust = new Customer();
        cust.setCustomerId(0);
        cust.setCustomerName("Maks");
        cust.setPhone("888");
        log.info("Добавление Customer: {}", cust);
        return cust;
    }

    public Customer getCustomer(int customerId) {
        log.info("Получение Customer: ", cust);
        return cust;
    }

    public boolean deleteCust(int customerId) {
        cust.setCustomerId(0);
        cust.setCustomerName("");
        cust.setPhone("");
        log.info("Удаление Customer: {}", cust);
        return true;
    }
}
