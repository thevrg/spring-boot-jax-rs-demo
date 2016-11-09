package hu.dpc.edu.rest;

import hu.dpc.edu.Customer;
import hu.dpc.edu.CustomerRepository;
import hu.dpc.edu.RestResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vrg on 2016. 11. 09..
 */
@RestResource
@Path("customers")
public class CustomersResource {

    private CustomerRepository repository;

    @Autowired
    public CustomersResource(CustomerRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCustomer(Customer customer) {
        repository.addCustomer(customer);
    }

    @GET
    @Produces("text/plain")
    public String test() {
        return "it works";
    }
}
