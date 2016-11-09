package hu.dpc.edu.rest;

import hu.dpc.edu.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by vrg on 2016. 11. 09..
 */
@RestResource
@Path("message")
public class MessageResource {

    private String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return message;
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void setMessage(String message) {
        this.message = message;
    }
}
