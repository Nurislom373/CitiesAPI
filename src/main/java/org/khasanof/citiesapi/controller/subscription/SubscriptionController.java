package org.khasanof.citiesapi.controller.subscription;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.controller.city.CityController;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import org.khasanof.citiesapi.service.subscription.SubscriptionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * This Controller class is used to handle requests to city APIs.
 * The AbstractController class accepts only services that extend from BaseService.
 *
 * @author Khasanof373
 * @see SubscriptionController
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/subscription/*")
public class SubscriptionController extends AbstractController<SubscriptionService> {

    public SubscriptionController(SubscriptionService service) {
        super(service);
    }

    /**
     * This method sends the DTO to the service's getSubscriptions method.
     *
     * @param userId -> Incoming id cannot be less than one.
     * @return Flux<CityGetDTO>
     * @since 1.0
     */
    @RequestMapping(value = "getSubscription/{userId}", method = RequestMethod.GET)
    public Flux<CityGetDTO> get(@PathVariable Integer userId) {
        return service.getSubscriptions(userId);
    }

    /**
     * This method sends the DTO to the service's subscribe method.
     *
     * @param dto ->
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "subscribeToCity", method = RequestMethod.POST)
    public Mono<Void> subscribe(@Valid @RequestBody SubscriptionCreateDTO dto) {
        return service.subscribeToCity(dto);
    }
}
