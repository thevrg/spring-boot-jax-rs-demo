package hu.dpc.edu.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by vrg on 2016. 11. 10..
 */
public abstract class RepositoryException  extends WebApplicationException {
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(Response response) {
        super(response);
    }

    public RepositoryException(String message, Response response) {
        super(message, response);
    }

    public RepositoryException(int status) {
        super(status);
    }

    public RepositoryException(String message, int status) {
        super(message, status);
    }

    public RepositoryException(Response.Status status) {
        super(status);
    }

    public RepositoryException(String message, Response.Status status) {
        super(message, status);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause, Response response) {
        super(cause, response);
    }

    public RepositoryException(String message, Throwable cause, Response response) {
        super(message, cause, response);
    }

    public RepositoryException(Throwable cause, int status) {
        super(cause, status);
    }

    public RepositoryException(String message, Throwable cause, int status) {
        super(message, cause, status);
    }

    public RepositoryException(Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(cause, status);
    }

    public RepositoryException(String message, Throwable cause, Response.Status status) throws IllegalArgumentException {
        super(message, cause, status);
    }
}
