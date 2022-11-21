package org.khasanof.citiesapi.service.subscription;

import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubscriptionService {

    Mono<Void> subscribeToCity(SubscriptionCreateDTO dto);

    Flux<SubscriptionGetDTO> getSubscriptions(Integer userId);

}
