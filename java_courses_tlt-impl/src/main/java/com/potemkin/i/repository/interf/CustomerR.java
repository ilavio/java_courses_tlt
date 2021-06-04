package com.potemkin.i.repository.interf;

import com.potemkin.i.domain.entity.Customer;

public interface CustomerR {

    public void addCustomer(Customer customer);

    public Customer getCustomer(int customerId);
    
    public void deleteCust(int customerId);
}
