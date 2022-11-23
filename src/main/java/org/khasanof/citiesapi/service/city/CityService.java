package org.khasanof.citiesapi.service.city;

import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.service.BaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The reason for getting BaseService extend is to collect
 * all services in one BaseService tag and include it in AbstractController.
 *
 * @author Khasanof373
 * @see CityService
 * @since 1.0
 */
public interface CityService extends BaseService {

    /**
     * This method is used to create the city entity.
     *
     * @param dto -> CityCreateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> create(CityCreateDTO dto);

    /**
     * This method is used to update the city entity.
     * Throws NotFoundException if city is not found for the id you provide.
     *
     * @param dto -> CityUpdateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> update(CityUpdateDTO dto);

    /**
     * This method is used to update weather the city entity.
     * Throws NotFoundException if city is not found for the id you provide.
     * Throws an InvalidValidationException if the dto info field you provide is invalid
     *
     * @param dto -> CityWeatherUpdateDTO comes in and all its fields must be not null
     * @return Mono<Void>
     * @since 1.0
     */
    Mono<Void> updateWeather(CityWeatherUpdateDTO dto);

    /**
     * This method is used to get the CityGetDTO.
     * Throws NotFoundException if city is not found for the given id.
     *
     * @param id -> Incoming id cannot be less than one.
     * @return Mono<CityGetDTO>
     * @since 1.0
     */
    Mono<CityGetDTO> get(Integer id);

    /**
     * This method is used to get list the CityGetDTO.
     *
     * @return Flux<CityGetDTO>
     * @since 1.0
     */
    Flux<CityGetDTO> list();

}
