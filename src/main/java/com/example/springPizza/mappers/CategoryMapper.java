package com.example.springPizza.mappers;

import com.example.springPizza.database.models.Category;
import com.example.springPizza.mappers.dtos.CategoryRequest;
import com.example.springPizza.mappers.dtos.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toModel(CategoryRequest categoryRequest);

    void updateModel(CategoryRequest categoryRequest, @MappingTarget Category category);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponses(List<Category> categories);
}
