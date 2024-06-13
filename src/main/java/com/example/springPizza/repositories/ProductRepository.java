package com.example.springPizza.repositories;

import com.example.springPizza.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
}
