package org.khasanof.citiesapi.dto.subscription;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionCreateDTO {
    @NotNull
    @Min(1)
    private Integer userId;
    @NotNull
    @Min(1)
    private Integer cityId;
}
