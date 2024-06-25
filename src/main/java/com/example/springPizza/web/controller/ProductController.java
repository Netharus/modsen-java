package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pizza/main")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductResponse> findAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/{id}")
    public ProductResponse findProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{name}")
    public List<ProductResponse> findProductByName(@PathVariable(name = "name") String name){
        return productService.getProductByName(name);
    }

    @PostMapping("/{price}")
    public List<ProductResponse> findProductByPrice(@PathVariable(name = "price") BigDecimal price){
        return productService.getProductByPriceLessThan(price);
    }

    @PostMapping("/{categoryId}")
    public List<ProductResponse> findProductByCategory(@PathVariable(name = "categoryId") String categoryName){
        return productService.getProductsByCategory(categoryName);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductResponse createProduct(@RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @PutMapping("/search/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductResponse updateProduct(@PathVariable(name = "id") Long id ,@RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/search/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
    }



}
