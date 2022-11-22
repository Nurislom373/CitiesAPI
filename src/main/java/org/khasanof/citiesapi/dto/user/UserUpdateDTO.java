package org.khasanof.citiesapi.dto.user;

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
public class UserUpdateDTO {
    @NotNull
    @Min(1)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 250)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 250)
    private String lastName;
    @NotBlank
    @Size(min = 4, max = 250)
    private String username;
}
