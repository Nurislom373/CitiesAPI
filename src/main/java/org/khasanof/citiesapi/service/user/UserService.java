package org.khasanof.citiesapi.service.user;

import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.BaseService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService extends BaseService, ReactiveUserDetailsService {

    Mono<Object> login(UserRequestDTO dto);

    Mono<Void> register(UserCreateDTO dto);

    Mono<Void> update(UserUpdateDTO dto);

    Mono<UserDetailDTO> detail(Integer id);

    Flux<UserGetDTO> list();

}
