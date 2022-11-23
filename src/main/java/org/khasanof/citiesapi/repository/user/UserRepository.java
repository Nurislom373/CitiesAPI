package org.khasanof.citiesapi.repository.user;

import org.khasanof.citiesapi.entity.user.UserEntity;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * This interface is used to save, delete and perform other operations on user objects.
 *
 * @author Khasanof373
 * @see UserRepository
 * @since 1.0
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);

}
