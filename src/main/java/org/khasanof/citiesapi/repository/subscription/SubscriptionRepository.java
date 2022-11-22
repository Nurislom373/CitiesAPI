package org.khasanof.citiesapi.repository.subscription;

import liquibase.pro.packaged.B;
import org.khasanof.citiesapi.entity.subscription.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity, Integer> {

    Flux<SubscriptionEntity> findAllByUserId(Integer userId);

    Mono<Boolean> existsByUserId(Integer userId);
    Mono<Boolean> existsByUserIdAndCityId(Integer userId, Integer cityId);

}
