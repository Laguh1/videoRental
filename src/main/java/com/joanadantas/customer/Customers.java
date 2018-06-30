package com.joanadantas.customer;

import com.joanadantas.customer.dao.CustomersLoader;
import java.util.List;

public class Customers {

    private List<Customer> customers;

    public Customers(){
        this.customers = CustomersLoader.getAllCustomersList();
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
