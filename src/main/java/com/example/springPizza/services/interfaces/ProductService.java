package com.example.springPizza.services.interfaces;

import com.example.springPizza.database.models.Product;
import com.example.springPizza.mappers.dtos.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(Long id);
    ProductDTO getProductById(Long id) throws Exception;
    List<ProductDTO> getProductByName(String name);
    List<ProductDTO> getProductByPriceLessThan(BigDecimal price); // gets products with price lower than price in args
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(String categoryName);
}