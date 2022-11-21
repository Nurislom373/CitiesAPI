package org.khasanof.citiesapi.dto.subscription;

import lombok.*;
import org.khasanof.citiesapi.dto.city.CityGetDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionGetDTO {
    private Integer userId;
    private List<CityGetDTO> subscriptions;
}
