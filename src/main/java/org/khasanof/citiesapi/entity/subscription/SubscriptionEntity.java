package org.khasanof.citiesapi.entity.subscription;

import lombok.*;
import org.khasanof.citiesapi.entity.Auditable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "subscription")
public class SubscriptionEntity extends Auditable {
    @Column(value = "city_id")
    private Integer cityId;
    @Column(value = "user_id")
    private Integer userId;
}
