package com.example.springPizza.web.controller;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.models.Order;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        List<ProductResponse> productResponseList = productService.getAllProducts();
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}")
    public ProductResponse findProductById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{name}")
    public ResponseEntity<List<ProductResponse>> findProductByName(@PathVariable(name = "name") String name){
        List<ProductResponse> productResponseList = productService.getProductByName(name);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
    }

    @PostMapping("/{price}")
    public ResponseEntity<List<ProductResponse>> findProductByPrice(@PathVariable(name = "price") BigDecimal price){
        List<ProductResponse> productResponseList = productService.getProductByPriceLessThan(price);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }

    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findProductByCategory(@PathVariable(name = "categoryId") String categoryName){
        List<ProductResponse> productResponseList = productService.getProductsByCategory(categoryName);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
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
