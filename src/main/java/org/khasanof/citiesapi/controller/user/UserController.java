package org.khasanof.citiesapi.controller.user;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.user.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user/*")
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Mono<Void> register(@Valid @RequestBody UserCreateDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Mono<Object> login(@Valid @RequestBody UserRequestDTO dto) {
        return service.login(dto);
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
