package com.proyecto.Minimarket.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.proyecto.Minimarket.dto.request.CategoryRequestDTO;
import com.proyecto.Minimarket.dto.response.CategoryResponseDTO;
import com.proyecto.Minimarket.dto.response.ProductsResponseDTO;
import com.proyecto.Minimarket.entity.Category;
import com.proyecto.Minimarket.entity.Products;
import com.proyecto.Minimarket.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    /**
     * coneccion con la vase de datos con categoryRepository
     */
    private final CategoryRepository categoryRepository;

    public MessageResponseDTO createCategory(CategoryRequestDTO request) {
        /**
         * registro de la categora a partir de la informacion del usuario
         */
        MessageResponseDTO response = new MessageResponseDTO();
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        categoryRepository.save(category);
        response.setMessage("categoria cread correctamente ");
        return response;

    }


    /**
     * metodo para listar todas las categorias
     * y enviarla en formato DTO
     * @return
     */
    public List<CategoryResponseDTO> getCateries(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> response = new ArrayList<>();
        for (Category category: categories){
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());
            response.add(dto);
           
        }
        
         return response;
    }

        /**
         * metodo para buscar categorias por su id
         * y mostrar la informacion junto sus productos 
         * @param id
         * @return
         */
        public HttpGlobalResponse<CategoryResponseDTO> getCateegory(Long id){
            HttpGlobalResponse<CategoryResponseDTO> response = new HttpGlobalResponse<>();
            Category category = categoryRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("categoria no encontrada "));
            CategoryResponseDTO responsecategory = new CategoryResponseDTO();
            responsecategory.setId(category.getId());
            responsecategory.setName(category.getName());
            responsecategory.setDescription(category.getDescription());
            List<ProductsResponseDTO> list = new ArrayList<>();

            for (Products products: category.getProducts()){
                ProductsResponseDTO responseProducts = new ProductsResponseDTO();
                responseProducts.setId(products.getId());
                responseProducts.setName(products.getName());
                responseProducts.setBarcode(products.getBarcode());
                responseProducts.setPrice(products.getPrice());
                responseProducts.setStock(products.getStock());
                responseProducts.setActive(products.isActive());
                responseProducts.setCategoryId(id);
                list.add(responseProducts);
            }
            responsecategory.setProducts(list);
            response.setMessage("categoria y sus productos encontrados ");
            response.setData(responsecategory);
            return response;

        }
        /**
         * metodo para la actualizacion con su id
         * y devuelve actualizada
         * @param id
         * @param request
         * @return
         */
        public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request){
            Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("categoria no encontrada"));
                category.setName(request.getName());
                category.setDescription(request.getDescription());
                categoryRepository.save(category);

                CategoryResponseDTO response = new CategoryResponseDTO();
                response.setId(category.getId());
                response.setName(category.getName());
                response.setDescription(category.getDescription());
                return response;           
            
    }

    
}
