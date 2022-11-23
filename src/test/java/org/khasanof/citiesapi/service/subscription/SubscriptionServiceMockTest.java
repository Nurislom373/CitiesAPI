package org.khasanof.citiesapi.service.subscription;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.service.subscription.SubscriptionServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

public class SubscriptionServiceMockTest {

    private SubscriptionServiceImpl service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(SubscriptionServiceImpl.class);
    }

    @Test
    public void subscribeToCityMethodIsOkTest() {
        var one = new SubscriptionCreateDTO(1, 2);

        Mockito.when(service.subscribeToCity(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.subscribeToCity(one);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void getMethodIsOkTest() {
        CityGetDTO one = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");
        CityGetDTO two = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");

        Mockito.when(service.getSubscriptions(ArgumentMatchers.any())).thenReturn(Flux.just(one, two));
        var flux = service.getSubscriptions(1);

        StepVerifier.create(flux)
                .expectNext(one)
                .expectNext(two)
                .expectComplete()
                .verify();
    }

}
