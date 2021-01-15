package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.authentication.AuthorizationServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorizationTests {
    private AuthorizationServiceImplementation authService;

    private AuthorizationTests() {
        authService = new AuthorizationServiceImplementation();
    }

    @Test
    void RequestTest() {
        String subject = "Subject";

        String newSubject = authService.authorizeRequest(subject);

        Assertions.assertNotEquals(subject, newSubject);
    }
}
