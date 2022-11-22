package org.khasanof.citiesapi.controller.city;

import org.khasanof.citiesapi.controller.AbstractController;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.service.city.CityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/city/*")
public class CityController extends AbstractController<CityService> {

    public CityController(CityService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Mono<Void> create(@Valid @RequestBody CityCreateDTO dto) {
        return service.create(dto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Mono<Void> update(@Valid @RequestBody CityUpdateDTO dto) {
        return service.update(dto);
    }

    @RequestMapping(value = "updateWeather", method = RequestMethod.PUT)
    public Mono<Void> updateWeather(@Valid @RequestBody CityWeatherUpdateDTO dto) {
        return service.updateWeather(dto);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Flux<CityGetDTO> list() {
        return service.list();
    }
}
