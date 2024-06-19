package com.example.springPizza.service;

import com.example.springPizza.database.models.Product;
import com.example.springPizza.database.models.dto.ProductDTO;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        Product product = convertFromDTO(productDTO);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Product product = convertFromDTO(productDTO);
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            log.info("Tried delete not existing product");
        }
    }

    @Override
    public ProductDTO getProductById(Long id) throws Exception {
        return convertToDTO(productRepository.findById(id).orElseThrow(() -> new Exception("Product not found")));
    }

    @Override
    public List<ProductDTO> getProductByName(String name) {
        return productRepository.findAllByName(name).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductByPriceLessThan(BigDecimal price) {
        return productRepository.findAllByPriceLessThan(price).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(Long.valueOf(product.getCategoryId()));
        return productDTO;
    }

    private Product convertFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategoryId(Math.toIntExact(productDTO.getCategoryId()));
        return product;
    }

}