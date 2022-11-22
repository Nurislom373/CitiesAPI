package org.khasanof.citiesapi.repository.user;

import org.khasanof.citiesapi.entity.user.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);

}
