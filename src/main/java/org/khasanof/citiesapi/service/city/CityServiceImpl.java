package org.khasanof.citiesapi.service.city;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.entity.city.CityEntity;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Override
    public Mono<Void> create(CityCreateDTO dto) {
        return repository.save(returnToEntity(dto))
                .then();
    }

    @Override
    public Mono<Void> update(CityUpdateDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    @Override
    public Mono<Void> updateWeather(CityWeatherUpdateDTO dto) {
        return repository.findById(dto.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    @Override
    public Mono<CityGetDTO> getDTO(Integer id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("City not found")))
                .map(this::returnToGetDTO);
    }

    @Override
    public Flux<CityGetDTO> list() {
        return repository.findAll()
                .map(this::returnToGetDTO);
    }

    private CityEntity mapToEntity(CityWeatherUpdateDTO dto, CityEntity entity) {
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    private CityEntity mapToEntity(CityUpdateDTO dto, CityEntity entity) {
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    private CityGetDTO returnToGetDTO(CityEntity entity) {
        CityGetDTO dto = new CityGetDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private CityEntity returnToEntity(CityCreateDTO dto) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        CityEntity entity = new CityEntity();
        entity.setDate(LocalDate.parse(dto.getDateStr(), dateTimeFormatter));
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
