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
    private String firstName;
    private String lastName;
    private UserRole role;
    private String username;
    private String password;
}
