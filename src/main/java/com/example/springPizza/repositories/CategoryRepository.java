package com.example.springPizza.repositories;

import com.example.springPizza.database.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Category findCategoryById(Long id);

    Category findCategoryByName(String name);
}
