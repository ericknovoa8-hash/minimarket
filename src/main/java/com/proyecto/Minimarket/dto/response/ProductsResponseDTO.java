package com.proyecto.Minimarket.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
/**
 * dto utilizado para la salida de datos 
 */
public class ProductsResponseDTO {
    private Long id;
    private String name;
    private String barcode;
    private BigDecimal price;
    private int stock;
    private boolean active;
    private Long category_id;
}
