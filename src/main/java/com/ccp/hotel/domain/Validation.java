package com.ccp.hotel.domain;

final class Validation {
    private Validation() {}
    static String text(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required");
        return value.trim();
    }
    static <T> T notNull(T value, String field) {
        if (value == null) throw new IllegalArgumentException(field + " is required");
        return value;
    }
    static int positive(int value, String field) {
        if (value <= 0) throw new IllegalArgumentException(field + " must be positive");
        return value;
    }
}
