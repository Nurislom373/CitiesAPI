package org.khasanof.citiesapi.service.user;

import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.BaseService;
import org.khasanof.citiesapi.service.city.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * The reason for getting BaseService extend is to collect
 * all services in one BaseService tag and include it in AbstractController.
 *
 * @author Khasanof373
 * @see UserService ReactiveUserDetailsService
 * @since 1.0
 */
public interface UserService extends BaseService, ReactiveUserDetailsService {

    /**
     * This method is used to create the user entity.
     *
     * @param dto -> UserCreateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> register(UserCreateDTO dto);

    /**
     * This method is used to update the user entity.
     *
     * @param dto -> UserUpdateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> update(UserUpdateDTO dto);

    /**
     * This method is used to get the UserDetailDTO.
     *
     * @param id -> Incoming id cannot be less than one.
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<UserDetailDTO> detail(Integer id);

    /**
     * This method is used to get list the UserGetDTO.
     *
     * @return Flux<CityGetDTO>
     * @since 1.0
     */
    Flux<UserGetDTO> list();

}
