package hu.dpc.edu;

import hu.dpc.edu.rest.*;
import hu.dpc.edu.rest.security.MySecurityResource;
import hu.dpc.edu.rest.security.MySecurityFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
        register(RolesAllowedDynamicFeature.class);
        register(MySecurityResource.class);
        register(MySecurityFilter.class);
    }
}
