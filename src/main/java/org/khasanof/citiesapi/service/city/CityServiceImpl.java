package org.khasanof.citiesapi.service.city;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.entity.city.CityEntity;
import org.khasanof.citiesapi.enums.CityVisibility;
import org.khasanof.citiesapi.exception.exception.InvalidValidationException;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is used for manipulation on the city entity.
 *
 * @author Khasanof373
 * @see CityServiceImpl
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.city.CityService#create(dto.city.CityCreateDTO)
     */
    @Override
    public Mono<Void> create(CityCreateDTO dto) {
        return repository.save(returnToEntity(dto))
                .then();
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.city.CityService#update(dto.city.CityUpdateDTO)
     */
    @Override
    public Mono<Void> update(CityUpdateDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.city.CityService#updateWeather(dto.city.CityWeatherUpdateDTO)
     */
    @Override
    public Mono<Void> updateWeather(CityWeatherUpdateDTO dto) {
        if (!CityVisibility.hasVisibility(dto.getInfo())) {
            throw new InvalidValidationException("Invalid Info!");
        }
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.city.CityService#getDTO(java.lang.Integer)
     */
    @Override
    public Mono<CityGetDTO> get(Integer id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map(this::returnToGetDTO);
    }

    /*
     * (non-Javadoc)
     * @see org.khasanof.citiesapi.service.city.CityService#list()
     */
    @Override
    public Flux<CityGetDTO> list() {
        return repository.findAll()
                .map(this::returnToGetDTO);
    }

    /**
     * This method is used to mapped to entity.
     *
     * @param dto    -> CityWeatherUpdateDTO must be not null
     * @param entity -> CityEntity must be not null
     * @return CityEntity
     * @since 1.0
     */
    private CityEntity mapToEntity(CityWeatherUpdateDTO dto, CityEntity entity) {
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    /**
     * This method is used to mapped to entity.
     *
     * @param dto    -> CityUpdateDTO must be not null
     * @param entity -> CityEntity must be not null
     * @return CityEntity
     * @since 1.0
     */
    private CityEntity mapToEntity(CityUpdateDTO dto, CityEntity entity) {
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    /**
     * This method is used to entity mapped to CityGetDTO.
     *
     * @param entity -> CityEntity must be not null
     * @return CityGetDTO
     * @since 1.0
     */
    private CityGetDTO returnToGetDTO(CityEntity entity) {
        CityGetDTO dto = new CityGetDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    /**
     * This method is used to dto mapped to CityEntity.
     *
     * @param dto -> CityEntity must be not null
     * @return CityEntity
     * @since 1.0
     */
    private CityEntity returnToEntity(CityCreateDTO dto) {
        if (!CityVisibility.hasVisibility(dto.getInfo())) {
            throw new RuntimeException("Invalid Info!");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        CityEntity entity = new CityEntity();
        entity.setDate(LocalDate.parse(dto.getDateStr(), dateTimeFormatter));
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
