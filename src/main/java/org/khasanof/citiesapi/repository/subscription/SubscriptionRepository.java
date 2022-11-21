package org.khasanof.citiesapi.repository.subscription;

import org.khasanof.citiesapi.entity.subscription.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity, Integer> {
}
