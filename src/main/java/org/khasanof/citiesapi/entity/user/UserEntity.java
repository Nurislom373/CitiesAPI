package org.khasanof.citiesapi.entity.user;

import lombok.*;
import org.khasanof.citiesapi.entity.Auditable;
import org.khasanof.citiesapi.enums.UserRole;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class UserEntity extends Auditable {
    @Column(value = "first_name")
    private String firstName;
    @Column(value = "last_name")
    private String lastName;
    private UserRole role;
    private String username;
    private String password;
}
