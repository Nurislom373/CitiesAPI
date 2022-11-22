//package org.khasanof.citiesapi.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfigurer {
//
//    public static final String[] WHITE_LIST = {
//            "/swagger-ui/**",
//            "/swagger-documentation/**"
//    };
//
//    @Bean
//    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
//        return http
//                .cors().disable()
//                .authorizeExchange().pathMatchers(WHITE_LIST).permitAll()
//                .anyExchange().authenticated()
//                .and().build();
//    }
//
//}
