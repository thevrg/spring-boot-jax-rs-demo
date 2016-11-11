package hu.dpc.edu;

import hu.dpc.edu.rest.Message;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.json.jaxrs.JsonStructureBodyReader;
import org.glassfish.json.jaxrs.JsonStructureBodyWriter;
import org.json.JSONObject;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.junit.Assert.assertEquals;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class RestClientTest {

    @Test
    public void testRestClient() {
        final Entity<Customer> entity = Entity.entity(new Customer("Bela", "Kovacs"), MediaType.APPLICATION_JSON_TYPE);

        final ClientConfig config = new ClientConfig()
                .property(ClientProperties.METAINF_SERVICES_LOOKUP_DISABLE, true)
                .property(ClientProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true)
                .register(JsonProcessingFeature.class);

        final Response response = ClientBuilder.newBuilder()
                .withConfig(config).newClient()
                .target("http://localhost:8080/customers")
                .request(MediaType.APPLICATION_JSON)
                .post(entity);

        final URI location = response.getLocation();

        final Response secondResponse = ClientBuilder.newBuilder()
                .withConfig(config).newClient()
                .target("http://localhost:8080/customers/{customerId}")
                .resolveTemplate("customerId", 1)
                .request()
                .get();

        System.out.println(location);

        System.out.println(secondResponse.readEntity(CustomerREST.class));
    }
}
