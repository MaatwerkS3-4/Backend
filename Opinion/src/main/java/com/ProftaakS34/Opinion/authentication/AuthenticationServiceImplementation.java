package com.ProftaakS34.Opinion.authentication;

import com.ProftaakS34.Opinion.helpers.AuthenticationConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {
    private AuthorizationService authorizationService;
    AuthenticationServiceImplementation(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
    @Override
    public String authorizeUserLogin(String id) {
        return authorizationService.authorizeRequest("loggedIn" + id);
    }
    @Override
    public String getTokenSubject(String jwt){
        if(jwt == null || jwt.isEmpty()) return null;

        return JWT.require(Algorithm.HMAC512(AuthenticationConstants.SECRET.getBytes()))
                .build()
                .verify(jwt.replace(AuthenticationConstants.TOKEN_PREFIX, ""))
                .getSubject();
    }

    @Override
    public String getId(String jwt) {
        return getTokenSubject(jwt).substring(8);
    }

    @Override
    public boolean userLoggedIn(String jwt) {
        return (getTokenSubject(jwt).substring(0,8).equals("loggedIn"));
    }

}
