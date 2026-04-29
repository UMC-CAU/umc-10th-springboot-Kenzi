package com.example.umc10th.health;

public record HealthResponse(String status, String message) {

    public static HealthResponse up(String message) {
        return new HealthResponse("UP", message);
    }

    public static HealthResponse down(String message) {
        return new HealthResponse("DOWN", message);
    }
}
