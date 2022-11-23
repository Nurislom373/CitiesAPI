package org.khasanof.citiesapi.repository.subscription;

import org.khasanof.citiesapi.entity.subscription.SubscriptionEntity;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface is used to save, delete and perform other operations on subscription objects.
 *
 * @author Khasanof373
 * @see SubscriptionRepository
 * @since 1.0
 */
@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity, Integer> {

    Flux<SubscriptionEntity> findAllByUserId(Integer userId);

    Mono<Boolean> existsByUserId(Integer userId);

    Mono<Boolean> existsByUserIdAndCityId(Integer userId, Integer cityId);

}
