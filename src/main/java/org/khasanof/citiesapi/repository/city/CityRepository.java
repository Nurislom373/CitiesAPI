package org.khasanof.citiesapi.repository.city;

import org.khasanof.citiesapi.entity.city.CityEntity;
import org.khasanof.citiesapi.service.city.CityServiceImpl;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is used to save, delete and perform other operations on city objects.
 *
 * @author Khasanof373
 * @see CityRepository
 * @since 1.0
 */
@Repository
public interface CityRepository extends ReactiveCrudRepository<CityEntity, Integer> {
}
