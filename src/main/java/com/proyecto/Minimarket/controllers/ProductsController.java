package com.proyecto.Minimarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Minimarket.dto.request.ProductsRequestDTO;
import com.proyecto.Minimarket.dto.response.MessageResponseDTO;
import com.proyecto.Minimarket.service.ProductsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductsController {
    private final ProductsService productsService;

    @PostMapping
    /**
     * creamos las categorias con un codigo 201
     * pero al fallar arrojaria 401
     * @param request
     * @return
     */
    public ResponseEntity<MessageResponseDTO> createProducts(@RequestBody ProductsRequestDTO request) {
        try {
            MessageResponseDTO response = productsService.createProducts(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            e.printStackTrace();
            MessageResponseDTO error = new MessageResponseDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
}
