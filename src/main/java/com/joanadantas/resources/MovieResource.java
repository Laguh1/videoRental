package com.joanadantas.resources;

import com.joanadantas.movie.Movie;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Path("/movies")
public class MovieResource {

    @GET
    //@Produces({"application/json"})
    public Response getMovie() {
       /* JSONObject jsonObject = new JSONObject();
        Double fahrenheit = 98.24;
        Double celsius;
        celsius = (fahrenheit - 32)*5/9;
        jsonObject.put("F Value", fahrenheit);
        jsonObject.put("C Value", celsius);
        String result = "Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
      */  String result2 = "Produto";
        List<Movie> allMovies  = MoviesCatalogueLoader.getAllMovies();
        return Response.status(200).entity(allMovies).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response getMsg() {

        String output = "Jersey say : ";
        LocalDate someDate = LocalDate.of(2014, Month.JANUARY, 1);
        Movie test = new Movie("Teste", someDate, "2.20");

        System.out.println("ARrived here");
       // return test;
        return Response.status(200).entity(test).build();

    }

}
