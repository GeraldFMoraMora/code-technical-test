package org.technical.test.model.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.technical.test.model.dao.UserKeyDao;
import org.technical.test.model.entity.UserKey;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CsrfTokenManagerService {

    @Inject
    UserKeyDao keyDao;

    // Metodo para generar un token CSRF
    public UserKey generateToken(UserKey userKey) {

        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String csrfToken = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

        userKey.setAnti_csrf_key(csrfToken);
        return userKey;
    }

    public boolean validateToken(String token, Integer customer_id){
        UserKey userKey = keyDao.findByCustomerId(customer_id);
        if (token.equals(userKey.getAnti_csrf_key())){
            return true;
        }
        return false;
    }
    
}
