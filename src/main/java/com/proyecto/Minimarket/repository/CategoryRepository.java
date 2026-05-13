package com.proyecto.Minimarket.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Minimarket.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
