package org.khasanof.citiesapi.controller.city;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.enums.CityVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CityControllerTest {

    @Autowired
    private WebTestClient client;

    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoiQURNSU4iLCJleHAiOjE2NjkyMTU4MzN9.2cIAhMTn3yGLj4hWQJCe8puHv8Ms5WEsAaj82xZ7_Ns";

    @Test
    public void createMethodIsOkTest() {
        var content = new CityCreateDTO("Uzbekistan", "Tashkent", 14, 9,
                "2022-11-23", CityVisibility.SUNNY.getValue());

        client.post().uri("http://localhost:8080/city/create")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void updateMethodIsOkTest() {
        var content = new CityUpdateDTO(1, "USA", "Washington");

        client.put().uri("http://localhost:8080/city/update")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void updateMethodIsNotFoundTest() {
        var content = new CityUpdateDTO(6, "USA", "Washington");

        client.put().uri("http://localhost:8080/city/update")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void updateWeatherMethodIsOkTest() {
        var content = new CityWeatherUpdateDTO(1, 25, 15, "CLEAR");

        client.put().uri("http://localhost:8080/city/updateWeather")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void updateWeatherMethodIsNotFoundTest() {
        var content = new CityWeatherUpdateDTO(6, 25, 15, "CLEAR");

        client.put().uri("http://localhost:8080/city/updateWeather")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(content)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    public void listMethodIsOkTest() {
        client.get().uri("http://localhost:8080/city/list")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
    }


}
