package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pizza/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {


    private final ProductService productService;

    @GetMapping()
    public List<ProductResponse> findAllProducts(){
        return productService.getAllProducts();
    }


    @PostMapping("/{id}")
    public ProductResponse findProductById(@PathVariable(name = "id") Long id){
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
    public List<ProductResponse> findProductByCategory(@PathVariable(name = "categoryId") Long categoryId){
        return productService.getProductsByCategory(categoryId);
    }

//    only for admin

    @PostMapping("/change")
    public ProductResponse createProduct(@RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @PutMapping("/change/{id}")
    public ProductResponse updateProduct(@PathVariable(name = "id") Long id ,@RequestBody ProductRequest request) throws Exception{
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/change/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
    }



}
