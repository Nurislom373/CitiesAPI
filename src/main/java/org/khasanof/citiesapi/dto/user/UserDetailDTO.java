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
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
