package com.proyecto.Minimarket.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
/**
 * el dto recibe los datos 
 */
public class ProductsRequestDTO {
    private String name;
    private String barcode;
    private BigDecimal price;
    private int stock;
    private boolean active;
    private Long category_id;

}
