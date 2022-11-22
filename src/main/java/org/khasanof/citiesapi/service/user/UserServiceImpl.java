package org.khasanof.citiesapi.service.user;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.config.encryption.PasswordEncoderConfigurer;
import org.khasanof.citiesapi.dto.user.*;
import org.khasanof.citiesapi.entity.user.UserEntity;
import org.khasanof.citiesapi.enums.UserRole;
import org.khasanof.citiesapi.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoderConfigurer encoderConfigurer;
    private final UserRepository repository;

    @Override
    public Mono<Object> login(UserRequestDTO dto) {
        return null;
    }

    @Override
    public Mono<Void> register(UserCreateDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setRole(UserRole.USER.getValue());
        entity.setPassword(encoderConfigurer.encoder().encode(dto.getPassword()));
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

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(u -> User.withUsername(u.getUsername())
                        .password(u.getPassword())
                        .authorities(u.getRole())
                        .accountExpired(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .accountLocked(false)
                        .build()
                );
    }
}
