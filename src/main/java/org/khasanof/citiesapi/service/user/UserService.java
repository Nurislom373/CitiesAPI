package org.khasanof.citiesapi.service.user;

import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UserService extends BaseService, ReactiveUserDetailsService {

    Mono<Void> register(UserCreateDTO dto);

    Mono<Void> update(UserUpdateDTO dto);

    Mono<UserDetailDTO> detail(Integer id);

    Flux<UserGetDTO> list();

}
