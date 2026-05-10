package com.proyecto.Minimarket.service;


import org.springframework.stereotype.Service;

import com.proyecto.Minimarket.dto.request.ProductsRequestDTO;
import com.proyecto.Minimarket.dto.response.MessageResponseDTO;
import com.proyecto.Minimarket.entity.Category;
import com.proyecto.Minimarket.entity.Products;
import com.proyecto.Minimarket.repository.CategoryRepository;
import com.proyecto.Minimarket.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    /**
     * el repositori para las operaciones
     */
    private final ProductsRepository productsRepository;
    /**
     * el repositori para las consultas
     */
    private final CategoryRepository categoryRepository;
    /**
     * es la solicitud que ingresa el usuario para la creacion
     * @param request
     * @return
     */
    public MessageResponseDTO createProducts(ProductsRequestDTO request){
        /**
         * devuelve el mensaje final
         */
        MessageResponseDTO response = new MessageResponseDTO();
        /**
         * se crea l entidad del producto
         */
        Products products = new Products();

        products.setName(request.getName());
        products.setBarcode(request.getBarcode());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());
        products.setActive(request.isActive());

        Category category = categoryRepository.findById(request.getCategory_id())
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        /**
         * relacionamos el producto con la categoria
         * save guarda el producto
         * 
         */
        products.setCategory(category);

        productsRepository.save(products);

        response.setMessage("Producto creado exitosamente");

        return response;
    }
}