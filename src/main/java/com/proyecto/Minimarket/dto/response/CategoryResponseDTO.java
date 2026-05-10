package com.proyecto.Minimarket.dto.response;

import java.util.List;


import lombok.Data;

@Data
/**
 * salida de los datos
 */
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<ProductsResponseDTO> products;
    
}
