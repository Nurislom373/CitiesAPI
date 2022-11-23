package org.khasanof.citiesapi.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.citiesapi.dto.user.UserCreateDTO;
import org.khasanof.citiesapi.dto.user.UserDetailDTO;
import org.khasanof.citiesapi.dto.user.UserGetDTO;
import org.khasanof.citiesapi.dto.user.UserUpdateDTO;
import org.khasanof.citiesapi.service.user.UserServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class UserServiceMockTest {

    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(UserServiceImpl.class);
    }

    @Test
    public void registerMethodIsOkTest() {
        var one = new UserCreateDTO("fhdsbfs", "fdsbjjfvhds", "fudsgfyud", "fdjsbgfdshyvf");

        Mockito.when(service.register(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.register(one);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void detailMethodIsOkTest() {
        var detail = new UserDetailDTO(1, "fdsvfs", "fdsjfs",
                "ADMIN", "fudbsuhfd", "243456");

        Mockito.when(service.detail(ArgumentMatchers.any())).thenReturn(Mono.just(detail));
        var mono = service.detail(1);

        StepVerifier.create(mono)
                .expectNextMatches(o -> detail.getId()
                        .equals(o.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void updateMethodIsOkTest() {
        var dto = new UserUpdateDTO(1, "fdsvfs", "fdsjfs",
                "fgdhvfsh");

        Mockito.when(service.update(ArgumentMatchers.any())).thenReturn(Mono.empty());
        var mono = service.update(dto);

        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

    @Test
    public void listMethodIsOkTest() {
        var dtoOne = new UserGetDTO(1, "fdsvfs", "fdsjfs",
                "fgdhvfsh");
        var dtoTwo = new UserGetDTO(2, "fdsvfs", "fdsjfs",
                "fgdhvfsh");

        Mockito.when(service.list()).thenReturn(Flux.just(dtoOne, dtoTwo));
        var flux = service.list();

        StepVerifier.create(flux)
                .expectNext(dtoOne)
                .expectNext(dtoTwo)
                .expectComplete()
                .verify();
    }

}
