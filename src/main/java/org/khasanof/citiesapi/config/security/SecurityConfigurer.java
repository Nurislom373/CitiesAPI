package org.khasanof.citiesapi.config.security;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.utils.jwt.JwtAuthenticationFilter;
import org.khasanof.citiesapi.utils.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfigurer {

    public final String[] WHITE_LIST = {
            "/ui",
            "/user/login",
            "/user/register",
            "/webjars/**",
            "/api-docs/**"
    };

    public final String[] ADMIN_LIST = {
            "/user/list",
            "/user/detail/**",
            "/user/update",
            "/city/create",
            "/city/update",
            "/city/updateWeather",
    };

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http, ReactiveAuthenticationManager manager, JwtTokenProvider tokenProvider) {
        return http.exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(manager)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(ex -> ex.pathMatchers(ADMIN_LIST).hasAuthority("ADMIN")
                        .pathMatchers(WHITE_LIST).permitAll()
                        .anyExchange().authenticated())
                .addFilterAt(new JwtAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService detailsService,
                                                                       PasswordEncoder passwordEncoder) {
        var manager = new UserDetailsRepositoryReactiveAuthenticationManager(detailsService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }

}
