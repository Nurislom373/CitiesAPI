package org.khasanof.citiesapi.dto.city;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityGetDTO {
    private String city;
    private String country;
    private Short day;
    private Short night;
    private LocalDate date;
    private String info;
}
