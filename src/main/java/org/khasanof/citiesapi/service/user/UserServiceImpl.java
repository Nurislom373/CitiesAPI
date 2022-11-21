package org.khasanof.citiesapi.service.user;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.entity.user.UserEntity;
import org.khasanof.citiesapi.enums.UserRole;
import org.khasanof.citiesapi.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Mono<Object> login(UserRequestDTO dto) {
        return null;
    }

    @Override
    public Mono<Void> register(Mono<UserCreateDTO> mono) {
        return mono.map((obj) -> {
                    UserEntity entity = new UserEntity();
                    entity.setRole(UserRole.USER);
                    BeanUtils.copyProperties(obj, entity);
                    return entity;
                }).flatMap(repository::save)
                .then();
    }

    @Override
    public Mono<Void> update(Mono<UserUpdateDTO> mono) {
        return mono.flatMap(dto -> Mono.just(dto)
                .publishOn(Schedulers.boundedElastic())
                .mapNotNull((obj) -> repository.findById(obj.getId())
                        .block())
                .flatMap(entity -> {
                    BeanUtils.copyProperties(dto, entity, "id");
                    return Mono.just(entity);
                }).map(repository::save).then());
    }

    @Override
    public Mono<UserDetailDTO> detail(Integer id) {
        checkId(id);
        return repository.findById(id).map((obj) -> {
            UserDetailDTO dto = new UserDetailDTO();
            BeanUtils.copyProperties(obj, dto);
            return dto;
        });
    }

    @Override
    public Flux<UserGetDTO> list() {
        return repository.findAll().map((obj) -> {
            UserGetDTO dto = new UserGetDTO();
            BeanUtils.copyProperties(obj, dto);
            return dto;
        });
    }

    private void checkId(Integer id) {
        if (Objects.isNull(id) || id > 1) {
            throw new RuntimeException("Invalid ID!");
        }
    }
}
