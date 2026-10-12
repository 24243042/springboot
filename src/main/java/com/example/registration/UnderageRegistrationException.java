package com.example.registration;

public class UnderageRegistrationException extends RuntimeException {

    private final int minimumAge;

    public UnderageRegistrationException(int minimumAge) {
        super("Registration requires an age of at least " + minimumAge + ".");
        this.minimumAge = minimumAge;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}