package com.joanadantas.customer.dao;

import com.joanadantas.customer.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomersLoader {

    //Creates a cached list of films for testing the app
    private static List<Customer> allCustomersList;
    private static HashMap<String, Customer> allCustomersMap;


    public static List<Customer> getAllCustomersList(){
        if (allCustomersList != null){
            return allCustomersList;
        }else{
            allCustomersList = new ArrayList<>();
            Customer customer1 = new Customer("001","Mary");
            Customer customer2 = new Customer("002","Joe");
            allCustomersList.addAll(Arrays.asList(customer1, customer2));
            return allCustomersList;
        }
    }

    public static HashMap<String, Customer> getAllCustomersMap(){
        if (allCustomersMap != null){
            return allCustomersMap;
        }else{
            allCustomersMap = new HashMap<>();
            getAllCustomersList().forEach(customer -> allCustomersMap.put(customer.getId(),customer));
            return allCustomersMap;
        }
    }
}
