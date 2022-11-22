package org.khasanof.citiesapi.service.city;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.city.CityCreateDTO;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.city.CityUpdateDTO;
import org.khasanof.citiesapi.dto.city.CityWeatherUpdateDTO;
import org.khasanof.citiesapi.entity.city.CityEntity;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
                .mapNotNull((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    @Override
    public Mono<Void> updateWeather(CityWeatherUpdateDTO dto) {
        return repository.findById(dto.getId())
                .mapNotNull((o) -> mapToEntity(dto, o))
                .flatMap(repository::save).then();
    }

    @Override
    public Flux<CityGetDTO> list() {
        return repository.findAll()
                .map(this::returnToGetDTO);
    }

    private CityEntity mapToEntity(CityWeatherUpdateDTO dto, CityEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    private CityEntity mapToEntity(CityUpdateDTO dto, CityEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    private CityGetDTO returnToGetDTO(CityEntity entity) {
        CityGetDTO dto = new CityGetDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private CityEntity returnToEntity(CityCreateDTO dto) {
        CityEntity entity = new CityEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
