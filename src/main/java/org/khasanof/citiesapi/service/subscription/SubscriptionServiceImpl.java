package org.khasanof.citiesapi.service.subscription;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.city.CityGetDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import org.khasanof.citiesapi.entity.subscription.SubscriptionEntity;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.khasanof.citiesapi.repository.subscription.SubscriptionRepository;
import org.khasanof.citiesapi.repository.user.UserRepository;
import org.khasanof.citiesapi.service.city.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;

    @Override
    public Mono<Void> subscribeToCity(SubscriptionCreateDTO dto) {
        return userRepository.existsById(dto.getUserId()).map((user) -> {
                    if (!user) {
                        throw new NotFoundException("User not found");
                    } else {
                        return dto.getCityId();
                    }
                }).flatMap(cityRepository::existsById).map(city -> {
                    if (!city) {
                        throw new NotFoundException("City not found");
                    } else {
                        return dto;
                    }
                }).flatMap(obj -> repository.existsByUserIdAndCityId(obj.getUserId(), obj.getCityId()))
                .map((val) -> {
                    if (val) {
                        throw new RuntimeException("Already Created Subscription");
                    } else {
                        return dto;
                    }
                }).flatMap(val -> Mono.just(val)
                        .map(o -> {
                            SubscriptionEntity subscription = new SubscriptionEntity();
                            BeanUtils.copyProperties(o, subscription);
                            return subscription;
                        })).flatMap(repository::save).then();
    }

    @Override
    public Mono<SubscriptionGetDTO> getSubscriptions(Integer userId) {
        return repository.findAllByUserId(userId)
                .switchIfEmpty(Mono.error(new NotFoundException("Subscription not found!")))
                .flatMap((subEntity) -> {
                    List<CityGetDTO> cities = new ArrayList<>();
                    return cityService.getDTO(subEntity.getCityId())
                            .map(cities::add)
                            .map((o) -> new SubscriptionGetDTO(userId, cities));
                }).single();
    }

}
