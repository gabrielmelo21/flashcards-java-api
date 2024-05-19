package api.englishAPI.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.UUID;

public class GenerateJWT {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static String generateToken(UUID id) {
        String token = Jwts.builder()
                .claim("id", id)
                .signWith(SECRET_KEY)
                .compact();
        return token;
    }

}