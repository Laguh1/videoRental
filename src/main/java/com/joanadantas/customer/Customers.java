package com.joanadantas.customer;

import com.joanadantas.customer.dao.CustomersLoader;
import java.util.List;

public final class Customers {

    private static Customers customers = null;
    private final List<Customer> customersList;

    private Customers(){
        this.customersList = CustomersLoader.getAllCustomersList();
    }

    public static Customers getInstance(){
        if(customers!=null){
            return customers;
        }else{
            return new Customers();
        }
    }

    public List<Customer> getCustomers() {
        return customersList;
    }
}
