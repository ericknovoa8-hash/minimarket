package com.proyecto.Minimarket.dto.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    /**
     * recibe los datos del login
     */
    private String username;
    private String password;
}
