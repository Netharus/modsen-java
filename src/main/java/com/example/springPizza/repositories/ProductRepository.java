package com.example.springPizza.repositories;

import com.example.springPizza.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
    List<Product> findAllByName(String name);
    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE price < :price")
    List<Product> findAllByPriceLessThan(@Param("price") BigDecimal price);

}
