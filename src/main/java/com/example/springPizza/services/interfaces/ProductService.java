package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByName(String name);
    List<ProductResponse> getProductsByPriceLessThan(BigDecimal price); // gets products with price lower than price in args
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(Long id);
}