package com.example.springPizza.controllers;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/pizza/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        List<ProductResponse> productResponseList = productService.getAllProducts();
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/by_name")
    public ResponseEntity<List<ProductResponse>> findProductByName(@RequestParam(name = "name") String name){
        List<ProductResponse> productResponseList = productService.getProductsByName(name);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
    }

    @GetMapping("/by_price")
    public ResponseEntity<List<ProductResponse>> findProductByPrice(@RequestParam(name = "price") BigDecimal price){
        List<ProductResponse> productResponseList = productService.getProductsByPriceLessThan(price);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }

    }

    @GetMapping("/by_categoryId")
    public ResponseEntity<List<ProductResponse>> findProductByCategory(@RequestParam(name = "categoryId") Long categoryId){
        List<ProductResponse> productResponseList = productService.getProductsByCategory(categoryId);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponseList, HttpStatus.OK);
        }
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestParam("name") String name,
                                                         @RequestParam("price") BigDecimal price,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("categoryId") Long categoryId,
                                                         @RequestParam("image") MultipartFile image){
        ProductRequest request = ProductRequest.builder()
                .name(name)
                .price(price)
                .description(description)
                .categoryId(categoryId)
                .image(image)
                .build();
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(name = "id") Long id,
                                                         @RequestParam("name") String name,
                                                         @RequestParam("price") BigDecimal price,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("categoryId") Long categoryId,
                                                         @RequestParam("image") MultipartFile image) {
        ProductRequest request = ProductRequest.builder()
                .name(name)
                .price(price)
                .description(description)
                .categoryId(categoryId)
                .image(image)
                .build();
        return new ResponseEntity<>(productService.updateProduct(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
