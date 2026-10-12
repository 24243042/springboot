package com.example.registration;

import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationPropertiesTest {

    @Test
    void rejectsConfiguredMinimumAgeBelowEighteen() {
        RegistrationProperties properties = new RegistrationProperties();
        properties.setMinimumAge(17);

        try (var validatorFactory = Validation.buildDefaultValidatorFactory()) {
            assertThat(validatorFactory.getValidator().validate(properties))
                    .anySatisfy(violation -> assertThat(violation.getMessage())
                            .isEqualTo("registration.minimum-age must be at least 18"));
        }
    }
}