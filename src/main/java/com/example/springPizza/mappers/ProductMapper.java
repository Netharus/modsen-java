package com.example.springPizza.mappers;

import com.example.springPizza.models.Image;
import com.example.springPizza.models.Product;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageId", ignore = true)
    Product toModel(ProductRequest productRequest);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "imageUrl", source = "image.url")
    ProductResponse toResponse(Product product, Image image);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "imageUrl", source = "image.url")
    List<ProductResponse> toResponses(List<Product> products);
}
