package com.example.springPizza.repositories;

import com.example.springPizza.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductsById(Long id);

    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findAllByName(String name);
    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE price < :price")
    List<Product> findAllByPriceLessThan(@Param("price") BigDecimal price);



}
