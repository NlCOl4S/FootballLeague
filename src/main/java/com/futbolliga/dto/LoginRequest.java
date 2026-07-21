package com.futbolliga.dto;

public record LoginRequest(
        String email,
        String password
) {
}
