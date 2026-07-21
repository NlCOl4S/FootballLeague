package com.futbolliga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//Se ven asi para que en la peticion del usuario se escriba asi access_token, pero en java es accessToken
public record TokenResponse(
        @JsonProperty("access_token") //Token rapido
        String accessToken,
        @JsonProperty("refresh_token") //Token duradero
        String refreshToken
) {
}
