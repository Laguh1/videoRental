package com.joanadantas.customer.dao;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joanadantas.customer.Customer;
import com.joanadantas.customer.Customers;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.Movies;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class CustomersLoader {

    //Creates a cached list of films for using the app simulates database as json file
    private static List<Customer> allCustomersList;
    private static HashMap<String, Customer> allCustomersMap;

    public static List<Customer> getAllCustomersList(){

        if (allCustomersList != null){
            return allCustomersList;
        }else{
            return new CustomersLoader().getCustomersFromJson();
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

    private List<Customer> getCustomersFromJson(){
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(classLoader.getResource("customers.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Customer>> mapType = new TypeReference<List<Customer>>() {};
        List<Customer> customersList = null;
        try{
            customersList = objectMapper.readValue(jsonFile, mapType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return customersList;
    }
}
