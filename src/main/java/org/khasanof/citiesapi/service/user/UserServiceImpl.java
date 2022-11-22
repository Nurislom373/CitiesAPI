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
    public Mono<Void> register(UserCreateDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setRole(UserRole.USER.getValue());
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity)
                .then();
    }

    @Override
    public Mono<Void> update(UserUpdateDTO dto) {
        return repository.findById(dto.getId())
                .flatMap(entity -> {
                    BeanUtils.copyProperties(dto, entity, "id");
                    return Mono.just(entity);
                }).map(repository::save).then();
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
