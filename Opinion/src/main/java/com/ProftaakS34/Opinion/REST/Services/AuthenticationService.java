package com.ProftaakS34.Opinion.REST.Services;

public interface AuthenticationService {
    String authorizeUserLogin(String username);
    String getTokenSubject(String jwt);
    boolean userLoggedIn(String jwt);
}
