package com.example.springPizza.mappers;

import com.example.springPizza.models.Product;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.utils.ProductMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ProductMapperUtil.class
)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", qualifiedByName = "mapPhotoToName", source = "photo")
    Product toModel(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", qualifiedByName = "updatePhotoToName", source = "photo")
    void updateModel(ProductRequest productRequest, @MappingTarget Product product);

    @Mapping(target = "photo", qualifiedByName = "mapNameToPhoto", source = "photo")
    ProductResponse toResponse(Product product);

    @Mapping(target = "photo", qualifiedByName = "mapNameToPhoto", source = "photo")
    List<ProductResponse> toResponses(List<Product> products);
}
