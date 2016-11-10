package hu.dpc.edu.rest;

import hu.dpc.edu.Customer;
import hu.dpc.edu.CustomerRepository;
import hu.dpc.edu.RestResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vrg on 2016. 11. 09..
 */
@RestResource
@Path("customers")
public class CustomersResource {

    private CustomerRepository repository;
    private Provider<CustomerResource>customerResourceProvider;

    @Autowired
    public CustomersResource(CustomerRepository repository, Provider<CustomerResource>customerResourceProvider) {
        this.repository = repository;
        this.customerResourceProvider = customerResourceProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers(@QueryParam("justLinks") @DefaultValue("true") boolean justLinks,
            @Context UriInfo uriInfo) {

        if (!justLinks) {
            return Response.ok().entity(repository.findAll()).build();
        } else {
            final List<String> uriList = repository.findAll().stream()
                    .map(customer -> uriInfo.getAbsolutePathBuilder()
                            .path(CustomersResource.class, "findCustomerById")
                            .build(customer.getId()).toString()
                    ).collect(Collectors.toList());

            return Response.ok().entity(uriList).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON+";charset=UTF-8", MediaType.APPLICATION_XML})
//    @CustomMarshaller(rootElement = "Uzenet")
    public Response addCustomer(Customer customer, @Context UriInfo uriInfo) {
        final long newId = repository.addCustomer(customer);

        final URI customerURI = uriInfo.getAbsolutePathBuilder()
                .path(CustomersResource.class, "findCustomerById")
                .build(newId);

        return Response
                .created(customerURI)
                .header("customHeader","customValue")
                .entity(new Message(201,
                        "Created",
                        "Customer successfully crated with id: " + newId))
//                .type(MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8"))
                .build();
    }

    @Path("{customerId:\\d+}")
    public CustomerResource findCustomerById(@PathParam("customerId") long id) {
        final CustomerResource customerResource = customerResourceProvider.get();
        customerResource.setCustomerId(id);
        return customerResource;
    }

    @GET
    @Path("titok")
    public String hello() {
        return "Hello world";
    }

    @GET
    @Produces("text/plain")
    public String test() {
        return "it works";
    }
}
