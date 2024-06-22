package com.example.springPizza.services;

import com.example.springPizza.models.Category;
import com.example.springPizza.mappers.dtos.CategoryDTO;
import com.example.springPizza.repositories.CategoryRepository;
import com.example.springPizza.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = convertFromDTO(categoryDTO);
        return categoryRepository.save(category);
    }

    //TODO: custom errors
    @Override
    public Category updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        categoryRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Category category = convertFromDTO(categoryDTO);
        return categoryRepository.saveAndFlush(category);
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
    public CategoryDTO getCategoryById(Long id) throws Exception {
        return convertToDTO(categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getCategoryByName(String name) throws Exception {
        return convertToDTO(categoryRepository.findByName(name).orElseThrow(() -> new Exception("Category not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        // categoryDTO.setId(category.getId()); - ругается, потому что id теперь нет
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    private Category convertFromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        // category.setId(categoryDTO.getId()); - ругается, потому что id теперь нет
        category.setName(categoryDTO.getName());
        return category;
    }
}