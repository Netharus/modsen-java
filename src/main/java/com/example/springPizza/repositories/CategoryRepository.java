package com.example.springPizza.repositories;

import com.example.springPizza.database.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
