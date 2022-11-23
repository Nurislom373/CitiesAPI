package org.khasanof.citiesapi.dto.city;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityCreateDTO {
    @NotBlank
    @Size(min = 2, max = 150)
    private String city;
    @NotBlank
    @Size(min = 2, max = 150)
    private String country;
    @NotNull
    @Min(-99)
    @Max(99)
    private Integer day;
    @NotNull
    @Min(-99)
    @Max(99)
    private Integer night;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateStr;
    @NotBlank
    private String info;
}
