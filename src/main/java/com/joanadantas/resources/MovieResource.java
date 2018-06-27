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

@Path("/movies")
public class MovieResource {

    @GET
    //@Produces({"application/json"})
    public Response getAllMovies() {
       /* JSONObject jsonObject = new JSONObject();
        Double fahrenheit = 98.24;
        Double celsius;
        celsius = (fahrenheit - 32)*5/9;
        jsonObject.put("F Value", fahrenheit);
        jsonObject.put("C Value", celsius);
        String result = "Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
      */  String result2 = "Produto";
        return Response.status(200).entity(result2).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Movie getMovie() {

        Movie movieResult = MoviesCatalogueLoader.getAllMoviesMap().get("001");
        return movieResult;
        //return Response.status(200).entity(movieResult).build();
        //return Response.status(200).entity(test).build();

    }

}
