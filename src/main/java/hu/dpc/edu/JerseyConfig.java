package hu.dpc.edu;

import hu.dpc.edu.rest.*;
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
        register(EntityNotFoundExceptionMapper.class);
        register(IllegalArgumentExceptionMapper.class);
    }
}
