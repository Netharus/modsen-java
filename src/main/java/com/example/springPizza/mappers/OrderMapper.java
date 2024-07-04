package com.example.springPizza.mappers;

import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.mappers.dtos.ProductResponse;
import com.example.springPizza.mappers.dtos.UserResponse;
import com.example.springPizza.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ProductMapper.class
)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Order toModel(OrderRequest orderRequest);
    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "products", source = "products")
    OrderResponse toResponse(Order order, UserResponse user, List<ProductResponse> products);

    default List<OrderResponse> toResponses(List<Order> orders, List<UserResponse> users, List<List<ProductResponse>> products) {
        if (orders.size() != users.size() || orders.size() != products.size()) {
            throw new IllegalArgumentException("Размеры списков orders, users и products должны быть одинаковы");
        }

        return IntStream.range(0, orders.size())
                .mapToObj(i -> toResponse(orders.get(i), users.get(i), products.get(i)))
                .toList();
    }
}
