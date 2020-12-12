package com.ProftaakS34.Opinion.authentication;
import com.ProftaakS34.Opinion.helpers.AuthenticationConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthorizationServiceImplementation implements AuthorizationService{
    @Override
    public String authorizeRequest(String subject) {
        return createToken(subject);
    }
    private String createToken(String subject) {
        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthenticationConstants.SECRET.getBytes()));

        return AuthenticationConstants.TOKEN_PREFIX + token;

    }
}
