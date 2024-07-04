package com.example.springPizza.mappers;

import com.example.springPizza.mappers.dtos.CategoryResponse;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.models.Image;
import com.example.springPizza.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageId", ignore = true)
    Product toModel(ProductRequest productRequest);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "imageUrl", source = "image.url")
    @Mapping(target = "category", source = "category")
    ProductResponse toResponse(Product product, Image image, CategoryResponse category);

    default List<ProductResponse> toResponses(List<Product> products, List<Image> images, List<CategoryResponse> categoryResponses) {
        return IntStream.range(0, products.size()).mapToObj(index -> toResponse(products.get(index), images.get(index), categoryResponses.get(index))).toList();
    }
}
