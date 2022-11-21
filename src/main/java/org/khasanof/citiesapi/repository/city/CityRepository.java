package org.khasanof.citiesapi.repository.city;

import org.khasanof.citiesapi.entity.city.CityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveCrudRepository<CityEntity, Integer> {
}
