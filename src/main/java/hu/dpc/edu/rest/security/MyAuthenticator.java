package hu.dpc.edu.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by vrg on 2016. 11. 11..
 */
@Component
public class MyAuthenticator {

    private MyAuthTokenStore tokenStore;

    @Autowired
    public MyAuthenticator(MyAuthTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public MyAuthToken authenticate(String username, String password) {
        if ("cangetin".equals(password)) {
            Set<String> roles = new HashSet<>();
            roles.add("user");
            if (username.contains("admin")) {
                roles.add("admin");
            }
            if (username.contains("manager")) {
                roles.add("manager");
            }
            final MyAuthToken token = new MyAuthToken(UUID.randomUUID().toString(), username, roles, 3600);
            tokenStore.storeToken(token);
            return token;
        } else {
            throw new IncorrectCredentialsException("Login failed");
        }
    }
}
