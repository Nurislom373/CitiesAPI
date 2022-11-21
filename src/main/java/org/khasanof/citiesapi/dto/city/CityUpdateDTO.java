package org.khasanof.citiesapi.dto.city;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityUpdateDTO {
    @NotNull
    @Min(1)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 150)
    private String city;
    @NotBlank
    @Size(min = 2, max = 150)
    private String country;
}
