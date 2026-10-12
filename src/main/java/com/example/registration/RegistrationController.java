package com.example.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationProperties properties;

    public RegistrationController(RegistrationProperties properties) {
        this.properties = properties;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse register(@Valid @RequestBody RegistrationRequest request) {
        if (request.age() < properties.getMinimumAge()) {
            throw new UnderageRegistrationException(properties.getMinimumAge());
        }

        return new RegistrationResponse(UUID.randomUUID(), request.email(), request.age());
    }
}