package hu.dpc.edu.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by vrg on 2016. 11. 10..
 */
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return Response.status(422)
                .entity(
                        new Message(422,
                                "unprocessable entity",
                                exception.getMessage()))
                .build();
    }
}
