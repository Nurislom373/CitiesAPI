package org.khasanof.citiesapi.entity.user;

import lombok.*;
import org.khasanof.citiesapi.entity.Auditable;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "auth_user")
public class UserEntity extends Auditable {
    private String firstname;
    private String lastname;
    private String role;
    private String username;
    private String password;
}
