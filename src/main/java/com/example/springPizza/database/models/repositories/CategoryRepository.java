package com.example.springPizza.database.models.repositories;

import com.example.springPizza.database.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
