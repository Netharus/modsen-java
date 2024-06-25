package com.example.springPizza.mappers;

import com.example.springPizza.database.models.Product;
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
//    @Mapping(target = "imageUrl", qualifiedByName = "mapImageToName", source = "imageUrl")
    Product toModel(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "imageUrl", qualifiedByName = "mapImageToName", source = "imageUrl")
    void updateModel(ProductRequest productRequest, @MappingTarget Product product);

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponses(List<Product> products);
}
