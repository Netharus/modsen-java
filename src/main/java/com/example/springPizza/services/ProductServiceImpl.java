package com.example.springPizza.services;

import com.example.springPizza.models.Product;
import com.example.springPizza.mappers.dtos.ProductDTO;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = convertFromDTO(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        Product product = convertFromDTO(productDTO);
        return productRepository.saveAndFlush(product);
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
    public ProductDTO getProductById(Long id) throws Exception {
        return convertToDTO(productRepository.findById(id).orElseThrow(() -> new Exception("Product not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductByName(String name) {
        return productRepository.findAllByName(name).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductByPriceLessThan(BigDecimal price) {
        return productRepository.findAllByPriceLessThan(price).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        return productRepository.findAllByCategoryName(categoryName).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        // productDTO.setCategoryId(Long.valueOf(product.getCategoryId())); - ругается, потому что id теперь нет
        return productDTO;
    }

    private Product convertFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        // product.setCategoryId(Math.toIntExact(productDTO.getCategoryId())); - ругается, потому что id теперь нет
        return product;
    }

}