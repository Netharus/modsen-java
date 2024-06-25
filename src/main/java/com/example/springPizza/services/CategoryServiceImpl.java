package com.example.springPizza.services;

import com.example.springPizza.exceptions.CategoryNotFoundException;
import com.example.springPizza.mappers.CategoryMapper;
import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.models.Category;
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

    @Override
    public CategoryResponse updateCategory(String name, CategoryRequest categoryRequest)  {
        categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
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

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toResponse(categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryByName(String name) {
        return categoryMapper.toResponse(categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toResponses(categoryRepository.findAll());
    }

}