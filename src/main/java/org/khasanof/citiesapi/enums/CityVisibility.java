package org.khasanof.citiesapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CityVisibility {
    CLEAR("CLEAR"),
    SUNNY("SUNNY");
    private final String value;

    public static boolean hasVisibility(String value) {
        return Arrays.stream(values())
                .anyMatch(val -> val.getValue()
                        .equals(value));
    }
}
