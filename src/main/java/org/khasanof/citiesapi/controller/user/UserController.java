package org.khasanof.citiesapi.controller.user;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.user.UserService;
import org.khasanof.citiesapi.utils.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

/**
 * This Controller class is used to handle requests to city APIs.
 * The AbstractController class accepts only services that extend from BaseService.
 *
 * @author Khasanof373
 * @see UserController
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/user/*")
public class UserController extends AbstractController<UserService> {

    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    public UserController(UserService service, JwtTokenProvider tokenProvider, ReactiveAuthenticationManager authenticationManager) {
        super(service);
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * This method is used to get a token for registered users through the register method.
     *
     * @param mono -> UserRequestDTO comes from the corresponding request body
     * @return Mono<Object>
     * @since 1.0
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Mono<Object> login(@Valid @RequestBody Mono<UserRequestDTO> mono) {
        return mono.flatMap(login -> this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
                .map(this.tokenProvider::createToken)
        ).map(jwt -> {
            var token = Map.of("access_token", jwt);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        });
    }

    /**
     * This method sends the DTO to the service's register method.
     *
     * @param dto -> UserCreateDTO comes from the corresponding request body
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Mono<Void> register(@Valid @RequestBody UserCreateDTO dto) {
        return service.register(dto);
    }

    /**
     * This method sends the DTO to the service's update method.
     *
     * @param dto -> UserUpdateDTO comes from the corresponding request body
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Mono<Void> update(@Valid @RequestBody UserUpdateDTO dto) {
        return service.update(dto);
    }

    /**
     * This method sends the DTO to the service's detail method.
     *
     * @param id -> Incoming id cannot be less than one.
     * @return Mono<UserDetailDTO>
     * @since 1.0
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public Mono<UserDetailDTO> detail(@PathVariable Integer id) {
        return service.detail(id);
    }

    /**
     * This method is used to return the result.
     *
     * @return Flux<UserGetDTO>
     * @since 1.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Flux<UserGetDTO> list() {
        return service.list();
    }
}
