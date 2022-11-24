package org.khasanof.citiesapi.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "role";

    private final JwtUtils utils;

    public String createToken(Authentication authentication) {
        Date expiry = JwtUtils.getExpiry();
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> collect = authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiry)
                .withClaim("role", collect)
                .sign(JwtUtils.getAlgorithm());
    }

    public Authentication getAuthentication(String token) {

        DecodedJWT jwt = JwtUtils.getVerifier().verify(token);
        String username = jwt.getSubject();

        List<String> roles;
        if (Objects.isNull(jwt.getClaim("role").asList(String.class))) {
            roles = new ArrayList<>();
        } else {
            roles = jwt.getClaim("role").asList(String.class);
        }
        System.out.println("roles = " + roles);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        System.out.println("authorities = " + authorities);
        User principal = new User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT verify = JwtUtils.getVerifier().verify(token);
            log.info("expiration date: {}", verify.getExpiresAt());
            return true;
        } catch (IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

}
