package com.example.springPizza.services;

import com.example.springPizza.exceptions.ProductNotFoundException;
import com.example.springPizza.mappers.ProductMapper;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.models.Image;
import com.example.springPizza.models.Product;
import com.example.springPizza.repositories.ProductRepository;
import com.example.springPizza.services.interfaces.CategoryService;
import com.example.springPizza.services.interfaces.ImageService;
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
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productMapper.toModel(request);
        Image image = imageService.createImage(request.getImage());

        product.setImageId(image.getId());
        productRepository.save(product);

        return productMapper.toResponse(product, image, categoryService.getCategoryById(product.getCategoryId()));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product oldProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Image image = imageService.updateImage(oldProduct.getImageId(), productRequest.getImage());

        Product product = productMapper.toModel(productRequest);
        product.setId(id);
        product.setImageId(image.getId());
        productRepository.saveAndFlush(product);

        return productMapper.toResponse(product, image, categoryService.getCategoryById(product.getCategoryId()));
    }

    @Override
    public void deleteProduct(Long id) {
        Product deleteProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
        imageService.deleteImage(deleteProduct.getImageId());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Image image = imageService.getImageById(product.getImageId());
        return productMapper.toResponse(product, image, categoryService.getCategoryById(product.getCategoryId()));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductsByName(String name) {
        return getProductResponses(productRepository.findAllByName(name));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductsByPriceLessThan(BigDecimal price) {
        return getProductResponses(productRepository.findAllByPriceLessThan(price));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getAllProducts() {
        return getProductResponses(productRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductResponse> getProductsByCategory(Long id) {
        return getProductResponses(productRepository.findAllByCategoryId(id));
    }

    private List<ProductResponse> getProductResponses(List<Product> products) {
        List<Image> images = products.stream().map(product -> imageService.getImageById(product.getImageId())).toList();
        List<CategoryResponse> categories = products.stream().map(product -> categoryService.getCategoryById(product.getCategoryId())).toList();

        return productMapper.toResponses(products, images, categories);
    }

}