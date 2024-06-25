package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) throws Exception;
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse getCategoryByName(String name);
    List<CategoryResponse> getAllCategories();
}