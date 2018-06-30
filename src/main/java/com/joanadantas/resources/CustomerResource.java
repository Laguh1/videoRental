package com.joanadantas.resources;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.Customers;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.Movies;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customers")
public class CustomerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        Customers customers = new Customers();
        return Response.status(200).entity(customers).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer/{customerId}")
    public Response getCustomer(@PathParam("customerId") String customerId) {

        Customer customerResult = CustomersLoader.getAllCustomersMap().get(customerId);
        if (customerResult!=null){
            return Response.status(200).entity(customerResult).build();

        }else{
            return Response.status(404).build();
        }
    }
}
