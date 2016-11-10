package hu.dpc.edu;

import hu.dpc.edu.rest.CustomersResource;
import hu.dpc.edu.rest.MessageResource;
import hu.dpc.edu.rest.MessageToXMLWriter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by vrg on 2016. 11. 09..
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MessageResource.class);
        register(CustomersResource.class);
        register(MessageToXMLWriter.class);
    }
}
