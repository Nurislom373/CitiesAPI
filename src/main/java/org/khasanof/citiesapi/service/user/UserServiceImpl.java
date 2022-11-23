package org.khasanof.citiesapi.service.user;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.config.encryption.PasswordEncoderConfigurer;
import org.khasanof.citiesapi.dto.user.UserCreateDTO;
import org.khasanof.citiesapi.dto.user.UserDetailDTO;
import org.khasanof.citiesapi.dto.user.UserGetDTO;
import org.khasanof.citiesapi.dto.user.UserUpdateDTO;
import org.khasanof.citiesapi.entity.user.UserEntity;
import org.khasanof.citiesapi.enums.UserRole;
import org.khasanof.citiesapi.exception.exception.InvalidValidationException;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


/**
 * This class is used for manipulation on the user entity.
 *
 * @author Khasanof373
 * @see UserServiceImpl
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoderConfigurer encoderConfigurer;
    private final UserRepository repository;

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.user.UserService#register(dto.user.UserCreateDTO)
     */
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

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.user.UserService#update(dto.user.UserUpdateDTO)
     */
    @Override
    public Mono<Void> update(UserUpdateDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
                .flatMap(entity -> swapToObj(dto, entity)
                        .flatMap(repository::save)).then();
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.user.UserService#detail(java.lang.Integer)
     */
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

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.user.UserService#list()
     */
    @Override
    public Flux<UserGetDTO> list() {
        return repository.findAll().map((obj) -> {
            UserGetDTO dto = new UserGetDTO();
            BeanUtils.copyProperties(obj, dto);
            return dto;
        });
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.user.ReactiveUserDetailsService#findByUsername(java.lang.String)
     */
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

    /**
     * This method is used to swap the dto entity
     *
     * @param dto -> UserUpdateDTO comes in and all its fields must be not null
     * @param entity -> UserEntity comes in and all its fields must be not null
     * @return Mono<UserEntity>
     * @since 1.0
     */
    private Mono<UserEntity> swapToObj(UserUpdateDTO dto, UserEntity entity) {
        entity.setUsername(dto.getUsername());
        entity.setLastname(dto.getLastName());
        entity.setFirstname(dto.getFirstName());
        return Mono.just(entity);
    }

    /**
     * This method is used to check id.
     *
     * @param id -> Incoming id cannot be less than one.
     * @since 1.0
     */
    private void checkId(Integer id) {
        if (Objects.isNull(id) || id < 1) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }
}
