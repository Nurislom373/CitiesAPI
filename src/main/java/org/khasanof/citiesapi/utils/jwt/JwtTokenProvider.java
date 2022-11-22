package org.khasanof.citiesapi.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "roles";

    private final JWTUtils utils;

    public String createToken(Authentication authentication) {
        Date expiry = JWTUtils.getExpiry();
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiry)
                .withClaim("role", authorities.stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0))
                .sign(JWTUtils.getAlgorithm());
    }

}
