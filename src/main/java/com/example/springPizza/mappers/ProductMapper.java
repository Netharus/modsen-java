package com.example.springPizza.mappers;

import com.example.springPizza.database.models.Product;
import com.example.springPizza.mappers.dtos.ProductRequest;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.utils.ProductMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProductMapperUtil.class}
)
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", qualifiedByName = "mapPhotoToName", source = "photo")
    @Mapping(target = "categoryId", qualifiedByName = "mapCategoryNameToId", source = "categoryName")
    Product toModel(ProductRequest productRequest);

    @Mapping(target = "photo", qualifiedByName = "mapNameToPhoto", source = "photo")
    @Mapping(target = "categoryName", qualifiedByName = "mapCategoryIdToName", source = "categoryId")
    ProductResponse toResponse(Product product);

    @Mapping(target = "photo", qualifiedByName = "mapNameToPhoto", source = "photo")
    @Mapping(target = "categoryName", qualifiedByName = "mapCategoryIdToName", source = "categoryId")
    List<ProductResponse> toResponses(List<Product> products);
}
