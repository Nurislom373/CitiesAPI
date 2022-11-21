package org.khasanof.citiesapi.repository.user;

import org.khasanof.citiesapi.entity.user.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {
}
