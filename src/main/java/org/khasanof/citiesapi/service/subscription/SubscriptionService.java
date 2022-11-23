package org.khasanof.citiesapi.service.subscription;

import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import org.khasanof.citiesapi.service.BaseService;
import org.khasanof.citiesapi.service.city.CityService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The reason for getting BaseService extend is to collect
 * all services in one BaseService tag and include it in AbstractController.
 *
 * @author Khasanof373
 * @see SubscriptionService
 * @since 1.0
 */
public interface SubscriptionService extends BaseService {

    /**
     * This method is used to create the subscription entity.
     *
     * @param dto -> SubscriptionCreateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> subscribeToCity(SubscriptionCreateDTO dto);

    /**
     * This method is used to get the subscription CityGetDTOs.
     *
     * @param userId -> Incoming id cannot be less than one.
     * @return Mono<Void>
     * @since 1.0
     */
    Flux<CityGetDTO> getSubscriptions(Integer userId);

}
