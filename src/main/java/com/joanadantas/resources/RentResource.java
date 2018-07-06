package com.joanadantas.resources;

import com.joanadantas.service.*;
import com.joanadantas.service.messages.CustomExceptionMessage;
import com.joanadantas.service.messages.SuccessfulRentMessage;
import com.joanadantas.service.messages.SuccessfulReturnMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rental")
public class RentResource {

    private RentService rentMovieService;
    private ReturnService returnMovieService;

    public RentResource(){
        this.rentMovieService = RentMovieService.getInstance();
        this.returnMovieService = ReturnMovieService.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rent/{customerId}")
    public Response rentMovie(@PathParam("customerId") String customerId,
                              @QueryParam("movieId") String movieId,
                              @QueryParam("daysRent") int numberOfDaysToRent) {
        SuccessfulRentMessage successfulRentMessage;
        try {
            successfulRentMessage = rentMovieService.rentAMovie(customerId, movieId, numberOfDaysToRent);
        } catch (CustomException cEx) {
            return Response.status(404).entity(new CustomExceptionMessage(cEx.getMessage())).build();
        }
        return Response.status(200).entity(successfulRentMessage).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/return/{customerId}")
    public Response returnMovie(@PathParam("customerId") String customerId,
                                @QueryParam("movieId") String movieId) {

        SuccessfulReturnMessage successfulReturnMessage;
        try {
            successfulReturnMessage = returnMovieService.returnAMovie(customerId, movieId);
        } catch (CustomException cEx) {
            return Response.status(404).entity(new CustomExceptionMessage(cEx.getMessage())).build();
        }
        return Response.status(200).entity(successfulReturnMessage).build();
    }
}
