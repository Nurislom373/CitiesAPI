package org.khasanof.citiesapi.service.city;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.service.city.CityServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

public class CityServiceMockTest {

    private CityServiceImpl service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(CityServiceImpl.class);
    }

    @Test
    public void getMethodIsOkTest() {
        CityGetDTO one = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");

        Mockito.when(service.get(ArgumentMatchers.any())).thenReturn(Mono.just(one));
        Mono<CityGetDTO> mono = service.get(1);

        StepVerifier.create(mono)
                .assertNext(o -> {
                    Assertions.assertEquals(o.getInfo(), "SUNNY");
                }).expectComplete().verify();
    }

    @Test
    public void getMethodIsNotFoundTest() {
        CityGetDTO one = new CityGetDTO(10, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");

        Mockito.when(service.get(ArgumentMatchers.any()))
                .thenReturn(Mono.error(new NotFoundException("City not found")));
        Mono<CityGetDTO> mono = service.get(1);

        StepVerifier.create(mono)
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("City not found")
                ).verify();
    }

    @Test
    public void createMethodIsOkTest() {
        var one = new CityCreateDTO("uigfds", "fdus", 12,
                2, "2022-10-18", "SUNNY");

        Mockito.when(service.create(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.create(one);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void updateMethodIsOkTest() {
        var one = new CityUpdateDTO(1, "uigfds", "fdus");

        Mockito.when(service.update(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.update(one);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void updateWeatherMethodIsOkTest() {
        var one = new CityWeatherUpdateDTO(1, 12, 6, "CLEAR");

        Mockito.when(service.updateWeather(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.updateWeather(one);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void listMethodIsOkTest() {
        CityGetDTO one = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");
        CityGetDTO two = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");

        Mockito.when(service.list()).thenReturn(Flux.just(one, two));
        Flux<CityGetDTO> list = service.list();

        StepVerifier.create(list)
                .expectNext(one)
                .expectNext(two)
                .expectComplete()
                .verify();
    }

}
