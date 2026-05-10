package com.proyecto.Minimarket.dto.response;

import lombok.Data;

@Data
/**
 * Es la forma de generar los resultados en Http 
 */
public class HttpGlobalResponse <T>{
    private String message;
    private T data;
    
}
