package com.proyecto.Minimarket.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    /**
     * Responde con jwt
     */
    private String jwt;
    private String message;
}
