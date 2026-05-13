package com.proyecto.Minimarket.dto.response;

import lombok.Data;

@Data
public class RefreshTokenResponse {
    private String message;
    private String jwt;
}
