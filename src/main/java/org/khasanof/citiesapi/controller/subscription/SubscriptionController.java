package org.khasanof.citiesapi.controller.subscription;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import org.khasanof.citiesapi.service.subscription.SubscriptionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/subscription/*")
public class SubscriptionController extends AbstractController<SubscriptionService> {

    public SubscriptionController(SubscriptionService service) {
        super(service);
    }

    @RequestMapping(value = "getSubscription/{id}", method = RequestMethod.GET)
    public Mono<SubscriptionGetDTO> get(@PathVariable Integer id) {
        return service.getSubscriptions(id);
    }

    @RequestMapping(value = "subscribeToCity", method = RequestMethod.POST)
    public Mono<Void> subscribe(@Valid @RequestBody SubscriptionCreateDTO dto) {
        return service.subscribeToCity(dto);
    }
}
