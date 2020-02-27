package ru.unisuite.jdbc.driver.retry;

public class DriverRegistrationException extends RuntimeException {
    public DriverRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
