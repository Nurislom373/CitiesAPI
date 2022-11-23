package org.khasanof.citiesapi.dto.user;

import lombok.*;
import org.khasanof.citiesapi.enums.UserRole;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String role;
    private String username;
    private String password;
}
