package com.potemkin.i.repository.interf;

import com.potemkin.i.domain.entity.Customer;

/**
 * Интерфес CustomerRepository
 * 
 * @author Илья Пот
 *
 */
public interface CustomerRepository {

    public Customer addCustomer(Customer customer);

    public Customer getCustomer(int customerId);
    
    public boolean deleteCust(int customerId);
}
