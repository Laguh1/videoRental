package com.joanadantas.resources;

import com.joanadantas.movie.Movie;
import com.joanadantas.movie.Movies;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {
        Movies movies = new Movies();
        return Response.status(200).entity(movies).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/movie/{movieId}")
    public Response getMovie(@PathParam("movieId") String movieId) {

        Movie movieResult = MoviesCatalogueLoader.getAllMoviesMap().get(movieId);
        if (movieResult!=null){
            return Response.status(200).entity(movieResult).build();

        }else{
            return Response.status(404).build();
        }
    }
}
