package com.joanadantas.resources;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.CustomException;
import com.joanadantas.service.RentMovieService;
import com.joanadantas.service.ReturnMovieService;
import com.joanadantas.service.messages.CustomErrorMessage;
import com.joanadantas.service.messages.CustomExceptionMessage;
import com.joanadantas.service.messages.SuccesfullRentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rental")
public class RentResource {

    private RentMovieService rentMovieService;
    private ReturnMovieService returnMovieService;

    @Autowired
    public RentResource(){
        this.rentMovieService = new RentMovieService();
        this.returnMovieService = new ReturnMovieService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rent/{customerId}")
    public Response rentMovie(@PathParam("customerId") String customerId,
                              @QueryParam("movieId") String movieId,
                              @QueryParam("daysRent") int numberOfDaysToRent) {
        SuccesfullRentMessage succesfullRentMessage;
        try {
            succesfullRentMessage = rentMovieService.rentAMovie(customerId, movieId, numberOfDaysToRent);
        } catch (CustomException cEx) {
            return Response.status(404).entity(new CustomExceptionMessage(cEx.getMessage())).build();
        }
        return Response.status(200).entity(succesfullRentMessage).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/return/{customerId}")
    public Response returnMovie(@PathParam("customerId") String customerId,
                             @QueryParam("movieId") String movieId) {
        Customer customerResult = CustomersLoader.getAllCustomersMap().get(customerId);
        Movie movieResult = MoviesCatalogueLoader.getAllMoviesMap().get(movieId);
        if (customerResult != null && movieResult != null) {
            int amountDue;
            try {
                amountDue = returnMovieService.returnAMovie(customerResult, movieResult);
            }catch (CustomException cEx){
                return Response.status(404).entity(new CustomExceptionMessage(cEx.getMessage())).build();
            }
            return Response.status(200).entity(new CustomErrorMessage("Amount due is: "+amountDue)).build();
        } else {
            return Response.status(404).entity(new CustomErrorMessage("Movie or Customer not found")).build();
        }
    }
}
