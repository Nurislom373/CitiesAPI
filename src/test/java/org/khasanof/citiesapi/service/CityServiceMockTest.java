package org.khasanof.citiesapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.service.city.CityServiceImpl;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

public class CityServiceMockTest {

    private CityServiceImpl service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(CityServiceImpl.class);
    }

    @Test
    public void listMethodTest() {
        CityGetDTO one = new CityGetDTO(1, "uigfds", "fdus", 12,
                2, LocalDate.now(), "SUNNY");
        CityGetDTO two = new CityGetDTO(2, "fdjs", "fdus", 22,
                12, LocalDate.now(), "SUNNY");

        Mockito.when(service.list()).thenReturn(Flux.just(one, two));
        Flux<CityGetDTO> list = service.list();

        StepVerifier.create(list)
                .assertNext(o -> {
                    Assertions.assertEquals(o.getInfo(), "SUNNY");
                }).expectComplete().verify();
    }

}
