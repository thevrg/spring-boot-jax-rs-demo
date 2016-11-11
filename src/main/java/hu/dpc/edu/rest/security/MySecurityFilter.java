package hu.dpc.edu.rest.security;

import hu.dpc.edu.rest.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vrg on 2016. 11. 11..
 */
@Provider
@ProtectedByMySecurityFilter
@Priority(Priorities.AUTHENTICATION)
public class MySecurityFilter implements ContainerRequestFilter {
    public final static Pattern bearerTokenPattern = Pattern.compile("Bearer\\s+(.*)", Pattern.CASE_INSENSITIVE);

    @Autowired
    private MyAuthTokenStore tokenStore;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {


        String tokenId = getTokenId(requestContext);

        if (tokenId == null) {
            requestContext.abortWith(Response
                    .status(Response.Status.FORBIDDEN)
                    .build());
            return;
        }

        try {
            final MyAuthToken token = tokenStore.findById(tokenId);
            final MySecurityContext context = new MySecurityContext(token.getUsername(),
                    token.getRoles());


            requestContext.setSecurityContext(context);
        } catch (EntityNotFoundException ex) {
            requestContext.abortWith(Response
                    .status(Response.Status.FORBIDDEN)
                    .build());
            return;
        }



    }

    private String getTokenId(ContainerRequestContext requestContext) {
        String tokenId = null;

        final String authorization = requestContext.getHeaderString("Authorization");

        if (authorization == null) {
            return null;
        }

        final Matcher matcher = bearerTokenPattern.matcher(authorization);
        if (matcher.matches()) {
            tokenId = matcher.group(1);
        }
        return tokenId;
    }
}
