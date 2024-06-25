package com.example.springPizza.services;

import com.example.springPizza.mappers.CategoryMapper;
import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.database.models.Category;
import com.example.springPizza.repositories.CategoryRepository;
import com.example.springPizza.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toModel(categoryRequest);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    //TODO: custom errors
    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) throws Exception {
        categoryRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Category category = categoryMapper.toModel(categoryRequest);
        categoryRepository.saveAndFlush(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing category");
        }

    }

    //TODO: custom errors
    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryById(Long id){
        return categoryMapper.toResponse(categoryRepository.findCategoryById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryByName(String name){
        return categoryMapper.toResponse(categoryRepository.findCategoryByName(name));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toResponses(categoryRepository.findAll());
    }

}