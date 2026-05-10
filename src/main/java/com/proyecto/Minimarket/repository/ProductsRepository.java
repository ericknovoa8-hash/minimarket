package com.proyecto.Minimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Minimarket.entity.Products;

@Repository

public interface ProductsRepository extends JpaRepository<Products, Long>{

}