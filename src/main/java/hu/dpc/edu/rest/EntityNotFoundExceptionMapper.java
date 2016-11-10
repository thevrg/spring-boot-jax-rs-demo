package hu.dpc.edu.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by vrg on 2016. 11. 10..
 */
@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(
                        new Message(Response.Status.NOT_FOUND.getStatusCode(),
                                "not found",
                                exception.getMessage()))
                .build();
    }
}
