package ru.flamexander.transfer.service.limits.backend.errors;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, String s) {
        super(message);
    }
}
