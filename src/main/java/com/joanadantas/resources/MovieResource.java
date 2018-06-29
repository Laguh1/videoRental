package com.joanadantas.resources;

import com.joanadantas.movie.Movie;
import com.joanadantas.movie.Movies;
import com.joanadantas.movie.dao.MoviesCatalogueLoader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @Path("/movie")
    public Response getMovie() {

        Movie movieResult = MoviesCatalogueLoader.getAllMoviesMap().get("001");
        return Response.status(200).entity(movieResult).build();
    }
}
