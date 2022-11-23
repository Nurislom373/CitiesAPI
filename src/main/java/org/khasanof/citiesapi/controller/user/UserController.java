package org.khasanof.citiesapi.controller.user;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.user.UserService;
import org.khasanof.citiesapi.utils.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Map;

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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Mono<Void> register(@Valid @RequestBody UserCreateDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Mono<Void> update(@Valid @RequestBody UserUpdateDTO dto) {
        return service.update(dto);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public Mono<UserDetailDTO> detail(@PathVariable Integer id) {
        return service.detail(id);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Flux<UserGetDTO> list() {
        return service.list();
    }
}
