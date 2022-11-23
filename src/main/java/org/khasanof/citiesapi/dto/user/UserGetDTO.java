package org.khasanof.citiesapi.dto.user;

import lombok.*;
import org.khasanof.citiesapi.enums.UserRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserGetDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
}
