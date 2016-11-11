package hu.dpc.edu.rest.security;

import hu.dpc.edu.rest.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vrg on 2016. 11. 11..
 */
@Component
public class MyAuthTokenStore {
    private Map<String, MyAuthToken> tokens = new HashMap<>();

    public void storeToken(MyAuthToken token) {
        this.tokens.put(token.getTokenId(), token);
    }

    public MyAuthToken findById(String tokenId) {
        final MyAuthToken token = tokens.get(tokenId);
        if (token == null || token.isExpired()) {
            tokens.remove(tokenId);
            throw new EntityNotFoundException(MyAuthToken.class, 1);
        }
        return token;
    }
}
