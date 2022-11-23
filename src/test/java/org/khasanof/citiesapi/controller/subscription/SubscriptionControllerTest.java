package org.khasanof.citiesapi.controller.subscription;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.enums.CityVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SubscriptionControllerTest {

    @Autowired
    private WebTestClient client;

    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2NjkyMTU4MzN9.2cIAhMTn3yGLj4hWQJCe8puHv8Ms5WEsAaj82xZ7_Ns";

    @Test
    public void subscribeMethodIsOkTest() {

        var content = new SubscriptionCreateDTO(1, 4);

        client.post().uri("http://localhost:8080/subscription/subscribeToCity")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void subscribeMethodIsNotFoundTest() {

        var list = List.of(
                new SubscriptionCreateDTO(10, 4),
                new SubscriptionCreateDTO(1, 40),
                new SubscriptionCreateDTO(5, 4)
        );

        list.forEach((var) -> {
            client.post().uri("http://localhost:8080/subscription/subscribeToCity")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(var)
                    .exchange()
                    .expectStatus().isNotFound();
        });

    }

    @Test
    public void subscribeMethodIsBadRequestTest() {

        var list = List.of(
                new SubscriptionCreateDTO(1, 4),
                new SubscriptionCreateDTO(1, 1)
        );

        list.forEach((var) -> {
            client.post().uri("http://localhost:8080/subscription/subscribeToCity")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(var)
                    .exchange()
                    .expectStatus().isBadRequest();
        });

    }

    @Test
    public void getSubscribeMethodIsOkTest() {

        var variable = 1;

        client.get().uri("http://localhost:8080/subscription/getSubscription/" + variable)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void getSubscribeMethodIsNotFoundTest() {

        var variable = 10;

        client.get().uri("http://localhost:8080/subscription/getSubscription/" + variable)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isNotFound();

    }

}
