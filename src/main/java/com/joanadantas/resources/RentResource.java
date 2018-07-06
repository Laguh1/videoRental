package com.joanadantas.resources;

import com.joanadantas.service.*;
import com.joanadantas.service.messages.*;
import com.joanadantas.service.rental.RentMovieService;
import com.joanadantas.service.rental.RentService;
import com.joanadantas.service.rental.ReturnMovieService;
import com.joanadantas.service.rental.ReturnService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rental")
public final class RentResource {

    private RentService rentMovieService;
    private ReturnService returnMovieService;

    public RentResource(){
        this.rentMovieService = RentMovieService.getInstance();
        this.returnMovieService = ReturnMovieService.getInstance();
    }

    @POST
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rent/{customerId}")
    public Response rentMovies(@PathParam("customerId") String customerId,
                               List<RentMoviesRequest> movieIdsRequested) {
        MultipleOperationMessage multipleOperationMessage = new MultipleOperationMessage();
        SuccessfulRentMessage successfulRentMessage;
        for (RentMoviesRequest rentMoviesRequest : movieIdsRequested) {
            try {
                successfulRentMessage = rentMovieService.rentAMovie(customerId, rentMoviesRequest.getMovieId(), rentMoviesRequest.getNumberOfDays());
                multipleOperationMessage.insertMessageInOperationList(successfulRentMessage);

            } catch (CustomException cEx) {
                multipleOperationMessage.insertMessageInOperationList(new CustomExceptionMessage(cEx.getMessage()));
            }
        }
        return Response.status(200).entity(multipleOperationMessage).build();
    }

    @POST
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/return/{customerId}")
    public Response returnMovies(@PathParam("customerId") String customerId,
                               List<RentMoviesRequest> movieIdsRequested) {
        MultipleOperationMessage multipleOperationMessage = new MultipleOperationMessage();
        SuccessfulReturnMessage successfulReturnMessage;
        for (RentMoviesRequest rentMoviesRequest : movieIdsRequested) {
            try {
                successfulReturnMessage = returnMovieService.returnAMovie(customerId, rentMoviesRequest.getMovieId());
                multipleOperationMessage.insertMessageInOperationList(successfulReturnMessage);

            } catch (CustomException cEx) {
                multipleOperationMessage.insertMessageInOperationList(new CustomExceptionMessage(cEx.getMessage()));
            }
        }
        return Response.status(200).entity(multipleOperationMessage).build();
    }
}
