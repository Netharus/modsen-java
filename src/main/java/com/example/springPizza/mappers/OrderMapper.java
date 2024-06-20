package com.example.springPizza.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "userId", qualifiedByName = "mapUserLoginToId")
//    Order toModel(OrderRequest orderRequest);
//
//    OrderResponse toResponse(Order order);
//
//    List<OrderResponse> toResponses(List<Order> order);
}
