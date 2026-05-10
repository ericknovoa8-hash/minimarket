package com.proyecto.Minimarket.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Minimarket.dto.request.CategoryRequestDTO;
import com.proyecto.Minimarket.dto.response.CategoryResponseDTO;
import com.proyecto.Minimarket.dto.response.HttpGlobalResponse;
import com.proyecto.Minimarket.dto.response.MessageResponseDTO;
import com.proyecto.Minimarket.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
/**
 * recive las peticiones Http,devuelve Json y 
 * Spring la detecta como controller
 */
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createCategory(@RequestBody CategoryRequestDTO request){
        /**
         * crea las categorias con un codigo 201
         */
        try{
            MessageResponseDTO response =categoryService.createCategory(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/{id}")
    /**
     * maneja las peticiones si las encuentra arroja codigo 200 ok
     * si no lo encuentra aroja codigo 404 not found 
     * @param id
     * @return
     */
    public ResponseEntity<HttpGlobalResponse<CategoryResponseDTO>> getCategory(@PathVariable Long id){
        try{
            HttpGlobalResponse<CategoryResponseDTO> response = categoryService.getCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch(Exception e){
            HttpGlobalResponse<CategoryResponseDTO>error = new HttpGlobalResponse<>();
            error.setMessage("Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpGlobalResponse<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO request){
       try{
        CategoryResponseDTO updateCategory = categoryService.updateCategory(id, request);
        HttpGlobalResponse<CategoryResponseDTO> response = new HttpGlobalResponse<>();
        response.setMessage("categoría actualizada correctamente");
        response.setData(updateCategory);
        return ResponseEntity.ok(response);
        } catch (Exception e){
        HttpGlobalResponse<CategoryResponseDTO> error = new HttpGlobalResponse<>();
        error.setMessage("Error al actualizar la categoria");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
}
