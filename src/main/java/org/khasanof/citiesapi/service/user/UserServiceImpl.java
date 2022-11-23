package org.khasanof.citiesapi.service.user;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.config.encryption.PasswordEncoderConfigurer;
import org.khasanof.citiesapi.dto.user.UserCreateDTO;
import org.khasanof.citiesapi.dto.user.UserDetailDTO;
import org.khasanof.citiesapi.dto.user.UserGetDTO;
import org.khasanof.citiesapi.dto.user.UserUpdateDTO;
import org.khasanof.citiesapi.entity.user.UserEntity;
import org.khasanof.citiesapi.enums.UserRole;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
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
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMap(entity -> swapToObj(dto, entity)
                        .flatMap(repository::save)).then();
    }

    private Mono<UserEntity> swapToObj(UserUpdateDTO dto, UserEntity entity) {
        entity.setUsername(dto.getUsername());
        entity.setLastname(dto.getLastName());
        entity.setFirstname(dto.getFirstName());
        return Mono.just(entity);
    }

    @Override
    public Mono<UserDetailDTO> detail(Integer id) {
        checkId(id);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .map((obj) -> {
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
        if (Objects.isNull(id) || id < 1) {
            throw new RuntimeException("Invalid ID!");
        }
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username)
                .switchIfEmpty(Mono.error(new NotFoundException("User not found! try again")))
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
