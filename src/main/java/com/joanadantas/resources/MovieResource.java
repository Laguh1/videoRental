package com.joanadantas.resources;

import org.json.JSONObject;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//@Produces(MediaType.APPLICATION_JSON)
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
        return Response.status(200).entity(result2).build();
    }

    @GET
    @Path("/test")
    public Response getMsg() {

        String output = "Jersey say : ";

        return Response.status(200).entity(output).build();

    }

}
