package com.futbolliga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        @JsonProperty("access_token") //Token rapido
        String accessToken,
        @JsonProperty("refresh_token") //Token duradero
        String refreshToken
) {
}
