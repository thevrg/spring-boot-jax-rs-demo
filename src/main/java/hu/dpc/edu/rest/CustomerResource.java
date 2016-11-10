package hu.dpc.edu.rest;

import hu.dpc.edu.Customer;
import hu.dpc.edu.CustomerREST;
import hu.dpc.edu.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilderFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by vrg on 2016. 11. 10..
 */
@Component
@Scope("prototype")
public class CustomerResource {

    private long customerId;
    private CustomerRepository repository;

    @Autowired
    public CustomerResource(CustomerRepository repository) {
        this.repository = repository;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerREST get(@Context UriInfo uriInfo) {

        final CustomerREST customerREST = new CustomerREST(repository.findById(customerId));
        final URI uri = uriInfo.getAbsolutePathBuilder().build();
        customerREST.add(new Link(uri.toString(), "self"));
        return customerREST;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Customer customer) {
        customer.setId(customerId);
        repository.updateCustomer(customer);
        return Response.ok()
                .entity(new Message(200, "success", "Customer was updated successfully"))
                .build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete() {
        repository.removeCustomer(customerId);
        return Response.ok()
                .entity(new Message(200, "success", "Customer was deleted successfully"))
                .build();
    }

    @Path("{pathElement}")
    public RecursiveResource handlePath(@PathParam("pathElement")String pathElement) {
        return new RecursiveResource(pathElement);
    }

}
