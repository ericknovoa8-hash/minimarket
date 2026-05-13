package com.proyecto.Minimarket.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    
    private Long id;
    private String username;
    private String password;
    private Long rolId;
}
