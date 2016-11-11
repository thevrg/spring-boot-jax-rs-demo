package hu.dpc.edu.rest.security;

import hu.dpc.edu.RestResource;
import hu.dpc.edu.rest.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * Created by vrg on 2016. 11. 11..
 */
@Path("security")
@RestResource
public class MySecurityResource {

    private MyAuthenticator authenticator;

    private MyAuthTokenStore tokenStore;

    @Autowired
    public MySecurityResource(MyAuthenticator authenticator, MyAuthTokenStore tokenStore) {
        this.authenticator = authenticator;
        this.tokenStore = tokenStore;
    }

    @Path("authenticate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(Credentials credentials, @Context UriInfo uriInfo) {
        final MyAuthToken token = authenticator
                .authenticate(credentials.getUsername(), credentials.getPassword());

        final URI tokenId = uriInfo.getBaseUriBuilder()
                .path(MySecurityResource.class, "findTokenById")
                .build(token.getTokenId());

        return Response
                .created(tokenId)
                .entity(new Message(201, "success", "Authentication was successful"))
                .build();
    }

    @Path("tokens/{tokenId}")
    @GET
    public MyAuthToken findTokenById(@PathParam("tokenId")String tokenId) {
        return tokenStore.findById(tokenId);
    }
}
