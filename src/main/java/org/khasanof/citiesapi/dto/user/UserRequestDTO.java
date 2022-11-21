package org.khasanof.citiesapi.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
    @NotBlank
    @Size(min = 4, max = 120)
    private String username;
    @NotBlank
    @Size(min = 4, max = 120)
    private String password;
}
