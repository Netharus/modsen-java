package com.example.springPizza.services.interfaces;

import com.example.springPizza.models.Category;
import com.example.springPizza.mappers.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
    void deleteCategory(Long id);
    CategoryDTO getCategoryById(Long id) throws Exception; //TODO: add Exceptions for all models: CategoryNotFoundException
    CategoryDTO getCategoryByName(String name) throws Exception;
    List<CategoryDTO> getAllCategories();
}