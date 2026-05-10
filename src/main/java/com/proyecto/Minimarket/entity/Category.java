package com.proyecto.Minimarket.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    /**
     * creamos los datos de la tabla categories
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * creamos la llave primaria 
     */
    @Column(name = "name")
    private String name;
    /**
     * nombre de la categoria
     */
    @Column(name ="description")
    private String description;
    /**
     * descripcion de categorias
     */
    
    @OneToMany(mappedBy = "category")
    private List<Products> products;
    
    /**
     * se  utiliza para que la clase exista y funcioe el list y get 
     * @return
     */
    public List<Products> getProducts() {
    return products;
    }
    public void setProducts(List<Products> products) {
    this.products = products;
    }

}
