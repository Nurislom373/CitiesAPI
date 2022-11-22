package org.khasanof.citiesapi.entity.city;

import lombok.*;
import org.khasanof.citiesapi.entity.Auditable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "city")
public class CityEntity extends Auditable {
    private String city;
    private String country;
    private Short day;
    private Short night;
    private LocalDate date;
    private String info;
}
