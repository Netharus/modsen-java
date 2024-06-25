package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Long id, ProductRequest productRequest) throws Exception;
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductByName(String name);
    List<ProductResponse> getProductByPriceLessThan(BigDecimal price); // gets products with price lower than price in args
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory(Long categoryId);
}