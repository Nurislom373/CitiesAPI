package org.khasanof.citiesapi.service.user;

import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.service.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService extends BaseService {

    Mono<Object> login(UserRequestDTO dto);

    Mono<Void> register(Mono<UserCreateDTO> mono);

    Mono<Void> update(Mono<UserUpdateDTO> mono);

    Mono<UserDetailDTO> detail(Integer id);

    Flux<UserGetDTO> list();

}
