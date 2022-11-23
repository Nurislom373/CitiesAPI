package org.khasanof.citiesapi.controller.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.user.UserCreateDTO;
import org.khasanof.citiesapi.dto.user.UserRequestDTO;
import org.khasanof.citiesapi.dto.user.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient client;

    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2NjkyMTU4MzN9.2cIAhMTn3yGLj4hWQJCe8puHv8Ms5WEsAaj82xZ7_Ns";

    @Test
    public void listMethodIsOkTest() {
        client.get().uri("http://localhost:8080/user/list")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void loginMethodIsOkTest() {

        var content = new UserRequestDTO("hack123", "123");

        client.post().uri("http://localhost:8080/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void registerMethodIsOkTest() {

        var content = new UserCreateDTO("Bob", "Bobov", "hack123", "123");

        client.post().uri("http://localhost:8080/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void updateMethodIsOkTest() {

        var content = new UserUpdateDTO(3,"Bobok", "Bobov", "hack123");

        client.put().uri("http://localhost:8080/user/update")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void updateMethodIsNotFoundTest() {

        var content = new UserUpdateDTO(30,"Bobok", "Bobov", "hack123");

        client.put().uri("http://localhost:8080/user/update")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    public void detailMethodIsOkTest() {
        var variable = 1;

        client.get().uri("http://localhost:8080/user/detail/" + variable)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        var variable = -10;

        client.get().uri("http://localhost:8080/user/detail/" + variable)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void detailMethodIsNotFoundTest() {
        var variable = 10;

        client.get().uri("http://localhost:8080/user/detail/" + variable)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isNotFound();
    }

}
