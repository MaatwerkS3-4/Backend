package com.ProftaakS34.Opinion.authentication;

public interface AuthenticationService {
    String authorizeUserLogin(String id);
    String getTokenSubject(String jwt);
    boolean userLoggedIn(String jwt);
    String getId(String jwt);
}
