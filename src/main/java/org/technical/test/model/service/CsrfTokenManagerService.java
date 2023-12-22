package org.technical.test.model.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.technical.test.model.entity.UserKey;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CsrfTokenManagerService {

    // Metodo para generar un token CSRF
    public UserKey generateToken(UserKey userKey) {

        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String csrfToken = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

        userKey.setAnti_csrf_key(csrfToken);
        return userKey;
    }

    public boolean validateToken(String token){
        return false;
    }
    
}
