package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.models.Category;
import com.example.springPizza.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllCategory(){
        List<CategoryResponse> categoryList = categoryService.getAllCategories();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public CategoryResponse findCategoryById(@PathVariable(name = "id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/getBy/{name}")
    public CategoryResponse findCategoryByName(@PathVariable(name = "name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryRequest.getName(), categoryRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);
    }

}
