package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllByUserId(Long userId);
    //    OrderResponse createOrder(OrderRequest request, UserDetails userDetails);
    OrderResponse updateOrder(Long id, OrderRequest request);
    void deleteOrder(Long id);
}
