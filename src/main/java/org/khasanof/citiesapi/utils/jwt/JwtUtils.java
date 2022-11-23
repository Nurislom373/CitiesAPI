package org.khasanof.citiesapi.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {

    // validity in milliseconds
    public static Long expiry = TimeUnit.MINUTES.toMillis(60);
    public static String secretKey = "qwertyuiop1lkjhgfdsa2zxcvbnm3mnbvcxz4asdfghjkl5poiuytrewq";

    public static Date getExpiry() {
        return new Date(System.currentTimeMillis() + expiry);
    }

    public static Date getExpiryForRefreshToken() {
        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3));
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
