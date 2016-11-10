package hu.dpc.edu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class RecursiveResource {

    private String path;

    public RecursiveResource(String path) {
        this.path = path;
    }

    @Path("{pathElement}")
    public RecursiveResource handlePath(@PathParam("pathElement")String pathElement) {
        return new RecursiveResource(path + " :: " + pathElement);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPath() {
        return path;
    }
}
