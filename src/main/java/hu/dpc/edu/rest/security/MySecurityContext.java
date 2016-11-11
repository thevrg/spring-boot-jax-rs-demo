package hu.dpc.edu.rest.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MySecurityContext implements SecurityContext {

    private MyPrincipal principal;
    private Set<String> roles;

    public MySecurityContext(String username, Set<String> roles) {
        principal = new MyPrincipal(username);
        this.roles = roles;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return roles.contains(role);
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
