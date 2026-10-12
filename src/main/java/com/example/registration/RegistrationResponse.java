package com.example.registration;

import java.util.UUID;

public record RegistrationResponse(UUID id, String email, int age) {
}