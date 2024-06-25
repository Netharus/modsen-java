package com.example.springPizza.services.interfaces;

import com.example.springPizza.mappers.dtos.OrderRequest;
import com.example.springPizza.mappers.dtos.OrderResponse;
import com.example.springPizza.database.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getALLByUserId(Long userId);
    OrderResponse createOrder(OrderRequest request);
    OrderResponse updateOrder(Long id, OrderRequest request) throws Exception;
    void deleteOrder(Long id);
}
