package org.khasanof.citiesapi.service.city;

import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.service.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService extends BaseService {

    Mono<Void> create(CityCreateDTO dto);

    Mono<Void> update(CityUpdateDTO dto);

    Mono<Void> updateWeather(CityWeatherUpdateDTO dto);

    Mono<CityGetDTO> getDTO(Integer id);

    Flux<CityGetDTO> list();

}
