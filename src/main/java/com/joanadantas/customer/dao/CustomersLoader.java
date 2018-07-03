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

    //Creates a cached list of films for testing the app
    private static List<Customer> allCustomersList;
    private static HashMap<String, Customer> allCustomersMap;

    public static List<Customer> getAllCustomersList(){

        if (allCustomersList != null){
            return allCustomersList;
        }else{
       //     allCustomersList = new CustomersLoader().getCustomersFromJson();
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
/*
    ObjectMapper mapper = new ObjectMapper();

    private List<Customer> getCustomersFromJson() {

        List<Customer> listofCustomers = null;


      //  Customers customers.json = null;
        try {
            // Convert JSON string from file to Object
      //      customers.json = mapper.readValue(new File("customer"), Movies.class);



            listofCustomers = mapper.readValue(jsonCarArray, new TypeReference<List<Customer>>(){});

            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(classLoader.getResource("customers.json").getFile());
            Customer[] arrayOfCustomer = mapper.readValue(jsonFile, Arrays.class);
            listofCustomers = Arrays.asList(mapper.readValue(jsonFile, Arrays.class));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listofCustomers;
    }*/

}
