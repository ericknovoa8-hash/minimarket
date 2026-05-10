package com.proyecto.Minimarket.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {
    /**
     * creamos la lave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * creamos los nombres de products
     */
    @Column(name = "name")
    private String name;
    /**
     * codigo de barras
     */
    @Column(name = "barcode")
    private String barcode;
    /**
     * creamos los prince
     */
    @Column(name = "price")
    private BigDecimal price;
    /**
     * creamos los stock
     */
    @Column(name = "stock")
    private int stock;
    /**
     * creamos los activos
     */
    @Column(name = "active")
    private boolean active;
    /**
     * aqui unimos las tablas con las llaves priaria 
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
}
