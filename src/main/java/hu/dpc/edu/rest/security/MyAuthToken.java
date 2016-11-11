package hu.dpc.edu.rest.security;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MyAuthToken {
    private String tokenId;
    private String username;
    private Set<String> roles;
    private LocalDateTime expiresOn;

    public MyAuthToken(String tokenId, String username, Set<String> roles, int expiresInSeconds) {
        this.tokenId = tokenId;
        this.username = username;
        this.roles = roles;
        expiresOn = LocalDateTime.now().plusSeconds(expiresInSeconds);
    }

    public boolean isExpired() {
        return expiresOn.isBefore(LocalDateTime.now());
    }

    public String getUsername() {
        return username;
    }

    public String getTokenId() {
        return tokenId;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
