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

/**
 * This Controller class is used to handle requests to city APIs.
 * The AbstractController class accepts only services that extend from BaseService.
 *
 * @author Khasanof373
 * @see CityController
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/city/*")
public class CityController extends AbstractController<CityService> {

    public CityController(CityService service) {
        super(service);
    }

    /**
     * This method sends the DTO to the service's create method.
     *
     * @param dto -> CityCreateDTO comes from the corresponding request body
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Mono<Void> create(@Valid @RequestBody CityCreateDTO dto) {
        return service.create(dto);
    }

    /**
     * This method sends the DTO to the service's update method.
     *
     * @param dto -> CityUpdateDTO comes from the corresponding request body
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Mono<Void> update(@Valid @RequestBody CityUpdateDTO dto) {
        return service.update(dto);
    }

    /**
     * This method sends the DTO to the service's updateWeather method.
     *
     * @param dto -> CityWeatherUpdateDTO comes from the corresponding request body
     * @return Mono<Void>
     * @since 1.0
     */
    @RequestMapping(value = "updateWeather", method = RequestMethod.PUT)
    public Mono<Void> updateWeather(@Valid @RequestBody CityWeatherUpdateDTO dto) {
        return service.updateWeather(dto);
    }

    /**
     * This method is used to return the result.
     *
     * @return Flux<CityGetDTO>
     * @since 1.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Flux<CityGetDTO> list() {
        return service.list();
    }
}
