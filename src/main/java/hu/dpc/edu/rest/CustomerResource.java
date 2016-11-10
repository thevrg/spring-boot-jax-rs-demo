package hu.dpc.edu.rest;

import hu.dpc.edu.Customer;
import hu.dpc.edu.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class CustomerResource {

    private long customerId;
    private CustomerRepository repository;

    public CustomerResource(long customerId, CustomerRepository repository) {
        this.customerId = customerId;
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer get() {
        return repository.findById(customerId);
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

}
