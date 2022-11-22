package org.khasanof.citiesapi.service.subscription;

import lombok.RequiredArgsConstructor;
import org.khasanof.citiesapi.dto.subscription.SubscriptionCreateDTO;
import org.khasanof.citiesapi.dto.subscription.SubscriptionGetDTO;
import org.khasanof.citiesapi.entity.subscription.SubscriptionEntity;
import org.khasanof.citiesapi.repository.city.CityRepository;
import org.khasanof.citiesapi.repository.subscription.SubscriptionRepository;
import org.khasanof.citiesapi.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Override
    public Mono<Void> subscribeToCity(SubscriptionCreateDTO dto) {
        checkSubCrDTO(dto);
        SubscriptionEntity entity = new SubscriptionEntity();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity).then();
    }

    @Override
    public Flux<SubscriptionGetDTO> getSubscriptions(Integer userId) {
        return null;
    }

    private void checkSubCrDTO(SubscriptionCreateDTO dto) {
        userRepository.existsById(dto.getUserId()).map(o -> !o)
                .onErrorResume(e -> Mono.error(new RuntimeException("User not found")));
        cityRepository.existsById(dto.getCityId()).map(o -> !o)
                .onErrorResume(e -> Mono.error(new RuntimeException("City not found")));
    }
}
