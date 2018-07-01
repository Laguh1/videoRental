package com.joanadantas.resources;

import com.joanadantas.customer.Customer;
import com.joanadantas.customer.dao.CustomersLoader;
import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;
import com.joanadantas.service.CustomException;
import com.joanadantas.service.RentMovieService;
import com.joanadantas.service.ReturnMovieService;
import com.joanadantas.service.dto.CustomErrorMessageDTO;
import com.joanadantas.service.dto.CustomExceptionMessageDTO;
import com.joanadantas.service.dto.SuccesfullRentMessage;
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
            return Response.status(404).entity(new CustomExceptionMessageDTO(cEx.getMessage())).build();
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
        //Checking existance of movie and customer together for simplicity sake,
        // ideally should be separated, so the not found response would be specific
        if (customerResult != null && movieResult != null) {
            int amountDue;
            try {
                amountDue = returnMovieService.returnAMovie(customerResult, movieResult);
            }catch (CustomException cEx){
                return Response.status(404).entity(new CustomExceptionMessageDTO(cEx.getMessage())).build();
            }
            return Response.status(200).entity(new CustomErrorMessageDTO("Amount due is: "+amountDue)).build();
        } else {
            return Response.status(404).entity(new CustomErrorMessageDTO("Movie or Customer not found")).build();
        }
    }
}
