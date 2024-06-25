package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pizza/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryResponse> findAllCategory(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/{id}")
    public CategoryResponse findCategoryById(@PathVariable(name = "id") Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/{name}")
    public CategoryResponse findCategoryByName(@PathVariable(name = "name") String name){
        return categoryService.getCategoryByName(name);
    }

//  for admin

    @PostMapping("/change")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/change/{id}")
    public CategoryResponse updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryRequest categoryRequest) throws Exception {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/change/{id}")
    public void deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);
    }

}
