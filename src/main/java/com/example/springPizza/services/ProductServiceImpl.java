package com.example.springPizza.services;

import com.example.springPizza.mappers.ProductMapper;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.database.models.Product;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toModel(request);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws Exception {
        productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Product product = productMapper.toModel(productRequest);
        product.setId(id);
        productRepository.saveAndFlush(product);
        return productMapper.toResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing product");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse getProductById(Long id){
        return productMapper.toResponse(productRepository.getById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductByName(String name) {
        return productMapper.toResponses(productRepository.findAllByName(name));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductByPriceLessThan(BigDecimal price) {
        return productMapper.toResponses(productRepository.findAllByPriceLessThan(price));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAllProducts() {
        return  productMapper.toResponses(productRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productMapper.toResponses(productRepository.findAllByCategoryId(categoryId));
    }

}