package org.khasanof.citiesapi.dto.city;

import lombok.*;
import org.khasanof.citiesapi.enums.CityVisibility;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityWeatherUpdateDTO {
    @NotNull
    @Min(1)
    private Integer id;
    @NotNull
    @Min(-99)
    @Max(99)
    private Integer day;
    @NotNull
    @Min(-99)
    @Max(99)
    private Integer night;
    @NotNull
    private String info;
}
