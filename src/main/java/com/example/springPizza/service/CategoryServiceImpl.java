package com.example.springPizza.service;

import com.example.springPizza.database.models.Category;
import com.example.springPizza.repositories.CategoryRepository;
import com.example.springPizza.database.models.dto.CategoryDTO;
import com.example.springPizza.service.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = convertFromDTO(categoryDTO);
        return categoryRepository.save(category);
    }


    //TODO: custom errors
    @Override
    @Transactional
    public Category updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        categoryRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Category category = convertFromDTO(categoryDTO);
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }else{
            log.info("Tried delete not existing category");
        }

    }

    //TODO: custom errors
    @Override
    public CategoryDTO getCategoryById(Long id) throws Exception {
        return convertToDTO(categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found")));
    }

    @Override
    public CategoryDTO getCategoryByName(String name) throws Exception {
        return convertToDTO(categoryRepository.findByName(name).orElseThrow(() -> new Exception("Category not found")));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    private Category convertFromDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}